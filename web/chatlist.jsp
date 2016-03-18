<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container">
    <div class="clr440062 margint10 font20 clrbedeaf4 paddingl20 paddingt10 paddingb10">私信会话列表</div>
    <ul id="talklist" class="list-unstyled">

    </ul>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
if (${param.type == 0}) {
    var param_data = {
        "jobHunterId": '${sessionScope.jobHunterId}'
    }
    $.post(
            '/page/demo/job/interact/hunter/elpiactjobs',
            param_data,
            function (data) {

                var envelopeList = null;
                //首先查询未读列表
                $.ajax({
                    url: '/page/demo/job/envelope/getunreadbyhunterid',
                    dataType: 'json',
                    data: $.param({jobHunterId: '${param.jobHunterId}'}),
                    async: false,
                    success: function (data) {
                        envelopeList = data.envelope_unread;
                    }
                });


                var interactList = data.job_interact_list;
                var str = "";
                for (var i = 0; i < interactList.length; ++i) {
                    str += interactList[i].employerId + ",";
                }
                if (str.lastIndexOf(",") == str.length - 1) {
                    str = str.substring(0, str.length - 1);
                }
                param_data = {
                    "employerIds": str
                }

                $.post(
                        '/page/demo/employer/employerdetails',
                        param_data,
                        function (data) {
                            if (data.employer_detail == undefined) {
                                return;
                            }
                            for (var i = 0; i < data.employer_detail.length; ++i) {
                                var badgeNum = 0;
                                if (envelopeList != undefined) {
                                    for (var j = 0; j < envelopeList.length; ++j) {
                                        if (envelopeList[j].employerId == data.employer_detail[i].employerId) {
                                            badgeNum++;
                                        }
                                    }
                                }
                                var online = 0;
                                var param_data = {
                                    id: data.employer_detail[i].employerId,
                                    type: 1
                                }
                                $.ajax({
                                    type: 'post',
                                    url: '/envelopechatlist',
                                    dataType: 'json',
                                    data: $.param(param_data),
                                    async: false,
                                    success: function (data) {
                                        online = data.online;
                                    }
                                });
                                var logoPath = '';
                                if (data.employer_detail[i].logoPath == undefined) {
                                    logoPath = '/img/companydefault.png';
                                } else {
                                    logoPath = data.employer_detail[i].logoPath;
                                }
                                $("#talklist").append(
                                        "<li class=\"list-group-item\">"
                                                + "<div class=\"pointer\" onclick='showDialog(\"/jsp/chat/login.jsp?action=loginRoom&type=1&jobHunterId=${param.jobHunterId}&employerId="
                                                + data.employer_detail[i].employerId
                                                + "&employerName="
                                                + data.employer_detail[i].employerName
                                                + "&logoPath="
                                                + data.employer_detail[i].logoPath
                                                + "&online="
                                                + online
                                                + "\")'>"
                                                + "<div>"
                                                + "<div class=\"row\">"
                                                + "<div class=\"col-md-1\">"
                                                + "<img class='size60' alt='"
                                                + data.employer_detail[i].employerName
                                                + "' src=\""
                                                + logoPath
                                                + "\"/>"
                                                + "</div>"
                                                + "<div class=\"col-md-5\">"
                                                + "<ul class=\"list-unstyled\">"
                                                + "<li ><span class=\"font16 letterspace1 fontem\" href='javascript:void(0);'>"
                                                + data.employer_detail[i].employerName
                                                + "</span>"
                                                + ((badgeNum > 0) ? ("<span class='badge clrbred'>" + badgeNum + "</span> ") : "")
                                                + "<button class='marginl10 circle sizew10 sizeh10 borderw0 padding0 " +
                                                (online ? "clrbb5d95f" : "clra6a6a6") +
                                                "'></button>"
                                                + "</li>"
                                                + "<li><span class='clra6a6a6'> " +
                                                regular(data.employer_detail[i].address) +
                                                "</span></li>" +
                                                "</ul></div></div></div></div></li>")
                            }
                        },
                        'json');
            },
            'json');

}
else {
    var param_data = {
        "employerId": '${sessionScope.employerId}'
    }
    $.post(
            '/page/demo/job/interact/employer/elpiactjobs',
            param_data,
            function (data) {
                var envelopeList;
                //首先查询未读列表
                $.ajax({
                    url: '/page/demo/job/envelope/getunreadbyemployerid',
                    dataType: 'json',
                    data: $.param({employerId: '${param.employerId}'}),
                    async: false,
                    success: function (data) {
                        envelopeList = data.envelope_unread;
                    }
                });


                var interactList = data.job_interact_list;
                var str = "";
                for (var i = 0; i < interactList.length; ++i) {
                    str += interactList[i].jobHunterId + ",";
                }
                if (str.lastIndexOf(",") == str.length - 1) {
                    str = str.substring(0, str.length - 1);
                }
                param_data = {
                    "hunterIds": str
                }
                $.post(
                        '/page/demo/hunter/hunterdetails',
                        param_data,
                        function (data) {
                            if (data.job_hunter_detail == undefined) {
                                return;
                            }
                            for (var i = 0; i < data.job_hunter_detail.length; ++i) {
                                var badgeNum = 0;
                                if (envelopeList != undefined) {
                                    for (var j = 0; j < envelopeList.length; ++j) {
                                        if (envelopeList[j].jobHunterId == data.job_hunter_detail[i].jobHunterId) {
                                            badgeNum++;
                                        }
                                    }
                                }

                                var online = 0;
                                var param_data = {
                                    id: data.job_hunter_detail[i].jobHunterId,
                                    type: 0
                                }
                                $.ajax({
                                    type: 'post',
                                    url: '/envelopechatlist',
                                    dataType: 'json',
                                    data: $.param(param_data),
                                    async: false,
                                    success: function (data) {
                                        online = data.online;
                                    }
                                });
                                var avatarPath = '';
                                if (data.job_hunter_detail[i].jobHunterAvatarPath == undefined) {
                                    avatarPath = '/img/avatar_default.jpg';
                                } else {
                                    avatarPath = data.job_hunter_detail[i].jobHunterAvatarPath;
                                }
                                $("#talklist").append(
                                        "<li class=\"list-group-item\">"
                                                + "<div class=\"pointer\" onclick='showDialog(\"/jsp/chat/login.jsp?action=loginRoom&type=0&employerId=${param.employerId}&jobHunterId="
                                                + data.job_hunter_detail[i].jobHunterId
                                                + "&hunterName="
                                                + data.job_hunter_detail[i].jobHunterName
                                                + "&avatarPath="
                                                + data.job_hunter_detail[i].jobHunterAvatarPath
                                                + "&online="
                                                + online
                                                + "\")'>"
                                                + "<div class=\"woolistitem\" "
                                                + ">"
                                                + "<div class=\"row\">"
                                                + "<div class=\"col-md-1\">"
                                                + "<img class='size60' alt='"
                                                + data.job_hunter_detail[i].jobHunterName
                                                + "' src=\""
                                                + avatarPath
                                                + "\"/>"
                                                + "</div>"
                                                + "<div class=\"col-md-5\">"
                                                + "<ul class=\"list-unstyled\">"
                                                + "<li ><span class=\"font16 letterspace1 fontem\" href='javascript:void(0);'>"
                                                + data.job_hunter_detail[i].jobHunterName
                                                + "</span><button class='marginl10 circle sizew10 sizeh10 borderw0 padding0 "
                                                + ((badgeNum > 0) ? ("<span class='badge clrbred'>" + badgeNum + "</span> ") : "")
                                                + (online ? "clrbb5d95f" : "clra6a6a6") +
                                                "'></button>" +
                                                "</li>"
                                                + "<li><span class='clra6a6a6'> " +
                                                regular(data.job_hunter_detail[i].jobHunterBachelorDegree) + "|" + regular(data.job_hunter_detail[i].jobHunterSex) +
                                                "</span></li>"
                                                + "</ul></div></div></div></a></li>");
                            }
                        },
                        'json');
            },
            'json');

}

function showDialog(url) {
    var iTop = (window.screen.availHeight - 600) / 2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth - 800) / 2; //获得窗口的水平位置;
    var winOption = "height=600px,width=800px,top=" + iTop + ",left=" + iLeft + ",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=0";
    return  window.open(url, window, winOption);
}
</script>
</body>
</html>