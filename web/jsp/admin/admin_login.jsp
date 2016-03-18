<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/jsp/comm/header.jsp" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>登录</title>

    <script type="text/javascript">
        function adminlogin() {
            var param_data = {
                username: $("#email").val(),
                password: $("#password").val()
            };
            $.post("/page/demo/admin/get", $.param(param_data), function (json) {
                var data = $.parseJSON(json);
                if (data.admin != null) {
                    window.location.href = "/jsp/admin/approve.jsp";
                }
            });
        }
    </script>

</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container sizew300 margint64 clrba6a6a6">
    <div class="paddingt20">
        <div class="form-group">
            <label for="name" class="control-label">账号:</label>
            <input id="email" type="text" class="form-control" id="name">
        </div>
        <div class="form-group">
            <label for="password" class="control-label">密码:</label>
            <input id="password" class="form-control">
        </div>
        <div class="form-group form-inline">
            <label>验证码:</label>
            <input id="validCodeId" class="sizew64 sizeh32" type=text name=rand maxlength=4 value="">
            <img id="validImgId" class="sizew64 sizeh32" border=0>
        </div>
        <div>
            <a class="pointer" onclick="setImgUrl()">看不清换一张</a>
            <input type="hidden" id="validImgValId">
        </div>
        <label id="errorLblId" style="visibility: hidden; color: red">账号、密码或者验证码错误</label>

        <div class="form-group form-inline">
            <button id="submitId" class="btn btn-block" type="submit"
                    onclick="adminlogin()">登 &nbsp;&nbsp; 录
            </button>
        </div>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        setImgUrl();
    });
</script>
</body>
</html>