package com.tccv.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by ywy on 16/9/2.
 */
public class MinaClientSupport {
    private IoHandler handler = null;

    private IoSession session;

    private String host;

    private int port;

    public boolean send(Object message) {
        if (session != null && session.isConnected()) {
            throw new IllegalStateException(
                    "Already connected. Disconnect first.");
        }
        SocketAddress address = new InetSocketAddress(host, port);
        NioSocketConnector connector = new NioSocketConnector();
        try {

            connector.getFilterChain().addLast("mdc", new MdcInjectionFilter());

            connector.setHandler(handler);
            ConnectFuture future1 = connector.connect(address);
            future1.awaitUninterruptibly();
            if (!future1.isConnected()) {
                return false;
            }
            session = future1.getSession();
            session.write(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void close() {
        if (session != null) {
            if (session.isConnected()) {
                // Wait until the chat ends.
                session.getCloseFuture().awaitUninterruptibly();
            }
            session.close();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHandler(IoHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {
        System.out.println("---------------------");
        MinaClientSupport client = new MinaClientSupport();
        MinaClientHandler handler = new MinaClientHandler();
        client.setHandler(handler);
        client.setHost("localhost");
        client.setPort(1235);
        String msg = "hello world!";
        client.send(msg);
        System.out.println("Client Send: " + msg);
        client.close();
    }
}
