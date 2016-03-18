package com.delta.worko2o.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.JobHunterCurrentStatus;
import com.delta.worko2o.model.JobSearchClass;
import com.delta.worko2o.model.Region;
import com.delta.worko2o.service.api.DictionaryServiceApi;

/**
 * <Description> <br>
 * 职位相关操作类
 *
 * @param <T>
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月13日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class DictionaryAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private DictionaryServiceApi dictionaryService;

    /**
     * 省字典查询
     *
     * @return
     */
    @At("/page/demo/dic/province")
    public Map<String, Object> provinceRAction() {

        log.debug("[provinceRAction] begin");

        try {
            List<Region> regions = dictionaryService.qryProvince();

            if (Lang.isEmpty(regions)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found province");
            } else {
                setRetInfo(RetConstants.KEY_REGION_LIST, regions);
            }
        } catch (Exception e) {
            log.debug("provinceRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[provinceRAction] end");

        return retMap;
    }

    /**
     * 市字典查询
     *
     * @return
     */
    @At("/page/demo/dic/city")
    public Map<String, Object> cityRAction(
            @Param("provinceCode") Integer provinceCode) {

        log.debug("[cityRAction] begin");

        try {
            List<Region> regions = dictionaryService.qryCityByProvinceCode(provinceCode);

            if (Lang.isEmpty(regions)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found city");
            } else {
                setRetInfo(RetConstants.KEY_REGION_LIST, regions);
            }
        } catch (Exception e) {
            log.debug("cityRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[cityRAction] end");

        return retMap;
    }

    /**
     * 地区字典查询
     *
     * @return
     */
    @At("/page/demo/dic/area")
    public Map<String, Object> areaRAction(
            @Param("cityCode") Integer cityCode) {

        log.debug("[areaRAction] begin");

        try {
            List<Region> regions = dictionaryService.qryAreaByCityCode(cityCode);

            if (Lang.isEmpty(regions)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found area");
            } else {
                setRetInfo(RetConstants.KEY_REGION_LIST, regions);
            }
        } catch (Exception e) {
            log.debug("areaRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[areaRAction] end");

        return retMap;
    }

    /**
     * 通用字典查询
     *
     * @return
     */
    @At("/page/demo/dic/common")
    public Map<String, Object> commonRAction(
            @Param("classId") Integer classId) {

        log.debug("[commonRAction] begin");

        try {
            List<JobSearchClass> jobSearchClasses = dictionaryService.qryCommonDictionaryByClassId(classId);

            if (Lang.isEmpty(jobSearchClasses)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found common dictionary");
            } else {
                setRetInfo(RetConstants.KEY_COMMON_DICTIONARY_LIST, jobSearchClasses);
            }
        } catch (Exception e) {
            log.debug("commonRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[commonRAction] end");

        return retMap;
    }

    /**
     * 通用字典查询
     *
     * @return
     */
    @At("/page/demo/dic/commonWithCodition1")
    public Map<String, Object> commonRAction1(
            @Param("classId") Integer classId) {

        log.debug("[commonRAction] begin");

        try {
            List<JobSearchClass> jobSearchClasses = dictionaryService.qryCommonDictionaryByClassIdAuto(classId);

            if (Lang.isEmpty(jobSearchClasses)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found common dictionary");
            } else {
                setRetInfo(RetConstants.KEY_COMMON_DICTIONARY_LIST, jobSearchClasses);
            }
        } catch (Exception e) {
            log.debug("commonRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[commonRAction] end");

        return retMap;
    }

    /**
     * 通用字典查询
     *
     * @return
     */
    @At("/page/demo/dic/commonWithCodition2")
    public Map<String, Object> commonRAction2(
            @Param("parentClassId") Integer parentClassId) {

        log.debug("[commonRAction] begin");

        try {
            List<JobSearchClass> jobSearchClasses = dictionaryService.qryCommonDictionaryByParentClassId(parentClassId);

            if (Lang.isEmpty(jobSearchClasses)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found common dictionary");
            } else {
                setRetInfo(RetConstants.KEY_COMMON_DICTIONARY_LIST, jobSearchClasses);
            }
        } catch (Exception e) {
            log.debug("commonRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[commonRAction] end");

        return retMap;
    }

    /**
     * 主页上显示的市
     *
     * @return
     */
    @At("/page/demo/dic/cityMainPage")
    public Map<String, Object> cityRActionMainPage() {

        log.debug("[cityRActionMainPage] begin");

        try {
            List<Region> regions = dictionaryService.qryCityMainPage();

            if (Lang.isEmpty(regions)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found city");
            } else {
                setRetInfo(RetConstants.KEY_REGION_LIST, regions);
            }
        } catch (Exception e) {
            log.debug("cityRActionMainPage error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[cityRActionMainType] end");

        return retMap;
    }

    /**
     * 当前状态字典查询
     *
     * @return
     */
    @At("/page/demo/dic/currentstatus")
    public Map<String, Object> currentStatusRAction() {

        log.debug("[currentStatusRAction] begin");

        try {
            List<JobHunterCurrentStatus> jobHunterCurrentStatus = dictionaryService.qryCurrentStatus();

            if (Lang.isEmpty(jobHunterCurrentStatus)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found current status");
            } else {
                setRetInfo(RetConstants.KEY_CURRENT_STATUS_LIST, jobHunterCurrentStatus);
            }
        } catch (Exception e) {
            log.debug("currentStatusRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[currentStatusRAction] end");

        return retMap;
    }

    /**
     * 所有市
     *
     * @return
     */
    @At("/page/demo/dic/cityList")
    public Map<String, Object> cityListRAction() {

        log.debug("[cityListRAction] begin");

        try {
            List<Region> regions = dictionaryService.qryCityList();

            if (Lang.isEmpty(regions)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found city");
            } else {
                setRetInfo(RetConstants.KEY_REGION_LIST, regions);
            }
        } catch (Exception e) {
            log.debug("cityListRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[cityListRAction] end");

        return retMap;
    }
}
