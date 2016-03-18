package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_JOB的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-49-09 01:49:00 <br>
 * @since V1.0<br>
 */
@Table("T_JOB")
public class Job {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_id
     */
    @Id
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * job_name
     */
    @Column(value = "job_name")
    private String jobName;

    /**
     * job_main_type
     */
    @Column(value = "job_main_type")
    private String jobMainType;

    /**
     * job_sub_type
     */
    @Column(value = "job_sub_type")
    private String jobSubType;

    /**
     * work_pattern
     */
    @Column(value = "work_pattern")
    private String workPattern;

    /**
     * job_desc
     */
    @Column(value = "job_desc")
    private String jobDesc;

    /**
     * exp_req
     */
    @Column(value = "exp_req")
    private String expReq;

    /**
     * edu_req
     */
    @Column(value = "edu_req")
    private String eduReq;

    /**
     * major_req
     */
    @Column(value = "major_req")
    private String majorReq;

    /**
     * sex_req
     */
    @Column(value = "sex_req")
    private String sexReq;

    /**
     * salary_begin
     */
    @Column(value = "salary_begin")
    private Integer salaryBegin;

    /**
     * salary_end
     */
    @Column(value = "salary_end")
    private Integer salaryEnd;

    /**
     * number_req
     */
    @Column(value = "number_req")
    private Integer numberReq;

    /**
     * province
     */
    @Column(value = "province")
    private String province;

    /**
     * city
     */
    @Column(value = "city")
    private String city;

    /**
     * district
     */
    @Column(value = "district")
    private String district;

    /**
     * job_praised_cnt
     */
    @Column(value = "job_praised_cnt")
    private Integer jobPraisedCnt;

    /**
     * job_comment_cnt
     */
    @Column(value = "job_comment_cnt")
    private Integer jobCommentCnt;

    /**
     * read_times
     */
    @Default("0")
    @Column(value = "read_times")
    private Integer readTimes;

    /**
     * main_page
     */
    @Default("0")
    @Column(value = "main_page")
    private Integer mainPage;

    /**
     * publish_time
     */
    @Column(value = "publish_time")
    private java.util.Date publishTime;

    /**
     * dead_time
     */
    @Column(value = "dead_time")
    private java.util.Date deadTime;

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
     * website
     */
    @Column(value = "website")
    private String website;

    /**
     * website_show
     */
    @Column(value = "website_show")
    private Integer websiteShow;

    /**
     * store_name
     */
    @Column(value = "store_name")
    private String storeName;

    /**
     * sale_type
     */
    @Column(value = "sale_type")
    private String saleType;

    /**
     * employer_scale
     */
    @Column(value = "employer_scale")
    private String employerScale;

    /**
     * logo_path
     */
    @Column(value = "logo_path")
    private String logoPath;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    /**
     * tag1
     */
    @Column(value = "tag1")
    private String tag1;

    /**
     * tag2
     */
    @Column(value = "tag2")
    private String tag2;

    /**
     * tag3
     */
    @Column(value = "tag3")
    private String tag3;

    /**
     * tag4
     */
    @Column(value = "tag4")
    private String tag4;

    /**
     * tag5
     */
    @Column(value = "tag5")
    private String tag5;

    /**
     * status
     */
    @Column(value = "status")
    private Integer status;

    /**
     * lat
     */
    @Column(value = "lat")
    private String lat;

    /**
     * lng
     */
    @Column(value = "lng")
    private String lng;

    /**
     * questionnaire_id
     */
    @Column(value = "questionnaire_id")
    private Integer questionnaireId;

    /**
     * questionnaire_on
     */
    @Default("1")
    @Column(value = "questionnaire_on")
    private Integer questionnaireOn;

    /**
     * qq
     */
    @Column(value = "qq")
    private Integer qq;

    /**
     * preach_job
     */
    @Column(value = "preach_job")
    private Integer preachJob;

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobMainType() {
        return this.jobMainType;
    }

    public void setJobMainType(String jobMainType) {
        this.jobMainType = jobMainType;
    }

    public String getJobSubType() {
        return this.jobSubType;
    }

    public void setJobSubType(String jobSubType) {
        this.jobSubType = jobSubType;
    }

    public String getWorkPattern() {
        return this.workPattern;
    }

    public void setWorkPattern(String workPattern) {
        this.workPattern = workPattern;
    }

    public String getJobDesc() {
        return this.jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getExpReq() {
        return this.expReq;
    }

    public void setExpReq(String expReq) {
        this.expReq = expReq;
    }

    public String getEduReq() {
        return this.eduReq;
    }

    public void setEduReq(String eduReq) {
        this.eduReq = eduReq;
    }

    public String getMajorReq() {
        return this.majorReq;
    }

    public void setMajorReq(String majorReq) {
        this.majorReq = majorReq;
    }

    public String getSexReq() {
        return this.sexReq;
    }

    public void setSexReq(String sexReq) {
        this.sexReq = sexReq;
    }

    public Integer getSalaryBegin() {
        return this.salaryBegin;
    }

    public void setSalaryBegin(Integer salaryBegin) {
        this.salaryBegin = salaryBegin;
    }

    public Integer getSalaryEnd() {
        return this.salaryEnd;
    }

    public void setSalaryEnd(Integer salaryEnd) {
        this.salaryEnd = salaryEnd;
    }

    public Integer getNumberReq() {
        return this.numberReq;
    }

    public void setNumberReq(Integer numberReq) {
        this.numberReq = numberReq;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getJobPraisedCnt() {
        return this.jobPraisedCnt;
    }

    public void setJobPraisedCnt(Integer jobPraisedCnt) {
        this.jobPraisedCnt = jobPraisedCnt;
    }

    public Integer getJobCommentCnt() {
        return this.jobCommentCnt;
    }

    public void setJobCommentCnt(Integer jobCommentCnt) {
        this.jobCommentCnt = jobCommentCnt;
    }

    public Integer getReadTimes() {
        return this.readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getMainPage() {
        return this.mainPage;
    }

    public void setMainPage(Integer mainPage) {
        this.mainPage = mainPage;
    }

    public java.util.Date getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(java.util.Date publishTime) {
        this.publishTime = publishTime;
    }

    public java.util.Date getDeadTime() {
        return this.deadTime;
    }

    public void setDeadTime(java.util.Date deadTime) {
        this.deadTime = deadTime;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getWebsiteShow() {
        return websiteShow;
    }

    public void setWebsiteShow(Integer websiteShow) {
        this.websiteShow = websiteShow;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSaleType() {
        return this.saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getEmployerScale() {
        return this.employerScale;
    }

    public void setEmployerScale(String employerScale) {
        this.employerScale = employerScale;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTag1() {
        return this.tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return this.tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return this.tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return this.tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getTag5() {
        return this.tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getQuestionnaireOn() {
        return questionnaireOn;
    }

    public void setQuestionnaireOn(Integer questionnaireOn) {
        this.questionnaireOn = questionnaireOn;
    }

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public Integer getPreachJob() {
        return preachJob;
    }

    public void setPreachJob(Integer preachJob) {
        this.preachJob = preachJob;
    }
}
