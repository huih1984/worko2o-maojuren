<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>重置密码</title>
    <%@ include file="/jsp/comm/header.jsp" %>
    <script type="text/javascript">
        function sendForget() {
            var email = $('#emailId').val();
            if (!emailValid(email)) {
                $("#errorId").show();
                return;
            } else {
                $("#errorId").hide();
            }
            var userType = $('input[name="userType"]:checked').val();
            if (userType == 0) {
                $.post('/page/demo/jobHunter/valid?jobHunterEmail=' + email, null, function (json) {
                    var data = $.parseJSON(json);
                    if (data.job_hunter_detail != null) {
                        var param_data = {
                            userType: userType,
                            email: email
                        }
                        $.post('/page/demo/valid/forget', param_data, function (data) {
                            window.location.href = "/jsp/password/email_forget_send.jsp?userType=" + userType + "&email=" + email;
                        });
                    } else {
                        $("#errorId").show();
                    }
                })
            } else {
                $.post('/page/demo/employer/valid?hrEmail=' + email, null, function (json) {
                    var data = $.parseJSON(json);
                    if (data.employer_detail != null) {
                        var param_data = {
                            userType: userType,
                            email: email
                        }
                        $.post('/page/demo/valid/forget', param_data, function (data) {
                            window.location.href = "/jsp/password/email_forget_send.jsp?userType=" + userType + "&email=" + email;
                        });
                    } else {
                        $("#errorId").show();
                    }
                })
            }


        }
    </script>
</head>

<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint64">
    <div class="form-horizontal sizew400 horizontalmiddle">
        <div class="form-group">
            <div class="col-md-offset-2">
                <label class="woo-bigger-margin-bottom">找回密码</label>
            </div>
        </div>
        <div class="form-group">
            <div class="radio col-md-offset-2 col-md-10" id="user_type">
                <label class="radio-inline">
                    <input type="radio" name="userType" id="inlineRadio1" value="0" checked> 我是求职者
                </label>
                <label class="radio-inline">
                    <input type="radio" name="userType" id="inlineRadio2" value="1"> 我是企业
                </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">邮箱地址：</label>

            <div class="col-md-6">
                <input class="form-control" id="emailId" type="email">

                <div id="errorId" class="woo-error" style="display: none">请输入合法的邮箱地址</div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6 col-md-offset-4">
                <button class="margint30 btn btn-primary btn-block" type="submit" onclick="sendForget()">发送
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>