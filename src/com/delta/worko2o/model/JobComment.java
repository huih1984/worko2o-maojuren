package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_JOB_COMMENT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_COMMENT")
public class JobComment {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * comment_id
     */
    @Id
    @Column(value = "comment_id")
    private Integer commentId;

    /**
     * job_id
     */
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * job_comment
     */
    @Column(value = "job_comment")
    private String jobComment;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private String jobHunterId;

    /**
     * job_hunter_name
     */
    @Column(value = "job_hunter_name")
    private String jobHunterName;

    /**
     * job_hunter_avatar_path
     */
    @Column(value = "job_hunter_avatar_path")
    private String jobHunterAvatarPath;

    /**
     * comment_time
     */
    @Column(value = "comment_time")
    private java.util.Date commentTime;

    /**
     * praise_this_job
     */
    @Default("0")
    @Column(value = "praise_this_comment")
    private Integer praiseThisComment;

    /**
     * deprecate_this_comment
     */
    @Default("0")
    @Column(value = "deprecate_this_comment")
    private Integer deprecateThisComment;

    public Integer getCommentId() {
        return this.commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobComment() {
        return this.jobComment;
    }

    public void setJobComment(String jobComment) {
        this.jobComment = jobComment;
    }

    public String getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(String jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getJobHunterName() {
        return this.jobHunterName;
    }

    public void setJobHunterName(String jobHunterName) {
        this.jobHunterName = jobHunterName;
    }

    public String getJobHunterAvatarPath() {
        return this.jobHunterAvatarPath;
    }

    public void setJobHunterAvatarPath(String jobHunterAvatarPath) {
        this.jobHunterAvatarPath = jobHunterAvatarPath;
    }

    public java.util.Date getCommentTime() {
        return this.commentTime;
    }

    public void setCommentTime(java.util.Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getPraiseThisComment() {
        return this.praiseThisComment;
    }

    public void setPraiseThisComment(Integer praiseThisComment) {
        this.praiseThisComment = praiseThisComment;
    }

    public Integer getDeprecateThisComment() {
        return deprecateThisComment;
    }

    public void setDeprecateThisComment(Integer deprecateThisComment) {
        this.deprecateThisComment = deprecateThisComment;
    }
}
