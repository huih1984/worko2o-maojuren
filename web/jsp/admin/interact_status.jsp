<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/jsp/comm/header.jsp" %>
<html lang="zh-CN">
<head>
    <title>投递状态查看</title>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container  margint64">
    <div class="search">
        <div class="input-group">
            <input id="searchName" type="text" class="form-control borderw0" placeholder=""/>
              <span class="input-group-btn">
              <button id="searchconfirm" class="btn btn-primary padding0" type="button"
                      onclick="searchEmployer()">
                  <span class="fa fa-search"></span>
              </button>
              </span>
        </div>
    </div>
</div>
<table id="table-transform" data-toolbar="#transform-buttons" class="table table-hover">
    <thead>
    <tr>
        <th style="">
            <div class="th-inner ">公司ID</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">求职者ID</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">职位ID</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">求职者状态</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">公司状态</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">求职者刷新时间</div>
            <div class="fht-cell"></div>
        </th>
        <th style="">
            <div class="th-inner ">公司刷新时间</div>
            <div class="fht-cell"></div>
        </th>
    </tr>
    </thead>
    <tbody id="tbody">
    </tbody>
</table>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        searchEmployer();
    });

    function searchEmployer() {
        $("#tbody").html('');
        var param_data = null;
        if ($("#searchName").val() != '') {
            param_data = {employerId: $("#searchName").val()};
        } else {
            param_data = {};
        }
        $.ajax({
            url: '/page/demo/job/interact/employer/myinteractjobs',
            dataType: "json",
            data: $.param(param_data),
            success: function (data) {
                if (data.job_interact_list != null) {
                    for (var i = 0; i < data.job_interact_list.length; ++i) {
                        $("#tbody").append("<tr class=\"tr-class-1\" data-index=\"" +
                                data.job_interact_list[i].employerId +
                                "\">" +
                                "<td class=\"td-class-1\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"\"" +
                                "onclick='getEmployerInfo(this, " +
                                data.job_interact_list[i].employerId +
                                ")'>" +
                                data.job_interact_list[i].employerId +
                                "</td>" +
                                "<td class=\"td-class-2\">" +
                                data.job_interact_list[i].jobHunterId +
                                "</td>" +
                                "<td class=\"td-class-3\">" +
                                data.job_interact_list[i].jobId +
                                "</td>" +
                                "<td class=\"td-class-4\">" +
                                data.job_interact_list[i].hunterStatus +
                                "</td>" +
                                "<td class=\"td-class-5\">" +
                                data.job_interact_list[i].employerStatus +
                                "</td>" +
                                "<td class=\"td-class-6\">" +
                                data.job_interact_list[i].hunterUpdateTime +
                                "</td>" +
                                "<td class=\"td-class-7\">" +
                                data.job_interact_list[i].employerUpdateTime +
                                "</td>" +
                                "</tr>")
                    }
                }
            }
        });
    }

    function getEmployerInfo(btn, id) {
        if ($(btn).attr("title") == '') {
            $.ajax({
                url: '/page/demo/employer/employerdetail',
                data: $.param({employerId: id}),
                dataType: "json",
                success: function (data) {

                    $(btn).attr("title", data.employer_detail.employerName + ' ' + data.employer_detail.tel);
                }
            });
        }
    }
</script>
</body>
</html>