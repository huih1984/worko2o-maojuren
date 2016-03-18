package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_PROGRAM_EXPERIENCE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_PROGRAM_EXPERIENCE")
public class JobHunterProgramExperience {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_program_experience_id
     */
    @Id
    @Column(value = "job_hunter_program_experience_id")
    private Integer jobHunterProgramExperienceId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * program_name
     */
    @Column(value = "program_name")
    private String programName;

    /**
     * program_job_name
     */
    @Column(value = "program_job_name")
    private String programJobName;

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
     * program_desc
     */
    @Column(value = "program_desc")
    private String programDesc;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    public Integer getJobHunterProgramExperienceId() {
        return this.jobHunterProgramExperienceId;
    }

    public void setJobHunterProgramExperienceId(Integer jobHunterProgramExperienceId) {
        this.jobHunterProgramExperienceId = jobHunterProgramExperienceId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramJobName() {
        return this.programJobName;
    }

    public void setProgramJobName(String programJobName) {
        this.programJobName = programJobName;
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

    public String getProgramDesc() {
        return this.programDesc;
    }

    public void setProgramDesc(String programDesc) {
        this.programDesc = programDesc;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
