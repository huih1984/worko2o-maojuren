package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.CommServiceApi;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.StringUtil;
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

@IocBean(name = "commService")
public class CommServiceImpl implements CommServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

}
