package com.tccv.mina.codec.appPOS.pojo;

/**
 * 软pos信息
 * Created by ywy on 16/9/6.
 *
 */
public class AppPOSmsg {
    private char mark = '~'; //标识
    private String apiVersion = "1.0";
    private String cmd = "doPay";
    private int bodyLength;
    private String jsonMsg;

    public AppPOSmsg() {
    }

    public AppPOSmsg(char mark, String apiVersion, String cmd, int bodyLength, String jsonMsg) {
        this.mark = mark;
        this.apiVersion = apiVersion;
        this.cmd = cmd;
        this.bodyLength = bodyLength;
        this.jsonMsg = jsonMsg;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getJsonMsg() {
        return jsonMsg;
    }

    public void setJsonMsg(String jsonMsg) {
        this.jsonMsg = jsonMsg;
    }
}
