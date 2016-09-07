package com.jmeter;


import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import java.nio.charset.Charset;

/**
 * Created by ywy on 16/9/6.
 * POS多路分离编码工厂
 */
public class POScodecFactory extends DemuxingProtocolCodecFactory {

    private final Charset charset = Charset.forName("utf-8");

    public POScodecFactory() {

        super.addMessageEncoder(AppPOSmsg.class, AppPOSmsgEncoder.class);
        super.addMessageDecoder(AppPOSresultDncoder.class);
    }

}
