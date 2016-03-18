package com.delta.worko2o.weixinModel.message.resp;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-17
 * Time: ����5:43
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

/**
 * @author liufeng
 * @date 2013-05-19
 */
public class NewsMessage extends BaseMessage {
    private int ArticleCount;
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}