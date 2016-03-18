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
    <div id="resumeContentId" class="col-md-8 col-md-offset-2">
        <div class="row clrbedeaf4 paddingt10 paddingb10 margin0">
            <div class="col-md-3">
                <div class="" style="position:relative;">
                    <img id="avatarId" class="circle sizew150 sizeh150" src="/img/avatar_default.jpg">
                </div>
            </div>
            <div id="basicInfoId" class="col-md-6">
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
</div>


<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        queryMyResume('${sessionScope.username}', '${sessionScope.password}');
    });

    function queryMyResume(email, password) {
        $.post("/page/demo/jobHunter/jobhunter", $.param({jobHunterId: escape('${param.jobHunterId}')}), function (json) {
            var data = $.parseJSON(json);
            setResumeValByData(data);
        })
    }

    var myJobs = new Array();
    function queryallMyJobs() {
        var jsonData = new Object();
        jsonData.pageIndex = 1;
        jsonData.pageSize = 20;
        jsonData.employerId = escape('${param.employerId}');


        $.ajax({url: "/page/demo/job/employerjob",
            data: jsonData,
            async: false,
            dataType: "json",
            success: function (data) {
                var list = data.job_list;
                for (var i = 0; i < list.length; ++i) {
                    myJobs += '<option' +
                            ' value="' +
                            list[i].jobId +
                            '">' +
                            list[i].jobName +
                            '</option>';
                }
            }
        }, "json");
    }

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
                    $.simplyToast('您已成功邀请了一个职位，不再邀请请点击取消退出。', 'success');
                }
            }
        }, "json");
    }
</script>
</body>
</html>