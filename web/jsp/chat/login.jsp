<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%
    String type = request.getParameter("type");
    String url;
    String jobHunterId = request.getParameter("jobHunterId");
    String employerId = request.getParameter("employerId");
    String online = request.getParameter("online");
    request.getSession().setAttribute("jobHunterId", jobHunterId);
    request.getSession().setAttribute("employerId", employerId);
    request.getSession().setAttribute("online", online);

    if ("1".equals(type)) {
        String employerName = request.getParameter("employerName");
        String logoPath = request.getParameter("logoPath");
        request.getSession().setAttribute("employerName", employerName);
        request.getSession().setAttribute("logoPath", logoPath);
        url = "/Messages?action=loginRoom";
    } else {
        String hunterName = request.getParameter("hunterName");
        String avatarPath = request.getParameter("avatarPath");
        request.getSession().setAttribute("avatarPath", avatarPath);
        request.getSession().setAttribute("hunterName", hunterName);
        url = "/Messages?action=loginRoom";
    }


    //保存当前登录的用户名
    session.setAttribute("loginTime", new Date().toLocaleString());        //保存登录时间
    response.sendRedirect(url);
%>