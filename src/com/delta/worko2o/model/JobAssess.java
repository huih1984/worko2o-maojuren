package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_JOB_ASSESS的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-25-02 10:25:27 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_ASSESS")
public class JobAssess {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_assess_id
     */
    @Id
    @Column(value = "job_assess_id")
    private Integer jobAssessId;

    /**
     * job_id
     */
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * join_writtentest
     */
    @Default("0")
    @Column(value = "join_writtentest")
    private Integer joinWrittentest;

    /**
     * interviewer_nice
     */
    @Default("0")
    @Column(value = "interviewer_nice")
    private Integer interviewerNice;

    /**
     * description_match
     */
    @Default("0")
    @Column(value = "description_match")
    private Integer descriptionMatch;

    public Integer getJobAssessId() {
        return this.jobAssessId;
    }

    public void setJobAssessId(Integer jobAssessId) {
        this.jobAssessId = jobAssessId;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobHunterId() {
        return jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getJoinWrittentest() {
        return this.joinWrittentest;
    }

    public void setJoinWrittentest(Integer joinWrittentest) {
        this.joinWrittentest = joinWrittentest;
    }

    public Integer getInterviewerNice() {
        return this.interviewerNice;
    }

    public void setInterviewerNice(Integer interviewerNice) {
        this.interviewerNice = interviewerNice;
    }

    public Integer getDescriptionMatch() {
        return this.descriptionMatch;
    }

    public void setDescriptionMatch(Integer descriptionMatch) {
        this.descriptionMatch = descriptionMatch;
    }

}
