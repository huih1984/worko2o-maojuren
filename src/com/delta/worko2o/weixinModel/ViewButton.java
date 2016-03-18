package com.delta.worko2o.weixinModel;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-7-23
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */

/**
 * view类型的菜单
 *
 * @author liuyq
 * @date 2013-04-10
 */
public class ViewButton extends Button {
    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
