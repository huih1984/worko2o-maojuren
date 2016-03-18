package com.delta.worko2o.action;

import java.util.List;
import java.util.Map;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.JobInteractServiceApi;
import com.delta.worko2o.service.api.JobServiceApi;
import com.delta.worko2o.util.DwrMsg;
import com.delta.worko2o.util.UserInfoListener;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.service.api.EmployerServiceApi;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <Description> <br>
 * 企业相关操作
 *
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月18日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class EmployerAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private EmployerServiceApi employerService;

    @Inject
    private JobServiceApi jobService;

    @Inject
    private JobInteractServiceApi jobInteractService;

    /**
     * Description: <br>
     * 企业注册
     */
    @At("/page/demo/employer/valid")
    public Map<String, Object> employerValidAction(
            @Param("hrEmail") String hrEmail) {

        log.debug("[employerValidAction] begin");
        try {

            Employer employer = employerService.qryEmployerByEmail(hrEmail);
            if (employer != null) {
                setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL, employer);
            } else {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found this account!");
            }
        } catch (Exception e) {
            log.debug("employerValidAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerValidAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业注册
     */
    @At("/page/demo/employer/register")
    public Map<String, Object> employerRegisterAction(
            @Param("sinaId") String sinaId,
            @Param("tencentId") String tencentId,
            @Param("weixinId") String weixinId,
            @Param("hrEmail") String hrEmail,
            @Param("password") String password,
            @Param("employerName") String employerName,
            @Param("tel") String tel,
            @Param("certificatePath") String certificatePath,
            @Param("identityCardPath") String identityCardPath, HttpServletRequest request) {

        log.debug("[employerRegisterAction] begin");
        DwrMsg.sendMsg("有新注册用户了！");
        try {
            // 构造EmployerRegister
            Employer employer = null;
            employer = (Employer) request.getSession().getAttribute("employer");
            if (employer == null) {
                employer = new Employer();
                employer.setSinaId(sinaId);
                employer.setTencentId(tencentId);
                employer.setWeixinId(weixinId);
                employer.setHrEmail(hrEmail);
                employer.setPassword(password);
                employer.setEmployerName(employerName);
                employer.setTel(tel);
                employer.setCertificatePath(certificatePath);
                //employer.setIdentityCardPath(identityCardPath);
                employer.setUpdateTime(DateUtil.getNowDate());
            } else {
                employer.setCertificatePath(certificatePath);
                //employer.setIdentityCardPath(identityCardPath);
                employer.setUpdateTime(DateUtil.getNowDate());
            }
            Integer employerId = employerService.addEmployer(employer);

            EmployerStore employerStore = new EmployerStore();
            employerStore.setEmployerId(employerId);
            employerService.addEmployerStore(employerStore);


            setRetInfo(RetConstants.KEY_LAST_INSERT_ID, employerId);
        } catch (Exception e) {
            log.debug("employerRegisterAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerRegisterAction] end");

        return retMap;
    }

    @At("/page/demo/employer/maxid")
    public Map<String, Object> employerMaxIdRAction() {
        log.debug("[employerMaxIdRAction] begin");

        try {
            Integer id = employerService.qryMaxEmployerId();
            setRetInfo(RetConstants.KEY_MAX_ID, id);
        } catch (Exception e) {
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerMaxIdRAction] end");
        return retMap;
    }

    /**
     * Description: <br>
     * 企业完善资料
     */
    @At("/page/demo/employer/profile")
    public Map<String, Object> employerProfileWAction(
            @Param("employerId") Integer employerId,
            @Param("logoPath") String logoPath,
            @Param("employerDesc") String employerDesc,
            @Param("address") String address,
            @Param("province") String province, @Param("city") String city,
            @Param("district") String district,
            @Param("employerType") String employerType,
            @Param("employerScale") String employerScale,
            @Param("saleType") String saleType,
            @Param("website") String website, @Param("tag1") String tag1,
            @Param("tag2") String tag2, @Param("tag3") String tag3,
            @Param("tag4") String tag4, @Param("tag5") String tag5,
            @Param("product1Pic") String product1Pic,
            @Param("product2Pic") String product2Pic,
            @Param("product3Pic") String product3Pic,
            @Param("product4Pic") String product4Pic,
            @Param("product5Pic") String product5Pic,
            @Param("taobaoStoreName") String taobaoStoreName,
            @Param("taobaoLevel") String taobaoLevel,
            @Param("lat") String lat,
            @Param("lng") String lng,
            @Param("resumeDownloadLimit") Integer resumeDownloadLimit,
            @Param("resumeHaveDownloadNum") Integer resumeHaveDownloadNum) {

        log.debug("[employerProfileWAction] begin");

        try {
            final Employer employer = new Employer();
            employer.setEmployerId(employerId);
            employer.setLogoPath(logoPath);
            employer.setEmployerDesc(employerDesc);
            employer.setAddress(address);
            employer.setProvince(province);
            employer.setCity(city);
            employer.setDistrict(district);
            employer.setEmployerType(employerType);
            employer.setEmployerScale(employerScale);
            employer.setSaleType(saleType);
            employer.setWebsite(website);
            employer.setTag1(tag1);
            employer.setTag2(tag2);
            employer.setTag3(tag3);
            employer.setTag4(tag4);
            employer.setTag5(tag5);
            employer.setProduct1Pic(product1Pic);
            employer.setProduct2Pic(product2Pic);
            employer.setProduct3Pic(product3Pic);
            employer.setProduct4Pic(product4Pic);
            employer.setProduct5Pic(product5Pic);
            employer.setResumeDownloadLimit(resumeDownloadLimit);
            employer.setResumeHaveDownloadNum(resumeHaveDownloadNum);
            employer.setStatus(1);
            employer.setLat(lat);
            employer.setLng(lng);
            employer.setUpdateTime(DateUtil.getNowDate());

            employerService.modEmployer(employer, true);
            //在js中进行修改了
//            EmployerStore employerStore = new EmployerStore();
//            employerStore.setEmployerId(employerId);
//            employerStore.setTaobaoLevel(taobaoLevel);
//            employerStore.setTaobaoStoreName(taobaoStoreName);
//            employerService.modEmployerStore(employerStore, true);
        } catch (Exception e) {
            log.debug("employerProfileWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerProfileWAction] end");

        return retMap;
    }

    @At("/page/demo/employer/thirdaccount")
    public Map<String, Object> employerThirdAccountWAction(
            @Param("employerId") Integer employerId,
            @Param("sinaId") String sinaId,
            @Param("weixinId") String weixinId,
            @Param("tencentId") String tencentId) {

        log.debug("[employerThirdAccountWAction] begin");

        try {
            final Employer employer = new Employer();
            employer.setEmployerId(employerId);
            employer.setSinaId(sinaId);
            employer.setWeixinId(weixinId);
            employer.setTencentId(tencentId);

            employerService.modEmployer(employer, true);

        } catch (Exception e) {
            log.debug("employerThirdAccountWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerThirdAccountWAction] end");

        return retMap;
    }

    @At("/page/demo/employer/store/add")
    public Map<String, Object> storeWAction(@Param("employerId") Integer employerId, @Param("taobaoStoreName") String taobaoStoreName,
                                            @Param("taobaoLevel") String taobaoLevel) {
        log.debug("[storeWAction] begin");

        try {
            final EmployerStore employerStore = new EmployerStore();
            employerStore.setEmployerId(employerId);
            employerStore.setTaobaoStoreName(taobaoStoreName);
            employerStore.setTaobaoLevel(taobaoLevel);

            employerService.addEmployerStore(employerStore);

        } catch (Exception e) {
            log.debug("storeWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[storeWAction] end");

        return retMap;
    }

    @At("/page/demo/employer/store/mod")
    public Map<String, Object> storeMAction(@Param("employerId") Integer employerId, @Param("taobaoStoreName") String taobaoStoreName,
                                            @Param("taobaoLevel") String taobaoLevel) {
        log.debug("[storeRAction] begin");

        try {
            final EmployerStore employerStore = new EmployerStore();
            employerStore.setEmployerId(employerId);
            employerStore.setTaobaoStoreName(taobaoStoreName);
            employerStore.setTaobaoLevel(taobaoLevel);

            employerService.modEmployerStore(employerStore, true);

        } catch (Exception e) {
            log.debug("storeRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[storeRAction] end");

        return retMap;
    }

    @At("/page/demo/employer/store/read")
    public Map<String, Object> storeRAction(
            @Param("employerId") Integer employerId) {

        log.debug("[storeRAction] begin");

        try {
            EmployerStore employerStore = employerService.qryEmployerStoreById(employerId);

            if (employerStore == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_STORE, employerStore);
            }
        } catch (Exception e) {
            log.debug("storeRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[storeRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业重置密码
     */
    @At("/page/demo/employer/resetpassword")
    public Map<String, Object> resetPasswordWAction(
            @Param("hrEmail") String hrEmail,
            @Param("oldPassword") String oldPassword,
            @Param("password") String password,
            HttpServletRequest request) {

        log.debug("[resetPasswordWAction] begin");

        try {
            if (oldPassword != null) {
                Employer employer = employerService.qryEmployerByEmail(hrEmail);
                if (!employer.getPassword().equalsIgnoreCase(oldPassword)) {
                    setRetCodeAndMsg(RetConstants.VAL_FAIL, "old password is incorrect!");
                    return retMap;
                }
            }
            employerService.modEmployerByEmail(hrEmail, password);
            request.getSession().setAttribute("password", password);

        } catch (Exception e) {
            log.debug("resetPasswordWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[resetPasswordWAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业登录
     */
    @At("/page/demo/employer/login")
    public Map<String, Object> employerLoginAction(
            @Param("sinaId") String sinaId,
            @Param("tencentId") String tencentId,
            @Param("hrEmail") String hrEmail, @Param("password") String password, HttpServletRequest request) {

        log.debug("[employerLoginAction] begin");
        HttpSession session = request.getSession();
        try {
            Employer employer = null;
            if (StringUtil.isNotEmpty(sinaId)) {
                employer = employerService
                        .qryEmployerBySinaId(sinaId);
            } else if (StringUtil.isNotEmpty(tencentId)) {
                employer = employerService
                        .qryEmployerByTencentId(tencentId);
            } else if (StringUtil.isNotEmpty(hrEmail)
                    && StringUtil.isNotEmpty(password)) {
                employer = employerService.qryEmployerByEmailAndPassword(
                        hrEmail, password);
            }

            if (employer == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "can not found employer");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL,
                        employer);
                session.setAttribute("userType", "employer");
                session.setAttribute("username", employer.getHrEmail());
                session.setAttribute("password", employer.getPassword());
                session.setAttribute("employerId", employer.getEmployerId());
                session.setAttribute("logoPath", employer.getLogoPath());
                session.setAttribute("employerName", employer.getEmployerName());
                if (session.getAttribute("userInfo") == null) {
                    UserInfoListener userInfoListener = new UserInfoListener();
                    userInfoListener.userInfo.setUserType(1);
                    userInfoListener.userInfo.setUserName(employer.getHrEmail());
                    userInfoListener.userInfo.setUserId(employer.getEmployerId());
                    session.setAttribute("userInfo", userInfoListener);
                }
            }

        } catch (Exception e) {
            log.debug("employerLoginAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerLoginAction] end");

        return retMap;
    }

    @At("/page/demo/employer/logout")
    public Map<String, Object> employerLogoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("username");
        session.removeAttribute("userType");
        session.removeAttribute("password");
        session.removeAttribute("employerId");
        session.removeAttribute("logoPath");
        session.removeAttribute("employerName");
        session.removeAttribute("interactGot");
        session.removeAttribute("envelopeGot");
        return retMap;
    }

    /**
     * Description: <br>
     * 企业详情
     */
    @At("/page/demo/employer/employerdetail")
    public Map<String, Object> employerDetailRAction(
            @Param("employerId") Integer employerId) {

        log.debug("[employerDetailRAction] begin");

        try {
            Employer employer = employerService.qryEmployerById(employerId);

            if (employer == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL, employer);
            }
        } catch (Exception e) {
            log.debug("employerDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerDetailRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业详情
     */
    @At("/page/demo/employer/employerdetails")
    public Map<String, Object> employerDetailsRAction(
            @Param("employerIds") String employerIds) {

        log.debug("[employerDetailRAction] begin");

        try {
            List<Employer> employers = employerService.qryEmployers(employerIds);

            if (employers == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_DETAIL, employers);
            }
        } catch (Exception e) {
            log.debug("employerDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerDetailRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/favorite")
    public Map<String, Object> employersFavoriteRAction(@Param("employerId") Integer employerId, @Param("jobHunterId") Integer jobHunterId) {

        log.debug("[employersFavoriteRAction] begin");

        try {
            EmployerFavoriteHunter employerFavoriteHunter = employerService.qryEmployerFavorite(employerId, jobHunterId);

            if (employerFavoriteHunter == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_FAVORITE, employerFavoriteHunter);
            }
        } catch (Exception e) {
            log.debug("employersFavoriteRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employersFavoriteRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/allfavorites")
    public Map<String, Object> employersAllFavoriteRAction(@Param("employerId") Integer employerId) {

        log.debug("[employersFavoriteRAction] begin");

        try {
            List<EmployerFavoriteHunter> employerFavoriteHunters = employerService.qryEmployerAllFavorites(employerId);
            List<JobHunter> jobHunters = null;
            if (employerFavoriteHunters != null) {
                jobHunters = employerService.qryFavoriteHunterByIds(employerFavoriteHunters);
            }
            if (jobHunters == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found hunters by id");
            } else {
                setRetInfo(RetConstants.KEY_JOB_HUNTER_LIST, jobHunters);
                setRetInfo(RetConstants.KEY_EMPLOYER_FAVORITE, employerFavoriteHunters);
            }
        } catch (Exception e) {
            log.debug("employersFavoriteRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employersFavoriteRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/addfavorite")
    public Map<String, Object> employersFavoriteWAction(@Param("employerId") Integer employerId, @Param("jobHunterId") Integer jobHunterId,
                                                        @Param("favorited") Integer favorited, @Param("downloaded") Integer downloaded) {

        log.debug("[employersFavoriteWAction] begin");

        try {
            EmployerFavoriteHunter employerFavoriteHunter = new EmployerFavoriteHunter();
            employerFavoriteHunter.setEmployerId(employerId);
            employerFavoriteHunter.setJobHunterId(jobHunterId);
            employerFavoriteHunter.setFavorited(favorited);
            employerFavoriteHunter.setDownloaded(downloaded);
            employerFavoriteHunter.setUpdateTime(DateUtil.getNowDate());
            employerService.addEmployerFavorite(employerFavoriteHunter);
        } catch (Exception e) {
            log.debug("employersFavoriteWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employersFavoriteWAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/modfavorite")
    public Map<String, Object> employersFavoriteMAction(@Param("employerFavoriteHunterId") Integer employerFavoriteHunterId,
                                                        @Param("employerId") Integer employerId, @Param("jobHunterId") Integer jobHunterId,
                                                        @Param("favorited") Integer favorited, @Param("downloaded") Integer downloaded) {

        log.debug("[employersFavoriteWAction] begin");

        try {
            EmployerFavoriteHunter employerFavoriteHunter = new EmployerFavoriteHunter();
            employerFavoriteHunter.setEmployerFavoriteHunterId(employerFavoriteHunterId);
            employerFavoriteHunter.setEmployerId(employerId);
            employerFavoriteHunter.setJobHunterId(jobHunterId);
            employerFavoriteHunter.setFavorited(favorited);
            employerFavoriteHunter.setDownloaded(downloaded);
            employerFavoriteHunter.setUpdateTime(DateUtil.getNowDate());
            employerService.modEmployerFavorite(employerFavoriteHunter, true);
        } catch (Exception e) {
            log.debug("employersFavoriteWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employersFavoriteWAction] end");

        return retMap;
    }


    /**
     * Description: <br>
     */
    @At("/page/demo/employer/removefavorite")
    public Map<String, Object> employersFavoriteDAction(@Param("employerFavoriteHunterId") Integer employerFavoriteHunterId) {

        log.debug("[employersFavoriteDAction] begin");

        try {
            EmployerFavoriteHunter employerFavoriteHunter = new EmployerFavoriteHunter();
            employerFavoriteHunter.setEmployerFavoriteHunterId(employerFavoriteHunterId);
            employerService.removeEmployerFavorite(employerFavoriteHunter);
        } catch (Exception e) {
            log.debug("employersFavoriteDAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employersFavoriteDAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业详情
     */
    @At("/page/demo/employer/hotemployers")
    public Map<String, Object> hotEmployersDetailRAction() {

        log.debug("[hotEmployersDetailRAction] begin");

        try {
            List<EmployerHot> employerHots = employerService.qryHotEmployers();

            if (employerHots == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_HOT, employerHots);
            }
        } catch (Exception e) {
            log.debug("hotEmployersDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hotEmployersDetailRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     * 企业详情
     */
    @At("/page/demo/employer/singlehot")
    public Map<String, Object> hotEmployersDetailRAction(@Param("employerId") Integer employerId) {

        log.debug("[hotEmployersDetailRAction] begin");

        try {
            EmployerHot employerHot = employerService.qryHotEmployerByEmployerId(employerId);

            if (employerHot == null) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found employer by id");
            } else {
                setRetInfo(RetConstants.KEY_EMPLOYER_HOT, employerHot);
            }
        } catch (Exception e) {
            log.debug("hotEmployersDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hotEmployersDetailRAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/addtimes")
    public Map<String, Object> employerHotWAction(@Param("employerId") Integer employerId, @Param("employerName") String employerName,
                                                  @Param("logoPath") String logoPath, @Param("jobId") Integer jobId) {

        log.debug("[employerHotWAction] begin");

        try {
            EmployerHot employerHot = employerService.qryHotEmployerByEmployerId(employerId);
            if (employerHot == null) {
                employerHot = new EmployerHot();
                employerHot.setEmployerId(employerId);
                employerHot.setEmployerName(employerName);
                employerHot.setLogoPath(logoPath);
                if (DateUtil.getDay() == 1) {
                    employerHot.setDay1times(1);
                } else if (DateUtil.getDay() == 2) {
                    employerHot.setDay2times(1);
                } else if (DateUtil.getDay() == 3) {
                    employerHot.setDay3times(1);
                } else if (DateUtil.getDay() == 4) {
                    employerHot.setDay4times(1);
                } else if (DateUtil.getDay() == 5) {
                    employerHot.setDay5times(1);
                } else if (DateUtil.getDay() == 6) {
                    employerHot.setDay6times(1);
                } else if (DateUtil.getDay() == 7) {
                    employerHot.setDay7times(1);
                }
                employerService.addHotEmployer(employerHot);
            } else {
                if (DateUtil.getDay() == 1) {
                    employerHot.setDay1times(employerHot.getDay1times() == null ? 1 : employerHot.getDay1times() + 1);
                } else if (DateUtil.getDay() == 2) {
                    employerHot.setDay2times(employerHot.getDay2times() == null ? 1 : employerHot.getDay2times() + 1);
                } else if (DateUtil.getDay() == 3) {
                    employerHot.setDay3times(employerHot.getDay3times() == null ? 1 : employerHot.getDay3times() + 1);
                } else if (DateUtil.getDay() == 4) {
                    employerHot.setDay4times(employerHot.getDay4times() == null ? 1 : employerHot.getDay4times() + 1);
                } else if (DateUtil.getDay() == 5) {
                    employerHot.setDay5times(employerHot.getDay5times() == null ? 1 : employerHot.getDay5times() + 1);
                } else if (DateUtil.getDay() == 6) {
                    employerHot.setDay6times(employerHot.getDay6times() == null ? 1 : employerHot.getDay6times() + 1);
                } else if (DateUtil.getDay() == 7) {
                    employerHot.setDay7times(employerHot.getDay7times() == null ? 1 : employerHot.getDay7times() + 1);
                }
                employerService.modHotEmployer(employerHot, true);
            }
            Job job = new Job();
            job.setJobId(jobId);
            jobService.modJobTempReadTimes(jobId);
        } catch (Exception e) {
            log.debug("employerHotWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[employerHotWAction] end");

        return retMap;
    }


    /**
     * Description: <br>
     */
    @At("/page/demo/employer/hunterjoin")
    public Map<String, Object> hunterJoinWAction(@Param("jobInteractId") Integer jobInteractId, @Param("jobId") Integer jobId,
                                                 @Param("jobHunterId") Integer jobHunterId, @Param("employerId") Integer employerId,
                                                 @Param("haveJoin") Integer haveJoin) {

        log.debug("[hunterJoinWAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setJobInteractId(jobInteractId);
            jobInteract.setJoinFeedback(1);
            jobInteract.setEmployerUpdateTime(DateUtil.getNowDate());
            jobInteractService.modJobInteract(jobInteract, true);

            JobHunterCredit jobHunterCredit = new JobHunterCredit();
            jobHunterCredit.setHaveJoin(haveJoin);
            jobHunterCredit.setJobHunterId(jobHunterId);
            jobHunterCredit.setJobId(jobId);
            employerService.addJobHunterCredit(jobHunterCredit);
            employerService.modEmployerCredit(employerId);
        } catch (Exception e) {
            log.debug("hunterJoinWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterJoinWAction] end");

        return retMap;
    }

    /**
     * Description: <br>
     */
    @At("/page/demo/employer/hunteraccept")
    public Map<String, Object> hunterAcceptWAction(@Param("jobInteractId") Integer jobInteractId, @Param("jobId") Integer jobId,
                                                   @Param("jobHunterId") Integer jobHunterId, @Param("employerId") Integer employerId,
                                                   @Param("haveAccept") Integer haveAccept) {

        log.debug("[hunterAcceptWAction] begin");

        try {
            JobInteract jobInteract = new JobInteract();
            jobInteract.setJobInteractId(jobInteractId);
            jobInteract.setAcceptFeedback(1);
            jobInteract.setEmployerUpdateTime(DateUtil.getNowDate());
            jobInteractService.modJobInteract(jobInteract, true);

            JobHunterCredit jobHunterCredit = new JobHunterCredit();
            jobHunterCredit.setHaveAccept(haveAccept);
            jobHunterCredit.setJobHunterId(jobHunterId);
            jobHunterCredit.setJobId(jobId);
            employerService.modJobHunterCredit(jobHunterCredit, true);
            employerService.modEmployerCredit(employerId);
        } catch (Exception e) {
            log.debug("hunterAcceptWAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterAcceptWAction] end");

        return retMap;
    }

    @At("/page/demo/employer/huntercredit")
    public Map<String, Object> hunterCreditRAction(@Param("jobHunterId") Integer jobHunterId) {

        log.debug("[hunterCreditRAction] begin");

        try {
            List<JobHunterCredit> jobHunterCredits = employerService.qryJobHunterCredit(jobHunterId);
            setRetInfo("job_hunter_credit", jobHunterCredits);
        } catch (Exception e) {
            log.debug("hunterCreditRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[hunterCreditRAction] end");

        return retMap;
    }
}
