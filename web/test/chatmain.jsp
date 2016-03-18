<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%
    // 这三句话只是为了防止浏览器缓存
    response.setHeader("Cache-Control", "no-cache, no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server


    // 检查并按需创建“聊天室”
    ArrayList<String> lstMsg = (ArrayList<String>) application.getAttribute("room");
    if (lstMsg == null) {
        application.setAttribute("room", new ArrayList<String>());
    }
%>
<html>
<frameset rows="80%, 20%">
    <frame src="chatwnd.jsp"></frame>
    <frame src="chatsender.jsp"></frame>
</frameset>
</html>