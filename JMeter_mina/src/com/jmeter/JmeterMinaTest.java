package com.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.log.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by ywy on 16/9/7.
 */
public class JmeterMinaTest extends AbstractJavaSamplerClient {
    /**
     * 输出到Jmeter控制台的日志类.
     * 需要引用Jmeter lib目录下的logkit-2.0.jar.
     */
    private Logger log = getLogger();
    /**
     * 运行结果.
     */
    private SampleResult results;
    /**
     * Jmeter控制台输入的参数.
     */
    private String message;


    /**
     * 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行，<br>
     * 类似于LoadRunner中的init方法.
     * {@inheritDoc}
     * @see org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient#setupTest(
     * org.apache.jmeter.protocol.java.sampler.JavaSamplerContext)
     * @author ices 2013-3-18 上午8:44:51 <br>
     */
    @Override
    public void setupTest(JavaSamplerContext arg0) {
        log.info("execute setupTest...");
        results = new SampleResult();
        message = arg0.getParameter("message", "");

    }


    /**
     * 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中.
     * {@inheritDoc}
     * @see org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient
     * #getDefaultParameters()
     * @author ices 2013-3-18 上午8:45:02 <br>
     */
    @Override
    public Arguments getDefaultParameters() {
        log.info("execute getDefaultParameters...");
        Arguments params = new Arguments();
     /*
     * 定义一个参数，显示到Jmeter的参数列表中，
     * 第一个参数为参数默认的显示名称，
     * 第二个参数为默认值
     */
        params.addArgument("message", "");
        return params;
    }


    /**
     * 测试执行的循环体，根据线程数和循环次数的不同可执行多次，类似于LoadRunner中的Action方法
     * {@inheritDoc}
     * @see org.apache.jmeter.protocol.java.sampler.JavaSamplerClient#runTest(
     * org.apache.jmeter.protocol.java.sampler.JavaSamplerContext)
     * @author ices 2013-3-18 上午8:45:18 <br>
     */
    @Override
    public SampleResult runTest(JavaSamplerContext arg0) {
        log.info("execute runTest...");

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
                log.info("fail... future1.isConnected");
                results.setSuccessful(false);
            }

            AppPOSmsg msg = new AppPOSmsg();
            msg.setMark('~');
            msg.setCmd("pay");
            msg.setApiVersion("1.0");
            msg.setJsonMsg(message);

            IoSession session = future1.getSession();
            session.write(msg);


            results.setSuccessful(true);
        } catch (Exception e) {
            log.info("fail... " +e.getMessage());
            results.setSuccessful(false);
        }

        return results;
    }



    /**
     * 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行，<br>
     * 类似于LoadRunner中的end方法.
     * {@inheritDoc}
     * @see org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient#teardownTest(
     * org.apache.jmeter.protocol.java.sampler.JavaSamplerContext)
     * @author ices 2013-3-18 上午8:45:53 <br>
     */
    @Override
    public void teardownTest(JavaSamplerContext arg0) {
    }
}
