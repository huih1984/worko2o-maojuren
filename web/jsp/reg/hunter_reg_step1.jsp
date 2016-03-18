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
                        <div class="line1 clrba6a6a6"></div>
                    </td>
                    <td>
                        <button class="clrba6a6a6 btn circle size34 floatright" style="cursor:default">2</button>
                    </td>
                    <td>
                        <div class="line1 clrba6a6a6"></div>
                    </td>
                    <td>
                        <button class="clrba6a6a6 btn circle size34 floatright" style="cursor:default">3</button>
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
    <div class="margint20 col-md-offset-1">
        <div>
            <div style="overflow: hidden;">
                <div class="sizew720 marginl4r4 floatleft">
                    <label class="clr9b59b6">猫举人网</label> <label>感谢注册！确认邮件已发送至你的注册邮箱
                    :<span>${param.email}</span> 。请进入邮箱查看邮件，点击链接激活猫举人网账号
                </label>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <button class="btn btn-primary margint20"
                                onclick="emailUrl('${param.email}')">登录邮箱
                        </button>
                    </div>
                </div>
            </div>
            <dl class="margint64">
                <dt>没有收到邮件？</dt>
                <dd>
                    1、请检查邮箱地址是否正确，你可以返回<a id="js_returnEmail" href="reg.jsp">重新填写</a>。
                </dd>
                <dd>2、检查你的邮件垃圾箱</dd>
                <dd>
                    3、若仍未收到确认，请尝试<a id="js_reSendEmail" href="javascrpit:;">重新发送</a>
                </dd>
            </dl>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    function reSend() {
        var param_data = {
            email: '<%=request.getParameter("email")%>',
            password: '<%=request.getParameter("password")%>',
            userType: '0'
        };
        if (escape('${param.tencentId}') != '') {
            $.append(param_data, {tencentId: escape('${param.tencentId}')});
        } else if (escape('${param.sinaId}') != '') {
            $.append(param_data, {sinaId: escape('${param.sinaId}')});
        } else if (escape('${param.weixinId}') != '') {
            $.append(param_data, {weixinId: escape('${param.weixinId}')});
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/valid/user",
            data: $.param(param_data)
        });
    }

    $(document).ready(function () {
        $('#js_reSendEmail').click(function () {
            reSend();
        });
    });
</script>
</body>
</html>