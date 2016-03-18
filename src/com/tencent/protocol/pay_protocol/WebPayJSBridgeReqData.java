package com.tencent.protocol.pay_protocol;

/**
 * User: rizenguo
 * Date: 2014/10/22
 * Time: 21:29
 */

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求被扫支付API需要提交的数据
 */
public class WebPayJSBridgeReqData {

    //每个字段具体的意思请查看API文档
    public String appid = "";
    public String nonce_str = "";
    public String timeStamp = "";
    public String packageValue = "";
    public String signType = "";
    public String paySign = "";

    public WebPayJSBridgeReqData() {


    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
