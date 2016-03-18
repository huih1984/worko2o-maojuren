package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * <Description> T_JOB_HUNTER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-34-11 02:34:46 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER")
@View("V_JOB_HUNTER")
public class JobHunter implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_id
     */
    @Id
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * sina_id
     */
    @Column(value = "sina_id")
    private String sinaId;

    /**
     * tencent_id
     */
    @Column(value = "tencent_id")
    private String tencentId;

    /**
     * weixin_id
     */
    @Column(value = "weixin_id")
    private String weixinId;

    /**
     * job_hunter_name
     */
    @Column(value = "job_hunter_name")
    private String jobHunterName;

    /**
     * job_hunter_email
     */
    @Column(value = "job_hunter_email")
    private String jobHunterEmail;

    /**
     * job_hunter_password
     */
    @Column(value = "job_hunter_password")
    private String jobHunterPassword;

    /**
     * job_hunter_tel
     */
    @Column(value = "job_hunter_tel")
    private String jobHunterTel;

    /**
     * job_hunter_bachelor_degree
     */
    @Column(value = "job_hunter_bachelor_degree")
    private String jobHunterBachelorDegree;

    /**
     * job_hunter_start_work_year
     */
    @Column(value = "job_hunter_start_work_year")
    private String jobHunterStartWorkYear;

    /**
     * job_hunter_sex
     */
    @Column(value = "job_hunter_sex")
    private String jobHunterSex;

    /**
     * job_hunter_birthday
     */
    @Column(value = "job_hunter_birthday")
    private java.util.Date jobHunterBirthday;

    /**
     * job_hunter_avatar_path
     */
    @Column(value = "job_hunter_avatar_path")
    private String jobHunterAvatarPath;

    /**
     * job_hunter_depict
     */
    @Column(value = "job_hunter_depict")
    private String jobHunterDepict;

    /**
     * job_hunter_current_province
     */
    @Column(value = "job_hunter_current_province")
    private String jobHunterCurrentProvince;

    /**
     * job_hunter_current_city
     */
    @Column(value = "job_hunter_current_city")
    private String jobHunterCurrentCity;

    /**
     * job_hunter_current_district
     */
    @Column(value = "job_hunter_current_district")
    private String jobHunterCurrentDistrict;

    /**
     * job_hunter_current_status
     */
    @Column(value = "job_hunter_current_status")
    private String jobHunterCurrentStatus;

    /**
     * job_hunter_major
     */
    @Column(value = "job_hunter_major")
    private String jobHunterMajor;

    /**
     * job_hunter_college
     */
    @Column(value = "job_hunter_college")
    private String jobHunterCollege;

    /**
     * job_hunter_last_occupation
     */
    @Column(value = "job_hunter_last_occupation")
    private String jobHunterLastOccupation;

    /**
     * job_hunter_last_employer
     */
    @Column(value = "job_hunter_last_employer")
    private String jobHunterLastEmployer;

    /**
     * update_time
     */
    @Default("1979-01-01 00:00:00")
    @Column(value = "update_time")
    private java.util.Date updateTime;

    /**
     * import_from_other
     */
    @Default("0")
    @Column(value = "import_from_other")
    private Integer importFromOther;

    /**
     * job_hunter_specialty
     */
    @Column(value = "job_hunter_specialty")
    private String jobHunterSpecialty;

    /**
     * product_pic1
     */
    @Column(value = "product_pic1")
    private String productPic1;

    /**
     * product_pic2
     */
    @Column(value = "product_pic2")
    private String productPic2;

    /**
     * product_pic3
     */
    @Column(value = "product_pic3")
    private String productPic3;

    /**
     * product_pic4
     */
    @Column(value = "product_pic4")
    private String productPic4;

    /**
     * product_pic5
     */
    @Column(value = "product_pic5")
    private String productPic5;

    /**
     * product_url
     */
    @Column(value = "product_url")
    private String productUrl;

    /**
     * preach_id
     */
    @Column(value = "preach_id")
    private String preachId;

    /**
     * weixin_nickname
     */
    @Column(value = "weixin_nickname")
    private String weixinNickname;


    @Column(value = "expect_salary")
    @Readonly        // <- 这里声明了只读字段，即视图里增加的字段
    private String expectSalary;


    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getSinaId() {
        return this.sinaId;
    }

    public void setSinaId(String sinaId) {
        this.sinaId = sinaId;
    }

    public String getTencentId() {
        return this.tencentId;
    }

    public void setTencentId(String tencentId) {
        this.tencentId = tencentId;
    }

    public String getWeixinId() {
        return this.weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getJobHunterName() {
        return this.jobHunterName;
    }

    public void setJobHunterName(String jobHunterName) {
        this.jobHunterName = jobHunterName;
    }

    public String getJobHunterEmail() {
        return this.jobHunterEmail;
    }

    public void setJobHunterEmail(String jobHunterEmail) {
        this.jobHunterEmail = jobHunterEmail;
    }

    public String getJobHunterPassword() {
        return this.jobHunterPassword;
    }

    public void setJobHunterPassword(String jobHunterPassword) {
        this.jobHunterPassword = jobHunterPassword;
    }

    public String getJobHunterTel() {
        return this.jobHunterTel;
    }

    public void setJobHunterTel(String jobHunterTel) {
        this.jobHunterTel = jobHunterTel;
    }

    public String getJobHunterBachelorDegree() {
        return this.jobHunterBachelorDegree;
    }

    public void setJobHunterBachelorDegree(String jobHunterBachelorDegree) {
        this.jobHunterBachelorDegree = jobHunterBachelorDegree;
    }

    public String getJobHunterStartWorkYear() {
        return jobHunterStartWorkYear;
    }

    public void setJobHunterStartWorkYear(String jobHunterStartWorkYear) {
        this.jobHunterStartWorkYear = jobHunterStartWorkYear;
    }

    public String getJobHunterSex() {
        return this.jobHunterSex;
    }

    public void setJobHunterSex(String jobHunterSex) {
        this.jobHunterSex = jobHunterSex;
    }

    public java.util.Date getJobHunterBirthday() {
        return this.jobHunterBirthday;
    }

    public void setJobHunterBirthday(java.util.Date jobHunterBirthday) {
        this.jobHunterBirthday = jobHunterBirthday;
    }

    public String getJobHunterAvatarPath() {
        return this.jobHunterAvatarPath;
    }

    public void setJobHunterAvatarPath(String jobHunterAvatarPath) {
        this.jobHunterAvatarPath = jobHunterAvatarPath;
    }

    public String getJobHunterDepict() {
        return this.jobHunterDepict;
    }

    public void setJobHunterDepict(String jobHunterDepict) {
        this.jobHunterDepict = jobHunterDepict;
    }

    public String getJobHunterCurrentProvince() {
        return this.jobHunterCurrentProvince;
    }

    public void setJobHunterCurrentProvince(String jobHunterCurrentProvince) {
        this.jobHunterCurrentProvince = jobHunterCurrentProvince;
    }

    public String getJobHunterCurrentCity() {
        return this.jobHunterCurrentCity;
    }

    public void setJobHunterCurrentCity(String jobHunterCurrentCity) {
        this.jobHunterCurrentCity = jobHunterCurrentCity;
    }

    public String getJobHunterCurrentDistrict() {
        return this.jobHunterCurrentDistrict;
    }

    public void setJobHunterCurrentDistrict(String jobHunterCurrentDistrict) {
        this.jobHunterCurrentDistrict = jobHunterCurrentDistrict;
    }

    public String getJobHunterCurrentStatus() {
        return this.jobHunterCurrentStatus;
    }

    public void setJobHunterCurrentStatus(String jobHunterCurrentStatus) {
        this.jobHunterCurrentStatus = jobHunterCurrentStatus;
    }

    public String getJobHunterMajor() {
        return jobHunterMajor;
    }

    public void setJobHunterMajor(String jobHunterMajor) {
        this.jobHunterMajor = jobHunterMajor;
    }

    public String getJobHunterCollege() {
        return jobHunterCollege;
    }

    public void setJobHunterCollege(String jobHunterCollege) {
        this.jobHunterCollege = jobHunterCollege;
    }

    public String getJobHunterLastOccupation() {
        return jobHunterLastOccupation;
    }

    public void setJobHunterLastOccupation(String jobHunterLastOccupation) {
        this.jobHunterLastOccupation = jobHunterLastOccupation;
    }

    public String getJobHunterLastEmployer() {
        return jobHunterLastEmployer;
    }

    public void setJobHunterLastEmployer(String jobHunterLastEmployer) {
        this.jobHunterLastEmployer = jobHunterLastEmployer;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getImportFromOther() {
        return importFromOther;
    }

    public void setImportFromOther(Integer importFromOther) {
        this.importFromOther = importFromOther;
    }

    public String getJobHunterSpecialty() {
        return jobHunterSpecialty;
    }

    public void setJobHunterSpecialty(String jobHunterSpecialty) {
        this.jobHunterSpecialty = jobHunterSpecialty;
    }

    public String getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public String getProductPic1() {
        return productPic1;
    }

    public void setProductPic1(String productPic1) {
        this.productPic1 = productPic1;
    }

    public String getProductPic2() {
        return productPic2;
    }

    public void setProductPic2(String productPic2) {
        this.productPic2 = productPic2;
    }

    public String getProductPic3() {
        return productPic3;
    }

    public void setProductPic3(String productPic3) {
        this.productPic3 = productPic3;
    }

    public String getProductPic4() {
        return productPic4;
    }

    public void setProductPic4(String productPic4) {
        this.productPic4 = productPic4;
    }

    public String getProductPic5() {
        return productPic5;
    }

    public void setProductPic5(String productPic5) {
        this.productPic5 = productPic5;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPreachId() {
        return preachId;
    }

    public void setPreachId(String preachId) {
        this.preachId = preachId;
    }

    public String getWeixinNickname() {
        return weixinNickname;
    }

    public void setWeixinNickname(String weixinNickname) {
        this.weixinNickname = weixinNickname;
    }
}
