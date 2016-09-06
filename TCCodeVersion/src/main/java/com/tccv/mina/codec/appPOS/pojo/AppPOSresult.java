package com.tccv.mina.codec.appPOS.pojo;

/**
 * 软pos返回结果
 * Created by ywy on 16/9/6.
 */
public class AppPOSresult {
    private int bodyLength;
    private String jsonMsg;

    public AppPOSresult() {
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public String getJsonMsg() {
        return jsonMsg;
    }

    public void setJsonMsg(String jsonMsg) {
        this.jsonMsg = jsonMsg;
    }
}
