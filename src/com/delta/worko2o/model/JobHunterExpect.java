package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_EXPECT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_EXPECT")
public class JobHunterExpect {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_expect_id
     */
    @Id
    @Column(value = "job_hunter_expect_id")
    private Integer jobHunterExpectId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * city
     */
    @Column(value = "city")
    private String city;

    /**
     * work_pattern
     */
    @Column(value = "work_pattern")
    private String workPattern;

    /**
     * expect_job
     */
    @Column(value = "expect_job")
    private String expectJob;

    /**
     * expect_salary
     */
    @Column(value = "expect_salary")
    private String expectSalary;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    public Integer getJobHunterExpectId() {
        return this.jobHunterExpectId;
    }

    public void setJobHunterExpectId(Integer jobHunterExpectId) {
        this.jobHunterExpectId = jobHunterExpectId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkPattern() {
        return this.workPattern;
    }

    public void setWorkPattern(String workPattern) {
        this.workPattern = workPattern;
    }

    public String getExpectJob() {
        return this.expectJob;
    }

    public void setExpectJob(String expectJob) {
        this.expectJob = expectJob;
    }

    public String getExpectSalary() {
        return this.expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
