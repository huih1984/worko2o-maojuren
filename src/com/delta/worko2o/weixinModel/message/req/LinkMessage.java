package com.delta.worko2o.weixinModel.message.req;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time: ����5:33
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author liufeng
 * @date 2013-05-19
 */
public class LinkMessage extends BaseMessage {

    private String Title;

    private String Description;

    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
