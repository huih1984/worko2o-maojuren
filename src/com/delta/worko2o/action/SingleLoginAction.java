package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.Employer;
import com.delta.worko2o.model.JobHunter;
import com.delta.worko2o.service.api.EmployerServiceApi;
import com.delta.worko2o.service.api.JobHunterServiceApi;
import com.delta.worko2o.service.impl.EmployerServiceImpl;
import com.delta.worko2o.service.impl.JobHunterServiceImpl;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-2-12
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
@IocBean(singleton = false)
public class SingleLoginAction extends BaseAction {

    protected Map<String, Object> retMap = new HashMap<String, Object>();

    public static final Log log = Logs.get();

    @Inject
    private JobHunterServiceApi jobHunterService;

    @Inject
    private EmployerServiceApi employerService;

    @At("/page/demo/connet")
    public Map<String, ?> connetAction(@Param("type") Integer type, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.debug("[connetAction] begin");
        if (type == 0) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            try {
                httpServletResponse.sendRedirect(new com.qq.connect.oauth.Oauth().getAuthorizeURL(httpServletRequest));
            } catch (Exception e) {
                log.debug("connetAction error", e);
                setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
            }
        } else if (type == 1) {
            try {
                weibo4j.Oauth oauth = new weibo4j.Oauth();
//                BareBonesBrowserLaunch.openURL(oauth.authorize("code"))
                httpServletResponse.sendRedirect(oauth.authorize("code"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.debug("[connetAction] end");

        return retMap;
    }


    @At("/page/demo/sina/bind")
    public Map<String, ?> sinaRedirectAction(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        weibo4j.Oauth oauth = new weibo4j.Oauth();
        String code = httpServletRequest.getParameter("code");
        try {
            weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
            if (accessToken == null) {
                PrintWriter out = httpServletResponse.getWriter();
                out.print("accesstoken is null");
            }
            httpServletRequest.setAttribute("uid", accessToken.getUid());
            httpServletRequest.setAttribute("type", 1);

            Cookie[] cookies = httpServletRequest.getCookies();//这样便可以获取一个cookie数组
            int type = -1;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("woo-user-type")) {
                    if (cookie.getValue().equals("hunter")) {
                        type = 1;
                        break;
                    } else {
                        type = 2;
                        break;
                    }
                }
            }

            httpServletRequest.setAttribute("callbackType", "sina");
            httpServletRequest.setAttribute("sinaId", accessToken.getUid());
            boolean isMobile = (Boolean) httpServletRequest.getSession().getAttribute("isMobile");
            if (type == 1) {
                JobHunter jobHunter = jobHunterService.qryJobHunterBySinaId(accessToken.getUid());
                if (jobHunter != null) {
                    httpServletRequest.setAttribute("callback", "yes");

                    RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_login.jsp" : "/login.jsp");
                    view.forward(httpServletRequest, httpServletResponse);
                    return retMap;
                }
            } else if (type == 2) {
                Employer employer = employerService.qryEmployerBySinaId(accessToken.getUid());
                if (employer != null) {
                    httpServletRequest.setAttribute("callback", "yes");
                    RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_login.jsp" : "/login.jsp");
                    view.forward(httpServletRequest, httpServletResponse);
                    return retMap;
                }
            }

            RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_bindsso.jsp" : "/bindsso.jsp");
            view.forward(httpServletRequest, httpServletResponse);
        } catch (WeiboException e) {
            e.printStackTrace();
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.print(e.toString());
            } catch (Exception e1) {

            }
        } catch (Exception e) {
            log.debug("redirectAction error", e);
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.print("redirectAction error" + e.toString());
            } catch (Exception e1) {

            }
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }
        return retMap;
    }

    @At("/page/demo/qq/bind")
    public Map<String, ?> qqRedirectAction(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.debug("[redirectAction] begin");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(httpServletRequest);
            if (accessTokenObj == null) {
                PrintWriter out = httpServletResponse.getWriter();
                out.print("accesstoken is null");
            }
            String accessToken = null,
                    openID = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
                setRetInfo(RetConstants.KEY_TOKEN, "没有获取到响应参数");
                PrintWriter out = httpServletResponse.getWriter();
                out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

//                httpServletRequest.getSession().setAttribute("demo_access_token", accessToken);
//                httpServletRequest.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

//                setRetInfo(RetConstants.KEY_TOKEN, "欢迎你，代号为 " + openID + " 的用户!");
//                out.println("欢迎你，代号为 " + openID + " 的用户!");
                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                httpServletRequest.setAttribute("nickname", weiboUserInfoBean.getNickName());
                httpServletRequest.setAttribute("openID", openID);
                httpServletRequest.setAttribute("type", 0);
                Cookie[] cookies = httpServletRequest.getCookies();//这样便可以获取一个cookie数组
                int type = -1;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("woo-user-type")) {
                        if (cookie.getValue().equals("hunter")) {
                            type = 1;
                            break;
                        } else {
                            type = 2;
                            break;
                        }
                    }
                }
                httpServletRequest.setAttribute("callbackType", "tencent");
                httpServletRequest.setAttribute("tencentId", openID);
                boolean isMobile = (Boolean) httpServletRequest.getSession().getAttribute("isMobile");
                if (type == 1) {
                    JobHunter jobHunter = jobHunterService.qryJobHunterByTencentId(openID);
                    if (jobHunter != null) {
                        httpServletRequest.setAttribute("callback", "yes");
                        RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_login.jsp" : "/login.jsp");
                        view.forward(httpServletRequest, httpServletResponse);
                        return retMap;
                    }
                } else if (type == 2) {
                    Employer employer = employerService.qryEmployerByTencentId(openID);
                    if (employer != null) {
                        httpServletRequest.setAttribute("callback", "yes");
                        RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_login.jsp" : "/login.jsp");
                        view.forward(httpServletRequest, httpServletResponse);
                        return retMap;
                    }
                }

                //如果还没有返回，说明还没绑定，现在就绑定
                if (weiboUserInfoBean.getRet() == 0) {
                    httpServletRequest.setAttribute("email", weiboUserInfoBean.getEmail());
                }
                RequestDispatcher view = httpServletRequest.getRequestDispatcher(isMobile ? "/wap_bindsso.jsp" : "/bindsso.jsp");
                view.forward(httpServletRequest, httpServletResponse);
            }
        } catch (QQConnectException e) {
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.print(e.toString());
            } catch (Exception e1) {

            }
        } catch (Exception e) {
            log.debug("redirectAction error", e);
            try {
                PrintWriter out = httpServletResponse.getWriter();
                out.print("redirectAction error" + e.toString());
            } catch (Exception e1) {

            }
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[redirectAction] end");

        return retMap;
    }
}
