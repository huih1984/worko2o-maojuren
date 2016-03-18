package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_TRAIN_EXPERIENCE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-34-18 03:34:21 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_TRAIN_EXPERIENCE")
public class JobHunterTrainExperience {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_train_experience_id
     */
    @Id
    @Column(value = "job_hunter_train_experience_id")
    private Integer jobHunterTrainExperienceId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * train_institution_name
     */
    @Column(value = "train_institution_name")
    private String trainInstitutionName;

    /**
     * course_name
     */
    @Column(value = "course_name")
    private String courseName;

    /**
     * certificate_name
     */
    @Column(value = "certificate_name")
    private String certificateName;

    /**
     * train_desc
     */
    @Column(value = "train_desc")
    private String trainDesc;

    /**
     * train_begin_date
     */
    @Column(value = "train_begin_date")
    private java.util.Date trainBeginDate;

    /**
     * train_end_date
     */
    @Column(value = "train_end_date")
    private java.util.Date trainEndDate;

    public Integer getJobHunterTrainExperienceId() {
        return this.jobHunterTrainExperienceId;
    }

    public void setJobHunterTrainExperienceId(Integer jobHunterTrainExperienceId) {
        this.jobHunterTrainExperienceId = jobHunterTrainExperienceId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getTrainInstitutionName() {
        return this.trainInstitutionName;
    }

    public void setTrainInstitutionName(String trainInstitutionName) {
        this.trainInstitutionName = trainInstitutionName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCertificateName() {
        return this.certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getTrainDesc() {
        return this.trainDesc;
    }

    public void setTrainDesc(String trainDesc) {
        this.trainDesc = trainDesc;
    }

    public java.util.Date getTrainBeginDate() {
        return this.trainBeginDate;
    }

    public void setTrainBeginDate(java.util.Date trainBeginDate) {
        this.trainBeginDate = trainBeginDate;
    }

    public java.util.Date getTrainEndDate() {
        return this.trainEndDate;
    }

    public void setTrainEndDate(java.util.Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }

}
