package com.delta.worko2o.util;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-18
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */


import com.delta.worko2o.weixinModel.AccessToken;
import com.delta.worko2o.weixinModel.CustomText;
import com.delta.worko2o.weixinModel.Menu;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


/**
 * 公众平台通用接口工具类
 *
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            //log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            //log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            Util.log("连接超时：{}" + ce);
        } catch (Exception e) {
            Util.log("https请求异常：{}" + e);
        }
        return null;
    }

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                //log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    public static String open_url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    public static String open_url2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static String open_url3 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    public static String open_url4 = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    public static String custom_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    // 第三方用户唯一凭证
    public static String appId = "wxb8d3b33bfca07e46";
    // 第三方用户唯一凭证密钥
    public static String appSecret = "19f712ce4536051c4f43298f3cedc7b6";

    public static String getCode(String appid, String appsecret, String redirecturi) {
        try {
            String requestUrl = open_url1.replace("APPID", appid).replace("SECRET", appsecret).replace("REDIRECT_URI", URLEncoder.encode(redirecturi, "utf-8"));
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            String temp = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendCustom(CustomText customText) {
        try {
            AccessToken accessToken = getAccessToken(WeixinUtil.appId, WeixinUtil.appSecret);
            String accessTokenS = accessToken.getToken();
            String cu_url;
            cu_url = custom_url.replace("ACCESS_TOKEN", accessTokenS);
            String jsonMenu = JSONObject.fromObject(customText).toString();
            JSONObject jsonObject = httpRequest(cu_url, "POST", jsonMenu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static Customer getAccessTokenAndOpenId(String appid, String appsecret, String code) {
//        try {
//            String requestUrl = open_url2.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
//            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
//            Customer customer = new Customer();
//            customer.setOpenId((String) jsonObject.get("openid"));
//            customer.setAccessToken((String)jsonObject.get("access_token"));
//            return customer;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Customer getCustomerInfo(Customer customer){
//        String open_url = open_url4.replace("ACCESS_TOKEN", customer.getAccessToken()).replace("OPENID", customer.getOpenId());
//        JSONObject jsonObject = httpRequest(open_url, "GET", null);
//        customer.setCity((String)jsonObject.get("city"));
//        customer.setCountry((String) jsonObject.get("country"));
//        customer.setProvince((String) jsonObject.get("province"));
//        customer.setSex((Integer) jsonObject.get("sex"));
//        customer.setCustomerNickname((String)jsonObject.get("nickname"));
//        return customer;
//    }

    public static SortedMap<Object, Object> toMap(Object mapObj) {
        SortedMap<Object, Object> map = new TreeMap<Object, Object>();
        Field[] fields = mapObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(mapObj);
                if (obj != null && !"".equals(obj)) {
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

    /**
     * @param parameters 请求参数
     * @return
     * @author 李欣桦
     * @date 2014-12-5下午2:29:34
     * @Description：sign签名
     */
    public static String createSign(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Configure.getKey());
        String sign = SignUtil.MD5Encode(sb.toString()).toUpperCase();
        return sign;
    }

    public static String getRequestXml(Object xmlObj) {
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        return postDataXML;
    }


    /**
     * @param parameters 请求参数
     * @return
     * @author 李欣桦
     * @date 2014-12-5下午2:32:05
     * @Description：将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) String.valueOf(entry.getValue());
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}