package com.delta.worko2o.action;

import com.delta.worko2o.service.api.JobServiceApi;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-7-26
 * Time: 上午10:48
 * To change this template use File | Settings | File Templates.
 */
@IocBean(singleton = false)
public class CommAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private JobServiceApi jobService;

    @At("/page/demo/comm/online")
    public java.util.Map<String, ?> onlineRAction(@Param("id") Integer id, @Param("type") Integer type) {

        log.debug("[onlineRAction] begin");
        return retMap;
    }
}

