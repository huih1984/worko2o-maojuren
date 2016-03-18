<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<%@ page import="com.delta.worko2o.model.JobHunter" %>
<html lang="zh-CN">
<head>
    <title>注册</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="horizontalmiddle">
        <div class="margint64">
            <table class="horizontalmiddle">
                <tbody class="banner">
                <tr>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">1</button>
                    </td>
                    <td>
                        <div class="line1 clrb8e44ad"></div>
                    </td>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">2</button>
                    </td>
                    <td>
                        <div class="line1 clrb8e44ad"></div>
                    </td>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">3</button>
                    </td>
                    <td>
                        <div class="line1 clrba6a6a6"></div>
                    </td>
                    <td>
                        <button class="clrba6a6a6 btn circle size34 floatright" style="cursor:default">4</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="margint20">
            <div class="row horizontalmiddle">
                <div class="col-md-3 ">
                    <h5 class="clra6a6a6 text-center">邮箱激活</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">求职者联系方式</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">基本信息</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">注册成功</h5>
                </div>
            </div>
        </div>
    </div>
    <%
        String jobHunterName = (String) request
                .getParameter("jobHunterName");
        String tel = (String) request.getParameter("jobHunterTel");
        String sex = (String) request.getParameter("jobHunterSex");
        JobHunter jobHunter = (JobHunter) session.getAttribute("jobHunter");
        if (jobHunter != null) {
            jobHunter.setJobHunterName(jobHunterName);
            jobHunter.setJobHunterTel(tel);
            jobHunter.setJobHunterSex(sex);
        }
    %>

    <div class="margint20 col-md-offset-1">
        <div>
            <div class="form-group">
                <div class="row">
                    <label class="col-sm-2 control-label"><em
                            class="clrred">*</em> 开始工作年份</label>

                    <div class="col-sm-8">
                        <div id="startWorkYearList" class="btn-group woo-btn-dropdown">
                            <button id="jobHunterStartWorkYear" type="button"
                                    class="woo-check-drop-down btn dropdown-toggle"
                                    data-toggle="dropdown"
                                    aria-expanded="false"><span class="placeholder">选择开始工作年份</span>
                                <span class="imageback downarrow size13"></span>
                            </button>
                            <ul class="dropdown-menu scroll sizemh260" role="menu">
                            </ul>
                        </div>
                        <div class="woo-error">请选择开始工作年份</div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <label class="col-sm-2 control-label"><em
                            class="clrred">*</em> 最高学历</label>

                    <div class="col-sm-8">
                        <div id="bachelorDegreeList" class="btn-group woo-btn-dropdown">
                            <button id="jobHunterBachelorDegree" type="button"
                                    class="woo-check-drop-down btn dropdown-toggle"
                                    data-toggle="dropdown"
                                    aria-expanded="false"><span class="placeholder">选择最高学历</span>
                                <span class="imageback downarrow size13"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                            </ul>
                        </div>
                        <div class="woo-error">请选择最高学历</div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-2 control-label"><em
                            class="clrred" style="visibility: hidden;">*</em> <label>当前状态</label>
                    </div>
                    <div class="col-md-8">
                        <div id="currentStatusList" class="btn-group woo-btn-dropdown">
                            <button id="jobHunterCurrentStatus" type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown"
                                    aria-expanded="false">
                                <span class="placeholder">选择当前状态</span>
                                <span class="imageback downarrow size13"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary sizew210 margint20" onclick="regSubmit()">下一步
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        queryStartWorkYearList();
        queryBachelorDegreeList();
        queryCurrentStatusList();
        hideErrors();
    });


</script>
<script type="text/javascript">
    function regSubmit() {
        if (!formValidCheck()) return false;
        var currentStatus = $('#jobHunterCurrentStatus').find(".placeholder").text();
        currentStatus = setNotSelectedValueToNull(currentStatus);
        var param_data = {
            jobHunterStartWorkYear: $('#jobHunterStartWorkYear').find(".placeholder").text(),
            jobHunterBachelorDegree: $('#jobHunterBachelorDegree').find(".placeholder").text(),
            jobHunterCurrentStatus: currentStatus
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/jobHunter/register",
            data: $.param(param_data),
            success: function (data) {
//						if (msg.retCode == 'fail') {
//							var alertMessage = msg.retMsg;
//							alert(alertMessage);
//						}

                document.location.href = "/jsp/reg/hunter_reg_step4.jsp?jobHunterId=" + data.job_hunter_id;
            }
        });
    }
</script>
</body>
</html>