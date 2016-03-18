<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title> 猫举人网-电子商务招聘平台</title>
    <%@ include file="/jsp/comm/header.jsp" %>
    <%--<link href="/css/html5tricks-login.css" rel="stylesheet">--%>
    <meta property="qc:admins" content="26516356646517252566375"/>
    <meta property="wb:webmaster" content="b877b4de0fa1651e"/>
    <meta name="baidu-site-verification" content="sKheRIWBOx"/>
    <style type="text/css">

        .templatemo-login-form-2 {
            background-color: rgba(13, 13, 13, 0.25);
            border-radius: 8px;
            color: #fff;
            font-weight: 300;
            max-width: 650px;
            padding: 0 30px 30px 30px;
        }

        @media ( max-width :1200px) {
            .templatemo-login-form-2 {
                background-color: transparent;
            }
        }
        .nav>li>a {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .nav-tabs {
            border: 0px;
        }

        .wrapper {
            height: 100%;
        }
    </style>
</head>

<body>
<div class="main">
    <div>
        <div class="htmleaf-container">
            <div class="wrapper">
                <div class="container paddingt20">
                    <div class="col-md-6 hidden-xs">
                        <img src="/images/lz_skill.png" class="margint64 paddingt10" style="width:400px;">
                    </div>
                    <div class="col-md-4 col-md-offset-1 templatemo-login-form-2 margint64">
                        <div class="text-center paddingt20 paddingb20 marginb10"
                             style="border-bottom:1px solid rgba(255, 255, 255, 0.4);">
                            <img src="/img/icon/mjricon3.png" class="sizew64">
                        </div>

                        <form class="form" onSubmit="return login('<%out.print(request.getHeader("Referer"));%>');"
                              target="hideIframe">
                            <%--<nav role="navigation" class="hidden-xs main-navigation paddingl110">--%>
                            <%--<ul>--%>
                            <%--<li class="tab-1 active" data-bg-color="hsl(190, 100%, 35%)" value="1">--%>
                            <%--<a href="#home">--%>
                            <%--<span>我是求职者</span>--%>
                            <%--<svg class="tab-left">--%>
                            <%--<use xlink:href="#tab-shape-left"></use>--%>
                            <%--</svg>--%>
                            <%--<svg class="tab-right">--%>
                            <%--<use xlink:href="#tab-shape-right"></use>--%>
                            <%--</svg>--%>
                            <%--</a>--%>
                            <%--</li>--%>
                            <%--<li class="tab-2" data-bg-color="hsl(205, 80%, 45%)" value="2">--%>
                            <%--<a href="#about">--%>
                            <%--<span>我是企业</span>--%>
                            <%--<svg class="tab-left">--%>
                            <%--<use xlink:href="#tab-shape-left"></use>--%>
                            <%--</svg>--%>
                            <%--<svg class="tab-right">--%>
                            <%--<use xlink:href="#tab-shape-right"></use>--%>
                            <%--</svg>--%>
                            <%--</a>--%>
                            <%--</li>--%>
                            <%--</ul>--%>
                            <%--</nav>--%>
                            <ul id="myTab" class="nav nav-tabs marginb10 main-navigation">
                                <li id="hunter" class="active" value="1">
                                    <a href="#" data-toggle="tab">
                                        我是求职者
                                    </a>
                                </li>
                                <li id="employer" value="2">
                                    <a href="#" data-toggle="tab">我是企业</a>
                                </li>

                            </ul>
                            <div class="form-group">
                                <input id="account_ipt" class="paddingt10 form-control" type="text" placeholder="邮箱">
                            </div>
                            <div class="form-group">
                                <input id="password_ipt" class="form-control" type="password" placeholder="密码">
                            </div>
                            <div class="form-group">
                                <button id="loginbtn" type="submit" class="btn btn-primary form-control" value="登录">登录
                                </button>
                                <div id="inputErrorId " class="clrred loginaccounterr" style="display: none;">
                                    登录失败，您输入的账号不存在或密码错误！
                                </div>
                            </div>
                            <div class="clredeaf4 text-center">
                                <%--<span class="">其它登录方式:</span>--%>
                            <span class="marginlp7">
                            <%--<a class="iconqq inline marginr10" href="#" onclick="goSingleLoginUrl(0)"></a>--%>
                            <%--<a class="iconsina inline" href="#" onclick="goSingleLoginUrl(1)"></a>--%>
                            </span>

                                <div class="text-center margint20">
                                    <a href="/index.jsp" class="clrfff">继续浏览 <span
                                            class="fa fa-arrow-circle-right marginlp5"></span></a>
                                    <a href="/jsp/reg/reg.jsp" class="hidden-xs clrfff marginl10"> 注册
                                    </a>
                                </div>
                            </div>

                        </form>
                        <iframe id="hideIframe" name="hideIframe" style="display:none;"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script src="/js/prefixfree.min.js"></script>
<%--<script type="text/javascript">--%>
<%--var menuItems = $('.main-navigation li');--%>

<%--menuItems.on("click", function (event) {--%>

<%--menuItems.removeClass("active");--%>

<%--$(this).addClass("active");--%>

<%--$(".main-content").css({--%>
<%--"background": $(this).data("bg-color")--%>
<%--});--%>

<%--event.preventDefault();--%>
<%--});--%>
<%--</script>--%>
<script type="text/javascript">
    $(function () {
        document.onkeydown = function (e) {
            if (event.keyCode == 13) {
                document.getElementById("loginbtn").click();
                return false;
            }
        }
    });
    /*回调中调用函数*/
    $(document).ready(function () {
//        hideErrors();

        if ('${param.userType}' == 2) {
            $("#employer").click();
        }

        var callback = '${requestScope.callback}';
        if (callback == 'yes') {
            if ('${requestScope.userType}' == 'hunter') {
                var param_data = new Object();
                if ('${requestScope.callbacktype}' == 'tencent') {
                    param_data.tencentId = '${requestScope.tencentId}';
                } else if ('${requestScope.callbacktype}' == 'sina') {
                    param_data.sinaId = '${requestScope.sinaId}';
                }
                login("index", param_data);
            }
        }
    })
</script>

</body>
</html>