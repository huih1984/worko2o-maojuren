package com.delta.worko2o.weixinModel.message.resp;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time: ����5:40
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author liufeng
 * @date 2013-05-19
 */
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private int FuncFlag;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
