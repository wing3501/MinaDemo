package com.tccv.mina.codec.smartPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSresult;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by ywy on 16/9/7.
 */
public class SmartPOSresultEncoder implements MessageEncoder<SmartPOSresult> {

    private final Charset charset;

    public SmartPOSresultEncoder() {
        this.charset = Charset.forName("utf-8");
    }

    public SmartPOSresultEncoder(Charset charset) {
        this.charset = charset;
    }
    @Override
    public void encode(IoSession ioSession, SmartPOSresult smartPOSresult, ProtocolEncoderOutput out) throws Exception {
        CharsetEncoder ce = charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(6 + smartPOSresult.getBodyLength());
        buffer.putChar(smartPOSresult.getHeader());
        buffer.putInt(smartPOSresult.getBodyLength());
        buffer.putString(smartPOSresult.getJsonMsg(),ce);
        buffer.flip();
        out.write(buffer);
    }
}
