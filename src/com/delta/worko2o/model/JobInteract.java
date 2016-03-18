package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_JOB_INTERACT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-59-15 10:59:04 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_INTERACT")
public class JobInteract {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_interact_id
     */
    @Id
    @Column(value = "job_interact_id")
    private Integer jobInteractId;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private Integer employerId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * job_id
     */
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * hunter_status
     */
    @Default("0")
    @Column(value = "hunter_status")
    private Integer hunterStatus;

    /**
     * employer_status
     */
    @Default("0")
    @Column(value = "employer_status")
    private Integer employerStatus;

    /**
     * hunter_status_changed
     */
    @Default("0")
    @Column(value = "hunter_status_changed")
    private Integer hunterStatusChanged;

    /**
     * employer_status_changed
     */
    @Default("0")
    @Column(value = "employer_status_changed")
    private Integer employerStatusChanged;

    /**
     * hunter_update_time
     */
    @Column(value = "hunter_update_time")
    private java.util.Date hunterUpdateTime;

    /**
     * employer_update_time
     */
    @Column(value = "employer_update_time")
    private java.util.Date employerUpdateTime;

    /**
     * join_feedback
     */
    @Default("0")
    @Column(value = "join_feedback")
    private Integer joinFeedback;

    /**
     * accept_feedback
     */
    @Default("0")
    @Column(value = "accept_feedback")
    private Integer acceptFeedback;

    /**
     * interview_feedback
     */
    @Default("0")
    @Column(value = "interview_feedback")
    private Integer interviewFeedback;

    public Integer getJobInteractId() {
        return this.jobInteractId;
    }

    public void setJobInteractId(Integer jobInteractId) {
        this.jobInteractId = jobInteractId;
    }

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getHunterStatus() {
        return this.hunterStatus;
    }

    public void setHunterStatus(Integer hunterStatus) {
        this.hunterStatus = hunterStatus;
    }

    public Integer getEmployerStatus() {
        return this.employerStatus;
    }

    public void setEmployerStatus(Integer employerStatus) {
        this.employerStatus = employerStatus;
    }

    public Integer getHunterStatusChanged() {
        return this.hunterStatusChanged;
    }

    public void setHunterStatusChanged(Integer hunterStatusChanged) {
        this.hunterStatusChanged = hunterStatusChanged;
    }

    public Integer getEmployerStatusChanged() {
        return this.employerStatusChanged;
    }

    public void setEmployerStatusChanged(Integer employerStatusChanged) {
        this.employerStatusChanged = employerStatusChanged;
    }

    public java.util.Date getHunterUpdateTime() {
        return this.hunterUpdateTime;
    }

    public void setHunterUpdateTime(java.util.Date hunterUpdateTime) {
        this.hunterUpdateTime = hunterUpdateTime;
    }

    public java.util.Date getEmployerUpdateTime() {
        return this.employerUpdateTime;
    }

    public void setEmployerUpdateTime(java.util.Date employerUpdateTime) {
        this.employerUpdateTime = employerUpdateTime;
    }

    public Integer getJoinFeedback() {
        return this.joinFeedback;
    }

    public void setJoinFeedback(Integer joinFeedback) {
        this.joinFeedback = joinFeedback;
    }

    public Integer getAcceptFeedback() {
        return this.acceptFeedback;
    }

    public void setAcceptFeedback(Integer acceptFeedback) {
        this.acceptFeedback = acceptFeedback;
    }

    public Integer getInterviewFeedback() {
        return interviewFeedback;
    }

    public void setInterviewFeedback(Integer interviewFeedback) {
        this.interviewFeedback = interviewFeedback;
    }
}
