package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.JobHunterServiceApi;
import com.delta.worko2o.service.api.JobServiceApi;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.SessionData;
import com.delta.worko2o.util.StringUtil;
import com.delta.worko2o.util.UserInfoListener;
import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <Description> <br>
 * 求职者相关处理类
 *
 * @author XXX<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月18日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class JobHunterAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private JobHunterServiceApi jobHunterService;

    @Inject
    private JobServiceApi jobService;

    /**
     * Description: <br>
     * 企业注册
     */
    @At("/page/demo/jobHunter/valid")
    public Map<String, Object> jobHunterValidAction(@Param("jobHunterEmail") String jobHunterEmail) {

        log.debug("[jobHunterValidAction] begin");
        try {

            JobHunter jobHunter = jobHunterService.qryJobHunterByEmail(jobHunterEmail);
            if (jobHunter != null) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, jobHunter);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found this account!");
            }

        } catch (Exception e) {
            log.debug("jobHunterValidAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerValidAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/register")
    public Map<String, Object> jobHunterRegisterAction(@Param("sinaId") String sina_id,
                                                       @Param("tencentId") String tencent_id, @Param("jobHunterEmail") String job_hunter_email,
                                                       @Param("jobHunterPassword") String job_hunter_password, @Param("jobHunterName") String job_hunter_name,
                                                       @Param("jobHunterStartWorkYear") String job_hunter_start_work_year, @Param("jobHunterBachelorDegree") String job_hunter_bachelor_degree,
                                                       @Param("jobHunterCurrentStatus") String job_hunter_current_status, HttpServletRequest httpServletRequest) {

        log.debug("[jobHunterRegisterAction] begin");

        try {
            JobHunter jobHunter = null;
            jobHunter = (JobHunter) httpServletRequest.getSession().getAttribute("jobHunter");
            if (jobHunter != null) {
                jobHunter.setJobHunterStartWorkYear(job_hunter_start_work_year);
                jobHunter.setJobHunterBachelorDegree(job_hunter_bachelor_degree);
                jobHunter.setJobHunterCurrentStatus(job_hunter_current_status);
                jobHunter.setUpdateTime(DateUtil.getNowDate());
            } else {
                jobHunter = new JobHunter();
                jobHunter.setSinaId(sina_id);
                jobHunter.setTencentId(tencent_id);
                jobHunter.setJobHunterName(job_hunter_name);
                jobHunter.setJobHunterPassword(job_hunter_password);
                jobHunter.setJobHunterEmail(job_hunter_email);
                jobHunter.setJobHunterStartWorkYear(job_hunter_start_work_year);
                jobHunter.setJobHunterBachelorDegree(job_hunter_bachelor_degree);
                jobHunter.setJobHunterCurrentStatus(job_hunter_current_status);
                jobHunter.setUpdateTime(DateUtil.getNowDate());
            }

            int jobHunterId = jobHunterService.addJobHunter(jobHunter);
            setRetInfo(RetConstants.KEY_JOB_HUNTER_ID, jobHunterId);

        } catch (Exception e) {
            log.debug("jobHunterRegisterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterRegisterAction] end");

        return retMap;
    }

    @At("/page/demo/hunter/thirdaccount")
    public Map<String, Object> hunterThirdAccountWAction(
            @Param("jobHunterId") Integer jobHunterId,
            @Param("sinaId") String sinaId,
            @Param("weixinId") String weixinId,
            @Param("tencentId") String tencentId) {

        log.debug("[hunterThirdAccountWAction] begin");

        try {
            final JobHunter jobHunter = new JobHunter();
            jobHunter.setJobHunterId(jobHunterId);
            jobHunter.setSinaId(sinaId);
            jobHunter.setWeixinId(weixinId);
            jobHunter.setTencentId(tencentId);

            jobHunterService.modJobHunter(jobHunter, true);

        } catch (Exception e) {
            log.debug("hunterThirdAccountWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterThirdAccountWAction] end");

        return retMap;
    }

    /**
     * 增加教育经验
     *
     * @param jobHunterId
     * @param college
     * @param major
     * @param bachelorDegree
     * @param beginTime
     * @param endTime
     * @return
     */
    @At("/page/demo/jobHunter/addedu")
    public Map<String, Object> jobHunterEduExperienceWAction(@Param("jobHunterId") Integer jobHunterId,
                                                             @Param("college") String college,
                                                             @Param("major") String major,
                                                             @Param("bachelorDegree") String bachelorDegree,
                                                             @Param("beginTime") java.util.Date beginTime,
                                                             @Param("endTime") java.util.Date endTime) {

        log.debug("[jobHunterEduExperienceWAction] begin");

        try {
            JobHunterEduExperience jobHunterEduExperience = new JobHunterEduExperience();
            jobHunterEduExperience.setJobHunterId(jobHunterId);
            jobHunterEduExperience.setCollege(college);
            jobHunterEduExperience.setMajor(major);
            jobHunterEduExperience.setBachelorDegree(bachelorDegree);
            jobHunterEduExperience.setBeginTime(beginTime);
            jobHunterEduExperience.setEndTime(endTime);
            jobHunterEduExperience.setUpdateTime(DateUtil.getNowDate());

            Integer jobHunterEduExperienceId = jobHunterService.addJobHunterEduExperience(jobHunterEduExperience);

            setRetInfo(RetConstants.KEY_LAST_INSERT_ID, jobHunterEduExperienceId);
        } catch (Exception e) {
            log.debug("jobHunterEduExperienceWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterEduExperienceWAction] end");

        return retMap;
    }

    /**
     * 增加求职者期望工作情况
     *
     * @param jobHunterId
     * @param city
     * @param workPattern
     * @param jobHunterCurrentStatus
     * @param expectJob
     * @param expectSalary
     * @return
     */
    @At("/page/demo/jobHunter/addexpect")
    public Map<String, Object> jobHunterExpectWAction(@Param("jobHunterId") Integer jobHunterId,
                                                      @Param("city") String city,
                                                      @Param("workPattern") String workPattern,
                                                      @Param("jobHunterCurrentStatus") String jobHunterCurrentStatus,
                                                      @Param("expectJob") String expectJob,
                                                      @Param("expectSalary") String expectSalary) {

        log.debug("[jobHunterExpectWAction] begin");

        try {
            JobHunterExpect jobHunterExpect = null;
            if ((jobHunterExpect = jobHunterService.qryJobHunterExpectByJobHunterId(jobHunterId)) != null) {
                jobHunterExpect.setJobHunterId(jobHunterId);
                jobHunterExpect.setCity(city);
                jobHunterExpect.setWorkPattern(workPattern);
                jobHunterExpect.setExpectJob(expectJob);
                jobHunterExpect.setExpectSalary(expectSalary);
                jobHunterExpect.setUpdateTime(DateUtil.getNowDate());
                jobHunterService.modJobHunterExpect(jobHunterExpect, true);
            } else {
                jobHunterExpect = new JobHunterExpect();
                jobHunterExpect.setJobHunterId(jobHunterId);
                jobHunterExpect.setCity(city);
                jobHunterExpect.setWorkPattern(workPattern);
                jobHunterExpect.setExpectJob(expectJob);
                jobHunterExpect.setExpectSalary(expectSalary);
                jobHunterExpect.setUpdateTime(DateUtil.getNowDate());
                Integer jobHunterExpectId = jobHunterService.addJobHunterExpect(jobHunterExpect);
                setRetInfo(RetConstants.KEY_LAST_INSERT_ID, jobHunterExpectId);
            }
        } catch (Exception e) {
            log.debug("jobHunterExpectWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterExpectWAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/addprogram")
    public Map<String, Object> jobHunterProgramExperienceWAction(@Param("jobHunterId") Integer jobHunterId,
                                                                 @Param("programName") String programName,
                                                                 @Param("programJobName") String programJobName,
                                                                 @Param("programDesc") String programDesc,
                                                                 @Param("beginTime") java.util.Date beginTime,
                                                                 @Param("endTime") java.util.Date endTime) {

        log.debug("[jobHunterProgramExperienceWAction] begin");

        try {
            JobHunterProgramExperience jobHunterProgramExperience = new JobHunterProgramExperience();
            jobHunterProgramExperience.setJobHunterId(jobHunterId);
            jobHunterProgramExperience.setProgramName(programName);
            jobHunterProgramExperience.setProgramJobName(programJobName);
            jobHunterProgramExperience.setProgramDesc(programDesc);
            jobHunterProgramExperience.setBeginTime(beginTime);
            jobHunterProgramExperience.setEndTime(endTime);
            jobHunterProgramExperience.setUpdateTime(DateUtil.getNowDate());

            Integer jobHunterProgramExperienceId = jobHunterService.addJobHunterProgramExperience(jobHunterProgramExperience);

            setRetInfo(RetConstants.KEY_LAST_INSERT_ID, jobHunterProgramExperienceId);
        } catch (Exception e) {
            log.debug("jobHunterProgramExperienceWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterProgramExperienceWAction] end");

        return retMap;

    }

    @At("/page/demo/jobHunter/addworkexperience")
    public Map<String, Object> jobHunterWorkExperienceWAction(@Param("jobHunterId") Integer jobHunterId,
                                                              @Param("employerName") String employerName,
                                                              @Param("jobName") String jobName,
                                                              @Param("beginTime") java.util.Date beginTime,
                                                              @Param("endTime") java.util.Date endTime,
                                                              @Param("salary") String salary,
                                                              @Param("workDesc") String workDesc) {

        log.debug("[jobHunterWorkExperienceWAction] begin");

        try {
            JobHunterWorkExperience jobHunterWorkExperience = new JobHunterWorkExperience();
            jobHunterWorkExperience.setJobHunterId(jobHunterId);
            jobHunterWorkExperience.setEmployerName(employerName);
            jobHunterWorkExperience.setJobName(jobName);
            jobHunterWorkExperience.setBeginTime(beginTime);
            jobHunterWorkExperience.setEndTime(endTime);
            jobHunterWorkExperience.setSalary(salary);
            jobHunterWorkExperience.setWorkDesc(workDesc);
            jobHunterWorkExperience.setUpdateTime(DateUtil.getNowDate());

            Integer jobHunterWorkExperienceId = jobHunterService.addJobHunterWorkExperience(jobHunterWorkExperience);

            setRetInfo(RetConstants.KEY_LAST_INSERT_ID, jobHunterWorkExperienceId);
        } catch (Exception e) {
            log.debug("jobHunterWorkExperienceWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterWorkExperienceWAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/addtrainexperience")
    public Map<String, Object> jobHunterTrainExperienceWAction(@Param("jobHunterId") Integer jobHunterId,
                                                               @Param("trainInstitutionName") String trainInstitutionName,
                                                               @Param("courseName") String courseName,
                                                               @Param("certificateName") String certificateName,
                                                               @Param("trainBeginDate") java.util.Date trainBeginDate,
                                                               @Param("trainEndDate") java.util.Date trainEndDate,
                                                               @Param("trainDesc") String trainDesc) {

        log.debug("[jobHunterTrainExperienceWAction] begin");

        try {
            JobHunterTrainExperience jobHunterTrainExperience = new JobHunterTrainExperience();
            jobHunterTrainExperience.setJobHunterId(jobHunterId);
            jobHunterTrainExperience.setTrainInstitutionName(trainInstitutionName);
            jobHunterTrainExperience.setCourseName(courseName);
            jobHunterTrainExperience.setCertificateName(certificateName);
            jobHunterTrainExperience.setTrainBeginDate(trainBeginDate);
            jobHunterTrainExperience.setTrainEndDate(trainEndDate);
            jobHunterTrainExperience.setTrainDesc(trainDesc);

            Integer jobHunterTrainExperienceId = jobHunterService.addJobHunterTrainExperience(jobHunterTrainExperience);

            setRetInfo(RetConstants.KEY_LAST_INSERT_ID, jobHunterTrainExperienceId);
        } catch (Exception e) {
            log.debug("jobHunterTrainExperienceWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterTrainExperienceWAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/resetpassword")
    public Map<String, Object> resetPasswordWAction(@Param("jobHunterEmail") String jobHunterEmail,
                                                    @Param("jobHunterOldPassword") String oldPassword,
                                                    @Param("jobHunterPassword") String jobHunterPassword,
                                                    HttpServletRequest request) {

        log.debug("[resetPasswordWAction] begin");

        try {
            if (oldPassword != null) {
                JobHunter jobHunter = jobHunterService.qryJobHunterByEmail(jobHunterEmail);
                if (!jobHunter.getJobHunterPassword().equalsIgnoreCase(oldPassword)) {
                    setRetCodeAndMsg(RetConstants.VAL_FAIL, "old password is incorrect!");
                    return retMap;
                }
            }
            jobHunterService.modJobHunterByEmail(jobHunterEmail, jobHunterPassword);
            request.getSession().setAttribute("password", jobHunterPassword);

        } catch (Exception e) {
            log.debug("resetPasswordWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[resetPasswordWAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/login")
    public Map<String, Object> jobHunterLoginAction(@Param("sinaId") String sinaId,
                                                    @Param("tencentId") String tencentId, @Param("jobHunterEmail") String jobHunterEmail,
                                                    @Param("jobHunterPassword") String jobHunterPassword, HttpServletRequest request,
                                                    HttpServletResponse response) {

        log.debug("[jobHunterLoginAction] begin");
        HttpSession session = request.getSession();
        try {
            JobHunter jobHunter = null;
            if (StringUtil.isNotEmpty(sinaId)) {
                jobHunter = jobHunterService.qryJobHunterBySinaId(sinaId);
            } else if (StringUtil.isNotEmpty(tencentId)) {
                jobHunter = jobHunterService.qryJobHunterByTencentId(tencentId);
            } else if (StringUtil.isNotEmpty(jobHunterEmail) && StringUtil.isNotEmpty(jobHunterPassword)) {
                jobHunter = jobHunterService.qryJobHunterByEmailAndPassword(jobHunterEmail, jobHunterPassword);
            }

            if (jobHunter == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "can not found job hunter");
            } else {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, jobHunter);
                session.setAttribute("username", jobHunter.getJobHunterEmail());
                session.setAttribute("password", jobHunterPassword);
                session.setAttribute("jobHunterId", jobHunter.getJobHunterId());
                session.setAttribute("jobHunterName", jobHunter.getJobHunterName());
                session.setAttribute("avatarPath", jobHunter.getJobHunterAvatarPath());
                session.setAttribute("userType", "hunter");

                if (session.getAttribute("userInfo") == null) {
                    UserInfoListener userInfoListener = new UserInfoListener();
                    userInfoListener.userInfo.setUserType(0);
                    userInfoListener.userInfo.setUserName(jobHunter.getJobHunterEmail());
                    userInfoListener.userInfo.setUserId(jobHunter.getJobHunterId());
                    session.setAttribute("userInfo", userInfoListener);
                }
                SessionData sessionData = new SessionData();
                sessionData.setAvatarPath(jobHunter.getJobHunterAvatarPath());
                sessionData.setCurrent_user(jobHunter.getJobHunterName());
                sessionData.setPassword(jobHunterPassword);
                sessionData.setUsername(jobHunter.getJobHunterEmail());
                JSONObject jsonObject = JSONObject.fromObject(sessionData);
                jangoSession jangosession = new jangoSession();
                jangosession.setSessionKey(session.getId());
                Cookie cookie = new Cookie("sessionid", session.getId());
                cookie.setMaxAge(30 * 60);
                cookie.setPath("/");
                cookie.setDomain("maojuren.com");
                response.addCookie(cookie);
                jangosession.setSessionData(jsonObject.toString());
                jangosession.setExpireDate(new Date(session.getLastAccessedTime()));
                try {
                    jobHunterService.addJangoSession(jangosession);
                } catch (Exception e) {
                    //如果已经插入过了
                }
            }

        } catch (Exception e) {
            log.debug("jobHunterLoginAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterLoginAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/logout")
    public Map<String, Object> jobHunterLogoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("username");
        session.removeAttribute("userType");
        session.removeAttribute("password");
        session.removeAttribute("jobHunterId");
        session.removeAttribute("avatarPath");
        session.removeAttribute("jobHunterName");
        session.removeAttribute("interactGot");
        session.removeAttribute("envelopeGot");
        session.invalidate();
        return retMap;
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
        sb.append("<li><a href=\"#\" onclick=\"queryJob(1)\">«</a>");
        for (int i = 0; i < pageSize; i++) {
            if (i == pageIndex - 1) {
                sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryResumes(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
            } else {
                sb.append("<li><a href=\"#\" onclick=\"queryResumes(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
            }
        }
        sb.append("<li><a href=\"#\" onclick=\"queryResumes(" + pageSize + ")\">»</a></li>");

        return sb.toString();
    }

    @At("/page/demo/jobHunter/jobhunters")
    public Map<String, Object> jobHuntersRAction(
            @Param("jobHunterBachelorDegree") String jobHunterBachelorDegree,
            @Param("jobHunterStartWorkYear") String jobHunterStartWorkYear,
            @Param("jobHunterSex") String jobHunterSex,
            @Param("jobHunterCurrentProvince") String jobHunterCurrentProvince,
            @Param("jobHunterCurrentCity") String jobHunterCurrentCity,
            @Param("jobHunterCurrentDistrict") String jobHunterCurrentDistrict,
            @Param("jobHunterMajor") String jobHunterMajor,
            @Param("jobHunterCurrentStatus") String jobHunterCurrentStatus,
            @Param("updateTime") String updateTime,
            @Param("keyword") String keyword,
            @Param("expectSalary") String expectSalary,
            @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[jobHuntersRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        try {
            int totalCount = jobHunterService.countJobHuntersByPager(jobHunterBachelorDegree, jobHunterStartWorkYear,
                    jobHunterSex, jobHunterCurrentProvince, jobHunterCurrentCity, jobHunterCurrentDistrict,
                    jobHunterMajor, jobHunterCurrentStatus, updateTime, keyword, expectSalary);

            setRetInfo(RetConstants.PAGINATION_INFO, getPaginationInfo(totalCount, pageIndex));

            List<JobHunter> jobHunters = jobHunterService.qryJobHunterByPager(jobHunterBachelorDegree, jobHunterStartWorkYear,
                    jobHunterSex, jobHunterCurrentProvince, jobHunterCurrentCity, jobHunterCurrentDistrict,
                    jobHunterMajor, jobHunterCurrentStatus, updateTime, keyword, expectSalary,
                    pageIndex, pageSize);

            if (jobHunters != null && jobHunters.size() > 0) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_LIST, jobHunters);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter list on page index");
            }

        } catch (Exception e) {
            log.debug("jobPageRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHuntersRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntereduexperience")
    public Map<String, Object> jobHunterEduExperienceRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterEduExperienceRAction] begin");

        try {

            List<JobHunterEduExperience> jobHunterEduExperiences = jobHunterService.qryAllJobHunterEduExperienceByJobHunterId(jobHunterId);

            if (!Lang.isEmpty(jobHunterEduExperiences)) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_EDU_EXPERIENCE_LIST, jobHunterEduExperiences);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter edu experience");
            }

        } catch (Exception e) {
            log.debug("jobHunterEduExperienceRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterEduExperienceRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntereduexperiencew")
    public Map<String, Object> jobHunterEduExperienceMAction(@Param("jobHunterEduExperienceId") Integer jobHunterEduExperienceId,
                                                             @Param("college") String college,
                                                             @Param("major") String major,
                                                             @Param("bachelorDegree") String bachelorDegree,
                                                             @Param("beginTime") java.util.Date beginTime,
                                                             @Param("endTime") java.util.Date endTime) {

        log.debug("[jobHunterEduExperienceMAction] begin");

        try {

            JobHunterEduExperience jobHunterEduExperience = new JobHunterEduExperience();
            jobHunterEduExperience.setJobHunterEduExperienceId(jobHunterEduExperienceId);
            jobHunterEduExperience.setCollege(college);
            jobHunterEduExperience.setMajor(major);
            jobHunterEduExperience.setBachelorDegree(bachelorDegree);
            jobHunterEduExperience.setBeginTime(beginTime);
            jobHunterEduExperience.setEndTime(endTime);
            jobHunterEduExperience.setUpdateTime(DateUtil.getNowDate());

            jobHunterService.modJobHunterEduExperience(jobHunterEduExperience, true);

        } catch (Exception e) {
            log.debug("jobHunterEduExperienceMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterEduExperienceMAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntereduexperienced")
    public Map<String, Object> jobHunterEduExperienceDAction(@Param("jobHunterEduExperienceId") Integer jobHunterEduExperienceId) {

        log.debug("[jobHunterEduExperienceDAction] begin");

        try {

            JobHunterEduExperience jobHunterEduExperience = new JobHunterEduExperience();
            jobHunterEduExperience.setJobHunterEduExperienceId(jobHunterEduExperienceId);

            jobHunterService.delJobHunterEduExperience(jobHunterEduExperience);

        } catch (Exception e) {
            log.debug("jobHunterEduExperienceDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterEduExperienceDAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterexpect")
    public Map<String, Object> jobHunterExpectRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterExpectRAction] begin");

        try {

            List<JobHunterExpect> jobHunterExpects = jobHunterService.qryAllJobHunterExpectByJobHunterId(jobHunterId);

            if (!Lang.isEmpty(jobHunterExpects)) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_EXPECE_LIST, jobHunterExpects);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter expect");
            }

        } catch (Exception e) {
            log.debug("jobHunterExpectRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterExpectRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterexpectw")
    public Map<String, Object> jobHunterExpectMAction(@Param("jobHunterExpectId") Integer jobHunterExpectId,
                                                      @Param("city") String city,
                                                      @Param("workPattern") String workPattern,
                                                      @Param("expectJob") String expectJob,
                                                      @Param("expectSalary") String expectSalary,
                                                      @Param("jobHunterCurrentStatus") String jobHunterCurrentStatus) {

        log.debug("[jobHunterExpectMAction] begin");

        try {

            JobHunterExpect jobHunterExpect = new JobHunterExpect();
            jobHunterExpect.setJobHunterExpectId(jobHunterExpectId);
            jobHunterExpect.setCity(city);
            jobHunterExpect.setWorkPattern(workPattern);
            jobHunterExpect.setExpectJob(expectJob);
            jobHunterExpect.setExpectSalary(expectSalary);
            jobHunterExpect.setUpdateTime(DateUtil.getNowDate());

            jobHunterService.modJobHunterExpect(jobHunterExpect, true);

        } catch (Exception e) {
            log.debug("jobHunterExpectMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterExpectMAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterexpectd")
    public Map<String, Object> jobHunterExpectDAction(@Param("jobHunterExpectId") Integer jobHunterExpectId) {

        log.debug("[jobHunterExpectDAction] begin");

        try {

            JobHunterExpect jobHunterExpect = new JobHunterExpect();
            jobHunterExpect.setJobHunterExpectId(jobHunterExpectId);

            jobHunterService.delJobHunterExpect(jobHunterExpect);

        } catch (Exception e) {
            log.debug("jobHunterExpectDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterExpectDAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterprogramexperience")
    public Map<String, Object> jobHunterProgramExperienceRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterProgramExperienceRAction] begin");

        try {

            JobHunterProgramExperience jobHunterProgramExperience = jobHunterService
                    .qryJobHunterProgramExperienceByJobHunterId(jobHunterId);

            if (!Lang.isEmpty(jobHunterProgramExperience)) {
                setRetInfo(RetConstants.KEY_JOB_PROGRAM_EXPERIENCE_DETAIL, jobHunterProgramExperience);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter program experience");
            }

        } catch (Exception e) {
            log.debug("jobHunterProgramExperienceRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterProgramExperienceRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterprogramexperiencew")
    public Map<String, Object> jobHunterProgramExperienceMAction(@Param("jobHunterProgramExperienceId") Integer jobHunterProgramExperienceId,
                                                                 @Param("programName") String programName,
                                                                 @Param("programJobName") String programJobName,
                                                                 @Param("beginTime") java.util.Date beginTime,
                                                                 @Param("endTime") java.util.Date endTime,
                                                                 @Param("programDesc") String programDesc) {

        log.debug("[jobHunterProgramExperienceMAction] begin");

        try {

            JobHunterProgramExperience jobHunterProgramExperience = new JobHunterProgramExperience();
            jobHunterProgramExperience.setJobHunterProgramExperienceId(jobHunterProgramExperienceId);
            jobHunterProgramExperience.setProgramJobName(programJobName);
            jobHunterProgramExperience.setProgramName(programName);
            jobHunterProgramExperience.setBeginTime(beginTime);
            jobHunterProgramExperience.setEndTime(endTime);
            jobHunterProgramExperience.setProgramDesc(programDesc);
            jobHunterProgramExperience.setUpdateTime(DateUtil.getNowDate());

            jobHunterService.modJobHunterProgramExperience(jobHunterProgramExperience, true);

        } catch (Exception e) {
            log.debug("jobHunterProgramExperienceMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterProgramExperienceMAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterprogramexperienced")
    public Map<String, Object> jobHunterProgramExperienceDAction(@Param("jobHunterProgramExperienceId") Integer jobHunterProgramExperienceId) {

        log.debug("[jobHunterProgramExperienceDAction] begin");

        try {

            JobHunterProgramExperience jobHunterProgramExperience = new JobHunterProgramExperience();
            jobHunterProgramExperience.setJobHunterProgramExperienceId(jobHunterProgramExperienceId);

            jobHunterService.delJobHunterProgramExperience(jobHunterProgramExperience);

        } catch (Exception e) {
            log.debug("jobHunterProgramExperienceDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterProgramExperienceDAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterworkexperience")
    public Map<String, Object> jobHunterWorkExperienceRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterWorkExperienceRAction] begin");

        try {

            List<JobHunterWorkExperience> jobHunterWorkExperiences = jobHunterService.qryAllJobHunterWorkExperienceByJobHunterId(jobHunterId);

            if (!Lang.isEmpty(jobHunterWorkExperiences)) {
                setRetInfo(RetConstants.KEY_JOB_WORK_EXPERIENCE_LIST, jobHunterWorkExperiences);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter work experience");
            }

        } catch (Exception e) {
            log.debug("jobHunterWorkExperienceRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterWorkExperienceRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterworkexperiencew")
    public Map<String, Object> jobHunterWorkExperienceMAction(@Param("jobHunterWorkExperienceId") Integer jobHunterWorkExperienceId,
                                                              @Param("employerName") String employerName,
                                                              @Param("jobName") String jobName,
                                                              @Param("beginTime") java.util.Date beginTime,
                                                              @Param("endTime") java.util.Date endTime,
                                                              @Param("salary") String salary,
                                                              @Param("workDesc") String workDesc) {

        log.debug("[jobHunterWorkExperienceMAction] begin");

        try {

            JobHunterWorkExperience jobHunterWorkExperience = new JobHunterWorkExperience();
            jobHunterWorkExperience.setJobHunterWorkExperienceId(jobHunterWorkExperienceId);
            jobHunterWorkExperience.setEmployerName(employerName);
            jobHunterWorkExperience.setJobName(jobName);
            jobHunterWorkExperience.setBeginTime(beginTime);
            jobHunterWorkExperience.setEndTime(endTime);
            jobHunterWorkExperience.setSalary(salary);
            jobHunterWorkExperience.setWorkDesc(workDesc);
            jobHunterWorkExperience.setUpdateTime(DateUtil.getNowDate());

            jobHunterService.modJobHunterWorkExperience(jobHunterWorkExperience, true);

        } catch (Exception e) {
            log.debug("jobHunterWorkExperienceMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterWorkExperienceMAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterworkexperienced")
    public Map<String, Object> jobHunterWorkExperienceDAction(@Param("jobHunterWorkExperienceId") Integer jobHunterWorkExperienceId) {

        log.debug("[jobHunterWorkExperienceDAction] begin");

        try {

            JobHunterWorkExperience jobHunterWorkExperience = new JobHunterWorkExperience();
            jobHunterWorkExperience.setJobHunterWorkExperienceId(jobHunterWorkExperienceId);

            jobHunterService.delJobHunterWorkExperience(jobHunterWorkExperience);

        } catch (Exception e) {
            log.debug("jobHunterWorkExperienceDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterWorkExperienceDAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntertrainexperience")
    public Map<String, Object> jobHunterTrainExperienceRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterTrainExperienceRAction] begin");

        try {

            List<JobHunterTrainExperience> jobHunterTrainExperiences = jobHunterService.qryAllJobHunterTrainExperienceByJobHunterId(jobHunterId);

            if (!Lang.isEmpty(jobHunterTrainExperiences)) {
                setRetInfo(RetConstants.KEY_JOB_TRAIN_EXPERIENCE_LIST, jobHunterTrainExperiences);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter train experience");
            }

        } catch (Exception e) {
            log.debug("jobHunterTrainExperienceRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterTrainExperienceRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntertrainexperiencew")
    public Map<String, Object> jobHunterTrainExperienceMAction(@Param("jobHunterTrainExperienceId") Integer jobHunterTrainExperienceId,
                                                               @Param("trainInstitutionName") String trainInstitutionName,
                                                               @Param("courseName") String courseName,
                                                               @Param("certificateName") String certificateName,
                                                               @Param("trainBeginDate") java.util.Date trainBeginDate,
                                                               @Param("trainEndDate") java.util.Date trainEndDate,
                                                               @Param("trainDesc") String trainDesc) {

        log.debug("[jobHunterTrainExperienceMAction] begin");

        try {

            JobHunterTrainExperience jobHunterTrainExperience = new JobHunterTrainExperience();
            jobHunterTrainExperience.setJobHunterTrainExperienceId(jobHunterTrainExperienceId);
            jobHunterTrainExperience.setTrainInstitutionName(trainInstitutionName);
            jobHunterTrainExperience.setCourseName(courseName);
            jobHunterTrainExperience.setCertificateName(certificateName);
            jobHunterTrainExperience.setTrainBeginDate(trainBeginDate);
            jobHunterTrainExperience.setTrainEndDate(trainEndDate);
            jobHunterTrainExperience.setTrainDesc(trainDesc);

            jobHunterService.modJobHunterTrainExperience(jobHunterTrainExperience, true);

        } catch (Exception e) {
            log.debug("jobHunterTrainExperienceMAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterTrainExperienceMAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhuntertrainexperienced")
    public Map<String, Object> jobHunterTrainExperienceDAction(@Param("jobHunterTrainExperienceId") Integer jobHunterTrainExperienceId) {

        log.debug("[jobHunterTrainExperienceDAction] begin");

        try {

            JobHunterTrainExperience jobHunterTrainExperience = new JobHunterTrainExperience();
            jobHunterTrainExperience.setJobHunterTrainExperienceId(jobHunterTrainExperienceId);

            jobHunterService.delJobHunterTrainExperience(jobHunterTrainExperience);

        } catch (Exception e) {
            log.debug("jobHunterTrainExperienceDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterTrainExperienceDAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterbasic")
    public Map<String, Object> jobHunterBasicRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterBasicRAction] begin");

        try {

            JobHunter jobHunter = jobHunterService.qryJobHunter(jobHunterId);

            if (!Lang.isEmpty(jobHunter)) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, jobHunter);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter basic");
            }

        } catch (Exception e) {
            log.debug("jobHunterBasicRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterBasicRAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunterbasicw")
    public Map<String, Object> jobHunterBasicWAction(@Param("jobHunterId") Integer jobHunterId,
                                                     @Param("jobHunterName") String jobHunterName,
                                                     @Param("jobHunterEmail") String jobHunterEmail,
                                                     @Param("jobHunterTel") String jobHunterTel,
                                                     @Param("jobHunterBachelorDegree") String jobHunterBachelorDegree,
                                                     @Param("jobHunterStartWorkYear") String jobHunterStartWorkYear,
                                                     @Param("jobHunterSex") String jobHunterSex,
                                                     @Param("jobHunterBirthday") java.util.Date jobHunterBirthday,
                                                     @Param("jobHunterAvatarPath") String jobHunterAvatarPath,
                                                     @Param("jobHunterDepict") String jobHunterDepict,
                                                     @Param("jobHunterCurrentProvince") String jobHunterCurrentProvince,
                                                     @Param("jobHunterCurrentCity") String jobHunterCurrentCity,
                                                     @Param("jobHunterCurrentDistrict") String jobHunterCurrentDistrict,
                                                     @Param("jobHunterMajor") String jobHunterMajor,
                                                     @Param("jobHunterCollege") String jobHunterCollege,
                                                     @Param("jobHunterLastOccupation") String jobHunterLastOccupation,
                                                     @Param("jobHunterLastEmployer") String jobHunterLastEmployer,
                                                     @Param("jobHunterCurrentStatus") String jobHunterCurrentStatus,
                                                     @Param("jobHunterSpecialty") String jobHunterSpecialty,
                                                     @Param("productPic1") String productPic1,
                                                     @Param("productPic2") String productPic2,
                                                     @Param("productPic3") String productPic3,
                                                     @Param("productPic4") String productPic4,
                                                     @Param("productPic5") String productPic5,
                                                     @Param("productUrl") String productUrl) {

        log.debug("[jobHunterBasicWAction] begin");

        try {

            JobHunter jobHunter = new JobHunter();
            jobHunter.setJobHunterId(jobHunterId);
            jobHunter.setJobHunterName(jobHunterName);
            jobHunter.setJobHunterEmail(jobHunterEmail);
            jobHunter.setJobHunterTel(jobHunterTel);
            jobHunter.setJobHunterBachelorDegree(jobHunterBachelorDegree);
            jobHunter.setJobHunterStartWorkYear(jobHunterStartWorkYear);
            jobHunter.setJobHunterSex(jobHunterSex);
            jobHunter.setJobHunterBirthday(jobHunterBirthday);
            jobHunter.setJobHunterAvatarPath(jobHunterAvatarPath);
            jobHunter.setJobHunterDepict(jobHunterDepict);
            jobHunter.setJobHunterCurrentProvince(jobHunterCurrentProvince);
            jobHunter.setJobHunterCurrentCity(jobHunterCurrentCity);
            jobHunter.setJobHunterCurrentDistrict(jobHunterCurrentDistrict);
            jobHunter.setJobHunterMajor(jobHunterMajor);
            jobHunter.setJobHunterCollege(jobHunterCollege);
            jobHunter.setJobHunterLastOccupation(jobHunterLastOccupation);
            jobHunter.setJobHunterLastEmployer(jobHunterLastEmployer);
            jobHunter.setJobHunterCurrentStatus(jobHunterCurrentStatus);
            jobHunter.setJobHunterSpecialty(jobHunterSpecialty);
            jobHunter.setProductPic1(productPic1);
            jobHunter.setProductPic2(productPic2);
            jobHunter.setProductPic3(productPic3);
            jobHunter.setProductPic4(productPic4);
            jobHunter.setProductPic5(productPic5);
            jobHunter.setProductUrl(productUrl);
            jobHunter.setUpdateTime(DateUtil.getNowDate());

            jobHunterService.modJobHunter(jobHunter, true);

        } catch (Exception e) {
            log.debug("jobHunterBasicWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterBasicWAction] end");

        return retMap;
    }

    @At("/page/demo/jobHunter/jobhunter")
    public Map<String, Object> jobHunterRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterRAction] begin");

        try {

            JobHunter jobHunter = jobHunterService.qryJobHunter(jobHunterId);

            if (!Lang.isEmpty(jobHunter)) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, jobHunter);

                List<JobHunterEduExperience> jobHunterEduExperiences = jobHunterService.qryAllJobHunterEduExperienceByJobHunterId(jobHunterId);
                setRetInfo(RetConstants.KEY_JOB_HUNTER_EDU_EXPERIENCE_LIST, jobHunterEduExperiences);

                List<JobHunterExpect> jobHunterExpects = jobHunterService.qryAllJobHunterExpectByJobHunterId(jobHunterId);
                setRetInfo(RetConstants.KEY_JOB_HUNTER_EXPECE_LIST, jobHunterExpects);

                //List<JobHunterProgramExperience> jobHunterProgramExperiences = jobHunterService.qryAllJobHunterProgramExperienceByJobHunterId(jobHunterId);
                //setRetInfo(RetConstants.KEY_JOB_PROGRAM_EXPERIENCE_LIST, jobHunterProgramExperiences);

                List<JobHunterTrainExperience> jobHunterTrainExperiences = jobHunterService.qryAllJobHunterTrainExperienceByJobHunterId(jobHunterId);
                setRetInfo(RetConstants.KEY_JOB_TRAIN_EXPERIENCE_LIST, jobHunterTrainExperiences);

                List<JobHunterWorkExperience> jobHunterWorkExperiences = jobHunterService.qryAllJobHunterWorkExperienceByJobHunterId(jobHunterId);
                setRetInfo(RetConstants.KEY_JOB_WORK_EXPERIENCE_LIST, jobHunterWorkExperiences);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter");
            }

        } catch (Exception e) {
            log.debug("jobHunterRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterRAction] end");

        return retMap;
    }

    /**
     * 判断简历的完整度，返回一系列的参数给前台去使用
     */
    @At("/page/demo/jobHunter/complete")
    public Map<String, Object> jobHunterCompleteRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[jobHunterRAction] begin");

        try {

            JobHunter jobHunter = jobHunterService.qryJobHunter(jobHunterId);

            if (!Lang.isEmpty(jobHunter)) {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, "ok");

                List<JobHunterEduExperience> jobHunterEduExperiences = jobHunterService.qryAllJobHunterEduExperienceByJobHunterId(jobHunterId);
                if (jobHunterEduExperiences != null && jobHunterEduExperiences.size() > 0)
                    setRetInfo(RetConstants.KEY_JOB_HUNTER_EDU_EXPERIENCE_LIST, "ok");

                List<JobHunterExpect> jobHunterExpects = jobHunterService.qryAllJobHunterExpectByJobHunterId(jobHunterId);
                if (jobHunterExpects != null && jobHunterExpects.size() > 0)
                    setRetInfo(RetConstants.KEY_JOB_HUNTER_EXPECE_LIST, "ok");

                List<JobHunterTrainExperience> jobHunterTrainExperiences = jobHunterService.qryAllJobHunterTrainExperienceByJobHunterId(jobHunterId);
                if (jobHunterTrainExperiences != null && jobHunterTrainExperiences.size() > 0)
                    setRetInfo(RetConstants.KEY_JOB_TRAIN_EXPERIENCE_LIST, "ok");

                List<JobHunterWorkExperience> jobHunterWorkExperiences = jobHunterService.qryAllJobHunterWorkExperienceByJobHunterId(jobHunterId);
                if (jobHunterWorkExperiences != null && jobHunterWorkExperiences.size() > 0)
                    setRetInfo(RetConstants.KEY_JOB_WORK_EXPERIENCE_LIST, "ok");
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job hunter");
            }

        } catch (Exception e) {
            log.debug("jobHunterCompleteRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobHunterCompleteRAction] end");

        return retMap;
    }


    /**
     * Description: <br>
     * 企业详情
     */
    @At("/page/demo/hunter/hunterdetails")
    public Map<String, Object> hunterDetailsRAction(
            @Param("hunterIds") String hunterIds) {

        log.debug("[hunterDetailsRAction] begin");

        try {
            List<JobHunter> jobHunters = jobHunterService.qryJobHunters(hunterIds);

            if (jobHunters == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_DETAIL, jobHunters);
            }
        } catch (Exception e) {
            log.debug("hunterDetailsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterDetailsRAction] end");

        return retMap;
    }
}
