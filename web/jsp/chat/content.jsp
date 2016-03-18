<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="enhance" uri="http://pukkaone.github.com/jsp" %>
<enhance:out escapeXml="false">
    <%=application.getAttribute("room" + request.getSession().getAttribute("jobHunterId") + "" + request.getSession().getAttribute("employerId"))%>
</enhance:out>