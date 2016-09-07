package com.tccv.mina;

import com.tccv.mina.codec.appPOS.codec.AppPOSmsgDecoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSmsgEncoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSresultDncoder;
import com.tccv.mina.codec.appPOS.codec.AppPOSresultEncoder;
import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import com.tccv.mina.codec.smartPOS.codec.SmartPOSmsgDecoder;
import com.tccv.mina.codec.smartPOS.codec.SmartPOSmsgEncoder;
import com.tccv.mina.codec.smartPOS.codec.SmartPOSresultDecoder;
import com.tccv.mina.codec.smartPOS.codec.SmartPOSresultEncoder;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSmsg;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSresult;
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
        super.addMessageEncoder(SmartPOSresult.class, SmartPOSresultEncoder.class);
        super.addMessageDecoder(AppPOSmsgDecoder.class);
        super.addMessageDecoder(SmartPOSmsgDecoder.class);

    }

    public POScodecFactory(boolean server) {
        if (server) {
            super.addMessageEncoder(AppPOSresult.class, AppPOSresultEncoder.class);
            super.addMessageDecoder(AppPOSmsgDecoder.class);

        } else {
            super.addMessageEncoder(AppPOSmsg.class, AppPOSmsgEncoder.class);
            super.addMessageDecoder(AppPOSresultDncoder.class);

//            super.addMessageEncoder(SmartPOSmsg.class, SmartPOSmsgEncoder.class);
//            super.addMessageDecoder(SmartPOSresultDecoder.class);
        }
    }
}
