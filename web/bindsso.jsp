<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>登录</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body style="background-color: #fff">

<div class="paddingb20 wrapper">
    <div>
        <div class="col-md-6 text-center verticalmiddle hidden-xs">
            <img src="/img/login_top1.png" class="sizew300">
        </div>
        <div class="col-md-5 margint30 text-center verticalmiddle  hidden-xs">
            <div class="sizew400 horizontalmiddle">
                <ul id="user_type" class="nav nav-tabs sizew210 paddingt20">
                    <li id="hunterTabId" value="1" role="presentation" class="active"><a href="#">我是求职者</a></li>
                    <li id="employerTabId" value="2" role="presentation"><a href="#">我是企业</a></li>
                </ul>
                <div class="paddingt10">
                    <div>
                        <input id="account_ipt" type="email"
                               class="woo-check-input form-control floatleft sizew210 marginb10"
                               placeholder="请输入登录邮箱地址" required="" autofocus="">
                        <label class="woo-error marginl4r4 floatleft">请输入邮箱地址</label>

                        <div id="nicknameId" style="display: none;">
                            <label><span></span>&nbsp;欢迎回来！</label>
                        </div>
                    </div>
                    <div>
                        <input id="password_ipt" type="password"
                               class="woo-check-input form-control floatleft sizew210 marginb10"
                               placeholder="请输入密码" required="">

                        <label class="woo-error marginl4r4 floatleft">请输入密码</label>
                    </div>
                    <label id="errorLblId" style="color: red; display: none">该邮箱已注册过，您输入的密码错误</label>
                    <button id="bindBtnId" class="btn btn-primary btn-block sizew210" type="submit"
                            onclick="createAndBind()">
                        创建并绑定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="text-center margint20">
    <img src="/img/login.jpg">
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
    $(function () {
        document.onkeydown = function (e) {
            if (event.keyCode == 13) {
                document.getElementById("bindBtnId").click();
                return false;
            }
        }
    });
    hideErrors();
    $("#user_type > li").on("click", function () {
        var opt = $(this);
        $("#user_type > li").removeClass("active");
        opt.addClass("active");
    });
    if ('${requestScope.userType}' == 'employer') {
        $("#hunterTabId").removeClass("active");
        $('#employerTabId').addClass("active");
    }
    var email = '${requestScope.email}';
    if (email != '') {
        $("#account_ipt").val(email);
    }

    if ('<%=request.getAttribute("nickname")%>' != 'null') {
        $("#nicknameId").css("display", "inherit");
        $("#nicknameId span").text('<%=request.getAttribute("nickname")%>');
    }

});

function createAndBind() {
    if (!formValidCheck())return false;
    var userType = $('#user_type .active').val();
    if (userType == 1) {
        var emailExist = false;
        var password = null;
        url = '/page/demo/jobHunter/valid';
        param_data = {
            jobHunterEmail: $('#account_ipt').val()
        };

        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: url,
            data: $.param(param_data),
            success: function (msg) {
                if (msg.job_hunter_detail != null) {
                    emailExist = true;
                    password = msg.job_hunter_detail.jobHunterPassword;
                }
            }
        });

        var account = $("#account_ipt").val();
        var password = $.md5($("#password_ipt").val());
        var param_data = {
            jobHunterEmail: account,
            jobHunterPassword: password
        };
        $.ajax({
            url: "/page/demo/jobHunter/login",
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            success: function (data) {
                if (data.job_hunter_detail == undefined) {
                    if (emailExist) {
                        $("#errorLblId").show();
                        return;
                    } else {
                        if ('${requestScope.callbackType}' == 'tencent') {
                            param_data = {
                                email: $('#account_ipt').val(),
                                password: $.md5($('#password_ipt').val()),
                                userType: 0,
                                tencentId: '${requestScope.openID}'
                            };
                        } else if ('${requestScope.callbackType}' == 'sina') {
                            param_data = {
                                email: $('#account_ipt').val(),
                                password: $.md5($('#password_ipt').val()),
                                userType: 1,
                                sinaId: '${requestScope.sinaId}'
                            };
                        } else {
                            return;
                        }
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            async: false,
                            url: "/page/demo/valid/user",
                            data: $.param(param_data),
                            success: function (msg) {
                                window.location.href = "http://www.maojuren.com/jsp/reg/hunter_reg_step1.jsp?"
                                        + $.param(param_data);
                            }
                        })
                    }
                } else {
                    var value = data.job_hunter_detail;
                    if ('${requestScope.callbackType}' == 'tencent') {
                        param_data = {
                            jobHunterId: value.jobHunterId,
                            tencentId: '${requestScope.openID}'
                        }
                    } else if ('${requestScope.callbackType}' == 'sina') {
                        param_data = {
                            jobHunterId: value.jobHunterId,
                            sinaId: '${requestScope.sinaId}'
                        }
                    }
                    $.post("/page/demo/hunter/thirdaccount", $.param(param_data));
                    $.cookie('a', value.jobHunterEmail, { expires: 3, path: "/", domain: "maojuren.com" });
                    $.cookie('b', value.jobHunterPassword, { expires: 3, path: "/", domain: "maojuren.com" });
                    $.cookie('c', 'hunter', { expires: 3, path: "/", domain: "maojuren.com" });
                    window.location.href = "/index.jsp";
                }

            }
        });
    } else {
        var emailExist = false;
        var password = null;
        url = '/page/demo/employer/valid';
        param_data = {
            hrEmail: $('#account_ipt').val()
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: url,
            data: $.param(param_data),
            success: function (msg) {
                if (msg.employer_detail != null) {
                    emailExist = true;
                    password = msg.employer_detail.password;
                }
            }
        });

        var account = $("#account_ipt").val();
        var password = $.md5($("#password_ipt").val());
        var param_data = {
            hrEmail: account,
            password: password
        };
        $.ajax({
            url: "/page/demo/employer/login",
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            success: function (data) {
                if (data.employer_detail == undefined) {
                    if (emailExist) {
                        $("#errorLblId").show();
                        return;
                    } else {
                        if ('${requestScope.callbackType}' == 'tencent') {
                            param_data = {
                                email: $('#account_ipt').val(),
                                password: $.md5($('#password_ipt').val()),
                                userType: 2,
                                tencentId: '${requestScope.openID}'
                            };
                        } else if ('${requestScope.callbackType}' == 'sina') {
                            param_data = {
                                email: $('#account_ipt').val(),
                                password: $.md5($('#password_ipt').val()),
                                userType: 2,
                                sinaId: '${requestScope.sinaId}'
                            };
                        } else {
                            return;
                        }
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            async: false,
                            url: "/page/demo/valid/user",
                            data: $.param(param_data),
                            success: function (msg) {
                                window.location.href = "http://www.maojuren.com/jsp/reg/employer_reg_step1.jsp?"
                                        + $.param(param_data);
                            }
                        })
                    }
                } else {
                    var value = data.employer_detail;
                    if ('${requestScope.callbackType}' == 'tencent') {
                        param_data = {
                            employerId: value.employerId,
                            tencentId: '${requestScope.openID}'
                        }
                    } else if ('${requestScope.callbackType}' == 'sina') {
                        param_data = {
                            employerId: value.employerId,
                            sinaId: '${requestScope.sinaId}'
                        }
                    }
                    $.post("/page/demo/employer/thirdaccount", $.param(param_data));
                    $.cookie('d', value.hrEmail, { expires: 3, path: "/", domain: "maojuren.com" });
                    $.cookie('e', value.password, { expires: 3, path: "/", domain: "maojuren.com" });
                    $.cookie('c', 'employer', { expires: 3, path: "/", domain: "maojuren.com" });
                    window.location.href = "/index.jsp";
                }

            }
        });
    }

}
</script>
</body>
</html>