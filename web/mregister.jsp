<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN" style="background-color: transparent;">
<head>
    <title>Login</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">
    <link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
    <style>
        .btn-primary {
            color: #fff;
            background-color: #9B59B6;
            border-color: #AB59B6;
        }

        .btn-primary:hover, .btn-primary:focus, .btn-primary.focus, .btn-primary:active, .btn-primary.active, .open>.dropdown-toggle.btn-primary {
            border-color: #942ad0;
            background-color: #8E44AD;
        }
    </style>
</head>
<body class="templatemo-bg-image-1">
<div class="container">
    <div class="col-md-12">
        <div class="row form-horizontal templatemo-login-form-2">
            <div class="templatemo-one-signin col-md-12 margint40">
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-envelope"></i>
                            <input type="text" class="form-control woo-check-input" id="email" placeholder="输入您的邮箱">
                        </div>
                        <div id="checkemail" class="woo-error">请输入邮箱</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-lock"></i>
                            <input type="password" class="form-control woo-check-input" id="password" placeholder="密码">
                        </div>
                        <div id="checkpassword" class="woo-error">请输入密码</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-lock"></i>
                            <input type="password" class="form-control woo-check-input" id="passwords"
                                   placeholder="密码确认">
                        </div>
                        <div class="woo-error">密码确认不正确</div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-phone"></i>
                            <input type="text" class="form-control woo-check-input" id="phone" placeholder="手机号">
                        </div>
                        <div class="woo-error">请输入手机号</div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-user"></i>
                            <input type="text" class="form-control" id="name" placeholder="姓名">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-group"></i>
                            <input type="text" class="form-control" id="profession" placeholder="专业">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-user-md"></i>
                            <select type="text" class="form-control" id="sex" placeholder="性别">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-calendar"></i>
                            <select type="text" class="form-control" id="worklife">
                                <option value="在校生">在校生</option>
                                <option value="应届毕业生">应届毕业生</option>
                                <option value="2002以前">2002以前</option>
                                <option value="2003">2003</option>
                                <option value="2004">2004</option>
                                <option value="2005">2005</option>
                                <option value="2006">2006</option>
                                <option value="2006">2006</option>
                                <option value="2006">2006</option>
                                <option value="2007">2007</option>
                                <option value="2008">2008</option>
                                <option value="2009">2009</option>
                                <option value="2010">2010</option>
                                <option value="2011">2011</option>
                                <option value="2012">2012</option>
                                <option value="2013">2013</option>
                                <option value="2014">2014</option>
                                <option value="2015">2015</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <input type="submit" value="注册" class="btn btn-primary" onclick="regSubmit()">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="control-label">已有账号</label>
                    </div>

                    <div class="col-md-12 text-center">
                        <a href="/mlogin.jsp">登录</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">

    function hideErrors() {
        $(".woo-error").each(function () {
            $(this).css("visibility", "hidden");
            $(this).css("display", "none");
        });
    }

    function showError(element) {
        var ff = element.parent().find(".woo-error");
        element.parent().parent().find(".woo-error").css("visibility", "visible");
        element.parent().parent().find(".woo-error").css("display", "inherit");
    }

    function hideError(element) {
        element.parent().parent().find(".woo-error").css("visibility", "hidden");
        element.parent().parent().find(".woo-error").css("display", "none");
    }

    function formValidCheck(form) {
        var canSubmit = true;
        if (form == undefined) {
            form = $("body");
        }
        form.find(".woo-check-drop-down").each(function () {
            var temp = $(this).find(".placeholder").text();
            if (temp.indexOf("选择") >= 0) {
                showError($(this));
                canSubmit = false;
            } else {
                hideError($(this));
            }
        });
        form.find(".woo-check-input").each(function () {
            var temp = $(this).val();
            if (temp == '') {
                showError($(this));
                canSubmit = false;
            } else {
                hideError($(this));
            }
        });
        return canSubmit;
    }

    function formValidCheckSub(rootNode) {
        var canSubmit = true;
        rootNode.find(".woo-check-drop-down").each(function () {
            var temp = $(this).find(".placeholder").text();
            if (temp.indexOf("选择") >= 0) {
                showError($(this));
                canSubmit = false;
            } else {
                hideError($(this));
            }
        });
        rootNode.find(".woo-check-input").each(function () {
            var temp = $(this).val();
            if (temp == '') {
                showError($(this));
                canSubmit = false;
            } else {
                hideError($(this));
            }
        });
        return canSubmit;
    }


    $(document).ready(function () {
        hideErrors();
    });

    function validUser() {
        var retval = true;
        param_data = {
            jobHunterEmail: $('#email').val()
        };

        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: '/page/demo/jobHunter/valid',
            data: $.param(param_data),
            success: function (msg) {
                if (msg.job_hunter_detail != null) {
                    retval = false;
                }
            }
        });
        return retval;
    }
    function regSubmit() {
        if (!formValidCheck()) return false;
        if (!validUser()) {
            $("#checkemail").text("该邮箱已经注册过了！");
            $("#checkemail").css("display", "inherit");
            $("#checkemail").css("visibility", "visible");
            return false;
        }
        if ($("#password").val().length < 6) {
            $("#checkpassword").text("密码不能少于6位！");
            $("#checkpassword").css("display", "inherit");
            $("#checkpassword").css("visibility", "visible");
            return false;
        }
        if ($("#passwords").val() != $("#password").val()) return false;
        var param_data = new Object();
        param_data.jobHunterEmail = $("#email").val();
        param_data.jobHunterPassword = $.md5($("#password").val());
        param_data.jobHunterName = $("#name").val();
        param_data.jobHunterTel = $("#phone").val();
        param_data.jobHunterMajor = $("#profession").val();
        param_data.jobHunterSex = $("#sex").val();
        param_data.jobHunterStartWorkYear = $("#worklife").val();

        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/jobHunter/register",
            data: $.param(param_data),
            success: function (data) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: $.param(param_data),
                    url: "/page/demo/jobHunter/login",
                    success: function (data) {
                        if (data.retCode == "fail") {
                            $(".loginaccounterr").css("display", "inherit");
                            return false;
                        }
                        window.location.href = "/preach/preachlist.jsp";
                    }
                });
            }
        });
    }

</script>
</body>
</html>