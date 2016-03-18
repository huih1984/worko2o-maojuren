package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.JobHunterServiceApi;
import com.delta.worko2o.util.DateUtil;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.Daos;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.text.SimpleDateFormat;
import java.util.List;

@IocBean(name = "jobHunterService")
public class JobHunterServiceImpl implements JobHunterServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    public Integer addJobHunter(JobHunter jobHunter) {

        log.debug("[jobHunterService]-[addJobHunter]-begin");

        JobHunter jobHunterNew = dao.insert(jobHunter);

        log.debug("[jobHunterService]-[addJobHunter]-end");

        return jobHunterNew.getJobHunterId();
    }

    public Integer addJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience) {

        log.debug("[jobHunterService]-[addJobHunterEduExperience]-begin");

        JobHunterEduExperience jobHunterEduExperienceNew = dao.insert(jobHunterEduExperience);

        log.debug("[jobHunterService]-[addJobHunterEduExperience]-end");

        return jobHunterEduExperienceNew.getJobHunterEduExperienceId();
    }

    public Integer addJobHunterExpect(JobHunterExpect jobHunterExpect) {

        log.debug("[jobHunterService]-[addJobHunterExpect]-begin");

        JobHunterExpect jobHunterExpectNew = dao.insert(jobHunterExpect);

        log.debug("[jobHunterService]-[addJobHunterExpect]-end");

        return jobHunterExpectNew.getJobHunterExpectId();
    }

    public Integer addJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience) {

        log.debug("[jobHunterService]-[addJobHunterExpect]-begin");

        JobHunterProgramExperience jobHunterProgramExperienceNew = dao.insert(jobHunterProgramExperience);

        log.debug("[jobHunterService]-[addJobHunterExpect]-end");

        return jobHunterProgramExperienceNew.getJobHunterProgramExperienceId();
    }

    public Integer addJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience) {

        log.debug("[jobHunterService]-[addJobHunterWorkExperience]-begin");

        JobHunterWorkExperience jobHunterWorkExperienceNew = dao.insert(jobHunterWorkExperience);

        log.debug("[jobHunterService]-[addJobHunterWorkExperience]-end");

        return jobHunterWorkExperienceNew.getJobHunterWorkExperienceId();
    }

    @Override
    public Integer addJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience) {

        log.debug("[jobHunterService]-[addJobHunterTrainExperience]-begin");

        JobHunterTrainExperience jobHunterTrainExperienceNew = dao.insert(jobHunterTrainExperience);

        log.debug("[jobHunterService]-[addJobHunterTrainExperience]-end");

        return jobHunterTrainExperienceNew.getJobHunterTrainExperienceId();
    }

    public void modJobHunter(JobHunter jobHunter, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunter]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunter.class, true)).update(jobHunter);
        } else {
            dao.update(jobHunter);
        }

        log.debug("[jobHunterService]-[modJobHunter]-end");
    }

    public void modJobHunterByEmail(String jobHunterEmail, String jobHunterPassword) {

        log.debug("[employerService]-[modJobHunterByEmail]-begin");

        Chain chain = Chain.make("job_hunter_password", jobHunterPassword);
        Condition c = Cnd.where("job_hunter_email", "=", jobHunterEmail);
        dao.update("T_JOB_HUNTER", chain, c);

        log.debug("[employerService]-[modJobHunterByEmail]-end");
    }

    @Override
    public void modJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunterEduExperience]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunterEduExperience.class, true)).update(jobHunterEduExperience);
        } else {
            dao.update(jobHunterEduExperience);
        }

        log.debug("[jobHunterService]-[modJobHunterEduExperience]-end");
    }

    @Override
    public void modJobHunterExpect(JobHunterExpect jobHunterExpect, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunterExpect]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunterExpect.class, true)).update(jobHunterExpect);
        } else {
            dao.update(jobHunterExpect);
        }

        log.debug("[jobHunterService]-[modJobHunterExpect]-end");
    }

    @Override
    public void modJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunterProgramExperience]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunterProgramExperience.class, true)).update(jobHunterProgramExperience);
        } else {
            dao.update(jobHunterProgramExperience);
        }

        log.debug("[jobHunterService]-[modJobHunterProgramExperience]-end");
    }

    @Override
    public void modJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunterWorkExperience]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunterWorkExperience.class, true)).update(jobHunterWorkExperience);
        } else {
            dao.update(jobHunterWorkExperience);
        }

        log.debug("[jobHunterService]-[modJobHunterWorkExperience]-end");
    }

    @Override
    public void modJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience, boolean ignoreNull) {

        log.debug("[jobHunterService]-[modJobHunterTrainExperience]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobHunterTrainExperience.class, true)).update(jobHunterTrainExperience);
        } else {
            dao.update(jobHunterTrainExperience);
        }

        log.debug("[jobHunterService]-[modJobHunterTrainExperience]-end");
    }

    @Override
    public void delJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience) {

        log.debug("[jobHunterService]-[delJobHunterEduExperience]-begin");

        dao.delete(jobHunterEduExperience);

        log.debug("[jobHunterService]-[delJobHunterEduExperience]-end");
    }

    @Override
    public void delJobHunterExpect(JobHunterExpect jobHunterExpect) {
        log.debug("[jobHunterService]-[delJobHunterExpect]-begin");

        dao.delete(jobHunterExpect);

        log.debug("[jobHunterService]-[delJobHunterExpect]-end");
    }

    @Override
    public void delJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience) {
        log.debug("[jobHunterService]-[delJobHunterProgramExperience]-begin");

        dao.delete(jobHunterProgramExperience);

        log.debug("[jobHunterService]-[delJobHunterProgramExperience]-end");
    }

    @Override
    public void delJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience) {
        log.debug("[jobHunterService]-[delJobHunterWorkExperience]-begin");

        dao.delete(jobHunterWorkExperience);

        log.debug("[jobHunterService]-[delJobHunterWorkExperience]-end");
    }

    @Override
    public void delJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience) {
        log.debug("[jobHunterService]-[delJobHunterTrainExperience]-begin");

        dao.delete(jobHunterTrainExperience);

        log.debug("[jobHunterService]-[delJobHunterTrainExperience]-end");
    }

    public JobHunter qryJobHunterBySinaId(String sinaId) {

        log.debug("[jobHunterService]-[qryJobHunterBySinaId]-begin");

        Condition c = Cnd.where("sina_id", "=", sinaId);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterBySinaId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunter qryJobHunterByTencentId(String tencentId) {

        log.debug("[jobHunterService]-[qryJobHunterByTencentId]-begin");

        Condition c = Cnd.where("tencent_id", "=", tencentId);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterByTencentId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunter qryJobHunterByEmailAndPassword(String jobHunterEmail, String password) {

        log.debug("[jobHunterService]-[qryJobHunterByEmailAndPassword]-begin");

        Condition c = Cnd.where("job_hunter_email", "=", jobHunterEmail).and("job_hunter_password", "=", password);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterByEmailAndPassword]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunter qryJobHunter(Integer jobHunterId) {
        log.debug("[jobHunterService]-[qryJobHunter]-begin");

        JobHunter jobHunter = dao.fetch(JobHunter.class, jobHunterId);

        log.debug("[jobHunterService]-[qryJobHunter]-end");

        return jobHunter;
    }

    public JobHunterEduExperience qryJobHunterEduExperienceByJobHunterId(Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryJobHunterEduExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterEduExperience> tempList = dao.query(JobHunterEduExperience.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterEduExperienceByJobHunterId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunterExpect qryJobHunterExpectByJobHunterId(Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryJobHunterExpectByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterExpect> tempList = dao.query(JobHunterExpect.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterExpectByJobHunterId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunterProgramExperience qryJobHunterProgramExperienceByJobHunterId(Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryJobHunterProgramExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterProgramExperience> tempList = dao.query(JobHunterProgramExperience.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterProgramExperienceByJobHunterId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public JobHunterWorkExperience qryJobHunterWorkExperienceByJobHunterId(Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryJobHunterWorkExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterWorkExperience> tempList = dao.query(JobHunterWorkExperience.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterWorkExperienceByJobHunterId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<JobHunterEduExperience> qryAllJobHunterEduExperienceByJobHunterId(
            Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryAllJobHunterEduExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterEduExperience> tempList = dao.query(JobHunterEduExperience.class, c, null);

        log.debug("[jobHunterService]-[qryAllJobHunterEduExperienceByJobHunterId]-end");

        return tempList;
    }

    @Override
    public List<JobHunterExpect> qryAllJobHunterExpectByJobHunterId(
            Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryAllJobHunterExpectByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterExpect> tempList = dao.query(JobHunterExpect.class, c, null);

        log.debug("[jobHunterService]-[qryAllJobHunterExpectByJobHunterId]-end");

        return tempList;
    }

    @Override
    public List<JobHunterProgramExperience> qryAllJobHunterProgramExperienceByJobHunterId(
            Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryAllJobHunterProgramExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterProgramExperience> tempList = dao.query(JobHunterProgramExperience.class, c, null);

        log.debug("[jobHunterService]-[qryAllJobHunterProgramExperienceByJobHunterId]-end");

        return tempList;
    }

    @Override
    public List<JobHunterWorkExperience> qryAllJobHunterWorkExperienceByJobHunterId(
            Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryAllJobHunterWorkExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterWorkExperience> tempList = dao.query(JobHunterWorkExperience.class, c, null);

        log.debug("[jobHunterService]-[qryAllJobHunterWorkExperienceByJobHunterId]-end");

        return tempList;
    }

    @Override
    public JobHunter qryJobHunterByEmail(String jobHunterEmail) {

        log.debug("[jobHunterService]-[qryJobHunterByEmail]-begin");

        Condition c = Cnd.where("job_hunter_email", "=", jobHunterEmail);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunterByEmail]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<JobHunterTrainExperience> qryAllJobHunterTrainExperienceByJobHunterId(
            Integer jobHunterId) {

        log.debug("[jobHunterService]-[qryAllJobHunterTrainExperienceByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterTrainExperience> tempList = dao.query(JobHunterTrainExperience.class, c, null);

        log.debug("[jobHunterService]-[qryAllJobHunterTrainExperienceByJobHunterId]-end");

        return tempList;
    }

    @Override
    public int countJobHuntersByPager(String jobHunterBachelorDegree, String jobHunterStartWorkYear, String jobHunterSex, String jobHunterCurrentProvince,
                                      String jobHunterCurrentCity, String jobHunterCurrentDistrict, String jobHunterMajor,
                                      String jobHunterCurrentStatus, String updateTime, String keyword, String expectSalary) {
        log.debug("[jobService]-[countJobHuntersByPager]-begin");
        String[] kwds = null;
        if (keyword != null && keyword.length() != 0) {
            kwds = keyword.split(",");
            if (kwds.length == 1) {
                kwds = keyword.split("，");
            }
        }
        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobHunterBachelorDegree != null) {
            cri.where().andEquals("job_hunter_bachelor_degree", jobHunterBachelorDegree);
        }
        if (jobHunterStartWorkYear != null) {
            String contains = null;
            SimpleDateFormat dft = new SimpleDateFormat("yyyy");
            String currYear = dft.format(DateUtil.getNowDate());
            if (jobHunterStartWorkYear.contains("以下")) {
                contains = "'应届毕业生,在校生," + currYear + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", ">", "0"));
            } else if (jobHunterStartWorkYear.contains("以上")) {
                Integer val = Integer.valueOf(currYear);
                contains = "'应届毕业生,在校生,";
                for (int y = val; y > val - 5; --y) {
                    contains += y + ",";
                }
                contains += val - 5 + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", "=", "0"));
            } else {
                Integer val = Integer.valueOf(currYear);
                String[] tempStrs = jobHunterStartWorkYear.split("-");
                Integer beginYear = val - Integer.valueOf(tempStrs[0]);
                contains = "'" + beginYear + "," + (beginYear - 1) + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", ">", "0"));
            }
        }
        if (jobHunterSex != null) {
            cri.where().andEquals("job_hunter_sex", jobHunterSex);
        }
        if (jobHunterCurrentProvince != null && jobHunterCurrentProvince.length() != 0) {
            cri.where().andEquals("job_hunter_current_province", jobHunterCurrentProvince);
        }
        if (jobHunterCurrentCity != null && jobHunterCurrentCity.length() != 0) {
            cri.where().andEquals("job_hunter_current_city", jobHunterCurrentCity);
        }
        if (jobHunterCurrentDistrict != null && jobHunterCurrentDistrict.length() != 0) {
            cri.where().andEquals("job_hunter_current_district", jobHunterCurrentDistrict);
        }
        SqlExpressionGroup sqlExpressionGroup = null;
        String[] jobHunterMajors = null;
        if (jobHunterMajor != null && jobHunterMajor.length() != 0) {
            jobHunterMajors = jobHunterMajor.split(",");
            if (jobHunterMajors.length == 1) {
                jobHunterMajors = jobHunterMajor.split("，");
            }
        }

        if (jobHunterMajors != null) {
            if (jobHunterMajors.length == 1) {
                sqlExpressionGroup = Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[0] + "%");
            } else {
                sqlExpressionGroup = Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[0] + "%");
                for (int i = 1; i < jobHunterMajors.length; ++i) {
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[i] + "%"));
                }
            }
        }
        if (sqlExpressionGroup != null) {
            cri.where().and(sqlExpressionGroup);
        }

        sqlExpressionGroup = null;
        if (kwds != null) {
            sqlExpressionGroup = Cnd.exps("job_hunter_last_occupation", "like", "%" + kwds[0] + "%");
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_depict", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_name", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_college", "like", "%" + kwds[0] + "%"));
            if (kwds.length > 1) {
                for (int i = 1; i < kwds.length; ++i) {
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_last_occupation", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_depict", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_name", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_college", "like", "%" + kwds[i] + "%"));
                }
            }
        }
        if (jobHunterCurrentStatus != null && jobHunterCurrentStatus.length() != 0) {
            cri.where().andEquals("job_hunter_current_status", jobHunterCurrentStatus);
        }
        if (updateTime != null && updateTime.length() != 0) {
            if (updateTime.equals("三天以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(3)));
            } else if (updateTime.equals("一周以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(7)));
            } else if (updateTime.equals("一个月以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(30)));
            } else if (updateTime.equals("三个月以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(90)));
            }
        }

        if (expectSalary != null) {
            cri.where().andEquals("expect_salary", expectSalary);
        }
        int cnt = dao.count(JobHunter.class, cri);

        log.debug("[jobService]-[countJobHuntersByPager]-end");

        return cnt;
    }

    @Override
    public List<JobHunter> qryJobHunterByPager(String jobHunterBachelorDegree, String jobHunterStartWorkYear, String jobHunterSex, String jobHunterCurrentProvince,
                                               String jobHunterCurrentCity, String jobHunterCurrentDistrict, String jobHunterMajor,
                                               String jobHunterCurrentStatus, String updateTime, String keyword, String expectSalary, Integer pageIndex, Integer pageSize) {
        log.debug("[jobService]-[qryJobHunterByPager]-begin");
        String[] kwds = null;
        if (keyword != null && keyword.length() != 0) {
            kwds = keyword.split(",");
            if (kwds.length == 1) {
                kwds = keyword.split("，");
            }
        }
        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobHunterBachelorDegree != null) {
            cri.where().andEquals("job_hunter_bachelor_degree", jobHunterBachelorDegree);
        }
        if (jobHunterStartWorkYear != null) {
            String contains = null;
            SimpleDateFormat dft = new SimpleDateFormat("yyyy");
            String currYear = dft.format(DateUtil.getNowDate());
            if (jobHunterStartWorkYear.contains("以下")) {
                contains = "'应届毕业生,在校生," + currYear + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", ">", "0"));
            } else if (jobHunterStartWorkYear.contains("以上")) {
                Integer val = Integer.valueOf(currYear);
                contains = "'应届毕业生,在校生,";
                for (int y = val; y > val - 5; --y) {
                    contains += y + ",";
                }
                contains += val - 5 + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", "=", "0"));
            } else {
                Integer val = Integer.valueOf(currYear);
                String[] tempStrs = jobHunterStartWorkYear.split("-");
                Integer beginYear = val - Integer.valueOf(tempStrs[0]);
                contains = "'" + beginYear + "," + (beginYear - 1) + "'";
                cri.where().and(Cnd.exps("LOCATE(job_hunter_start_work_year," + contains + ")", ">", "0"));
            }
        }
        if (jobHunterSex != null) {
            if (!jobHunterSex.equals("不限")) {
                cri.where().andEquals("job_hunter_sex", jobHunterSex);
            }
        }

        if (jobHunterCurrentProvince != null && jobHunterCurrentProvince.length() != 0) {
            cri.where().andEquals("job_hunter_current_province", jobHunterCurrentProvince);
        }
        if (jobHunterCurrentCity != null && jobHunterCurrentCity.length() != 0) {
            cri.where().andEquals("job_hunter_current_city", jobHunterCurrentCity);
        }
        if (jobHunterCurrentDistrict != null && jobHunterCurrentDistrict.length() != 0) {
            cri.where().andEquals("job_hunter_current_district", jobHunterCurrentDistrict);
        }
        String[] jobHunterMajors = null;
        if (jobHunterMajor != null && jobHunterMajor.length() != 0) {
            jobHunterMajors = jobHunterMajor.split(",");
            if (jobHunterMajors.length == 1) {
                jobHunterMajors = jobHunterMajor.split("，");
            }
        }

        SqlExpressionGroup sqlExpressionGroup = null;
        if (jobHunterMajors != null) {
            if (jobHunterMajors.length == 1) {
                sqlExpressionGroup = Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[0] + "%");
            } else {
                sqlExpressionGroup = Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[0] + "%");
                for (int i = 1; i < jobHunterMajors.length; ++i) {
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + jobHunterMajors[i] + "%"));
                }
            }
        }
        if (sqlExpressionGroup != null) {
            cri.where().and(sqlExpressionGroup);
        }


        sqlExpressionGroup = null;
        if (kwds != null) {
            sqlExpressionGroup = Cnd.exps("job_hunter_last_occupation", "like", "%" + kwds[0] + "%");
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_depict", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_name", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + kwds[0] + "%"));
            sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_college", "like", "%" + kwds[0] + "%"));
            if (kwds.length > 1) {
                for (int i = 1; i < kwds.length; ++i) {
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_last_occupation", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_depict", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_name", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_major", "like", "%" + kwds[i] + "%"));
                    sqlExpressionGroup = sqlExpressionGroup.or(Cnd.exps("job_hunter_college", "like", "%" + kwds[i] + "%"));
                }
            }
        }
        if (sqlExpressionGroup != null) {
            cri.where().and(sqlExpressionGroup);
        }
        if (jobHunterCurrentStatus != null && jobHunterCurrentStatus.length() != 0) {
            cri.where().andEquals("job_hunter_current_status", jobHunterCurrentStatus);
        }
        if (updateTime != null && updateTime.length() != 0) {
            if (updateTime.equals("三天以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(3)));
            } else if (updateTime.equals("一周以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(7)));
            } else if (updateTime.equals("一个月以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(30)));
            } else if (updateTime.equals("三个月以内")) {
                cri.where().and(Cnd.exp("update_time", ">=", DateUtil.getNowBeforeDate(90)));
            }
        }

        if (expectSalary != null) {
            cri.where().andEquals("expect_salary", expectSalary);
        }
        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);
        List<JobHunter> tempList = dao.query(JobHunter.class, cri, pager);

        log.debug("[jobHunterService]-[qryJobHunterByPager]-end");

        return tempList;
    }

    @Override
    public List<JobHunter> qryJobHunters(String ids) {

        log.debug("[jobHunterService]-[qryJobHunters]-begin");

        Condition c = Cnd.where("job_hunter_id", "in", ids);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[jobHunterService]-[qryJobHunters]-end");

        return tempList;
    }

    @Override
    public jangoSession qryJangoSession(String sessionKey) {

        log.debug("[sessionService]-[qryJangoSession]-begin");

        jangoSession jangosession = dao.fetch(jangoSession.class, sessionKey);

        log.debug("[sessionService]-[qryJangoSession]-end");

        return jangosession;
    }

    @Override
    public void addJangoSession(jangoSession jangosession) {

        log.debug("[sessionService]-[addJangoSession]-begin");

        dao.insert(jangosession);

        log.debug("[sessionService]-[addJangoSession]-end");
    }

}
