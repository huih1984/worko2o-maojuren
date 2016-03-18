<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<%@ page import="com.delta.worko2o.model.Employer" %>
<html lang="zh-CN">
<head>
    <title>找回密码</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="main margint64">
    <div class="woo-form-reg-step woo-form woo-form-max-width-2">
        <div style="overflow: hidden;">
            <button class="circle woo-big-circle  woo-vertical-middle" style="float: left;">
                <span class="glyphicon glyphicon-envelope"></span>
            </button>
            <div class=" woo-vertical-middle" style="float: left; margin-left: 16px; width: 540px">
                <label style="color: #9B59B6">猫举人网</label>
                <label>邮件已发送至邮箱:<span>${param.email}</span> 。请进入邮箱，点击链接重置密码</label>
            </div>
        </div>
    </div>
</div>
</body>
</html>