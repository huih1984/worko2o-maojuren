<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宣讲会列表</title>
    <meta charset="utf-8"/>
    <link href="//cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!--[if lte IE 8]>
    <script src="/js/html5shiv.min.js"></script><![endif]-->
    <link rel="stylesheet" href="/css/html5up.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/css/ie8.css"/><![endif]-->
</head>
<body class="homepage">
<div id="page-wrapper">

    <!-- Header -->
    <div id="header-wrapper" class="wrapper">
        <div id="header">

            <!-- Logo -->
            <div id="logo">
                <h1><a href="preachlist.jsp">校园宣讲会</a></h1>
            </div>

        </div>
    </div>

    <!-- Intro -->
    <div id="intro-wrapper" class="wrapper style1">
        <div class="title">近期列表</div>
        <section id="intro" class="container">
            <p class="style2" style="color: rgba(255, 255, 255, 0.74902);">

            <table style="color: rgb(145, 148, 153);" id="table-transform" data-toolbar="#transform-buttons"
                   class="table table-hover">
                <thead>
                <tr>
                    <th style="">
                        <div class="th-inner ">举办时间</div>
                        <div class="fht-cell"></div>
                    </th>
                    <th style="">
                        <div class="th-inner ">名称</div>
                        <div class="fht-cell"></div>
                    </th>
                    <th style="">
                        <div class="th-inner ">学校</div>
                        <div class="fht-cell"></div>
                    </th>
                    <th style="">
                        <div class="th-inner ">校区</div>
                        <div class="fht-cell"></div>
                    </th>
                    <th style="">
                        <div class="th-inner ">地点</div>
                        <div class="fht-cell"></div>
                    </th>
                </tr>
                </thead>
                <tbody id="tbody">
                </tbody>
            </table>
            </p>
        </section>
    </div>


</div>

<!-- Scripts -->

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script src="/js/jquery.dropotron.min.js"></script>
<script src="/js/skel.min.js"></script>
<script src="/js/skel-viewport.min.js"></script>
<script src="/js/util.js"></script>
<!--[if lte IE 8]>
<script src="/js/ie/respond.min.js"></script><![endif]-->
<script src="/js/html5up.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: '/page/demo/getpreach',
            success: function (msg) {
                if (msg.preach != null) {
                    $("tbody").html('');
                    for (var i = 0; i < msg.preach.length; ++i) {
                        $("tbody").append('<tr class="tr-class-1" onclick="javascript:window.location.href=\'/jsp/preach/preachdetail.jsp?id=' +
                                msg.preach[i].preachId +
                                '\'">' +
                                '<td class="td-class-1">' +
                                getDateStr(newDateAndTime(msg.preach[i].openDate)) + ' ' +
                                getTimeStr(newDateAndTime(msg.preach[i].openTime)) +
                                '</td>' +
                                '<td class="td-class-2">' +
                                msg.preach[i].preachName +
                                '</td>' +
                                '<td class="td-class-3">' +
                                msg.preach[i].college +
                                '</td>' +
                                '<td class="td-class-4">' +
                                msg.preach[i].campus +
                                '</td>' +
                                '<td class="td-class-5">' +
                                msg.preach[i].openPlace +
                                '</td>' +
                                '</tr>')

                    }
                }
                else {
                    $("tbody").html('<div class="text-center font20">暂时没有宣讲会，敬请期待哦！</div>');
                }
            }
        });
    });
</script>
</body>
</html>