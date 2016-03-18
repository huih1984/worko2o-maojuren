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
public class VoiceMessage extends BaseMessage {

    private String MediaId;

    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
