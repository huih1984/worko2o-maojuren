<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>收藏的简历</title>
    <%@ include file="/jsp/comm/header.jsp" %>

</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="col-md-8">
        <h4 class="paddingb10 bottomdashedborder">
            <div>收藏的简历</div>
        </h4>
        <ul id="tableList" class="list-group woo-bigger-margin-top">

        </ul>
    </div>
    <%@ include file="employer_nav.jsp" %>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#favoritepage").addClass("active");
        queryallMyJobs(escape(${param.employerId}));
        queryFavoriteResume();
    });
    var employerFavoriteList = null;
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

    function removeEmployerFavoriteResumeModel(btn, i) {
        $("body").removeClass("modal-open").css("padding-right", "inherit");
        $(btn).parents(".list-group-item").remove();

        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(employerFavoriteList[i]),
            url: "/page/demo/employer/removefavorite",
            success: function (data) {

            }
        });
    }

    function queryFavoriteResume() {
        var url = '/page/demo/employer/allfavorites?&employerId='
                + escape('${param.employerId}');
        $.post(
                url,
                null,
                function (json) {
                    var data = $.parseJSON(json);
                    if (data.retCode == "fail") {
                        $('#tableList').html("");
                        $('#tableList').append('<div class="solidborder1 woo-bigger-margin-top woo-label-padding">' +
                                '<h4 class="woo-horizontal-middle" style="text-align: center;">您还没有收藏任何简历！</h4>' +
                                '</div>');
                        return;
                    }
                    $('#tableList').html("");
                    var jobHunterDetail = null;
                    employerFavoriteList = data.employer_favorite;
                    var jobHunterDetailList = data.job_hunter_list;
                    for (var i = 0; i < jobHunterDetailList.length; ++i) {
                        jobHunterDetail = jobHunterDetailList[i];
                        var avatarPath = '', yearold = '', hunterSex = '', currentPlace = '', jobHunterName = '';
                        if (jobHunterDetail.jobHunterAvatarPath != null && jobHunterDetail.jobHunterAvatarPath != '') {
                            avatarPath = jobHunterDetail.jobHunterAvatarPath;
                        } else {
                            avatarPath = '/img/avatar_default.jpg'
                        }
                        if (jobHunterDetail.jobHunterBirthday != null && jobHunterDetail.jobHunterBirthday != '') {
                            yearold = '<li>' + calcusYear(jobHunterDetail.jobHunterBirthday) + '岁</li>';
                        }
                        if (jobHunterDetail.jobHunterSex != null && jobHunterDetail.jobHunterSex != '') {
                            hunterSex = '<li>' + jobHunterDetail.jobHunterSex + '</li>';
                        }
                        if (regular(jobHunterDetail.jobHunterCurrentCity) != '' || regular(jobHunterDetail.jobHunterCurrentDistrict) != '') {
                            currentPlace = '<li>' + '目前所在地：' + regular(jobHunterDetail.jobHunterCurrentCity) +
                                    '-' + regular(jobHunterDetail.jobHunterCurrentDistrict) + '</li>';
                        }
                        if (jobHunterDetail.jobHunterName != null && jobHunterDetail.jobHunterName != '') {
                            jobHunterName = '<li>' + jobHunterDetail.jobHunterName + '</li>';
                        }
                        if (regular(jobHunterDetail.jobHunterMajor) != '') {
                            jobHunterMajor = ' 专业：' + jobHunterDetail.jobHunterMajor;
                        }
                        var tI = getTimeIdentity();
                        $("#tableList").append('<li class="list-group-item">' +
                                '<div class="woolistitem">' +
                                '<div class="row">' +
                                '<div class="col-md-2">' +
                                '<a class="media-left media-middle" href="/jsp/employer/hunter_resume_r.jsp?jobHunterId=' +
                                jobHunterDetail.jobHunterId +
                                '&employerId=' +
                                escape(${param.employerId}) +
                                '">' +
                                '<img class="woo-avatar" src="' +
                                avatarPath +
                                '">' +
                                '</a></div>' +
                                '<div class="col-md-5">' +
                                '<ul class="list-inline">' +
                                jobHunterName +
                                yearold +
                                hunterSex +
                                currentPlace +
                                '<li>' +
                                '</ul>' +
                                '<ul class="list-inline">' +
                                '<li>' +
                                getExperienceByStartWorkYear(jobHunterDetail.jobHunterStartWorkYear) +
                                '学历：' +
                                jobHunterDetail.jobHunterBachelorDegree +
                                jobHunterMajor +
                                '</li>' +
                                '</ul>' +
                                '</div>' +
                                '<div class="col-md-4">' +
                                '<div class="woo-invite-button">' +
                                '<button class="btn btn-primary floatright" data-toggle="modal" data-target="#inviteModal' +
                                tI +
                                '">邀请投递职位' +
                                '</button>' +
                                '<div class="modal fade" id="inviteModal' +
                                tI +
                                '" tabindex="-1" role="dialog" aria-labelledby="inviteModalLabel"' +
                                'aria-hidden="true">' +
                                '<div class="modal-dialog">' +
                                '<div class="modal-content">' +
                                '<div class="modal-header">' +
                                '<button type="button" class="close" data-dismiss="modal"><span' +
                                'aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
                                '</button>' +
                                '<h4 class="modal-title fontem" id="inviteModalLabel">邀请</h4>' +
                                '</div>' +
                                '<div class="modal-body fontem">' +
                                '<select class="form-control">' +
                                myJobs +
                                '</select>' +
                                '</div>' +
                                '<div class="modal-footer">' +
                                '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>' +
                                '<button type="button" class="btn btn-primary"' +
                                'onclick="invite(this,' +
                                jobHunterDetail.jobHunterId + ',${param.employerId}' +
                                ')">邀请' +
                                '</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-md-1">' +
                                '<button style="background: transparent;border: ' +
                                'transparent" data-toggle="modal"' +
                                'data-target="#deleteModal' +
                                tI +
                                '">' +
                                '<span class="fa fa-trash-o"></span></button>' +
                                '<div class="modal fade" id="deleteModal' +
                                tI +
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
                                '你确定删除此条收藏记录吗？' +
                                '</div>' +
                                '<div class="modal-footer">' +
                                '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消' +
                                '</button>' +
                                '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="removeEmployerFavoriteResumeModel(this,' +
                                i +
                                ')">确定' +
                                '</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</li>');
                    }
                });
    }
</script>
</body>
</html>