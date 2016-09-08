package com.tccv.mina.codec.appPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by ywy on 16/9/6.
 * 客户端使用的软POS返回结果解码器
 *
 *
 */
public class AppPOSresultDncoder implements MessageDecoder {

    private final Charset charset;

    private final AttributeKey CONTEXT = new AttributeKey(getClass(), "AppPOSresultContext");

    public AppPOSresultDncoder() {
        this.charset = Charset.forName("utf-8");
    }

    public AppPOSresultDncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {

        Context ctx = getContext(ioSession);
        if (ctx.getBodyLength() > 0){
            //已经在一次解码流程中了
            return MessageDecoderResult.OK;
        }else{
            if (ioBuffer.remaining() > 4){
                return MessageDecoderResult.OK;
            }
            return MessageDecoderResult.NEED_DATA;
        }
    }

    @Override
    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
        Context ctx = getContext(ioSession);
        CharsetDecoder cd = charset.newDecoder();
        int matchCount = ctx.getMatchCount();//用来统计读到了第几个字节
        IoBuffer buffer = ctx.innerBuffer;//缓存的数据
        int bodyLength = ctx.getBodyLength();

        while (ioBuffer.hasRemaining()){
            if (ctx.getBodyLength() > 0){
                matchCount++;
                buffer.put(ioBuffer.get());
                ctx.setMatchCount(matchCount);
                if (matchCount == bodyLength){
                    break;
                }
            }else{
                bodyLength = ioBuffer.getInt();
                ctx.setBodyLength(bodyLength);
            }

        }


        if (matchCount == bodyLength){
            ctx.reset();
            AppPOSresult result = new AppPOSresult();
            result.setBodyLength(bodyLength);
            result.setJsonMsg(buffer.getString(cd));
            out.write(result);
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
        private int bodyLength;

        public Context() {
            innerBuffer = IoBuffer.allocate(100).setAutoExpand(true);
        }

        public void reset() {
            this.innerBuffer.clear();
            this.matchCount = 0;
            this.bodyLength = 0;
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
        public int getBodyLength() {
            return bodyLength;
        }

        public void setBodyLength(int bodyLength) {
            this.bodyLength = bodyLength;
        }
    }
}
