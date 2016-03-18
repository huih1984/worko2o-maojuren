package com.delta.worko2o.action;

import java.util.HashMap;
import java.util.Map;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.delta.worko2o.constants.RetConstants;

public class BaseAction {

    //没页显示多少行
    public static int DEFAULT_PAGE_SIZE = 10;

    public static final Log log = Logs.get();

    protected Map<String, Object> retMap = new HashMap<String, Object>();

    BaseAction() {
        setRetCodeAndMsg(RetConstants.KEY_RET_CODE, "success");
    }

    protected void setRetCodeAndMsg(String retCode, String retMsg) {
        retMap.put(RetConstants.KEY_RET_CODE, retCode);
        retMap.put(RetConstants.KEY_RET_MSG, retMsg);
    }

    protected void setRetInfo(String key, Object val) {
        retMap.put(key, val);
    }

}
