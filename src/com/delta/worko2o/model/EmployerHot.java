package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * <Description> T_EMPLOYER_HOT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-31-04 10:31:33 <br>
 * @since V1.0<br>
 */
@Table("T_EMPLOYER_HOT")
public class EmployerHot {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * employer_hot_id
     */
    @Id
    @Column(value = "employer_hot_id")
    private Integer employerHotId;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private Integer employerId;

    /**
     * employer_name
     */
    @Column(value = "employer_name")
    private String employerName;

    /**
     * logo_path
     */
    @Column(value = "logo_path")
    private String logoPath;

    /**
     * job_cnt
     */
    @Column(value = "job_cnt")
    private Integer jobCnt;

    /**
     * indication
     */
    @Column(value = "indication")
    private Integer indication;

    /**
     * hr_score
     */
    @Column(value = "hr_score")
    private Integer hrScore;

    /**
     * join_score
     */
    @Column(value = "join_score")
    private Integer joinScore;

    /**
     * day1times
     */
    @Default("1")
    @Column(value = "day1times")
    private Integer day1times;

    /**
     * day2times
     */
    @Default("1")
    @Column(value = "day2times")
    private Integer day2times;

    /**
     * day3times
     */
    @Default("1")
    @Column(value = "day3times")
    private Integer day3times;

    /**
     * day4times
     */
    @Default("1")
    @Column(value = "day4times")
    private Integer day4times;

    /**
     * day5times
     */
    @Default("1")
    @Column(value = "day5times")
    private Integer day5times;

    /**
     * day6times
     */
    @Default("1")
    @Column(value = "day6times")
    private Integer day6times;

    /**
     * day7times
     */
    @Default("1")
    @Column(value = "day7times")
    private Integer day7times;

    public Integer getEmployerHotId() {
        return this.employerHotId;
    }

    public void setEmployerHotId(Integer employerHotId) {
        this.employerHotId = employerHotId;
    }

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return this.employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Integer getJobCnt() {
        return this.jobCnt;
    }

    public void setJobCnt(Integer jobCnt) {
        this.jobCnt = jobCnt;
    }

    public Integer getIndication() {
        return this.indication;
    }

    public void setIndication(Integer indication) {
        this.indication = indication;
    }

    public Integer getHrScore() {
        return this.hrScore;
    }

    public void setHrScore(Integer hrScore) {
        this.hrScore = hrScore;
    }

    public Integer getJoinScore() {
        return this.joinScore;
    }

    public void setJoinScore(Integer joinScore) {
        this.joinScore = joinScore;
    }

    public Integer getDay1times() {
        return this.day1times;
    }

    public void setDay1times(Integer day1times) {
        this.day1times = day1times;
    }

    public Integer getDay2times() {
        return this.day2times;
    }

    public void setDay2times(Integer day2times) {
        this.day2times = day2times;
    }

    public Integer getDay3times() {
        return this.day3times;
    }

    public void setDay3times(Integer day3times) {
        this.day3times = day3times;
    }

    public Integer getDay4times() {
        return this.day4times;
    }

    public void setDay4times(Integer day4times) {
        this.day4times = day4times;
    }

    public Integer getDay5times() {
        return this.day5times;
    }

    public void setDay5times(Integer day5times) {
        this.day5times = day5times;
    }

    public Integer getDay6times() {
        return this.day6times;
    }

    public void setDay6times(Integer day6times) {
        this.day6times = day6times;
    }

    public Integer getDay7times() {
        return this.day7times;
    }

    public void setDay7times(Integer day7times) {
        this.day7times = day7times;
    }

}
