package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.EbusinessNews;
import com.delta.worko2o.model.jangoSession;
import com.delta.worko2o.service.api.SessionServiceApi;
import com.delta.worko2o.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Date;
import java.util.List;

@IocBean(name = "sessionService")
public class SessionServiceImpl implements SessionServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

}
