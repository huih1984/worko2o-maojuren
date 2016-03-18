package com.delta.worko2o.service.api;

import com.delta.worko2o.model.Envelope;
import com.delta.worko2o.model.JobAssess;
import com.delta.worko2o.model.JobInteract;
import com.delta.worko2o.model.StaticHr;

import java.util.List;

public interface JobInteractServiceApi {

    void addJobInteract(JobInteract jobInteract);

    JobInteract qryJobInteractById(Integer jobHunterId, Integer employerId, Integer jobId);

    List<JobInteract> qryJobInteractAll(Integer jobHunterId, Integer jobId);

    List<JobInteract> qryEnvelopeJobInteractHunter(Integer jobHunterId);

    List<JobInteract> qryJobInteractByPager(Integer jobHunterId, Integer jobId, Integer pageIndex, Integer pageSize);

    List<JobInteract> qryJobInteractByEmployerId(Integer employerId, Integer jobId);

    List<JobInteract> qryEnvelopeJobInteractEmployer(Integer employerId);

    void modJobInteract(JobInteract jobInteract, boolean ignoreNull);

    void modJobInteractByHunterKey(JobInteract jobInteract, boolean ignoreNull);

    void addEnvelope(Envelope envelope);

    List<Envelope> qryEnvelopeById(Integer employerId, Integer jobHunterId);

    int qryUnreadEnvelopeNumByJobHunterId(Integer jobHunterId);

    List<Envelope> qryUnreadEnvelopeByJobHunterId(Integer jobHunterId);

    int qryUnreadEnvelopeNumByEmployerId(Integer employerId);

    void modEnvelope(Envelope envelope, boolean ignoreNull);

    void removeEnvelope(Envelope envelope);

    List<Envelope> qryUnreadEnvelopeByEmployerId(Integer employerId);

    void modHunterInteractByEmployerId(Integer employerI, Integer jobId);

    void modEmployerInteractByHunterId(Integer hunterId, Integer jobId);

    List<StaticHr> qryStaticHr(Integer employerId);

    void addJobAssess(JobAssess jobAssess);

    List<JobAssess> qryJobAssess(Integer jobId);
}
