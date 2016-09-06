package com.tccv.mina.codec.appPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by ywy on 16/9/6.
 * 服务端使用的软POS信息解码器
 *
 *
 * 协议如下(每一行都有换行符):
 * ~
 * A:1.0
 * C:doPay
 * L:1024
 * M:{"name":"styf"}
 *
 */
public class AppPOSmsgDecoder implements MessageDecoder {

    private final Charset charset;
    private final AttributeKey CONTEXT = new AttributeKey(getClass(), "AppPOSmsgContext");

    public AppPOSmsgDecoder( ) {
        this.charset = Charset.forName("utf-8");
    }

    public AppPOSmsgDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {

        Context ctx = getContext(ioSession);
        if (ctx.getMark() == 126){
            //已经在一次解码流程中了
            return MessageDecoderResult.OK;
        }else{
            if (ioBuffer.remaining() < 2){
                return MessageDecoderResult.NEED_DATA;
            }else{

                CharsetDecoder cd = charset.newDecoder();
                String tempMark = null;
                try {
                    tempMark = ioBuffer.getString(1,cd);
                } catch (CharacterCodingException e) {
                    e.printStackTrace();
                }
                char mark = tempMark.charAt(0);
                if (mark == 126){//  '~'的ASCII
                    //可以使用这个解码器
                    return MessageDecoderResult.OK;
                }else{
                    //不适用这个解码器
                    return MessageDecoderResult.NOT_OK;
                }
            }
        }
    }

    @Override
    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
        Context ctx = getContext(ioSession);
        CharsetDecoder cd = charset.newDecoder();
        int matchCount = ctx.getMatchCount();//用来统计读到了第几个字符
        int line = ctx.getLine();//用来统计读到了第几行
        IoBuffer buffer = ctx.innerBuffer;//缓存每一行的数据
        char mark = ctx.getMark();
        String apiVersion = ctx.getApiVersion();
        String cmd = ctx.getCmd();
        int bodyLength = ctx.getBodyLength();
        String jsonMsg = ctx.getJsonMsg();

        while (ioBuffer.hasRemaining()){
            byte b = ioBuffer.get();
            matchCount++;
            buffer.put(b);

            if (line < 4&& b == 10){//读到的第一个字符是换行符(也就是说刚好读完了一行)
                if (line == 0) {//第一行: ~\n
                    buffer.flip();
                    String tempMark = buffer.getString(matchCount,cd);
                    mark = tempMark.charAt(0);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setMark(mark);

                }

                if (line == 1) {//第二行: A:1.0\n
                    buffer.flip();
                    apiVersion = buffer.getString(matchCount, cd);
                    apiVersion = apiVersion.substring(0, apiVersion.length() - 1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setApiVersion(apiVersion.split(":")[1]);
                }

                if (line == 2) {//第三行: C:doPay\n
                    buffer.flip();
                    cmd = buffer.getString(matchCount, cd);
                    cmd = cmd.substring(0, cmd.length() - 1);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setCmd(cmd.split(":")[1]);
                }

                if (line == 3) {//第四行: L:1024\n
                    buffer.flip();
                    String tempBodyLength = buffer.getString(matchCount, cd);
                    tempBodyLength = tempBodyLength.substring(0, tempBodyLength.length() - 1);
                    bodyLength = Integer.valueOf(tempBodyLength.split(":")[1]);
                    matchCount = 0;
                    buffer.clear();
                    ctx.setBodyLength(bodyLength);
                }
                line++;
            }else if (line == 4){//第五行: M:{"name":"styf"}\n
                if (matchCount == bodyLength){
                    buffer.flip();
                    jsonMsg = buffer.getString(matchCount, cd);
                    jsonMsg = jsonMsg.substring(2,jsonMsg.length() - 1);//去掉 M: 和 \n
                    ctx.setJsonMsg(jsonMsg);
                    // 由于下面的break,这里需要调用else外面的两行代码
                    ctx.setMatchCount(matchCount);
                    ctx.setLine(line);
                    break;
                }

            }
            //记录本次读取后的位置
            ctx.setMatchCount(matchCount);
            ctx.setLine(line);
        }

        if (ctx.getLine() == 4 && ctx.getBodyLength() == ctx.getMatchCount()){
            AppPOSmsg msg = new AppPOSmsg(ctx.getMark(),ctx.getApiVersion(),ctx.getCmd(),ctx.getBodyLength(),ctx.getJsonMsg());
            out.write(msg);
            ctx.reset();
            return MessageDecoderResult.OK;
        }else{
            return MessageDecoderResult.NEED_DATA;
        }



    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }


    private Context getContext(IoSession session) {
        Context context = (Context) session.getAttribute(CONTEXT);
        if (context == null) {
            context = new Context();
            session.setAttribute(CONTEXT, context);
        }
        return context;
    }

    private class Context {
        private final IoBuffer innerBuffer;
        private int matchCount = 0;
        private int line = 0;
        private char mark;
        private String apiVersion;
        private String cmd;
        private int bodyLength;
        private String jsonMsg;

        public Context() {
            innerBuffer = IoBuffer.allocate(100).setAutoExpand(true);
        }

        public void reset() {
            this.innerBuffer.clear();
            this.matchCount = 0;
            this.line = 0;
            this.mark = 0;
            this.apiVersion = "";
            this.cmd = "";
            this.bodyLength = 0;
            this.jsonMsg = "";
        }

        public IoBuffer getInnerBuffer() {
            return innerBuffer;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public void setMatchCount(int matchCount) {
            this.matchCount = matchCount;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public char getMark() {
            return mark;
        }

        public void setMark(char mark) {
            this.mark = mark;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public int getBodyLength() {
            return bodyLength;
        }

        public void setBodyLength(int bodyLength) {
            this.bodyLength = bodyLength;
        }

        public String getJsonMsg() {
            return jsonMsg;
        }

        public void setJsonMsg(String jsonMsg) {
            this.jsonMsg = jsonMsg;
        }
    }


}


