var searchCity = null;
var searchClassPropertyId = null;
//var searchNameType = null;
var searchNameType = 1;
var searchSaleType = null;
var searchEduReq = null;
var searchExpReq = null;
var searchEmployerScale = null;
var searchWorkPattern = null;
var searchSalaryBegin = null;
var searchSalaryEnd = null;
var searchReduType = 0;

function queryHotCompany() {
    var yestoday = new Date();
    yestoday.setDate(yestoday.getDate() - 1);

    var url = '/page/demo/employer/hotemployers';


    $.post(url, null, function (data) {
        var list = data.employer_hot;

        $('#newsList').html("");
        for (var i = 0; i < Math.min(list.length, 10); i++) {
            var logoPath = '';
            if (list[i].logoPath == undefined) {
                logoPath = '/img/companydefault.png';
            } else {
                logoPath = list[i].logoPath;
            }
            var top3clr = '';
            if (i < 3) {
                top3clr = 'clrred';
            }
            var liInfo =
                '<li class="marginb10">'
                    + '<a class="' +
                    top3clr +
                    '" href="/jsp/employer/employer_detail_r.jsp?employerId='
                    + list[i].employerId + '" target="_blank">' + list[i].employerName +
                    '</a></li>';

            $('#newsList').append(liInfo);

        }
    }, "json");
}

function addHotTimes(employerId, employerName, logoPath, jobId) {
    var param_data = {
        employerId: employerId,
        employerName: employerName,
        logoPath: logoPath,
        jobId: jobId
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/employer/addtimes",
        success: function (data) {
        }
    });
    window.open("/jsp/job/job_detail_r.jsp?jobId=" + jobId, "_blank");

}

function getQqSection(qq) {
    return "<a class='label label-warning clrfff marginl10' href='tencent://Message/?Uin=" +
        qq +
        "&websiteName=q-zone.qq.com&Menu=yes'>QQ咨询</a>";
}


function setJobList(url) {
    $.post(
        url,
        null,
        function (data) {
            var list = data.job_list;
            $('#pagination').html(data.paginationInfo);
            $('#tableList').html("");
            if (list != null && list.length > 0) {
                var imgSize = null;
                for (var i = 0; i < list.length; i++) {
                    //公司信息
                    var logoPath = '';
                    if (list[i].logoPath == undefined) {
                        logoPath = '/img/companydefault.png';
                    } else {
                        logoPath = list[i].logoPath;
                    }
                    var addHotTimesStr = "addHotTimes(\"" +
                        list[i].employerId +
                        "\",\"" +
                        list[i].employerName +
                        "\",\"" +
                        logoPath +
                        "\",\"" +
                        list[i].jobId +
                        "\")";
                    var tagInfo = '<li><ul class="list-inline">';
                    if (list[i].tag1 != null && list[i].tag1 != "") {
                        tagInfo = tagInfo
                            + "<li class=\"tag2\">"
                            + "<span>"
                            + list[i].tag1 + "</span></li>";
                    }
                    if (list[i].tag2 != null && list[i].tag2 != "") {
                        tagInfo = tagInfo
                            + "<li class=\"tag2\">"
                            + "<span>"
                            + list[i].tag2 + "</span></li>";
                    }
                    if (list[i].tag3 != null && list[i].tag3 != "") {
                        tagInfo = tagInfo
                            + "<li class=\"tag2\">"
                            + "<span>"
                            + list[i].tag3 + "</span></li>";
                    }
                    if (list[i].tag4 != null && list[i].tag4 != "") {
                        tagInfo = tagInfo
                            + "<li class=\"tag2\">"
                            + "<span>"
                            + list[i].tag4 + "</span></li>";
                    }
                    if (list[i].tag5 != null && list[i].tag5 != "") {
                        tagInfo = tagInfo
                            + "<li class=\"tag2\">"
                            + "<span>"
                            + list[i].tag5 + "</span></li>";
                    }
                    tagInfo = tagInfo + '</ul></li>';
                    var editli = '';
                    if (list[i].questionnaireId != null && list[i].questionnaireOn == 1) {
                        editli = '<i class="fa fa-edit marginl10"></i>';
                    }
                    var updateTime = list[i].updateTime;
                    if (updateTime != null
                        && updateTime.length > 10) {
                        updateTime = updateTime.substr(0, 10);
                    }
                    var workPlace = "";
                    if (list[i].district == undefined
                        || list[i].district === "") {
                        workPlace = list[i].city;
                    } else {
                        workPlace = list[i].city + "-"
                            + list[i].district;
                    }

                    var imgStr =
                        '<div class="box viewport-flip" title="点击翻面">' +
                            '<div class="list flip in a"><img class="circle sizew64 sizeh64" src="' +
                            logoPath +
//                        '/img/avatar_female.png' +
                        '" alt="' +
                        list[i].employerName +
                        '" /></div>' +
//                        '<div class="list flip out b">'
//                        + '<div class="hrratioid circle sizew150 sizeh150 text-center paddingt20">'
//                        + '<input class="employeridhidden" type="hidden" value="'
//                        + list[i].employerId
//                        + '">'
//                        + '<label class="clrffa201 font15 fontem paddingl4r4">HR 效率</label>'
//                        + '<div class="hrratio font15 clra6a6a6 paddingl4r4">还没数据哦~</div>'
//                        + '</div>'
//                        + '</div>' +
                        '</div>';

                    var website = '';
                    if (regular(list[i].website) != '' && list[i].websiteShow == 1) {
                        if (list[i].website.indexOf("http") < 0 && list[i].website.indexOf("https:") < 0) {
                            list[i].website = "http://" + list[i].website;
                        }
                        website = "<a target='_blank' href='"
                            + list[i].website
                            + "' class='label label-warning clrfff'>" +
                            "店铺" +
                            "</a>"
                    }

                    var qqtalk = '';
                    if (list[i].qq != 0 && list[i].qq != null && !isMobile()) {
                        qqtalk = getQqSection(list[i].qq);
                    }
                    var salary = salaryVal(list[i].salaryBegin, list[i].salaryEnd);
                    var liInfo = "<div class=\"woolistitem blockbackground \" "
                        + ">"
                        + "<div class=\"row\">"
                        + "<div class=\"hidden-sm hidden-xs col-md-2\">"
                        + "<a class=\"media-left\" target='_blank' href='"
                        + "/jsp/employer/employer_detail_r.jsp?employerId=" + list[i].employerId
                        + "' >"
                        + imgStr
                        + "</a>"
                        + "</div>"
                        + "<div class=\"col-lg-5 col-md-5 col-sm-7 col-xs-12 job-title\">"
                        + "<h5><a class=\"font16 clr440062 letterspace1 fontem\"  href='javascript:void(0);' onclick='"
                        + addHotTimesStr
                        + "'>"
                        + list[i].jobName
                        + editli
                        + "</a></h5>"
//                        + "<p>"
                        + "<ul class=\"list-inline\">"
                        + "<li class='clr440062'>"
                        + "<i class='fa fa-credit-card'></i> "
                        + "</li>"
                        + "<li>"
                        + '<a href="javascript:;"> '
                        + list[i].employerName
                        + '</a>'
                        + "</li>"
                        + "</ul>"
//                        + "</p>"
//                        + "<p>"
                        + "<ul class=\"list-unstyled\">"
                        + tagInfo
                        + "</ul>"
//                        + " </p>"
                        + "</div>"
                        + "<div class=\"col-lg-5 col-md-5 col-sm-5 col-xs-12 job-location\">"
                        + "<h5 class='clrec6941 font15 fontem'><i class=\"fa fa-cny clr440062\"></i>月薪&nbsp;"
                        + salary
                        + "</h5><p><i class=\"fa fa-map-marker clr440062\"></i>"
                        + workPlace
                        + "</p>"
                        + "<p>"
                        + "<ul class=\"list-inline\">"
                        + "<li>"
                        + website
                        + "</li>"
                        + "<li>"
                        + qqtalk
                        + "</li>"
                        + "</ul>"
                        + " </p>"
                        + "</div>"
                        + "</div>"
                        + "<div class='list-bottom-right'>"
                        + "<ul class=\"list-inline clra6a6a6\">"
                        + "<li><i class=\"fa fa-comment-o\">"
                        + "</i><span>"
                        + (list[i].jobCommentCnt == undefined ? 0 : list[i].jobCommentCnt)
                        + "</span></li>"
                        + "<li><i class=\"fa fa-thumbs-o-up\">"
                        + "</i><span>"
                        + (list[i].jobPraisedCnt == undefined ? 0 : list[i].jobPraisedCnt)
                        + "</span></li>"
                        + " </ul>"
                        + "</div></div>";
                    var node = document.createElement('li');
                    node.className = "list-group-item";
                    node.innerHTML = liInfo;
                    $('#tableList')[0].appendChild(node);
                    queryAssess(list[i].jobId, $(node).find(".star"))
                }
            }
            $("#spinerId").css("visibility", "hidden");


        }, "json");
}

function queryJob(pageIndex) {
    $("#spinerId").css("visibility", "inherit");
    var param = encodeURI(getData(pageIndex)).replace(/\+/g, '%2B');
    var url = '/page/demo/job/jobpage' + param;
//    var url = encodeURI('/page/demo/job/jobpage' + getData(pageIndex));
//    var url = '/page/demo/job/jobpage' + encodeURIComponent(getData(pageIndex));
    setJobList(url)

}

function queryMainPageJob(pageIndex) {
    $("#spinerId").css("visibility", "inherit");
    var param = encodeURI(getData(pageIndex)).replace(/\+/g, '%2B');
    var url = '/page/demo/job/jobpagemainpage' + param;
    setJobList(url)
}
//
//$("body").on("mouseenter", ".box", function () {
//    var front = $(this).find(".in");
//    var back = $(this).find(".out");
//    if (back.hasClass("out")) {
//        front.addClass("out").removeClass("in");
//    }
//    setTimeout(function () {
//        if (front.hasClass("out")) {
//            back.addClass("in").removeClass("out");
//        }
//    }, 225);
//    queryHRRatio($(this).find(".employeridhidden").val(), this);
//    return false;
//});


function queryRedu() {
    searchReduType = 1;
    $("#zonghe").removeClass("hint");
    $("#redu").addClass("hint");
    queryJob(1);
}
function queryZonghe() {
    searchReduType = 0;
    $("#redu").removeClass("hint");
    $("#zonghe").addClass("hint");
    queryJob(1);
}
//组装查询条件
function getData(pageIndex) {
    if (pageIndex == undefined || pageIndex == null || pageIndex == "") {
        pageIndex = 1;
    }
    var data = '?pageIndex=' + pageIndex;
    //增加查询条件
    var keyWord = $("#searchName").val();
    if (keyWord != null && keyWord != "") {
        data = data + '&keyWord=' + keyWord;
    }
    if (searchCity != null && searchCity != "" && searchCity != "全国") {
        data = data + '&workplace=' + searchCity;
    }
    if (searchClassPropertyId != null && searchClassPropertyId != "" && searchClassPropertyId != undefined) {
        data = data + '&jobMainType=' + searchClassPropertyId;
    }
    if (searchNameType != null && searchNameType != "" && searchNameType != undefined) {
        data = data + '&keyWordType=' + searchNameType;
    }
    if (searchSaleType != null && searchSaleType != "" && searchSaleType != undefined) {
        data = data + '&saleType=' + searchSaleType;
    }

    if (searchEduReq != null && searchEduReq != "" && searchEduReq != undefined) {
        data = data + '&eduReq=' + searchEduReq;
    }
    if (searchExpReq != null && searchExpReq != "" && searchExpReq != undefined) {
        data = data + '&expReq=' + searchExpReq;
    }
    if (searchEmployerScale != null && searchEmployerScale != "" && searchEmployerScale != undefined) {
        data = data + '&employerScale=' + searchEmployerScale;
    }
    if (searchWorkPattern != null && searchWorkPattern != "" && searchWorkPattern != undefined) {
        data = data + '&workPattern=' + searchWorkPattern;
    }

    if (searchSalaryBegin != null && searchSalaryBegin != "" && searchSalaryBegin != undefined) {
        data = data + '&salaryBegin=' + searchSalaryBegin;
    }
    if (searchSalaryEnd != null && searchSalaryEnd != "" && searchSalaryEnd != undefined) {
        data = data + '&salaryEnd=' + searchSalaryEnd;
    }
    if (regular(searchReduType) != "" && searchReduType != 0) {
        data = data + '&redu=' + searchReduType;
    }
    return data;
}

function showInPage(value) {
    if (value == undefined) {
        return "";
    }
    return value;
}

function initSearchCondition() {
    //职位分类
    initSearchConditionZhiWei();

    //地区
    $.post(
        "/page/demo/dic/cityMainPage",
        null,
        function (data) {
            var list = data.region_list;
            var temp = "<option>全国</option>";
            for (var i = 0; i < list.length; i++) {
                temp += "<option>" + list[i].regionName + "</option>";
            }
            $('#workPlace').html(temp);
        }, "json");

    //薪资
    var url = '/page/demo/dic/common?classId=11';
    $.post(
        url,
        null,
        function (data) {
            var list = data.common_dictionary_list;
            var temp = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryXinZhi1">薪资:</li></div><div class="col-md-10">';
            for (var i = 0; i < list.length; i++) {
                if (list[i].classThresholdBegin === 0) {
                    temp = temp
                        + "<li><a href=\"javascript:;\" onclick=\"queryXinZhi(this)\" value=\"1\">"
                        + list[i].classThresholdEnd
                        + "元以下</a></li>";
                } else if (list[i].classThresholdEnd === ""
                    || list[i].classThresholdEnd == undefined) {
                    temp = temp
                        + "<li><a href=\"javascript:;\" onclick=\"queryXinZhi(this)\">"
                        + list[i].classThresholdBegin
                        + "元以上</a></li>";
                } else {
                    temp = temp
                        + "<li><a href=\"javascript:;\" onclick=\"queryXinZhi(this)\">"
                        + list[i].classThresholdBegin + "-"
                        + list[i].classThresholdEnd
                        + "元</a></li>";
                }
            }
            temp += "</div></div>";
            $('#queryXinZhi2').html(temp);
        }, "json");
    //淘宝店铺等级=>卖品类别
    var url = '/page/demo/dic/common?classId=15';
    $.post(url, null, function (data) {
        var list = data.common_dictionary_list;
        var temp = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="querySaleType1">热门行业:</li></div><div class="col-md-10">';
        for (var i = 0; i < list.length; i++) {
            temp = temp
                + "<li><a href=\"javascript:;\" onclick=\"querySaleType(this)\">"
                + list[i].classPropertyName + "</a></li>";
        }
        temp += '</div></div>';
        $('#querySaleType2').html(temp);
    }, "json");
    //点赞=>公司规模
    var url = '/page/demo/dic/common?classId=19';
    $.post(url, null, function (data) {
        var list = data.common_dictionary_list;
        var temp = "<option>不限</option>";
        for (var i = 0; i < list.length; i++) {
            if (list[i].classThresholdEnd === ""
                || list[i].classThresholdEnd == undefined) {
                temp += "<option>" + list[i].classThresholdBegin + "人以上</option>";
            } else {
                temp += "<option>" + list[i].classThresholdBegin + "-" + list[i].classThresholdEnd + "人</option>";
            }
        }
        $('#employerscaleInfo').html(temp);
    }, "json");
    //工作方式
    var url = '/page/demo/dic/common?classId=18';
    $.post(url, null, function (data) {

        var list = data.common_dictionary_list;
        var temp = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryGongzuo1">工作方式:</li></div><div class="col-md-10">';
        for (var i = 0; i < list.length; i++) {
            temp = temp
                + "<li><a href=\"javascript:;\" onclick=\"queryGongzuo(this)\">"
                + list[i].classPropertyName + "</a></li>";
        }
        temp += "</div></div>";
        $('#queryGongzuo2').html(temp);
    }, "json");

    //学历
    var url = '/page/demo/dic/common?classId=16';
    $.post(url, null, function (data) {

        var list = data.common_dictionary_list;
        var temp = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryXueli1">学历:</li></div><div class="col-md-10">';
        for (var i = 0; i < list.length; i++) {
            temp = temp + "<li><a href=\"javascript:;\" onclick=\"queryXueli(this)\">"
                + list[i].classPropertyName + "</a></li>";
        }
        temp += '</div></div>';
        $('#queryXueli2').html(temp);
    }, "json");

    //工作年限
    var url = '/page/demo/dic/common?classId=17';
    $.post(
        url,
        null,
        function (data) {
            var list = data.common_dictionary_list;
            var temp = "<option>不限</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].classThresholdBegin === 0) {
                    temp += "<option>" + list[i].classThresholdEnd + "年以下</option>";
                } else if (list[i].classThresholdEnd === ""
                    || list[i].classThresholdEnd == undefined) {
                    temp += "<option>" + list[i].classThresholdBegin + "年以上</option>";
                } else {
                    temp += "<option>" + list[i].classThresholdBegin + "-"
                        + list[i].classThresholdEnd + "年</option>";
                }
            }
            $('#nianxianInfo').html(temp);
        }, "json");
}

function initSearchConditionZhiWei(classPropertyId) {
    var url = '';
    if (classPropertyId == undefined) {
        url = '/page/demo/dic/commonWithCodition1?classId=10';
        searchClassPropertyId = null;
    } else {
        url = '/page/demo/dic/commonWithCodition2?parentClassId='
            + classPropertyId;
    }
    //searchClassPropertyId = classPropertyId;
    $.post(
        url,
        null,
        function (data) {
            var list = data.common_dictionary_list;
            if (list == undefined || list.length == 0) {
                $('#queryZhiWei1').hide();
                $('#queryZhiWei2').hide();
            } else {
                var classTitle = "职位分类:";
                if (classPropertyId != undefined) {
                    classTitle = list[0].classTitleName + ":";
                }
                var temp = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryZhiWei1">' + classTitle + '</li></div><div class="col-md-10">';
                for (var i = 0; i < list.length; i++) {
                    temp = temp
                        + "<li><a href=\"javascript:;\" onclick=\"queryZhiWei(this)\" id=\""
                        + list[i].classPropertyId + "\">"
                        + list[i].classPropertyName
                        + "</a></li>";
                }
                temp += '</div></div>';
                $('#queryZhiWei2').html(temp);
                $('#queryZhiWei1').show();
                $('#queryZhiWei2').show();
            }
        }, "json");
}


function queryAll() {
    initSearchConditionZhiWei();
    $('#queryZhiWei1').show();
    $('#queryZhiWei2').show();
    $('#allZhiWeiCondition1').html("");
    $('#allZhiWeiCondition2').find("a").click();
    queryJob(1);
}

function queryZhiWei(th) {
    var classPropertyId = $(th).attr("id");
    initSearchConditionZhiWei(classPropertyId);
    var temp = "<a href=\"javascript:;\" class='solidborder1 paddingl4r4 verticalmiddle' id=\""
        + classPropertyId
        + "\" onclick=\"notQueryZhiWei(this)\">"
        + $(th).html()
        + "</a> ";
    $('#allZhiWeiCondition1').append(temp);
    searchClassPropertyId = $(th).html();
    queryJob(1);
}

function notQueryZhiWei(th) {
    var classPropertyId = $(th).attr("id");
    $('#allZhiWeiCondition1').find("a").each(function () {
        var id = $(this).attr("id");
        if (id != undefined && id.length > classPropertyId.length) {
//            $(this).prev().remove();
            $(this).remove();
        }
    });
//    $(th).prev().remove();
//    $(th).remove();
    $('#allZhiWeiCondition2').find("a").click();
    initSearchConditionZhiWei(classPropertyId);
    searchClassPropertyId = null;
    queryJob(1);
}

function queryXinZhi(th) {
    searchSalaryBegin = $("#salaryBegin").val();
    searchSalaryEnd = $("#salaryEnd").val();
    queryJob(1);
}

function querySaleType(th) {
    var temp = "<a href=\"javascript:;\" class=\"tag sizew120 verticalmiddle\" onclick=\"notquerySaleType(this)\">"
        + $(th).html()
        + "</a>";
    $('#allZhiWeiCondition2').append(temp);
    $('#querySaleType1').hide();
    $('#querySaleType2').hide();
    searchSaleType = $(th).html();
    queryJob(1);
}
function notquerySaleType(th) {
    $(th).remove();
    $('#querySaleType1').show();
    $('#querySaleType2').show();
    searchSaleType = null;
    queryJob(1);
}


$("#employerscaleInfo").change(function () {
    searchEmployerScale = $(this).children('option:selected').val();
    if (searchEmployerScale == "不限")
        searchEmployerScale = null;
    queryJob(1);
})

function queryGongzuo(th) {
    var temp = "<a href=\"javascript:;\" class=\"tag sizew120 verticalmiddle\" onclick=\"notQueryGongzuo(this)\">"
        + $(th).html()
        + "</a>";
    $('#allZhiWeiCondition2').append(temp);
    $('#queryGongzuo1').hide();
    $('#queryGongzuo2').hide();
    searchWorkPattern = $(th).html();
    queryJob(1);
}
function notQueryGongzuo(th) {
    $(th).remove();
    $('#queryGongzuo1').show();
    $('#queryGongzuo2').show();
    searchWorkPattern = null;
    queryJob(1);
}

function queryXueli(th) {
    var temp = "<a href=\"javascript:;\" class=\"tag sizew120 verticalmiddle\" onclick=\"notQueryXueli(this)\">"
        + $(th).html()
        + "</a>";
    $('#allZhiWeiCondition2').append(temp);
    $('#queryXueli1').hide();
    $('#queryXueli2').hide();
    searchEduReq = $(th).html();
    queryJob(1);
}
function notQueryXueli(th) {
    $(th).remove();
    $('#queryXueli1').show();
    $('#queryXueli2').show();
    searchEduReq = null;
    queryJob(1);
}


function bindNianXian() {
    $("#nianxianInfo").find("li").find("a").each(function (i) {
        $(this).bind("click", function () {
            $("#nianxianInfo").find("li").find("a").attr("class", "");
            $(this).attr("class", "woo-select-now");
            searchExpReq = $(th).html();
            queryJob(1);
        });
    });
}

$("#nianxianInfo").change(function () {
    searchExpReq = $(this).children('option:selected').val();
    if (searchExpReq == "不限")
        searchExpReq = null;
    queryJob(1);
})

$("#workPlace").change(function () {
    searchCity = $(this).children('option:selected').val();
    if (searchCity == "不限")
        searchCity = null;
    queryJob(1);
})

//热词
function queryReChi(rc) {
    $("#searchName").val($(rc).attr("value"));
    queryJob(1);
}

function queryKeyWord() {
    queryJob(1);
}

function setSearchNameType1() {
    searchNameType = "1";
}
function setSearchNameType2() {
    searchNameType = "2";
}

function queryAssess(jobId, node) {
    var param_data = new Object();
    param_data.jobId = jobId;
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/jobassessr",
        success: function (data) {
            if (data.job_assess != null) {
                var interviewerNice = 0;
                var descriptionMatch = 0;
                for (var i = 0; i < data.job_assess.length; ++i) {
                    interviewerNice += data.job_assess[i].interviewerNice;
                    descriptionMatch += data.job_assess[i].descriptionMatch;
                }
                var percent = parseInt((interviewerNice + descriptionMatch) / (2 * data.job_assess.length));
                var li = "<li class='font14'><span class='fa fa-star'></span></li>";
                var lihalf = "<li class='font14'><span class='fa fa-star-half-o'></span></li>";
                var text = '';
                for (var j = 0; j < percent / 20; ++j) {
                    text += li;
                }
                if (percent % 20 > 10) {
                    text += lihalf;
                }
                node.html(text);
            }
        }
    })
}