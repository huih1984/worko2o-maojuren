package com.delta.worko2o.service.api;

import java.util.List;

import com.delta.worko2o.model.JobHunterCurrentStatus;
import com.delta.worko2o.model.JobSearchClass;
import com.delta.worko2o.model.Region;

public interface DictionaryServiceApi {

    List<Region> qryProvince();

    List<Region> qryCityByProvinceCode(Integer provinceCode);

    List<Region> qryAreaByCityCode(Integer cityCode);

    List<JobSearchClass> qryCommonDictionaryByClassId(Integer classId);

    List<JobSearchClass> qryCommonDictionaryByClassIdAuto(Integer classId);

    List<JobSearchClass> qryCommonDictionaryByParentClassId(Integer classId);

    List<Region> qryCityMainPage();

    List<JobHunterCurrentStatus> qryCurrentStatus();

    List<Region> qryCityList();
}
