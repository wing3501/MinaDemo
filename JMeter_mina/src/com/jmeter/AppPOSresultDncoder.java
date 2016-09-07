package com.jmeter;

import org.apache.mina.core.buffer.IoBuffer;
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
 * 注意:没有做缓存,这个类写法是有问题的
 */
public class AppPOSresultDncoder implements MessageDecoder {

    private final Charset charset;

    public AppPOSresultDncoder() {
        this.charset = Charset.forName("utf-8");
    }

    public AppPOSresultDncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        if (ioBuffer.remaining() > 4){
            return MessageDecoderResult.OK;
        }
        return MessageDecoderResult.NEED_DATA;
    }

    @Override
    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
        CharsetDecoder cd = charset.newDecoder();
        int bodyLength = ioBuffer.getInt();
        if (bodyLength == ioBuffer.remaining()){
            AppPOSresult result = new AppPOSresult();
            result.setBodyLength(bodyLength);
            result.setJsonMsg(ioBuffer.getString(cd));
            out.write(result);
            return MessageDecoderResult.OK;
        }else{
            return MessageDecoderResult.NEED_DATA;
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
