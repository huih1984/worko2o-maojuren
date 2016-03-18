package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_QUESTIONNAIRE_ANSWER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-51-12 07:51:34 <br>
 * @since V1.0<br>
 */
@Table("T_QUESTIONNAIRE_ANSWER")
public class QuestionnaireAnswer {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * questionnaire_answer_id
     */
    @Id
    @Column(value = "questionnaire_answer_id")
    private Integer questionnaireAnswerId;

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
     * questionnaire_id
     */
    @Column(value = "questionnaire_id")
    private Integer questionnaireId;

    /**
     * answer
     */
    @Column(value = "answer")
    private String answer;

    /**
     * check
     */
    @Column(value = "checkans")
    private String checkans;

    public Integer getQuestionnaireAnswerId() {
        return this.questionnaireAnswerId;
    }

    public void setQuestionnaireAnswerId(Integer questionnaireAnswerId) {
        this.questionnaireAnswerId = questionnaireAnswerId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getQuestionnaireId() {
        return this.questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCheckans() {
        return this.checkans;
    }

    public void setCheckans(String checkans) {
        this.checkans = checkans;
    }

}
