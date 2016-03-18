package com.delta.worko2o.weixinModel.message.req;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time:
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author liufeng
 * @date 2013-05-19
 */
public class TextMessage extends BaseMessage {

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}