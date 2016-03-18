package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.JobServiceApi;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.StringUtil;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

@IocBean(name = "jobService")
public class JobServiceImpl implements JobServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    public void addJobTemp(JobTemp jobTemp) {

        log.debug("[jobService]-[addJobTemp]-begin");

        dao.insert(jobTemp);

        log.debug("[jobService]-[addJobTemp]-end");
    }

    @Override
    public void modJobTemp(JobTemp jobTemp, boolean ignoreNull) {
        log.debug("[jobService]-[modJobTemp]-begin");

        // 过滤空字段
//        if (ignoreNull) {
        Daos.ext(dao, FieldFilter.create(JobTemp.class, ignoreNull)).update(jobTemp);
//        } else {
//            dao.update(jobTemp);
//        }

        log.debug("[jobService]-[modJobTemp]-end");
    }

    @Override
    public void modJobTempReadTimes(Integer jobId) {
        log.debug("[jobService]-[modJobTempReadTimes]-begin");

        String sqlStr = "update T_JOB_TEMP set read_times = read_times + 1, status = 3 where job_id = " + jobId;
        Sql sql = Sqls.create(sqlStr);
        dao.execute(sql);

        log.debug("[jobService]-[modJobTempReadTimes]-end");
    }

    @Override
    public void deleteJobTemp(JobTemp jobTemp) {

        log.debug("[jobService]-[deleteJobTemp]-begin");

        dao.delete(jobTemp);

        log.debug("[jobService]-[deleteJobTemp]-end");
    }

    public List<Job> qryJobsOrderByUpdateTimeAndLimit(Integer limitNum) {

        log.debug("[jobService]-[qryJobsOrderByUpdateTimeAndLimit]-begin");

        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("update_time");
        Pager pager = dao.createPager(1, limitNum);

        List<Job> tempList = dao.query(Job.class, cri, pager);

        log.debug("[jobService]-[qryJobsOrderByUpdateTimeAndLimit]-end");

        return tempList;
    }


    @Override
    public int countJobsByPager(String workplace, String jobMainType, Integer salaryBegin, Integer salaryEnd,
                                String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer redu) {
        log.debug("[jobService]-[countJobsByPager]-begin");

        Criteria cri = getCondition(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);

        log.debug("[jobService]-[countJobsByPager]-end");

        return dao.count(Job.class, cri);
    }

    @Override
    public List<Job> qryJobsByPager(String workplace, String jobMainType, Integer salaryBegin, Integer salaryEnd,
                                    String saleType, String workPattern, String employerScale,
                                    String keyWord, String keyWordType, String eduReq, String expReq, Integer pageIndex, Integer pageSize, Integer redu) {

        log.debug("[jobService]-[qryJobsByPager]-begin");

        Criteria cri = getCondition(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);

        if (redu == null) {
            if (salaryBegin != null) {
                cri.getOrderBy().desc("salary_begin");
            } else {
                cri.getOrderBy().desc("update_time");
            }
        } else {
            cri.getOrderBy().desc("read_times");
        }
        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);

        log.debug("[jobService]-[qryJobsByPager]-end");

        return dao.query(Job.class, cri, pager);
    }

    @Override
    public int countJobsMainPageByPager(String workplace, String jobMainType, Integer salaryBegin, Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer redu) {
        log.debug("[jobService]-[countJobsMainPageByPager]-begin");

        Criteria cri = getCondition(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);
        cri.where().andEquals("main_page", "1");
        log.debug("[jobService]-[countJobsMainPageByPager]-end");

        return dao.count(Job.class, cri);
    }

    @Override
    public List<Job> qryJobsMainPageByPager(String workplace, String jobMainType, Integer salaryBegin, Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer pageIndex, Integer pageSize, Integer redu) {

        log.debug("[jobService]-[qryJobsMainPageByPager]-begin");

        Criteria cri = getCondition(workplace, jobMainType, salaryBegin, salaryEnd, saleType, workPattern, employerScale, keyWord, keyWordType, eduReq, expReq, redu);
        cri.where().andEquals("main_page", "1");
        if (redu == null) {
            if (salaryBegin != null) {
                cri.getOrderBy().desc("salary_begin");
            } else {
                cri.getOrderBy().desc("update_time");
            }
        } else {
            cri.getOrderBy().desc("read_times");
        }
        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);

        log.debug("[jobService]-[qryJobsMainPageByPager]-end");

        return dao.query(Job.class, cri, pager);
    }

    private Criteria getCondition(String workplace, String jobMainType, Integer salaryBegin, Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord,
                                  String keyWordType, String eduReq, String expReq, Integer redu) {
        // 拼装sql
        Criteria cri = Cnd.cri();


        if (!StringUtil.isEmpty(workplace)) {
            cri.where().andEquals("city", workplace);
        }
        if (!Lang.isEmpty(jobMainType)) {
            cri.where().and(Cnd.exps("job_main_type",
                    "like",
                    "%" + jobMainType + "%").or("job_sub_type", "like", "%" + jobMainType + "%"));
        }
        /*if (!Lang.isEmpty(salaryBegin)) {
            cri.where().andEquals("salary", salaryBegin);
        }*/
        if (StringUtil.isNotEmpty(keyWord)) {
            if ("2".equals(keyWordType)) {
                //cri.where().and(Cnd.exps("employer_name",
                //        "like",
                //        "%" + keyWord + "%").or("job_desc", "like", "%" + keyWord + "%"));
                cri.where().and(Cnd.exps("employer_name",
                        "like",
                        "%" + keyWord + "%"));
            } else {
                //cri.where().and(Cnd.exps("job_name",
                //        "like",
                //        "%" + keyWord + "%").or("job_desc", "like", "%" + keyWord + "%"));
                cri.where().and(Cnd.exps("job_name",
                        "like",
                        "%" + keyWord + "%").or("job_main_type", "like", "%" + keyWord + "%")
                        .or("job_sub_type", "like", "%" + keyWord + "%"));
            }
        }
        if (!StringUtil.isEmpty(saleType)) {
            cri.where().andEquals("sale_type", saleType);
        }
        if (!StringUtil.isEmpty(workPattern)) {
            cri.where().andEquals("work_pattern", workPattern);
        }
        if (!StringUtil.isEmpty(employerScale)) {
            cri.where().andEquals("employer_scale", employerScale);
        }
        if (!StringUtil.isEmpty(eduReq)) {
            cri.where().andEquals("edu_req", eduReq);
        }
        if (!StringUtil.isEmpty(expReq)) {
            cri.where().andEquals("exp_req", expReq);
        }
        if (salaryBegin != null && salaryEnd == null) {
            cri.where().and(Cnd.exps("salary_end", ">=", salaryBegin));
        } else if (salaryBegin == null && salaryEnd != null) {
            cri.where().and(Cnd.exps("salary_begin", "<=", salaryEnd));
        } else if (salaryBegin != null && salaryEnd != null) {
            cri.where().and((Cnd.exps("salary_begin", ">=", salaryBegin).and("salary_begin", "<=", salaryEnd))
                    .or((Cnd.exps("salary_begin", "<=", salaryBegin).and("salary_end", ">=", salaryBegin))
                    ));
        }
        cri.where().and("dead_time", ">", DateUtil.getNowDate());
        cri.where().and("preach_job", "=", 0);
        return cri;
    }

    @Override
    public Job qryJobByJobId(Integer jobId) {

        log.debug("[jobService]-[qryJobByJobId]-begin");

        Job job = dao.fetch(Job.class, jobId);

        log.debug("[jobService]-[qryJobByJobId]-end");

        return job;

    }

    @Override
    public JobComment qryJobCommentByJobId(Integer jobId) {

        log.debug("[jobService]-[qryJobByJobId]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_id", jobId);

        List<JobComment> tempList = dao.query(JobComment.class, cri, null);

        log.debug("[jobService]-[qryJobByJobId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public void addJobComment(JobComment jobComment) {

        log.debug("[jobService]-[addJobComment]-begin");
        dao.insert(jobComment);

        JobTemp jobTemp = new JobTemp();
        jobTemp.setJobId(jobComment.getJobId());
        jobTemp.setJobCommentCnt(jobTemp.getJobCommentCnt() + 1);
        jobTemp.setStatus(3);
        modJobTemp(jobTemp, true);

        log.debug("[jobService]-[addJobComment]-end");
    }

    @Override
    public void modJobComment(JobComment jobComment, boolean ignoreNull) {
        log.debug("[jobService]-[modJobComment]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobComment.class, true)).update(jobComment);
        } else {
            dao.update(jobComment);
        }

        log.debug("[jobService]-[modJobComment]-end");
    }

    @Override
    public List<JobHunterFavoriteJob> qryJobHunterFavoriteJobsByJobHunterId(Integer jobHunterId, Integer pageIndex, Integer pageSize) {

        log.debug("[jobService]-[qryJobHunterFavoriteJobsByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId).and("favorited", "=", 1);
        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);

        List<JobHunterFavoriteJob> tempList = dao.query(JobHunterFavoriteJob.class, c, pager);

        log.debug("[jobService]-[qryJobHunterFavoriteJobsByJobHunterId]-end");

        return tempList;
    }

    @Override
    public List<JobHunterFavoriteJob> qryJobHunterApplyJobsByJobHunterId(Integer jobHunterId) {

        log.debug("[jobService]-[qryJobHunterApplyJobsByJobHunterId]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId).and("diliver", "=", 1);

        List<JobHunterFavoriteJob> tempList = dao.query(JobHunterFavoriteJob.class, c);

        log.debug("[jobService]-[qryJobHunterApplyJobsByJobHunterId]-end");

        return tempList;

    }

    @Override
    public JobHunterFavoriteJob qryJobHunterFavoriteJobById(Integer jobId, Integer jobHunterId) {

        log.debug("[jobService]-[qryJobHunterFavoriteJobById]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_id", jobId);
        cri.where().andEquals("job_hunter_id", jobHunterId);

        List<JobHunterFavoriteJob> tempList = dao.query(JobHunterFavoriteJob.class, cri, null);

        log.debug("[jobService]-[qryJobHunterFavoriteJobById]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public void modJobHunterFavoriteJobById(JobHunterFavoriteJob jobHunterFavoriteJob, boolean ignoreNull) {
        log.debug("[jobService]-[modJobHunterFavoriteJobById]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobInteract.class, true)).update(jobHunterFavoriteJob);
        } else {
            dao.update(jobHunterFavoriteJob);
        }

        log.debug("[jobService]-[modJobHunterFavoriteJobById]-end");
    }

    @Override
    public void addJobHunterFavoriteJob(JobHunterFavoriteJob jobHunterFavoriteJob) {

        log.debug("[jobService]-[addJobHunterFavoriteJob]-begin");

        dao.insert(jobHunterFavoriteJob);

        log.debug("[jobService]-[addJobHunterFavoriteJob]-end");
    }

    @Override
    public void delJobHunterFavoriteJob(JobHunterFavoriteJob jobHunterFavoriteJob) {

        log.debug("[jobService]-[delJobHunterFavoriteJob]-begin");

        //不是真的删除，是将值置为0

        JobHunterFavoriteJob jobHunterFavoriteJob1 = new JobHunterFavoriteJob();

        jobHunterFavoriteJob1.setMyFavoriteJobId(jobHunterFavoriteJob.getMyFavoriteJobId());
        jobHunterFavoriteJob1.setFavorited(0);
        Daos.ext(dao, FieldFilter.create(JobInteract.class, true)).update(jobHunterFavoriteJob1);

        log.debug("[jobService]-[delJobHunterFavoriteJob]-end");
    }

    @Override
    public void delJobHunterDiliverJob(JobHunterFavoriteJob jobHunterFavoriteJob) {

        log.debug("[jobService]-[delJobHunterDiliverJob]-begin");

        //不是真的删除，是将值置为0

        JobHunterFavoriteJob jobHunterFavoriteJob1 = new JobHunterFavoriteJob();

        jobHunterFavoriteJob1.setMyFavoriteJobId(jobHunterFavoriteJob.getMyFavoriteJobId());
        jobHunterFavoriteJob1.setDiliver(0);
        Daos.ext(dao, FieldFilter.create(JobHunterFavoriteJob.class, true)).update(jobHunterFavoriteJob1);

        log.debug("[jobService]-[delJobHunterDiliverJob]-end");
    }

    @Override
    public int countHunterJobsByPager(Integer jobHunterId, Integer jobId) {

        log.debug("[jobService]-[countHunterJobsByPager]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobId != null) {
            cri.where().andEquals("job_id", jobId);
        }
        cri.where().andEquals("job_hunter_id", jobHunterId);
        cri.where().andNotEquals("hunter_status", 4);
        int cnt = dao.count(JobInteract.class, cri);

        log.debug("[jobService]-[countHunterJobsByPager]-end");

        return cnt;
    }

    @Override
    public int countFavoriteJobsByPager(Integer jobHunterId) {

        log.debug("[jobService]-[countFavoriteJobsByPager]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_hunter_id", jobHunterId).and("favorited", "=", 1);
        int cnt = dao.count(JobHunterFavoriteJob.class, cri);

        log.debug("[jobService]-[countFavoriteJobsByPager]-end");

        return cnt;
    }

    @Override
    public int countJobsByPager(Integer employerId) {

        log.debug("[jobService]-[countJobsByPager employerId]-begin");

        Condition c = Cnd.where("employer_id", "=", employerId);
        int count = dao.count(Job.class, c);

        log.debug("[jobService]-[countJobsByPager employerId]-end");

        return count;
    }

    @Override
    public List<Job> qryJobsByPager(Integer employerId,
                                    Integer pageIndex, Integer pageSize) {

        log.debug("[jobService]-[qryJobsByPager employerId]-begin");

        Condition c = Cnd.where("employer_id", "=", employerId);

        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);

        List<Job> jobs = dao.query(Job.class, c, pager);

        log.debug("[jobService]-[qryJobsByPager employerId]-end");

        return jobs;
    }

    @Override
    public List<JobComment> qryJobCommentsByJobId(Integer jobId) {

        log.debug("[jobService]-[qryJobCommentsByJobId jobId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_id", jobId);
        cri.getOrderBy().desc("comment_time");

        List<JobComment> jobComments = dao.query(JobComment.class, cri, null);

        log.debug("[jobService]-[qryJobCommentsByJobId jobId]-end");

        return jobComments;
    }

}
