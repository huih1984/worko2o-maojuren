<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>简历搜索</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint64">
<div class=" col-md-8">
<div class="form-horizontal paddingt10 clrbedeaf4">
<div class="paddingb20 paddingt20">
<div class="form-group">
    <label class="col-md-2 control-label">学历要求</label>

    <div class="col-md-2">
        <div id="bachelorDegreeList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder">选择</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
    <label class="col-md-2 control-label">专业要求</label>

    <div class="col-md-4">
        <input id="majorId" class="woo-check-input form-control majorname-cls"
               placeholder="请输入专业关键词"
               required="" maxlength="40">
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">工作方式</label>

    <div class="col-md-2">
        <div id="workPatternList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <span class="placeholder">全职</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
    <label class="col-md-2 control-label">性别要求</label>

    <div class="col-md-4">
        <div class="radio">
            <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="sexReqRadio0" value="不限"
                       checked=""> 不限
            </label>
            <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="sexReqRadio1" value="男"> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="sexReqRadio2" value="女"> 女
            </label>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">简历更新</label>

    <div class="col-md-2">
        <div id="resumeUpdateList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">选择</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li role="presentation" value="0">
                    <a role="menuitem" tabindex="0" href="javascript:void(0);">选择</a>
                </li>
                <li role="presentation" value="1">
                    <a role="menuitem" tabindex="1" href="javascript:void(0);">三天以内</a>
                </li>
                <li role="presentation" value="2">
                    <a role="menuitem" tabindex="2" href="javascript:void(0);">一周以内</a>
                </li>
                <li role="presentation" value="3">
                    <a role="menuitem" tabindex="3" href="javascript:void(0);">一个月以内</a>
                </li>
                <li role="presentation" value="4">
                    <a role="menuitem" tabindex="4" href="javascript:void(0);">三个月以内</a>
                </li>
            </ul>
        </div>
    </div>
    <label class="col-md-2 control-label">求职状态</label>

    <div class="col-md-2">
        <div id="currentStatusList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">选择</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">工作年限</label>

    <div class="col-md-2">
        <div id="experienceYearList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder">选择</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
    <label class="col-md-2 control-label">期望薪水</label>

    <div class="col-md-2">
        <div id="salaryList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder">选择</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">当前所在地</label>

    <div class="col-md-10">
        <div id="provinceList" class="btn-group woo-btn-dropdown">
            <button type="button" class=" btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">  选择省份 </span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            </ul>
        </div>
        <div id="cityList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder"> 选择城市</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
        <div id="areaList" class="btn-group woo-btn-dropdown">
            <button type="button" class="btn dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">选择地区</span>

                <div class="padding0 textleft verticalmiddle"><span
                        class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">关键字</label>

    <div class="col-md-10">
        <table>
            <tbody>
            <tr>
                <td>
                    <input id="inputKeyword" class="form-control">
                </td>
                <td class="clra6a6a6">&nbsp;&nbsp;关键字之间用逗号隔开</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="form-group">
    <div class="paddingt20">
        <div class="col-md-offset-2 col-md-4">
            <button class="btn btn-primary btn-block"
                    type="submit" onclick="searchHunter()">搜索
            </button>
        </div>
        <div class="col-md-4">
            <button class="btn btn-secondary btn-block"
                    type="submit" onclick="resetSearch()">重置
            </button>
        </div>
        <div id="spinerId" class="col-md-1" style="visibility: hidden;">
            <i class="fa fa-spinner fa-spin font45 clr8e44ad"></i>
        </div>
    </div>
</div>
</div>
</div>

<div class="margint20">
    <div class="solidborder1">
        <label class="fontem">&nbsp;&nbsp;<i
                class="fa fa-list"></i>&nbsp;&nbsp;简历列表</label>
    </div>
    <ul class="list-group margint10 clear-both" id="tableList">
    </ul>
    <ul class="pagination " id="pagination">
    </ul>
</div>
</div>
<%@ include file="employer_nav.jsp" %>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
    $("#searchpage").addClass("active");
    var param_data = {
        hrEmail: '${sessionScope.username}',
        password: '${sessionScope.password}'
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,
        data: $.param(param_data),
        url: "/page/demo/employer/login",
        success: function (data) {
            if (data.retCode == "fail") {
            } else {
                if (data.employer_detail.censorStatus < 1) {
                    $(".main").html("<div class='margint64 text-center'> <p>请耐心等待审核通过，一个工作日内完成审核，有任何其他问题，请致电客服电话15250986830！</p>");
                }
            }
        }
    });
    queryProvince();
    queryBachelorDegreeList();
    queryExperienceYearList();
    queryCurrentStatusList();
    queryWorkPattern();
    querySalaryList();
    new customDropDown($("#resumeUpdateList"), null);
    queryallMyJobs(escape(${param.employerId}));
    queryInteractList(${param.employerId});
})

function invite(btn, jobHunterId) {
    var selectff = $(btn).parent().parent().find("select").find('option:selected');
    var index = $(selectff).text();
    var jobId = $(selectff).attr("value");
    var param_data = {
        employerStatus: 1,
        employerStatusChanged: 1,
        jobHunterId: jobHunterId,
        employerId: escape('${param.employerId}'),
        jobId: jobId
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/interact/employer/add",
        success: function (data) {
            if (data.retCode == "fail") {
                $.simplyToast('邀请失败，可能您之前已经发送了邀请，请换一个职位发送。', 'danger');
            } else {
                $.simplyToast('您已成功邀请了一个职位。', 'success');
            }
        }
    }, "json");
}

var interactList;
function queryInteractList(employerId) {
    var param_data = new Object();
    param_data.employerId = employerId;

    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/interact/employer/myinteractjobs",
        success: function (data) {
            interactList = data.job_interact_list;
        }
    });
}

function queryResumes(pageIndex) {
    $("#spinerId").css("visibility", "inherit");
    var jsonData = new Object();
    if ($("#bachelorDegreeList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterBachelorDegree = $("#bachelorDegreeList").find(".placeholder").text();
    }
    if ($("#experienceYearList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterStartWorkYear = $("#experienceYearList").find(".placeholder").text();
    }
    if ($("#provinceList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterCurrentProvince = $("#provinceList").find(".placeholder").text();
    }
    if ($("#cityList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterCurrentCity = $("#cityList").find(".placeholder").text();
    }
    if ($("#areaList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterCurrentDistrict = $("#areaList").find(".placeholder").text();
    }
    if ($("#currentStatusList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.jobHunterCurrentStatus = $("#currentStatusList").find(".placeholder").text();
    }
    if ($("#salaryList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.expectSalary = $("#salaryList").find(".placeholder").text();
    }
    jsonData.jobHunterSex = $('input[name="inlineRadioOptions"]:checked ').val();
    if ($("#majorId").val() != '') {
        jsonData.jobHunterMajor = $("#majorId").val();
    }
    if ($("#inputKeyword").val() != '') {
        jsonData.keyword = $("#inputKeyword").val();
    }
    if ($("#resumeUpdateList").find(".placeholder").text().indexOf("选择") < 0) {
        jsonData.updateTime = $("#resumeUpdateList").find(".placeholder").text();
    }
    jsonData.pageIndex = pageIndex;
    $.post(
            '/page/demo/jobHunter/jobhunters',
            jsonData,
            function (json) {
                $('#tableList').html("");
                var data = $.parseJSON(json);
                if (data.retCode == "fail") {
                    return;
                }
                var list = data.job_hunter_list;
                var jobHunterDetail = null;
                if (list == undefined) {
                    return;
                }
                for (var i = 0; i < list.length; ++i) {
                    jobHunterDetail = list[i];
                    var avatarPath = '', yearold = '', hunterSex = '', currentPlace = '', jobHunterName = '', jobHunterMajor = '';
                    if (jobHunterDetail.jobHunterAvatarPath != null && jobHunterDetail.jobHunterAvatarPath != '') {
                        avatarPath = jobHunterDetail.jobHunterAvatarPath;
                    } else {
                        avatarPath = '/img/avatar_default.jpg'
                    }
                    if (jobHunterDetail.jobHunterBirthday != null && jobHunterDetail.jobHunterBirthday != '') {
                        yearold = '<li>' + calcusYear(jobHunterDetail.jobHunterBirthday) + '岁</li>';
                    }
                    if (jobHunterDetail.jobHunterSex != null && jobHunterDetail.jobHunterSex != '') {
                        hunterSex = '<li>' + jobHunterDetail.jobHunterSex + '</li>';
                    }
                    if (regular(jobHunterDetail.jobHunterCurrentCity) != '' || regular(jobHunterDetail.jobHunterCurrentDistrict) != '') {
                        currentPlace = '<li>' + '目前所在地：' + regular(jobHunterDetail.jobHunterCurrentCity) +
                                '-' + regular(jobHunterDetail.jobHunterCurrentDistrict) + '</li>';
                    }
                    if (regular(jobHunterDetail.jobHunterMajor) != '') {
                        jobHunterMajor = ' 专业：' + jobHunterDetail.jobHunterMajor;
                    }
                    var jobstr = '';
                    var got = false;
                    if (myJobs == undefined) {
                        return;
                    }
                    for (var j = 0; j < myJobs.length; ++j) {
                        got = false;
                        if (interactList == undefined) {
                            if (!got) {
                                jobstr += '<option ' +
                                        ' value="' +
                                        myJobs[j].jobId +
                                        '">' +
                                        myJobs[j].jobName +
                                        '</option>';
                            }
                            continue;
                        }
                        for (var k = 0; k < interactList.length; ++k) {
                            if (myJobs[j].jobId == interactList[k].jobId && interactList[k].jobHunterId == jobHunterDetail.jobHunterId) {
                                jobstr += '<option class="clrred" ' +
                                        ' value="' +
                                        myJobs[j].jobId +
                                        '">' +
                                        myJobs[j].jobName +
                                        '</option>';
                                got = true;
                            }
                        }
                    }
                    var tI = getTimeIdentity();
                    $("#tableList").append('<li class="list-group-item">' +
                            '<div class="woolistitem">' +
                            '<div class="row">' +
                            '<div class="col-md-2">' +
                            '<a target="_blank" class="media-left media-middle" href="/jsp/employer/hunter_resume_r.jsp?jobHunterId=' +
                            jobHunterDetail.jobHunterId +
                            '&employerId=' +
                            '${param.employerId}' +
                            '">' +
                            '<img class="size80" src="' +
                            avatarPath +
                            '">' +
                            '</a></div>' +
                            '<div class="col-md-6">' +
                            '<ul class="list-unstyled"><li><a class="fontem font15 clr440062 marginb10" href="/jsp/employer/hunter_resume_r.jsp?jobHunterId=' +
                            jobHunterDetail.jobHunterId +
                            '&employerId=' +
                            '${param.employerId}' +
                            '">' +
                            regular(jobHunterDetail.jobHunterName) +
                            '</a></li></ul>' +
                            '<ul class="list-inline">' +
                            yearold + '/' +
                            hunterSex + '/' +
                            currentPlace +
                            '</ul>' +
                            '<ul class="list-inline">' +
                            '<li>' +
                            getExperienceByStartWorkYear(jobHunterDetail.jobHunterStartWorkYear) +
                            '学历：' +
                            jobHunterDetail.jobHunterBachelorDegree + ' /' +
                            jobHunterMajor +
                            '</li>' +
                            '</ul>' +
                            '</div>' +
                            '<div class="col-md-4">' +
                            '<div class="woo-invite-button">' +
                            '<button class="btn btn-primary floatright" data-toggle="modal" data-target="#inviteModal' +
                            tI +
                            '">邀请投递职位' +
                            '</button>' +
                            '<div class="modal fade" id="inviteModal' +
                            tI +
                            '" tabindex="-1" role="dialog" aria-labelledby="inviteModalLabel"' +
                            'aria-hidden="true">' +
                            '<div class="modal-dialog">' +
                            '<div class="modal-content">' +
                            '<div class="modal-header">' +
                            '<button type="button" class="close" data-dismiss="modal"><span' +
                            'aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
                            '</button>' +
                            '<h4 class="modal-title fontem" id="inviteModalLabel">邀请</h4>' +
                            '</div>' +
                            '<div class="modal-body fontem">' +
                            '<select class="form-control">' +
                            jobstr +
                            '</select>' +
                            '<label class="clrred">*红色为您已经邀请过，请勿重复邀请</label>' +
                            '</div>' +
                            '<div class="modal-footer">' +
                            '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>' +
                            '<button type="button" data-dismiss="modal" class="btn btn-primary"' +
                            'onclick="invite(this,' +
                            jobHunterDetail.jobHunterId +
                            ')">邀请' +
                            '</button>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '<div class="list-bottom-right">' +
                            '<ul class="list-inline">' +
                            '<li style="text-align: right">' +
                            '更新日期：' +
                            '</li>' +
                            '<li>' +
                            jobHunterDetail.updateTime.substr(0, 10) +
                            '</li>' +
                            '</ul>' +
                            '</div>' +
                            '</li>');
                }
                $('#pagination').html(data.paginationInfo);
                $("#spinerId").css("visibility", "hidden");
            })
}

function searchHunter() {
    queryResumes(1);
    $.simplyToast('搜索成功!', 'success');
}

function resetSearch() {
    $("#bachelorDegreeList .placeholder").text("选择");
    $("#workPatternList .placeholder").text("选择");
    $("#resumeUpdateList .placeholder").text("选择");
    $("#currentStatusList .placeholder").text("选择");
    $("#experienceYearList .placeholder").text("选择");
    $("#salaryList .placeholder").text("选择");
    $("#provinceList .placeholder").text("选择");
    $("#cityList .placeholder").text("选择");
    $("#areaList .placeholder").text("选择");
    $("#majorId").val("");
    $("#inputKeyword").val("");
    $('input:radio[name=inlineRadioOptions]')[0].checked = true;
}
</script>
</body>
</html>