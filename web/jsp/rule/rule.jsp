<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>注册</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="woo-form sizew720 horizontalmiddle">
        <h4 class="fontem">用户积分规则：</h4>

        <ol>
            <%--<li>完善简历，每增加一条简历的内容，积分增加5分。</li>--%>
            <li>
                在社区发表提问，长度在1-100，一次加2分，多余100，一次加5分。
            </li>
            <li>
                提问的回答，获得一个点赞积分加2分。
            </li>
            <li>
                发表性质恶劣的帖子或者回帖（如恶意攻击商户，造谣诽谤），系统将删除帖子，同时视情节严重性扣除10-20个积分，直至积分清零为止。
            </li>
        </ol>

        <h4 class="fontem">企业积分规则：</h4>
        <ol>
            <li>
                反馈应约，跳失分别给予积分10个积分
            </li>
            <li>
                发表公开问卷给予积分10个积分
            </li>
        </ol>

    </div>
</div>
<%@ include file="/jsp/comm/comm_foot.jsp" %>
<%@ include file="/jsp/comm/comm_js.jsp" %>
</body>
</html>