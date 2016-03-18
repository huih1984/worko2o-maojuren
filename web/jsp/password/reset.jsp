<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>重置密码</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint64">
    <div class="woo-form sizew300 horizontalmiddle">
        <label class="fontem">重置密码</label>

        <div role="form">
            <label>输入新密码</label>

            <input id="pwdId" type="password" class="form-control" placeholder="请输入密码"
                   required="" id="password" name="password" onBlur="validPassword()">

            <div id="reg_password_error"
                 class="woo-normal-margin-left woo-small-font-size woo-big-margin-bottom"
                 style="color: red; display: none">
                <b id="regPassword_error_span_i">密码至少为6位，区分大小写</b>
            </div>
            <label>再次输入密码</label>

            <div>
                <input id="spwdId" type="password" class="form-control" placeholder="确认密码"
                       required=""
                       autofocus="">

                <div id="errorId1" class="woo-error">请保证确认密码和输入密码一致</div>
            </div>
            <div class="margint20 form-inline">
                <label>验证码:</label>
                <input id="validCodeId" class="sizew80" type=text name=rand maxlength=4 value="">
                <img id="validImgId" class="floatright" border=0 src="#">
            </div>
            <div class="margint20 clearboth">
                <a class="pointer floatright" onclick="setImgUrl()">看不清换一张</a>
                <input type="hidden" id="validImgValId">
            </div>
            <label id="errorLblId" class="clearboth woo-error">验证码错误</label>

            <button id="submitId" class="clearboth btn btn-primary margint30" type="submit"
                    onclick="submit()">
                设置
            </button>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        setImgUrl();
        validResetUrl();
        hideErrors();
    });

    var email = escape('${param.email}'), token = escape('${param.token}'), userType = escape('${param.userType}');
    var canDo = false;
    function validResetUrl() {
        var param_data = {
            email: escape('${param.email}'),
            token: escape('${param.token}'),
            userType: escape('${param.userType}')
        }
        $.post('/page/demo/valid/user/get', $.param(param_data), function (json) {
            var data = $.parseJSON(json);
            if (regular(data.user) == 1) {
                canDo = true;
            } else {
                window.location.href = "/";
            }
        });
    }

    function submit() {
        if (checkValidCode($("#validCodeId").val()) && $("#pwdId").val() == $("#spwdId").val()) {
            var password = $("#pwdId").val();
            var md5password = $.md5(password);
            if (userType == 0) {
                var param_data = {
                    jobHunterEmail: email,
                    jobHunterPassword: md5password
                }
                $.post('/page/demo/jobHunter/resetpassword', $.param(param_data));
            } else {
                var param_data = {
                    hrEmail: email,
                    password: md5password
                }
                $.post('/page/demo/employer/resetpassword', $.param(param_data), function (data) {
                    if ($.parseJSON(data).retCode == 'fail') {
                    } else {
                        $.simplyToast("保存成功！", "success");
                    }
                });
            }
            window.location.href = "/";
        } else {
            if ($("#pwdId").val() != $("#spwdId").val()) {
                $("#errorId1").show();
            } else {
                $("#errorLblId").show();
            }
        }
    }

    function validPassword() {
        if ($("#pwdId").val().length < 6) {
            $('#reg_password_error').show();
            $('#submitId').attr("disabled", true);
        } else {
            $('#reg_password_error').hide();
            $('#submitId').attr("disabled", false);
        }
    }
</script>
</body>
</html>