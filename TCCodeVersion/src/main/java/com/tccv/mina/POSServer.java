package com.tccv.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * Created by ywy on 16/9/6.
 */
public class POSServer {
    public static void main(String[] args) throws Exception { IoAcceptor acceptor = new NioSocketAcceptor();
        LoggingFilter lf = new LoggingFilter(); acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
                5);
        acceptor.getFilterChain().addLast("logger", lf); acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new POScodecFactory()));
        acceptor.setHandler(new POSHandler());
        acceptor.bind(new InetSocketAddress(1235)); }
}
