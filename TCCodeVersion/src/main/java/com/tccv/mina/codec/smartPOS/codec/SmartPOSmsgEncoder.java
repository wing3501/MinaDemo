package com.tccv.mina.codec.smartPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSmsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by ywy on 16/9/7.
 *
 * 客户端的智能pos信息编码器
 * 协议如下
 * 1024{"name":"styf"}
 *
 */
public class SmartPOSmsgEncoder implements MessageEncoder<SmartPOSmsg> {

    private final Charset charset;

    public SmartPOSmsgEncoder(){
        this.charset = Charset.forName("utf-8");
    }

    public SmartPOSmsgEncoder(Charset charset) {
        this.charset = charset;
    }
    @Override
    public void encode(IoSession ioSession, SmartPOSmsg smartPOSmsg, ProtocolEncoderOutput out) throws Exception {
        CharsetEncoder ce = charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        buffer.putChar(smartPOSmsg.getMark());
        buffer.putInt(smartPOSmsg.getBodyContent().getBytes(charset).length);
        buffer.putString(smartPOSmsg.getBodyContent(),ce);

        buffer.flip();
        out.write(buffer);
    }
}
