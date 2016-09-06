package com.tccv.mina.codec.appPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 服务端软pos返回结果编码器
 * Created by ywy on 16/9/6.
 */
public class AppPOSresultEncoder implements MessageEncoder<AppPOSresult> {

    private final Charset charset;

    public AppPOSresultEncoder() {
        this.charset = Charset.forName("utf-8");
    }

    public AppPOSresultEncoder(Charset charset) {
        this.charset = charset;
    }
    @Override
    public void encode(IoSession ioSession, AppPOSresult appPOSresult, ProtocolEncoderOutput out) throws Exception {
        CharsetEncoder ce = charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(4 + appPOSresult.getBodyLength());
        buffer.putInt(appPOSresult.getBodyLength());
        buffer.putString(appPOSresult.getJsonMsg(),ce);
        buffer.flip();
        out.write(buffer);
    }
}
