package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> T_JOB_HUNTER_RECEIVED_INVITATION的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_JOB_HUNTER_RECEIVED_INVITATION")
public class JobHunterReceivedInvitation {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ob_hunter_received_invitation_id
     */
    @Id
    @Column(value = "ob_hunter_received_invitation_id")
    private Integer obHunterReceivedInvitationId;

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
     * job_id
     */
    @Column(value = "job_id")
    private Integer jobId;

    /**
     * job_hunter_have_read
     */
    @Column(value = "job_hunter_have_read")
    private Integer jobHunterHaveRead;

    /**
     * job_hunter_delived
     */
    @Column(value = "job_hunter_delived")
    private Integer jobHunterDelived;

    public Integer getObHunterReceivedInvitationId() {
        return this.obHunterReceivedInvitationId;
    }

    public void setObHunterReceivedInvitationId(Integer obHunterReceivedInvitationId) {
        this.obHunterReceivedInvitationId = obHunterReceivedInvitationId;
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

    public Integer getJobId() {
        return this.jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getJobHunterHaveRead() {
        return this.jobHunterHaveRead;
    }

    public void setJobHunterHaveRead(Integer jobHunterHaveRead) {
        this.jobHunterHaveRead = jobHunterHaveRead;
    }

    public Integer getJobHunterDelived() {
        return this.jobHunterDelived;
    }

    public void setJobHunterDelived(Integer jobHunterDelived) {
        this.jobHunterDelived = jobHunterDelived;
    }

}
