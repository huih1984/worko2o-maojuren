package com.delta.worko2o.weixinModel;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-5-21
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class CustomText {
    public String touser;
    public String msgtype;
    public Text text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
