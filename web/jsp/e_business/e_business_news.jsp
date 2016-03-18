<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>资讯</title>
    <%@ include file="/jsp/comm/header.jsp" %>

</head>
<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>
<ul id="navhead" class="row bottombar list-inline font20 gradientnav paddingl180">
    <li class="active text-center sizew100" data="1"><a href="#">
        <span class="fontem clredeaf4 newshead">资讯</span></a></li>
    <li class="text-center sizew100" data="3"><a href="#">
        <span class="fontem clredeaf4 newshead">商道</span></a></li>
    <li class="text-center sizew100" data="2"><a href="#">
        <span class="fontem clredeaf4 newshead">人物</span></a></li>
</ul>
<div class="container main">
    <div class="woo-news">
        <div>
            <ul id="news_list" class="list-group">
            </ul>
        </div>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        queryNews(1);
        $(".bottombar li").click(function () {
            queryNews($(this).attr("data"));
        });
    });

    function main(url) {
        window.location.href = url;
    }

    function queryNews(newsType) {
        var yestoday = new Date();
        yestoday.setDate(yestoday.getDate() - 1);

        var dateString = (yestoday.getYear() + 1900) + '-' + (yestoday.getMonth() + 1) + '-'
                + yestoday.getDate() + ' 23:59:59';

        var url1 = '/page/demo/news/ebusinessnews?newsType=' + newsType + '&newsPublishTime=' + dateString;

        $.post(url1, null, function (data) {
            var list = data.ebusiness_news_list;
            $('#news_list').html("");
            for (var i = 0; i < list.length; i++) {
                var htmlImg = '';
                if (list[i].iconPath != null && list[i].iconPath != '') {
                    htmlImg =
                            '           <a class="media-left col-md-3 col-xs-12" href="/jsp/e_business/e_business_detail.jsp?newsId=' + list[i].newsId + '">'
                                    + '             <img class="media-object newspic" alt=""'
                                    + '                  src="/' + list[i].iconPath + '"'
                                    + '                  data-holder-rendered="true">'
                                    + '         </a>';
                }

                var htmlTags = '';
                if (list[i].newsTag != null) {
                    var newsTags = list[i].newsTag.split(',');
                    for (pos in newsTags) {
                        htmlTags += '<span class="label woo-news-label">' + newsTags[pos] + '</span>';
                    }
                }

                var newsPublishTime = list[i].newsPublishTime;
                newsPublishTime = newsPublishTime.substring(0, newsPublishTime.indexOf(' '));

                var liInfo =
                        '           <li class="list-group-item woo-news-item paddingt20 paddingb20 row">'
                                + '             <div class="media">' + htmlImg
                                + '                 <div class="media-body col-md-8 col-xs-12">'
                                + '                     <a class="fontem font20" target="_blank" href="/jsp/e_business/e_business_detail.jsp?newsId=' + list[i].newsId + '"'
                                + '                        title="' + list[i].newsTitle + '">' + list[i].newsTitle + '</a>'
                                + '                     <p class="margint30 sizeh100">' + list[i].newsSummary + '</p>'
                                + '                     <p class="clra6a6a6">' + newsPublishTime + '&nbsp; '
                                + '                        <i class="fa fa-tag"></i>' + htmlTags
                                + '                     </p>'
                                + '                 </div>'
                                + '             </div>'
                                + '         </li>';

                $('#news_list').append(liInfo);
            }
        }, "json");
    }

</script>
</body>
</html>