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
    <div id="header-wrapper" class="" style="background-image: url(/images/hainanbg1.jpg);">
        <div id="header">

            <!-- Nav -->
            <nav id="nav">
                <ul>
                    <!-- <li class="current"><a href="index.html">Home</a></li> -->
                    <li><a href="#intro">公司介绍</a></li>
                    <li><a href="#main">招聘职位</a></li>
                    <li><a href="#highlights">招聘流程</a></li>
                </ul>
            </nav>

        </div>
    </div>

    <!-- Intro -->
    <div id="intro-wrapper" class="wrapper style1">
        <div class="title">公司介绍</div>
        <section id="intro" class="container wrapper2">
            <!-- <p class="style1">一、海南航空简介</p> -->
            <div class="inner alt" id="employerinfo">
            </div>
        </section>
    </div>

    <!-- Main -->
    <div class="wrapper style3">
        <div class="title">招聘职位</div>
        <div id="main" class="container">
            <section id="features">
                <p align="center">可点击以下各岗位快速投递简历</p>
                <br>

                <table class="table table-hover">
                    <tbody id="jobs">
                    </tbody>
                </table>
                <br>

                <div class="text-center">
                    <button id="diliver" class="btn btn-primary" onclick="diliver()">立即投递</button>
                </div>
            </section>
        </div>
    </div>

    <!-- Highlights -->
    <div class="wrapper style2">
        <div class="title">招聘流程</div>
        <div id="highlights" class="container">
            <div class="">
                <p id="instruction" align="center">

                </p>
            </div>
        </div>
    </div>

</div>

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
            data: $.param({preachId: ${param.id}}),
            url: '/page/demo/getpreachbyid',
            success: function (preachInfo) {
                $("#instruction").html(preachInfo.preach.instruction);
            }
        });
        var employerIds = '', i = 0;
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param({preachId: ${param.id}}),
            url: '/page/demo/getemployerbypreachid',
            success: function (preachemployerInfo) {
                var employerIds = '', i = 0;
                $("#employerinfo").html('');
                for (i = 0; i < preachemployerInfo.employer.length; ++i) {
                    employerIds += preachemployerInfo.employer[i].employerId + ',';
                    $("#employerinfo").append('<section class="spotlight">'
                            + '<div class="image"><img src="' +
                            preachemployerInfo.employer[i].logoPath +
                            '" alt=""/></div>'
                            + '<div class="content">'
                            + '<h3>' +
                            preachemployerInfo.employer[i].employerName +
                            '</h3>' +
                            '<p>' +
                            preachemployerInfo.employer[i].employerDesc +
                            '</p></div></section>');

                }
                employerIds = employerIds.substring(0, employerIds.length - 1);
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: '/page/demo/getjobsbypreachid',
                    data: $.param({employerIds: employerIds}),
                    success: function (msg) {
                        if (msg.job != null) {
                            $("#jobs").html('');
                            var diliverd = false;
                            for (var i = 0; i < msg.job.length; ++i) {
                                diliverd = false;
                                if ('${sessionScope.jobHunterId}' != '') {
                                    var param_data = {
                                        jobId: msg.job[i].jobId,
                                        jobHunterId: '${sessionScope.jobHunterId}'
                                    };
                                    $.ajax({
                                        type: "POST",
                                        dataType: "json",
                                        data: $.param(param_data),
                                        async: false,
                                        url: "/page/demo/job/favoritestatus",
                                        success: function (data) {
                                            if (data.retMsg == 'success' && data.job_hunter_favorite_job.diliver == 1) {
                                                diliverd = true;
                                            }
                                        }
                                    });
                                }
                                $("#jobs").append('<tr class="tr-class-1">' +
                                        '<td class="td-class-2">' +
                                        msg.job[i].employerName +
                                        '</td>' +
                                        '<td class="td-class-3">' +
                                        '<a href="/jsp/job/job_detail_r.jsp?jobId=' +
                                        msg.job[i].jobId +
                                        '">' +
                                        msg.job[i].jobName +
                                        '</a>' +
                                        '</td>' +
                                        '<td class="td-class-4">' +
                                        salaryVal(msg.job[i].salaryBegin, msg.job[i].salaryEnd) +
                                        '</td>' +
                                        '<td class="td-class-5"><input name="checkbox" type="checkbox" value="' +
                                        msg.job[i].jobId +
                                        '" employerid="' +
                                        msg.job[i].employerId +
                                        '" style="width: 13pt;" ' +
                                        (diliverd ? 'DISABLED' : '') +
                                        '/>' +
                                        '</td>' +
                                        '</tr>');
                            }
                        }
                    }
                });
            }
        });
    });

    function diliver() {
        if ('${sessionScope.jobHunterId}' == '') {
            if (window.confirm("还没有登录哦！")) {
                window.location = "/login.jsp";
            }
        } else {
            var cnt = 0, employerids = new Array(), exists = false, selectedcnt = 0;
            $("input[name='checkbox']").each(function () {
                if (this.checked) {
                    selectedcnt ++;
                    exists = false;
                    for (var i = 0; i < cnt; ++i) {
                        if ($(this).attr("employerid") == employerids[cnt]) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        cnt++;
                        employerids[cnt] = $(this).attr("employerid");
                    }
                }
            });
            if (selectedcnt > 2){
                alert("您最多只能选择两家两个职位进行投递！");
            }
//            if (employerids.length > 2) {
//                alert("您最多只能选择两家公司的职位进行投递！");
//            }
            else if (employerids.length == 0) {
                alert("请您勾选职位！");
            } else {
                $("#diliver").removeClass("btn-primary").addClass("btn-success").text("已投递").attr("disabled", "disabled");
                var param_data = new Object();
                param_data.jobHunterId = '${sessionScope.jobHunterId}';
                $("input[name='checkbox']").each(function () {
                    if (this.checked) {
                        param_data.employerId = $(this).attr("employerid");
                        param_data.jobId = $(this).attr("value");
                        $.post("/page/demo/job/applyjob", $.param(param_data));
                    }
                });
            }

        }
    }

    var $window = $(window),
            $body = $('body');
    // Title Bar.
    $(
            '<div id="titleBar">' +
                    '<a href="#navPanel" class="toggle"></a>' +
                    '<span class="title">' + $('#logo').html() + '</span>' +
                    '</div>'
    )
            .appendTo($body);

    // Navigation Panel.
    $(
            '<div id="navPanel">' +
                    '<nav>' +
                    $('#nav').navList() +
                    '</nav>' +
                    '</div>'
    )
            .appendTo($body)
            .panel({
                delay: 500,
                hideOnClick: true,
                hideOnSwipe: true,
                resetScroll: true,
                resetForms: true,
                side: 'left',
                target: $body,
                visibleClass: 'navPanel-visible'
            });

    // Fix: Remove navPanel transitions on WP<10 (poor/buggy performance).
    if (skel.vars.os == 'wp' && skel.vars.osVersion < 10)
        $('#titleBar, #navPanel, #page-wrapper')
                .css('transition', 'none');
</script>
</body>
</html>