<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <%@ include file="/jsp/comm/header.jsp" %>
    <title>地图组件</title>
    <script type="text/javascript" src="/js/jquery.min.js"/>
    <style>
        body {
            background: black;
        }

        #myDiv {
            background: #fff;
            width: 250px;
            height: 250px;
            display: none;
        }
    </style>
</head>
<body>
<div id="myDiv">
    This is a div;
</div>
<input id="btn" type="button" value="显示DIV"/>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    var myDiv = $("#myDiv");
    $(function () {
        $("#btn").click(function (event) {
            showDiv();//调用显示DIV方法
            $(document).one("click", function () {//对document绑定一个影藏Div方法
                $(myDiv).hide();
            });
            event.stopPropagation();//阻止事件向上冒泡
        });
        $(myDiv).click(function (event) {
            event.stopPropagation();//阻止事件向上冒泡
        });
    });
    function showDiv() {
        $(myDiv).fadeIn();
    }
</script>
</body>
</html>