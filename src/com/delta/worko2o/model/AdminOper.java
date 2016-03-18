package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_ADMIN_OPER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_ADMIN_OPER")
public class AdminOper {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * admin_oper_id
     */
    @Id
    @Column(value = "admin_oper_id")
    private Integer adminOperId;

    /**
     * admin_username
     */
    @Column(value = "admin_username")
    private String adminUsername;

    /**
     * employer_id
     */
    @Column(value = "employer_id")
    private Integer employerId;

    /**
     * from_status
     */
    @Column(value = "from_status")
    private Integer fromStatus;

    /**
     * to_status
     */
    @Column(value = "to_status")
    private Integer toStatus;

    /**
     * oper_time
     */
    @Column(value = "oper_time")
    private java.util.Date operTime;

    public Integer getAdminOperId() {
        return this.adminOperId;
    }

    public void setAdminOperId(Integer adminOperId) {
        this.adminOperId = adminOperId;
    }

    public String getAdminUsername() {
        return this.adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public Integer getEmployerId() {
        return this.employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Integer getFromStatus() {
        return this.fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return this.toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public java.util.Date getOperTime() {
        return this.operTime;
    }

    public void setOperTime(java.util.Date operTime) {
        this.operTime = operTime;
    }

}
