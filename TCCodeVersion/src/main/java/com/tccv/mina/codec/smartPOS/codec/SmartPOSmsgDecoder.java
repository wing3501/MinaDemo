package com.tccv.mina.codec.smartPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSmsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by ywy on 16/9/7.
 */
public class SmartPOSmsgDecoder implements MessageDecoder {
    private final Charset charset;

    public SmartPOSmsgDecoder( ) {
        this.charset = Charset.forName("utf-8");
    }

    public SmartPOSmsgDecoder(Charset charset) {
        this.charset = charset;
    }

    private final AttributeKey CONTEXT = new AttributeKey(getClass(), "SmartPOSmsgContext");


    @Override
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        Context ctx = getContext(ioSession);
        if (ctx.getMark() == 43){
            //已经在一次解码流程中了
            return MessageDecoderResult.OK;
        }else{
            if (ioBuffer.remaining() < 6){
                return MessageDecoderResult.NEED_DATA;
            }else{
                char mark = ioBuffer.getChar();
                if (mark == 43){//  '+'的ASCII
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
        int matchCount = ctx.getMatchCount();//用来统计读到了body第几个字节
        int bodyLength = ctx.getBodyLength();
        String bodyContent = ctx.getBodyContent();
        IoBuffer buffer = ctx.innerBuffer;//缓存每一行的数据
        char mark = ctx.getMark();

        while (ioBuffer.hasRemaining()){
            if (mark == 0){
                mark = ioBuffer.getChar();
                bodyLength = ioBuffer.getInt();
                ctx.setMark(mark);
                ctx.setBodyLength(bodyLength);
            }else{
                byte b = ioBuffer.get();
                matchCount++;
                buffer.put(b);

                ctx.setMatchCount(matchCount);
                if (matchCount == bodyLength){
                    buffer.flip();
                    bodyContent = buffer.getString(matchCount, cd);
                    ctx.setBodyContent(bodyContent);
                    break;
                }
            }
        }


        if (matchCount == bodyLength){
            SmartPOSmsg msg = new SmartPOSmsg(ctx.getMark(),ctx.getBodyContent(),ctx.getBodyLength());
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
        private char mark;
        private int bodyLength;
        private String bodyContent;

        public Context() {
            innerBuffer = IoBuffer.allocate(100).setAutoExpand(true);
        }

        public void reset() {
            this.innerBuffer.clear();
            this.matchCount = 0;
            this.mark = 0;
            this.bodyLength = 0;
            this.bodyContent = "";
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

        public char getMark() {
            return mark;
        }

        public void setMark(char mark) {
            this.mark = mark;
        }

        public int getBodyLength() {
            return bodyLength;
        }

        public void setBodyLength(int bodyLength) {
            this.bodyLength = bodyLength;
        }

        public String getBodyContent() {
            return bodyContent;
        }

        public void setBodyContent(String bodyContent) {
            this.bodyContent = bodyContent;
        }
    }


    public static void main(String[] args) throws CharacterCodingException {
        Charset charset = Charset.forName("utf-8");
        CharsetEncoder ce =
                charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        buffer.putString("sssss",ce);
        buffer.flip();

        int a = '+';
        System.out.println(a);
    }
}
