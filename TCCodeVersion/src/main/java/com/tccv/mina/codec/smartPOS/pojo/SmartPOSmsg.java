package com.tccv.mina.codec.smartPOS.pojo;

/**
 * 智能pos信息
 * Created by ywy on 16/9/6.
 */
public class SmartPOSmsg {
    private char mark = '+';
    private int bodyLength;
    private String bodyContent;

    public SmartPOSmsg() {
    }

    public SmartPOSmsg(char mark, String bodyContent, int bodyLength) {
        this.mark = mark;
        this.bodyContent = bodyContent;
        this.bodyLength = bodyLength;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }
}
