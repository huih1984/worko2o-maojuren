<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<%@ page import="com.delta.worko2o.model.Employer" %>
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
                        <div class="line1 clrb8e44ad"></div>
                    </td>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">4</button>
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
                    <h5 class="clra6a6a6 text-center">企业名称及电话</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">身份证明资料</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">注册成功</h5>
                </div>
            </div>
        </div>
    </div>
    <div class="margint20 col-md-offset-1">
        <div>
            <span class="glyphicon glyphicon-ok label-success"></span>
            <label>注册成功!</label>
        </div>
        <div class="verticalmiddle">
            <label>尊敬的企业用户，感谢您注册猫举人网，您的信息将进行审核，三个工作日内反馈您审核结果，审核通过需完善公司资料即可发布职位。</label>
        </div>
        <button class="btn btn-primary sizew210 margint64"
                onclick="javascript:window.location.href='/login.jsp?userType=2'">马上登录
        </button>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
</body>
</html>
</html>