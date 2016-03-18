/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-2-8
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */

function queryExperienceYearList() {
    $.post(
        '/page/demo/dic/common?classId=17',
        null,
        function (data) {
            setexprienceList(data, "experienceYearList", 1);
        }, "json");
}

function setexprienceList(data, id, nolimit) {
    $("#" + id + " ul").html("");
    var list = data.common_dictionary_list;

    if (nolimit == 1) {
        var liInfo = '<li role="presentation" value="'
            + (list[0].classPropertyId - 1)
            + '"> <a role="menuitem" tabindex="1"'
            + '" href="javascript:void(0);">选择'
            + '</a></li>';

        $("#" + id + " ul").append(liInfo);
    }

    for (var i = 0; i < list.length; i++) {

        if (list[i].classThresholdBegin === 0) {

            liInfo = '<li role="presentation" value="'
                + list[i].classPropertyId + '">'
                + '             <a role="menuitem" tabindex="' + (i + 2)
                + '" href="javascript:void(0);">' + list[i].classThresholdEnd
                + '年以下</a>' + '         </li>';
        } else if (list[i].classThresholdEnd === ""
            || list[i].classThresholdEnd == undefined) {

            liInfo = '<li role="presentation" value="'
                + list[i].classPropertyId + '">'
                + '             <a role="menuitem" tabindex="' + (i + 2)
                + '" href="javascript:void(0);">' + list[i].classThresholdBegin
                + '年以上</a>' + '         </li>';
        } else {

            liInfo = '<li role="presentation" value="'
                + list[i].classPropertyId + '">'
                + '             <a role="menuitem" tabindex="' + (i + 2)
                + '" href="javascript:void(0);">' + list[i].classThresholdBegin + "-"
                + list[i].classThresholdEnd
                + '年</a>' + '         </li>';
        }

        $("#" + id + " ul").append(liInfo);
    }

    new customDropDown($("#" + id), null);
}

function queryStartWorkYearList() {
    $.post("/page/demo/dic/common?classId=21", null, function (data) {
        var list = data.common_dictionary_list;
        $('#startWorkYearList ul').html("");
        var liInfo;
        liInfo = '<li role="presentation" >'
            + ' <a href="javascript:void(0);">选择</a></li>';
        $('#startWorkYearList ul').append(liInfo);
        for (var i = 0; i < list.length; i++) {
            liInfo = '<li role="presentation" value="' + list[i].jobSearchClassId + '">'
                + '             <a role="menuitem" tabindex="' + (i + 1) + '" href="javascript:void(0);">' + list[i].classPropertyName + '</a>'
                + '         </li>';

            $('#startWorkYearList ul').append(liInfo);
        }

        new customDropDown($("#startWorkYearList"), null);
    }, "json");
}

function queryBachelorDegreeList() {
    $.post("/page/demo/dic/common?classId=16", null, function (data) {

        var list = data.common_dictionary_list;

        $('#bachelorDegreeList ul').html("");
        var liInfo;
        liInfo = '<li role="presentation" >'
            + ' <a href="javascript:void(0);">选择</a></li>';
        $('#bachelorDegreeList ul').append(liInfo);
        for (var i = 0; i < list.length; i++) {
            liInfo = '<li role="presentation" value="' + list[i].jobSearchClassId + '">'
                + '             <a role="menuitem" tabindex="' + (i + 1) + '" href="javascript:void(0);">' + list[i].classPropertyName + '</a>'
                + '         </li>';

            $('#bachelorDegreeList ul').append(liInfo);
        }

        new customDropDown($("#bachelorDegreeList"), null);
    }, "json");
}

function queryBachelorDegreeListByNode(node) {
    $.post("/page/demo/dic/common?classId=16", null, function (data) {
        var list = data.common_dictionary_list;

        node.find('ul').html("");
        for (var i = 0; i < list.length; i++) {
            var liInfo =
                '           <li role="presentation" value="' + list[i].jobSearchClassId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 1) + '" href="javascript:void(0);">' + list[i].classPropertyName + '</a>'
                    + '         </li>';
            node.find('ul').append(liInfo);
        }
        new customDropDown(node, null);

    }, "json");
}

function queryWorkPattern() {
    $.post(
        '/page/demo/dic/common?classId=18',
        null,
        function (data) {
            $('#workPatternList ul').html("");
            var list = data.common_dictionary_list;
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#workPatternList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                liInfo = '<li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 1)
                    + '" href="javascript:void(0);">' + list[i].classPropertyName
                    + '</a>' + '         </li>';

                $('#workPatternList ul').append(liInfo);
            }

            new customDropDown($("#workPatternList"), null);
        }, "json");
}

function queryCurrentStatusList() {
    $.post("/page/demo/dic/currentstatus", null, function (data) {
        var list = data.current_status_list;

        $('#currentStatusList ul').html("");
        var liInfo;
        liInfo = '<li role="presentation" >'
            + ' <a href="javascript:void(0);">选择</a></li>';
        $('#currentStatusList ul').append(liInfo);
        for (var i = 0; i < list.length; i++) {
            liInfo =
                '           <li role="presentation" value="' + list[i].jobHunterCurrentStatusId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 1) + '" href="javascript:void(0);">' + list[i].status + '</a>'
                    + '         </li>';

            $('#currentStatusList ul').append(liInfo);
        }

        new customDropDown($("#currentStatusList"), null);
    }, "json");
}

function queryProvince() {
    $.post("/page/demo/dic/province", null,
        function (data) {
            var list = data.region_list;

            $('#provinceList ul').html("");
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#provinceList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                liInfo = '           <li role="presentation" value="'
                    + list[i].regionCode + '">'
                    + '             <a role="menuitem" tabindex="'
                    + (i + 1) + '" href="javascript:void(0);">'
                    + list[i].regionName + '</a>' + '         </li>';

                $('#provinceList ul').append(liInfo);
            }

            new customDropDown($("#provinceList"), selectProvince);

            var provinceName = $("#provinceList").find(".placeholder").text();
            var provinceCode = $(
                '#provinceList ul > li:has(> a:contains('
                    + provinceName + '))').attr('value');
            if (provinceCode != null && provinceCode != '') {
                queryCity(provinceCode.substring(0, 2));
            }
        }, "json");
}

function selectProvince(obj) {
    if (obj.text.indexOf("选择") < 0) {
        queryCity(obj.val.substring(0, 2));
    }
    $('#cityList').find(".placeholder").text("选择城市");
    $('#areaList').find(".placeholder").text("选择地区");
    $('#areaList ul').html("");
}

function queryCity(provinceCode) {
    $
        .post(
        "/page/demo/dic/city?provinceCode=" + provinceCode,
        null,
        function (data) {
            var list = data.region_list;

            $('#cityList ul').html("");
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#cityList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                liInfo = '           <li role="presentation" value="'
                    + list[i].regionCode
                    + '">'
                    + '             <a role="menuitem" tabindex="'
                    + (i + 1)
                    + '" href="javascript:void(0);">'
                    + list[i].regionName
                    + '</a>'
                    + '         </li>';

                $('#cityList ul').append(liInfo);
            }

            new customDropDown($("#cityList"), selectCity);

            var cityName = $("#cityList").find(".placeholder")
                .text();
            var cityCode = $(
                '#cityList ul > li:has(> a:contains('
                    + cityName + '))').attr('value');
            if (cityCode != null && cityCode != '') {
                queryArea(cityCode.substring(0, 4));
            }
        }, "json");
}

function selectCity(obj) {
    if (obj.text.indexOf("选择") < 0) {
        queryArea(obj.val.substring(0, 4));
    }
    $('#areaList').find(".placeholder").text("选择地区");
}

function queryArea(cityCode) {
    $.post("/page/demo/dic/area?cityCode=" + cityCode, null, function (data) {
        var list = data.region_list;

        $('#areaList ul').html("");
        var liInfo;
        liInfo = '<li role="presentation" >'
            + ' <a href="javascript:void(0);">选择</a></li>';
        $('#areaList ul').append(liInfo);
        for (var i = 0; i < list.length; i++) {
            liInfo = '           <li role="presentation" value="'
                + list[i].regionCode + '">'
                + '             <a role="menuitem" tabindex="' + (i + 1)
                + '" href="javascript:void(0);">' + list[i].regionName
                + '</a>' + '         </li>';

            $('#areaList ul').append(liInfo);
        }

        new customDropDown($("#areaList"), null);
    }, "json");
}

function queryCreditList() {
    $.post(
        "/page/demo/dic/common?classId=12",
        null,
        function (data) {
            var list = data.common_dictionary_list;
            $('#creditList ul').html("");
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#creditList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                liInfo = '<li role="presentation" value="' + list[i].jobSearchClassId + '">'
                    + '<a role="menuitem" tabindex="'
                    + (i + 1)
                    + '" href="javascript:void(0);">'
                    + list[i].classPropertyName
                    + '</a>'
                    + '         </li>';
                $('#creditList ul').append(liInfo);
            }
            new customDropDown($("#creditList"), null);
        }, "json");
}

function queryScaleList() {
    $.post(
        "/page/demo/dic/common?classId=19",
        null,
        function (data) {
            var list = data.common_dictionary_list;
            $('#scaleList ul').html("");
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#scaleList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                if (list[i].classThresholdEnd === ""
                    || list[i].classThresholdEnd == undefined) {
                    liInfo = '           <li role="presentation" value="' + list[i].jobSearchClassId + '">'
                        + '             <a role="menuitem" tabindex="'
                        + (i + 1)
                        + '" href="javascript:void(0);">'
                        + list[i].classThresholdBegin
                        + '人以上</a>'
                        + '         </li>';
                } else {
                    var liInfo = '           <li role="presentation" value="' + list[i].jobSearchClassId + '">'
                        + '             <a role="menuitem" tabindex="'
                        + (i + 1)
                        + '" href="javascript:void(0);">'
                        + list[i].classThresholdBegin
                        + '-'
                        + list[i].classThresholdEnd
                        + '人</a>'
                        + '         </li>';
                }


                $('#scaleList ul').append(liInfo);
            }

            new customDropDown($("#scaleList"), null);
        }, "json");
}

function querySaleTypeList() {
    $.post(
        "/page/demo/dic/common?classId=15",
        null,
        function (data) {
            var list = data.common_dictionary_list;
            $('#saleTypeList ul').html("");
            var liInfo;
            liInfo = '<li role="presentation" >'
                + ' <a href="javascript:void(0);">选择</a></li>';
            $('#saleTypeList ul').append(liInfo);
            for (var i = 0; i < list.length; i++) {
                liInfo = '           <li role="presentation" value="' + list[i].jobSearchClassId + '">'
                    + '             <a role="menuitem" tabindex="'
                    + (i + 1)
                    + '" href="javascript:void(0);">'
                    + list[i].classPropertyName
                    + '</a>'
                    + '         </li>';

                $('#saleTypeList ul').append(liInfo);
            }
            new customDropDown($("#saleTypeList"), null);
        }, "json");
}

function querySalaryList() {
    $.post("/page/demo/dic/common?classId=11", null, function (data) {
        var list = data.common_dictionary_list;

        $('#salaryList ul').html("");
        for (var i = 0; i < list.length; i++) {

            if (list[i].classThresholdBegin === 0) {

                liInfo = '<li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 2)
                    + '" href="javascript:void(0);">' + list[i].classThresholdEnd
                    + '元以下</a>' + '         </li>';
            } else if (list[i].classThresholdEnd === ""
                || list[i].classThresholdEnd == undefined) {

                liInfo = '<li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 2)
                    + '" href="javascript:void(0);">' + list[i].classThresholdBegin
                    + '元以上</a>' + '         </li>';
            } else {

                liInfo = '<li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 2)
                    + '" href="javascript:void(0);">' + list[i].classThresholdBegin + "-"
                    + list[i].classThresholdEnd
                    + '元</a>' + '         </li>';
            }

            $('#salaryList ul').append(liInfo);
        }
        liInfo = '<li role="presentation" value="'
            + '">'
            + '             <a role="menuitem" tabindex="' + (list.length + 2)
            + '" href="javascript:void(0);">'
            + '面议</a>' + '         </li>';
        $('#salaryList ul').append(liInfo);
        new customDropDown($("#salaryList"), null);
    }, "json");
}

function queryzhiweiMain() {
    $.post(
        '/page/demo/dic/commonWithCodition1?classId=10',
        null,
        function (data) {
            $('#zhiweiMainList ul').html("");
            var list = data.common_dictionary_list;
            for (var i = 0; i < list.length; i++) {
                var liInfo = '           <li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 1)
                    + '" href="javascript:void(0);">' + list[i].classPropertyName
                    + '</a>' + '         </li>';

                $('#zhiweiMainList ul').append(liInfo);
            }

            var zhiweiMainName = $("#zhiweiMainList").find(".placeholder").text();
            var zhiweiCode = $(
                '#zhiweiMainList ul > li:has(> a:contains('
                    + zhiweiMainName + '))').attr('value');

            if (zhiweiCode != null && zhiweiCode != '') {
                queryzhiweiSub(zhiweiCode);
            }
            new customDropDown($("#zhiweiMainList"), selectZhiweiMain);
        }, "json");
}

function queryzhiweiSub(classPropertyId) {
    $.post(
        '/page/demo/dic/commonWithCodition2?parentClassId='
            + classPropertyId,
        null,
        function (data) {
            $('#zhiweiSubList ul').html("");
            var list = data.common_dictionary_list;
            if (list == null) {
                $('#zhiweiSubList ul').append('<li><a href="javascript:void(0);">无</a></li>');
                return;
            }
            for (var i = 0; i < list.length; i++) {
                var liInfo = '           <li role="presentation" value="'
                    + list[i].classPropertyId + '">'
                    + '             <a role="menuitem" tabindex="' + (i + 1)
                    + '" href="javascript:void(0);">' + list[i].classPropertyName
                    + '</a>' + '         </li>';

                $('#zhiweiSubList ul').append(liInfo);
            }

            new customDropDown($("#zhiweiSubList"), null);
        }, "json");
}


function enableMultiSelect(multi, btn) {
    multi.multiselect();
    btn.on('click', function () {
        multi.find('option:selected').each(function () {
            $(this).prop('selected', false);
        })
        multi.multiselect('refresh');
    });
}

function setMultiSelect(multi, value) {
    if (value != null && value != '') {
        var selectList = value.split(',');
        for (var pos in selectList) {
            multi.find('option:contains(' + selectList[pos].trim() + ')').each(function () {
                $(this).prop('selected', true);
            })
        }
    } else {
        multi.find('option:selected').each(function () {
            $(this).prop('selected', false);
        })
    }
    multi.multiselect('refresh');
}

function getMultiSelect(multi) {
    return multi.next().find("button").attr("title");
}

function queryMultiJob() {
    $.post("/page/demo/dic/common?classId=10", null, function (data) {
        var list = data.common_dictionary_list;

        var rootNode = $("#expectjobcondition");
        var other = "";
        rootNode.html("");
        var item = "";
        if (list != null) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].haveSubClassProperty == 1) {
                    item = " <div class=\"conditionitem clearfix\">"
                        + "<div class=\"row\">"
                        + "<div class=\"col-md-2\">"
                        + "<li class=\"woo-class-title\" data=\""
                        + list[i].classPropertyId
                        + "\">"
                        + list[i].classPropertyName
                        + ":</li>"
                        + "</div>";
                    $.ajax({
                        url: '/page/demo/dic/commonWithCodition2?parentClassId='
                            + list[i].classPropertyId,
                        async: false,
                        dataType: 'json',
                        success: function (data) {
                            item += " <div class=\"col-md-10\">";
                            var list = data.common_dictionary_list;
                            for (var i = 0; i < list.length; ++i) {
                                item += "<li><a href=\"javascript:;\" onclick=\"addselect($('#selectexpectjobconditon'), this)\" data=\"" +
                                    list[i].classPropertyId +
                                    "\">" +
                                    list[i].classPropertyName +
                                    "</a></li>";
                            }
                            rootNode.append(item + "</div> </div> </div>");
                            item = "";
                        }
                    });
                } else {
                    if (list[i].parentClassPropertyId == undefined) {
                        other += "<li><a href=\"javascript:;\" onclick=\"addselect($('#selectexpectjobconditon'), this)\" data=\"" +
                            list[i].classPropertyId +
                            "\">" +
                            list[i].classPropertyName +
                            "</a></li>";
                    }
                }
            }
            rootNode.append(" <div class=\"conditionitem clearfix\">"
                + "<div class=\"row\">"
                + "<div class=\"col-md-2\">"
                + "<li class=\"woo-class-title\""
                + ">"
                + "其他:</li>"
                + "</div>"
                + "<div class=\"col-md-10\">" + other + "</div> </div> </div>");
        }
    }, "json");
}

function addselect(selectcondition, btn) {
    var bo = false;
    var text = $(btn).text();
    $(btn).css("color", "#a1a1a1");
    selectcondition.find("a").each(function () {
        if (text == $(this).text()) {
            bo = true;
        }
    });
    if (!bo) {
        selectcondition.append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
            + 'onclick="removeselect(this)">'
            + text
            + '</a> </li>');
    }
}

function removeselect(ele) {
    $(ele).parent().remove();
}

function queryMultiCity() {
    $.post("/page/demo/dic/cityList", null, function (data) {
        var list = data.region_list;

        var rootNode = $("#expectplacecondition");
        rootNode.html("");
        if (list != null) {

            for (var i = 0; i < list.length; i++) {
                if (list[i].regionCode % 10000 == 0) {
                    rootNode.append(" <div class=\"conditionitem clearfix\">"
                        + "<div class=\"row\">"
                        + "<div class=\"col-md-2\">"
                        + "<li class=\"woo-class-title\" data=\""
                        + list[i].regionCode / 10000
                        + "\">"
                        + list[i].regionName
                        + ":</li>"
                        + "</div><div class=\"col-md-10\"></div></div></div>");
                } else {
                    var tem = rootNode.find("li[data=" + parseInt(list[i].regionCode / 10000) + "]");
                    tem.parent().next().append(
                        " <li><a href=\"javascript:;\" onclick=\"addselect($('#selectexpectplaceconditon'), this)\" data=\"" +
                            list[i].regionCode +
                            "\">" +
                            list[i].regionName +
                            "</a></li>");
                }
            }
        }
    }, "json");
}

function queryMultiSpecialty() {
    $.post("/page/demo/dic/common?classId=20", null, function (data) {
        var list = data.common_dictionary_list;

        var rootNode = $("#specialitycondition");
        rootNode.html("");
        if (list != null) {
            for (var i = 0; i < list.length; i++) {
                var tem = rootNode.find("li[data=" + list[i].classTitleName + "]");

                if (tem.length == 0) {
                    rootNode.append(" <div class=\"conditionitem clearfix\">"
                        + "<div class=\"row\">"
                        + "<div class=\"col-md-2\">"
                        + "<li class=\"woo-class-title\" data=\""
                        + list[i].classTitleName
                        + "\">"
                        + list[i].classTitleName
                        + ":</li>"
                        + "</div><div class=\"col-md-10\"></div></div></div>");
                    tem = rootNode.find("li[data=" + list[i].classTitleName + "]");
                }

                tem.parent().next().append(
                    " <li><a href=\"javascript:;\" onclick=\"addselect($('#selectspecialityconditon'), this)\" data=\"" +
                        list[i].classPropertyName +
                        "\">" +
                        list[i].classPropertyName +
                        "</a></li>");
            }
        }
    }, "json");
}

function queryEmployerjob(pageIndex, employerId) {
    var url = '/page/demo/job/employerjob?employerId=' + employerId;
    $.post(url, null, function (data) {
        var list = data.job_list;

        $('#jobList').html('<div class="font20 fontem"><label class="clr440062 marginl4r4"><i class="nameplate verticalmiddle"></i>' +
            '<span class="verticalmiddle">&nbsp;&nbsp;招聘职位</span></label></div>' +
            '<div class="clra6a6a6 floatright paddingl4r4 marginb10">该公司有 ' +
            '<span class="font20 fontem clrffa201 italic">' +
            list.length +
            '</span>' +
            ' 个职位正在发布</div>');

        for (var i = 0; i < list.length; i++) {
            var publishTime = list[i].publishTime;

            var intervaldays = dayInterval(newDateAndTime(publishTime), new Date());
            var workPattern = '';
            if (list[i].workPattern != null) {
                workPattern = list[i].workPattern;
            }

            var salary = salaryVal(list[i].salaryBegin, list[i].salaryEnd);
            var intervalStr = intervaldays == 0 ? "今天" : intervaldays + '天前发布';
            var liInfo =
                '    <div class="sizemh74 clrbfff clearboth margin10">'
                    + '<div class="row">'
                    + '<div class="col-md-1 col-xs-1">'
                    + '        <label class="sizeh20 marginb0 clrb440062 sizemh74 padding0">&nbsp;</label></div>'
                    + '        <div class="col-md-8 col-xs-7">'
                    + '            <div class="margint15"><a class="clrffa201 font15 fontem" href="/jsp/job/job_detail_r.jsp?jobId=' + list[i].jobId + '">' + list[i].jobName + '</a> <span class="clra6a6a6"> ['
                    + list[i].city
                    + ' ]</span></div>'
                    + '<div class="paddingt10 paddingb10">' + list[i].eduReq + ' / ' + workPattern + ' / ' + '月薪&nbsp;' + salary
                    + '</div>'
                    + '        </div>'
                    + '        <div class="col-md-3  col-xs-3 fontem paddingt10 text-right paddingr25">' + intervalStr + '</li>'
                    + '        </div>'
                    + '</div>'
                    + '    </div>';
            $('#jobList').append(liInfo);
        }
    }, "json");
}

function queryHRRatio(employerId, item) {
    $.post('/page/demo/job/interact/statichr?employerId=' + employerId, null, function (data) {
//统计一个月内的HR处理简历用时
        var dateTotal = 0;
        if (data.static_hr != null) {
            for (var i = 0; i < data.static_hr.length; ++i) {
                dateTotal += dayInterval(newDateAndTime(data.static_hr[i].deliverTime), newDateAndTime(data.static_hr[i].watchTime));
            }
            if (data.static_hr.length > 0) {
                var ratioItem = $(item).find(".hrratio");
                ratioItem.removeClass("clra6a6a6").removeClass("font20").addClass("clrb5d95f").addClass("font45");
                ratioItem.html(parseInt(dateTotal / data.static_hr.length) + "<span class=\"font15 clra6a6a6\">天</span>");
            }
//            else {
//                $(item).remove();
//            }
        }

    }, "json");
}

function queryEmployer(employerId) {
    var url = '/page/demo/employer/employerdetail?employerId=' + employerId;
    $.post(url, null, function (data) {
        var value = data.employer_detail;

        if (value == null) {
            $('div.row').remove();
        } else {
            $('#inputTel').attr("value", value.tel);
            $('#logoPreview').attr("src", regular(value.logoPath));
            $('#logoPreview').attr("alt", regular(value.employerName));
            $('#inputemployerDesc').html(value.employerDesc);
            $("#inputEmployer").text(value.employerName);
            if (regular(value.website) == '') {
                $("#webSiteId").css("display", "none");
            } else {
                if (value.website.indexOf("http") < 0 && value.website.indexOf("https:") < 0) {
                    value.website = "http://" + value.website;
                }
                $("#webSiteId").attr("href", value.website);
            }
            $("#saleTypeId").text(value.saleType);
            $("#scaleId").text(value.employerScale);
            $("#placeId").html(value.province + '&nbsp;&nbsp;' + value.city);
            $("#placeDetailId").text(value.address);

            var url = '/page/demo/employer/store/read?employerId='
                + employerId;
            $.post(url, null, function (data) {
                var value = data.employer_store;

                if (value != null) {
                    $("#webSiteId").text(value.taobaoStoreName);
                    $("#taobaoLevelId").text('  ' + value.taobaoLevel);
                }
            }, "json");

            $('#tagList').html('');
            var tagbefore = '<li class="marginb10"><a class="tag  sizew120 verticalmiddle">';
            var tagafter = '</a></li>'
            if (value.tag1 != null && value.tag1 != '') {
                $('#tagList').append(tagbefore + value.tag1 + tagafter);
            }
            if (value.tag2 != null && value.tag2 != '') {
                $('#tagList').append(tagbefore + value.tag2 + tagafter);
            }
            if (value.tag3 != null && value.tag3 != '') {
                $('#tagList').append(tagbefore + value.tag3 + tagafter);
            }
            if (value.tag4 != null && value.tag4 != '') {
                $('#tagList').append(tagbefore + value.tag4 + tagafter);
            }
            if (value.tag5 != null && value.tag5 != '') {
                $('#tagList').append(tagbefore + value.tag5 + tagafter);
            }

            if (regular(value.product1Pic) != "" || regular(value.product2Pic) || regular(value.product3Pic) || regular(value.product4Pic) || regular(value.product5Pic)) {
                $("#employerdetail").before(' <div id="Carousel" class="carousel slide margint20" style="width: 100%;" data-ride="carousel">' +
                    '<ol class="carousel-indicators">' +
                    '</ol>' +
                    '<div class="carousel-inner" role="listbox">' +
                    '</div></div>');
                var indicator = 0;
                if (value.product1Pic != null && value.product1Pic != '') {
                    $('.carousel-indicators').append('<li data-target="#Carousel" data-slide-to="' +
                        indicator +
                        '" class="active"></li>');
                    $('.carousel-inner').append('<div class="item active">' +
                        '<img style="width: 682px; height: 300px;" src="' +
                        value.product1Pic +
                        '" alt="...">' +
                        '</div>');
                    indicator++;
                }

                if (value.product2Pic != null && value.product2Pic != '') {
                    $('.carousel-indicators').append('<li data-target="#Carousel" data-slide-to="' +
                        indicator +
                        '"></li>');
                    $('.carousel-inner').append('<div class="item">' +
                        '<img style="width: 682px; height: 300px;" src="' +
                        value.product2Pic +
                        '" alt="...">' +
                        '</div>');
                    indicator++;
                }
                if (value.product3Pic != null && value.product3Pic != '') {
                    $('.carousel-indicators').append('<li data-target="#Carousel" data-slide-to="' +
                        indicator +
                        '"></li>');
                    $('.carousel-inner').append('<div class="item">' +
                        '<img style="width: 682px; height: 300px;" src="' +
                        value.product3Pic +
                        '" alt="...">' +
                        '</div>');
                    indicator++;
                }
                if (value.product4Pic != null && value.product4Pic != '') {
                    $('.carousel-indicators').append('<li data-target="#Carousel" data-slide-to="' +
                        indicator +
                        '"></li>');
                    $('.carousel-inner').append('<div class="item">' +
                        '<img style="width: 682px; height: 300px;" src="' +
                        value.product4Pic +
                        '" alt="...">' +
                        '</div>');
                    indicator++;
                }
                if (value.product5Pic != null && value.product5Pic != '') {
                    $('.carousel-indicators').append('<li data-target="#Carousel" data-slide-to="' +
                        indicator +
                        '"></li>');
                    $('.carousel-inner').append('<div class="item">' +
                        '<img style="width: 682px; height: 300px;" src="' +
                        value.product5Pic +
                        '" alt="...">' +
                        '</div>');
                    indicator++;
                }
//                setSlide();
                $('.carousel').carousel({
                    interval: 6000,
                    wrap: false
                });
            }
        }
    }, "json");
}

function querysingleEmployerHot(employerId) {
    $.post('/page/demo/employer/singlehot?employerId=' + employerId, null, function (data) {
        var employerHot = data.employer_hot;
        var datereadTimesArray = new Array(employerHot.day1times, employerHot.day2times, employerHot.day3times, employerHot.day4times, employerHot.day5times, employerHot.day6times, employerHot.day7times);
        var newDateReadTimesArray = new Array();
        var totalTimes = 0;
        for (var i = 0; i < dateArray.length; ++i) {
            newDateReadTimesArray[i] = datereadTimesArray[(i + currentDay) % 7];
            totalTimes += newDateReadTimesArray[i];
        }
        $("#total").text(totalTimes);
        var lineChartData = {
            labels: newDateArray,
            datasets: [
                {
                    label: "My First dataset",
                    fillColor: "rgba(255,162,1,0.2)",
                    strokeColor: "rgba(255,162,1,1)",
                    pointColor: "rgba(255,162,1,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(255,162,1,1)",
                    data: newDateReadTimesArray
                }
            ]
        }

        var ctx = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(ctx).Line(lineChartData, {
            responsive: true
        });
    }, "json");
}