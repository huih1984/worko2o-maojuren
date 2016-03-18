<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>注册</title>
    <%@ include file="/jsp/comm/header.jsp" %>
    <%--<link href="/css/html5tricks-login.css" rel="stylesheet">--%>
</head>
<body style="background-color: #fff">
<div class="paddingb20 wrapper">
    <div>
        <div class="col-md-6 text-center verticalmiddle  hidden-xs">
            <img src="/img/login_top1.png" class="sizew300">
            <br>
            <label class="font24">专注电商、新媒体职业机遇</label>
        </div>
        <div class="col-md-5 margint30 text-center verticalmiddle">
            <form class="sizew400 horizontalmiddle" id="regForm" onsubmit="return regSubmit()" name="regForm"
                  method="post">
                <div class="text-left sizew210"><span class="">注册账号</span>
                    <a href="/login.jsp" class="floatright clrfff"> 登录 <span
                            class="fa fa-arrow-circle-right"></span></a></div>
                <ul id="user_type" class="nav nav-tabs sizew210 paddingt10 marginb10">
                    <li value="1" role="presentation" class="active"><a href="#">我是求职者</a></li>
                    <li value="2" role="presentation"><a href="#">我是企业</a></li>
                </ul>
                <div>
                    <div>
                        <input type="email" class="form-control sizew210 floatleft marginb10" placeholder="请输入登录邮箱地址"
                               required=""
                               autofocus="" id="email"
                               name="email" onBlur="validEmail()">

                        <label id="reg_email_error" class="clrred marginl4r4 floatleft" style="display: none">
                            帐号已存在，请直接登录或更换邮箱注册
                        </label>
                    </div>
                    <div>
                        <input id="pwdId" type="password" class="form-control sizew210 floatleft marginb10"
                               placeholder="请输入密码"
                               required="" id="password" name="password" onBlur="validPassword()">
                        <label id="reg_password_error" class="clrred marginl4r4 floatleft" style="display: none">
                            密码至少为6位，区分大小写
                        </label>
                    </div>
                    <div class="sizew210">
                        <input id="validCodeId" class="form-control" type=text name=rand maxlength=4 value=""
                               placeholder="验证码">
                    </div>
                    <div class="form-inline paddingt10 sizew210 text-left">
                        <img id="validImgId" border=0>
                        <a style="cursor:hand;" onclick="setImgUrl()">看不清换一张</a>
                        <input type="hidden" id="validImgValId">
                    </div>
                    <label id="errorLblId" class="woo-error" style="display: none">验证码错误</label>

                    <div class="checkbox sizew210">
                        <label> <input type="checkbox" id="agreementId" value="read_protocol" checked> 我已阅读并同意
                            <a href="/jsp/reg/agreement.jsp" class="clrfff">猫举人网用户协议 </a>
                        </label>
                    </div>
                    <div class="">
                        <button id="submitId" class="btn btn-block btn-primary sizew210" type="submit">立即注册
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="text-center margint20  hidden-xs">
    <img src="/img/login.jpg">
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    function validEmail() {
        var url;
        var param_data;
        var userType = $('#user_type .active').val();
        if (userType == 1) {
            url = '/page/demo/jobHunter/valid';
            param_data = {
                jobHunterEmail: $('#email').val()
            };

            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                url: url,
                data: $.param(param_data),
                success: function (msg) {
                    if (msg.job_hunter_detail != null) {
                        $('#reg_email_error').show();
                        $('#submitId').attr("disabled", true);
                    } else {
                        $('#reg_email_error').hide();
                        $('#submitId').attr("disabled", false);
                    }
                }
            });
        } else {
            url = '/page/demo/employer/valid';
            param_data = {
                hrEmail: $('#email').val()
            };

            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                url: url,
                data: $.param(param_data),
                success: function (msg) {
                    if (msg.employer_detail != null) {
                        $('#reg_email_error').show();
                        $('#submitId').attr("disabled", true);
                    } else {
                        $('#reg_email_error').hide();
                        $('#submitId').attr("disabled", false);
                    }
                }
            });
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


    function regSubmit() {
        if ($("#agreementId").prop("checked") == true) {
            if (!checkValidCode($("#validCodeId").val())) {
                $("#errorLblId").css("visibility", "inherit");
                return false;
            } else {
                $("#errorLblId").css("visibility", "hidden");
            }
            var param_data = {
                email: $('#email').val(),
                password: $.md5($('#pwdId').val()),
                userType: $('#user_type .active').val()
            };
            var userType = $('#user_type .active').val();
            var url = '/page/demo/valid/user';
            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                url: "/page/demo/valid/user",
                data: $.param(param_data),
                success: function (msg) {
                    if (userType == 1) {
                        document.forms[0].action = "hunter_reg_step1.jsp?"
                                + $.param(param_data);
                        document.forms[0].submit();
                    } else {
                        document.forms[0].action = "employer_reg_step1.jsp?"
                                + $.param(param_data);
                        document.forms[0].submit();
                    }
                    return false;
//                    }
                }
            });
            return true;
        } else {
            alert("请在用户协议上打勾再点注册按钮。");
            return false;
        }
    }

    $(document).ready(function () {

        var userType = $.cookie('c');
        if (userType == 'hunter') {
            var param_data = {
                userType: $.cookie('c'),
                jobHunterEmail: $.cookie('a'),
                jobHunterPassword: $.cookie('b')
            }
            login("null", param_data);
        } else if (userType == 'employer') {
            var param_data = {
                userType: $.cookie('c'),
                hrEmail: $.cookie('d'),
                password: $.cookie('e')
            }
            login("null", param_data);
        }

        setImgUrl();

        $("#user_type > li").on("click", function () {
            var opt = $(this);
            $("#user_type > li").removeClass("active");
            opt.addClass("active");
        });
    })
</script>
</body>
</html>