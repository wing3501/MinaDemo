package com.tccv.mina.codec.smartPOS.pojo;

/**
 * Created by ywy on 16/9/7.
 */
public class SmartPOSresult {
    private char header = '-';
    private int bodyLength;
    private String jsonMsg;

    public SmartPOSresult() {
    }

    public char getHeader() {
        return header;
    }

    public void setHeader(char header) {
        this.header = header;
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
