package com.tccv.mina;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tccv.core.dto.ResultPageDOHelper;
import com.tccv.core.util.ajax.AjaxUtils;
import com.tccv.core.util.json.JsonUtils;
import com.tccv.pojo.AppVersion;
import com.tccv.service.AppVersionService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.tccv.core.util.json.JsonUtils.jsonString;

/**
 * Created by ywy on 16/9/2.
 */
public class MinaProtocolHandler extends IoHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //private final Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());
    @Autowired
    private AppVersionService appVersionService;

    @Override
    public void messageReceived(IoSession session, Object message) {
        String msg = (String) message;
        System.out.println("Server Received: " + msg);
        //测试
        List<AppVersion> list = appVersionService.selectAllApp();
        try {
            msg = JsonUtils.mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("Server Received: " + msg);
        session.write("Server Send: " + msg);
        //sessions.add(session);
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
        System.out.println("IDLE "+session.getIdleCount(status));
    }
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("session opened");
    }

}
