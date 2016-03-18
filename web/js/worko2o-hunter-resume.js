function doFavoriteHunter(jobHunterId, employerId) {
    var param_data = {
        jobHunterId: jobHunterId,
        employerId: employerId,
        favorited: 1
    };
    $.ajax({
        type: "post",
        url: '/page/demo/employer/addfavorite',
        data: param_data,
        dataType: "json",
        success: function (msg) {
            $("#doFavoriteBtnId").hide();
            $("#favoritedBtnId").show();
        }
    }, "json");
}

function doDownloadResume(jobHunterId, employerId) {
    $("#doFavoriteBtnId").hide();
    $("#favoritedBtnId").show();
    $.post("/page/demo/jobHunter/jobhunter", $.param({jobHunterId: jobHunterId}), function (data) {
        $("#seePhoneBtnId").html('<i class="fa fa-phone"></i>&nbsp;' + data.job_hunter_detail.jobHunterTel);
        $("#seePhoneBtnId").attr("onclick", "");
        downloaded = true;
    }, 'json');
    var param_data = {
        jobHunterId: jobHunterId,
        employerId: employerId,
        favorited: 1,
        downloaded: 1
    };
    if (employer_favorite_hunter_id == null) {
        $.ajax({
            type: "post",
            url: '/page/demo/employer/addfavorite',
            data: param_data,
            dataType: "json",
            success: function (msg) {
            }
        }, "json");
    } else {
        param_data = {
            jobHunterId: jobHunterId,
            employerId: employerId,
            favorited: 1,
            downloaded: 1,
            employerFavoriteHunterId: employer_favorite_hunter_id
        };
        $.ajax({
            type: "post",
            url: '/page/demo/employer/modfavorite',
            data: param_data,
            dataType: "json",
            success: function (msg) {
            }
        }, "json");
    }

    downNum++;

    $("#downloadedProg").css("width", (downNum / totalNum) * 100 + '%');
    $("#downloadedId").text("" + downNum + "/" + totalNum + "查看");
    param_data = {
        jobHunterId: jobHunterId,
        employerId: employerId,
        resumeHaveDownloadNum: downNum
    };
    $.ajax({
        type: "post",
        url: '/page/demo/employer/profile',
        data: param_data,
        dataType: "json",
        success: function (msg) {
        }
    }, "json");
}


function setResumeValByData(data, employerId) {
    var basic_data = data.job_hunter_detail;
    var expect_list = data.job_hunter_expect_list;
    var edu_experience_list = data.job_hunter_edu_experience_list;
    var train_experience_list = data.job_hunter_train_experience_list;
    var work_experience_list = data.job_hunter_work_experience_list;
    var experienceYear;
    experienceYear = getExperienceByStartWorkYear(basic_data.jobHunterStartWorkYear);
    var specialty = '';
    if (regular(basic_data.jobHunterSpecialty) != '') {
        specialty = '<h5 class="margint10"><span class="clra6a6a6">技能特长：</span>' + basic_data.jobHunterSpecialty + '</h5>';
    }
    var telHtml = '';
    if (employerId == undefined) {
        telHtml = '<div id="seePhoneBtnId" class="block borderw2 clrbd8e44ad backtransparent margint10"><i class="fa fa-phone"></i>&nbsp;' +
            basic_data.jobHunterTel +
            '</div>';
    } else {
        telHtml = '<div id="seePhoneBtnId" class="block borderw2 clrbd8e44ad backtransparent margint10"' +
            ' onclick="doDownloadResume(' +
            basic_data.jobHunterId +
            ', ' +
            employerId +
            ')"><i class="fa fa-phone"></i>&nbsp;查看电话' +
            '</div>';
    }
    var basicInfo = '<span class="font20 fontem">' +
        regular(basic_data.jobHunterName) +
        '</span>' +
        '<span class="clr440062 marginl10">' +
        regular(basic_data.jobHunterSex) +
        '| ' +
        regular(calcusYear(basic_data.jobHunterBirthday)) +
        '| ' +
        experienceYear +
        '| ' +
        regular(basic_data.jobHunterCurrentCity) +
        '-' +
        regular(basic_data.jobHunterCurrentDistrict) +
        '</span>' +
        specialty +
        '<h5 class="margint10"><span class="clra6a6a6">求职状态：</span>' +
        regular(basic_data.jobHunterCurrentStatus) +
        '</h5>' +
        '<label class="clra6a6a6"> ' +
        regular(basic_data.jobHunterBachelorDegree) +
        '|' +
        regular(basic_data.jobHunterMajor) +
        '|' +
        regular(basic_data.jobHunterCollege) +
        '</label>' +
        telHtml;

    $("#basicInfoId").html(basicInfo);
    $("#avatarId").attr("src", basic_data.jobHunterAvatarPath);
    if (basic_data.jobHunterDepict != undefined) {
        $("#selfDepictId").html(
            '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
                '<div class="row margint10 marginb10">' +
                ' <div class="col-md-6">' +
                '<span class="fontem font20"><i class="fa fa-heart font24"></i> 自我评价</span>' +
                '</div>' +
                '</div>' +
                '<p>' +
                regular(basic_data.jobHunterDepict) +
                '</p></div>');
    } else {
        $("#selfDepictId").css("display", "none");
    }
    if (expect_list.length > 0) {
        var expectJob = regular(expect_list[0].expectJob);
        expectJob = expectJob == '' ? '' : '<span class="clra6a6a6">期望工作：</span>' + expectJob;
        var city = regular(expect_list[0].city);
        city = city == '' ? '' : '<span class="clra6a6a6">期望工作城市：</span>' + city;
        var expectSalary = regular(expect_list[0].expectSalary);
        expectSalary = expectSalary == '' ? '' : '<span class="clra6a6a6">期望薪水：</span>' + expectSalary;
        var workPattern = regular(expect_list[0].workPattern);
        workPattern = workPattern == '' ? '' : '<span class="clra6a6a6">期望工作方式：</span>' + workPattern;
        var expectInfo =
            '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
                '<div class="row margint10 marginb10">' +
                ' <div class="col-md-6">' +
                '<span class="fontem font20"><i class="fa fa-list-alt font24"></i> 职业意向</span>' +
                '</div>' +
                '</div>' +
                '<ul class="list-unstyled">' +
                '<li class="marginb10">' +
                expectJob +
                '</li>' +
                '<li class="marginb10">' +
                city +
                '</li>' +
                '<li class="marginb10">' +
                expectSalary +
                '</li>' +
                '<li class="marginb10">' +
                workPattern +
                '</li>' +
                '</ul>';
        $("#expectId").html(expectInfo);
    } else {
        $("#expectId").css("display", "none");
    }

    if (work_experience_list.length == 0) {
        $("#workExperienceId").css("display", "none");
    } else {
        var workexperienceInfo = '';
        $("#workExperienceId").html('');
        workexperienceInfo = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
            '<div class="row margint10 marginb10">' +
            ' <div class="col-md-6">' +
            '<span class="fontem font20"><i class="fa fa-briefcase font24"></i> 工作经历</span>' +
            '</div>' +
            '</div>';
        for (var i = 0; i < work_experience_list.length; ++i) {
            workexperienceInfo +=
                '<div class="clrbedeaf4 marginb10 padding6">' +
                    regular(formatDateTime(work_experience_list[i].beginTime)) +
                    '<span>--</span>' +
                    '<span>' +
                    regular(formatDateTime(work_experience_list[i].endTime)) +
                    '</span>' +
                    regular(work_experience_list[i].employerName) +
                    '</div>' +
                    '<p><span class="clra6a6a6">' +
                    '工作职位：</span>' +
                    regular(work_experience_list[i].jobName) +
                    '</p>' +
                    '<p><span class="clra6a6a6">' +
                    '工作职责：</span>' +
                    regular(work_experience_list[i].workDesc) +
                    '</p>';
        }
        workexperienceInfo += '</div>';
        $("#workExperienceId").append(workexperienceInfo);
    }

    if (train_experience_list.length == 0) {
        $("#trainExperienceId").css("display", "none");
    } else {
        var trainexperienceInfo = '';
        $("#trainExperienceId").html('');
        var trainexperienceInfo = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
            '<div class="row margint10 marginb10">' +
            ' <div class="col-md-6">' +
            '<span class="fontem font20"><i class="fa fa-signal font24"></i> 培训经历</span>' +
            '</div>' +
            '</div>';
        for (var i = 0; i < train_experience_list.length; ++i) {
            trainexperienceInfo +=
                '<div class="clrbedeaf4 marginb10 padding6">' +
                    regular(formatDateTime(train_experience_list[i].trainBeginDate)) +
                    '<span>--</span>' +
                    '<span>' +
                    regular(formatDateTime(train_experience_list[i].trainEndDate)) +
                    '</span>' +
                    regular(train_experience_list[i].trainInstitutionName) +
                    '</div>' +
                    '<p><span class="clra6a6a6">' +
                    '培训课程：</span>' +
                    regular(train_experience_list[i].courseName) +
                    '</p>' +
                    '<p><span class="clra6a6a6">' +
                    '所获证书： </span>' +
                    regular(train_experience_list[i].certificateName) +
                    '</p>' +
                    '<p><span class="clra6a6a6">' +
                    '培训描述：</span>' +
                    regular(train_experience_list[i].trainDesc) +
                    '</p>';
        }
        trainexperienceInfo += '</div>';
        $("#trainExperienceId").append(trainexperienceInfo);
    }

    if (edu_experience_list.length == 0) {
        $("#eduExperienceId").css("display", "none");
    } else {
        var eduexperienceInfo = '';
        $("#eduExperienceId").html('');
        var eduexperienceInfo = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
            '<div class="row margint10 marginb10">' +
            ' <div class="col-md-6">' +
            '<span class="fontem font20"><i class="fa fa-book font24"></i> 教育经历</span>' +
            '</div>' +
            '</div>';
        for (var i = 0; i < edu_experience_list.length; ++i) {
            eduexperienceInfo +=
                '<div class="clrbedeaf4 marginb10 padding6">' +
                    regular(formatDateTime(edu_experience_list[i].beginTime)) +
                    '</span>' +
                    '<span>--</span>' +
                    '<span>' +
                    regular(formatDateTime(edu_experience_list[i].endTime)) +
                    '</span>' +
                    regular(edu_experience_list[i].college) +
                    '</div>' +
                    '<p><span class="clra6a6a6">专业：</span>' +
                    regular(edu_experience_list[i].major) +
                    '</p>' +
                    '<p><span class="clra6a6a6">学历：</span>' +
                    regular(edu_experience_list[i].bachelorDegree) +
                    '</p>';

        }
        eduexperienceInfo += '</div>';
        $("#eduExperienceId").append(eduexperienceInfo);
    }
    if (regular(basic_data.productPic1) != "" || regular(basic_data.productPic2) || regular(basic_data.productPic3) || regular(basic_data.productPic4) || regular(basic_data.productPic5)) {

        var productInfo = '';
        $("#productId").html('');
        var productInfo = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">' +
            '<div class="row margint10 marginb10">' +
            ' <div class="col-md-6">' +
            '<span class="fontem font20"><i class="fa fa-picture-o font24"></i> 作品展示</span>' +
            '</div>' +
            '</div>';
        var photoConstFore = '<div class="text-center"><img class="productpic" src="';
        var photoConstEnd = '" ';
        var photoEnd2 = 'onclick="window.location.href=\'';
        var photoEnd3 = '\'" alt="' +
            basic_data.jobHunterName + '的作品' +
            '" />'
            + '</div>';
        if (basic_data.productPic1 != null && basic_data.productPic1 != '') {
            productInfo += photoConstFore + basic_data.productPic1 + photoConstEnd + photoEnd2 + basic_data.productPic1 + photoEnd3;
        }
        if (basic_data.productPic2 != null && basic_data.productPic2 != '') {
            productInfo += photoConstFore + basic_data.productPic2 + photoConstEnd + photoEnd2 + basic_data.productPic2 + photoEnd3;
        }
        if (basic_data.productPic3 != null && basic_data.productPic3 != '') {
            productInfo += photoConstFore + basic_data.productPic3 + photoConstEnd + photoEnd2 + basic_data.productPic3 + photoEnd3;
        }
        if (basic_data.productPic4 != null && basic_data.productPic4 != '') {
            productInfo += photoConstFore + basic_data.productPic4 + photoConstEnd + photoEnd2 + basic_data.productPic4 + photoEnd3;
        }
        if (basic_data.productPic5 != null && basic_data.productPic5 != '') {
            productInfo += photoConstFore + basic_data.productPic5 + photoConstEnd + photoEnd2 + basic_data.productPic5 + photoEnd3;
        }
        if (regular(basic_data.productUrl) != '') {
            productInfo += "<div><span>作品链接</span><a href='" +
                basic_data.productUrl +
                "'>" +
                basic_data.productUrl +
                "</a></div>"
        }
        productInfo += "</div>";
        $("#productId").append(productInfo);
    }
}

function queryResume(jobHunterId, employerId, hrEmail, password) {
    $.post("/page/demo/jobHunter/jobhunter", $.param({jobHunterId: jobHunterId}), function (data) {
        setResumeValByData(data, employerId);
        if (downloaded) {
            $("#seePhoneBtnId").html('<i class="fa fa-phone"></i>&nbsp;' + data.job_hunter_detail.jobHunterTel);
            $("#seePhoneBtnId").attr("onclick", "");
        }
    }, 'json');
    addInviteJobBtn(jobHunterId, employerId);
}

function invite(btn, jobHunterId, employerId) {
    var selectff = $(btn).parent().parent().find("select").find('option:selected');
    var index = $(selectff).text();
    var jobId = $(selectff).attr("value");
    var param_data = {
        employerStatus: 1,
        employerStatusChanged: 1,
        jobHunterId: jobHunterId,
        employerId: employerId,
        jobId: jobId
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        data: $.param(param_data),
        url: "/page/demo/job/interact/employer/add",
        success: function (data) {
            if (data.retCode == "fail") {
                $.simplyToast('邀请失败，可能您之前已经发送了邀请，请换一个职位发送。', 'danger');
            } else {
                $.simplyToast('您已成功邀请了一个职位，不再邀请请点击取消退出。', 'success');
            }
        }
    }, "json");
}

function addInviteJobBtn(jobHunterId, employerId) {
    if (employerId != '') {
        if (myJobs.length != 0) {
            var inviteBtn = '<button class="btn btn-primary" style="font-size: 14px; float: right;" data-toggle="modal" data-target="#inviteModal">邀请投递职位' +
                '</button>' +
                '<div class="modal fade" id="inviteModal" tabindex="-1" role="dialog" aria-labelledby="inviteModalLabel"' +
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
                jobHunterId + ',' + employerId +
                ')">邀请' +
                '</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $(".woo-operation").append(inviteBtn);
        }
    }
}