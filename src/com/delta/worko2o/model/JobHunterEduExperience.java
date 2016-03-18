package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_EDU_EXPERIENCE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_EDU_EXPERIENCE")
public class JobHunterEduExperience {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_edu_experience_id
     */
    @Id
    @Column(value = "job_hunter_edu_experience_id")
    private Integer jobHunterEduExperienceId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * college
     */
    @Column(value = "college")
    private String college;

    /**
     * major
     */
    @Column(value = "major")
    private String major;

    /**
     * bachelor_degree
     */
    @Column(value = "bachelor_degree")
    private String bachelorDegree;

    /**
     * begin_time
     */
    @Column(value = "begin_time")
    private java.util.Date beginTime;

    /**
     * end_time
     */
    @Column(value = "end_time")
    private java.util.Date endTime;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    public Integer getJobHunterEduExperienceId() {
        return this.jobHunterEduExperienceId;
    }

    public void setJobHunterEduExperienceId(Integer jobHunterEduExperienceId) {
        this.jobHunterEduExperienceId = jobHunterEduExperienceId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getBachelorDegree() {
        return this.bachelorDegree;
    }

    public void setBachelorDegree(String bachelorDegree) {
        this.bachelorDegree = bachelorDegree;
    }

    public java.util.Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(java.util.Date beginTime) {
        this.beginTime = beginTime;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
