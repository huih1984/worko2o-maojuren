package com.delta.worko2o.service.api;

import com.delta.worko2o.model.*;

import java.util.List;


public interface EmployerServiceApi {

    Integer addEmployer(Employer employer);

    void modEmployer(Employer employer, boolean ignoreNull);

    void modEmployerCredit(Integer employerId);

    void modEmployerByEmail(String hrEmail, String password);

    Employer qryEmployerBySinaId(String sinaAccessToken);

    Employer qryEmployerByTencentId(String tencentAccessToken);

    Employer qryEmployerByEmailAndPassword(String hrEmail, String password);

    Employer qryEmployerById(Integer employerId);

    List<Employer> qryEmployers(String employerIds);

    Employer qryEmployerByEmail(String hrEmail);

    void addEmployerStore(EmployerStore employerStore);

    void modEmployerStore(EmployerStore employerStore, boolean ignoreNull);

    EmployerStore qryEmployerStoreById(Integer employerId);

    Integer qryMaxEmployerId();

    List<EmployerHot> qryHotEmployers();

    EmployerHot qryHotEmployerByEmployerId(Integer employerId);

    EmployerHot addHotEmployer(EmployerHot employerHot);

    void modHotEmployer(EmployerHot employerHot, boolean ignoreNull);

    EmployerFavoriteHunter qryEmployerFavorite(int employerId, int jobHunterId);

    List<EmployerFavoriteHunter> qryEmployerAllFavorites(int employerId);

    List<JobHunter> qryFavoriteHunterByIds(List<EmployerFavoriteHunter> employerFavoriteHunters);

    void addEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter);

    void modEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter, boolean ignoreNull);

    void removeEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter);

    void addJobHunterCredit(JobHunterCredit jobHunterCredit);

    void modJobHunterCredit(JobHunterCredit jobHunterCredit, boolean ignoreNull);

    List<JobHunterCredit> qryJobHunterCredit(int jobHunterId);
}
