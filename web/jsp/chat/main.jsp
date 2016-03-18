<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>聊天室</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/worko2o.css" rel="stylesheet">
    <script language="javascript" src="/js/jquery.min.js"></script>
    <script language="javascript">
        function showContent() {
            $.ajax({
                url: "/Messages?action=getMessages&nocache=" + new Date().getTime(),
                type: "GET",
                timeout: 1000,
                success: function (msg) {
                    if (msg) {
                        $("#content").html(msg);
                    }
                }
            });
            checkScrollScreen();
        }

        function send() {
            var _val = $("#content1").val();
            $("#content1").val("");
            if (_val) {
                $.ajax({
                    url: "/Messages?action=sendMessage",
                    type: "POST",
                    timeout: 1000,
                    data: {
                        employerId: '${sessionScope.employerId}',
                        jobHunterId: '${sessionScope.jobHunterId}',
                        unread: '${sessionScope.online == 0}' ? 0 : 1,
                        author: ("hunter" == '${sessionScope.userType}') ? 0 : 1,
                        content: _val
                    },
                    success: function (msg) {
                        if (console) {
                            console.info("send success. msg " + _val);
                        }
                    },
                    error: function () {
                        if (console) {
                            console.info("send failed. msg " + _val);
                        }
                    }
                });
            } else {
                alert("发送信息不可以为空！");
                $("#content1").focus();
            }
            return false;
        }

        function checkScrollScreen() {
            if ($("#form1").scrollScreen.checked) {
                $("#content").css({overflow: "scroll"});
            } else {
                $("#content").css({overflow: "hidden"});
                $("#content").scrollTop = $("#content").scrollHeight * 2
            }
        }

        $(document).ready(function () {
            setInterval("showContent();", 1000);
        });
    </script>
</head>
<body>
<div class="solidborder1 horizontalmiddle" style="height:50%; width: 80%; overflow:hidden" id="content">聊天内容</div>
<form id="form1" action="" name="form1" method="post" class="margint20">
    <div class="horizontalmiddle" style="width: 80%;">
        <div style="padding-left: 0px;">
            <textarea id="content1" name="content1" placeholder="请输入。。。"
                      onKeyDown="if(event.keyCode==13 && event.ctrlKey){send(); return false;}"
                      style="height: 20%;width: 100%;"></textarea>
        </div>
        <div>
            <input type="button" class="btn btn-primary sizew80 margint10" value="发送" onClick="send(); return false;"/>
            <%--<label>显示滚动条</label>--%>
            <%--<input name="scrollScreen" type="checkbox" class="noborder" id="scrollScreen"--%>
            <%--onClick="checkScrollScreen()" value="1" checked>--%>
        </div>
    </div>
</form>
</body>
</html>
