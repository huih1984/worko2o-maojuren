package com.delta.worko2o.service.impl;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.delta.worko2o.model.JobHunterCurrentStatus;
import com.delta.worko2o.model.JobSearchClass;
import com.delta.worko2o.model.Region;
import com.delta.worko2o.service.api.DictionaryServiceApi;

@IocBean(name = "dictionaryService")
public class DictionaryServiceImpl implements DictionaryServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    public List<Region> qryProvince() {
        log.debug("[dictionaryService]-[qryProvince]-begin");

        Condition c = Cnd.format("where substring(region_code,3,4)='0000'");
        List<Region> tempList = dao.query(Region.class, c, null);

        log.debug("[dictionaryService]-[qryProvince]-end");

        return tempList;
    }

    public List<Region> qryCityByProvinceCode(Integer provinceCode) {
        log.debug("[dictionaryService]-[qryCityByProvinceCode]-begin");

        Condition c = Cnd.format("where substring(region_code, 1, 2)='%d'"
                + " and substring(region_code, 3, 2)<>'00' and substring(region_code,5,2)='00'",
                provinceCode);
        List<Region> tempList = dao.query(Region.class, c, null);

        log.debug("[dictionaryService]-[qryCityByProvinceCode]-end");

        return tempList;
    }

    public List<Region> qryAreaByCityCode(Integer cityCode) {
        log.debug("[dictionaryService]-[qryAreaByCityCode]-begin");

        Condition c = Cnd.format("where substring(region_code, 1, 4)='%d'"
                + " and substring(region_code, 5, 2)<>'00'",
                cityCode);
        List<Region> tempList = dao.query(Region.class, c, null);

        log.debug("[dictionaryService]-[qryAreaByCityCode]-end");

        return tempList;
    }

    public List<JobSearchClass> qryCommonDictionaryByClassId(Integer classId) {
        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-begin");

        Condition c = Cnd.format("where substring(class_property_id, 1, 2)='%d'",
                classId);
        List<JobSearchClass> tempList = dao.query(JobSearchClass.class, c, null);

        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-end");

        return tempList;
    }

    public List<JobSearchClass> qryCommonDictionaryByClassIdAuto(Integer classId) {
        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-begin");

        Condition c = Cnd.format("where substring(class_property_id, " + 1 + ", " + classId.toString().length() + ")='%d' and parent_class_property_id is null", classId);

        List<JobSearchClass> tempList = dao.query(JobSearchClass.class, c, null);

        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-end");

        return tempList;
    }


    public List<JobSearchClass> qryCommonDictionaryByParentClassId(Integer parentClassId) {
        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-begin");

        Condition c = Cnd.where("parent_class_property_id", "=", parentClassId);

        List<JobSearchClass> tempList = dao.query(JobSearchClass.class, c, null);

        log.debug("[dictionaryService]-[qryCommonDictionaryByClassId]-end");

        return tempList;
    }

    @Override
    public List<Region> qryCityMainPage() {
        log.debug("[dictionaryService]-[qryCityMainPage]-begin");

        Condition c = Cnd.where("main_page", "=", "1");

        List<Region> tempList = dao.query(Region.class, c, null);

        log.debug("[dictionaryService]-[qryCityMainPage]-end");

        return tempList;
    }

    @Override
    public List<JobHunterCurrentStatus> qryCurrentStatus() {
        log.debug("[dictionaryService]-[qryCurrentStatus]-begin");

        List<JobHunterCurrentStatus> tempList = dao.query(JobHunterCurrentStatus.class, null, null);

        log.debug("[dictionaryService]-[qryCurrentStatus]-end");

        return tempList;
    }

    @Override
    public List<Region> qryCityList() {
        log.debug("[dictionaryService]-[qryCityList]-begin");

        Condition c = Cnd.format("where substring(region_code,5,6)='00'");
        List<Region> tempList = dao.query(Region.class, c, null);

        log.debug("[dictionaryService]-[qryCityList]-end");

        return tempList;
    }
}
