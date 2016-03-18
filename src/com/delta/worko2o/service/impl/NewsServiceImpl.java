package com.delta.worko2o.service.impl;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.delta.worko2o.model.EbusinessNews;
import com.delta.worko2o.model.EbusinessNewsRemainder;
import com.delta.worko2o.service.api.NewsServiceApi;
import com.delta.worko2o.util.StringUtil;

@IocBean(name = "newsService")
public class NewsServiceImpl implements NewsServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    public List<EbusinessNews> qryEbusinessNewsByPublishTimeAndNewsType(
            Date newsPublishTime, String newsType) {

        log.debug("[newsService]-[qryEbusinessNewsByPublishTimeAndNewsType]-begin");

        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("news_publish_time");
        // option param newsType, default all
        if (StringUtil.isNotEmpty(newsType)) {
            cri.where().and("news_publish_time", ">", newsPublishTime).and(
                    "news_type", "=", newsType);
        } else {
            cri.where().and("news_publish_time", ">", newsPublishTime);
        }


        Pager pager = dao.createPager(1, 10);

        List<EbusinessNews> tempList = dao.query(EbusinessNews.class, cri, pager);

        log.debug("[newsService]-[qryEbusinessNewsByPublishTimeAndNewsType]-end");

        return tempList;
    }

    public EbusinessNews qryEbusinessNewsById(Integer newsId) {

        log.debug("[newsService]-[qryEbusinessNewsById]-begin");

        EbusinessNews ebusinessNews = dao.fetch(EbusinessNews.class, newsId);

        // append remainder news content
        if (ebusinessNews.getHaveRemainder() != null && ebusinessNews.getHaveRemainder() != 0) {
            EbusinessNewsRemainder ebusinessNewsRemainder = dao.fetch(
                    EbusinessNewsRemainder.class, newsId);

            if (ebusinessNewsRemainder != null) {
                ebusinessNews.setNewsContent(ebusinessNews.getNewsContent()
                        + ebusinessNewsRemainder.getNewsContent());
            }
        }
        // sql decode
        ebusinessNews.setNewsContent(StringUtil.decodeSql(ebusinessNews
                .getNewsContent()));

        log.debug("[newsService]-[qryEbusinessNewsById]-end");

        return ebusinessNews;
    }

}
