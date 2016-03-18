package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * <Description> T_EMPLOYER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-19-15 10:19:49 <br>
 * @since V1.0<br>
 */
@Table("T_EMPLOYER")
public class Employer implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * employer_id
     */
    @Id
    @Column(value = "employer_id")
    private Integer employerId;

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
     * employer_name
     */
    @Column(value = "employer_name")
    private String employerName;

    /**
     * employer_type
     */
    @Column(value = "employer_type")
    private String employerType;

    /**
     * employer_scale
     */
    @Column(value = "employer_scale")
    private String employerScale;

    /**
     * sale_type
     */
    @Column(value = "sale_type")
    private String saleType;

    /**
     * hr_email
     */
    @Column(value = "hr_email")
    private String hrEmail;

    /**
     * password
     */
    @Column(value = "password")
    private String password;

    /**
     * employer_desc
     */
    @Column(value = "employer_desc")
    private String employerDesc;

    /**
     * address
     */
    @Column(value = "address")
    private String address;

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
     * tel
     */
    @Column(value = "tel")
    private String tel;

    /**
     * logo_path
     */
    @Column(value = "logo_path")
    private String logoPath;

    /**
     * certificate_path
     */
    @Column(value = "certificate_path")
    private String certificatePath;

    /**
     * identity_card_path
     */
    @Column(value = "identity_card_path")
    private String identityCardPath;

    /**
     * website
     */
    @Column(value = "website")
    private String website;

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
     * product1_pic
     */
    @Column(value = "product1_pic")
    private String product1Pic;

    /**
     * product2_pic
     */
    @Column(value = "product2_pic")
    private String product2Pic;

    /**
     * product3_pic
     */
    @Column(value = "product3_pic")
    private String product3Pic;

    /**
     * product4_pic
     */
    @Column(value = "product4_pic")
    private String product4Pic;

    /**
     * product5_pic
     */
    @Column(value = "product5_pic")
    private String product5Pic;

    /**
     * resume_download_limit
     */
    @Default("200")
    @Column(value = "resume_download_limit")
    private Integer resumeDownloadLimit;

    /**
     * resume_have_download_num
     */
    @Default("0")
    @Column(value = "resume_have_download_num")
    private Integer resumeHaveDownloadNum;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    /**
     * status
     */
    @Default("0")
    @Column(value = "status")
    private Integer status;

    /**
     * censor_status
     */
    @Default("0")
    @Column(value = "censor_status")
    private Integer censorStatus;

    /**
     * lng
     */
    @Column(value = "lng")
    private String lng;

    /**
     * lat
     */
    @Column(value = "lat")
    private String lat;

    /**
     * feedback_score
     */
    @Column(value = "feedback_score")
    private Integer feedbackScore;

    /**
     * preach_id
     */
    @Column(value = "preach_id")
    private Integer preachId;

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
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

    public String getEmployerName() {
        return this.employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerType() {
        return this.employerType;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public String getEmployerScale() {
        return this.employerScale;
    }

    public void setEmployerScale(String employerScale) {
        this.employerScale = employerScale;
    }

    public String getSaleType() {
        return this.saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getHrEmail() {
        return this.hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployerDesc() {
        return this.employerDesc;
    }

    public void setEmployerDesc(String employerDesc) {
        this.employerDesc = employerDesc;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getCertificatePath() {
        return this.certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getIdentityCardPath() {
        return this.identityCardPath;
    }

    public void setIdentityCardPath(String identityCardPath) {
        this.identityCardPath = identityCardPath;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getProduct1Pic() {
        return this.product1Pic;
    }

    public void setProduct1Pic(String product1Pic) {
        this.product1Pic = product1Pic;
    }

    public String getProduct2Pic() {
        return this.product2Pic;
    }

    public void setProduct2Pic(String product2Pic) {
        this.product2Pic = product2Pic;
    }

    public String getProduct3Pic() {
        return this.product3Pic;
    }

    public void setProduct3Pic(String product3Pic) {
        this.product3Pic = product3Pic;
    }

    public String getProduct4Pic() {
        return this.product4Pic;
    }

    public void setProduct4Pic(String product4Pic) {
        this.product4Pic = product4Pic;
    }

    public String getProduct5Pic() {
        return this.product5Pic;
    }

    public void setProduct5Pic(String product5Pic) {
        this.product5Pic = product5Pic;
    }

    public Integer getResumeDownloadLimit() {
        return this.resumeDownloadLimit;
    }

    public void setResumeDownloadLimit(Integer resumeDownloadLimit) {
        this.resumeDownloadLimit = resumeDownloadLimit;
    }

    public Integer getResumeHaveDownloadNum() {
        return this.resumeHaveDownloadNum;
    }

    public void setResumeHaveDownloadNum(Integer resumeHaveDownloadNum) {
        this.resumeHaveDownloadNum = resumeHaveDownloadNum;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCensorStatus() {
        return this.censorStatus;
    }

    public void setCensorStatus(Integer censorStatus) {
        this.censorStatus = censorStatus;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getFeedbackScore() {
        return this.feedbackScore;
    }

    public void setFeedbackScore(Integer feedbackScore) {
        this.feedbackScore = feedbackScore;
    }

    public Integer getPreachId() {
        return preachId;
    }

    public void setPreachId(Integer preachId) {
        this.preachId = preachId;
    }
}
