<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="msgs" class="java.util.HashMap" scope="application"/>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    if (action.equals("login")) {
        //用户登录，获得用户名。然后创建两个变量，保存用户登录信息和聊天信息
        String username = request.getParameter("username");
        String msg = "欢迎" + username + "光临本聊天室！<br/>";
        session.setAttribute("username", username);
        msgs.put(username, msg);
        response.sendRedirect("main.html");
    }

    if (action.equals("sendMsg")) {
        String newMsg = session.getAttribute("username") + "：" + request.getParameter("msg");
        //发送消息时，将聊天室所有人的消息都加上新的发言内容
        Iterator it = msgs.keySet().iterator();
        String username = null;
        String msg = null;
        while (it.hasNext()) {
            username = (String) it.next();
            msg = (String) msgs.get(username);
            msg = msg + "<br/>" + newMsg;
            msgs.put(username, msg);
        }
        response.sendRedirect("inputMsg.jsp");
    }

    if (action.equals("showMsg")) {
        //显示某个用户的消息
        String username = (String) session.getAttribute("username");
        String msg = (String) msgs.get(username);
        out.println("loadContent.innerHTML=\"" + msg + "\";");
    }
%>