package com.tccv.mina;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.smartPOS.pojo.SmartPOSmsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.apache.poi.util.SystemOutLogger;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

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
            connector.getFilterChain().addLast("poscodec", new ProtocolCodecFilter(new POScodecFactory(false)));

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

    public static void main(String[] args) throws Exception {
//        System.out.println("---------------------");
//        MinaClientSupport client = new MinaClientSupport();
//        MinaClientHandler handler = new MinaClientHandler();
//        client.setHandler(handler);
//        client.setHost("localhost");
//        client.setPort(1235);
//        String msg = "hello world!";
//        client.send(msg);
//        client.close();

        //测试软pos请求
        testAppPos();

        //测试智能pos请求
//        testSmartPos();


//        CharsetEncoder ce = Charset.forName("utf-8").newEncoder();
//        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
//        buffer.putChar('+');
//        buffer.putInt(22);
//        String jsonbody = "{\"name\":\"SmartPOSmsg\"}";
//        buffer.putString(jsonbody,ce);
//
//        buffer.flip();
//
//        byte[] bytes = new byte[buffer.remaining()];
//        buffer.get(bytes, 0, bytes.length);


//        System.out.println(jsonbody.getBytes("utf-8").length);
//        printHexString(bytes);
    }

    // 以16进制打印出byte数组
    public static void printHexString( byte[] b)
    {

        for (int i = 0; i < b.length; i++)
        {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println();
    }


    public static void testSmartPos (){
        System.out.println("---------------------");
        MinaClientSupport client = new MinaClientSupport();
        MinaClientHandler handler = new MinaClientHandler();
        client.setHandler(handler);
        client.setHost("localhost");
        client.setPort(1235);
        SmartPOSmsg msg = new SmartPOSmsg();
        msg.setMark('+');
        String jsonbody = "{\"name\":\"SmartPOSmsg\"}";
        msg.setBodyContent(jsonbody);


        client.send(msg);
        client.close();
    }



    public static void testAppPos (){
        System.out.println("---------------------");
        MinaClientSupport client = new MinaClientSupport();
        MinaClientHandler handler = new MinaClientHandler();
        client.setHandler(handler);
        client.setHost("localhost");
        client.setPort(1235);
        AppPOSmsg msg = new AppPOSmsg();
        msg.setMark('~');
        msg.setCmd("pay");
        msg.setApiVersion("1.0");
        String jsonbody = "{\"name\":\"styf\"}";
        msg.setJsonMsg(jsonbody);


        client.send(msg);
        client.close();
    }




}


