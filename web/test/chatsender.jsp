<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    // 这三句话只是为了防止浏览器缓存
    response.setHeader("Cache-Control", "no-cache, no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server


    // 获取POST的“发言”
    String msg = request.getParameter("msg");
    if (msg != null && msg.length() > 0) {
        msg = "[" + sdf.format(new Date()) + "]" + session.getId() + ": " + msg;
        System.out.println(msg); // 调试跟踪用的信息
        ArrayList<String> lstMsg = (ArrayList<String>) application.getAttribute("room");
        synchronized (lstMsg) {
            lstMsg.add(msg); // 消息放入池中（消息太多会溢出的，这里并没有进行处理）
            lstMsg.notifyAll(); // 通知chatwnd.jsp们，有新的消息来了，可以推给浏览器了
        }
    }
%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<form method="post">
    My Speak: <input name="msg" type="text" size="40"/> <input type="submit"/>
</form>
</html>