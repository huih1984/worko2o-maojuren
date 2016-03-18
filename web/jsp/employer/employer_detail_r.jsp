<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>公司详情</title>
    <%@ include file="/jsp/comm/header.jsp" %>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="header padding-zero hidden-xs">
    <h1 class="title">
        <img src="/img/header-banner2.png" alt="猫举人网"/>
    </h1>
</div>
<div class="container main">
    <div class="row">
        <div class="col-md-8">
            <div>
                <div id="employerdetail" class="blockbackground margint10">
                    <div>
                        <div class="fontem font20 clrbedeaf4">
                            <label class="clr440062 marginl4r4"><i
                                    class="mans verticalmiddle"></i>&nbsp;&nbsp;公司详情</label>
                        </div>
                        <div class="clrba6a6a6 margint20"></div>
                        <p id="inputemployerDesc" class="paddingl10 paddingr10">
                        </p>
                    </div>
                </div>

                <div id="jobList" class="blockbackground margint20 clrbedeaf4">
                    <div class="font20 fontem">
                        <label class="clr440062 marginl4r4"><i
                                class="nameplate verticalmiddle"></i>&nbsp;&nbsp;招聘职位</label>
                    </div>

                    <div class="clrba6a6a6 margint20 marginb10"></div>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-offset-1">
            <a href="javascript:void(0);">
                <img id="logoPreview" class="sizeh180 sizew180 margint-90 hidden-xs" src="../../img/companydefault.png"
                     alt="公司logo">
            </a>
            <h4 id="inputEmployer" class="marginl4r4 font20 margint20"></h4>
            <ul class="marginl4r4 list-unstyled">
                <li class="marginb10"><a id="webSiteId" href="#" class=""
                                         style="color: #0000ff; text-decoration: underline"></a></li>
                <li class="marginb10"><span class="fa fa-trophy clrffa201 font20">  </span><span
                        id="taobaoLevelId"></span></li>
                <li id="placeDetailId" class="marginb10 margint20"></li>
                <li class="marginb10"><span id="placeId"></span></li>
                <li class="marginb10">卖品种类：<span id="saleTypeId"></span></li>
                <li class="marginb10">公司规模：<span id="scaleId"></span></li>
                <li class="marginb10">
                    <ul id="tagList" class="list-inline"></ul>
                </li>
            </ul>
            <div id="hrratioid" class="solidborder1 sizew210 hidden-xs">
                <label class="font20 fontem paddingl4r4">HR 效率</label>

                <div class="hrratio font20 clra6a6a6 paddingl10">还没数据哦~</div>
                <label class="clra6a6a6 paddingl4r4">一个月内简历处理平均用时</label>
            </div>
            <div class="solidborder1 margint20 sizew210 hidden-xs">
                <div class="fontem marginl4r4 clra6a6a6">近一周被访问总次数</div>
                <div class="fontem marginl4r4 font40 clrffa201 bottomdashedborder"><i class="fa fa-home"></i> <span
                        id="total"></span></div>
                <canvas id="canvas" height="150" width="210"></canvas>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_foot.jsp" %>
<%@ include file="/jsp/comm/comm_js.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {
        queryEmployer(${param.employerId});
    });

    var dateArray = new Array('周日', '周一', '周二', '周三', '周四', '周五', '周六');
    var newDateArray = new Array();
    var currentDay = new Date().getDay();
    for (var i = 0; i < dateArray.length; ++i) {
        newDateArray[i] = dateArray[(i + currentDay) % 7];
    }

    window.onload = function () {

        queryEmployerjob(1, ${param.employerId});

        queryHRRatio('${param.employerId}', document.getElementById("hrratioid"));

        querysingleEmployerHot(${param.employerId});

    }
</script>
</body>
</html>