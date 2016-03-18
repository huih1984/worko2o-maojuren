package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * <Description> T_ENVELOPE的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-09-23 09:09:03 <br>
 * @since V1.0<br>
 */
@Table("T_ENVELOPE")
public class Envelope {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * envelope_id
     */
    @Id
    @Column(value = "envelope_id")
    private Integer envelopeId;

    /**
     * job_hunter_id
     */
    @Column(value = "job_hunter_id")
    private Integer jobHunterId;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private Integer employerId;

    /**
     * author
     */
    @Column(value = "author")
    private Integer author;

    /**
     * content
     */
    @Column(value = "content")
    private String content;

    /**
     * unread
     */
    @Default("0")
    @Column(value = "unread")
    private Integer unread;


    /**
     * publishTime
     */
    @Column(value = "publish_time")
    private Date publishTime;

    public Integer getEnvelopeId() {
        return this.envelopeId;
    }

    public void setEnvelopeId(Integer envelopeId) {
        this.envelopeId = envelopeId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getAuthor() {
        return this.author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUnread() {
        return this.unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
