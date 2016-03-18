<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    session.invalidate();
    response.sendRedirect("/jsp/chat/index.jsp");
%>