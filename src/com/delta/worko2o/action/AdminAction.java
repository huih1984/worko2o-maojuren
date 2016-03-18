package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.Admin;
import com.delta.worko2o.model.Employer;
import com.delta.worko2o.service.api.AdminServiceApi;
import com.delta.worko2o.service.api.EmailValidServiceApi;
import com.delta.worko2o.service.api.EmployerServiceApi;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@IocBean(singleton = false)
public class AdminAction extends BaseAction {

    public static final Log log = Logs.get();

    @Inject
    private AdminServiceApi adminService;
    @Inject
    private EmployerServiceApi employerService;
    @Inject
    private EmailValidServiceApi emailValidService;

    @At("/page/demo/admin/add")
    public Map<String, ?> adminWAction(@Param("username") String username, @Param("password") String password, HttpServletRequest request) {

        log.debug("[adminWAction] begin");

        try {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            adminService.addAdmin(admin);

        } catch (Exception e) {
            log.debug("adminWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[adminWAction] end");

        return retMap;
    }

    @At("/page/demo/admin/get")
    public Map<String, ?> adminRAction(@Param("username") String username, @Param("password") String password, HttpServletRequest request) {

        log.debug("[adminRAction] begin");

        try {
            Admin admin = adminService.qryAdmin(username, password);
            setRetInfo(RetConstants.KEY_ADMIN, admin);
        } catch (Exception e) {
            log.debug("adminRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[adminRAction] end");

        return retMap;
    }

    @At("/page/demo/admin/get_not_approve")
    public Map<String, ?> employerRAction(@Param("employerName") String employerName) {

        log.debug("[employerRAction] begin");

        try {
            List<Employer> employers = adminService.qryNotApproveEmployer(employerName);
            setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL, employers);
        } catch (Exception e) {
            log.debug("employerRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerRAction] end");

        return retMap;
    }

    @At("/page/demo/admin/get_approve")
    public Map<String, ?> employerAllRAction(@Param("employerName") String employerName) {

        log.debug("[employerAllRAction] begin");

        try {
            List<Employer> employers = adminService.qryAllEmployer(employerName);
            setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL, employers);
        } catch (Exception e) {
            log.debug("employerAllRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerAllRAction] end");

        return retMap;
    }

    @At("/page/demo/admin/approve")
    public Map<String, ?> employerWAction(@Param("employerId") Integer employerId) {

        log.debug("[employerWAction] begin");

        try {
            Employer employer = new Employer();
            employer.setEmployerId(employerId);
            employer.setCensorStatus(1);
            adminService.modEmployer(employer);
            employer = employerService.qryEmployerById(employerId);
            emailValidService.sendCheckPassedEmail(employer);
        } catch (Exception e) {
            log.debug("employerWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerWAction] end");

        return retMap;
    }

}
