package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_QUESTIONNAIRE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-51-12 07:51:50 <br>
 * @since V1.0<br>
 */
@Table("T_QUESTIONNAIRE")
public class Questionnaire {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * questionnaire_id
     */
    @Id
    @Column(value = "questionnaire_id")
    private Integer questionnaireId;

    /**
     * name
     */
    @Column(value = "name")
    private String name;

    /**
     * fit_type
     */
    @Column(value = "fit_type")
    private String fitType;

    /**
     * content
     */
    @Column(value = "content")
    private String content;

    /**
     * job_id
     */
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * time_limit
     */
    @Column(value = "time_limit")
    private String timeLimit;

    /**
     * update_time
     */
    @Column(value = "update_time")
    private java.util.Date updateTime;

    /**
     * is_open
     */
    @Column(value = "is_open")
    private Integer isOpen;

    public Integer getQuestionnaireId() {
        return this.questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFitType() {
        return this.fitType;
    }

    public void setFitType(String fitType) {
        this.fitType = fitType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

}
