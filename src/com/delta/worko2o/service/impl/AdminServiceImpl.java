package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.Admin;
import com.delta.worko2o.model.Employer;
import com.delta.worko2o.service.api.AdminServiceApi;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

@IocBean(name = "adminService")
public class AdminServiceImpl implements AdminServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    @Override
    public void addAdmin(Admin admin) {

        log.debug("[adminService]-[addAdmin]-begin");

        dao.insert(admin);

        log.debug("[adminService]-[addAdmin]-end");
    }

    @Override
    public Admin qryAdmin(String userName, String password) {
        log.debug("[adminService]-[qryAdmin]-begin");

        Criteria cri = Cnd.cri();
        cri.where().and("userName", "=", userName);
        cri.where().and("password", "=", password);
        List<Admin> tempList = dao.query(Admin.class, cri, null);

        log.debug("[adminService]-[qryAdmin]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public List<Employer> qryNotApproveEmployer(String employerName) {

        log.debug("[adminService]-[qryNotApproveEmployer]-begin");

        Criteria cri = Cnd.cri();
        if (employerName != null) {
            cri.where().andLike("employerName", employerName);
        }
        cri.where().and("censor_status", "<", 1);
        List<Employer> tempList = dao.query(Employer.class, cri, null);

        log.debug("[adminService]-[qryNotApproveEmployer]-end");

        return tempList;
    }

    @Override
    public List<Employer> qryAllEmployer(String employerName) {

        log.debug("[adminService]-[qryAllEmployer]-begin");

        Criteria cri = Cnd.cri();
        if (employerName != null) {
            cri.where().andLike("employerName", employerName);
        }
        List<Employer> tempList = dao.query(Employer.class, cri, null);

        log.debug("[adminService]-[qryAllEmployer]-end");

        return tempList;
    }

    public void modEmployer(Employer employer) {
        log.debug("[adminService]-[modEmployer]-begin");
        Daos.ext(dao, FieldFilter.create(Employer.class, true)).update(employer);
        log.debug("[adminService]-[modEmployer]-begin");
    }
}
