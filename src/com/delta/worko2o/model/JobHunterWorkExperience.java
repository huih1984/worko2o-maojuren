package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_WORK_EXPERIENCE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-24-23 05:24:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_WORK_EXPERIENCE")
public class JobHunterWorkExperience {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_work_experience_id
     */
    @Id
    @Column(value = "job_hunter_work_experience_id")
    private Integer jobHunterWorkExperienceId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * employer_name
     */
    @Column(value = "employer_name")
    private String employerName;

    /**
     * job_name
     */
    @Column(value = "job_name")
    private String jobName;

    /**
     * work_desc
     */
    @Column(value = "work_desc")
    private String workDesc;

    /**
     * salary
     */
    @Column(value = "salary")
    private String salary;

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

    public Integer getJobHunterWorkExperienceId() {
        return this.jobHunterWorkExperienceId;
    }

    public void setJobHunterWorkExperienceId(Integer jobHunterWorkExperienceId) {
        this.jobHunterWorkExperienceId = jobHunterWorkExperienceId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getEmployerName() {
        return this.employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getWorkDesc() {
        return this.workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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
