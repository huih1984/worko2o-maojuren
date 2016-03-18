<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>职位预览</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">

    <div class="row">
        <div class="col-md-offset-2 col-sm-8 woo-main">

            <div class="row clrbedeaf4 margin0">
                <div class="col-md-10">
                    <div>
                        <h4 id="jobName" class="font24 fontem  letterspace2 clr440062 paddingt10"></h4>
                        <ul class="list-inline">
                            <li id="salary" class="clrec6941"></li>
                            <li id="workplace" class="fontem"></li>
                        </ul>
                        <ul class="list-inline">
                            <li>
                                <ul id="tags" class="list-inline">
                                </ul>
                            </li>
                        </ul>
                        <ul class="list-inline">
                            <li>
                                <ul class="list-inline clra6a6a6">
                                    <li>
                                        <label>发布：</label>
                                        <label id="updateTime"></label>
                                    </li>
                                    <li>
                                        <label>截止：</label>
                                        <label id="deadTime"></label>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="solidbordernotop paddingl4r4">
                <div class="paddingt20 font20 fontem">
                    <span class="sizeh20 sizew10 clrb440062 marginr10">&nbsp;</span><label>职位详情</label>
                </div>
                <div class="paddingt10">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="clra6a6a6  paddingl10">学历要求：</label>
                            <label id="eduReq"></label>
                        </div>
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">专业要求：</label>
                            <label id="majorReq"></label>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">经验要求：</label>
                            <label id="expReq"></label>
                        </div>
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">性别要求：</label>
                            <label id="sexReq"></label>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">职位类别：</label>
                            <label id="jobType"></label>
                        </div>
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">招聘人数：</label>
                            <label id="numberReq"></label>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="clra6a6a6 paddingl10">工作方式：</label>
                            <label id="workPattern"></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="margint20">
                <div class="solidborder1 paddingl4r4">
                    <div class="margint20 font20 fontem">
                        <span class="sizeh20 sizew10 clrb440062 marginr10">&nbsp;</span><label>职位描述</label>
                    </div>
                    <div id="jobDesc" class="paddingl10">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var opener = window.opener;
        if (opener) {
            var oDom = opener.document;
            $("#jobName").text(oDom.getElementById("jobName").value);
            var salaryBegin = oDom.getElementById("salaryBegin").value;
            var salaryEnd = oDom.getElementById("salaryEnd").value;
            if (salaryBegin == 0 && salaryEnd == 0) {
                $("#salary").text("面议");
            } else if (salaryBegin == 0) {
                $("#salary").text(salaryEnd + "元");
            } else if (salaryEnd == 0) {
                $("#salary").text(salaryBegin + "元");
            } else {
                $("#salary").text(salaryBegin + "-" + salaryEnd + "元");
            }
            var te = oDom.getElementById("city");
            $("#workplace").text(oDom.getElementById("city").innerText + "-" + oDom.getElementById("district").innerText);
            var tag1 = '', tag2 = '', tag3 = '', tag4 = '', tag5 = '';
            var elements = oDom.getElementsByClassName("woo-tags");
            for (var i = 0; i < elements.length; ++i) {
                if (i == 0) {
                    tag1 = $(elements[i]).find("input").val();
                } else if (i == 1) {
                    tag2 = $(elements[i]).find("input").val();
                } else if (i == 2) {
                    tag3 = $(elements[i]).find("input").val();
                } else if (i == 3) {
                    tag4 = $(elements[i]).find("input").val();
                } else if (i == 4) {
                    tag5 = $(elements[i]).find("input").val();
                }
            }
            var tagInfo = '';
            var tagbefore = '<li class="label label-default">';
            var tagafter = '</li>';
            if (typeof(tag1) != "undefined" && tag1 != '') {
                tagInfo += tagbefore + tag1 + tagafter;
            }
            if (typeof(tag2) != "undefined" && tag2 != '') {
                tagInfo += tagbefore + tag2 + tagafter;
            }
            if (typeof(tag3) != "undefined" && tag3 != '') {
                tagInfo += tagbefore + tag3 + tagafter;
            }
            if (typeof(tag4) != "undefined" && tag4 != '') {
                tagInfo += tagbefore + tag4 + tagafter;
            }
            if (typeof(tag5) != "undefined" && tag5 != '') {
                tagInfo += tagbefore + tag5 + tagafter;
            }
            $("#tags").html(tagInfo);
            $("#jobDesc").html(oDom.getElementById("jobDesc").value);
            $("#eduReq").text(oDom.getElementById("eduReq").innerText);
            $("#expReq").text(oDom.getElementById("expReq").innerText);
            $("#majorReq").text(oDom.getElementById("majorReq").value);
            var sexReq;
            if (oDom.getElementById("sexReqRadio1").checked) {
                sexReq = "男";
            } else if (oDom.getElementById("sexReqRadio2").checked) {
                sexReq = "女";
            } else {
                sexReq = "不限";
            }
            $("#sexReq").text(sexReq);
            $("#workPattern").text(oDom.getElementById("workPattern").innerText);
            if (oDom.getElementById("numberReq").value == 0) {
                $("#numberReq").text("不限");
            } else {
                $("#numberReq").text(oDom.getElementById("numberReq").value + "人");
            }
            $("#jobType").text(oDom.getElementById("jobMainType").innerText + "-" + oDom.getElementById("jobSubType").innerText);

            var date = new Date();
            $("#updateTime").text(date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate());
            ele = oDom.getElementById("deadTime");
            var date2 = oDom.getElementById("deadTime").value.replaceAll('/', '-');
            $("#deadTime").text(date2);
        }

    })
</script>
</body>
</html>