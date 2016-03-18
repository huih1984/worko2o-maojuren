package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_CREDIT的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-40-16 01:40:37 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_CREDIT")
public class JobHunterCredit {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * job_hunter_credit_id
     */
    @Id
    @Column(value = "job_hunter_credit_id")
    private Integer jobHunterCreditId;

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
     * have_join
     */
    @Column(value = "have_join")
    private Integer haveJoin;

    /**
     * have_accept
     */
    @Column(value = "have_accept")
    private Integer haveAccept;

    public Integer getJobHunterCreditId() {
        return this.jobHunterCreditId;
    }

    public void setJobHunterCreditId(Integer jobHunterCreditId) {
        this.jobHunterCreditId = jobHunterCreditId;
    }

    public Integer getJobHunterId() {
        return this.jobHunterId;
    }

    public void setJobHunterId(Integer jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getHaveJoin() {
        return this.haveJoin;
    }

    public void setHaveJoin(Integer haveJoin) {
        this.haveJoin = haveJoin;
    }

    public Integer getHaveAccept() {
        return this.haveAccept;
    }

    public void setHaveAccept(Integer haveAccept) {
        this.haveAccept = haveAccept;
    }

}
