package com.delta.worko2o.service.api;

import com.delta.worko2o.model.*;

import java.util.List;

public interface PreachServiceApi {

    List<Preach> qryPreach();

    Preach qryPreachById(Integer preachId);

    List<Employer> qryEmployersByPreachId(Integer preachId);

    List<Job> qryJobsByEmployIds(String employerIds);
}
