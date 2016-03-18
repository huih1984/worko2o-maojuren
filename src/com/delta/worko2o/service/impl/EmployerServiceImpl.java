package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.EmployerServiceApi;
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

/**
 * <Description> <br>
 * 企业相关的原子服务类
 *
 * @author XXX<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月18日 <br>
 * @see com.delta.worko2o.service.impl <br>
 */

@IocBean(name = "employerService")
public class EmployerServiceImpl implements EmployerServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    public Integer addEmployer(Employer employer) {

        log.debug("[employerService]-[addEmployer]-begin");

        Employer employerNew = dao.insert(employer);

        log.debug("[employerService]-[addEmployer]-end");

        return employerNew.getEmployerId();
    }

    public void modEmployer(final Employer employer, boolean ignoreNull) {

        log.debug("[employerService]-[modEmployer]-begin");

        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(Employer.class, true)).update(
                    employer);
        } else {
            dao.update(employer);
        }

        //t_job中的数据更新，通过trigger触发
//        Chain chain = Chain.make("employer_scale", employer.getEmployerScale());
//        chain.add("sale_type", employer.getSaleType());
//        chain.add("logo_path", employer.getLogoPath());
//        Condition c = Cnd.where("employer_id", "=", employer.getEmployerId());
//        dao.update("T_JOB", chain, c);

        log.debug("[employerService]-[modEmployer]-end");
    }

    @Override
    public void modEmployerCredit(Integer employerId) {
        log.debug("[employerService]-[modEmployerCredit]-begin");
        Sql sql = Sqls.create("UPDATE T_EMPLOYER SET feedback_score = feedback_score + 10 WHERE employer_id=" +
                employerId +
                "");
        dao.execute(sql);
        log.debug("[employerService]-[modEmployerCredit]-end");
    }

    public void modEmployerByEmail(String hrEmail, String password) {

        log.debug("[employerService]-[modEmployerByEmail]-begin");

        Chain chain = Chain.make("password", password);
        Condition c = Cnd.where("hr_email", "=", hrEmail);
        dao.update("T_EMPLOYER", chain, c);

        log.debug("[employerService]-[modEmployerByEmail]-end");
    }

    public Employer qryEmployerBySinaId(String sinaId) {
        // TODO Auto-generated method stub

        log.debug("[employerService]-[qryEmployerBySinaId]-begin");

        Condition c = Cnd.where("sina_id", "=", sinaId);

        List<Employer> tempList = dao.query(Employer.class, c, null);

        log.debug("[employerService]-[qryEmployerBySinaId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public Employer qryEmployerByTencentId(String tencentId) {

        log.debug("[employerService]-[qryEmployerByTencentId]-begin");

        Condition c = Cnd
                .where("tencent_id", "=", tencentId);

        List<Employer> tempList = dao.query(Employer.class, c, null);

        log.debug("[employerService]-[qryEmployerByTencentId]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public Employer qryEmployerByEmailAndPassword(String hrEmail,
                                                  String password) {

        log.debug("[employerService]-[qryEmployerByEmailAndPassword]-begin");

        Condition c = Cnd.where("hr_email", "=", hrEmail).and("password", "=",
                password);

        List<Employer> tempList = dao.query(Employer.class, c, null);

        log.debug("[employerService]-[qryEmployerByEmailAndPassword]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public Employer qryEmployerById(Integer employerId) {

        log.debug("[employerService]-[qryEmployerById]-begin");

        Employer employer = dao.fetch(Employer.class, employerId);

        log.debug("[employerService]-[qryEmployerById]-end");

        return employer;
    }

    public Employer qryEmployerByEmail(String hrEmail) {

        log.debug("[employerService]-[qryEmployerByEmail]-begin");

        Condition c = Cnd.where("hr_email", "=", hrEmail);

        List<Employer> tempList = dao.query(Employer.class, c, null);

        log.debug("[employerService]-[qryEmployerByEmail]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<Employer> qryEmployers(String employerIds) {

        log.debug("[employerService]-[qryEmployers]-begin");

        Condition c = Cnd.where("employer_id", "in", employerIds);

        List<Employer> tempList = dao.query(Employer.class, c, null);

        log.debug("[employerService]-[qryEmployers]-end");

        return tempList;
    }

    @Override
    public void addEmployerStore(EmployerStore employerStore) {

        log.debug("[employerService]-[addEmployerStore]-begin");

        dao.insert(employerStore);

        log.debug("[employerService]-[addEmployerStore]-end");
    }

    @Override
    public void modEmployerStore(EmployerStore employerStore, boolean ignoreNull) {
        log.debug("[employerService]-[modEmployerStore]-begin");

        Condition c = Cnd.where("employer_id", "=", employerStore.getEmployerId());
        Chain chain = Chain.make("taobao_level", employerStore.getTaobaoLevel()).add("taobao_store_name", employerStore.getTaobaoStoreName());
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(EmployerStore.class, true)).update("T_EMPLOYER_STORE", chain, c);
        } else {
            dao.update("T_EMPLOER_STORE", chain, c);
        }

        log.debug("[employerService]-[modEmployerStore]-end");
    }

    @Override
    public EmployerStore qryEmployerStoreById(Integer employerId) {

        log.debug("[employerService]-[qryEmployerStoreById]-begin");

        Condition c = Cnd.where("employer_id", "=", employerId);

        List<EmployerStore> tempList = dao.query(EmployerStore.class, c, null);

        log.debug("[employerService]-[qryEmployerStoreById]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public Integer qryMaxEmployerId() {
        log.debug("[employerService]-[qryMaxEmployerId]-begin");

        Integer ret = dao.getMaxId(Employer.class);

        log.debug("[employerService]-[qryMaxEmployerId]-end");

        return ret;
    }

    @Override
    public List<EmployerHot> qryHotEmployers() {

        log.debug("[employerService]-[qryHotEmployers]-begin");

        Pager pager = dao.createPager(0, 10);
        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("indication");
        List<EmployerHot> tempList = dao.query(EmployerHot.class, cri, pager);

        log.debug("[employerService]-[qryHotEmployers]-end");

        return tempList;
    }

    @Override
    public EmployerHot qryHotEmployerByEmployerId(Integer employerId) {

        log.debug("[employerService]-[qryHotEmployerByEmployerId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("employer_id", "=", employerId);
        List<EmployerHot> tempList = dao.query(EmployerHot.class, cri, null);

        log.debug("[employerService]-[qryHotEmployerByEmployerId]-end");
        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public EmployerHot addHotEmployer(EmployerHot employerHot) {
        log.debug("[employerService]-[addHotEmployer]-begin");
        EmployerHot employerHotA = null;
        try {
            employerHotA = dao.insert(employerHot);
        } catch (Exception e) {
        }
        log.debug("[employerService]-[addHotEmployer]-end");
        return employerHotA;
    }

    @Override
    public void modHotEmployer(EmployerHot employerHot, boolean ignoreNull) {
        log.debug("[employerService]-[modHotEmployer]-begin");

        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(EmployerHot.class, true)).update(employerHot);
        } else {
            dao.update(employerHot);
        }

        log.debug("[employerService]-[modHotEmployer]-end");
    }

    @Override
    public EmployerFavoriteHunter qryEmployerFavorite(int employerId, int jobHunterId) {

        log.debug("[employerService]-[qryEmployerFavorite]-begin");

        Condition c = Cnd.where("employer_id", "=", employerId).and("job_hunter_id", "=", jobHunterId);

        List<EmployerFavoriteHunter> tempList = dao.query(EmployerFavoriteHunter.class, c, null);

        log.debug("[employerService]-[qryEmployerFavorite]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<EmployerFavoriteHunter> qryEmployerAllFavorites(int employerId) {

        log.debug("[employerService]-[qryEmployerAllFavorites]-begin");

        Condition c = Cnd.where("employer_id", "=", employerId);

        List<EmployerFavoriteHunter> tempList = dao.query(EmployerFavoriteHunter.class, c, null);

        log.debug("[employerService]-[qryEmployerAllFavorites]-end");


        return tempList;
    }

    @Override
    public List<JobHunter> qryFavoriteHunterByIds(List<EmployerFavoriteHunter> employerFavoriteHunters) {

        log.debug("[employerService]-[qryFavoriteHunterByIds]-begin");
        String ids = "";
        for (EmployerFavoriteHunter employerFavoriteHunter : employerFavoriteHunters) {
            ids += employerFavoriteHunter.getJobHunterId() + ",";
        }
        ids = ids.substring(0, ids.length() - 1);
        Condition c = Cnd.where("job_hunter_id", "in", ids);

        List<JobHunter> tempList = dao.query(JobHunter.class, c, null);

        log.debug("[employerService]-[qryFavoriteHunterByIds]-end");


        return tempList;
    }

    @Override
    public void addEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter) {

        log.debug("[employerService]-[addEmployerFavorite]-begin");

        dao.insert(employerFavoriteHunter);

        log.debug("[employerService]-[addEmployerFavorite]-end");

    }

    @Override
    public void modEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter, boolean ignoreNull) {

        log.debug("[employerService]-[addEmployerFavorite]-begin");

        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(EmployerFavoriteHunter.class, true)).update(
                    employerFavoriteHunter);
        } else {
            dao.update(employerFavoriteHunter);
        }

        log.debug("[employerService]-[addEmployerFavorite]-end");
    }

    @Override
    public void removeEmployerFavorite(EmployerFavoriteHunter employerFavoriteHunter) {

        log.debug("[employerService]-[removeEmployerFavorite]-begin");

        dao.delete(employerFavoriteHunter);

        log.debug("[employerService]-[removeEmployerFavorite]-end");
    }

    @Override
    public void addJobHunterCredit(JobHunterCredit jobHunterCredit) {

        log.debug("[employerService]-[addJobHunterCredit]-begin");

        dao.insert(jobHunterCredit);

        log.debug("[employerService]-[addJobHunterCredit]-end");

    }

    @Override
    public void modJobHunterCredit(JobHunterCredit jobHunterCredit, boolean ignoreNull) {

        log.debug("[employerService]-[modJobHunterCredit]-begin");
        if (jobHunterCredit.getJobHunterCreditId() == null) {
            Chain chain = null;
            if (jobHunterCredit.getHaveJoin() != null) {
                chain = Chain.make("have_join", jobHunterCredit.getHaveAccept());
            }
            if (jobHunterCredit.getHaveAccept() != null) {
                chain = Chain.make("have_accept", jobHunterCredit.getHaveAccept());
            }
            Condition c = Cnd.where("job_id", "=", jobHunterCredit.getJobId()).and("job_hunter_id", "=", jobHunterCredit.getJobHunterId());
            dao.update("T_JOB_HUNTER_CREDIT", chain, c);
        } else {
            if (ignoreNull) {
                Daos.ext(dao, FieldFilter.create(JobHunterCredit.class, true)).update(
                        jobHunterCredit);
            } else {
                dao.update(jobHunterCredit);
            }
        }

        log.debug("[employerService]-[modJobHunterCredit]-end");
    }

    @Override
    public List<JobHunterCredit> qryJobHunterCredit(int jobHunterId) {

        log.debug("[employerService]-[qryJobHunterCredit]-begin");

        Condition c = Cnd.where("job_hunter_id", "=", jobHunterId);

        List<JobHunterCredit> tempList = dao.query(JobHunterCredit.class, c, null);

        log.debug("[employerService]-[qryJobHunterCredit]-end");

        return tempList;
    }
}
