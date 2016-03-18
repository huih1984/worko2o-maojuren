package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.Envelope;
import com.delta.worko2o.model.JobAssess;
import com.delta.worko2o.model.JobInteract;
import com.delta.worko2o.model.StaticHr;
import com.delta.worko2o.service.api.JobInteractServiceApi;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

@IocBean(name = "jobInteractService")
public class JobInteractServiceImpl implements JobInteractServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;


    @Override
    public void addJobInteract(JobInteract jobInteract) {

        log.debug("[jobInteractService]-[addJobInteract]-begin");

        dao.insert(jobInteract);

        log.debug("[jobInteractService]-[addJobInteract]-end");
    }

    @Override
    public JobInteract qryJobInteractById(Integer jobHunterId, Integer employerId, Integer jobId) {

        log.debug("[jobInteractService]-[qryJobInteractById]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("employer_id", employerId);
        cri.where().andEquals("job_id", jobId);
        cri.where().andEquals("job_hunter_id", jobHunterId);

        List<JobInteract> tempList = dao.query(JobInteract.class, cri, null);

        log.debug("[jobInteractService]-[qryJobInteractById]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<JobInteract> qryJobInteractAll(Integer jobHunterId, Integer jobId) {

        log.debug("[jobInteractService]-[qryJobInteractByPager]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobId != null) {
            cri.where().andEquals("job_id", jobId);
        }
        if (jobHunterId != null) {
            cri.where().andEquals("job_hunter_id", jobHunterId);
        }
        cri.where().andNotEquals("hunter_status", 4);
        // 创建分页条件
        List<JobInteract> tempList = dao.query(JobInteract.class, cri, null);

        log.debug("[jobInteractService]-[qryJobInteractByPager]-end");

        return tempList;
    }

    @Override
    public List<StaticHr> qryStaticHr(Integer employerId) {
        log.debug("[jobInteractService]-[qryStaticHr]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("employer_id", employerId);

        List<StaticHr> staticHrs = dao.query(StaticHr.class, cri, null);

        log.debug("[jobInteractService]-[qryStaticHr]-end");

        return staticHrs;
    }

    @Override
    public void modHunterInteractByEmployerId(Integer employerId, Integer jobId) {

        log.debug("[jobInteractService]-[modHunterInteractByEmployerId]-begin");

        Chain chain = Chain.make("hunter_status_changed", 0);
        Condition c;
        if (jobId == null) {
            c = Cnd.where("hunter_status_changed", "<>", 0).and("employer_id", "=", employerId);
        } else {
            c = Cnd.where("hunter_status_changed", "<>", 0).and("employer_id", "=", employerId).and("job_id", "=", jobId);
        }
        dao.update("T_JOB_INTERACT", chain, c);

        log.debug("[jobInteractService]-[modHunterInteractByEmployerId]-end");

    }

    @Override
    public List<JobInteract> qryEnvelopeJobInteractHunter(Integer jobHunterId) {

        log.debug("[jobInteractService]-[qryEnvelopeJobInteractHunter]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_hunter_id", jobHunterId);
        cri.where().and("hunter_status", ">=", 2);
        cri.where().andNotEquals("hunter_status", 4);
        // 创建分页条件
        List<JobInteract> tempList = dao.query(JobInteract.class, cri, null);

        log.debug("[jobInteractService]-[qryEnvelopeJobInteractHunter]-end");

        return tempList;

    }

    public List<JobInteract> qryJobInteractByPager(Integer jobHunterId, Integer jobId, Integer pageIndex, Integer pageSize) {

        log.debug("[jobInteractService]-[qryJobInteractByPager]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobId != null) {
            cri.where().andEquals("job_id", jobId);
        }
        cri.where().andEquals("job_hunter_id", jobHunterId);
        cri.where().andNotEquals("hunter_status", 4);
        // 创建分页条件
        Pager pager = dao.createPager(pageIndex, pageSize);
        List<JobInteract> tempList = dao.query(JobInteract.class, cri, pager);

        log.debug("[jobInteractService]-[qryJobInteractByPager]-end");

        return tempList;
    }

    public List<JobInteract> qryJobInteractByEmployerId(Integer employerId, Integer jobId) {

        log.debug("[jobInteractService]-[qryJobInteractByEmployerId]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        if (jobId != null) {
            cri.where().andEquals("job_id", jobId);
        }
        if (employerId != null) {
            cri.where().andEquals("employer_id", employerId);
        }

        List<JobInteract> tempList = dao.query(JobInteract.class, cri, null);

        log.debug("[jobInteractService]-[qryJobInteractByEmployerId]-end");

        return tempList;
    }

    @Override
    public List<JobInteract> qryEnvelopeJobInteractEmployer(Integer employerId) {

        log.debug("[jobInteractService]-[qryEnvelopeJobInteractEmployer]-begin");

        // 拼装sql
        Criteria cri = Cnd.cri();
        cri.where().andEquals("employer_id", employerId);
        cri.where().and("hunter_status", ">=", 2);
        cri.where().andNotEquals("hunter_status", 4);
        // 创建分页条件
        List<JobInteract> tempList = dao.query(JobInteract.class, cri, null);

        log.debug("[jobInteractService]-[qryEnvelopeJobInteractEmployer]-end");

        return tempList;

    }

    @Override
    public void modJobInteract(JobInteract jobInteract, boolean ignoreNull) {

        log.debug("[jobInteractService]-[modJobInteract]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(JobInteract.class, true)).update(jobInteract);
        } else {
            dao.update(jobInteract);
        }

        log.debug("[jobInteractService]-[modJobInteract]-end");
    }

    @Override
    public void modJobInteractByHunterKey(JobInteract jobInteract, boolean ignoreNull) {

        log.debug("[jobInteractService]-[modJobInteractByHunterKey]-begin");

        Chain chain = Chain.make("hunter_status_changed", jobInteract.getHunterStatusChanged()).add("hunter_status", jobInteract.getHunterStatus());
        Condition c;
        c = Cnd.where("job_id", "=", jobInteract.getJobId()).and("job_hunter_id", "=", jobInteract.getJobHunterId());
        dao.update("T_JOB_INTERACT", chain, c);

        log.debug("[jobInteractService]-[modJobInteractByHunterKey]-end");
    }

    @Override
    public void modEmployerInteractByHunterId(Integer hunterId, Integer jobId) {

        log.debug("[jobInteractService]-[modEmployerInteractByHunterId]-begin");

        Chain chain = Chain.make("employer_status_changed", 0);
        Condition c;
        if (jobId == null) {
            c = Cnd.where("employer_status_changed", "<>", 0).and("job_hunter_id", "=", hunterId);
        } else {
            c = Cnd.where("employer_status_changed", "<>", 0).and("job_hunter_id", "=", hunterId).and("job_id", "=", jobId);
        }
        dao.update("T_JOB_INTERACT", chain, c);

        log.debug("[jobInteractService]-[modEmployerInteractByHunterId]-end");
    }

    @Override
    public void addEnvelope(Envelope envelope) {

        log.debug("[jobInteractService]-[addEnvelope]-begin");

        dao.insert(envelope);

        log.debug("[jobInteractService]-[addEnvelope]-end");
    }

    @Override
    public List<Envelope> qryEnvelopeById(Integer employerId, Integer jobHunterId) {

        log.debug("[jobInteractService]-[qryEnvelopeById]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("employer_id", employerId);
        cri.where().andEquals("job_hunter_id", jobHunterId);

        List<Envelope> envelopes = dao.query(Envelope.class, cri, null);

        log.debug("[jobInteractService]-[qryEnvelopeById]-end");

        return envelopes;
    }

    @Override
    public int qryUnreadEnvelopeNumByJobHunterId(Integer jobHunterId) {

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByJobHunterId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("unread", 1);
        cri.where().andEquals("author", 1);
        cri.where().andEquals("job_hunter_id", jobHunterId);
        List<Envelope> envelopes = dao.query(Envelope.class, cri, null);

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByJobHunterId]-end");

        return envelopes.size();
    }

    @Override
    public List<Envelope> qryUnreadEnvelopeByJobHunterId(Integer jobHunterId) {

        log.debug("[jobInteractService]-[qryUnreadEnvelopeByJobHunterId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("unread", 1);
        cri.where().andEquals("author", 1);
        cri.where().andEquals("job_hunter_id", jobHunterId);
        cri.getOrderBy().asc("envelope_id");
        List<Envelope> envelopes = dao.query(Envelope.class, cri, null);

        log.debug("[jobInteractService]-[qryUnreadEnvelopeByJobHunterId]-end");

        return envelopes;
    }

    @Override
    public int qryUnreadEnvelopeNumByEmployerId(Integer employerId) {

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByEmployerId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("unread", 1);
        cri.where().andEquals("author", 0);
        cri.where().andEquals("employer_id", employerId);
        List<Envelope> envelopes = dao.query(Envelope.class, cri, null);

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByEmployerId]-end");

        return envelopes.size();
    }

    @Override
    public void modEnvelope(Envelope envelope, boolean ignoreNull) {

        log.debug("[jobInteractService]-[modEnvelope]-begin");

        // 过滤空字段
        if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(Envelope.class, true)).update(envelope);
        } else {
            dao.update(envelope);
        }

        log.debug("[jobInteractService]-[modEnvelope]-end");

    }

    @Override
    public void removeEnvelope(Envelope envelope) {

        log.debug("[jobInteractService]-[removeEnvelope]-begin");

        dao.delete(envelope);

        log.debug("[jobInteractService]-[removeEnvelope]-end");
    }

    @Override
    public List<Envelope> qryUnreadEnvelopeByEmployerId(Integer employerId) {

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByEmployerId]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("unread", 1);
        cri.where().andEquals("author", 0);
        cri.where().andEquals("employer_id", employerId);
        cri.getOrderBy().asc("envelope_id");
        List<Envelope> envelopes = dao.query(Envelope.class, cri, null);

        log.debug("[jobInteractService]-[qryUnreadEnvelopeNumByEmployerId]-end");

        return envelopes;
    }

    @Override
    public void addJobAssess(JobAssess jobAssess) {

        log.debug("[jobInteractService]-[addJobAssess]-begin");

        dao.insert(jobAssess);

        log.debug("[jobInteractService]-[addJobAssess]-end");
    }

    @Override
    public List<JobAssess> qryJobAssess(Integer jobId) {

        log.debug("[jobInteractService]-[qryJobAssess]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("job_id", jobId);
        List<JobAssess> jobAssesses = dao.query(JobAssess.class, cri, null);

        log.debug("[jobInteractService]-[qryJobAssess]-end");

        return jobAssesses;
    }
}
