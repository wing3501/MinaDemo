package com.tccv.mina.codec.appPOS.codec;

import com.tccv.mina.codec.appPOS.pojo.AppPOSmsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by ywy on 16/9/6.
 * 客户端的软pos信息编码器
 *
 * 协议如下(每一行都有换行符):
 * ~
 * A:1.0
 * C:doPay
 * L:1024
 * M:{"name":"styf"}
 *
 *
 */
public class AppPOSmsgEncoder implements MessageEncoder<AppPOSmsg> {

    private final Charset charset;

    public AppPOSmsgEncoder(){
        this.charset = Charset.forName("utf-8");
    }

    public AppPOSmsgEncoder(Charset charset) {
        this.charset = charset;
    }
    @Override
    public void encode(IoSession ioSession, AppPOSmsg appPOSmsg, ProtocolEncoderOutput out) throws Exception {

        CharsetEncoder ce = charset.newEncoder();
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        buffer.putString((appPOSmsg.getMark() + "\n"),ce);
        buffer.putString("A:" + appPOSmsg.getApiVersion() + "\n",ce);
        buffer.putString("C:" + appPOSmsg.getCmd() + "\n",ce);
        buffer.putString("L:" + (appPOSmsg.getJsonMsg().getBytes(charset).length+3) + '\n',ce);
        buffer.putString("M:" + appPOSmsg.getJsonMsg() + '\n',ce);
        buffer.flip();
        out.write(buffer);
    }


    public static void main(String[] args) throws CharacterCodingException {
        Charset charset = Charset.forName("utf-8");
        CharsetEncoder ce =
                charset.newEncoder();

        AppPOSmsg appPOSmsg = new AppPOSmsg();
        appPOSmsg.setMark('~');
        appPOSmsg.setCmd("pay");
        appPOSmsg.setApiVersion("1.0");
        String jsonbody = "{\"name\":\"styf\"}";
        appPOSmsg.setJsonMsg(jsonbody);


        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        buffer.putString((appPOSmsg.getMark() + "\n"),ce);
        buffer.putString("A:" + appPOSmsg.getApiVersion() + "\n",ce);
        buffer.putString("C:" + appPOSmsg.getCmd() + "\n",ce);
        buffer.putString("L:" + (appPOSmsg.getJsonMsg().getBytes(charset).length+ 3 + '\n'),ce);
        buffer.putString("M:" + appPOSmsg.getJsonMsg() + '\n',ce);
        buffer.flip();

        String one = buffer.getString(2,charset.newDecoder());
        System.out.println(one);
    }
}
