<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>我的投递</title>
    <%@ include file="/jsp/comm/header.jsp" %>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css"/>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="col-md-8">
        <ul class="list-inline bottombar margint20">
            <li class="marginb10 paddingb10 active" data="1"><a id="watchedbarid" href="#">
                <span class="fontem solidborder1">已被查看</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="2"><a id="interviewbarid" href="#">
                <span class="fontem solidborder1">入选面试</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="3"><a id="completeinterviewbarid" href="#">
                <span class="fontem solidborder1">完成面试</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="4"><a id="deletedbarid" href="#">
                <span class="fontem solidborder1"> 未入选</span></a></li>
            <li class="marginb10 paddingb10" data="5"><a id="alldiliver" href="#">
                <span class="fontem solidborder1 clr419641">所有投递</span></a></li>
        </ul>
        <ul id="tableList" class="list-group margint20">
        </ul>
        <ul class="pagination " id="pagination">
        </ul>
    </div>
    <%@ include file="/jsp/hunter/hunter_nav.jsp" %>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript" src="/js/star-rating.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-checkbox.min.js"></script>
<script type="text/javascript">
var jHJobsIList;//全局变量
$(document).ready(function () {
    $("#applypage").addClass("active");
    var param_data = {
        jobHunterId: escape('${param.jobHunterId}')
    }

    var url = '/page/demo/job/hunterapplyjobs?' + 'jobHunterId=' + escape('${param.jobHunterId}');
    $.post(
            url,
            null,
            function (data) {
                if (data.retCode == "fail") {
                    return;
                } else {
                    var list = data.job_list;
                    jHJobsIList = list;
                    if (jHJobsIList != null) {
                        var watchedNoticeTimes = 0;
                        var tobeInterviewNoticeTimes = 0;
                        var completeinterviewNoticeTimes = 0;
                        var deletedNoticeTimes = 0;
                        if (jHJobsIList != null) {
                            for (var i = 0; i < jHJobsIList.length; ++i) {
                                if (jHJobsIList[i].jobInteract.employerStatusChanged == 1) {
                                    if (jHJobsIList[i].jobInteract.employerStatus == 2) {
                                        ++watchedNoticeTimes;
                                    } else if (jHJobsIList[i].jobInteract.employerStatus == 3 && jHJobsIList[i].jobInteract.joinFeedback == 0 && jHJobsIList[i].jobInteract.interviewFeedback == 0) {
                                        ++tobeInterviewNoticeTimes;
                                    } else if (jHJobsIList[i].jobInteract.employerStatus == 3 && jHJobsIList[i].jobInteract.joinFeedback == 1) {
                                        ++completeinterviewNoticeTimes;
                                    } else if (jHJobsIList[i].jobInteract.employerStatus == 4) {
                                        ++deletedNoticeTimes;
                                    }
                                }
                            }
                        }
                        if (watchedNoticeTimes > 0) {
                            $("#watchedbarid").append("<span class=\"badge clrbred\">" + watchedNoticeTimes + "</span>")
                        }
                        if (tobeInterviewNoticeTimes > 0) {
                            $("#interviewbarid").append("<span class=\"badge clrbred\">" + tobeInterviewNoticeTimes + "</span>")
                        }
                        if (completeinterviewNoticeTimes > 0) {
                            $("#completeinterviewbarid").append("<span class=\"badge clrbred\">" + completeinterviewNoticeTimes + "</span>")
                        }
                        if (deletedNoticeTimes > 0) {
                            $("#deletedbarid").append("<span class=\"badge clrbred\">" + deletedNoticeTimes + "</span>")
                        }
                        queryApplyWatched();
                        //显示完了，将通知状态进行重置
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "/page/demo/job/interact/hunter/regress",
                            data: $.param(param_data),
                            success: function (msg) {
                            }
                        });
                    }
                }
            },
            "json"
    );
    $(".bottombar li").click(function () {
        if ($(this).attr("data") == 1) {
            $("#watchedbarid .badge").remove();
            queryApplyWatched();
        } else if ($(this).attr("data") == 2) {
            $("#interviewbarid .badge").remove();
            queryToBeInterview();
        } else if ($(this).attr("data") == 3) {
            $("#completeinterviewbarid .badge").remove();
            queryCompleteInterview();
        } else if ($(this).attr("data") == 4) {
            $("#deletedbarid .badge").remove();
            queryApplyBeDeleted();
        } else if ($(this).attr("data") == 5) {
            queryAllApply();
        }
    });
});

function acceptInvite(btn, i) {
    jHJobsIList[i].jobInteract.hunterStatus = 3;
    jHJobsIList[i].jobInteract.hunterStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jHJobsIList[i].jobInteract),
        url: "/page/demo/job/interact/hunter/mod"
    });
    $(btn).attr('disabled', "true");
    $(btn).text("已接受邀请");
    $(btn).parent().next().remove();
}

function rejectInvite(btn, i) {
    jHJobsIList[i].jobInteract.hunterStatus = 4;
    jHJobsIList[i].jobInteract.hunterStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jHJobsIList[i].jobInteract),
        url: "/page/demo/job/interact/hunter/mod"
    });
    $(btn).attr('disabled', "true");
    $(btn).text("已拒绝邀请");
    $(btn).parent().prev().remove();
}

function removeHunterInteractModel(btn, i) {
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    $(btn).parents(".list-group-item").remove();
    jHJobsIList[i].jobInteract.hunterStatus = 4;
    jHJobsIList[i].jobInteract.hunterStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jHJobsIList[i].jobInteract),
        url: "/page/demo/job/interact/hunter/mod",
        success: function (data) {

        }
    });
}

function removeHunterApplyModel(btn, jobId, i) {
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    $(btn).parents(".list-group-item").remove();
    var param_data = new Object();
    param_data.hunterStatus = 4;
    param_data.hunterStatusChanged = 1;
    param_data.jobHunterId = ${param.jobHunterId};
    param_data.jobId = jobId;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/interact/hunter/modbyhunterkey",
        success: function (data) {

        }
    });

    var param_data2 = new Object();
    param_data2.myFavoriteJobId = favoriteList[i].myFavoriteJobId;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data2),
        url: "/page/demo/job/removediliverjob",
        success: function (data) {

        }
    });
}

function setItem(i, noticeInfo2) {
    var timeIdentify = getTimeIdentity();
    //公司信息
    var logoPath = '';
    if (jHJobsIList[i].job.logoPath == undefined) {
        logoPath = '/img/companydefault.png';
    } else {
        logoPath = jHJobsIList[i].job.logoPath;
    }
    var addHotTimesStr = "addHotTimes(\"" +
            jHJobsIList[i].job.employerId +
            "\",\"" +
            jHJobsIList[i].job.employerName +
            "\",\"" +
            logoPath +
            "\",\"" +
            jHJobsIList[i].job.jobId +
            "\")";
    var tagInfo = '<li><ul class="list-inline">';
    if (jHJobsIList[i].job.tag1 != null && jHJobsIList[i].job.tag1 != "") {
        tagInfo = tagInfo
                + "<li>"
                + "<span class=\"tag2\">"
                + jHJobsIList[i].job.tag1 + "</span></li>";
    }
    if (jHJobsIList[i].job.tag2 != null && jHJobsIList[i].job.tag2 != "") {
        tagInfo = tagInfo
                + "<li>"
                + "<span class=\"tag2\">"
                + jHJobsIList[i].job.tag2 + "</span></li>";
    }
    if (jHJobsIList[i].job.tag3 != null && jHJobsIList[i].job.tag3 != "") {
        tagInfo = tagInfo
                + "<li>"
                + "<span class=\"tag2\">"
                + jHJobsIList[i].job.tag3 + "</span></li>";
    }
    if (jHJobsIList[i].job.tag4 != null && jHJobsIList[i].job.tag4 != "") {
        tagInfo = tagInfo
                + "<li>"
                + "<span class=\"tag2\">"
                + jHJobsIList[i].job.tag4 + "</span></li>";
    }
    if (jHJobsIList[i].job.tag5 != null && jHJobsIList[i].job.tag5 != "") {
        tagInfo = tagInfo
                + "<li>"
                + "<span class=\"tag2\">"
                + jHJobsIList[i].job.tag5 + "</span></li>";
    }
    tagInfo = tagInfo + '</ul></li>';
    var workPlace = "";
    if (jHJobsIList[i].job.district == undefined
            || jHJobsIList[i].job.district === "") {
        workPlace = jHJobsIList[i].job.city;
    } else {
        workPlace = jHJobsIList[i].job.city + "-"
                + jHJobsIList[i].job.district;
    }

    var salary = salaryVal(jHJobsIList[i].job.salaryBegin, jHJobsIList[i].job.salaryEnd);
    var liInfo = "<li class=\"list-group-item\">"
            + "<div class=\"woolistitem\" "
            + ">"
            + "<div class=\"row\">"
            + "<div class=\"col-xs-2 col-md-2\">"
            + "<a class=\"media-left media-middle\"  href='javascript:void(0);' onclick='"
            + addHotTimesStr
            + "' >"
            + "<img class='size80' alt='" +
            jHJobsIList[i].job.employerName +
            "' src=\""
            + logoPath
            + "\""
            + "</a>"
            + "</div>"
            + "<div class=\"col-xs-5 col-md-5\">"
            + "<ul class=\"list-unstyled\">"
            + "<li ><a class=\"font16 clr440062 letterspace1 fontem\" href='javascript:void(0);' onclick='"
            + addHotTimesStr
            + "'>"
            + jHJobsIList[i].job.jobName
            + "</a></li>"
            + "<li>"
            + jHJobsIList[i].job.employerName
            + "</li>"
            + "<li class=\"clrec6941 font15\">月薪&nbsp;"
            + salary
            + "</li>"
            + "</ul>"
            + "</div>"
            + "<div class=\"col-xs-4 col-md-4\">"
            + "<ul class=\"list-unstyled\">"
            + "<li class=\"clra6a6a6\">"
            + workPlace
            + "</li>"
            + tagInfo
            + noticeInfo2
            + "</ul>"
            + "</div>"
            + "<div class='col-xs-1 col-md-1'>"
            + '<button class="backtransparent bordertransparent floatright"  ' +
            'data-toggle="modal"' +
            'data-target="#deleteModal' +
            timeIdentify +
            '">' +
            '<span class="fa fa-remove"></span></button>' +
            '<div class="modal fade" id="deleteModal' +
            timeIdentify +
            '" tabindex="-1" ' +
            'role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal">' +
            '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
            '</button>' +
            '<h4 class="modal-title fontem">删除</h4>' +
            '</div>' +
            '<div class="modal-body fontem">' +
            '你确定删除此条申请记录吗？' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
            '</button>' +
            '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeHunterInteractModel(this,' +
            i +
            ')">确定' +
            '</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            "</li>";
    $('#tableList').append(liInfo);
}

function queryApplyWatched() {
    $('#tableList').html("");
    if (jHJobsIList == null) {
        return;
    }
    for (var i = 0; i < jHJobsIList.length; i++) {
        var noticeInfo2 = '';
        if (jHJobsIList[i].jobInteract.employerStatus == 1 || jHJobsIList[i].jobInteract.employerStatus == 2) {
            noticeInfo2 = '<li class=" clra6a6a6">被查看：' +
                    formatDateTime(jHJobsIList[i].jobInteract.employerUpdateTime) +
                    '</li>';
            setItem(i, noticeInfo2);
        }
    }
}

function queryToBeInterview() {
    $('#tableList').html("");
    if (jHJobsIList == null) {
        return;
    }
    for (var i = 0; i < jHJobsIList.length; i++) {
        var noticeInfo2 = '';
        if (jHJobsIList[i].jobInteract.employerStatus == 3 && jHJobsIList[i].jobInteract.joinFeedback == 0 && jHJobsIList[i].jobInteract.interviewFeedback == 0) {
            if (jHJobsIList[i].jobInteract.hunterStatus >= 3) {
                noticeInfo2 =
                        '<li class=" clra6a6a6"><button class="btn btn-secondary" disabled=true>已接受邀请</i></button></li>';
            } else {
                noticeInfo2 =
                        '<li class=" clra6a6a6"><button class="btn btn-primary" onclick="acceptInvite(this,' +
                                i +
                                ')"> 接受面试</button></li>' +
                                '<li class=" clra6a6a6"><button class="btn btn-secondary" onclick="rejectInvite(this,' +
                                i +
                                ')"> 拒绝面试</button></li>';
            }
            setItem(i, noticeInfo2);
        }
    }
}

function queryCompleteInterview() {
    $('#tableList').html("");
    if (jHJobsIList == null) {
        return;
    }
    for (var i = 0; i < jHJobsIList.length; i++) {
        var noticeInfo2 = '';
        if (jHJobsIList[i].jobInteract.employerStatus == 3 && jHJobsIList[i].jobInteract.joinFeedback == 1) {
            if (jHJobsIList[i].jobInteract.interviewFeedback == 0) {
                var timeIdentify = getTimeIdentity();
                noticeInfo2 = '<li class="clra6a6a6"><button class="btn btn-secondary sizew80" data-toggle="modal"' +
                        'data-target="#deleteModal' +
                        timeIdentify +
                        '">' +
                        '评价</button></li>' +
                        '<div class="modal fade" id="deleteModal' +
                        timeIdentify +
                        '" tabindex="-1" ' +
                        'role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
                        '<div class="modal-dialog">' +
                        '<div class="modal-content">' +
                        '<div class="modal-header">' +
                        '<button type="button" class="close" data-dismiss="modal">' +
                        '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
                        '</button>' +
                        '<h4 class="modal-title fontem">评价</h4>' +
                        '</div>' +
                        '<div class="modal-body fontem">' +
                        '<div>' +
                        '<div class="form-inline"><div class="row"><div class="col-md-4"><label class="lineheight40">面试官很赞</label></div><div class="col-md-8"><input id="interviewernice" class="rating" min="0" max="5" step="0.5" data-size="xs" data-symbol="&#xf005;" data-glyphicon="false" data-rating-class="rating-fa"></div></div></div>' +
                        '<div class="form-inline"><div class="row"><div class="col-md-4"><label class="lineheight40">职位描述准确</label></div><div class="col-md-8"><input id="descriptionmatch" class="rating" min="0" max="5" step="0.5" data-size="xs" data-symbol="&#xf005;" data-glyphicon="false" data-rating-class="rating-fa"></div></div></div>' +
                        '<div class="form-inline"><div class="row"><div class="col-md-4"><label class="lineheight40">是否有笔试</label></div><div class="col-md-8"><input id="writtentest" type="checkbox" checked></div></div>' +
                        '</div></div>' +
                        '<div class="modal-footer">' +
                        '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
                        '</button>' +
                        '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="assess(this,' +
                        i +
                        ')">确定' +
                        '</button>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';

            } else {
                noticeInfo2 = '<li class="clra6a6a6">已评价</li>';
            }
            setItem(i, noticeInfo2);
        }
    }
    var $input = $('input.rating'), count = Object.keys($input).length;
    if (count > 0) {
        $input.rating();
    }
    $(':checkbox').checkboxpicker();
}

function assess(btn, i) {
    var param_data = new Object();
    param_data.joinWrittentest = $("#writtentest").next().find(".active").text() == '是' ? 1 : 0;
    var tem = parseInt(100 * parseFloat($('#interviewernice').prev().css('width')) / parseFloat($('#interviewernice').prev().parent().css('width')));
    param_data.interviewerNice = tem;
    tem = parseInt(100 * parseFloat($('#descriptionmatch').prev().css('width')) / parseFloat($('#descriptionmatch').prev().parent().css('width')));
    param_data.descriptionMatch = tem;
    param_data.jobId = jHJobsIList[i].jobInteract.jobId;
    param_data.jobHunterId = jHJobsIList[i].jobInteract.jobHunterId;

    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: '/page/demo/job/jobassessw',
        success: function (data) {
            var assessBtn = $(btn).parents(".modal").prev();
            assessBtn.text("已评价").attr("disabled", true);
            jHJobsIList[i].jobInteract.interviewFeedback = 1;
            $.ajax({
                type: "POST",
                dataType: "json",
                data: $.param(jHJobsIList[i].jobInteract),
                url: "/page/demo/job/interact/hunter/mod",
                success: function (data) {

                }
            });
        }
    });
}

function queryApplyBeDeleted() {
    $('#tableList').html("");
    if (jHJobsIList == null) {
        return;
    }
    for (var i = 0; i < jHJobsIList.length; i++) {
        var noticeInfo2 = '';
        if (jHJobsIList[i].jobInteract.employerStatus == 4) {
            noticeInfo2 = '<li class=" clra6a6a6">投递被清理：' +
                    formatDateTime(jHJobsIList[i].jobInteract.employerUpdateTime) +
                    '</li>';
            setItem(i, noticeInfo2);
        }
    }
}

var diliverList;
var favoriteList;
var haveQueryDilverList = false;
function queryAllApply() {
    $('#tableList').html("");
    if (!haveQueryDilverList) {
        var param_data = {
            jobHunterId: escape('${param.jobHunterId}')
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            url: '/page/demo/job/hunterdiliverjob',
            async: false,
            success: function (data) {
                haveQueryDilverList = true;
                diliverList = data.job_list;
                favoriteList = data.favorite_list;
            }
        });
    }
    if (diliverList == null) {
        return;
    }
    $('#tablelist').html("");
    for (var i = 0; i < diliverList.length; i++) {

        //公司信息
        var logoPath = '';
        if (diliverList[i].logoPath == undefined) {
            logoPath = '/img/companydefault.png';
        } else {
            logoPath = diliverList[i].logoPath;
        }
        var addHotTimesStr = "addHotTimes(\"" +
                diliverList[i].employerId +
                "\",\"" +
                diliverList[i].employerName +
                "\",\"" +
                logoPath +
                "\",\"" +
                diliverList[i].jobId +
                "\")";
        var tagInfo = '<li><ul class="list-inline">';
        if (diliverList[i].tag1 != null && diliverList[i].tag1 != "") {
            tagInfo = tagInfo
                    + "<li>"
                    + "<span class=\"tag2\">"
                    + diliverList[i].tag1 + "</span></li>";
        }
        if (diliverList[i].tag2 != null && diliverList[i].tag2 != "") {
            tagInfo = tagInfo
                    + "<li>"
                    + "<span class=\"tag2\">"
                    + diliverList[i].tag2 + "</span></li>";
        }
        if (diliverList[i].tag3 != null && diliverList[i].tag3 != "") {
            tagInfo = tagInfo
                    + "<li>"
                    + "<span class=\"tag2\">"
                    + diliverList[i].tag3 + "</span></li>";
        }
        if (diliverList[i].tag4 != null && diliverList[i].tag4 != "") {
            tagInfo = tagInfo
                    + "<li>"
                    + "<span class=\"tag2\">"
                    + diliverList[i].tag4 + "</span></li>";
        }
        if (diliverList[i].tag5 != null && diliverList[i].tag5 != "") {
            tagInfo = tagInfo
                    + "<li>"
                    + "<span class=\"tag2\">"
                    + diliverList[i].tag5 + "</span></li>";
        }
        tagInfo = tagInfo + '</ul></li>';
        var updateTime = diliverList[i].updateTime;
        if (updateTime != null
                && updateTime.length > 10) {
            updateTime = updateTime.substr(0, 10);
        }
        var workPlace = "";
        if (diliverList[i].district == undefined
                || diliverList[i].district === "") {
            workPlace = diliverList[i].city;
        } else {
            workPlace = diliverList[i].city + "-"
                    + diliverList[i].district;
        }

        var timeIdentify = getTimeIdentity();
        var salary = salaryVal(diliverList[i].salaryBegin, diliverList[i].salaryEnd);
        var liInfo = "<li class=\"list-group-item\">"
                + "<div class=\"woolistitem\" "
                + ">"
                + "<div class=\"row\">"
                + "<div class=\"col-xs-2 col-md-2\">"
                + "<a class=\"media-left media-middle\" target='_blank' target='_blank' href='"
                + "/jsp/employer/employer_detail_r.jsp?employerId=" + diliverList[i].employerId
                + "' >"
                + "<img class='size80' alt='" +
                diliverList[i].employerName +
                "' src=\""
                + logoPath
                + "\""
                + "</a>"
                + "</div>"
                + "<div class=\"col-xs-10 col-md-10\">"
                + "<div class='marginb10'><a class=\"font16 clr440062 letterspace1 fontem\"  href='javascript:void(0);' onclick='"
                + addHotTimesStr
                + "'>"
                + diliverList[i].jobName
                + " <span class='font12'>["
                + workPlace
                + "]</span>"
                + "</a>" +
                '<button class="backtransparent bordertransparent floatright"  ' +
                'data-toggle="modal"' +
                'data-target="#deleteModal' +
                timeIdentify +
                '">' +
                '<span class="fa fa-remove"></span></button>' +
                '<div class="modal fade" id="deleteModal' +
                timeIdentify +
                '" tabindex="-1" ' +
                'role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
                '<div class="modal-dialog">' +
                '<div class="modal-content">' +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal">' +
                '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
                '</button>' +
                '<h4 class="modal-title fontem">删除</h4>' +
                '</div>' +
                '<div class="modal-body fontem">' +
                '你确定删除此条申请记录吗？' +
                '</div>' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
                '</button>' +
                '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeHunterApplyModel(this,' +
                diliverList[i].jobId + ',' + i +
                ')">确定' +
                '</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                "</div>"
                + "<div class=\"padding0 col-xs-5 col-md-5\">"
                + "<ul class=\"list-unstyled\">"
                + "<li class=\"clrec6941 font15\">月薪&nbsp;"
                + salary
                + "</li>"
                + "</ul>"
                + "</div>"
                + "<div class=\"col-xs-5 col-md-5\">"
                + "<ul class=\"list-unstyled\">"
                + tagInfo
                + "</ul>"
                + " </div>"
                + "</div>" + "</div>"
                + "<div class=''>"
                + "<ul class=\"list-inline list-bottom-right\">"
                + "<li><span class=\"glyphicon comment clra6a6a6\">"
                + regular(diliverList[i].jobCommentCnt)
                + "</span></li>"
                + "<li><span class=\"glyphicon thumbup clra6a6a6\">"
                + regular(diliverList[i].jobPraisedCnt)
                + "</span></li>"
                + "<li class='clra6a6a6'>"
                + updateTime
                + "</li>"
                + " </ul>"
                + "</div></div>"
                + "</li>";

        $('#tableList').append(liInfo);
    }
}

</script>
</body>
</html>