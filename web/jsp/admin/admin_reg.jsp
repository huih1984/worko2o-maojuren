<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/jsp/comm/header.jsp" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>注册</title>
    <script type="text/javascript">
        function regSubmit() {
            var param_data = {
                username: $("#email").val(),
                password: $("#password").val()
            };
            $.post("/page/demo/admin/add", $.param(param_data));
        }
    </script>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container sizew300 margint64 clrba6a6a6">
    <div class="woo-form paddingt20" role="form" id="regForm" method="post">
        <label class="fontem">管理员注册</label>

        <div class="form-group">
            <input type="email" class="form-control" placeholder="请输入账号"
                   required="" autofocus="" id="email" name="email">
        </div>
        <div class="form-group">
            <input type="password"
                   class="form-control" placeholder="请输入密码" required="" id="password"
                   name="password">
        </div>
        <button class="btn btn-primary btn-block margint20 marginb20" type="submit"
                onclick="regSubmit()">注 &nbsp;&nbsp; 册
        </button>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
</body>
</html>