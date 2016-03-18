package com.delta.worko2o.weixinModel.message.resp;

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
public class Article {

    private String Title;

    private String Description;

    private String PicUrl;

    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return null == PicUrl ? "" : PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return null == Url ? "" : Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
