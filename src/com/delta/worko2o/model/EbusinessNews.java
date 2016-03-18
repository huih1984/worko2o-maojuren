package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_EBUSINESS_NEWS的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_EBUSINESS_NEWS")
public class EbusinessNews {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * news_id
     */
    @Id
    @Column(value = "news_id")
    private Integer newsId;

    /**
     * news_title
     */
    @Column(value = "news_title")
    private String newsTitle;

    /**
     * news_summary
     */
    @Column(value = "news_summary")
    private String newsSummary;

    /**
     * news_tag
     */
    @Column(value = "news_tag")
    private String newsTag;

    /**
     * icon_path
     */
    @Column(value = "icon_path")
    private String iconPath;

    /**
     * news_publish_time
     */
    @Column(value = "news_publish_time")
    private java.util.Date newsPublishTime;

    /**
     * news_type
     */
    @Column(value = "news_type")
    private Integer newsType;

    /**
     * news_content
     */
    @Column(value = "news_content")
    private String newsContent;

    /**
     * read_times
     */
    @Default("0")
    @Column(value = "read_times")
    private Integer readTimes;

    /**
     * source_from
     */
    @Column(value = "source_from")
    private String sourceFrom;

    /**
     * have_remainder
     */
    @Column(value = "have_remainder")
    private Integer haveRemainder;

    public Integer getNewsId() {
        return this.newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return this.newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSummary() {
        return this.newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public String getNewsTag() {
        return this.newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public java.util.Date getNewsPublishTime() {
        return this.newsPublishTime;
    }

    public void setNewsPublishTime(java.util.Date newsPublishTime) {
        this.newsPublishTime = newsPublishTime;
    }

    public Integer getNewsType() {
        return this.newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getNewsContent() {
        return this.newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Integer getReadTimes() {
        return this.readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public String getSourceFrom() {
        return this.sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public Integer getHaveRemainder() {
        return this.haveRemainder;
    }

    public void setHaveRemainder(Integer haveRemainder) {
        this.haveRemainder = haveRemainder;
    }

}
