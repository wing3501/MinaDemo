package com.jmeter;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by ywy on 16/9/7.
 */
public class Test {
    public static void main(String[] args) {
        SocketAddress address = new InetSocketAddress("localhost", 1235);
        NioSocketConnector connector = new NioSocketConnector();
        MinaClientHandler handler = new MinaClientHandler();
        try {

            connector.getFilterChain().addLast("mdc", new MdcInjectionFilter());
            connector.getFilterChain().addLast("poscodec", new ProtocolCodecFilter(new POScodecFactory()));

            connector.setHandler(handler);
            ConnectFuture future1 = connector.connect(address);
            future1.awaitUninterruptibly();
            if (!future1.isConnected()) {
                System.out.println("fail... future1.isConnected");
//                log.info("fail... future1.isConnected");
//                results.setSuccessful(false);
            }

            AppPOSmsg msg = new AppPOSmsg();
            msg.setMark('~');
            msg.setCmd("pay");
            msg.setApiVersion("1.0");
            msg.setJsonMsg("啦啦啦啦啦");

            IoSession session = future1.getSession();
            session.write(msg);


            System.out.println("success");
//            results.setSuccessful(true);
        } catch (Exception e) {
            System.out.println("fail... " +e.getMessage());
//            log.info("fail... " +e.getMessage());
//            results.setSuccessful(false);
        }
    }
}
