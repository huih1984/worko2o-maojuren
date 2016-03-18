<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
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
    <div class="margint20 col-md-offset-1">
        <div>
            <span class="glyphicon glyphicon-ok label-success"></span>
            <label>注册成功!</label>
        </div>
        <div class="verticalmiddle">
            <label>尊敬的求职者用户，感谢您注册猫举人网，请完善您的资料后再投递简历，如果您在智联或者前程无忧注册过简历，那您还可以直接导入简历。
                智联网站请下载htm格式，前程无忧请下载mht格式，登录后在我的简历中通过导入简历一键您的简历。</label>
        </div>
        <div>
            <button class="btn btn-primary sizew210 margint64"
                    onclick="javascript:window.location.href='/jsp/hunter/basic_info_w.jsp'">完善资料
            </button>
        </div>
        <div class="margint20">
            <button class="btn btn-primary sizew210"
                    onclick="javascript:window.location.href='/login.jsp'">马上登录
            </button>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
</body>
</html>