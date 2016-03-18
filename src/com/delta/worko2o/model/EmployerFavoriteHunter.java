package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * <Description> T_EMPLOYER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_EMPLOYER_FAVORITE_HUNTER")
public class EmployerFavoriteHunter {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * employer_favorite_hunter_id
     */
    @Id
    @Column(value = "employer_favorite_hunter_id")
    private Integer employerFavoriteHunterId;

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
     * favorited
     */
    @Default("0")
    @Column(value = "favorited")
    private Integer favorited;

    /**
     * downloaded
     */
    @Default("0")
    @Column(value = "downloaded")
    private Integer downloaded;

    /**
     * update_time
     */
    @Default("1979-01-01 00:00:00")
    @Column(value = "update_time")
    private java.util.Date updateTime;

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getEmployerFavoriteHunterId() {
        return employerFavoriteHunterId;
    }

    public void setEmployerFavoriteHunterId(Integer employerFavoriteHunterId) {
        this.employerFavoriteHunterId = employerFavoriteHunterId;
    }

    public Integer getJobHunterId() {
        return jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
    }

    public Integer getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Integer downloaded) {
        this.downloaded = downloaded;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
