<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>职位申请人</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint20">
    <div class="col-md-8">
        <ul id="navhead" class="list-inline bottombar margint20">
            <li class="marginb10 paddingb10 active" data="1"><a id="newapplybarid" href="#">
                <span class="fontem solidborder1">新的求职者</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="2"><a id="interviewbarid" href="#">
                <span class="fontem solidborder1">已接受面试</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="3"><a id="passinterviewbarid" href="#">
                <span class="fontem solidborder1"> 通过面试</span><i class="fa fa-long-arrow-right marginl10"></i></a></li>
            <li class="marginb10 paddingb10" data="4"><a id="deletedbarid" href="#">
                <span class="fontem solidborder1"> 已放弃</span></a></li>
        </ul>
        <ul id="jobListGroupId" class="list-group margint20">
        </ul>
    </div>
    <%@ include file="employer_nav.jsp" %>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
var jIList;//全局变量
var quesExists = ${param.ques == '1'};
$(document).ready(function () {
    var param_data = {
        employerId: escape('${param.employerId}'),
        jobId: escape('${param.jobId}')
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/interact/employer/myinteractjobs",
        success: function (data) {
            jIList = data.job_interact_list;
            var jIListTemp = new Array();
            if (jIList == null) {
                return;
            }
            for (var j = 0; j < jIList.length; ++j) {
                if (jIList[j].employerStatus != 4) {
                    jIListTemp.push(jIList[j]);
                }
            }
            jIList = jIListTemp;
            if (jIList != null) {
                var newapplyNoticeTimes = 0;
                var interviewNoticeTimes = 0;
                var passInterviewNoticeTimes = 0;
                var deletedNoticeTimes = 0;
                for (var i = 0; i < jIList.length; ++i) {
                    if (jIList[i].hunterStatusChanged == 1 && jIList[i].hunterStatus == 2) {
                        ++newapplyNoticeTimes;
                    } else if (jIList[i].hunterStatus == 3 && jIList[i].hunterStatusChanged == 1) {
                        ++interviewNoticeTimes;
                    } else if (jIList[i].hunterStatus == 3 && jIList[i].joinFeedback == 1 && jIList[i].acceptFeedback == 0) {
                        ++passInterviewNoticeTimes;
                    } else if (jIList[i].hunterStatus == 4 && jIList[i].hunterStatusChanged == 1) {
                        ++deletedNoticeTimes;
                    }
                }
                if (newapplyNoticeTimes > 0) {
                    $("#newapplybarid").append("<span class=\"badge clrbred\">" + newapplyNoticeTimes + "</span>")
                }
                if (interviewNoticeTimes > 0) {
                    $("#interviewbarid").append("<span class=\"badge clrbred\">" + interviewNoticeTimes + "</span>")
                }
                if (passInterviewNoticeTimes > 0) {
                    $("#passinterviewbarid").append("<span class=\"badge clrbred\">" + passInterviewNoticeTimes + "</span>")
                }
                if (deletedNoticeTimes > 0) {
                    $("#deletedbarid").append("<span class=\"badge clrbred\">" + deletedNoticeTimes + "</span>")
                }
                if (${param.feedback == '1'}) {
                    $("#navhead").children().eq(0).removeClass("active");
                    $("#navhead").children().eq(1).addClass("active");
                    queryJobHunterInterview();
                } else if (${param.feedback == '2'}) {
                    $("#navhead").children().eq(0).removeClass("active");
                    $("#navhead").children().eq(2).addClass("active");
                    queryJobHunterPassInterview();
                } else {
                    queryJobHunterNew();
                }
                //显示完了，将通知状态进行重置
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/page/demo/job/interact/employer/regress",
                    data: $.param(param_data),
                    success: function (msg) {
                    }
                });
            }
        }
    });
    $(".bottombar li").click(function () {
        if ($(this).attr("data") == 1) {
            $("#newapplybarid .badge").remove();
            queryJobHunterNew();
        } else if ($(this).attr("data") == 2) {
            $("#interviewbarid .badge").remove();
            queryJobHunterInterview();
        } else if ($(this).attr("data") == 3) {
            $("#deletedbarid .badge").remove();
            queryJobHunterPassInterview();
        } else if ($(this).attr("data") == 4) {
            $("#deletedbarid .badge").remove();
            queryJobHunterDelete();
        }
    });
});

function invite(btn, i) {
    jIList[i].employerStatus = 3;
    jIList[i].employerStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jIList[i]),
        url: "/page/demo/job/interact/employer/mod"
    });
    $(btn).attr('disabled', "true");
    $(btn).text("已发送邀请");
    $(btn).parent().next().remove();
}

function rejInvite(btn, i) {
    jIList[i].employerStatus = 4;
    jIList[i].employerStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jIList[i]),
        url: "/page/demo/job/interact/employer/mod"
    });
    $(btn).attr('disabled', "true");
    $(btn).text("已拒绝面试");
    $(btn).parent().prev().remove();
}

function removeModel(btn, i) {
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    $(btn).parents(".list-group-item").remove();
    jIList[i].employerStatus = 4;
    jIList[i].employerStatusChanged = 1;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(jIList[i]),
        url: "/page/demo/job/interact/employer/mod",
        success: function (data) {

        }
    });
}

function getToggleStr(i, jobHunterId, jobId) {
    return '<div class="accordion-group margint20">' +
            '<div class="accordion-heading">' +
            '<a class="accordion-toggle" data-toggle="collapse" href="#collapseOne' +
            i +
            '" ' +
            'onclick="toggleques(' +
            jobHunterId + ',' + jobId + ',' + i +
            ')" style="text-decoration:underline">' +
            '点击查看问卷作答情况' +
            '</a>' +
            '</div>' +
            '<div id="collapseOne' +
            i +
            '" class="accordion-body collapse" style="height: 0px; ">' +
            '<div class="accordion-inner">' +
            ' <div id="previewcontent' +
            i +
            '" class="marginlp66 marginr30">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
}


function queryHunterInfo(param_data, i, statusInfo, questionnaire) {
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,
        data: $.param(param_data),
        url: "/page/demo/jobHunter/jobhunterbasic",
        success: function (data) {
            var jobHunterDetail = data.job_hunter_detail;
            var avatarPath = '', yearold = '', hunterSex = '', currentPlace = '', jobHunterName = '';
            if (jobHunterDetail.jobHunterAvatarPath != null && jobHunterDetail.jobHunterAvatarPath != '') {
                avatarPath = jobHunterDetail.jobHunterAvatarPath;
            } else {
                avatarPath = '../../img/avatar_default.jpg'
            }
            if (jobHunterDetail.jobHunterBirthday != null && jobHunterDetail.jobHunterBirthday != '') {
                yearold = '<li>' + calcusYear(jobHunterDetail.jobHunterBirthday) + '岁</li>';
            }
            if (jobHunterDetail.jobHunterSex != null && jobHunterDetail.jobHunterSex != '') {
                hunterSex = '<li>' + jobHunterDetail.jobHunterSex + '</li>';
            }
            if (jobHunterDetail.jobHunterCurrentCity != null && jobHunterDetail.jobHunterCurrentCity != ''
                    && jobHunterDetail.jobHunterCurrentDistrict != null && jobHunterDetail.jobHunterCurrentDistrict != '') {
                currentPlace = '<li>' + '目前所在地：' + jobHunterDetail.jobHunterCurrentCity +
                        '-' + jobHunterDetail.jobHunterCurrentDistrict + '</li>';
            }
            if (jobHunterDetail.jobHunterName != null && jobHunterDetail.jobHunterName != '') {
                jobHunterName = '<li>' + jobHunterDetail.jobHunterName + '</li>';
            }
            var tI = getTimeIdentity();
            $("#jobListGroupId").append('<li class="list-group-item">' +
                    '<div class="woolistitem">' +
                    '<div class="row">' +
                    '<div class="col-md-2">' +
                    '<a class="media-left media-middle" href="/jsp/employer/hunter_resume_r.jsp?jobHunterId=' +
                    jobHunterDetail.jobHunterId +
                    '&employerId=' +
                    escape('${param.employerId}') +
                    '">' +
                    '<img class="size80" src="' +
                    avatarPath +
                    '">' +
                    '</a></div>' +
                    '<div class="col-md-6">' +
                    '<ul class="list-inline">' +
                    jobHunterName +
                    yearold +
                    hunterSex +
                    '</ul>' +
                    '<ul class="list-inline">' +
                    currentPlace +
                    '</ul>' +
                    '<ul class="list-inline">' +
                    '<li>' +
                    jobHunterDetail.jobHunterBachelorDegree +
                    '</li>' +
                    '<li>' +
                    getExperienceByStartWorkYear(jobHunterDetail.jobHunterStartWorkYear) +
                    '</li>' +
                    '</ul>' +
                    '</div>' +
                    '<div class="col-md-3">' +
                    '<ul class="list-unstyled">' +
                    statusInfo +
                    '</ul>' +
                    '</div>' +
                    '<div class="col-md-1">' +
                    '<button style="background: transparent;border: transparent" data-toggle="modal" title="删除该申请人" data-target="#deleteModal' +
                    tI +
                    '">' +
                    '<span class="fa fa-trash-o"></span></button> ' +
                    '<div class="modal fade" id="deleteModal' +
                    tI +
                    '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
                    '<div class="modal-dialog">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span>' +
                    '</button>' +
                    '<h4 class="modal-title fontem" id="myModalLabel">删除</h4>' +
                    '</div>' +
                    '<div class="modal-body fontem">' +
                    '你确定删除此条申请记录吗？' +
                    '</div>' +
                    '<div class="modal-footer">' +
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
                    '</button>' +
                    '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeModel(this,' +
                    i +
                    ')">确定' +
                    '</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    (questionnaire && quesExists ? getToggleStr(i, jobHunterDetail.jobHunterId, '${param.jobId}') : '') +
                    '</div>' +
                    '</li>');
        }
    });
}

function queryJobHunterNew() {
    $("#jobListGroupId").html("");
    if (jIList == null) {
        return;
    }
    for (var i = 0; i < jIList.length; ++i) {
        var statusInfo = '';
        if (jIList[i].hunterStatus == 2) {
            if (jIList[i].employerStatus < 2) {
                jIList[i].employerStatus = 2;
                jIList[i].employerStatusChanged = 1;
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    data: $.param(jIList[i]),
                    url: "/page/demo/job/interact/employer/mod",
                    success: function (data) {

                    }
                });
            }
            param_data = {
                jobHunterId: jIList[i].jobHunterId
            }
            if (jIList[i].employerStatus >= 3) {
                statusInfo = '<li>投递：' +
                        formatDateTime(jIList[i].hunterUpdateTime) +
                        '</li>' +
                        '<li><button class="btn btn-secondary margint10" disabled=true>已邀请面试</i></button></li>';
            } else {
                statusInfo = '<li>投递：' +
                        formatDateTime(jIList[i].hunterUpdateTime) +
                        '</li>' +
                        '<li><button class="btn btn-primary" onclick="invite(this,' +
                        i +
                        ')">邀请面试</button></li>' +
                        '<li><button class="btn btn-secondary" onclick="rejInvite(this,' +
                        i +
                        ')">拒绝面试</button></li>';
            }
            queryHunterInfo(param_data, i, statusInfo, true);
        }
    }
}

function queryJobHunterInterview() {
    $("#jobListGroupId").html("");
    if (jIList == null) {
        return;
    }
    for (var i = 0; i < jIList.length; ++i) {
        param_data = {
            jobHunterId: jIList[i].jobHunterId
        }
        var statusInfo = '';
        if (jIList[i].hunterStatus == 3) {
            var joinFeed = false;
            var acceptFeed = false;
            var dayI = dayInterval(newDateAndTime(jIList[i].hunterUpdateTime), new Date());
            if (jIList[i].employerStatusChanged == 0 && jIList[i].joinFeedback == 0 &&
                    dayI >= 1) {
                joinFeed = true;
            }
            if (joinFeed) {
                statusInfo = '<li class="font12 clra6a6a6">接受面试：' +
                        formatDateTime(jIList[i].hunterUpdateTime) +
                        '</li><li><button class="btn btn-primary sizew150" onclick="hunterJoin(this, 1, ' +
                        jIList[i].jobHunterId + ',' + jIList[i].jobId + ',' +
                        jIList[i].jobInteractId +
                        ')">应约</button></li><li><button class="btn btn-secondary sizew150" onclick="hunterJoin(this, 0, ' +
                        jIList[i].jobHunterId + ',' + jIList[i].jobId + ',' +
                        jIList[i].jobInteractId +
                        ')">没应约</button></li>';
                queryHunterInfo(param_data, i, statusInfo);
            } else if (jIList[i].joinFeedback == 0) {
                statusInfo = '<li>接受面试：' +
                        formatDateTime(jIList[i].hunterUpdateTime) +
                        '</li>';
                queryHunterInfo(param_data, i, statusInfo);
            }
        }
    }
}

function queryJobHunterPassInterview() {
    $("#jobListGroupId").html("");
    if (jIList == null) {
        return;
    }
    for (var i = 0; i < jIList.length; ++i) {
        param_data = {
            jobHunterId: jIList[i].jobHunterId
        }
        var statusInfo = '';
        if (jIList[i].hunterStatus == 3) {
            var acceptFeed = false;
            var dayI2 = dayInterval(newDateAndTime(jIList[i].employerUpdateTime), new Date());
            if (jIList[i].joinFeedback == 1) {
                acceptFeed = true;
            }
            if (acceptFeed) {
                if (jIList[i].acceptFeedback == 0 && dayI2 >= 1) {
                    statusInfo = '<li class="font12 clra6a6a6">接受面试：' +
                            formatDateTime(jIList[i].hunterUpdateTime) +
                            '</li><li><button class="btn btn-primary sizew150" onclick="hunterAccept(this, 1, ' +
                            jIList[i].jobHunterId + ',' + jIList[i].jobId + ',' +
                            jIList[i].jobInteractId +
                            ')">成功入职</button></li><li><button class="btn btn-secondary sizew150" onclick="hunterAccept(this, 2, ' +
                            jIList[i].jobHunterId + ',' + jIList[i].jobId + ',' +
                            jIList[i].jobInteractId +
                            ')">不合适</button></li></li><li><button title="面试通过没来" class="btn btn-secondary sizew150" onclick="hunterAccept(this, 0, ' +
                            jIList[i].jobHunterId + ',' + jIList[i].jobId + ',' +
                            jIList[i].jobInteractId +
                            ')">面试通过没来</button></li>';
                }
                queryHunterInfo(param_data, i, statusInfo);
            }
        }
    }
}

function hunterJoin(btn, status, jobHunterId, jobId, jobInteractId) {
    var param_data = new Object();
    param_data.jobHunterId = jobHunterId;
    param_data.jobId = jobId;
    param_data.jobInteractId = jobInteractId;
    param_data.haveJoin = status;
    param_data.employerId = '${param.employerId}';
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/employer/hunterjoin",
        success: function (data) {
            $.simplyToast("反馈成功", "success");
            $(btn).parent().parent().remove();
        }
    });
}

function hunterAccept(btn, status, jobHunterId, jobId, jobInteractId) {
    var param_data = new Object();
    param_data.jobHunterId = jobHunterId;
    param_data.jobId = jobId;
    param_data.jobInteractId = jobInteractId;
    param_data.haveAccept = status;
    param_data.employerId = '${param.employerId}';
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/employer/hunteraccept",
        success: function (data) {
            $.simplyToast("反馈成功", "success");
            $(btn).parent().parent().remove();
        }
    });
}

function queryJobHunterDelete() {
    $("#jobListGroupId").html("");
    if (jIList == null) {
        return;
    }
    for (var i = 0; i < jIList.length; ++i) {
        param_data = {
            jobHunterId: jIList[i].jobHunterId
        }
        var statusInfo;
        var statusInfo = '';
        if (jIList[i].hunterStatus == 4) {
            statusInfo = '<li>放弃：' +
                    formatDateTime(jIList[i].hunterUpdateTime) +
                    '</li>';
            queryHunterInfo(param_data, i, statusInfo);
        }
    }
}

var questionnairemap = new Object();
var answermap = new Object();
var volatileid = '';
function toggleques(jobHunterId, jobId, i) {
    if (answermap[jobHunterId + jobId] == null) {
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param({jobHunterId: jobHunterId, jobId: jobId}),
            url: "/page/demo/questionnaire/rquesans",
            success: function (data) {
                var ans = data.questionnaire_answer;
                answermap[ans.jobHunterId + ans.jobId] = ans;
                volatileid = ans.jobHunterId + ans.jobId;
                if (questionnairemap[ans.questionnaireId] == null) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        data: $.param({questionnaireId: ans.questionnaireId}),
                        url: "/page/demo/questionnaire/rques",
                        success: function (data) {
                            if (data.questionnaire != null) {
                                questionnairemap[data.questionnaire.questionnaireId] = $.parseJSON(data.questionnaire.content);
                                onPreviewByData(questionnairemap[data.questionnaire.questionnaireId], 'houtai', $("#previewcontent" + i));
                                var answer = $.parseJSON(answermap[volatileid].answer);
                                $(".pre_div_question").each(function (i) {
                                    if ($(this).attr('type') == 'radio' || $(this).attr('type') == 'picradio') {
                                        $(this).find('input:radio[name=optionsRadios' + i + ']')[parseInt(answer[i])].checked = true;
                                    } else if ($(this).attr('type') == 'checkbox' || $(this).attr('type') == 'piccheckbox') {
                                        for (var j = 0; j < answer[i].length; ++j) {
                                            $(this).find('input:checkbox[name=optionsRadios' + i + ']')[parseInt(answer[i].substring(j, j + 1))].checked = true;
                                        }
                                    } else {
                                        $(this).find('textarea').val(answer[i]);
                                    }
                                });
                                $('input:radio').attr("disabled", true);
                                $('input:checkbox').attr("disabled", true);
                                $('textarea').attr("disabled", true);
                            }
                        }
                    });
                }
            }
        });
    } else {
        answermap[ans.jobHunterId + ans.jobId];
    }
}
</script>
<script type="text/javascript" src="/js/worko2o-question.js"></script>
</body>
</html>