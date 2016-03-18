package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * <Description> T_PREACH的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-45-09 03:45:15 <br>
 * @since V1.0<br>
 */
@Table("T_PREACH")
public class Preach {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * preach_id
     */
    @Id
    @Column(value = "preach_id")
    private Integer preachId;

    /**
     * preach_name
     */
    @Column(value = "preach_name")
    private String preachName;

    /**
     * city
     */
    @Column(value = "city")
    private String city;

    /**
     * college
     */
    @Column(value = "college")
    private String college;

    /**
     * campus
     */
    @Column(value = "campus")
    private String campus;

    /**
     * open_place
     */
    @Column(value = "open_place")
    private String openPlace;

    /**
     * open_date
     */
    @Column(value = "open_date")
    private java.util.Date openDate;

    /**
     * open_time
     */
    @Column(value = "open_time")
    private java.util.Date openTime;

    /**
     * instruction
     */
    @Column(value = "instruction")
    private String instruction;

    public Integer getPreachId() {
        return this.preachId;
    }

    public void setPreachId(Integer preachId) {
        this.preachId = preachId;
    }

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getOpenPlace() {
        return this.openPlace;
    }

    public void setOpenPlace(String openPlace) {
        this.openPlace = openPlace;
    }

    public java.util.Date getOpenDate() {
        return this.openDate;
    }

    public void setOpenDate(java.util.Date openDate) {
        this.openDate = openDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getPreachName() {
        return preachName;
    }

    public void setPreachName(String preachName) {
        this.preachName = preachName;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
