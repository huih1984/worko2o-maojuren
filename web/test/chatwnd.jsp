<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<html>
<h2>Welcome to the ChatRoom!</h2>
<%
    // 这三句话只是为了防止浏览器缓存
    response.setHeader("Cache-Control", "no-cache, no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server


    Integer myMsgPos = 0; // 记录最后显示消息的位置
    ArrayList<String> lstMsg = (ArrayList<String>) application.getAttribute("room");
    out.flush(); // 把之前的HTML信息都刷出去
    PrintWriter pw = response.getWriter(); // 用PrintWriter取代JspWriter以便于检查Socket异常
    try {
        synchronized (lstMsg) {
            while (true) { // 死循环，也就意味着本次HTTP请求不会结束
                if (lstMsg.size() > myMsgPos) { // 检查有没有新的消息
                    // 循环显示所有新消息
                    System.out.println("Got new msg to push: " + (lstMsg.size() - myMsgPos));
                    for (int i = myMsgPos; i < lstMsg.size(); i++) {
                        String msg = lstMsg.get(i);
                        pw.write("<p>" + msg + "</p>");
                    }
                    if (pw.checkError()) { // 强制将输出缓冲区内容发送出去，并检查异常
                        System.out.println("IOException detected, JSP end.");
                        break; // 说明已经发生IOException（一般是浏览器页面关掉了）
                    }
                    myMsgPos = lstMsg.size(); // 更新最后显示的消息位置
                }
                System.out.println("Waiting new msg..." + out.getRemaining());
                lstMsg.wait(); // 临时释放对lstMsg的独占（同步锁），等待新消息的到来
            }
        }
    } catch (Exception ex) {
        System.out.println(ex);
    }
%>
</html>