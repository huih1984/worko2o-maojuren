package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.pay_protocol.WebPayReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class WebPayService extends BaseService {

    public WebPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.UNIFIED_API);
    }

    /**
     * 请求支付服务
     *
     * @param webPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(WebPayReqData webPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(webPayReqData);

        return responseString;
    }
}
