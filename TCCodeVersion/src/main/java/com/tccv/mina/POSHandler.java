package com.tccv.mina;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tccv.core.util.json.JsonUtils;
import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import com.tccv.mina.codec.appPOS.pojo.AppPOSresult;
import com.tccv.pojo.AppVersion;
import com.tccv.service.AppVersionService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ywy on 16/9/6.
 * pos请求处理器
 */
public class POSHandler extends IoHandlerAdapter {

    @Autowired
    private AppVersionService appVersionService;

    @Override
    public void messageReceived(IoSession session, Object message) {

        if (message instanceof AppPOSmsg){//软pos信息请求
            AppPOSmsg msg = (AppPOSmsg)message;
            System.out.println("server receivird:" + msg.getJsonMsg());

            //模拟返回
            List<AppVersion> list = appVersionService.selectAllApp();
            String result = "";
            try {
                result = JsonUtils.mapper.writeValueAsString(list);
//                result = "{\"name\":\"styf\"}";
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AppPOSresult appPOSresult = new AppPOSresult();
            try {
                appPOSresult.setBodyLength(result.getBytes("utf-8").length);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            appPOSresult.setJsonMsg(result);
            session.write(appPOSresult);
        }

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session created");
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("closed session");
    }
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
//        session.write("服务端发送心跳包");
    }
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("session opened");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        System.out.println("===>" + cause.getMessage());
    }
}
