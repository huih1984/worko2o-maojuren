package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_EBUSINESS_NEWS_REMAINDER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_EBUSINESS_NEWS_REMAINDER")
public class EbusinessNewsRemainder {

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
     * news_content
     */
    @Column(value = "news_content")
    private String newsContent;

    public Integer getNewsId() {
        return this.newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsContent() {
        return this.newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

}
