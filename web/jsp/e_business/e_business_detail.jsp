<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>资讯</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint20">
    <div class="row">
        <div class="col-sm-8 woo-main">
            <div class="woo-post" id="news_content">
            </div>
            <!-- JiaThis Button BEGIN -->
            <%--<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0&amp;btn=r1.gif" charset="utf-8"></script>--%>
            <!-- JiaThis Button END -->
        </div>
        <!-- /.woo-main -->

        <div id="rightcol" class="col-sm-3 col-sm-offset-1 woo-sidebar">

            <div class="sidebar-module">
                <h4>
                    营销方案
                </h4>
                <ol class="list-unstyled" id="news_list_3">
                </ol>
            </div>
            <div class="sidebar-module">
                <h4>
                    电商资讯
                </h4>
                <ol class="list-unstyled" id="news_list_1">
                </ol>
            </div>
            <div class="sidebar-module">
                <h4>
                    人物资讯
                </h4>
                <ol class="list-unstyled" id="news_list_2">
                </ol>
            </div>
        </div>

    </div>
    <!-- /.row -->
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {
        query();
        if (isMobile()) {
            $("#rightcol").remove();
            $("#share").remove();
        } else {
            queryNews();
        }
    });

    function main(url) {
        window.location.href = url;
    }

    function query() {
        var url = '/page/demo/news/ebusinessnewsdetail?newsId='
                + escape('${param.newsId}');
        $.post(
                url,
                null,
                function (data) {
                    var value = data.ebusiness_news_detail;

                    if (value == null) {
                        $('#news_content').html("");
                    } else {
                        $("title").html(value.newsTitle);

                        var htmlTags = '';

                        if (value.newsTag != null) {
                            var newsTags = value.newsTag.split(',');
                            for (pos in newsTags) {
                                htmlTags += '    <span class="label woo-news-label">'
                                        + newsTags[pos] + '</span>';
                            }
                        }

                        var newsPublishTime = value.newsPublishTime;
                        newsPublishTime = newsPublishTime.substring(0,
                                newsPublishTime.indexOf(' '));

                        var htmlInfo = '<h2 class="font20 text-center fontem" style="margin-top: 20px;">'
                                + value.newsTitle
                                + '</h2>'
                                + '<div class="margint10 text-center">'
                                + '<p class="clra6a6a6 text-center">来源&nbsp;<a href="#">'
                                + value.sourceFrom
                                + '</a>  &nbsp; '
                                + newsPublishTime
                                + '</p>'
                                + "<div class='line margint20 clrb8e44ad'></div> "
                                + '<label class="clra6a6a6 margint10"><i class="fa fa-tag"></i></label>'
                                + htmlTags
                                + '</div>'
                                + '<div class="margint20 woo-article-content">'
                                + value.newsContent + '</div>';

                        $('#news_content').html(htmlInfo);
                        $('p').removeAttr("style");
                        $('p').css("text-align", "center");
                        $('.woo-article-content img').css("width", "90%");
                        $('.woo-article-content').css("font-size", "14px");
                        $('.woo-article-content').css("line-height", "24px");
                        $('.woo-article-content').css("padding-top", "10px");
                        $('.woo-article-content').css("font-family", "Microsoft YaHei");

                        $('body').css("background", "#fff");
                        $('#share').show();
                    }
                }, "json");
    }

    function queryNews() {
        var yestoday = new Date();
        yestoday.setDate(yestoday.getDate() - 1);

        var dateString = (yestoday.getYear() + 1900) + '-'
                + (yestoday.getMonth() + 1) + '-' + yestoday.getDate()
                + ' 23:59:59';

        var url1 = '/page/demo/news/ebusinessnews1?newsPublishTime='
                + dateString;

        $.post(
                url1,
                null,
                function (data) {
                    var list = data.ebusiness_news_list;
                    $('#news_list_1').html("");
                    for (var i = 0; i < list.length; i++) {
                        var liInfo = '<li class="woo-news-title-li">'
                                + '<div class="row"><div class="col-md-1"><i class="fa fa-file" style="margin-top: 4px; color: #440062"></i></div>'
                                + '<div class="col-md-10"><a href="/jsp/e_business/e_business_detail.jsp?newsId='
                                + list[i].newsId + '">' + list[i].newsTitle + '</a></div></div>'
                                + '</li>';

                        $('#news_list_1').append(liInfo);

                    }
                }, "json");

        var url2 = '/page/demo/news/ebusinessnews2?newsPublishTime='
                + dateString;

        $.post(
                url2,
                null,
                function (data) {
                    var list = data.ebusiness_news_list;
                    $('#news_list_2').html("");
                    for (var i = 0; i < list.length; i++) {
                        var liInfo = '<li class="woo-news-title-li">'
                                + '<div class="row"><div class="col-md-1"><i class="fa fa-file" style="margin-top: 4px; color: #440062"></i></div>'
                                + '<div class="col-md-10"><a href="/jsp/e_business/e_business_detail.jsp?newsId='
                                + list[i].newsId + '">' + list[i].newsTitle + '</a></div></div>'
                                + '</li>';

                        $('#news_list_2').append(liInfo);

                    }
                }, "json");

        var url3 = '/page/demo/news/ebusinessnews3?newsPublishTime='
                + dateString;

        $.post(
                url3,
                null,
                function (data) {
                    var list = data.ebusiness_news_list;
                    $('#news_list_3').html("");
                    for (var i = 0; i < list.length; i++) {
                        var liInfo = '<li class="woo-news-title-li">'
                                + '<div class="row"><div class="col-md-1"><i class="fa fa-file" style="margin-top: 4px; color: #440062"></i></div>'
                                + '<div class="col-md-10"><a href="/jsp/e_business/e_business_detail.jsp?newsId='
                                + list[i].newsId + '">' + list[i].newsTitle + '</a></div></div>'
                                + '</li>';

                        $('#news_list_3').append(liInfo);

                    }
                }, "json");
    }
</script>
</body>
</html>
