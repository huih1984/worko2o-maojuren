package com.delta.worko2o.service.api;

import java.util.Date;
import java.util.List;

import com.delta.worko2o.model.EbusinessNews;

/**
 * @author john
 */
public interface NewsServiceApi {

    List<EbusinessNews> qryEbusinessNewsByPublishTimeAndNewsType(Date newsPublishTime, String newsType);

    EbusinessNews qryEbusinessNewsById(Integer newsId);
}
