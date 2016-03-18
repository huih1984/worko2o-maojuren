package com.delta.worko2o.action;

import java.util.Map;
import java.util.UUID;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.User;
import com.delta.worko2o.service.api.EmailValidServiceApi;

@IocBean(singleton = false)
public class EmailValidAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private EmailValidServiceApi emailValidService;

    @At("/page/demo/valid/user")
    public Map<String, Object> userWAction(@Param("email") String email,
                                           @Param("password") String password,
                                           @Param("userType") Integer userType,
                                           @Param("sinaId") String sinaId,
                                           @Param("tencentId") String tencentId,
                                           @Param("weixinId") String weixinId) {

        log.debug("[userWAction] begin");

        try {
            /**
             * 添加是否已经注册校验
             */
            if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "邮箱错误！");
                return retMap;
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setTencentId(tencentId);
                user.setSinaId(sinaId);
                user.setWeixinId(weixinId);
                user.setStatus(0);
                emailValidService.sendEmail(user);
                emailValidService.addUser(user);
            }
        } catch (Exception e) {
            log.debug("userWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[userWAction] end");

        return retMap;
    }

    @At("/page/demo/valid/pass")
    public Map<String, Object> passRAction(@Param("token") String token) {

        log.debug("[passRAction] begin");

        try {
            emailValidService.modUser(token, true);
        } catch (Exception e) {
            log.debug("passRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[passRAction] end");

        return retMap;
    }

    @At("/page/demo/valid/forget")
    public Map<String, Object> userForgetWAction(@Param("email") String email,
                                                 @Param("userType") Integer userType) {

        log.debug("[userForgetWAction] begin");

        try {
            /**
             * 添加是否已经注册校验
             */
            if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "邮箱错误！");
                return retMap;
            } else {
                User user = new User();
                user.setEmail(email);
                user.setUserType(userType);
                user.setToken(UUID.randomUUID().toString());
                emailValidService.sendResetPasswordEmail(user);
                emailValidService.addUser(user);
            }
        } catch (Exception e) {
            log.debug("userForgetWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[userForgetWAction] end");

        return retMap;
    }

    @At("/page/demo/valid/user/get")
    public Map<String, Object> userRAction(@Param("email") String email,
                                           @Param("userType") Integer userType,
                                           @Param("token") String token) {
        log.debug("[userRAction] begin");

        try {
            User user = new User();
            user.setToken(token);
            user.setEmail(email);
            user.setUserType(userType);
            User existUser = emailValidService.qryUser(user);
            if (existUser != null) {
                setRetInfo(RetConstants.KEY_USER, "1");
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found user");
            }
        } catch (Exception e) {
            log.debug("userRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[userRAction] end");

        return retMap;
    }


}
