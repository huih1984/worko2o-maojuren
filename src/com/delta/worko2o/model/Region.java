package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_REGION的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_REGION")
public class Region {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * region_id
     */
    @Id
    @Column(value = "region_id")
    private Integer regionId;

    /**
     * region_code
     */
    @Column(value = "region_code")
    private Integer regionCode;

    /**
     * region_name
     */
    @Column(value = "region_name")
    private String regionName;

    /**
     * main_page
     */
    @Column(value = "main_page")
    private Integer mainPage;

    public Integer getRegionId() {
        return this.regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getMainPage() {
        return this.mainPage;
    }

    public void setMainPage(Integer mainPage) {
        this.mainPage = mainPage;
    }

}
