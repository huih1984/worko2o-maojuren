package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.*;
import com.delta.worko2o.util.DateUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-7-26
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
@IocBean(singleton = false)
public class JobInteractAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private JobInteractServiceApi jobInteractService;

    @Inject
    private JobHunterServiceApi jobHunterService;

    @Inject
    private EmployerServiceApi employerService;

    @Inject
    private EmailValidServiceApi emailValidService;

    @Inject
    private JobServiceApi jobService;


    @At("/page/demo/job/applyjob")
    public Map<String, ?> applyJobWAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                          @Param("employerId") Integer employerId) {

        log.debug("[applyJobWAction] begin");


        try {
            JobHunter jobHunter = jobHunterService.qryJobHunter(jobHunterId);
            Job job = jobService.qryJobByJobId(jobId);
            String jobName = job.getJobName();
            Employer employer = employerService.qryEmployerById(employerId);

            emailValidService.sendInform(employer, jobHunter, jobName);
            JobInteract jobInteract = jobInteractService.qryJobInteractById(jobHunterId, employerId, jobId);

            if (jobInteract != null) {
                jobInteract.setHunterStatus(2);
                jobInteract.setHunterStatusChanged(1);
                jobInteract.setHunterUpdateTime(DateUtil.getNowDate());
                jobInteractService.modJobInteract(jobInteract, true);
            } else {
                jobInteract = new JobInteract();
                jobInteract.setJobId(jobId);
                jobInteract.setEmployerId(employerId);
                jobInteract.setJobHunterId(jobHunterId);
                jobInteract.setHunterStatusChanged(1);
                jobInteract.setHunterUpdateTime(DateUtil.getNowDate());
                jobInteract.setHunterStatus(2);
                jobInteractService.addJobInteract(jobInteract);
            }
//            }

        } catch (Exception e) {
            log.debug("applyJobWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[applyJobWAction] end");

        return retMap;
    }

    class JobInteractionInfo {
        private Job job;
        private JobInteract jobInteract;

        Job getJob() {
            return job;
        }

        void setJob(Job job) {
            this.job = job;
        }

        JobInteract getJobInteract() {
            return jobInteract;
        }

        void setJobInteract(JobInteract jobInteract) {
            this.jobInteract = jobInteract;
        }
    }


    private String getPaginationInfo(int totalCount, Integer pageIndex) {
        if (0 == totalCount) {
            StringBuffer sb = new StringBuffer();
            sb.append("<li class=\"disabled\"><a href=\"#\">«</a>");
            sb.append("</li>");
            sb.append("<li class=\"active\"><a href=\"#\">1 <span class=\"sr-only\">(current)</span></a></li>");

            return sb.toString();
        }

        int pageSize = (totalCount % DEFAULT_PAGE_SIZE == 0) ? totalCount / DEFAULT_PAGE_SIZE : totalCount / DEFAULT_PAGE_SIZE + 1;

        StringBuffer sb = new StringBuffer();

        if (pageSize < 14) {
            for (int i = 0; i < pageSize; i++) {
                if (i == pageIndex - 1) {
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"\">" + (i + 1) + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"\">" + (i + 1) + "</a></li>");
                }
            }
        } else {
            if (pageIndex > 14) {
                int preFirst = ((int) ((pageIndex) / 14) - 1) * 14 + 1;
                sb.append("<li><a href=\"#\" onclick=\"queryJob(" + preFirst + ")\">«</a>");
            }
            int fIndex = ((int) ((pageIndex) / 14)) * 14 + 1;
            int lIndex = Math.min((fIndex + 14), totalCount);
            for (int i = fIndex; i < lIndex; i++) {
                if (i == pageIndex) {
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryJob(" + i + ")\">" + i + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryJob(" + i + ")\">" + i + "</a></li>");
                }
            }
            if ((fIndex + 13) < totalCount) {
                sb.append("<li><a href=\"#\" onclick=\"queryJob(" + (fIndex + 14) + ")\">»</a></li>");
            }
        }
        return sb.toString();
    }

    @At("/page/demo/job/hunterapplyjobs")
    public Map<String, ?> hunterApplyJobRAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[hunterApplyJobRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
//            pageSize = DEFAULT_PAGE_SIZE;
            pageSize = 100;
        }


        try {
            int totalCount = jobService.countHunterJobsByPager(jobHunterId, jobId);

            setRetInfo(RetConstants.PAGINATION_INFO, getPaginationInfo(totalCount, pageIndex));

            List<JobInteractionInfo> jobInteractionInfos = new ArrayList<JobInteractionInfo>();
            List<JobInteract> jobInteracts = jobInteractService.qryJobInteractByPager(jobHunterId, jobId, pageIndex, pageSize);

            for (JobInteract jobInteract : jobInteracts) {
                JobInteractionInfo jobInteractionInfo = new JobInteractionInfo();
                jobInteractionInfo.setJobInteract(jobInteract);
                jobInteractionInfo.setJob(jobService.qryJobByJobId(jobInteract.getJobId()));
                if (jobInteractionInfo.getJob() != null && jobInteractionInfo.getJobInteract() != null) {
                    jobInteractionInfos.add(jobInteractionInfo);
                }
            }

            if (!Lang.isEmpty(jobInteractionInfos)) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobInteractionInfos);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found apply job");
            }

        } catch (Exception e) {
            log.debug("hunterApplyJobRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterApplyJobRAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/employer/regress")
    public Map<String, ?> employerInteractRegressWAction(@Param("employerId") Integer employerId, @Param("jobId") Integer jobId) {

        log.debug("[employerInteractRegressWAction] begin");

        try {
            jobInteractService.modHunterInteractByEmployerId(employerId, jobId);
        } catch (Exception e) {
            log.debug("employerInteractRegressWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerInteractRegressWAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/hunter/regress")
    public Map<String, ?> hunterInteractRegressWAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId) {

        log.debug("[huntererInteractRegressWAction] begin");

        try {
            jobInteractService.modEmployerInteractByHunterId(jobHunterId, jobId);
        } catch (Exception e) {
            log.debug("huntererInteractRegressWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[huntererInteractRegressWAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/employer/add")
    public Map<String, ?> employerInteractWAction(@Param("jobInteractId") Integer jobInteractId, @Param("employerId") Integer employerId,
                                                  @Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                  @Param("hunterStatus") Integer hunterStatus, @Param("employerStatus") Integer employerStatus,
                                                  @Param("hunterStatusChanged") Integer hunterStatusChanged, @Param("employerStatusChanged") Integer employerStatusChanged
    ) {

        log.debug("[employerInteractWAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setJobInteractId(jobInteractId);
            jobInteract.setEmployerId(employerId);
            jobInteract.setJobHunterId(jobHunterId);
            jobInteract.setJobId(jobId);
            jobInteract.setHunterStatus(hunterStatus);
            jobInteract.setEmployerStatus(employerStatus);
            jobInteract.setHunterStatusChanged(hunterStatusChanged);
            jobInteract.setEmployerStatusChanged(employerStatusChanged);
            jobInteract.setEmployerUpdateTime(DateUtil.getNowDate());
            jobInteractService.addJobInteract(jobInteract);
        } catch (Exception e) {
            log.debug("employerInteractWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerInteractWAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/employer/mod")
    public Map<String, ?> employerInteractMAction(@Param("jobInteractId") Integer jobInteractId, @Param("employerId") Integer employerId,
                                                  @Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                  @Param("hunterStatus") Integer hunterStatus, @Param("employerStatus") Integer employerStatus,
                                                  @Param("hunterStatusChanged") Integer hunterStatusChanged,
                                                  @Param("employerStatusChanged") Integer employerStatusChanged
    ) {

        log.debug("[employerInteractMAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setJobInteractId(jobInteractId);
            jobInteract.setEmployerId(employerId);
            jobInteract.setJobHunterId(jobHunterId);
            jobInteract.setJobId(jobId);
            jobInteract.setHunterStatus(hunterStatus);
            jobInteract.setEmployerStatus(employerStatus);
            jobInteract.setHunterStatusChanged(hunterStatusChanged);
            jobInteract.setEmployerStatusChanged(employerStatusChanged);
            jobInteract.setEmployerUpdateTime(DateUtil.getNowDate());
            jobInteractService.modJobInteract(jobInteract, true);
        } catch (Exception e) {
            log.debug("employerInteractMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerInteractMAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/hunter/mod")
    public Map<String, ?> jobHunterInteractMAction(@Param("jobInteractId") Integer jobInteractId, @Param("employerId") Integer employerId,
                                                   @Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                   @Param("hunterStatus") Integer hunterStatus, @Param("employerStatus") Integer employerStatus,
                                                   @Param("hunterStatusChanged") Integer hunterStatusChanged, @Param("employerStatusChanged") Integer employerStatusChanged,
                                                   @Param("interviewFeedback") Integer interviewFeedback
    ) {

        log.debug("[jobHunterInteractMAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setJobInteractId(jobInteractId);
            jobInteract.setEmployerId(employerId);
            jobInteract.setJobHunterId(jobHunterId);
            jobInteract.setJobId(jobId);
            jobInteract.setHunterStatus(hunterStatus);
            jobInteract.setEmployerStatus(employerStatus);
            jobInteract.setHunterStatusChanged(hunterStatusChanged);
            jobInteract.setEmployerStatusChanged(employerStatusChanged);
            jobInteract.setInterviewFeedback(interviewFeedback);
            jobInteract.setHunterUpdateTime(DateUtil.getNowDate());
            jobInteractService.modJobInteract(jobInteract, true);
        } catch (Exception e) {
            log.debug("jobHunterInteractMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterInteractMAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/hunter/modbyhunterkey")
    public Map<String, ?> jobHunterInteractMByHunterAction(@Param("employerId") Integer employerId,
                                                           @Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                           @Param("hunterStatus") Integer hunterStatus, @Param("employerStatus") Integer employerStatus,
                                                           @Param("hunterStatusChanged") Integer hunterStatusChanged, @Param("employerStatusChanged") Integer employerStatusChanged
    ) {

        log.debug("[jobHunterInteractMAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setEmployerId(employerId);
            jobInteract.setJobHunterId(jobHunterId);
            jobInteract.setJobId(jobId);
            jobInteract.setHunterStatus(hunterStatus);
            jobInteract.setEmployerStatus(employerStatus);
            jobInteract.setHunterStatusChanged(hunterStatusChanged);
            jobInteract.setEmployerStatusChanged(employerStatusChanged);
            jobInteract.setHunterUpdateTime(DateUtil.getNowDate());
            jobInteractService.modJobInteractByHunterKey(jobInteract, true);
        } catch (Exception e) {
            log.debug("jobHunterInteractMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterInteractMAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/statichr")
    public Map<String, ?> jobStaticHRAction(@Param("employerId") Integer employerId) {

        log.debug("[jobStaticHRAction] begin");

        try {
            List<StaticHr> staticHrs = jobInteractService.qryStaticHr(employerId);
            setRetInfo("static_hr", staticHrs);
        } catch (Exception e) {
            log.debug("jobStaticHRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobStaticHRAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/addenvelope")
    public Map<String, ?> addEnvelopeWAction(@Param("employerId") Integer employerId, @Param("jobHunterId") Integer jobHunterId,
                                             @Param("author") Integer author, @Param("content") String content,
                                             @Param("unread") Integer unread) {

        log.debug("[addEnvelopeAction] begin");

        try {
            Envelope envelope = new Envelope();
            envelope.setAuthor(author);
            envelope.setEmployerId(employerId);
            envelope.setJobHunterId(jobHunterId);
            envelope.setContent(content);
            envelope.setUnread(unread);
            envelope.setPublishTime(DateUtil.getNowDate());
            jobInteractService.addEnvelope(envelope);
        } catch (Exception e) {
            log.debug("addEnvelopeAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[addEnvelopeAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/getbyid")
    /*获取某对求职者和个人之间的会话内容列表*/
    public Map<String, ?> envelopeRBYHunterAction(@Param("jobHunterId") Integer jobHunterId,
                                                  @Param("employerId") Integer employerId) {

        log.debug("[envelopeRBYHunterAction] begin");

        try {
            List<Envelope> envelopes = jobInteractService.qryEnvelopeById(employerId, jobHunterId);
            setRetInfo("envelope", envelopes);
        } catch (Exception e) {
            log.debug("envelopeRBYHunterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[envelopeRBYHunterAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/getunreadnumbyhunterid")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> unReadEnvelopeNumRBYHunterAction(@Param("jobHunterId") Integer jobHunterId, HttpServletRequest request) {

        log.debug("[unReadEnvelopeNumRBYHunterAction] begin");

        try {
            int num = jobInteractService.qryUnreadEnvelopeNumByJobHunterId(jobHunterId);
            setRetInfo("envelope_unread_num", num);
            request.getSession().setAttribute("envelopeGot", 1);
        } catch (Exception e) {
            log.debug("unReadEnvelopeNumRBYHunterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[unReadEnvelopeNumRBYHunterAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/modenvelope")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> modEnvelopeAction(@Param("envelopeId") Integer envelopeId, @Param("unread") Integer unread, HttpServletRequest request) {

        log.debug("[modEnvelopeAction] begin");

        try {
            Envelope envelope = new Envelope();
            envelope.setEnvelopeId(envelopeId);
            envelope.setUnread(unread);
            jobInteractService.modEnvelope(envelope, true);
        } catch (Exception e) {
            log.debug("modEnvelopeAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[modEnvelopeAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/removeenvelope")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> removeEnvelopeAction(@Param("envelopeId") Integer envelopeId, HttpServletRequest request) {

        log.debug("[removeEnvelopeAction] begin");

        try {
            Envelope envelope = new Envelope();
            envelope.setEnvelopeId(envelopeId);
            jobInteractService.removeEnvelope(envelope);
        } catch (Exception e) {
            log.debug("removeEnvelopeAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[removeEnvelopeAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/getunreadbyhunterid")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> unReadEnvelopeRBYHunterAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[unReadEnvelopeNumRBYHunterAction] begin");

        try {
            List<Envelope> envelopes = jobInteractService.qryUnreadEnvelopeByJobHunterId(jobHunterId);
            setRetInfo("envelope_unread", envelopes);
        } catch (Exception e) {
            log.debug("unReadEnvelopeNumRBYHunterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[unReadEnvelopeNumRBYHunterAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/getunreadnumbyemployerid")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> unReadEnvelopeNumRBYEmployerAction(@Param("employerId") Integer employerId, HttpServletRequest request) {

        log.debug("[unReadEnvelopeNumRBYHunterAction] begin");

        try {
            int num = jobInteractService.qryUnreadEnvelopeNumByEmployerId(employerId);
            setRetInfo("envelope_unread_num", num);
            request.getSession().setAttribute("envelopeGot", 1);
        } catch (Exception e) {
            log.debug("unReadEnvelopeNumRBYHunterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[unReadEnvelopeNumRBYHunterAction] end");

        return retMap;
    }

    @At("/page/demo/job/envelope/getunreademployerid")
    /*获取求职者的未读私信个数*/
    public Map<String, ?> unReadEnvelopeRBYEmployerAction(@Param("employerId") Integer employerId) {

        log.debug("[unReadEnvelopeNumRBYHunterAction] begin");

        try {
            List<Envelope> envelopes = jobInteractService.qryUnreadEnvelopeByEmployerId(employerId);
            setRetInfo("envelope_unread_num", envelopes);
        } catch (Exception e) {
            log.debug("unReadEnvelopeNumRBYHunterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[unReadEnvelopeNumRBYHunterAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/employer/myinteractjobs")
    public Map<String, ?> employerMyInteractJobsRAction(@Param("employerId") Integer employerId, @Param("jobId") Integer jobId,
                                                        HttpServletRequest request) {

        log.debug("[interactEmployerRAction] begin");

        try {
            List<JobInteract> jobInteracts = jobInteractService.qryJobInteractByEmployerId(employerId, jobId);

            if (!Lang.isEmpty(jobInteracts)) {
                setRetInfo(RetConstants.KEY_JOB_INTERACT_LIST, jobInteracts);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job_interact list on employerId");
            }
            request.getSession().setAttribute("interactGot", 1);
        } catch (Exception e) {
            log.debug("interactEmployerRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[interactEmployerRAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/employer/elpiactjobs")
    public Map<String, ?> envelopeInteractJobsEmployerRAction(@Param("employerId") Integer employerId,
                                                              HttpServletRequest request) {

        log.debug("[envelopeInteractJobsEmployerRAction] begin");

        try {
            List<JobInteract> jobInteracts = jobInteractService.qryEnvelopeJobInteractEmployer(employerId);

            if (!Lang.isEmpty(jobInteracts)) {
                setRetInfo(RetConstants.KEY_JOB_INTERACT_LIST, jobInteracts);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job_interact list on employerId");
            }
            request.getSession().setAttribute("interactGot", 1);
        } catch (Exception e) {
            log.debug("envelopeInteractJobsEmployerRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[envelopeInteractJobsEmployerRAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/hunter/myinteractjobs")
    public Map<String, ?> hunterMyInteractJobsRAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                      HttpServletRequest request) {

        log.debug("[interactHunterRAction] begin");

        try {
            List<JobInteract> jobInteracts = jobInteractService.qryJobInteractAll(jobHunterId, jobId);

            if (!Lang.isEmpty(jobInteracts)) {
                setRetInfo(RetConstants.KEY_JOB_INTERACT_LIST, jobInteracts);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job_interact list on employerId");
            }
            request.getSession().setAttribute("interactGot", 1);
        } catch (Exception e) {
            log.debug("interactHunterRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[interactHunterRAction] end");

        return retMap;
    }

    @At("/page/demo/job/interact/hunter/elpiactjobs")
    public Map<String, ?> envelopeInteractJobsRAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                                      HttpServletRequest request) {

        log.debug("[envelopeInteractJobsRAction] begin");

        try {
            List<JobInteract> jobInteracts = jobInteractService.qryEnvelopeJobInteractHunter(jobHunterId);

            if (!Lang.isEmpty(jobInteracts)) {
                setRetInfo(RetConstants.KEY_JOB_INTERACT_LIST, jobInteracts);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job_interact list on employerId");
            }
            request.getSession().setAttribute("interactGot", 1);
        } catch (Exception e) {
            log.debug("envelopeInteractJobsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[envelopeInteractJobsRAction] end");

        return retMap;
    }


    @At("/page/demo/job/jobassessw")
    public Map<String, ?> assessJobsWAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                            @Param("joinWrittentest") Integer joinWrittentest, @Param("interviewerNice") Integer interviewerNice,
                                            @Param("descriptionMatch") Integer descriptionMatch,
                                            HttpServletRequest request) {

        log.debug("[assessJobsRAction] begin");

        try {
            JobAssess jobAssess = new JobAssess();
            jobAssess.setJobId(jobId);
            jobAssess.setJobHunterId(jobHunterId);
            jobAssess.setJoinWrittentest(joinWrittentest);
            jobAssess.setInterviewerNice(interviewerNice);
            jobAssess.setDescriptionMatch(descriptionMatch);
            jobInteractService.addJobAssess(jobAssess);
        } catch (Exception e) {
            log.debug("assessJobsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[assessJobsRAction] end");

        return retMap;
    }

    @At("/page/demo/job/jobassessr")
    public Map<String, ?> assessJobsRAction(@Param("jobHunterId") Integer jobHunterId, @Param("jobId") Integer jobId,
                                            HttpServletRequest request) {

        log.debug("[assessJobsRAction] begin");

        try {
            List<JobAssess> jobAssesses = jobInteractService.qryJobAssess(jobId);
            if (!Lang.isEmpty(jobAssesses)) {
                setRetInfo("job_assess", jobAssesses);
            }
        } catch (Exception e) {
            log.debug("assessJobsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[assessJobsRAction] end");

        return retMap;
    }

}
