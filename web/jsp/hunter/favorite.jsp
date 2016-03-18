<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>我的收藏</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="col-sm-8">
        <h4 class="paddingb10 bottomdashedborder">
            <div>收藏的职位</div>
        </h4>
        <ul id="tableList" class="list-group woo-bigger-margin-top">

        </ul>
        <ul class="pagination " id="pagination">
        </ul>
    </div>
    <%@ include file="/jsp/hunter/hunter_nav.jsp" %>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#favoritepage").addClass("active");
        query(1);
    });

    function diliver(btn, jobId, jobHunterId, employerId) {
        var param_data = {
            jobId: jobId,
            jobHunterId: jobHunterId,
            employerId: employerId
        }
        $(btn).text("已投递");
        $(btn).attr("disabled", true);
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            url: "/page/demo/job/applyjob",
            success: function (data) {

            }
        });
    }

    function removeModel(btn, myFavoriteJobId) {
        $("body").removeClass("modal-open").css("padding-right", "inherit");
        $(btn).parents(".list-group-item").remove();
        var param_data = {
            myFavoriteJobId: myFavoriteJobId
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            url: "/page/demo/job/removefavoritejob",
            success: function (data) {

            }
        });
    }

    function query(pageIndex) {
        var url = '/page/demo/job/hunterfavoritejob' + getData(pageIndex) + '&jobHunterId='
                + escape('${param.jobHunterId}');
        $.post(
                url,
                null,
                function (data) {
                    if (data.retCode == "fail") {
                        $('#tableList').html("");
                        $('#tableList').append('<div class="solidborder1 woo-bigger-margin-top woo-label-padding">' +
                                '<h4 class="woo-horizontal-middle" style="text-align: center;">您还没有收藏任何职位！</h4>' +
                                '</div>');
                        return;
                    }
                    var list = data.job_list;
                    var favoriteList = data.favorite_list;
                    $('#tableList').html("");
                    for (var i = 0; i < list.length; i++) {
                        var logoPath = "";
                        if (list[i].logoPath == undefined || list[i].logoPath == "") {
                            logoPath = "/img/companydefault.png";
                        } else {
                            logoPath = list[i].logoPath;
                        }
                        var updateTime = '';
                        if (list[i].updateTime == undefined || list[i].updateTime == "") {

                        } else {
                            updateTime = list[i].updateTime;
                            updateTime = formatDateTime(updateTime);
                        }

                        var timeIdentify = getTimeIdentity();

                        var salary = salaryVal(list[i].salaryBegin, list[i].salaryEnd);

                        var liInfo = '<li class="list-group-item">'
                                + '<div class="woo-job-list-item">'
                                + '<div class="row">'
                                + '<div class="col-md-2">'
                                + '<a class="media-left media-middle" href="/jsp/job/job_detail_r.jsp?jobId=' +
                                list[i].jobId +
                                '" target="_blank">' +
                                '<img class="sizew80" src="' +
                                logoPath +
                                '" >' +
                                '</a>' +
                                '</div>' +
                                '<div class="col-md-6">' +
                                '<ul class="list-inline">' +
                                '<div class="col-md-8">' +
                                '<li class="woo-job-name"><a href="/jsp/job/job_detail_r.jsp?jobId=' +
                                list[i].jobId +
                                '" target="_blank">' +
                                '<h4>' +
                                showInPage(list[i].jobName) +
                                '</h4>' +
                                '</a></li>' +
                                '</div>' +
                                '<div class="col-md-4">' +
                                '<li class="woo-work-place">' +
                                showInPage(list[i].city) +
                                '</li>' +
                                '</div></ul>' +
                                '<ul class="list-inline">' +
                                '<div class="col-md-8">' +
                                '<li class="woo-company-name">' +
                                showInPage(list[i].employerName) +
                                '</li>' +
                                '</div>' +
                                '<div class="col-md-4">' +
                                '<li class="woo-update-time">' +
                                updateTime +
                                '</li>' +
                                '</div>' +
                                '</ul>' +
                                '</div>' +
                                '<div class="col-md-3">' +
                                '<ul class="list-unstyled">' +
                                '<li class="woo-salary">月薪&nbsp;<strong>' +
                                salary +
                                '</strong></li>' +
                                '<li class="woo-worktype">' +
                                showInPage(list[i].workPattern) +
                                '</li>' +
                                '</ul></div>' +
                                '<div class="col-md-1">' +
                                '<button style="background: transparent;border: ' +
                                'transparent" data-toggle="modal"' +
                                'data-target="#deleteModal' +
                                timeIdentify +
                                '">' +
                                '<span class="fa fa-remove"></span></button>' +
                                '<div class="modal fade" id="deleteModal' +
                                timeIdentify +
                                '" tabindex="-1" ' +
                                'role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
                                '<div class="modal-dialog">' +
                                '<div class="modal-content">' +
                                '<div class="modal-header">' +
                                '<button type="button" class="close" data-dismiss="modal">' +
                                '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
                                '</button>' +
                                '<h4 class="modal-title fontem">删除</h4>' +
                                '</div>' +
                                '<div class="modal-body fontem">' +
                                '你确定删除此条申请记录吗？' +
                                '</div>' +
                                '<div class="modal-footer">' +
                                '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
                                '</button>' +
                                '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeModel(this,' +
                                favoriteList[i].myFavoriteJobId +
                                ')">确定' +
                                '</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</li>';

                        $('#tableList').append(liInfo);
                    }
                    $('#pagination').html(data.paginationInfo);
                }, "json");
    }

    //组装查询条件
    function getData(pageIndex) {
        var data = '?pageIndex=' + pageIndex;
        //增加查询条件
        return data;
    }
    function showInPage(value) {
        if (value == undefined) {
            return "";
        }
        return value;
    }
</script>
</body>
</html>