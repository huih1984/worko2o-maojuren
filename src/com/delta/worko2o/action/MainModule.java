package com.delta.worko2o.action;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * <Description> <br>
 * 启动类,初始化IOC容器相关配置
 *
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月18日 <br>
 * @see com.delta.worko2o.action <br>
 */
@Modules(scanPackage = true)
@Ok("json")
@Fail("json")
@IocBy(type = ComboIocProvider.class, args = {
        "*json", "ioc", "*annotation", "com.delta.worko2o"
})
//@UrlMappingBy(value=UrlMappingSet.class)
public class MainModule {
}