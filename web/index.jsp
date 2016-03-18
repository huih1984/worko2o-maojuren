<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>
        猫举人网-电子商务招聘平台
    </title>
    <%@ include file="/jsp/comm/header.jsp" %>
    <meta property="qc:admins" content="26516356646517252566375"/>
    <meta property="wb:webmaster" content="b877b4de0fa1651e"/>
    <meta name="baidu-site-verification" content="sKheRIWBOx"/>
    <link href="/css/animate.css" rel="stylesheet">
    <%
        String hotJobName = PropertiesUtil.get("queryCondition",
                "QUERY_CONDITION_HOTNAME");
        String[] hotJobNameArr = hotJobName.split(",");
        String hotNameTemp = "";
        for (String hotName : hotJobNameArr) {
            hotNameTemp = hotNameTemp
                    + "<a class=\"grey\" href=\"javascript:;\" value=\"" + hotName + "\" onclick=\"queryReChi(this)\">"
                    + hotName + "</a> ";
        }
    %>

</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="header padding-zero hidden-xs">
    <h1 class="title">
        <div id="carousel" data-ride="carousel" data-interval="5200" class="carousel slide carousel-fade">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel" data-slide-to="0" class="active"></li>
                <li data-target="#carousel" data-slide-to="1"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="img-responsive" src="/img/pic1.jpg" alt="" onclick="javascript:window.location.href='/jsp/employer/employer_detail_r.jsp?employerId=59'" style="height:300px;width: 100%;">
                </div>
                <div class="item">
                    <img class="img-responsive" src="/img/pic2.jpg" alt="" onclick="javascript:window.location.href='/jsp/employer/employer_detail_r.jsp?employerId=58'" style="height:300px;width: 100%;">
                </div>
            </div>
        </div>
    </h1>
</div>
<div class="container main margint20 clrbfff paddingt20">
    <div class="">
        <div class="col-md-8 col-xs-12 woo-main padding-zero">
            <div class="search">
                <div class="input-group">
                    <div id="searchdrop" class="input-group-btn borderw2">
                        <button type="button" class="btn dropdown-toggle font15" data-toggle="dropdown"
                                aria-expanded="false">
                            <div class="row">
                                <div class="col-md-7 verticalmiddle"><span class="placeholder"> 职位 </span></div>
                                <div class="col-md-4 padding0 textleft verticalmiddle"><span
                                        class="imageback downarrow size13"></span></div>
                            </div>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li role="presentation" value="1" class="text-center">
                                <a role="menuitem" tabindex="1" href="javascript:;" onclick="setSearchNameType1()">
                                    职位
                                </a>
                            </li>
                            <li role="presentation" value="2" class="text-center">
                                <a role="menuitem" tabindex="2" href="javascript:;" onclick="setSearchNameType2()">
                                    公司
                                </a>
                            </li>
                        </ul>
                    </div>

                    <!-- /btn-group -->
                    <div class="inputAndSearch">
                        <input id="searchName" type="text" class="form-control solidborderl" placeholder=""/>

                        <div class="search-suggest" id="wrap-search">
                            <ul class="list-unstyled marginb0">
                            </ul>
                        </div>
                    </div>

                    <span class="input-group-btn">
                      <button id="searchconfirm" class="btn btn-primary padding0" type="button"
                              onclick="queryKeyWord()">
                          <span class="fa fa-search fa-2x"></span>
                      </button>
                    </span>


                </div>
                <p class="meta hidden-xs"><span href="javascript:;">热门搜索：</span>
                    <%=hotNameTemp%>
                </p>

                <div class="">
                    <a href="javascript:;" class="clra6a6a6 verticalmiddle" onclick="queryAll()">
                        所有分类 <span class="imageback rightarrow size13 verticalmiddle"></span>
                    </a>

                    <div id="allZhiWeiCondition">
                        <div id="allZhiWeiCondition1">
                        </div>
                        <div id="allZhiWeiCondition2">
                        </div>
                    </div>
                </div>
                <button class="collapsed floatright hideshow" data-toggle="collapse" data-target="#select-condition-id"
                        href="javascript:;">
                    <span id='toggle-btn-icon-id' class="imageback uparrow2 sizew10 sizeh10"></span>
                    <span id='toggle-btn-id'> &nbsp;隐藏搜索条件 </span>
                </button>
                <div id="select-condition-id" class="solidborder1 collapse in" style="clear: both;">

                    <div class="conditionitem clearfix" align="center" id="queryZhiWei2">
                        <ul class="list-unstyled" id="zhiweiInfo">

                        </ul>
                    </div>
                    <div class="conditionitem clearfix" align="center" id="querySaleType2">
                        <ul class="list-unstyled" id="saletypeInfo">
                        </ul>
                    </div>

                    <div class="conditionitem clearfix" align="center" id="queryXueli2">
                        <ul class="list-unstyled" id="xueliInfo">
                        </ul>
                    </div>

                    <div class="conditionitem borderb0 clearfix" align="center" id="queryGongzuo2">
                        <ul class="list-unstyled" id="gongzuoInfo">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="sortcondition clearboth">
                <div align="center">
                    <div class="hidden-xs verticalmiddle" style="width: 12%;">
                        <div class="verticalmiddle paddingl10" onclick="queryZonghe()"><label
                                id="zonghe" class="hint">综合排序</label></div>
                    </div>
                    <div class="hidden-xs verticalmiddle" style="width: 10%;">
                        <div class="verticalmiddle paddingl10" onclick="queryRedu()"><label
                                id="redu">热度</label></div>
                    </div>
                    <div class="col-xs-1  verticalmiddle" style="width: 10%;">
                        <label class="verticalmiddle padding0">薪资</label>
                    </div>
                    <div class="col-md-1 col-xs-1  verticalmiddle text-center padding0">
                        <input id="salaryBegin" type="text" data-container="body" data-toggle="popover"
                               data-placement="top"
                               data-content="请输入正整数">
                    </div>
                    <div class="col-md-1 col-xs-1  verticalmiddle text-center padding0" style="width: 5%;">
                        <label>-</label>
                    </div>
                    <div class="col-md-1 col-xs-1  verticalmiddle text-center padding0">
                        <input id="salaryEnd" type="text" data-container="body" data-toggle="popover"
                               data-placement="top"
                               data-content="请输入正整数">
                    </div>
                    <div class="col-md-1 col-xs-1 padding0 verticalmiddle text-center">
                        <a id="queryxinzi" href="javascript:;" onclick="check(this)">确定</a>
                    </div>
                    <div class="col-md-1  padding0 verticalmiddle select">
                        <select id="workPlace" style="width: 100%;"></select></li>
                    </div>
                    <div class="col-md-1  padding0 verticalmiddle select">
                        <select id="employerscaleInfo" style="width: 100%;"></select>
                    </div>
                    <div class="col-md-1  padding0 verticalmiddle select">
                        <select id="nianxianInfo" style="width: 100%;"></select>
                    </div>
                </div>
            </div>
            <ul class="clearfix list-group" id="tableList">
            </ul>
            <ul class="pagination " id="pagination">
            </ul>
        </div>
        <!-- /.woo-main -->
        <div id="spinerId" class="col-md-1 col-xs-12 text-center">
            <i class="fa fa-spinner fa-spin font45 clr8e44ad"></i>
        </div>
        <div id="rightcol" class="col-md-3 padding-zero hidden-xs">
            <div class="clearfix sidebar-module">
                <div><img id="cjjlId" src="/img/icon/cjjl.png" class="pointer marginb10" alt="创建简历"></div>
                <div><img id="fbzwId" src="/img/icon/fbzw.png" class="pointer marginb20" alt="发布职位"></div>
                <h5><i class="font20 glyphicon glyphicon-fire clrred"></i>&nbsp;热搜排行<span
                        class="clrred">&nbsp;Top10</span></h5>

                <div class="clearfix">
                    <ol id="newsList" class="rectangle-list">
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<%@ include file="/jsp/comm/comm_foot.jsp" %>
<script type="text/javascript">
var count = 0;
//function rotate() {
//    var elem5 = document.getElementById('div5');
//    elem5.style.MozTransform = 'scale(0.5) rotate(' + count + 'deg)';
//    elem5.style.WebkitTransform = 'scale(0.5) rotate(' + count + 'deg)';
//    if (count == 360) {
//        count = 0
//    }
//    count += 45;
//    window.setTimeout(rotate, 100);
//}
//window.setTimeout(rotate, 100);

$(function () {
    document.onkeydown = function (e) {
        if (event.keyCode == 13) {
            document.getElementById("searchconfirm").click();
            return false;
        }
    }
});

$(document).ready(function () {
    var userType = $.cookie('c');
    if (${sessionScope.username == null}) {
        if (userType == 'hunter') {
            var param_data = {
                userType: $.cookie('c'),
                jobHunterEmail: $.cookie('a'),
                jobHunterPassword: $.cookie('b')
            }
        } else {
            var param_data = {
                userType: $.cookie('c'),
                hrEmail: $.cookie('d'),
                password: $.cookie('e')
            }
        }
        login("null", param_data);
    }

    queryHotCompany();
    var mydropdown1 = new customDropDown($("#searchdrop"));
    queryMainPageJob(1);
    initSearchCondition();
    //bindWorkPlace();
    $("#searchName").bind("keypress", function (e) {
        if (e.which == 13) {
            queryJob(1);
        }
    });
    $("#searchButton").bind("click", function (e) {
        queryJob(1);
    });
    //首页面登录设置是不是mobile
    if (isMobile()) {
        <%session.setAttribute("isMobile", true);%>
    } else {
        <%session.setAttribute("isMobile", false);%>
    }

    if (${sessionScope.username != null}) {
        if (${sessionScope.userType == 'hunter'}) {
            $("#cjjlId").click(function () {
                window.location.href = '/jsp/hunter/basic_info_w.jsp?jobHunterId=${sessionScope.jobHunterId}';
            });
            $("#fbzwId").css("display", "none");
        } else {
            $("#fbzwId").click(function () {
                window.location.href = '/jsp/employer/job_detail_w.jsp?employerId=${sessionScope.employerId}';
            });
            $("#cjjlId").css("display", "none");
        }
    } else {
        $("#cjjlId").click(function () {
            window.location.href = "/login.jsp";
        });
        $("#fbzwId").click(function () {
            window.location.href = "/login.jsp?&userType=2";
        });
    }
})

bindRotate180(document.getElementById("toggle-btn-id"), document.getElementById("toggle-btn-icon-id"));
bindRotate180(document.getElementById("searchdrop"), document.querySelector("#searchdrop .downarrow"));
$('#select-condition-id').on(
        'hidden.bs.collapse',
        function () {
            //toggle-icon-id
            $('#toggle-btn-id').text(" 显示搜索条件");
//                $('#toggle-btn-icon-id').removeClass('uparrow2').addClass('downarrow2');
        });
$('#select-condition-id').on(
        'shown.bs.collapse',
        function () {
            //toggle-icon-id
            $('#toggle-btn-id').text(" 隐藏搜索条件");
//                $('#toggle-btn-icon-id').removeClass('downarrow2').addClass('uparrow2');
        });

function check(th) {
    var salarybegin = $("#salaryBegin").val();
    var salaryend = $("#salaryEnd").val();
    var re = /^[0-9]*$/;
    if (re.test(salarybegin)) {
        $("#salaryBegin").popover('hide');
    }
    if (re.test(salaryend)) {
        $("#salaryEnd").popover('hide');
    }
    if (!re.test(salarybegin)) {
        $("#salaryBegin").popover('show');
    } else if (!re.test(salaryend)) {
        $("#salaryEnd").popover('show');
    } else {
        queryXinZhi(th);
    }
}

function searchSuggest(searchFuc) {
    var input = $('#searchName');
    var wrap = $('#wrap-search');
    var key = "";
    var hideSuggest = function () {
        wrap.hide();
    }
    var init = function () {
        input.bind('keyup', sendKeyWord);
        input.bind('blur', function () {
            setTimeout(hideSuggest, 100);
        });
    }

    var sendKeyWord = function (event) {
        if (event.keyCode == 38 || event.keyCode == 40) {
            var current = wrap.find('li.hover');
            if (event.keyCode == 38) {
                if (current.length > 0) {
                    var prev = current.removeClass('hover').prev();
                    if (prev.length > 0) {
                        prev.addClass('hover');
                        input.val(prev.html());
                    }
                } else {
                    var last = wrap.find('li:last');
                    last.addClass('hover');
                    input.val(last.html());
                }
            } else if (event.keyCode == 40) {
                if (current.length > 0) {
                    var next = current.removeClass('hover').next();
                    if (next.length > 0) {
                        next.addClass('hover');
                        input.val(next.html());
                    }
                } else {
                    var first = wrap.find('li:first');
                    first.addClass('hover');
                    input.val(first.html());
                }
            }
        } else {
            var inputText = $.trim(input.val());
            if (inputText == '' || inputText == key) {
                return;
            }
            searchFuc(inputText);
            key = inputText;
        }
    }
    this.dataDisplay = function (data) {
        if (data.length <= 0) {
            wrap.hide();
            return;
        }
        var li;
        var tmp = document.createDocumentFragment();
        wrap.find('ul').html('');
        for (var i = 0; i < data.length; i++) {
            li = document.createElement('li');
            li.innerHTML = data[i];
            tmp.appendChild(li);

        }
        wrap.find('ul').append(tmp);
        wrap.show();

        wrap.find('li').hover(function () {
            wrap.find('li').removeClass('hover');
            $(this).addClass('hover');
        }, function () {
            $(this).removeClass('hover');
        });
        wrap.find('li').click(function () {
            input.val(this.innerHTML);
            queryKeyWord(); //鼠标点击关键词后立即调用搜索
            wrap.hide();
        })
    }
    init();

}
var suggest = new searchSuggest(backend);
function backend(keyword) {
//        var url = encodeURI('/page/demo/job/jobpage' + getData(1));
    var param = encodeURI(getData(1)).replace(/\+/g, '%2B');
    var url = '/page/demo/job/jobpage' + param;
    $.post(
            url,
            null,
            function (data) {
                var listSuggest = data.job_list;
                var nameList = [];
                var objList = {};
                if (listSuggest != null && listSuggest.length > 0) {
                    var length = listSuggest.length;
                    for (var i = 0, elem; i < length && objSize(objList) < 10 && ((elem = listSuggest[i].jobName) != null); i++) {
                        if (searchNameType == 1) {
                            if (!objList[elem]) {
                                nameList.push(listSuggest[i].jobName);
                                objList[elem] = 1;
                            }
                        } else if (searchNameType == 2) {
                            if (!objList[elem]) {
                                nameList.push(listSuggest[i].employerName);
                                objList[elem] = 1;
                            }

                        }
                    }

                }
                suggest.dataDisplay(nameList);
            },
            'json'
    );
}

function objSize(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) {
            size++;
        }
    }
    return size;
}
</script>
</body>
</html>
