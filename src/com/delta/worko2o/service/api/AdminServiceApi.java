package com.delta.worko2o.service.api;

import com.delta.worko2o.model.Admin;
import com.delta.worko2o.model.Employer;

import java.util.List;

/**
 * @author john
 */
public interface AdminServiceApi {

    void addAdmin(Admin admin);

    Admin qryAdmin(String account, String password);

    List<Employer> qryNotApproveEmployer(String employerName);

    List<Employer> qryAllEmployer(String employerName);

    void modEmployer(Employer employe);
}
