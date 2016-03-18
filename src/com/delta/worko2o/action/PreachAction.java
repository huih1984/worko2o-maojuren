package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.Employer;
import com.delta.worko2o.model.Job;
import com.delta.worko2o.model.Preach;
import com.delta.worko2o.service.api.PreachServiceApi;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <Description> <br>
 * 职位相关操作类
 *
 * @param <>
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月13日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class PreachAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private PreachServiceApi preachService;

    @At("/page/demo/getpreach")
    public Map<String, Object> getPreachAction(HttpServletRequest request) {

        log.debug("[getPreachAction] begin");

        try {
            List<Preach> preaches = preachService.qryPreach();
            setRetInfo("preach", preaches);
        } catch (Exception e) {
            log.debug("getPreachAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[getPreachAction] end");

        return retMap;
    }

    @At("/page/demo/getpreachbyid")
    public Map<String, Object> getPreachByIdAction(@Param("preachId") Integer preachId, HttpServletRequest request) {

        log.debug("[getPreachByIdAction] begin");

        try {
            Preach preach = preachService.qryPreachById(preachId);
            setRetInfo("preach", preach);
        } catch (Exception e) {
            log.debug("getPreachByIdAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[getPreachByIdAction] end");

        return retMap;
    }

    @At("/page/demo/getemployerbypreachid")
    public Map<String, Object> getEmployersByPreachIdAction(@Param("preachId") Integer preachId, HttpServletRequest request) {

        log.debug("[getEmployersByPreachIdAction] begin");

        try {
            List<Employer> employers = preachService.qryEmployersByPreachId(preachId);
            setRetInfo("employer", employers);
        } catch (Exception e) {
            log.debug("getEmployersByPreachIdAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[getEmployersByPreachIdAction] end");

        return retMap;
    }


    @At("/page/demo/getjobsbypreachid")
    public Map<String, Object> getJobsByPreachIdAction(@Param("employerIds") String employerIds, HttpServletRequest request) {

        log.debug("[getJobsByPreachIdAction] begin");

        try {
            List<Job> jobs = preachService.qryJobsByEmployIds(employerIds);
            setRetInfo("job", jobs);
        } catch (Exception e) {
            log.debug("getJobsByPreachIdAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[getJobsByPreachIdAction] end");

        return retMap;
    }
}
