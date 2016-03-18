package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_SEARCH_CLASS的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_SEARCH_CLASS")
public class JobSearchClass {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_search_class_id
     */
    @Id
    @Column(value = "job_search_class_id")
    private Integer jobSearchClassId;

    /**
     * parent_class_property_id
     */
    @Column(value = "parent_class_property_id")
    private Integer parentClassPropertyId;

    /**
     * have_sub_class_property
     */
    @Column(value = "have_sub_class_property")
    private Integer haveSubClassProperty;

    /**
     * class_property_id
     */
    @Column(value = "class_property_id")
    private Integer classPropertyId;

    /**
     * class_property_name
     */
    @Column(value = "class_property_name")
    private String classPropertyName;

    /**
     * class_title_name
     */
    @Column(value = "class_title_name")
    private String classTitleName;

    /**
     * is_threshold
     */
    @Column(value = "is_threshold")
    private Integer isThreshold;

    /**
     * class_threshold_begin
     */
    @Column(value = "class_threshold_begin")
    private Integer classThresholdBegin;

    /**
     * class_threshold_end
     */
    @Column(value = "class_threshold_end")
    private Integer classThresholdEnd;

    public Integer getJobSearchClassId() {
        return this.jobSearchClassId;
    }

    public void setJobSearchClassId(Integer jobSearchClassId) {
        this.jobSearchClassId = jobSearchClassId;
    }

    public Integer getParentClassPropertyId() {
        return this.parentClassPropertyId;
    }

    public void setParentClassPropertyId(Integer parentClassPropertyId) {
        this.parentClassPropertyId = parentClassPropertyId;
    }

    public Integer getHaveSubClassProperty() {
        return this.haveSubClassProperty;
    }

    public void setHaveSubClassProperty(Integer haveSubClassProperty) {
        this.haveSubClassProperty = haveSubClassProperty;
    }

    public Integer getClassPropertyId() {
        return this.classPropertyId;
    }

    public void setClassPropertyId(Integer classPropertyId) {
        this.classPropertyId = classPropertyId;
    }

    public String getClassPropertyName() {
        return this.classPropertyName;
    }

    public void setClassPropertyName(String classPropertyName) {
        this.classPropertyName = classPropertyName;
    }

    public String getClassTitleName() {
        return this.classTitleName;
    }

    public void setClassTitleName(String classTitleName) {
        this.classTitleName = classTitleName;
    }

    public Integer getIsThreshold() {
        return this.isThreshold;
    }

    public void setIsThreshold(Integer isThreshold) {
        this.isThreshold = isThreshold;
    }

    public Integer getClassThresholdBegin() {
        return this.classThresholdBegin;
    }

    public void setClassThresholdBegin(Integer classThresholdBegin) {
        this.classThresholdBegin = classThresholdBegin;
    }

    public Integer getClassThresholdEnd() {
        return this.classThresholdEnd;
    }

    public void setClassThresholdEnd(Integer classThresholdEnd) {
        this.classThresholdEnd = classThresholdEnd;
    }

}
