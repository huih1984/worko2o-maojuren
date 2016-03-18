package com.delta.worko2o.util;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-7-19
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;

public class UserInfoListener implements HttpSessionBindingListener, Serializable {
    public UserInfo userInfo = new UserInfo();

    public class UserInfo implements Serializable {
        //   用户信息
        int userType;
        String userName;
        int userId;

        public int getUserType() {
            return userType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserType(int i) {
            userType = i;
        }

        public void setUserName(String string) {
            userName = string;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }


    private static UserInfoListener user = new UserInfoListener();

    // 外界使用的instance对象
    public static UserInfoListener getInstance() {
        return user;
    }

    public void valueBound(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        HashMap hashMap = (HashMap) application.getAttribute("userlist");
        if (hashMap == null) {
            hashMap = new HashMap();
            application.setAttribute("userlist", hashMap);
        }
        hashMap.put(userInfo.getUserId() + "" + userInfo.getUserType(), userInfo);
        application.setAttribute("userlist", hashMap);
    }


    public void valueUnbound(HttpSessionBindingEvent event) {

        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        HashMap hashMap = (HashMap) application.getAttribute("userlist");
        hashMap.remove(userInfo.getUserId() + "" + userInfo.getUserType());
        application.setAttribute("userlist", hashMap);
    }
}
