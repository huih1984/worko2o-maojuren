<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/jsp/comm/header.jsp" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>公司搜索</title>
</head>

<body onload="onload();">
<audio id="chatAudio">
    <source src="/sound/notify.mp3" type="audio/mpeg">
    <source src="/sound/notify.ogg" type="audio/ogg">
    <source src="/sound/notify.wav" type="audio/wav">
</audio>
<div class="container  margint64">
    <div class="col-md-8">
        <div class="search">
            <div class="input-group">
                <input id="searchName" type="text" class="form-control borderw0" placeholder=""/>
              <span class="input-group-btn">
              <button id="searchconfirm" class="btn btn-primary padding0" type="button"
                      onclick="search()">
                  <span class="fa fa-search"></span>
              </button>
                  <button id="searchallconfirm" class="btn btn-primary padding0 font20" style="height: 38px;"
                          type="button"
                          onclick="searchall()">
                      <span class="fa fa-search"></span>
                  </button>
              </span>
            </div>
        </div>
        <div>
            <div>
                <div class="margint20">
                    <div class="solidborder1">
                        <label class="fontem">&nbsp;&nbsp;<i
                                class="fa fa-list woo-main-theme"></i>&nbsp;&nbsp;未审核公司列表</label>
                    </div>
                    <ul id="listGroup" class="list-group">
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <input class="margint64" type="text" id="messageid" value=""/>
    </div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {
        setEmployerList();
    });

    function approve(btn, id) {
        $.post("/page/demo/admin/approve?employerId=" + id);
        $(btn).text("已批准");
        $(btn).attr("disabled", true);
    }

    function search() {
        setEmployerList($("#keyId").val());
    }

    function searchall() {
        setAllEmployerList($("#keyId").val());
    }

    function setAllEmployerList(employerName) {
        var url;
        if (employerName != undefined) {
            url = '/page/demo/admin/get_approve?employerName=' + employerName;
        } else {
            url = '/page/demo/admin/get_approve';
        }
        $.ajax({
            url: url,
            dataType: "json",
            success: function (data) {
                var employerList = data.employer_detail;
                $("#listGroup").html("");
                for (var i = 0; i < employerList.length; ++i) {
                    $("#listGroup").append(
                            '<li class="list-group-item">' +
                                    '<div class="woolistitem">' +
                                    '<div class="row">' +
                                    '<div class="col-md-2">' +
                                    '<a class="media-left media-middle" href="' +
                                    employerList[i].certificatePath +
                                    '">' +
                                    '<img class="sizew80" src="' +
                                    employerList[i].certificatePath +
                                    '">' +
                                    '</a>' +
                                    '</div>' +
                                    '<div class="col-md-6">' +
                                    '<ul class="list-inline">' +
                                    '<li>' +
                                    employerList[i].employerName +
                                    '</li>' +
                                    '<li>' +
                                    employerList[i].tel +
                                    '</li>' +
                                    '</ul>' +
                                    '</div>' +
                                    '<div class="col-md-4">' +
                                    '<div class="woo-invite-button">' +
                                    '<button class="btn btn-primary floatright" onclick="approve(this,' +
                                    employerList[i].employerId +
                                    ')">批准' +
                                    '</button>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</li>')
                }
            }
        });
    }

    function setEmployerList(employerName) {
        var url;
        if (employerName != undefined) {
            url = '/page/demo/admin/get_not_approve?employerName=' + employerName;
        } else {
            url = '/page/demo/admin/get_not_approve';
        }
        $.ajax({
            url: url,
            dataType: "json",
            success: function (data) {
                var employerList = data.employer_detail;
                $("#listGroup").html("");
                for (var i = 0; i < employerList.length; ++i) {
                    $("#listGroup").append(
                            '<li class="list-group-item">' +
                                    '<div class="woolistitem">' +
                                    '<div class="row">' +
                                    '<div class="col-md-2">' +
                                    '<a class="media-left media-middle" href="' +
                                    employerList[i].certificatePath +
                                    '">' +
                                    '<img class="sizew80" src="' +
                                    employerList[i].certificatePath +
                                    '">' +
                                    '</a>' +
                                    '</div>' +
                                    '<div class="col-md-6">' +
                                    '<ul class="list-inline">' +
                                    '<li>' +
                                    employerList[i].employerName +
                                    '</li>' +
                                    '<li>' +
                                    employerList[i].tel +
                                    '</li>' +
                                    '</ul>' +
                                    '</div>' +
                                    '<div class="col-md-4">' +
                                    '<div class="woo-invite-button">' +
                                    '<button class="btn btn-primary floatright" onclick="approve(this,' +
                                    employerList[i].employerId +
                                    ')">批准' +
                                    '</button>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</li>')
                }
            }
        });
    }
</script>
<script type='text/javascript' src='../../dwr/engine.js'></script>
<script type='text/javascript' src='../../dwr/util.js'></script>
<script type='text/javascript' src='../../dwr/interface/DwrMsg.js'></script>
<script>

    function onload() {
        dwr.engine.setActiveReverseAjax(true);
    }

    function show(message) {
        $('#chatAudio')[0].play(); //播放声音
        document.getElementById('messageid').value = message;
    }

</script>
</body>
</html>