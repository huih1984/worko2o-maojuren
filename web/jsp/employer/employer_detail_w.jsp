<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- saved from url=(0036)http://v3.bootcss.com/examples/woo/ -->
<html lang="zh-CN">
<head>
    <title>完善资料</title>
    <%@ include file="/jsp/comm/header.jsp" %>

<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main  margint64 marginb64">
<div class="col-md-8">
<div class="form-horizontal" role="form">
<div class="paddingl10 clrbedeaf4">
    <div class="row">
        <div class="col-md-offset-10 col-md-2 paddingt10">
            <button class="btn btn-primary floatright marginr10 backtransparent bordertransparent"
                    onclick="javascript:window.location.href='employer_detail_r.jsp?employerId=${param.employerId}&employerName=${param.employerName}'">
                <span class="font20 fa fa-eye"></span>
            </button>
        </div>
    </div>
    <div class="row margint10">
        <div class="col-md-3">
            <div class="halftransparent" style="position:relative;">
                <img id="logoPreview" class="sizew150 sizeh150"
                     src="/img/companydefault.png">
                <input type="file" id="logoPath" accept="image/*"
                       class="sizew150 sizeh150 transparent pointer"
                       style="position: absolute;z-index: 2; top: 0px;">
            </div>
        </div>

        <div class="col-md-9">
            <div class="form-group">
                <div class="col-md-3 control-label">
                    <em class="clrred">*</em> <label>公司名称</label>
                </div>
                <div class="col-md-7">
                    <input id="inputEmployer" class="woo-check-input form-control"
                           value="" required maxlength=40>

                    <div class="woo-error">请填写公司名称</div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-3 control-label">
                    <em class="clrred">*</em> <label>联系电话</label>
                </div>
                <div class="col-md-7">
                    <input id='inputTel' class="form-control woo-check-input" placeholder="请输入联系电话" required=""
                           pattern="[0-9]{7,13}">

                    <div class="woo-error">请输入联系电话</div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-3 control-label">
                    <em class="clrred">*</em> <label>公司所在地</label>
                </div>
                <div class="col-md-9">
                    <div id="provinceList" class="btn-group">
                        <button type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                                aria-expanded="false">
                            <span class="placeholder">  选择省份 </span>

                            <div class="padding0 textleft verticalmiddle"><span
                                    class="imageback downarrow size13"></span></div>
                        </button>
                        <ul class="dropdown-menu sizemh260 scroll" role="menu" aria-labelledby="dropdownMenu1">
                        </ul>
                        <div class="woo-error">请选择省份</div>
                    </div>
                    <div id="cityList" class="btn-group">
                        <button type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                                aria-expanded="false"><span class="placeholder"> 选择城市</span>

                            <div class="padding0 textleft verticalmiddle"><span
                                    class="imageback downarrow size13"></span></div>
                        </button>
                        <ul class="dropdown-menu sizemh260 scroll" role="menu">
                        </ul>
                        <div class="woo-error">请选择城市</div>
                    </div>
                    <div id="areaList" class="btn-group">
                        <button type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                                aria-expanded="false">
                            <span class="placeholder">选择地区</span>

                            <div class="padding0 textleft verticalmiddle"><span
                                    class="imageback downarrow size13"></span></div>
                        </button>
                        <ul class="dropdown-menu sizemh260 scroll" role="menu">
                        </ul>
                        <div class="woo-error">请选择地区/县</div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="inputAddress" class="col-md-3 control-label"><em
                        class="clrred">*</em>通讯地址</label>

                <div class="col-sm-9">
                    <input id="inputAddress" class="woo-check-input form-control woo-input-max-length"
                           placeholder="" maxlength=100 value="" required="">

                    <div class="woo-error">请填入公司地址</div>
                    <label class="woo-prompt">请填入公司详细通讯地址</label>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"><em
                        class="clrred">*</em>公司规模</label>

                <div class="col-sm-9">
                    <div id="scaleList" class="btn-group woo-btn-dropdown">
                        <button type="button" class="woo-check-drop-down btn btn-primary dropdown-toggle"
                                data-toggle="dropdown" aria-expanded="false">
                            <span class="placeholder">选择公司规模</span><span class="imageback downarrow size13"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                        </ul>
                    </div>
                    <div class="woo-error">请选择公司规模</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">卖品类别</label>

                <div class="col-sm-9">
                    <div id="saleTypeList" class="btn-group woo-btn-dropdown">
                        <button type="button" class="btn dropdown-toggle" data-toggle="dropdown"
                                aria-expanded="false">
                            <span class="placeholder">选择卖品类别</span><span class="imageback downarrow size13"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                        </ul>
                    </div>
                    <div class="woo-error">请选择卖品类别</div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="form-group paddingt10">
        <label for="inputStore" class="col-sm-2 control-label">店铺名称</label>

        <div class="col-md-6">
            <input id="inputStore" class="form-control woo-input-max-length"
                   placeholder="" maxlength="45" value="" required="">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">店铺信誉</label>

        <div class="col-md-6">
            <div id="creditList" class="btn-group woo-btn-dropdown">
                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <span class="placeholder">选择店铺信誉</span><span class="imageback downarrow size13"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                </ul>
            </div>
        </div>
    </div>


    <div class="form-group">
        <label for="inputwebSite" class="col-sm-2 control-label">店铺网址</label>

        <div class="col-md-6">
            <input id="inputwebSite" class="form-control woo-input-max-length"
                   placeholder="" maxlength="45" value="" required="">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">公司标签</label>

        <div id="tagList" class="col-md-10">
            <label class="woo-prompt" type="button" class="btn clra6a6a6 woo-big-padding-left"
                   data-toggle="popover" title=""
                   data-content="做一休一 周末双休 带薪年假 五险一金 年终双薪 奖金丰厚 绩效奖金 年终分红 股票期权 全勤奖 人才推荐奖 加班补助 交通补助 出差补贴 包吃包住 包住宿 包三餐 房补 话补 采暖补贴 高温补贴 节日福利 专业培训 立即上岗 提供班车 弹性工作 补充医疗保险 定期体检 员工旅游 出国机会"
                   data-original-title="示例">
                最多可以添加5个标签,每个标签字数不多于6个</label>

            <div class="margint10 padding0 col-md-6">
                <button id="tagPlusBtn" class="btn plus verticalmiddle"></button>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="inputwebSite" class="col-sm-2 control-label">公司图片</label>

        <div id="photoList" class="col-md-10">
            <label class="woo-prompt">最多可以添加5张图片，可以是公司办公环境、员工合照，也可以是产品图片。请上传400X200尺寸，大小不超过2M图片</label>
            <button id="photoPlusBtn" class="btn plus verticalmiddle"></button>
        </div>
    </div>
</div>

<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="form-group paddingt10">
        <label class="col-sm-2 control-label">公司简介</label>

        <div class="col-md-10">
            <textarea id="inputemployerDesc" maxlength="1000" placeholder="请分段详细描述公司简介、企业文化等"
                      name="companyProfile" class="valid"
                      style="min-height: 240px; width: 100%"></textarea>

            <div class="word_count">
                你还可以输入 <span>1000</span> 字
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="form-group paddingt10">
        <label class="col-sm-2 control-label">地图标注</label>

        <div class="col-md-10">
            <input type="hidden" value="" name="positionLng" id="lng">
            <input type="hidden" value="" name="positionLat" id="lat">
            <button id="mapBtnId" class="btn marginr30 sizew150 clrb601986 clredeaf4 fontem" data-toggle="modal"
                    data-target="#mapModal">选择公司地图位置
            </button>
            <div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" style="width: 640px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title fontem">公司位置</h4>
                        </div>
                        <div class="modal-body fontem">
                            <div id="baiduMap">
                                <div class="marginb10">点击地图可重新定位公司所在的位置</div>
                                <div id="allmap"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                            </button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="chooseMap()">确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-2 col-md-offset-10">
        <button class="btn btn-primary margint20" type="submit">保 &nbsp;&nbsp; 存
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
    queryEmployerDetail();

    $("#tagPlusBtn").click(function () {
        plusTag("");
    });

    $("#photoPlusBtn").click(function () {
        plusPhoto("");
    });

    $("button[type='submit']").click(function () {
        submit();
    });

    $("#inputemployerDesc").bind('input propertychange', function () {
        checkLength(this, 1000);
        $(this).parent().find("div span").text((1000 - this.value.length));
    });

    hideErrors();
    $("#logoPath").change(function () {
        checkFile(this, 500, document.getElementById("logoPreview"), 150, 150, "");
    });
});

function queryEmployerDetail() {
    var url = '/page/demo/employer/employerdetail?employerId='
            + escape('${param.employerId}');
    $.post(url, null, function (data) {
        var value = data.employer_detail;

        if (value == null) {
            $('.woo-main-body').remove();
        } else {
            if (value.employerName != null && value.employerName != '') {
                $('#inputEmployer').attr("value", value.employerName);
            }
            if (value.tel != null && value.tel != '') {
                $('#inputTel').attr("value", value.tel);
            }
            if (value.logoPath != null && value.logoPath != '') {
                $('#logoPreview').attr("src", "" + value.logoPath);
            }
            if (value.address != null && value.address != '') {
                $('#inputAddress').attr("value", value.address);
            }

            var url = '/page/demo/employer/store/read?employerId='
                    + escape('${param.employerId}');
            $.post(url, null, function (data) {
                var value = data.employer_store;

                if (value != null) {
                    if (value.taobaoStoreName != null && value.taobaoStoreName != '') {
                        $('#inputStore').attr("value", value.taobaoStoreName);
                    }

                    if (value.taobaoLevel != null && value.taobaoLevel != '') {
                        $("#creditList").find(".placeholder").text(
                                value.taobaoLevel);
                    }
                }
                queryCreditList();
            }, "json");
            if (value.website != null && value.website != '') {
                $('#inputwebSite').attr("value", value.website);
            }
            if (value.employerDesc != null && value.employerDesc != '') {
                $('#inputemployerDesc').val(value.employerDesc);
                $('#inputemployerDesc').parent().find("div span").text((1000 - value.employerDesc.length));
            }

            if (value.province != null && value.province != '') {
                $("#provinceList").find(".placeholder")
                        .text(value.province);
            }
            if (value.city != null && value.city != '') {
                $("#cityList").find(".placeholder").text(value.city);
            }
            if (value.district != null && value.district != '') {
                $("#areaList").find(".placeholder").text(value.district);
            }
            queryProvince();

            if (value.employerScale != null && value.employerScale != '') {
                $("#scaleList").find(".placeholder").text(
                        value.employerScale);
            }
            queryScaleList();

            if (value.saleType != null && value.saleType != '') {
                $("#saleTypeList").find(".placeholder")
                        .text(value.saleType);
            }
            querySaleTypeList();

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

            if (value.product1Pic != null && value.product1Pic != '') {
                plusPhoto(value.product1Pic);
            }
            if (value.product2Pic != null && value.product2Pic != '') {
                plusPhoto(value.product2Pic);
            }
            if (value.product3Pic != null && value.product3Pic != '') {
                plusPhoto(value.product3Pic);
            }
            if (value.product4Pic != null && value.product4Pic != '') {
                plusPhoto(value.product4Pic);
            }
            if (value.product5Pic != null && value.product5Pic != '') {
                plusPhoto(value.product5Pic);
            }
            if (regular(value.lat) != '') {
                $('#lat').val(value.lat);
            }
            if (regular(value.lng) != '') {
                $('#lng').val(value.lng);
            }
        }
    }, "json");
}

function plusPhoto(url) {
    $("#photoPlusBtn")
            .before(
                    ' <div class="productpic" style="position:relative">'
                            + '          <img src="' + url + '" value="' + url + '" class="productpic">'
                            + '          <div><span class="circledel fa fa-remove"></span></div>'
                            + '      </div>'
                            + '           <input type=\"file\" accept=\"image/*\" class=\"margint10 marginb10\">');

    var newInputNode = $("#photoPlusBtn").prev();
    var newImgNode = newInputNode.prev();

    if ($("#photoList div img").size() >= 5) {
        $("#photoPlusBtn").hide();
    }

    newImgNode.children("div").click(function () {
        $(this).parent().next().remove();
        $(this).parent().remove();

        if ($("#photoList div img").size() < 5) {
            $("#photoPlusBtn").show();
        }
    });

    newInputNode.change(function () {
        checkFile(this, 2048, this.previousElementSibling.firstElementChild, 400, 200, "");
    });
}

function submitForm(jsonData) {
    jsonData.employerId = escape('${param.employerId}');
    jsonData.tel = $("#inputTel").val();
    jsonData.employerDesc = $('#inputemployerDesc').val();
    jsonData.address = $('#inputAddress').val();

    jsonData.province = $("#provinceList").find(".placeholder").text();
    jsonData.province = setNotSelectedValueToNull(jsonData.province);
    jsonData.city = $("#cityList").find(".placeholder").text();
    jsonData.city = setNotSelectedValueToNull(jsonData.city);
    jsonData.district = $("#areaList").find(".placeholder").text();
    jsonData.district = setNotSelectedValueToNull(jsonData.district);

    jsonData.employerScale = $("#scaleList").find(".placeholder").text();
    jsonData.employerScale = setNotSelectedValueToNull(jsonData.employerScale);
    jsonData.saleType = $("#saleTypeList").find(".placeholder").text();
    jsonData.saleType = setNotSelectedValueToNull(jsonData.saleType);
    jsonData.website = $('#inputwebSite').val();
    jsonData.lng = $('#lng').val();
    jsonData.lat = $('#lat').val();
    var urlList = new Array("", "", "", "", "");
    var tagList = $("#tagList div.woo-tags");
    for (var pos = 0; pos < tagList.length; pos++) {
        urlList[pos] = tagList.eq(pos).children("input").val();
    }
    jsonData.tag1 = urlList[0];
    jsonData.tag2 = urlList[1];
    jsonData.tag3 = urlList[2];
    jsonData.tag4 = urlList[3];
    jsonData.tag5 = urlList[4];

    $.ajax({
        type: "post",
        url: '/page/demo/employer/profile',
        data: jsonData,
        dataType: "json",
        success: function (msg) {
            if (msg.retMsg == "success") {
                $.simplyToast('保存成功!', 'success');
            } else {
                $.simplyToast('保存失败', 'danger');
            }
            var jsonData = new Object();
            jsonData.employerId = escape('${param.employerId}');
            jsonData.taobaoStoreName = $('#inputStore').val();
            jsonData.taobaoLevel = $("#creditList").find(".placeholder").text();
            if (jsonData.taobaoLevel == '选择店铺信誉') {
                jsonData.taobaoLevel = '';
            }

            $.ajax({
                type: "post",
                url: '/page/demo/employer/store/mod',
                data: jsonData,
                dataType: "json",
                success: function (msg) {
//                    alert(msg.retMsg);
                }
            }, "json");
        }
    }, "json");
}

function submitEmployePhoto(jsonData) {
    var files = new Array();
    var photoList = $("#photoList input");

    for (var pos = 0; pos < photoList.length; pos++) {
        if (photoList[pos].files != null && photoList[pos].files.length > 0) {
            files.push(photoList[pos].files[0]);
        }
    }
    ;

    if (files.length > 0) {
        uploadFiles(files, "/page/demo/upload", "employephoto", escape(${param.employerId}), function (msg) {

            var urlList = new Array("", "", "", "", "");
            var posMsg = 0;

            for (var pos = 0; pos < photoList.length; pos++) {
                var url = null;
                if (photoList[pos].files != null && photoList[pos].files.length > 0) {
                    var data = JSON.parse(msg[posMsg]);
                    urlList[pos] = data.file_path;

                    photoList[pos].outerHTML = photoList[pos].outerHTML;
                    photoList.eq(pos).prev().children("img").attr("value", data.file_path);

                    posMsg++;
                } else {
                    urlList[pos] = photoList.eq(pos).prev().children("img").attr("value");
                }
            }
            jsonData.product1Pic = urlList[0];
            jsonData.product2Pic = urlList[1];
            jsonData.product3Pic = urlList[2];
            jsonData.product4Pic = urlList[3];
            jsonData.product5Pic = urlList[4];
            submitForm(jsonData);
        });
    } else {
        var urlList = new Array("", "", "", "", "");
        for (var pos = 0; pos < photoList.length; pos++) {
            urlList[pos] = photoList.eq(pos).prev().children("img").attr("value");
        }

        jsonData.product1Pic = urlList[0];
        jsonData.product2Pic = urlList[1];
        jsonData.product3Pic = urlList[2];
        jsonData.product4Pic = urlList[3];
        jsonData.product5Pic = urlList[4];
        submitForm(jsonData);
    }
}

function submit() {
    if (!formValidCheck())return false;
    var logoPath = $("#logoPath");
    if (logoPath[0].files != null && logoPath[0].files.length > 0) {

        uploadFile(logoPath[0].files[0], "/page/demo/upload", "employerlogo", escape(${param.employerId}), 1, function (msg) {

            var data = JSON.parse(msg);

            logoPath[0].outerHTML = logoPath[0].outerHTML;
            $("#logoPreview").attr("value", data.file_path);

            var jsonData = new Object();
            jsonData.logoPath = data.file_path;
            submitEmployePhoto(jsonData);
        });
    } else {
        var jsonData = new Object();
        jsonData.logoPath = $("#logoPreview").attr("value");
        submitEmployePhoto(jsonData);
    }
}

function chooseMap() {
    if ($('#lat').val() == '' || $('#lng').val() == '') {
        $.simplyToast("您没有选择任何地点，地图上将不显示正确的公司地址！", "warning");
    }
}

function initialize() {

    var map = new BMap.Map("allmap");
    //控件添加
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.MapTypeControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());

    var point = new BMap.Point(116.331398, 39.897445);//初始化坐标点
    map.centerAndZoom(point, 15);
    /* map.centerAndZoom(, 15); */
    map.enableScrollWheelZoom(true);//允许缩放
    var gc = new BMap.Geocoder();//地址定位
    var local = new BMap.LocalSearch(map, {
        renderOptions: {map: map}
    });

    function showInfo(e) {
        map.clearOverlays();//清除所有覆盖物
        //map.centerAndZoom(new BMap.Point(olng, olat), 11);//重定义中心点
        //alert(e.point.lng + ", " + e.point.lat);
        var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注
        marker.addEventListener("mouseup", function (em) {//覆盖物监听事件--释放鼠标时定位覆盖物位置
            var pt = em.point;//移动后重新定位
            gc.getLocation(pt, function (rs) {
                var addComp = rs.addressComponents;
                var label = new BMap.Label("我的坐标：" + em.point.lng + ", " + em.point.lat + "  我的地址：" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber, {offset: new BMap.Size(20, -10)});
                // marker.setLabel(label);//新坐标-新地址
                if (rs) {
                    var sContent =
                            "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>" + addComp.province + "</h4>" +
                                    "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>" + rs.address + "</p>" +
                                    "</div>";
                    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
                    //图片加载完毕重绘infowindow
                    marker.openInfoWindow(infoWindow);
                }

                $('#lat').val(em.point.lat);
                $('#lng').val(em.point.lng);
            });
        });
        marker.enableDragging();    //可拖拽
        map.addOverlay(marker);     // 将标注添加到地图中

        /////////////////////地址定位
        var pt = e.point;
        gc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var label = new BMap.Label("我的坐标：" + e.point.lng + ", " + e.point.lat + "  我的地址：" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber, {offset: new BMap.Size(20, -10)});
            //marker.setLabel(label);
            if (rs) {
                var sContent =
                        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>" + addComp.province + "</h4>" +
                                "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>" + rs.address + "</p>" +
                                "</div>";
                var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
                //图片加载完毕重绘infowindow
                marker.openInfoWindow(infoWindow);
            }

            $('#lat').val(e.point.lat);
            $('#lng').val(e.point.lng);
        });

        //////////////////////重置中心点
//        olng = e.point.lng;
//        olat = e.point.lat;
    }

    map.addEventListener("click", showInfo);//地图点击事件

    $(function () {
        $('#mapBtnId').bind('click', function () {
//        $.colorbox({inline:true, href:"#baiduMap",title:"公司地址"});
            var address = $('#inputAddress').val();
            var city = $('#cityList .placeholder').text();
            map.setCurrentCity(city);
            map.setZoom(15);
            gc.getPoint(address, function (point) {
                if (point) {
                    map.centerAndZoom(point, 15);
                    map.setZoom(15);
                    map.setCenter(point);
                    local.search(address);
                }
            }, city);
            /* map.addEventListener("tilesloaded",function(){
             map.setZoom(15);
             }); */
        });
    });
}

function loadScript() {
    var script = document.createElement("script");
    script.src = "http://api.map.baidu.com/api?v=2.0&ak=k0t9RN7hjcLKmDKMZarEc9k0&callback=initialize";//此为v1.5版本的引用方式
    // http://api.map.baidu.com/api?v=1.5&ak=您的密钥&callback=initialize"; //此为v1.4版本及以前版本的引用方式
    document.body.appendChild(script);
}

window.onload = loadScript;

$(window).on('beforeunload', function (event) {
    var message = '离开前请确认所有数据都已保存';
    event.returnValue = message;
    return message;
});
</script>
<script>
    $('[data-toggle="popover"]').popover({});
</script>
</body>
</html>