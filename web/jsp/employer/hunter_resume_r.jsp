<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>个人简历</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64 paddingb20">
    <div id="resumeContentId" class="col-md-8">
        <div class="row clrbedeaf4 paddingt10 paddingb10 margin0">
            <div class="col-md-3">
                <div class="" style="position:relative;">
                    <img id="avatarId" class="circle sizew150 sizeh150" src="/img/avatar_default.jpg">
                </div>
            </div>
            <div id="basicInfoId" class="col-md-6">
            </div>

            <div class="col-md-3">
                <div>
                    <button id="doFavoriteBtnId" class="notfavorited floatright btn"
                            onclick="doDownloadResume('${param.jobHunterId}',
                                    '${param.employerId}')" data-toggle="modal"
                            data-target="#myModal">
                    </button>
                    <button id="favoritedBtnId" style="display: none" disabled="disabled"
                            class="favorited floatright btn">
                    </button>
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span
                                            aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title fontem" id="myModalLabel">收藏</h4>
                                </div>
                                <div class="modal-body fontem">
                                    你尚未登录，请登录后再收藏！
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary"
                                            onclick="javascript:window.location.href='/login.jsp'">登录
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="selfDepictId">

        </div>
        <div id="expectId">

        </div>
        <div id="eduExperienceId">

        </div>
        <div id="workExperienceId">

        </div>
        <div id="trainExperienceId">

        </div>
        <div id="productId">

        </div>
    </div>
    <div class="col-md-offset-1 col-md-3">
        <label id="downloadedId" class="clr440062 marginb10 text-right">3/2查看</label>

        <div class="progress margint10">
            <div id="downloadedProg" class="progress-bar clrb8e44ad" role="progressbar" aria-valuenow="60"
                 aria-valuemin="0" aria-valuemax="100">
                <span class="sr-only">60% Complete</span>
            </div>
        </div>

        <div id="canvas-holder" style="position: relative;">
            <canvas id="chart-area1" class="sizew150 sizeh150"></canvas>
            <div class="doughnut">
                <p class="font15 fontem text-center marginb0">应约率</p>

                <p id="joinratio" class="font40 fontem text-center"></p>
            </div>
            <p class="clra6a6a6">*在以往应聘中，对方接受面试邀请后，应约的比率</p>
        </div>
        <div style="position: relative;">
            <canvas id="chart-area2" class="sizew150 sizeh150"></canvas>
            <div class="doughnut">
                <p class="font15 fontem text-center marginb0">跳失率</p>

                <p id="acceptratio" class="font40 fontem text-center"></p>
            </div>
            <p class="clra6a6a6">*在以往应聘中，面试通过，却没来上班的比率</p>
        </div>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>

<script type="text/javascript">
    var favorited = false;
    var downloaded = false;
    var downNum = 0;
    var totalNum = 0;
    var employer_favorite_hunter_id = null;
    var hunterTel = null;
    $(document).ready(function () {
        if (${sessionScope.username != null}) {
            $("#doFavoriteBtnId").attr("data-toggle", "");
            $("#doFavoriteBtnId").attr("data-target", "");
        }
        //查询是否已经下载过，收藏过，已经下载了多少，还有多少可以下载。
        var param_data = {
            jobHunterId: escape(${param.jobHunterId}),
            employerId: escape(${param.employerId})
        };

        $.ajax({
            type: "post",
            url: '/page/demo/employer/favorite',
            data: param_data,
            dataType: "json",
            async: false,
            success: function (msg) {
                var employerFavorite = msg.employer_favorite;
                if (employerFavorite != undefined) {
                    favorited = employerFavorite.favorited == 0 ? false : true;
                    downloaded = employerFavorite.downloaded == 0 ? false : true;
                    employer_favorite_hunter_id = employerFavorite.employerFavoriteHunterId;
                    if (favorited) {
                        $("#doFavoriteBtnId").hide();
                        $("#favoritedBtnId").show();
                    }
                    if (downloaded) {
                        $("#seePhoneBtnId").attr("disabled", true);
                    }
                }
            }
        }, "json");

        $.ajax({
            type: "post",
            url: '/page/demo/employer/employerdetail',
            data: param_data,
            dataType: "json",
            async: false,
            success: function (msg) {
                var employerDetail = msg.employer_detail;
                downNum = employerDetail.resumeHaveDownloadNum == undefined ? 0 : employerDetail.resumeHaveDownloadNum;
                totalNum = employerDetail.resumeDownloadLimit == undefined ? 0 : employerDetail.resumeDownloadLimit;

                if (downNum == totalNum) {
                    $("#seePhoneBtnId").attr("disabled", true);
                }

                $("#downloadedProg").css("width", (downNum / totalNum) * 100 + '%');
                $("#downloadedId").text("" + downNum + "/" + totalNum + "查看");
            }
        }, "json");
        queryallMyJobs(${param.employerId});

        queryResume('${param.jobHunterId}', '${param.employerId}', '${sessionScope.username}', '${sessionScope.password}');
    });

    window.onload = function () {

        $.post("/page/demo/employer/huntercredit", $.param({jobHunterId: ${param.jobHunterId}}), function (data) {
            var haveJoin = 0;
            var haveNotJoin = 0;
            var haveAccept = 0;
            var haveNotAccept = 0;
            for (var i = 0; i < data.job_hunter_credit.length; ++i) {
                if (data.job_hunter_credit[i].haveJoin == 0) {
                    haveNotJoin++;
                } else {
                    haveJoin++;
                }
                if (data.job_hunter_credit[i].haveAccept == 0) {
                    haveNotAccept++;
                } else if (data.job_hunter_credit[i].haveAccept == 1) {
                    haveAccept++;
                }
            }
            haveNotJoin = Math.round(haveNotJoin / (haveNotJoin + haveJoin) * 100);
            haveJoin = 100 - haveNotJoin;
            var doughnutData = [
                {
                    value: haveJoin,
                    color: "#b1d456",
                    highlight: "#b1d456"
//                    label: haveJoin
                },
                {
                    value: haveNotJoin,
                    color: "#e6f0cd",
                    highlight: "#e6f0cd"
//                    label: haveNotJoin
                }
            ];

            haveNotAccept = Math.round(haveNotAccept / (haveNotAccept + haveAccept) * 100);
            haveAccept = 100 - haveNotAccept;
            var doughnutData2 = [
                {
                    value: haveNotAccept,
                    color: "#f19049",
                    highlight: "#f19049"
//                    label: haveNotJoin
                },
                {
                    value: haveAccept,
                    color: "#fde1b1",
                    highlight: "#fde1b1"
//                    label: haveJoin
                }
            ];

            $("#joinratio").text(haveJoin + "%");
            var ctx = document.getElementById("chart-area1").getContext("2d");
            window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive: true});

            $("#acceptratio").text(haveAccept + "%");
            var ctx = document.getElementById("chart-area2").getContext("2d");
            window.myDoughnut = new Chart(ctx).Doughnut(doughnutData2, {responsive: true});

        }, 'json');
    };

</script>
</body>
</html>