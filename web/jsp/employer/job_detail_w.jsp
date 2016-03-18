<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>职位编辑</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint64 marginb64">
<div class="col-md-8">
<div id="jobContent" class="form-horizontal" action="">
<div class="form-group">
    <label class="col-md-2 control-label"><em class="clrred"> *</em>职位名称</label>

    <div class="col-md-10">
        <input id="jobName" name="jobName" class="woo-check-input form-control sizew200" placeholder=""
               value="" required=""
               maxlength=80>

        <div class="woo-error">请填写职位名称</div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        <em class="clrred"> * </em>薪资待遇
    </label>

    <div class="col-md-10">
        <table>
            <tbody>
            <tr>
                <td>
                    <input id="salaryBegin" name="salaryBegin" class="form-control"
                           type="number" placeholder="0" value="0" required min="0" max="999999">

                    <div style="visibility: hidden">--</div>
                </td>
                <td class="woo-grey">
                    <label>--</label>

                    <div style="visibility: hidden">--</div>
                </td>
                <td>
                    <input id="salaryEnd" name="salaryEnd" class="form-control"
                           type="number" placeholder="0" value="0" required min="0" max="999999">

                    <div style="visibility: hidden">--</div>
                </td>
                <td class="woo-grey">
                    ￥&nbsp;&nbsp;都为0表示面议
                    <div style="visibility: hidden">--</div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        <em class="clrred"> * </em> 工作地点
    </label>

    <div class="col-md-10">
        <div id="provinceList" class="btn-group woo-btn-dropdown">
            <button id="province" type="button" class=" btn  dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">  选择省份 </span>

                <div class="padding0 textleft verticalmiddle"><span class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu sizemh260 scroll" role="menu" aria-labelledby="dropdownMenu1">
            </ul>
            <div class="woo-error">请填写省份</div>
        </div>
        <div id="cityList" class="btn-group woo-btn-dropdown">
            <button id="city" type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                    aria-expanded="false"><span
                    class="placeholder"> 选择城市</span>

                <div class="padding0 textleft verticalmiddle"><span class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu sizemh260 scroll" role="menu">
            </ul>
            <div class="woo-error">请填写城市</div>
        </div>
        <div id="areaList" class="btn-group woo-btn-dropdown">
            <button id="district" type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">选择地区</span>

                <div class="padding0 textleft verticalmiddle"><span class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu sizemh260 scroll" role="menu">
            </ul>
            <div class="woo-error">请填写地区</div>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        <em class="clrred"> * </em>
        职位类别
    </label>

    <div class="col-md-10">
        <div id="zhiweiMainList" class="btn-group woo-btn-dropdown">
            <button id="jobMainType" type="button" class="woo-check-drop-down btn  dropdown-toggle"
                    data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder">选择职位类别</span>

                <div class="padding0 textleft verticalmiddle"><span class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu sizemh260 scroll" role="menu">
            </ul>
            <div class="woo-error">请选择职位类别</div>
        </div>
        <div id="zhiweiSubList" class="btn-group woo-btn-dropdown">
            <button id="jobSubType" type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false"><span class="placeholder">选择职位子类别</span>

                <div class="padding0 textleft verticalmiddle"><span class="imageback downarrow size13"></span></div>
            </button>
            <ul class="dropdown-menu sizemh260 scroll" role="menu">
            </ul>
            <div style="visibility: hidden;display: none">选择职位子类别</div>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        <em class="clrred"> * </em>
        截止日期
    </label>

    <div class="col-md-10">
        <div class='input-group date datetimepicker-cls sizew200'>
            <input id="deadtime" type='text' class="woo-check-input form-control" data-date-format="YYYY/MM/DD"
                   placeholder="截止日期"/>
            <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
        </div>
        <div class="woo-error">请填写截止日期</div>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label">学历要求</label>

    <div class="col-md-4">
        <div id="bachelorDegreeList" class="btn-group woo-btn-dropdown">
            <button id="eduReq" type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">不限</span><span class="imageback downarrow size13"></span></button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
    <label class="col-md-2 control-label">
        专业要求
    </label>

    <div class="col-md-4">
        <div>
            <input id="majorReq" name="majorReq" class="form-control" value="不限" required>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        性别要求
    </label>

    <div class="col-md-4">
        <div id="sexReq" name="sexReq" class="radio">
            <label class="radio-inline">
                <input type="radio" name="sexReqRadio" id="sexReqRadio0" value="不限" checked>
                不限
            </label>
            <label class="radio-inline">
                <input type="radio" name="sexReqRadio" id="sexReqRadio1" value="男">
                男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sexReqRadio" id="sexReqRadio2" value="女">
                女
            </label>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">工作方式</label>

    <div class="col-md-2">
        <div id="workPatternList" class="btn-group woo-btn-dropdown">
            <button id="workPattern" type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder">全职</span>
                <span class="imageback downarrow size13"></span></button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        工作年限
    </label>

    <div class="col-md-2">
        <div id="experienceYearList" class="btn-group woo-btn-dropdown">
            <button id="expReq" type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">
                <span class="placeholder"> 不限 </span> <span class="imageback downarrow size13"></span></button>
            <ul class="dropdown-menu" role="menu">
            </ul>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        招聘人数
    </label>

    <div class="col-md-10">
        <table>
            <tbody>
            <tr>
                <td>
                    <input id="numberReq" name="numberReq" class="form-control"
                           type="number" placeholder="0" value="0" required min="0" max="999">
                </td>
                <td class="woo-grey">
                    &nbsp;&nbsp;0表示不限
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        职位标签
    </label>

    <div id="tagList" class="col-md-10">
                <span class="form-control sizew400"
                      data-toggle="popover" title=""
                      data-content="做一休一 周末双休 带薪年假 五险一金 年终双薪 奖金丰厚 绩效奖金 年终分红 股票期权 全勤奖 人才推荐奖 加班补助 交通补助 出差补贴 包吃包住 包住宿 包三餐 房补 话补 采暖补贴 高温补贴 节日福利 专业培训 立即上岗 提供班车 弹性工作 补充医疗保险 定期体检 员工旅游 出国机会"
                      data-original-title="示例">最多可以添加5个标签,每个标签字数不多于6个</span>

        <div class="margint10 padding0 col-md-6">
            <button id="tagPlusBtn" class="btn plus verticalmiddle"></button>
        </div>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label">
        <em class="clrred"> * </em>
        职位描述
    </label>

    <div class="col-md-10">
        <div>
            <textarea id="jobDesc" name="jobDesc" name="content" class="textarea"
                      placeholder="请输入。。。" required="" style="height: 320px; width: 100%;">
            </textarea>

            <div class="word_count">
                您最多可以输入<span>5000</span>字,请不要加入太多空行，5000字包含样式控制，这会大大减少输入字符字数限制。
            </div>
        </div>
        <!--通过$('#jobDesc').val();获得输入的值-->
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label">
        主页显示
    </label>

    <div class="col-md-10">
        <div id="mainPage" name="mainPage" class="radio">
            <label class="radio-inline">
                <input type="radio" name="mainPageRadio" id="mainPage0" value="1" checked="">
                显示
            </label>
            <label class="radio-inline">
                <input type="radio" name="mainPageRadio" id="mainPage1" value="0">
                不显示
            </label>
        </div>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label">
        网址是否显示在职位列表
    </label>

    <div class="col-md-10">
        <div id="website" name="websiteShow" class="radio">
            <label class="radio-inline">
                <input type="radio" name="websiteShowRadio" id="website0" value="1" checked="">
                显示
            </label>
            <label class="radio-inline">
                <input type="radio" name="websiteShowRadio" id="website1" value="0">
                不显示
            </label>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label">
        添加qq交谈
    </label>

    <div class="col-md-10">
        <button id="qqPlusBtn" class="btn plus verticalmiddle"></button>
    </div>
</div>
<div class="blockbackground">
    <label class="margint20">设置问卷(选填)</label>
    <label class="paddingl20 marginlp36" style="display: none">问卷id:<span id="questionnaireid"></span></label>

    <div class="clrbedeaf4 margint10 paddingt10 paddingb10 paddingl4r4">
        <h5>是否对<span class="clr440062">主动投递</span>本职位的投递者应用?</h5>

        <div class="radio">
            <label>
                <input type="radio" name="optionsRadios" id="optionsRadios1"
                       value="option1" checked> 是
            </label>
        </div>
        <div class="radio">
            <label>
                <input type="radio" name="optionsRadios" id="optionsRadios2"
                       value="option2"> 否
            </label>
        </div>

        <button id="questionnaireedit"
                class="btn btn-secondary btn-block sizew300 horizontalmiddle btn-lg"
                onclick="editQues()">
            问卷编辑
        </button>
    </div>
</div>

<div class="margint64 paddingt20">
    <div class="col-md-2 col-md-offset-8">
        <button
                class="btn btn-secondary btn-block"
                onclick="onReview()">
            预 览
        </button>
    </div>
    <div class="col-md-2">
        <button
                class="btn btn-primary btn-block"
                onclick="onPublish(true)">
            发 布
        </button>
    </div>
</div>
</div>
</div>
<%@ include file="employer_nav.jsp" %>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
    if (escape('${param.jobId}') != 'null' && escape('${param.jobId}') != '') {
        setDefault();
    }
    if ($("#deadtime").val() == '') {
        var datess = new Date((new Date()).getTime() + (60 * 1000 * 60 * 60 * 24));
        $("#deadtime").val(datess.format("yyyy/MM/dd"));
    }
    queryProvince();
    queryzhiweiMain();
    queryBachelorDegreeList();
    queryExperienceYearList();
    queryWorkPattern();
    $("#tagPlusBtn").click(function () {
        plusTag("");
    });
    $("#qqPlusBtn").click(function () {
        plusqq("");
    });
    hideErrors();
    queryEmployer();
    queryStore('${param.employerId}');
    bindTextareaCheck();
    $('#jobDesc').xheditor({
        tools: 'simple',
        skin: 'default'
//        upMultiple: false,
//        upImgUrl: "/page/demo/upload",
//        upImgExt: "jpg,jpeg,gif,bmp,png",
//        onUpload: insertUpload
    });
});

function bindTextareaCheck() {
    $("textarea").bind('input propertychange', function () {
        checkLength(this, 3000);
        $(this).next().find("span").text((3000 - this.value.length));
    });
}

function setDefault() {
    if (escape('${param.jobId}') == 'null') {
    }
    else {
        param_data = {
            jobId: escape('${param.jobId}')
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/job/jobdetail",
            data: $.param(param_data),
            success: function (data) {
                var value = data.job_detail;
                $("#jobName").attr('value', value.jobName);
                $("#salaryBegin").attr('value', value.salaryBegin);
                $("#salaryEnd").attr('value', value.salaryEnd);
                if (value.province != null && value.province != '') {
                    $("#provinceList").find(".placeholder").text(
                            value.province);
                }
                if (value.city != null && value.city != '') {
                    $("#cityList").find(".placeholder").text(value.city);
                }
                if (value.district != null && value.district != '') {
                    $("#areaList").find(".placeholder")
                            .text(value.district);
                }
                if (value.jobMainType != null && value.jobMainType != '') {
                    $("#zhiweiMainList").find(".placeholder")
                            .text(value.jobMainType);
                }
                if (value.jobSubType != null && value.jobSubType != '') {
                    $("#zhiweiSubList").find(".placeholder")
                            .text(value.jobSubType);
                }
                if (value.deadTime != null && value.deadTime != '') {
                    setDateTime($("#deadtime"), value.deadTime);
                }

                if (value.eduReq != null && value.eduReq != '') {
                    $("#bachelorDegreeList").find(".placeholder")
                            .text(value.eduReq);
                }
                if (value.expReq != null && value.expReq != '') {
                    $("#expReqList").find(".placeholder")
                            .text(value.expReq);
                }
                if (value.workPattern != null && value.workPattern != '') {
                    $("#workPatternList").find(".placeholder")
                            .text(value.workPattern);
                }
                if (value.tag1 != null && value.tag1 != '') {
                    plusTag(value.tag1);
                }
                if (value.tag2 != null && value.tag2 != '') {
                    plusTag(value.tag2);
                }
                if (value.tag3 != null && value.tag3 != '') {
                    plusTag(value.tag3);
                }
                if (value.tag4 != null && value.tag4 != '') {
                    plusTag(value.tag4);
                }
                if (value.tag5 != null && value.tag5 != '') {
                    plusTag(value.tag5);
                }
                if (value.jobDesc != null && value.jobDesc != '') {
                    $("#jobDesc").val(value.jobDesc);
                }
                if (value.majorReq != null && value.majorReq != '') {
                    $("#majorReq").val(value.majorReq);
                }
                if (value.numberReq != null && value.numberReq != '') {
                    $("#numberReq").attr('value', value.numberReq);
                }
                if (value.sexReq != null && value.sexReq != '') {
                    if (value.sexReq == "男") {
                        $('#sexReqRadio1').attr('checked', true);
                    } else if (value.sexReq == "女") {
                        $('#sexReqRadio2').attr('checked', true);
                    }
                }
                if (value.mainPage == 1) {
                    $('input:radio[name=mainPageRadio]:eq(0)').attr('checked', true);
                } else {
                    $('input:radio[name=mainPageRadio]:eq(1)').attr('checked', true);
                }
                if (value.websiteShow == 1) {
                    $('input:radio[name=websiteShowRadio]:eq(0)').attr('checked', true);
                } else {
                    $('input:radio[name=websiteShowRadio]:eq(1)').attr('checked', true);
                }
                if (regular(value.questionnaireId) != '') {
                    $('#questionnaireid').text(value.questionnaireId);
                    $("#questionnaireedit").text("修改问卷");
                    $('#questionnaireid').parent().show();
                } else {
                    $('#questionnaireid').parent().hide();
                }
                if (regular(value.questionnaireOn) == 0) {
                    $('input:radio[name=optionsRadios]')[1].checked = true;
                }
                if (value.qq != null && value.qq != '0') {
                    plusqq(value.qq);
                }
            }
        });
    }
}

function selectZhiweiMain(obj) {
    queryzhiweiSub(obj.val);
    $('#zhiweiSubList').find(".placeholder").text("选择职位子类别");
}


function setParam() {
//    var sexReq;
//    if ($('#sexReqRadio1').attr('checked')) {
//        sexReq = "男";
//    } else if ($('#sexReqRadio2').attr('checked')) {
//        sexReq = "女";
//    } else {
//        sexReq = "不限";
//    }
    var sexReq = $('input:radio[name=sexReqRadio]:checked').val();
    var mainPage = $('input:radio[name=mainPageRadio]:checked').val();
    var websiteShow = $('input:radio[name=websiteShowRadio]:checked').val();
    var tag1 = '', tag2 = '', tag3 = '', tag4 = '', tag5 = '';
    var ind = 1;
    $(".woo-tags").each(function () {
        if (ind == 1) {
            tag1 = $(this).find("input").val();
        } else if (ind == 2) {
            tag2 = $(this).find("input").val();
        } else if (ind == 3) {
            tag3 = $(this).find("input").val();
        } else if (ind == 4) {
            tag4 = $(this).find("input").val();
        } else if (ind == 5) {
            tag5 = $(this).find("input").val();
        }
        ind = ind + 1;
    });
    var qq = '0';
    if ($('#qq').val() != undefined) {
        qq = $('#qq').val();
    }
    var param_data = {
        jobName: $('#jobName').val(),
        jobMainType: $('#zhiweiMainList').find(".placeholder").text(),
        jobSubType: $('#zhiweiSubList').find(".placeholder").text(),
        province: $('#provinceList').find(".placeholder").text(),
        city: $('#cityList').find(".placeholder").text(),
        district: $('#areaList').find(".placeholder").text(),
        deadTime: $('#deadtime').val(),
        expReq: $('#experienceYearList').find(".placeholder").text(),
        eduReq: $('#bachelorDegreeList').find(".placeholder").text(),
        majorReq: $('#majorReq').val(),
        salaryBegin: $('#salaryBegin').val(),
        salaryEnd: $('#salaryEnd').val(),
        numberReq: $('#numberReq').val(),
        jobDesc: $('#jobDesc').val(),
        workPattern: $('#workPatternList').find(".placeholder").text(),
        sexReq: sexReq,
        mainPage: mainPage,
        websiteShow: websiteShow,
        tag1: tag1,
        tag2: tag2,
        tag3: tag3,
        tag4: tag4,
        tag5: tag5,
        qq: qq
    };
    if ($('input:radio[name=optionsRadios]').index($('input:radio[name=optionsRadios]:checked')) == 1) {
        $.extend(param_data, {questionnaireOn: 0});
    }
    if ($("#questionnaireid").text() != '') {
        $.extend(param_data, {questionnaireId: $("#questionnaireid").text()});
    }
    return param_data;
}

var employerDetail;
var storeName;

function employerStatusOk(employerDetail) {
    if (employerDetail == undefined) {
        return false;
    }
    if (employerDetail.censorStatus == 0) {
        $.simplyToast("您的店家身份审核还没有通过，我们将在您注册成功3个工作日内完成审核，请您耐心等待！");
        return false;
    } else if (employerDetail.status == 0) {
        alert("您需要完善资料后才能发布职位！点击确定，立即完善资料。");
        window.location.href = "/jsp/employer/employer_detail_w.jsp?employerId=" + escape("${param.employerId}");
    }
    return true;
}

function queryEmployer() {
    var url = '/page/demo/employer/employerdetail?employerId=' + escape('${param.employerId}');
    $.post(url, null, function (json) {
        var data = $.parseJSON(json);
        employerDetail = data.employer_detail;
        employerStatusOk(employerDetail);
    });
}

function queryStore(employerId) {
    var url = '/page/demo/employer/store/read?employerId='
            + employerId;
    $.post(url, null, function (data) {
        var value = data.employer_store;
        storeName = value.taobaoStoreName;
    }, "json");
}

function onPublish(go) {
    if (!formValidCheck())return false;
    if ($('#jobDesc').val().length > 5000) {
        var options = {
            delay: 10000
        };
        $.simplyToast("职位详情超过了5000，这可能是由于您的空行太多引起的，请去掉空行再发布，您可以将详情复制到文本编辑器以删除格式，再复制粘贴即可。", "danger", options);
        return false;
    }
    var param_data = setParam();
    if (!employerStatusOk(employerDetail)) return false;

    if (employerDetail != undefined) {
        $.extend(param_data, {
            employerId: employerDetail.employerId,
            employerName: employerDetail.employerName,
            website: employerDetail.website,
            storeName: storeName,
            logoPath: employerDetail.logoPath,
            saleType: employerDetail.saleType,
            employerScale: employerDetail.employerScale,
            lng: employerDetail.lng,
            lat: employerDetail.lat
        });
    }

    if (escape('${param.jobId}') == '') {
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/job/publish",
            data: $.param(param_data),
            success: function (msg) {
                if (go) {
                    window.location.href = "../employer/employer_jobs.jsp?employerId=" + escape("${param.employerId}") + "&employerName=" +
                            escape("${param.employerName}");
                }
            }
        });
    } else {
        $.extend(param_data, {
            jobId: escape('${param.jobId}')
        });
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "/page/demo/job/update",
            data: $.param(param_data),
            success: function (msg) {
                if (msg.retCode == 'fail') {
                    $.simplyToast("输入的职位字符串太长，请修改!");
                } else {
                    if (go) {
                        window.location.href = "../employer/employer_jobs.jsp?employerId=" +
                                escape('${param.employerId}') + "&employerName=" + escape("${param.employerName}");
                    }
                }
            }
        });
    }
}

function onReview() {
    if (!formValidCheck())return false;
    var param_data = setParam();
    var w;
    if ('${param.jobId}' == 'null') {
        w = window.open("job_detail_review.jsp?employerId=" + escape('${param.employerId}'), "_blank");
    } else {
        w = window.open("job_detail_review.jsp?employerId=" + escape('${param.employerId}') + "&jobId=" + escape("${param.jobId}"), "_blank");
    }
//    w.param_data = param_data;
    return w;
}

function editQues() {
    if (!formValidCheck()) {
        $.simplyToast("请先完成职位的编辑，再编辑问卷！", "warning");
        return false;
    }
    var win;
    if ($("#questionnaireid").text() != '') {
        win = window.open("/jsp/questionnaire/quesedit.jsp?questionnaireId=" + $("#questionnaireid").text(), "_blank");
    } else {
        win = window.open("/jsp/questionnaire/quesedit.jsp", "_blank");
    }
    if (win != null) {
        if (typeof win.addEventListener != "undefined") {
            win.addEventListener("unload", quickOut, false);
        } else {
            win.attachEvent("onunload", quickOut);
        }
    }
}

function quickOut() {
    onPublish(false);
}
</script>
<script type="text/javascript" src="/js/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="/js/xheditor_lang/zh-cn.js"></script>
<script>
    $('body').on('mouseover', '.datetimepicker-cls', function () {
        $(this).datetimepicker({
            pickTime: false,
            language: 'zh-CN'
        });
    });
</script>

<script type="text/javascript">
    $('[data-toggle="popover"]').popover({});
</script>
</body>
</html>
