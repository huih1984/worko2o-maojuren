package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_STATIC_HR的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-08-03 05:08:50 <br>
 * @since V1.0<br>
 */
@Table("T_STATIC_HR")
public class StaticHr {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * static_hr_id
     */
    @Id
    @Column(value = "static_hr_id")
    private Integer staticHrId;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private String employerId;

    /**
     * deliver_time
     */
    @Column(value = "deliver_time")
    private java.util.Date deliverTime;

    /**
     * watch_time
     */
    @Column(value = "watch_time")
    private java.util.Date watchTime;

    public Integer getStaticHrId() {
        return this.staticHrId;
    }

    public void setStaticHrId(Integer staticHrId) {
        this.staticHrId = staticHrId;
    }

    public String getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public java.util.Date getDeliverTime() {
        return this.deliverTime;
    }

    public void setDeliverTime(java.util.Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public java.util.Date getWatchTime() {
        return this.watchTime;
    }

    public void setWatchTime(java.util.Date watchTime) {
        this.watchTime = watchTime;
    }

}
