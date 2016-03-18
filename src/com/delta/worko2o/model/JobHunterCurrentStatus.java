package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_CURRENT_STATUS的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_CURRENT_STATUS")
public class JobHunterCurrentStatus {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_current_status_id
     */
    @Id
    @Column(value = "job_hunter_current_status_id")
    private Integer jobHunterCurrentStatusId;

    /**
     * status
     */
    @Column(value = "status")
    private String status;

    public Integer getJobHunterCurrentStatusId() {
        return this.jobHunterCurrentStatusId;
    }

    public void setJobHunterCurrentStatusId(Integer jobHunterCurrentStatusId) {
        this.jobHunterCurrentStatusId = jobHunterCurrentStatusId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
