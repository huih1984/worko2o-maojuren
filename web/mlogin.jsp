<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="zh-CN">
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
        <div class="text-center" style="margin-top: 20%; margin-bottom: 15%">
            <img style="width:64px; height:64px;" src="img/icon/mjricon2.png">
        </div>
        <div class="row form-horizontal templatemo-login-form-2">
            <div class="templatemo-one-signin col-md-12 margint40">
                <div class="form-group">
                    <div class="col-md-12">
                        <!-- <label for="username" class="control-label">Username</label> -->
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-user"></i>
                            <input type="text" class="form-control" id="username" placeholder="邮箱">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <!-- <label for="password" class="control-label">Password</label> -->
                        <div class="templatemo-input-icon-container">
                            <i class="fa fa-lock"></i>
                            <input type="password" class="form-control" id="password" placeholder="密码">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <input type="submit" value="登录" class="btn btn-primary" onclick="login()">
                    </div>
                    <div class="col-md-12">
                        <div id="inputErrorId" class="clrred loginaccounterr text-center" style="display: none;">
                            登录失败，您输入的账号不存在或密码错误！
                        </div>
                    </div>
                </div>

                <!-- 				        <div class="form-group">
                                              <div class="col-md-12">
                                                <a href="forgot-password.html" class="text-center">Cannot login?</a>
                                                </div>
                                        </div>  btn-lg btn-block-->
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="control-label">还没账号</label>
                    </div>
                    <div class="col-md-12 text-center">
                        <a href="/mregister.jsp">注册</a>
                    </div>
                </div>
            </div>

        </div>
        <!-- </form>		      		       -->
    </div>
</div>

<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.js"></script>
<script src="/js/jquery.md5.js"></script>
<script type="text/javascript">
    function login() {
        var param_data = new Object();
        param_data.jobHunterEmail = $("#username").val();
        param_data.jobHunterPassword = $.md5($("#password").val());
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
                window.location.href = "/jsp/preach/preachlist.jsp";
            }
        });
    }

</script>
</body>
</html>