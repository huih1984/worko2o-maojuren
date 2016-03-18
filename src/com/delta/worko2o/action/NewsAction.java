package com.delta.worko2o.action;

import com.delta.worko2o.constants.NewsTypeConstants;
import com.delta.worko2o.constants.RetConstants;
import com.delta.worko2o.model.EbusinessNews;
import com.delta.worko2o.service.impl.NewsServiceImpl;
import com.delta.worko2o.util.DateUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <Description> <br>
 * 消息处理类
 *
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月17日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class NewsAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private NewsServiceImpl newsService;

    @At("/page/demo/news/ebusinessnews1")
    public Map<String, Object> ebusinessNewsRAction(@Param("newsPublishTime") Date newsPublishTime) {

        log.debug("[ebusinessNewsRAction] begin");

        try {
            List<EbusinessNews> ebusinessNewss = newsService.qryEbusinessNewsByPublishTimeAndNewsType(DateUtil.getBeforeDate(newsPublishTime, 90),
                    NewsTypeConstants.NEWS_TYPE_1);

            if (Lang.isEmpty(ebusinessNewss)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found ebusiness news");
            } else {
                setRetInfo(RetConstants.KEY_EBUSINESS_NEWS_LIST, ebusinessNewss);
            }
        } catch (Exception e) {
            log.debug("ebusinessNewsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[ebusinessNewsRAction] end");

        return retMap;
    }

    @At("/page/demo/news/ebusinessnews2")
    public Map<String, Object> commercesNewsRAction(@Param("newsPublishTime") Date newsPublishTime) {

        log.debug("[commercesNewsRAction] begin");

        try {
            List<EbusinessNews> ebusinessNewss = newsService.qryEbusinessNewsByPublishTimeAndNewsType(DateUtil.getBeforeDate(newsPublishTime, 90),
                    NewsTypeConstants.NEWS_TYPE_2);

            if (Lang.isEmpty(ebusinessNewss)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found ebusiness news");
            } else {
                setRetInfo(RetConstants.KEY_EBUSINESS_NEWS_LIST, ebusinessNewss);
            }
        } catch (Exception e) {
            log.debug("commercesNewsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[commercesNewsRAction] end");

        return retMap;
    }

    @At("/page/demo/news/ebusinessnews3")
    public Map<String, Object> peopleNewsRAction(@Param("newsPublishTime") Date newsPublishTime) {

        log.debug("[peopleNewsRAction] begin");

        try {
            List<EbusinessNews> ebusinessNewss = newsService.qryEbusinessNewsByPublishTimeAndNewsType(DateUtil.getBeforeDate(newsPublishTime, 90),
                    NewsTypeConstants.NEWS_TYPE_3);

            if (Lang.isEmpty(ebusinessNewss)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found ebusiness news");
            } else {
                setRetInfo(RetConstants.KEY_EBUSINESS_NEWS_LIST, ebusinessNewss);
            }
        } catch (Exception e) {
            log.debug("peopleNewsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[peopleNewsRAction] end");

        return retMap;
    }

    @At("/page/demo/news/ebusinessnews")
    public Map<String, Object> newsListRAction(
            @Param("newsPublishTime") Date newsPublishTime,
            @Param("newsType") String newsType) {

        log.debug("[newsListRAction] begin");

        try {
            List<EbusinessNews> ebusinessNewss = newsService
                    .qryEbusinessNewsByPublishTimeAndNewsType(DateUtil.getBeforeDate(newsPublishTime, 90),
                            newsType);

            if (Lang.isEmpty(ebusinessNewss)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL,
                        "not found ebusiness news");
            } else {
                setRetInfo(RetConstants.KEY_EBUSINESS_NEWS_LIST, ebusinessNewss);
            }
        } catch (Exception e) {
            log.debug("peopleNewsRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[newsListRAction] end");

        return retMap;
    }

    @At("/page/demo/news/ebusinessnewsdetail")
    public Map<String, Object> newsDetailRAction(@Param("newsId") Integer newsId) {

        log.debug("[newsDetailRAction] begin");

        try {
            EbusinessNews ebusinessNews = newsService.qryEbusinessNewsById(newsId);

            if (Lang.isEmpty(ebusinessNews)) {
                setRetCodeAndMsg(RetConstants.VAL_FAIL, "not found ebusiness news");
            } else {
                setRetInfo(RetConstants.KEY_EBUSINESS_NEWS_DETAIL, ebusinessNews);
            }
        } catch (Exception e) {
            log.debug("newsDetailRAction error", e);
            setRetCodeAndMsg(RetConstants.VAL_FAIL, e.getMessage());
        }

        log.debug("[newsDetailRAction] end");

        return retMap;
    }
}
