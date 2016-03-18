<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
</head>
<body>
<form name="form1" method="post"
      action="method.jsp?action=sendMsg">
    <%=session.getAttribute("username")%>：<input name="msg" id="msg"
                                                 type="text" size="60"/> <input type="submit" name="Submit"
                                                                                value="发言"/>
</form>
</body>
</html>
</span>