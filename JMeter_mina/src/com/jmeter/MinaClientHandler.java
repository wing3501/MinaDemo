package com.jmeter;


import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

import java.nio.charset.Charset;

/**
 * Created by ywy on 16/9/2.
 */
public class MinaClientHandler extends IoHandlerAdapter {
    private static final IoFilter LOGGING_FILTER = new LoggingFilter();

    private static final IoFilter CODEC_FILTER = new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")));

    @Override
    public void sessionCreated(IoSession session) throws Exception {
//        session.getFilterChain().addLast("codec", CODEC_FILTER);
//        session.getFilterChain().addLast("logger", LOGGING_FILTER);
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        AppPOSresult result = (AppPOSresult)message;
        System.out.println("客户端收到消息: " + result.getJsonMsg());

        session.closeNow();
        session.getService().dispose();
    }

}
