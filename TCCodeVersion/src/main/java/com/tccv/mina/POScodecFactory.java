package com.tccv.mina;

import com.tccv.core.ali.dayu.SendMessage;
import com.tccv.mina.codec.appPOS.codec.AppPOSmsgDecoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSmsgEncoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSresultDncoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSresultEncoder;
import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import org.apache.mina.example.sumup.codec.ResultMessageDecoder;
import org.apache.mina.example.sumup.codec.ResultMessageEncoder;
import org.apache.mina.example.sumup.message.ResultMessage;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import java.nio.charset.Charset;

/**
 * Created by ywy on 16/9/6.
 * POS多路分离编码工厂
 */
public class POScodecFactory extends DemuxingProtocolCodecFactory {

    private final Charset charset = Charset.forName("utf-8");

    public POScodecFactory() {
        super.addMessageEncoder(AppPOSresult.class,AppPOSresultEncoder.class);
        super.addMessageDecoder(AppPOSmsgDecoder.class);
    }

    public POScodecFactory(boolean server) {
        if (server) {
            super.addMessageEncoder(AppPOSresult.class, AppPOSresultEncoder.class);
            super.addMessageDecoder(AppPOSmsgDecoder.class);

        } else {
            super.addMessageEncoder(AppPOSmsg.class, AppPOSmsgEncoder.class);
            super.addMessageDecoder(AppPOSresultDncoder.class);
        }
    }
}
