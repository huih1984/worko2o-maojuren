<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>我的职位发布</title>
    <%@ include file="/jsp/comm/header.jsp" %>

</head>
<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64 marginb64">
    <div class="col-md-8">
        <h4 class="paddingb10 bottomdashedborder">
            <div>已发布职位</div>
        </h4>
        <ul class="list-group clear-both" id="tableList">
        </ul>
        <ul class="pagination " id="pagination">
        </ul>
    </div>
    <%@ include file="employer_nav.jsp" %>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
var jIList;//全局变量

$(document).ready(function () {
    $("#emjobspage").addClass("active");

    if ('<%=request.getHeader("Referer")%>'.indexOf('job_detail_w.jsp') > 0) {
//        $.simplyToast("您的职位已经发布或者修改成功，系统将在30秒后上线或者完成刷新，请等待，或者您过会再回来查看^_^");
    }
    queryJobHunterStatus();
    queryEmployerJob(1);
    //绑定beforeunload事件
//            $(window).bind('beforeunload',function(){
//                return '您输入的内容尚未保存，确定离开此页面吗？';
//            });
});

function queryJobHunterStatus() {
    var param_data = {
        employerId: escape(${param.employerId})
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,
        data: $.param(param_data),
        url: "/page/demo/job/interact/employer/myinteractjobs",
        success: function (data) {
            jIList = data.job_interact_list;
        }
    });
}

function setNoticeTags() {
    $(".list-group-item").each(function () {
        var allnum = 0;
        var noticenum = 0;
        var jobId = $(this).children("label").text();
        for (var i = 0; i < jIList.length; ++i) {
            if (jIList[i].jobId == jobId) {
                if (jIList[i].employerStatus != 4) {
                    ++allnum;
                    if (jIList[i].hunterStatusChanged != 0) {
                        ++noticenum;
                    }
                }
            }
        }
        if (noticenum != 0) {
            $(this).find(".woo-all-num").html("&nbsp;&nbsp;<span class=\"badge clrred\">" + "new " + noticenum + "</span>");
        }
    });
}

function refresh(jobId) {
    $.simplyToast('该职位已刷新，将在30秒后生效!', 'success');
    var param_data = {
        jobId: jobId
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/page/demo/job/update",
        data: $.param(param_data),
        success: function (msg) {
        }
    });
}
function removeModel(btn, jobId) {
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    $(btn).parents(".list-group-item").remove();
    $.simplyToast('该职位已删除，将在10分钟后生效!', 'success');
    var param_data = {
        jobId: jobId
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/page/demo/job/delete",
        data: $.param(param_data),
        success: function (msg) {
        }
    });
}

function queryEmployerJob(pageIndex) {
    var url = '/page/demo/job/employerjob' + getData(pageIndex) + '&employerId='
            + ${param.employerId};
    $.post(
            url,
            null,
            function (data) {
                var list = data.job_list;
                $('#tableList').html("");
                for (var i = 0; i < list.length; i++) {
                    var tagInfo = '<li><ul class="list-inline">';
                    if (list[i].tag1 != null && list[i].tag1 != "") {
                        tagInfo = tagInfo
                                + "<li>"
                                + "<span class=\"tag2\">"
                                + list[i].tag1 + "</span></li>";
                    }
                    if (list[i].tag2 != null && list[i].tag2 != "") {
                        tagInfo = tagInfo
                                + "<li>"
                                + "<span class=\"tag2\">"
                                + list[i].tag2 + "</span></li>";
                    }
                    if (list[i].tag3 != null && list[i].tag3 != "") {
                        tagInfo = tagInfo
                                + "<li>"
                                + "<span class=\"tag2\">"
                                + list[i].tag3 + "</span></li>";
                    }
                    if (list[i].tag4 != null && list[i].tag4 != "") {
                        tagInfo = tagInfo
                                + "<li>"
                                + "<span class=\"tag2\">"
                                + list[i].tag4 + "</span></li>";
                    }
                    if (list[i].tag5 != null && list[i].tag5 != "") {
                        tagInfo = tagInfo
                                + "<li>"
                                + "<span class=\"tag2\">"
                                + list[i].tag5 + "</span></li>";
                    }
                    tagInfo = tagInfo + '</ul></li>';
                    var updateTime = list[i].updateTime;
                    if (updateTime != null
                            && updateTime.length > 10) {
                        updateTime = updateTime.substr(0, 10);
                    }
                    var workPlace = "";
                    if (list[i].district == undefined
                            || list[i].district === "") {
                        workPlace = list[i].city;
                    } else {
                        workPlace = list[i].city + "-"
                                + list[i].district;
                    }

                    var salary = salaryVal(list[i].salaryBegin, list[i].salaryEnd);
                    var timeIdentify = getTimeIdentity();
                    var liInfo = "<li class=\"list-group-item\">"
                            + "<label style='display: none'>"
                            + list[i].jobId
                            + "</label>"
                            + "<div class=\"woo-job-list-item padding6\">"
                            + "<div class=\"row\">"
                            + "<div class=\"col-md-8\">"
                            + "<ul class=\"list-inline row\">"
                            + "<div class=\"col-md-6\">"
                            + "<li class=\"woo-job-name\">"
                            + "<a href=\"../job/job_detail_r.jsp?jobId=" + list[i].jobId
                            + "\">"
                            + "<h4>"
                            + list[i].jobName
                            + "</h4>"
                            + "</a></li>"
                            + "</div>"
                            + "<div class=\"col-md-6\">"
                            + "<li class=\"woo-salary\">月薪&nbsp;<strong>"
                            + salary
                            + "</strong></li>"
                            + "</div>"
                            + "</ul>"
                            + "<ul class=\"list-inline row\">"
                            + "<div class=\"col-md-6\">"
                            + "<ul class=\"list-inline\">"
                            + "<li class=\"woo-bachelor-degree\">"
                            + showInPage(list[i].eduReq)
                            + "</li>"
                            + "<li class=\"woo-experience-year\">"
                            + "经验要求:" + showInPage(list[i].expReq)
                            + "</li>"
                            + "</ul>"
                            + "</div>"
                            + "<div class=\"col-md-6\">"
                            + "<li class=\"woo-worktype\">"
                            + showInPage(list[i].workPattern)
                            + "</li>"
                            + "</div>"
                            + "</ul>"
                            + "<ul class=\"list-inline woo-job-tags margint20\">"
                            + tagInfo
                            + "</ul>"
                            + "</div>"
                            + "<div class=\"col-md-3\">"
                            + "<ul class=\"list-unstyled\">"
                            + "<li class=\"woo-update-time\">"
                            + updateTime
                            + "</li>"
                            + "<li class=\"woo-work-place\">"
                            + workPlace
                            + "</li>"
                            + "<li class=\"margint10\">"
                            + "<ul class=\"list-inline\">"
                            + "<li class=\"woo-apply-times\" title=\"简历数\">"
                            + "<button class=\"btn btn-primary sizew150\" onclick='window.location.href=\"job_petitioners.jsp?jobId="
                            + list[i].jobId
                            + "&employerId="
                            + list[i].employerId
                            + "&ques="
                            + (list[i].questionnaireId == undefined ? 0 : 1)
                            + "\"'>简历"
                            + "<span class=\"woo-all-num\"></span></button>"
                            + "</li>"
                            + "</ul>"
                            + "</li>"
                            + "</ul>"
                            + "</div>"
                            + "<div class=\"col-md-1\">"
                            + " <ul class=\"list-unstyled\">"
                            + "<li>"
                            + "<button onclick=\"refresh("
                            + list[i].jobId
                            + ");\" style=\"background: transparent;border: transparent;\" title=\"刷新职位\">"
                            + "<span class=\"fa fa-refresh\"></span>"
                            + "</button>"
                            + "</li>"
                            + "<li>"
                            + "<button style=\"background: transparent;border: transparent;\"  title=\"编辑职位\""
                            + "onclick=\"javascript:window.location.href='/jsp/employer/job_detail_w.jsp?employerId="
                            + list[i].employerId + "&employerName=" + list[i].employerName + "&jobId=" + list[i].jobId + "'\">"
                            + "<span class=\"fa fa-edit\"></span>"
                            + "</button>"
                            + "</li>"
                            + "<li>"
                            + "<div class=\"woo-delete-button\" title=\"删除职位\">"
                            + "<button style=\"background: transparent;border: transparent\" data-toggle=\"modal\""
                            + "data-target=\"#deleteModal"
                            + timeIdentify
                            + "\">"
                            + "<span class=\"fa fa-trash-o\"></span></button>"
                            + "<div class=\"modal fade\" id=\"deleteModal"
                            + timeIdentify
                            + "\" tabindex=\"-1\" role=\"dialog\""
                            + "aria-labelledby=\"myModalLabel\""
                            + "aria-hidden=\"true\">"
                            + "<div class=\"modal-dialog\">"
                            + "<div class=\"modal-content\">"
                            + "<div class=\"modal-header\">"
                            + "       <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span"
                            + "               aria-hidden=\"true\">&times;</span><span"
                            + "               class=\"sr-only\">Close</span>"
                            + "       </button>"
                            + "       <h4 class=\"modal-title woo-font-family-em\" id=\"myModalLabel\">删除</h4>"
                            + "   </div>"
                            + "   <div class=\"modal-body woo-font-family-em\">"
                            + "       你确定删除此条已发布职位吗？"
                            + "   </div>"
                            + "   <div class=\"modal-footer\">"
                            + "       <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">取消"
                            + "       </button>"
                            + "       <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\""
                            + "               onclick=\"removeModel(this, "
                            + list[i].jobId
                            + ")\">确定"
                            + "       </button>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</li>"
                            + "</ul>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</li>";

                    $('#tableList').append(liInfo);
                }
                $('#pagination').html(data.paginationInfo);
                setNoticeTags();
            }, "json");
}

//组装查询条件
function getData(pageIndex) {
    var data = '?pageIndex=' + pageIndex;
    //增加查询条件
    return data;
}
function showInPage(value) {
    if (value == undefined) {
        return "";
    }
    return value;
}

</script>
</body>
</html>