package com.delta.worko2o.action;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.EmailValidServiceApi;
import com.delta.worko2o.service.api.EmployerServiceApi;
import com.delta.worko2o.service.api.JobHunterServiceApi;
import com.delta.worko2o.service.api.JobServiceApi;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.StringUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <Description> <br>
 * 职位相关操作类
 *
 * @param <>
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月13日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class JobAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private JobServiceApi jobService;
    @Inject
    private EmployerServiceApi employerService;
    @Inject
    private JobHunterServiceApi jobHunterService;
    @Inject
    private EmailValidServiceApi emailValidService;

    @At("/page/demo/job/publish")
    public Map<String, ?> jobWAction(@Param("jobName") String jobName, @Param("jobMainType") String jobMainType,
                                     @Param("jobSubType") String jobSubType, @Param("jobDesc") String jobDesc,
                                     @Param("expReq") String expReq, @Param("eduReq") String eduReq, @Param("majorReq") String majorReq,
                                     @Param("sexReq") String sexReq, @Param("salaryBegin") Integer salaryBegin, @Param("salaryEnd") Integer salaryEnd,
                                     @Param("numberReq") Integer numberReq, @Param("province") String province,
                                     @Param("city") String city, @Param("district") String district,
                                     @Param("mainPage") Integer mainPage, @Param("workPattern") String workPattern,
                                     @Param("deadTime") Date deadTime,
                                     @Param("employerId") Integer employerId, @Param("employerName") String employerName,
                                     @Param("website") String website, @Param("websiteShow") Integer websiteShow,
                                     @Param("storeName") String storeName,
                                     @Param("logoPath") String logoPath, @Param("saleType") String saleType,
                                     @Param("employerScale") String employerScale,
                                     @Param("tag1") String tag1,
                                     @Param("tag2") String tag2, @Param("tag3") String tag3,
                                     @Param("tag4") String tag4, @Param("tag5") String tag5,
                                     @Param("jobPraisedCnt") Integer jobPraisedCnt,
                                     @Param("lat") String lat,
                                     @Param("lng") String lng,
                                     @Param("questionnaireId") Integer questionnaireId,
                                     @Param("questionnaireOn") Integer questionnaireOn,
                                     @Param("qq") Integer qq,
                                     HttpServletRequest request) {

        log.debug("[jobWAction] begin");

        try {
            JobTemp jobTemp = null;
            jobTemp = (JobTemp) request.getSession().getAttribute("jobTemp");
            if (jobTemp == null) {
                jobTemp = new JobTemp();
                jobTemp.setJobName(jobName);
                jobTemp.setJobMainType(jobMainType);
                jobTemp.setJobSubType(jobSubType);
                jobTemp.setJobDesc(jobDesc);
                jobTemp.setExpReq(expReq);
                jobTemp.setEduReq(eduReq);
                jobTemp.setMajorReq(majorReq);
                jobTemp.setSexReq(sexReq);
                jobTemp.setSalaryBegin(salaryBegin);
                jobTemp.setSalaryEnd(salaryEnd);
                jobTemp.setNumberReq(numberReq);
                jobTemp.setProvince(province);
                jobTemp.setCity(city);
                jobTemp.setDistrict(district);
                jobTemp.setMainPage(mainPage);
                jobTemp.setWorkPattern(workPattern);
                jobTemp.setLogoPath(logoPath);
                jobTemp.setSaleType(saleType);
                jobTemp.setEmployerScale(employerScale);
                jobTemp.setPublishTime(DateUtil.getNowDate());
                jobTemp.setDeadTime(deadTime);
                jobTemp.setEmployerId(employerId);
                jobTemp.setEmployerName(employerName);
                jobTemp.setWebsiteShow(websiteShow);
                jobTemp.setStoreName(storeName);
                jobTemp.setWebsite(website);
                jobTemp.setTag1(tag1);
                jobTemp.setTag2(tag2);
                jobTemp.setTag3(tag3);
                jobTemp.setTag4(tag4);
                jobTemp.setTag5(tag5);
                jobTemp.setJobPraisedCnt(jobPraisedCnt);
                jobTemp.setStatus(1);
                jobTemp.setLat(lat);
                jobTemp.setLng(lng);
                jobTemp.setQuestionnaireId(questionnaireId);
                jobTemp.setQuestionnaireOn(questionnaireOn);
                jobTemp.setUpdateTime(DateUtil.getNowDate());
                jobTemp.setQq(qq);
            }

            jobService.addJobTemp(jobTemp);

        } catch (Exception e) {
            log.debug("jobWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobWAction] end");

        return retMap;
    }

    @At("/page/demo/job/update")
    public Map<String, ?> jobUpdateWAction(@Param("jobId") Integer jobId, @Param("jobName") String jobName, @Param("jobMainType") String jobMainType,
                                           @Param("jobSubType") String jobSubType, @Param("jobDesc") String jobDesc,
                                           @Param("expReq") String expReq, @Param("eduReq") String eduReq, @Param("majorReq") String majorReq,
                                           @Param("sexReq") String sexReq, @Param("salaryBegin") Integer salaryBegin, @Param("salaryEnd") Integer salaryEnd,
                                           @Param("numberReq") Integer numberReq, @Param("province") String province,
                                           @Param("city") String city, @Param("district") String district,
                                           @Param("mainPage") Integer mainPage, @Param("workPattern") String workPattern,
                                           @Param("publishTime") Date publishTime, @Param("deadTime") Date deadTime,
                                           @Param("employerId") Integer employerId, @Param("employerName") String employerName,
                                           @Param("website") String website, @Param("websiteShow") Integer websiteShow,
                                           @Param("storeName") String storeName,
                                           @Param("tag1") String tag1,
                                           @Param("tag2") String tag2, @Param("tag3") String tag3,
                                           @Param("tag4") String tag4, @Param("tag5") String tag5,
                                           @Param("jobPraisedCnt") Integer jobPraisedCnt,
                                           @Param("questionnaireId") Integer questionnaireId,
                                           @Param("questionnaireOn") Integer questionnaireOn,
                                           @Param("qq") Integer qq,
                                           HttpServletRequest request) {

        log.debug("[jobUpdateWAction] begin");

        try {
            JobTemp jobTemp = null;
            jobTemp = (JobTemp) request.getSession().getAttribute("jobTemp");
            if (jobTemp == null) {
                jobTemp = new JobTemp();
                jobTemp.setJobId(jobId);
                jobTemp.setJobName(jobName);
                jobTemp.setJobMainType(jobMainType);
                jobTemp.setJobSubType(jobSubType);
                jobTemp.setJobDesc(jobDesc);
                jobTemp.setExpReq(expReq);
                jobTemp.setEduReq(eduReq);
                jobTemp.setMajorReq(majorReq);
                jobTemp.setSexReq(sexReq);
                jobTemp.setSalaryBegin(salaryBegin);
                jobTemp.setSalaryEnd(salaryEnd);
                jobTemp.setNumberReq(numberReq);
                jobTemp.setProvince(province);
                jobTemp.setWorkPattern(workPattern);
                jobTemp.setCity(city);
                jobTemp.setDistrict(district);
                jobTemp.setMainPage(mainPage);
                jobTemp.setPublishTime(publishTime);
                jobTemp.setDeadTime(deadTime);
                jobTemp.setEmployerId(employerId);
                jobTemp.setEmployerName(employerName);
                jobTemp.setWebsiteShow(websiteShow);
                jobTemp.setStoreName(storeName);
                jobTemp.setWebsite(website);
                jobTemp.setTag1(tag1);
                jobTemp.setTag2(tag2);
                jobTemp.setTag3(tag3);
                jobTemp.setTag4(tag4);
                jobTemp.setTag5(tag5);
                jobTemp.setStatus(3);
                jobTemp.setQuestionnaireId(questionnaireId);
                jobTemp.setQuestionnaireOn(questionnaireOn);
                jobTemp.setUpdateTime(DateUtil.getNowDate());
                jobTemp.setQq(qq);
            }
            Boolean isPraise = (Boolean) request.getSession().getAttribute("praiseJob" + jobId);
            if (isPraise == null) {
                isPraise = false;
            }
            request.getSession().setAttribute("praiseJob" + jobId, !isPraise);
            if (jobPraisedCnt != null) {
                jobTemp.setJobPraisedCnt(jobPraisedCnt + (isPraise ? 0 : 1));
            }
            jobService.modJobTemp(jobTemp, true);
            setRetInfo(RetConstants.KEY_JOB_DETAIL, jobTemp);

        } catch (Exception e) {
            log.debug("jobUpdateWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobUpdateWAction] end");

        return retMap;
    }

    @At("/page/demo/job/delete")
    public Map<String, ?> jobDeleteWAction(@Param("jobId") Integer jobId, HttpServletRequest request) {
        log.debug("[jobDeleteWAction] begin");

        try {
            JobTemp jobTemp = null;
            jobTemp = (JobTemp) request.getSession().getAttribute("jobTemp");
            if (jobTemp == null) {
                jobTemp = new JobTemp();
                jobTemp.setJobId(jobId);
                jobTemp.setStatus(4);
                jobTemp.setUpdateTime(DateUtil.getNowDate());
            }
            jobService.modJobTemp(jobTemp, true);

        } catch (Exception e) {
            log.debug("jobDeleteWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobDeleteWAction] end");

        return retMap;
    }

    @At("/page/demo/job/mainpage")
    public Map<String, ?> jobMainPageRAction(@Param("limitNum") Integer limitNum) {

        log.debug("[jobMainPageRAction] begin");

        try {

            List<Job> jobList = jobService.qryJobsOrderByUpdateTimeAndLimit(limitNum);

            if (jobList != null && jobList.size() > 0) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job list");
            }

        } catch (Exception e) {
            log.debug("jobMainPageRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobMainPageRAction] end");

        return retMap;
    }

    @At("/page/demo/job/jobpage")
    public Map<String, ?> jobPageRAction(@Param("workplace") String workplace,
                                         @Param("jobMainType") String jobMainType,
                                         @Param("salaryBegin") Integer salaryBegin, @Param("salaryEnd") Integer salaryEnd,
                                         @Param("jobPraisedCnt") Integer jobPraisedCnt, @Param("saleType") String saleType,
                                         @Param("workPattern") String workPattern, @Param("employerScale") String employerScale,
                                         @Param("keyWord") String keyWord, @Param("keyWordType") String keyWordType,
                                         @Param("eduReq") String eduReq, @Param("expReq") String expReq,
                                         @Param("redu") Integer redu,
                                         @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[jobPageRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        try {

//            jobMainType = praseZhongwen(jobMainType);
//            keyWord = praseZhongwen(keyWord);
//            workplace = praseZhongwen(workplace);
//            saleType = praseZhongwen(saleType);
//            workPattern = praseZhongwen(workPattern);
//            employerScale = praseZhongwen(employerScale);
//            eduReq = praseZhongwen(eduReq);
//            expReq = praseZhongwen(expReq);

            int totalCount = jobService.countJobsByPager(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);

            setRetInfo(RetConstants.PAGINATION_INFO, getPaginationInfo(totalCount, pageIndex));

            List<Job> jobList = jobService.qryJobsByPager(workplace, jobMainType, salaryBegin,

                    salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, pageIndex, pageSize, redu);

            if (jobList != null && jobList.size() > 0) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job list on page index");
            }

        } catch (Exception e) {
            log.debug("jobPageRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobPageRAction] end");

        return retMap;
    }


    @At("/page/demo/job/jobpagemainpage")
    public Map<String, ?> jobPageMainPageRAction(@Param("workplace") String workplace,
                                                 @Param("jobMainType") String jobMainType,
                                                 @Param("salaryBegin") Integer salaryBegin, @Param("salaryEnd") Integer salaryEnd,
                                                 @Param("jobPraisedCnt") Integer jobPraisedCnt, @Param("saleType") String saleType,
                                                 @Param("workPattern") String workPattern, @Param("employerScale") String employerScale,
                                                 @Param("keyWord") String keyWord, @Param("keyWordType") String keyWordType,
                                                 @Param("eduReq") String eduReq, @Param("expReq") String expReq,
                                                 @Param("redu") Integer redu,
                                                 @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[jobPageIndentityRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        try {

            int totalCount = jobService.countJobsMainPageByPager(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);

            setRetInfo(RetConstants.PAGINATION_INFO, getPaginationMainPageInfo(totalCount, pageIndex));

            List<Job> jobList = jobService.qryJobsMainPageByPager(workplace, jobMainType, salaryBegin,

                    salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, pageIndex, pageSize, redu);

            if (jobList != null && jobList.size() > 0) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job list on page index");
            }

        } catch (Exception e) {
            log.debug("jobPageIndentityRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobPageIndentityRAction] end");

        return retMap;
    }

    private String praseZhongwen(String keyWord) throws UnsupportedEncodingException {
        if (StringUtil.isNotEmpty(keyWord)) {
            keyWord = java.net.URLDecoder.decode(keyWord, "UTF-8");
        }
        return keyWord;
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
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
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

    private String getPaginationMainPageInfo(int totalCount, Integer pageIndex) {
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
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryMainPageJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryMainPageJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
                }
            }
        } else {
            if (pageIndex > 14) {
                int preFirst = ((int) ((pageIndex) / 14) - 1) * 14 + 1;
                sb.append("<li><a href=\"#\" onclick=\"queryMainPageJob(" + preFirst + ")\">«</a>");
            }
            int fIndex = ((int) ((pageIndex) / 14)) * 14 + 1;
            int lIndex = Math.min((fIndex + 14), totalCount);
            for (int i = fIndex; i < lIndex; i++) {
                if (i == pageIndex) {
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryMainPageJob(" + i + ")\">" + i + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryMainPageJob(" + i + ")\">" + i + "</a></li>");
                }
            }
            if ((fIndex + 13) < totalCount) {
                sb.append("<li><a href=\"#\" onclick=\"queryMainPageJob(" + (fIndex + 14) + ")\">»</a></li>");
            }
        }
        return sb.toString();
    }

    private String getEmployerPaginationInfo(int totalCount, Integer pageIndex) {
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
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryEmployerJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryEmployerJob(" + (i + 1) + ")\">" + (i + 1) + "</a></li>");
                }
            }
        } else {
            if (pageIndex > 14) {
                int preFirst = ((int) ((pageIndex) / 14) - 1) * 14 + 1;
                sb.append("<li><a href=\"#\" onclick=\"queryEmployerJob(" + preFirst + ")\">«</a>");
            }
            int fIndex = ((int) ((pageIndex) / 14)) * 14 + 1;
            int lIndex = Math.min((fIndex + 14), totalCount);
            for (int i = fIndex; i < lIndex; i++) {
                if (i == pageIndex) {
                    sb.append("<li class=\"active\"><a href=\"#\" onclick=\"queryEmployerJob(" + i + ")\">" + i + "</a></li>");
                } else {
                    sb.append("<li><a href=\"#\" onclick=\"queryEmployerJob(" + i + ")\">" + i + "</a></li>");
                }
            }
            if ((fIndex + 13) < totalCount) {
                sb.append("<li><a href=\"#\" onclick=\"queryEmployerJob(" + (fIndex + 14) + ")\">»</a></li>");
            }
        }
        return sb.toString();
    }

    @At("/page/demo/job/jobdetail")
    public Map<String, ?> jobDetailRAction(@Param("jobId") Integer jobId) {

        log.debug("[jobDetailRAction] begin");

        try {

            Job Job = jobService.qryJobByJobId(jobId);

            if (!Lang.isEmpty(Job)) {
                setRetInfo(RetConstants.KEY_JOB_DETAIL, Job);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job by job id");
            }

        } catch (Exception e) {
            log.debug("jobDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobDetailRAction] end");

        return retMap;

    }

    @At("/page/demo/job/jobcomment")
    public Map<String, ?> jobCommentRAction(@Param("jobId") Integer jobId) {

        log.debug("[jobCommentRAction] begin");

        try {

            JobComment jobComment = jobService.qryJobCommentByJobId(jobId);

            if (!Lang.isEmpty(jobComment)) {
                setRetInfo(RetConstants.KEY_JOB_COMMENT_DETAIL, jobComment);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job comment by job id");
            }

        } catch (Exception e) {
            log.debug("jobCommentRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobCommentRAction] end");

        return retMap;

    }

    @At("/page/demo/job/modjobcomment")
    public Map<String, ?> jobCommentUpdateWAction(@Param("commentId") Integer commentId, @Param("jobId") Integer jobId, @Param("jobHunterId") String jobHunterId,
                                                  @Param("jobHunterName") String jobHunterName, @Param("jobHunterAvatarPath") String jobHunterAvatarPath,
                                                  @Param("jobComment") String jobComment, @Param("praiseThisComment") Integer praiseThisComment,
                                                  @Param("deprecateThisComment") Integer deprecateThisComment, HttpServletRequest request) {

        log.debug("[jobCommentUpdateWAction] begin");

        try {
            JobComment jobCommentT = null;
            jobCommentT = (JobComment) request.getSession().getAttribute("jobCommentT");
            if (jobCommentT == null) {
                jobCommentT = new JobComment();
                jobCommentT.setCommentId(commentId);
                jobCommentT.setJobId(jobId);
                jobCommentT.setJobHunterId(jobHunterId);
                jobCommentT.setJobHunterAvatarPath(jobHunterAvatarPath);
                jobCommentT.setJobHunterName(jobHunterName);
                jobCommentT.setJobComment(jobComment);
            }

            if (deprecateThisComment != null) {
                Boolean isDeprecate = (Boolean) request.getSession().getAttribute("deprecateComment" + commentId);
                if (isDeprecate == null) {
                    isDeprecate = false;
                }
                request.getSession().setAttribute("deprecateComment" + commentId, !isDeprecate);
                jobCommentT.setDeprecateThisComment(deprecateThisComment + (isDeprecate ? -1 : 1));
                jobService.modJobComment(jobCommentT, true);
            }

            if (praiseThisComment != null) {
                Boolean isPraise = (Boolean) request.getSession().getAttribute("praiseComment" + commentId);
                if (isPraise == null) {
                    isPraise = false;
                }
                request.getSession().setAttribute("praiseComment" + commentId, !isPraise);
                jobCommentT.setPraiseThisComment(praiseThisComment + (isPraise ? -1 : 1));
                jobService.modJobComment(jobCommentT, true);
            }

            setRetInfo(RetConstants.KEY_JOB_COMMENT_DETAIL, jobCommentT);

        } catch (Exception e) {
            log.debug("jobCommentUpdateWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobCommentUpdateWAction] end");

        return retMap;
    }

    @At("/page/demo/job/addjobcomment")
    public Map<String, ?> jobCommentWAction(@Param("jobId") Integer jobId, @Param("jobHunterId") String jobHunterId,
                                            @Param("jobHunterName") String jobHunterName, @Param("jobHunterAvatarPath") String jobHunterAvatarPath,
                                            @Param("jobComment") String jobComment, @Param("praiseThisComment") Integer praiseThisComment) {

        log.debug("[jobCommentWAction] begin");

        try {
            JobComment jobCommentT = new JobComment();
            jobCommentT.setJobId(jobId);
            jobCommentT.setJobHunterId(jobHunterId);
            jobCommentT.setJobHunterName(jobHunterName);
            jobCommentT.setJobHunterAvatarPath(jobHunterAvatarPath);
            jobCommentT.setJobComment(jobComment);
            jobCommentT.setCommentTime(DateUtil.getNowDate());
            jobCommentT.setPraiseThisComment(praiseThisComment);

            jobService.addJobComment(jobCommentT);

        } catch (Exception e) {
            log.debug("jobCommentWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobCommentWAction] end");

        return retMap;
    }

    @At("/page/demo/job/hunterfavoritejob")
    public Map<String, ?> hunterFavoriteJobRAction(@Param("jobHunterId") Integer jobHunterId,
                                                   @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[hunterFavoriteJobRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        try {
            int totalCount = jobService.countFavoriteJobsByPager(jobHunterId);

            setRetInfo(RetConstants.PAGINATION_INFO, getPaginationInfo(totalCount, pageIndex));

            List<Job> jobList = new ArrayList<Job>();

            List<JobHunterFavoriteJob> myFavoriteJobs = jobService.qryJobHunterFavoriteJobsByJobHunterId(jobHunterId, pageIndex, pageSize);

            for (JobHunterFavoriteJob myFavoriteJob : myFavoriteJobs) {
                Job job = jobService.qryJobByJobId(myFavoriteJob.getJobId());
                if (job != null) {
                    jobList.add(job);
                }
            }

            if (!Lang.isEmpty(jobList)) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
                setRetInfo(RetConstants.KEY_FAVORITE_LIST, myFavoriteJobs);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found favorite job");
            }

        } catch (Exception e) {
            log.debug("hunterFavoriteJobRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterFavoriteJobRAction] end");

        return retMap;
    }

    @At("/page/demo/job/hunterdiliverjob")
    public Map<String, ?> hunterDeliverJobRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[hunterDeliverJobRAction] begin");

        try {
            List<Job> jobList = new ArrayList<Job>();

            List<JobHunterFavoriteJob> myFavoriteJobs = jobService.qryJobHunterApplyJobsByJobHunterId(jobHunterId);

            for (JobHunterFavoriteJob myFavoriteJob : myFavoriteJobs) {
                Job job = jobService.qryJobByJobId(myFavoriteJob.getJobId());
                if (job != null) {
                    jobList.add(job);
                }
            }

            if (!Lang.isEmpty(jobList)) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
                setRetInfo(RetConstants.KEY_FAVORITE_LIST, myFavoriteJobs);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found favorite job");
            }

        } catch (Exception e) {
            log.debug("hunterDeliverJobRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterDeliverJobRAction] end");

        return retMap;
    }

    @At("/page/demo/job/addfavoritejob")
    public Map<String, ?> favoriteJobWAction(@Param("jobId") Integer jobId, @Param("jobHunterId") Integer jobHunterId) {

        log.debug("[favoriteJobWAction] begin");

        try {
            JobHunterFavoriteJob myFavoriteJob = new JobHunterFavoriteJob();
            myFavoriteJob.setJobId(jobId);
            myFavoriteJob.setJobHunterId(jobHunterId);
            myFavoriteJob.setFavorited(1);
            myFavoriteJob.setUpdateTime(DateUtil.getNowDate());
            JobHunterFavoriteJob jobHunterFavoriteJob = jobService.qryJobHunterFavoriteJobById(jobId, jobHunterId);
            if (jobHunterFavoriteJob != null) {
                myFavoriteJob.setMyFavoriteJobId(jobHunterFavoriteJob.getMyFavoriteJobId());
                jobService.modJobHunterFavoriteJobById(myFavoriteJob, true);
            } else {
                jobService.addJobHunterFavoriteJob(myFavoriteJob);
            }

        } catch (Exception e) {
            log.debug("favoriteJobWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[favoriteJobWAction] end");

        return retMap;
    }

    @At("/page/demo/job/removefavoritejob")
    public Map<String, ?> delFavoriteJobWAction(@Param("myFavoriteJobId") Integer myFavoriteJobId, @Param("jobId") Integer jobId, @Param("jobHunterId") Integer jobHunterId) {

        log.debug("[favoriteJobWAction] begin");

        try {
            JobHunterFavoriteJob myFavoriteJob = new JobHunterFavoriteJob();
            myFavoriteJob.setMyFavoriteJobId(myFavoriteJobId);
            myFavoriteJob.setJobId(jobId);
            myFavoriteJob.setJobHunterId(jobHunterId);
            jobService.delJobHunterFavoriteJob(myFavoriteJob);

        } catch (Exception e) {
            log.debug("favoriteJobWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[favoriteJobWAction] end");

        return retMap;
    }

    @At("/page/demo/job/removediliverjob")
    public Map<String, ?> deldiliverJobWAction(@Param("myFavoriteJobId") Integer myFavoriteJobId, @Param("jobId") Integer jobId, @Param("jobHunterId") Integer jobHunterId) {

        log.debug("[favoriteJobWAction] begin");

        try {
            JobHunterFavoriteJob myFavoriteJob = new JobHunterFavoriteJob();
            myFavoriteJob.setMyFavoriteJobId(myFavoriteJobId);
            myFavoriteJob.setJobId(jobId);
            myFavoriteJob.setJobHunterId(jobHunterId);
            jobService.delJobHunterDiliverJob(myFavoriteJob);

        } catch (Exception e) {
            log.debug("favoriteJobWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[favoriteJobWAction] end");

        return retMap;
    }

    /*
    实际上是查询收藏和投递状态
     */
    @At("/page/demo/job/favoritestatus")
    public Map<String, ?> isMyFavoriteJobRAction(@Param("jobId") Integer jobId, @Param("jobHunterId") Integer jobHunterId) {

        log.debug("[isMyFavoriteJobRAction] begin");

        try {
            JobHunterFavoriteJob jobHunterFavoriteJob = jobService.qryJobHunterFavoriteJobById(jobId, jobHunterId);
            if (jobHunterFavoriteJob != null) {
                setRetInfo(RetConstants.KEY_JOB_FAVORITE, jobHunterFavoriteJob);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "favorite job is null");
            }
        } catch (Exception e) {
            log.debug("isMyFavoriteJobRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[isMyFavoriteJobRAction] end");

        return retMap;
    }

    @At("/page/demo/job/employerjob")
    public Map<String, ?> jobEmployerRAction(@Param("employerId") Integer employerId,
                                             @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        log.debug("[jobEmployerRAction] begin");

        if (null == pageIndex) {
            pageIndex = 1;
        }

        if (null == pageSize) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        try {

            int totalCount = jobService.countJobsByPager(employerId);

            setRetInfo(RetConstants.PAGINATION_INFO, getEmployerPaginationInfo(totalCount, pageIndex));

            List<Job> jobList = jobService.qryJobsByPager(employerId, pageIndex, pageSize);

            if (jobList != null && jobList.size() > 0) {
                setRetInfo(RetConstants.KEY_JOB_LIST, jobList);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job list on employerid");
            }

        } catch (Exception e) {
            log.debug("jobEmployerRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobEmployerRAction] end");

        return retMap;
    }

    @At("/page/demo/job/jobcomments")
    public Map<String, ?> jobCommentsRAction(@Param("jobId") Integer jobId) {

        log.debug("[jobCommentsRAction] begin");

        try {
            List<JobComment> jobComments = jobService.qryJobCommentsByJobId(jobId);

            if (!Lang.isEmpty(jobComments)) {
                setRetInfo(RetConstants.KEY_JOB_COMMENT_LIST, jobComments);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found job comment list on jobId");
            }
        } catch (Exception e) {
            log.debug("jobCommentsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[jobCommentsRAction] end");

        return retMap;
    }

    @At("/page/demo/getaccount")
    public Map<String, Object> getAccountAction(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        setRetInfo("username", session.getAttribute("username"));
        setRetInfo("userType", session.getAttribute("userType"));
        setRetInfo("password", session.getAttribute("password"));
        setRetInfo("jobHunterId", session.getAttribute("jobHunterId"));
        setRetInfo("avatarPath", session.getAttribute("avatarPath"));
        setRetInfo("employerId", session.getAttribute("employerId"));
        setRetInfo("logoPath", session.getAttribute("logoPath"));
        setRetInfo("interactGot", session.getAttribute("interactGot"));
        setRetInfo("envelopeGot", session.getAttribute("envelopeGot"));

        return retMap;
    }

}
