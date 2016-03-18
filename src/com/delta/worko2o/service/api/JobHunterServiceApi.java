package com.delta.worko2o.service.api;

import java.util.List;

import com.delta.worko2o.model.*;

public interface JobHunterServiceApi {

    Integer addJobHunter(JobHunter jobHunter);

    Integer addJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience);

    Integer addJobHunterExpect(JobHunterExpect jobHunterExpect);

    Integer addJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience);

    Integer addJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience);

    Integer addJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience);

    void modJobHunter(JobHunter jobHunter, boolean ignoreNull);

    void modJobHunterByEmail(String jobHunterEmail, String jobHunterPassword);

    void modJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience, boolean ignoreNull);

    void modJobHunterExpect(JobHunterExpect jobHunterExpect, boolean ignoreNull);

    void modJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience, boolean ignoreNull);

    void modJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience, boolean ignoreNull);

    void modJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience, boolean ignoreNull);

    void delJobHunterEduExperience(JobHunterEduExperience jobHunterEduExperience);

    void delJobHunterExpect(JobHunterExpect jobHunterExpect);

    void delJobHunterProgramExperience(JobHunterProgramExperience jobHunterProgramExperience);

    void delJobHunterWorkExperience(JobHunterWorkExperience jobHunterWorkExperience);

    void delJobHunterTrainExperience(JobHunterTrainExperience jobHunterTrainExperience);

    JobHunter qryJobHunterBySinaId(String sinaId);

    JobHunter qryJobHunterByTencentId(String tencentId);

    JobHunter qryJobHunterByEmailAndPassword(String jobHunterEmail, String password);

    JobHunter qryJobHunter(Integer jobHunterId);

    JobHunterEduExperience qryJobHunterEduExperienceByJobHunterId(Integer jobHunterId);

    JobHunterExpect qryJobHunterExpectByJobHunterId(Integer jobHunterId);

    JobHunterProgramExperience qryJobHunterProgramExperienceByJobHunterId(Integer jobHunterId);

    JobHunterWorkExperience qryJobHunterWorkExperienceByJobHunterId(Integer jobHunterId);

    List<JobHunterEduExperience> qryAllJobHunterEduExperienceByJobHunterId(Integer jobHunterId);

    List<JobHunterExpect> qryAllJobHunterExpectByJobHunterId(Integer jobHunterId);

    List<JobHunterProgramExperience> qryAllJobHunterProgramExperienceByJobHunterId(Integer jobHunterId);

    List<JobHunterWorkExperience> qryAllJobHunterWorkExperienceByJobHunterId(Integer jobHunterId);

    JobHunter qryJobHunterByEmail(String jobHunterEmail);

    List<JobHunter> qryJobHunters(String ids);

    List<JobHunterTrainExperience> qryAllJobHunterTrainExperienceByJobHunterId(
            Integer jobHunterId);

    int countJobHuntersByPager(String jobHunterBachelorDegree, String jobHunterStartWorkYear, String jobHunterSex, String jobHunterCurrentProvince,
                               String jobHunterCurrentCity, String jobHunterCurrentDistrict, String jobHunterMajor,
                               String jobHunterCurrentStatus, String updateTime, String keyword, String expectSalary);

    List<JobHunter> qryJobHunterByPager(String jobHunterBachelorDegree, String jobHunterStartWorkYear, String jobHunterSex, String jobHunterCurrentProvince,
                                        String jobHunterCurrentCity, String jobHunterCurrentDistrict, String jobHunterMajor,
                                        String jobHunterCurrentStatus, String updateTime, String keyword, String expectSalary, Integer pageIndex, Integer pageSize);

    jangoSession qryJangoSession(String sessionKey);

    void addJangoSession(jangoSession jangosession);
}
