package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.Employer;
import com.delta.worko2o.model.Job;
import com.delta.worko2o.model.Preach;
import com.delta.worko2o.service.api.PreachServiceApi;
import com.delta.worko2o.util.DateUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

@IocBean(name = "preachService")
public class PreachServiceImpl implements PreachServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    @Override
    public List<Preach> qryPreach() {

        log.debug("[jobService]-[qryPreach jobId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("open_date", ">", DateUtil.getNowDate());

        List<Preach> preaches = dao.query(Preach.class, cri, null);

        log.debug("[jobService]-[qryPreach jobId]-end");

        return preaches;
    }

    @Override
    public Preach qryPreachById(Integer preachId) {

        log.debug("[preachService]-[qryPreachById preachId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("preach_id", "=", preachId);

        List<Preach> preaches = dao.query(Preach.class, cri, null);

        log.debug("[preachService]-[qryPreachById preachId]-end");

        if (Lang.isEmpty(preaches)) {
            return null;
        } else {
            return preaches.get(0);
        }
    }

    @Override
    public List<Employer> qryEmployersByPreachId(Integer preachId) {

        log.debug("[preachService]-[qryEmployersByPreachId preachId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("preach_id", "=", preachId);

        List<Employer> employers = dao.query(Employer.class, cri, null);

        log.debug("[preachService]-[qryEmployersByPreachId preachId]-end");

        return employers;
    }

    @Override
    public List<Job> qryJobsByEmployIds(String employerIds) {

        log.debug("[preachService]-[qryJobsByEmployIds employerId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("employer_id", "in", employerIds);

        List<Job> jobs = dao.query(Job.class, cri, null);

        log.debug("[preachService]-[qryJobsByEmployIds employerId]-end");

        return jobs;
    }
}
