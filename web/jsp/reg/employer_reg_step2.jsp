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

    <%
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        Employer employer = new Employer();
        employer.setHrEmail(email);
        employer.setPassword(password);
        employer.setTencentId(request.getParameter("tencentId"));
        employer.setSinaId(request.getParameter("sinaId"));
        employer.setWeixinId(request.getParameter("weixinId"));
        session.setAttribute("employer", employer);
    %>
    <form class="margint20 col-md-offset-1" action="employer_reg_step3.jsp" method="get" role="form">
        <div class="form-group">
            <em class="clrred">*</em> <label style="">公司名称：</label>
            <label><span>请输入与公司营业执照一致的公司全称，审核通过后不可更改</span></label>
            <input id="employerName" name="employerName"
                   class="form-control sizew210 clear-both"
                   placeholder="请输入公司名称" required="">
        </div>
        <div class="form-group">
            <em class="clrred">*</em> <label>联系电话：</label> <label><span
                >请填写真实有效的电话号码</span></label>
            <input id="tel" name="tel" class="form-control sizew210 clear-both"
                   placeholder="请输入联系电话" required="" pattern="[0-9]{7,13}"
                   title="请输入正确的手机或者座机号码，座机号码带区号请去掉中间的分割'-'">
        </div>
        <div class="form-group">
            <button class="btn btn-primary sizew210 margint20" type="submit">下一步
            </button>
        </div>
    </form>
</div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var param_data = {
            hrEmail: '${param.email}'
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            data: $.param(param_data),
            url: "/page/demo/employer/valid",
            success: function (data) {
                if (data.retMsg == "success") {
                    $(".woo-main-body").html("<div class='woo-bigger-margin-top' style='text-align: center'> <p>该账号已经注册过，请直接登录！</p>")
                }
            }
        });
    })
</script>
</body>
</html>