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
public class Music {
    private String Title;
    private String Description;
    private String MusicUrl;
    private String HQMusicUrl;

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String musicUrl) {
        HQMusicUrl = musicUrl;
    }

}
