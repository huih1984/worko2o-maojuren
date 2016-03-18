package com.delta.worko2o.weixinModel.message.req;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time: ����5:32
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author liufeng
 * @date 2013-05-19
 */
public class LocationMessage extends BaseMessage {

    private String Location_X;

    private String Location_Y;

    private String Scale;

    private String Label;

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}