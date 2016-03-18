<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>职位详情</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="header hidden-xs">
    <h1 class="title">
        <img src="/img/header-banner2.png" alt="猫举人网"/>
    </h1>
</div>
<div class="container main">

<div class="row">
<div id="jobInfo" class="col-md-8">
<div id="jobsummary" class="blockbackground">
    <div class="row clrbedeaf4 margin0">
        <div class="col-md-9 col-xs-12" style="padding-left:20px;">
            <div>
                <h4 id="jobName" class="font24 fontem  letterspace2 clr440062 paddingt10"></h4>
                <ul class="list-inline">
                    <li class="clrec6941"><i class="fa fa-cny clr440062 marginr10"></i><span id="salary"></span></li>
                    <li class="fontem"><i class="fa fa-map-marker clr440062 marginr10"></i><span id="workplace"></span>
                    </li>
                    <li>
                        <ul id="assessall" class="clrffa201 list-inline"></ul>
                    </li>
                </ul>
                <ul class="list-inline">
                    <li>
                        <ul id="tags" class="list-inline">
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-3 hidden-xs">
            <div class="text-right">
                <button id="doFavoriteBtnId" class="notfavorited btn clra6a6a6" data-toggle="modal"
                        data-target="#myModal">
                </button>
                <button id="favoritedBtnId" style="display: none" disabled="disabled"
                        class="favorited btn">
                </button>
                <div class="modal fade text-left" id="myModal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel"
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
        <ul class="list-inline col-md-7 col-xs-6" style="padding-left: 10px;">
            <li>
                <ul class="list-inline clra6a6a6">
                    <li id="questionnaire" style="display:none"><i class="fa fa-edit"></i></li>
                    <li>
                        <label>发布：</label>
                        <label id="updateTime"></label>
                    </li>
                    <li>
                        <label>截止：</label>
                        <label id="deadTime"></label>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="list-inline col-md-5 col-xs-6 clra6a6a6">
            <li>
                <button class="padding0 backtransparent bordertransparent"><i
                        class="fa fa-thumbs-o-up marginr2 clrec6941" onclick="praiseJob(this)"></i></button>
                <span id="praised_times">0</span>
            </li>
            <li><i class="fa fa-eye marginr2 clrec6941"></i>&nbsp;<span
                    id="read_times">0</span></li>

            <li><i class="fa fa-comment-o marginr2 clrec6941"></i>&nbsp;<span
                    id="comment_times">0</span></li>
        </ul>
        <ul class="list-inline col-md-7 hidden-xs">
            <li id="talkandweb" class="fontem"></li>
        </ul>
    </div>
    <div class="paddingl10 paddingr10">
        <div class="paddingt10 font20 fontem">
            <span class="sizeh20 sizew10 clrb440062 marginr10">&nbsp;</span><label>职位详情</label>
        </div>
        <div class="paddingt10">
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6  paddingl10">学历要求：</label>
                    <label id="eduReq"></label>
                </div>
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">专业要求：</label>
                    <label id="majorReq"></label>
                </div>
            </div>
        </div>
        <div class="">
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">经验要求：</label>
                    <label id="expReq"></label>
                </div>
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">性别要求：</label>
                    <label id="sexReq"></label>
                </div>
            </div>
        </div>
        <div class="">
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">职位类别：</label>
                    <label id="jobType"></label>
                </div>
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">招聘人数：</label>
                    <label id="numberReq"></label>
                </div>
            </div>
        </div>
        <div>
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <label class="clra6a6a6 paddingl10">工作方式：</label>
                    <label id="workPattern"></label>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="margint20">
    <div class="blockbackground paddingl10 paddingr10">
        <div class="paddingt10 font20 fontem">
            <span class="sizeh20 sizew10 clrb440062 marginr10">&nbsp;</span><label>职位描述</label>
        </div>
        <div id="jobDesc" class="paddingl10">

        </div>
        <div id="apply" class=" margint20 marginr30 marginb20">
            <div class="text-right">
                <button id="diliveredBtnId" class="btn btn-primary fontem" style="display: none"
                        disabled="disabled">已申请
                </button>
                <button id="doDiliverBtnId" class="btn btn-primary fontem" data-toggle="modal"
                        data-target="#myDeliverModal" type="submit">立即申请
                </button>
                <div class="modal fade text-left" id="myDeliverModal" tabindex="-1" role="dialog"
                     aria-labelledby="myDeliverModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title fontem" id="myDeliverModalLabel">申请</h4>
                            </div>
                            <div class="modal-body fontem">
                                你尚未登录，请登录后再申请！
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                                </button>
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
</div>
<div class="margint20 hidden-xs" id="assessdetail">
    <div class="solidborder1 paddingl4r4">
        <div class="margint20 font20 fontem">
            <span class="sizeh20 sizew10 clrb440062 marginr10">&nbsp;</span><label>评价</label>
        </div>
        <div class="paddingl10">
            <label class="clra6a6a6">*评价来自参加面试的求职者，仅供参考</label>

            <div class="row">
                <div class="col-md-4 col-xs-4"><label class="clra6a6a6">是否有笔试</label></div>
                <div class="col-md-8 col-xs-8"><label id="writtentest"></label><label class="clra6a6a6">
                    &nbsp;*（回答为是的个数）/（全部）</label></div>
            </div>
            <div class="row">
                <div class="col-md-4 col-xs-4"><label class="clra6a6a6">面试官很好</label></div>
                <div class="col-md-8 col-xs-8">
                    <ul id="interviewnice" class="clrffa201 list-inline"></ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-xs-4"><label class="clra6a6a6">职位描述符合度</label></div>
                <div class="col-md-8 col-xs-8">
                    <ul id="descriptionmatch" class="clrffa201 list-inline"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="publish_comment" class="hidden-xs margint20 paddingt10 paddingb10 clrbedeaf4 clearboth">
    <label class="marginl10 paddingl4r4 clra6a6a6 block">请文明评论公司</label>
    <textarea id="comment_textarea" placeholder="" name=""
              class="valid textarea marginl10 bdradius marginr10 paddingl4r4"></textarea>

    <div class="row margin0">
        <div class="col-md-6 col-xs-6">
            <div id="word_count" class="marginl10 paddingl4r4 clra6a6a6 clra6a6a6">你还可以输入 <span>500</span> 字</div>
        </div>
        <div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3 text-right">
            <button id="commentBtnId" class="btn btn-primary margint20 marginr30 sizew80 fontem"
                    data-toggle="modal"
                    data-target="#myCommentModal" onclick="commentPublish()">发表评论
            </button>
            <div class="modal fade" id="myCommentModal" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title fontem">评论</h4>
                        </div>
                        <div class="modal-body fontem">
                            你尚未登录，请登录后再发表评论！
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                            </button>
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
<div id="comment" class="solidborder1 margint20 marginb20  hidden-xs">
    <label class="paddingl4r4">用户评论</label>

    <div class="line1 clrba6a6a6 marginr10"></div>
</div>
</div>

<div id="employerInfo" class="col-md-3 col-md-offset-1 ">
    <div id="employerDetailInfo">
        <div class="row">
            <a href="/jsp/employer/employer_detail_r.jsp?employerId=23" target="_blank">
                <img id="logoPreview" class="sizew180 sizeh180 margint-90 marginb20 verticalmiddle" src="" alt=""></a>

            <div id="employertextinfo" class="col-xs-9">
                <h4 class="marginl4r4 font20" id="inputEmployer"></h4>
                <ul class="list-unstyled marginl4r4">
                    <li id="placeDetailId" class="marginb10 margint20"></li>
                    <li class="marginb10">卖品种类：<span id="saleTypeId"></span></li>
                    <li class="marginb10">公司规模：<span id="scaleId"></span></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="hrratioid" class="solidborder1 sizew210 hidden-xs">
        <label class="font20 fontem paddingl4r4">HR 效率</label>

        <div class="hrratio font20 clra6a6a6 paddingl10">还没数据哦~</div>
        <label class="clra6a6a6 paddingl4r4">近一个月内简历处理平均用时</label>
    </div>
    <div id="addressmapid" class="solidborder1 sizew210 margint20 hidden-xs">
        <label class="font20 fontem paddingl4r4">公司地址</label>

        <div>
            <label class="font15 fontem paddingl4r4"><span id="cityId"></span> <span id="districtId"></span></label>
        </div>
        <div id="smallmap"></div>
        <div class="text-center">
            <a href="javascript:;" data-toggle="modal" data-target="#mapModal">查看详细地图</a>
        </div>
        <div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" style="width: 640px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title fontem">公司位置</h4>
                    </div>
                    <div class="modal-body fontem">
                        <div id="baiduMap">
                            <div id="allmap"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</div>
<div class="modal fade" id="questionnairemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header clrbfff">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
            </div>
            <div id="previewcontent" class="modal-body marginlp5 marginr30">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary " data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="answer()">
                    提交
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<%@ include file="/jsp/comm/comm_foot.jsp" %>
<script src="/js/worko2o-question.js"></script>

<script type="text/javascript">
var employerId = 0;
var lat, lng;
var questionnaireId = 0;
$(document).ready(function () {
    var jobId = escape('${param.jobId}');
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,
        url: "/page/demo/job/jobdetail?jobId=" + jobId,
        success: function (data) {
            var value = data.job_detail;
            employerId = value.employerId;
            lat = value.lat;
            lng = value.lng;
            $("#cityId").text(value.city);
            $("#districtId").text(value.district);
            $("#praised_times").text(value.jobPraisedCnt);
            $("#read_times").text(value.readTimes);
            $("#jobName").text(value.jobName);
            if (value.salaryBegin == 0 && value.salaryEnd == 0) {
                $("#salary").text("面议");
            } else if (value.salaryBegin == 0) {
                $("#salary").text(value.salaryEnd + "元");
            } else if (value.salaryEnd == 0) {
                $("#salary").text(value.salaryBegin + "元");
            } else {
                $("#salary").text(value.salaryBegin + "-" + value.salaryEnd + "元");
            }
            $("#workplace").text(value.city + "-" + value.district);
            var tagInfo = '';
            var tagbefore = '<a class="tag sizew120 verticalmiddle">';
            var tagafter = '</a>';
            if (typeof(value.tag1) != "undefined" && value.tag1 != '') {
                tagInfo += tagbefore + value.tag1 + tagafter;
            }
            if (typeof(value.tag2) != "undefined" && value.tag2 != '') {
                tagInfo += tagbefore + value.tag2 + tagafter;
            }
            if (typeof(value.tag3) != "undefined" && value.tag3 != '') {
                tagInfo += tagbefore + value.tag3 + tagafter;
            }
            if (typeof(value.tag4) != "undefined" && value.tag4 != '') {
                tagInfo += tagbefore + value.tag4 + tagafter;
            }
            if (typeof(value.tag5) != "undefined" && value.tag5 != '') {
                tagInfo += tagbefore + value.tag5 + tagafter;
            }
            $("#tags").html(tagInfo);
            var website = '';
            if (regular(value.website) != '' && value.websiteShow == 1) {
                if (value.website.indexOf("http") < 0 && value.website.indexOf("https:") < 0) {
                    value.website = "http://" + value.website;
                }
                website = "<a target='_blank' href='"
                        + value.website
                        + "' class='label label-warning clrfff'>" +
                        "店铺" +
                        "</a>"
            }

            var qqtalk = '';
            if (value.qq != 0 && value.qq != null && !isMobile()) {
                qqtalk = getQqSection(value.qq);
            }
            $("#talkandweb").append(website);
            $("#talkandweb").append(qqtalk);
            $("#jobDesc").html(value.jobDesc);
            if (value.eduReq.indexOf("选择") > 0) {
                $("#eduReq").text('不限');
            } else {
                $("#eduReq").text(value.eduReq);
            }
            $("#expReq").text(value.expReq);
            $("#majorReq").text(value.majorReq);
            $("#sexReq").text(value.sexReq);
            $("#workPattern").text(value.workPattern);
            if (value.numberReq == 0) {
                $("#numberReq").text("不限");
            } else {
                $("#numberReq").text(value.numberReq + "人");
            }
            var jobMain = '';
            if (value.jobSubType.indexOf("选择") > 0) {
                jobMain = value.jobMainType + "-" + value.jobSubType
            } else {
                jobMain = value.jobMainType;
            }
            $("#jobType").text(jobMain);
            var date = newDateAndTime(value.updateTime);
            $("#updateTime").text(date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate());
            var date2 = newDateAndTime(value.deadTime);
            $("#deadTime").text(date2.getFullYear() + '-' + (date2.getMonth() + 1) + '-' + date2.getDate());
            //公司信息
            $('#inputEmployer').text(value.employerName);
            if (value.logoPath != undefined) {
                $('#logoPreview').attr("src", "" + value.logoPath);
            }
            if (value.questionnaireId != null && value.questionnaireOn == 1) {
                $("#questionnaire").show();
                questionnaireId = value.questionnaireId;
            }
            $('#logoPreview').attr("alt", "" + value.employerName);
            $('#logoPreview').parent("a").attr("href", "/jsp/employer/employer_detail_r.jsp?employerId=" + value.employerId);
            var employerInfo = $('#employerInfo');
            $("#saleTypeId").text(value.saleType);
            $("#scaleId").text(value.employerScale);
            if(value.preachJob == 1){
                $("#doDiliverBtnId").remove();
            }
            queryHRRatio(value.employerId, document.getElementById("hrratioid"));
        }
    });

    $("#comment_textarea").bind('input propertychange', function () {
        checkLength(this, 500);
        $("#word_count").find("span").text((500 - this.value.length));
    });
    queryComment();
    initUI();
    queryAssess('${param.jobId}');

    if (isMobile()) {
        <%session.setAttribute("isMobile", true);%>
        $("#employerDetailInfo").addClass("blockbackground paddingt10 paddingl10");
        $("#logoPreview").parent().addClass("col-xs-3");
        $("#employertextinfo").addClass("col-xs-9");
        $("#employerDetailInfo").insertAfter($("#jobsummary"));
    } else {
        <%session.setAttribute("isMobile", false);%>
    }
});

function deprecateComment(btn, commentId) {
    var num = parseInt($(btn).next("span").text());
    $.ajax({
        type: "post",
        url: "/page/demo/job/modjobcomment",
        data: $.param({commentId: commentId, deprecateThisComment: num}),
        async: false,
        success: function (json) {
            var data = $.parseJSON(json);
            var value = data.job_comment_detail;
            $(btn).next("span").text(value.deprecateThisComment);
        }
    });
}

function priseComment(btn, commentId) {
    var num = parseInt($(btn).next("span").text());
    $(btn).next("span").text(num);
    $.ajax({
        type: "post",
        url: "/page/demo/job/modjobcomment",
        data: $.param({commentId: commentId, praiseThisComment: num}),
        async: false,
        success: function (json) {
            var data = $.parseJSON(json);
            var value = data.job_comment_detail;
            $(btn).next("span").text(value.praiseThisComment);
        }
    });
}

function queryComment() {
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,
        url: "/page/demo/job/jobcomments?jobId=" + escape(${param.jobId}),
        success: function (data) {
            var list = data.job_comment_list;
            if (list != undefined) {
                $("#comment_times").text(list.length);
            }
            $("#comment").html("");
            if (list == null) {
                $("#comment").append('<p class="clra6a6a6">还没有人发起评论，来抢占沙发!</p>' +
                        '<p  class="text-center"><img src="../../img/sofa.png" ' +
                        'class="horizontalmiddle size80"></p>'
                );
            } else {
                for (var i = 0; i < list.length; i++) {
                    var firstline = '';
                    if (i < list.length - 1) {
                        firstline = '<div class="bottomdashedborder margint10">';
                    } else {
                        firstline = '<div class="paddingt10">';
                    }
                    var commentInfo = firstline +
                            '<label style="display: none"> ' +
                            list[i].commentId +
                            '</label>'
                            + '<div class="row">'
                            + '<div class="col-md-1 col-xs-1">'
                            + '<img class="size34 img-circle" src="'
                            + list[i].jobHunterAvatarPath
                            + '" >'
                            + '</div>'
                            + '<div class="col-md-11 col-xs-11">'
                            + '<p><span>'
                            + list[i].jobHunterName
                            + '</span>'
                            + '</p>'
                            + '<p class="paddingr10">'
                            + list[i].jobComment
                            + '</p>'
                            + '<p>'
                            + '<div class="row"> '
                            + '<div class="col-md-6 col-xs-6">'
                            + '<ul class="list-inline">'
                            + '<li><i class="fa fa-thumbs-o-up" style="cursor: pointer" onclick="priseComment(this,' +
                            list[i].commentId +
                            ')"></i> &nbsp;<span>'
                            + list[i].praiseThisComment
                            + '</span></li>'
                            + '<li><i class="fa fa-thumbs-o-down"  style="cursor: pointer" onclick="deprecateComment(this,' +
                            list[i].commentId +
                            ')"> </i> &nbsp;<span>'
                            + list[i].deprecateThisComment
                            + '</li>'
                            + '</ul>'
                            + '</div>'
                            + '<div class="col-md-6 col-xs-6">'
                            + '<span class="floatright paddingl4r4 clra6a6a6">'
                            + list[i].commentTime
                            + '</span>'
                            + '</div>'
                            + '</div>'
                            + '</div>'
                            + '</div>'
                            + '</div>';
                    $("#comment").append(commentInfo);
                }
            }
        }
    });
}

function commentPublish() {
    if (${sessionScope.username != null} &&
    ${sessionScope.userType == "hunter"})
    {
        if ($("#comment_textarea").val().length == 0) {
            $.simplyToast('请输入您的评论再按按钮,评论不能为空!', 'info');
            return;
        }
        var param_data = {
            jobId: '${param.jobId}',
            jobHunterId: '${sessionScope.jobHunterId}',
            jobHunterAvatarPath: '${sessionScope.avatarPath}',
            jobHunterName: '${sessionScope.username}',
            jobComment: $("#comment_textarea").val()
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            async: false,
            url: "/page/demo/job/addjobcomment",
            success: function (data) {
//                $("#commentBtnId").text("已评论");
            }
        });
        queryComment();
    }
}

function praiseJob(btn) {
    var temp = $(btn).attr("disabled");
    if (temp == undefined) {
        $(btn).attr("disabled", true);
        var param_data = {
            jobId: escape('${param.jobId}'),
            jobPraisedCnt: $("#praised_times").text()
        };
        $.post("/page/demo/job/update", $.param(param_data),
                function (json) {
                    var data = $.parseJSON(json);
                    var value = data.job_detail;
                    $("#praised_times").text(value.jobPraisedCnt);
                });
    }
}

/*检查是否已经登录，如果已经登录，设置收藏和申请按钮的状态*/
function initUI() {
    // var a = $.cookie('woo-log-in') > 1;
    // var b = $.cookie('woo-user-type') == 'hunter';
    var a = '<%=session.getAttribute("username") %>' != null;
    var b = '<%=session.getAttribute("userType") %>' == "hunter";


    if (a && b) {
        $("#commentBtnId").attr("data-toggle", "");
        $("#commentBtnId").attr("data-target", "");
        $("#doFavoriteBtnId").attr("data-toggle", "");
        $("#doFavoriteBtnId").attr("data-target", "");

        if (questionnaireId != 0) {
            $("#doDiliverBtnId").attr("data-target", "#questionnairemodal");
            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                data: $.param({questionnaireId: questionnaireId}),
                url: "/page/demo/questionnaire/rques",
                success: function (data) {
                    onPreviewByData($.parseJSON(data.questionnaire.content), 'houtai', $("#previewcontent"));
                }
            });
        } else {
            $("#doDiliverBtnId").attr("data-target", "");
            $("#doDiliverBtnId").attr("data-toggle", "");
            $("#doDiliverBtnId").click(function () {
                $(this).hide();
                $("#diliveredBtnId").show();
                $.extend(param_data, {employerId: employerId});
                $.post("/page/demo/job/applyjob", $.param(param_data));
            });
        }
        var param_data = {
            jobId: '${param.jobId}',
            jobHunterId: '${sessionScope.jobHunterId}'
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            url: "/page/demo/job/favoritestatus",
            success: function (data) {
                if (data.retCode == "fail") {
                    $("#doFavoriteBtnId").click(function () {
                        $(this).hide();
                        $("#favoritedBtnId").show();
                        $.post("/page/demo/job/addfavoritejob", $.param(param_data));
                    });
                } else {
                    if (data.job_hunter_favorite_job.favorited == 0) {
                        $("#doFavoriteBtnId").click(function () {
                            $(this).hide();
                            $("#favoritedBtnId").show();
                            $.post("/page/demo/job/addfavoritejob", $.param(param_data));
                        });
                    }
                    var value = data.job_hunter_favorite_job;
                    if (value.favorited == 1) {
                        $("#doFavoriteBtnId").hide();
                        $("#favoritedBtnId").show();
                    }
                    if (value.diliver == undefined || value.diliver == 0) {

                    } else {
                        $("#doDiliverBtnId").hide();
                        $("#diliveredBtnId").show();
                    }
                }
            }
        });
    }
}

function answer() {
    var check = true;
    var answer = new Array();
    $(".pre_div_question").each(function (i) {
        if (!check) {
            return;
        }
        if ($(this).attr('type') == 'radio' || $(this).attr('type') == 'picradio') {
            var checkedRadio = $(this).find('input:radio[name=optionsRadios' + i + ']:checked');
            if (checkedRadio.length == 0) {
                $.simplyToast("还有选项没有作答,请作答完再提交！", "warning");
                check = false;
                return;
            } else {
                answer.push($(this).find('input:radio[name=optionsRadios' + i + ']').index(checkedRadio));
            }
        } else if ($(this).attr('type') == 'checkbox' || $(this).attr('type') == 'piccheckbox') {
            var checkedRadio = $(this).find('input:checkbox[name=optionsRadios' + i + ']:checked');
            if (checkedRadio.length == 0) {
                $.simplyToast("还有选项没有作答,请作答完再提交！", "warning");
                check = false;
                return;
            } else {
                var para = '';
                var parent = $(this).find('input:checkbox[name=optionsRadios' + i + ']');
                $(this).find('input:checkbox[name=optionsRadios' + i + ']:checked').each(function (j) {
                    para += parent.index($(this));
                });
                answer.push(para);
            }
        } else {
            var text = $(this).find('textarea').val();
            if (text == '') {
                $.simplyToast("还有选项没有作答,请作答完再提交！", "warning");
                check = false;
                return;
            } else {
                answer.push(text);
            }
        }
    });
    var param_data = {
        jobId: '${param.jobId}',
        jobHunterId: '${sessionScope.jobHunterId}'
    };
    if (!check) {
        return;
    } else {
        $('#questionnairemodal').modal('hide');
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            data: $.param(param_data),
            url: "/page/demo/questionnaire/rquesans",
            success: function (data) {
                if (data.questionnaire_answer == null) {
                    $.extend(param_data, {questionnaireId: questionnaireId, answer: JSON.stringify(answer)});
                    $.post("/page/demo/questionnaire/wquesans", $.param(param_data));
                } else {
                    $.extend(param_data, {questionnaireAnswerId: data.questionnaire_answer.questionnaireAnswerId, answer: JSON.stringify(answer)});
                    $.post("/page/demo/questionnaire/mquesans", $.param(param_data));
                }
            }
        });

        $("#doDiliverBtnId").hide();
        $("#diliveredBtnId").show();
        $.extend(param_data, {employerId: employerId});
        $.post("/page/demo/job/applyjob", $.param(param_data));
    }
}

function queryAssess(jobId) {
    var param_data = new Object();
    param_data.jobId = jobId;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/jobassessr",
        success: function (data) {
            if (data.job_assess != null) {
                var writtentest = 0;
                var interviewerNice = 0;
                var descriptionMatch = 0;
                for (var i = 0; i < data.job_assess.length; ++i) {
                    if (data.job_assess[i].joinWrittentest == 1) {
                        writtentest++;
                    }
                    interviewerNice += data.job_assess[i].interviewerNice;
                    descriptionMatch += data.job_assess[i].descriptionMatch;
                }
                $("#writtentest").text(writtentest + "/" + data.job_assess.length);
                var percent = parseInt(interviewerNice / data.job_assess.length);
                var li = "<li class='font20'><span class='fa fa-star'></span></li>";
                var lihalf = "<li class='font20'><span class='fa fa-star-half-o'></span></li>";
                var text = '';
                for (var j = 0; j < percent / 20; ++j) {
                    text += li;
                }
                if (percent % 20 > 10) {
                    text += lihalf;
                }
                $("#interviewnice").html(text);

                var percent2 = parseInt(descriptionMatch / data.job_assess.length);
                text = '';
                for (var j = 0; j < percent2 / 20; ++j) {
                    text += li;
                }
                if (percent2 % 20 > 10) {
                    text += lihalf;
                }
                $("#descriptionmatch").html(text);
                var percent3 = (percent + percent2) / 2;
                text = '';
                for (var j = 0; j < percent3 / 20; ++j) {
                    text += li;
                }
                if (percent3 % 20 > 10) {
                    text += lihalf;
                }
                $("#assessall").html(text);
            } else {
                $("#assessdetail").remove();
            }
        }
    });
}

function initialize() {
    var smallMap = new BMap.Map("smallmap");
    var map = new BMap.Map("allmap");
    //控件添加
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.MapTypeControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());

    var marker = new BMap.Marker(new BMap.Point(lng, lat));  // 创建标注
    var marker2 = new BMap.Marker(new BMap.Point(lng, lat));  // 创建标注
    var point = new BMap.Point(lng, lat);//初始化坐标点
    map.centerAndZoom(point, 16);
    smallMap.centerAndZoom(point, 16);
    smallMap.enableScrollWheelZoom(true);//允许缩放
    /* map.centerAndZoom(, 15); */
    map.enableScrollWheelZoom(true);//允许缩放
    map.addOverlay(marker2);     // 将标注添加到地图中
    smallMap.addOverlay(marker);

    var gc = new BMap.Geocoder();//地址定位
    var local = new BMap.LocalSearch(map, {
        renderOptions: {map: map}
    });
}

function loadScript() {
    if (lat == null) {
        $("#addressmapid").remove();
    } else {
        var script = document.createElement("script");
        script.src = "http://api.map.baidu.com/api?v=2.0&ak=k0t9RN7hjcLKmDKMZarEc9k0&callback=initialize";//此为v1.5版本的引用方式
        // http://api.map.baidu.com/api?v=1.5&ak=您的密钥&callback=initialize"; //此为v1.4版本及以前版本的引用方式
        document.body.appendChild(script);
    }
}

window.onload = loadScript;

</script>
</body>
</html>