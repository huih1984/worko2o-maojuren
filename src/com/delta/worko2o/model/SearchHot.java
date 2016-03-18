package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_SEARCH_HOT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_SEARCH_HOT")
public class SearchHot {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * search_hot_id
     */
    @Id
    @Column(value = "search_hot_id")
    private Integer searchHotId;

    /**
     * hot_term
     */
    @Column(value = "hot_term")
    private String hotTerm;

    /**
     * search_times
     */
    @Column(value = "search_times")
    private Integer searchTimes;

    /**
     * main_page
     */
    @Column(value = "main_page")
    private Integer mainPage;

    public Integer getSearchHotId() {
        return this.searchHotId;
    }

    public void setSearchHotId(Integer searchHotId) {
        this.searchHotId = searchHotId;
    }

    public String getHotTerm() {
        return this.hotTerm;
    }

    public void setHotTerm(String hotTerm) {
        this.hotTerm = hotTerm;
    }

    public Integer getSearchTimes() {
        return this.searchTimes;
    }

    public void setSearchTimes(Integer searchTimes) {
        this.searchTimes = searchTimes;
    }

    public Integer getMainPage() {
        return this.mainPage;
    }

    public void setMainPage(Integer mainPage) {
        this.mainPage = mainPage;
    }

}
