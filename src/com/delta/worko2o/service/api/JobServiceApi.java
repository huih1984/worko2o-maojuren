package com.delta.worko2o.service.api;

import com.delta.worko2o.model.*;

import java.util.List;

public interface JobServiceApi {

    void addJobTemp(JobTemp jobTemp);

    void modJobTemp(JobTemp jobTemp, boolean ignoreNull);

    void modJobTempReadTimes(Integer jobId);

    void deleteJobTemp(JobTemp jobTemp);

    List<Job> qryJobsOrderByUpdateTimeAndLimit(Integer limitNum);

    int countJobsByPager(String workplace, String jobMainType, Integer salaryBegin,
                         Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer redu);

    int countJobsMainPageByPager(String workplace, String jobMainType, Integer salaryBegin,
                                 Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer redu);

    List<Job> qryJobsByPager(String workplace, String jobMainType, Integer salaryBegin,
                             Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer pageIndex, Integer pageSize, Integer redu);

    List<Job> qryJobsMainPageByPager(String workplace, String jobMainType, Integer salaryBegin,
                                     Integer salaryEnd, String saleType, String workPattern, String employerScale, String keyWord, String keyWordType, String eduReq, String expReq, Integer pageIndex, Integer pageSize, Integer redu);

    Job qryJobByJobId(Integer jobId);

    JobComment qryJobCommentByJobId(Integer jobId);

    List<JobComment> qryJobCommentsByJobId(Integer jobId);

    void addJobComment(JobComment jobComment);

    void modJobComment(JobComment jobComment, boolean ignoreNull);

    List<JobHunterFavoriteJob> qryJobHunterFavoriteJobsByJobHunterId(Integer jobHunterId, Integer pageIndex, Integer pageSize);

    List<JobHunterFavoriteJob> qryJobHunterApplyJobsByJobHunterId(Integer jobHunterId);

    JobHunterFavoriteJob qryJobHunterFavoriteJobById(Integer jobId, Integer jobHunterId);

    void modJobHunterFavoriteJobById(JobHunterFavoriteJob jobHunterFavoriteJob, boolean ignoreNull);

    void addJobHunterFavoriteJob(JobHunterFavoriteJob jobHunterFavoriteJob);

    void delJobHunterFavoriteJob(JobHunterFavoriteJob jobHunterFavoriteJob);

    void delJobHunterDiliverJob(JobHunterFavoriteJob jobHunterFavoriteJob);

    int countHunterJobsByPager(Integer jobHunterId, Integer jobId);

    int countFavoriteJobsByPager(Integer jobHunterId);

    int countJobsByPager(Integer employerId);

    List<Job> qryJobsByPager(Integer employerId, Integer pageIndex,
                             Integer pageSize);

}
