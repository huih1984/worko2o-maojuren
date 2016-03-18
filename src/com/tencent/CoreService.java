package com.tencent;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time: 下午10:14
 * To change this template use File | Settings | File Templates.
 */


import com.delta.worko2o.util.MessageUtil;
import com.delta.worko2o.weixinModel.Menu;
import com.delta.worko2o.weixinModel.message.resp.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 核心服务类
 *
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {
    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */

    public String processRequest(HttpServletRequest request) {
        Map<Integer, Menu> menuMap = new HashMap<Integer, Menu>();
        String classBasePath = CoreService.this.getClass().getClassLoader().getResource("").getPath();
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");
                if (content.equals("订票")) {
                    respContent = "请点击<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb8d3b33bfca07e46&redirect_uri=http%3A%2F%2Fmyapp.ngrok.io%2Fuser_code&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect\">马上选座订票" +
                            "</a>开始预订糊世刺身美食就餐座位，预订成功后您将收到客服系统给您的预订成功的消息，请您凭此消息就餐！";
                }
                // 回复文本消息
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setFuncFlag(0);
                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    // 回复文本消息
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    textMessage.setFuncFlag(0);
                    respContent = "揚名於深巷，也許有一天會出現在日本的街頭。---who's sashimi";
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }  // 自定义菜单点击事件


        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }


}
