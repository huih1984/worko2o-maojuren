package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_JOB_HUNTER_FAVORITE_JOB的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_FAVORITE_JOB")
public class JobHunterFavoriteJob {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * my_favorite_job_id
     */
    @Id
    @Column(value = "my_favorite_job_id")
    private Integer myFavoriteJobId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * job_id
     */
    @Default("0")
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * favorited
     */
    @Default("0")
    @Column(value = "favorited")
    private Integer favorited;

    /**
     * diliver
     */
    @Default("0")
    @Column(value = "diliver")
    private Integer diliver;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    public Integer getMyFavoriteJobId() {
        return this.myFavoriteJobId;
    }

    public void setMyFavoriteJobId(Integer myFavoriteJobId) {
        this.myFavoriteJobId = myFavoriteJobId;
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

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
    }

    public Integer getDiliver() {
        return this.diliver;
    }

    public void setDiliver(Integer diliver) {
        this.diliver = diliver;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}
