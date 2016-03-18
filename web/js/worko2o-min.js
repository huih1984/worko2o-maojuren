var myJobs = new Array();
function queryallMyJobs(b) {
    var a = new Object();
    a.pageIndex = 1;
    a.pageSize = 20;
    a.employerId = b;
    $.ajax({type: "post", url: "/page/demo/job/employerjob", data: a, dataType: "json", async: false, success: function (e) {
        var d = e.job_list;
        if (d != undefined) {
            for (var c = 0; c < d.length; ++c) {
                myJobs.push(d[c])
            }
        }
    }})
}
function hideErrors() {
    $(".woo-error").each(function () {
        $(this).css("visibility", "hidden");
        $(this).css("display", "none")
    })
}
function showError(b) {
    $.simplyToast("您有必填项没填，请填完再提交！", "danger");
    var a = b.parent().find(".woo-error");
    b.parent().find(".woo-error").css("visibility", "visible");
    b.parent().find(".woo-error").css("display", "inherit")
}
function hideError(a) {
    a.parent().find(".woo-error").css("visibility", "hidden");
    a.parent().find(".woo-error").css("display", "none")
}
function formValidCheck(b) {
    var a = true;
    if (b == undefined) {
        b = $("body")
    }
    b.find(".woo-check-drop-down").each(function () {
        var c = $(this).find(".placeholder").text();
        if (c.indexOf("选择") >= 0) {
            showError($(this));
            a = false
        } else {
            hideError($(this))
        }
    });
    b.find(".woo-check-input").each(function () {
        var c = $(this).val();
        if (c == "") {
            showError($(this));
            a = false
        } else {
            hideError($(this))
        }
    });
    return a
}
function formValidCheckSub(a) {
    var b = true;
    a.find(".woo-check-drop-down").each(function () {
        var c = $(this).find(".placeholder").text();
        if (c.indexOf("选择") >= 0) {
            showError($(this));
            b = false
        } else {
            hideError($(this))
        }
    });
    a.find(".woo-check-input").each(function () {
        var c = $(this).val();
        if (c == "") {
            showError($(this));
            b = false
        } else {
            hideError($(this))
        }
    });
    return b
}
function doFavoriteHunter(b, c) {
    var a = {jobHunterId: b, employerId: c, favorited: 1};
    $.ajax({type: "post", url: "/page/demo/employer/addfavorite", data: a, dataType: "json", success: function (d) {
        $("#doFavoriteBtnId").hide();
        $("#favoritedBtnId").show()
    }}, "json")
}
function doDownloadResume(b, c) {
    $("#doFavoriteBtnId").hide();
    $("#favoritedBtnId").show();
    $.post("/page/demo/jobHunter/jobhunter", $.param({jobHunterId: b}), function (d) {
        $("#seePhoneBtnId").html('<i class="fa fa-phone"></i>&nbsp;' + d.job_hunter_detail.jobHunterTel);
        $("#seePhoneBtnId").attr("onclick", "");
        downloaded = true
    }, "json");
    var a = {jobHunterId: b, employerId: c, favorited: 1, downloaded: 1};
    if (employer_favorite_hunter_id == null) {
        $.ajax({type: "post", url: "/page/demo/employer/addfavorite", data: a, dataType: "json", success: function (d) {
        }}, "json")
    } else {
        a = {jobHunterId: b, employerId: c, favorited: 1, downloaded: 1, employerFavoriteHunterId: employer_favorite_hunter_id};
        $.ajax({type: "post", url: "/page/demo/employer/modfavorite", data: a, dataType: "json", success: function (d) {
        }}, "json")
    }
    downNum++;
    $("#downloadedProg").css("width", (downNum / totalNum) * 100 + "%");
    $("#downloadedId").text("" + downNum + "/" + totalNum + "查看");
    a = {jobHunterId: b, employerId: c, resumeHaveDownloadNum: downNum};
    $.ajax({type: "post", url: "/page/demo/employer/profile", data: a, dataType: "json", success: function (d) {
    }}, "json")
}
function setResumeValByData(z, y) {
    var b = z.job_hunter_detail;
    var t = z.job_hunter_expect_list;
    var q = z.job_hunter_edu_experience_list;
    var m = z.job_hunter_train_experience_list;
    var g = z.job_hunter_work_experience_list;
    var w;
    w = getExperienceByStartWorkYear(b.jobHunterStartWorkYear);
    var e = "";
    if (regular(b.jobHunterSpecialty) != "") {
        e = '<h5 class="margint10"><span class="clra6a6a6">技能特长：</span>' + b.jobHunterSpecialty + "</h5>"
    }
    var d = "";
    if (y == undefined) {
        d = '<div id="seePhoneBtnId" class="block borderw2 clrbd8e44ad backtransparent margint10"><i class="fa fa-phone"></i>&nbsp;' + b.jobHunterTel + "</div>"
    } else {
        d = '<div id="seePhoneBtnId" class="block borderw2 clrbd8e44ad backtransparent margint10" onclick="doDownloadResume(' + b.jobHunterId + ", " + y + ')"><i class="fa fa-phone"></i>&nbsp;查看电话</div>'
    }
    var s = '<span class="font20 fontem">' + regular(b.jobHunterName) + '</span><span class="clr440062 marginl10">' + regular(b.jobHunterSex) + "| " + regular(calcusYear(b.jobHunterBirthday)) + "| " + w + "| " + regular(b.jobHunterCurrentCity) + "-" + regular(b.jobHunterCurrentDistrict) + "</span>" + e + '<h5 class="margint10"><span class="clra6a6a6">求职状态：</span>' + regular(b.jobHunterCurrentStatus) + '</h5><label class="clra6a6a6"> ' + regular(b.jobHunterBachelorDegree) + "|" + regular(b.jobHunterMajor) + "|" + regular(b.jobHunterCollege) + "</label>" + d;
    $("#basicInfoId").html(s);
    $("#avatarId").attr("src", b.jobHunterAvatarPath);
    if (b.jobHunterDepict != undefined) {
        $("#selfDepictId").html('<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-heart font24"></i> 自我评价</span></div></div><p>' + regular(b.jobHunterDepict) + "</p></div>")
    } else {
        $("#selfDepictId").css("display", "none")
    }
    if (t.length > 0) {
        var r = regular(t[0].expectJob);
        r = r == "" ? "" : '<span class="clra6a6a6">期望工作：</span>' + r;
        var v = regular(t[0].city);
        v = v == "" ? "" : '<span class="clra6a6a6">期望工作城市：</span>' + v;
        var a = regular(t[0].expectSalary);
        a = a == "" ? "" : '<span class="clra6a6a6">期望薪水：</span>' + a;
        var h = regular(t[0].workPattern);
        h = h == "" ? "" : '<span class="clra6a6a6">期望工作方式：</span>' + h;
        var f = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-list-alt font24"></i> 职业意向</span></div></div><ul class="list-unstyled"><li class="marginb10">' + r + '</li><li class="marginb10">' + v + '</li><li class="marginb10">' + a + '</li><li class="marginb10">' + h + "</li></ul>";
        $("#expectId").html(f)
    } else {
        $("#expectId").css("display", "none")
    }
    if (g.length == 0) {
        $("#workExperienceId").css("display", "none")
    } else {
        var j = "";
        $("#workExperienceId").html("");
        j = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-briefcase font24"></i> 工作经历</span></div></div>';
        for (var x = 0; x < g.length; ++x) {
            j += '<div class="clrbedeaf4 marginb10 padding6">' + regular(formatDateTime(g[x].beginTime)) + "<span>--</span><span>" + regular(formatDateTime(g[x].endTime)) + "</span>" + regular(g[x].employerName) + '</div><p><span class="clra6a6a6">工作职位：</span>' + regular(g[x].jobName) + '</p><p><span class="clra6a6a6">工作职责：</span>' + regular(g[x].workDesc) + "</p>"
        }
        j += "</div>";
        $("#workExperienceId").append(j)
    }
    if (m.length == 0) {
        $("#trainExperienceId").css("display", "none")
    } else {
        var k = "";
        $("#trainExperienceId").html("");
        var k = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-signal font24"></i> 培训经历</span></div></div>';
        for (var x = 0; x < m.length; ++x) {
            k += '<div class="clrbedeaf4 marginb10 padding6">' + regular(formatDateTime(m[x].trainBeginDate)) + "<span>--</span><span>" + regular(formatDateTime(m[x].trainEndDate)) + "</span>" + regular(m[x].trainInstitutionName) + '</div><p><span class="clra6a6a6">培训课程：</span>' + regular(m[x].courseName) + '</p><p><span class="clra6a6a6">所获证书： </span>' + regular(m[x].certificateName) + '</p><p><span class="clra6a6a6">培训描述：</span>' + regular(m[x].trainDesc) + "</p>"
        }
        k += "</div>";
        $("#trainExperienceId").append(k)
    }
    if (q.length == 0) {
        $("#eduExperienceId").css("display", "none")
    } else {
        var u = "";
        $("#eduExperienceId").html("");
        var u = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-book font24"></i> 教育经历</span></div></div>';
        for (var x = 0; x < q.length; ++x) {
            u += '<div class="clrbedeaf4 marginb10 padding6">' + regular(formatDateTime(q[x].beginTime)) + "</span><span>--</span><span>" + regular(formatDateTime(q[x].endTime)) + "</span>" + regular(q[x].college) + '</div><p><span class="clra6a6a6">专业：</span>' + regular(q[x].major) + '</p><p><span class="clra6a6a6">学历：</span>' + regular(q[x].bachelorDegree) + "</p>"
        }
        u += "</div>";
        $("#eduExperienceId").append(u)
    }
    if (regular(b.productPic1) != "" || regular(b.productPic2) || regular(b.productPic3) || regular(b.productPic4) || regular(b.productPic5)) {
        var c = "";
        $("#productId").html("");
        var c = '<div class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground"><div class="row margint10 marginb10"> <div class="col-md-6"><span class="fontem font20"><i class="fa fa-picture-o font24"></i> 作品展示</span></div></div>';
        var p = '<div class="text-center"><img class="productpic" src="';
        var l = '" ';
        var o = "onclick=\"window.location.href='";
        var n = '\'" alt="' + b.jobHunterName + '的作品" /></div>';
        if (b.productPic1 != null && b.productPic1 != "") {
            c += p + b.productPic1 + l + o + b.productPic1 + n
        }
        if (b.productPic2 != null && b.productPic2 != "") {
            c += p + b.productPic2 + l + o + b.productPic2 + n
        }
        if (b.productPic3 != null && b.productPic3 != "") {
            c += p + b.productPic3 + l + o + b.productPic3 + n
        }
        if (b.productPic4 != null && b.productPic4 != "") {
            c += p + b.productPic4 + l + o + b.productPic4 + n
        }
        if (b.productPic5 != null && b.productPic5 != "") {
            c += p + b.productPic5 + l + o + b.productPic5 + n
        }
        if (regular(b.productUrl) != "") {
            c += "<div><span>作品链接</span><a href='" + b.productUrl + "'>" + b.productUrl + "</a></div>"
        }
        c += "</div>";
        $("#productId").append(c)
    }
}
function queryResume(b, d, c, a) {
    $.post("/page/demo/jobHunter/jobhunter", $.param({jobHunterId: b}), function (e) {
        setResumeValByData(e, d);
        if (downloaded) {
            $("#seePhoneBtnId").html('<i class="fa fa-phone"></i>&nbsp;' + e.job_hunter_detail.jobHunterTel);
            $("#seePhoneBtnId").attr("onclick", "")
        }
    }, "json");
    addInviteJobBtn(b, d)
}
function invite(e, d, g) {
    var f = $(e).parent().parent().find("select").find("option:selected");
    var c = $(f).text();
    var a = $(f).attr("value");
    var b = {employerStatus: 1, employerStatusChanged: 1, jobHunterId: d, employerId: g, jobId: a};
    $.ajax({type: "POST", dataType: "json", data: $.param(b), url: "/page/demo/job/interact/employer/add", success: function (h) {
        if (h.retCode == "fail") {
            $.simplyToast("邀请失败，可能您之前已经发送了邀请，请换一个职位发送。", "danger")
        } else {
            $.simplyToast("您已成功邀请了一个职位，不再邀请请点击取消退出。", "success")
        }
    }}, "json")
}
function addInviteJobBtn(a, c) {
    if (c != "") {
        if (myJobs.length != 0) {
            var b = '<button class="btn btn-primary" style="font-size: 14px; float: right;" data-toggle="modal" data-target="#inviteModal">邀请投递职位</button><div class="modal fade" id="inviteModal" tabindex="-1" role="dialog" aria-labelledby="inviteModalLabel"aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><spanaria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title fontem" id="inviteModalLabel">邀请</h4></div><div class="modal-body fontem"><select class="form-control">' + myJobs + '</select></div><div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button><button type="button" class="btn btn-primary"onclick="invite(this,' + a + "," + c + ')">邀请</button></div></div></div></div>';
            $(".woo-operation").append(b)
        }
    }
}
function saveProducts(a) {
    $.ajax({type: "post", url: "/page/demo/jobHunter/jobhunterbasicw", data: a, dataType: "json", success: function (b) {
        if (b.retMsg == "success") {
            $.simplyToast("保存成功!", "success")
        } else {
            $.simplyToast("保存失败", "danger")
        }
    }}, "json")
}
function saveProduct(c, b) {
    var f = new Array();
    var e = $("#photoList input");
    for (var g = 0; g < e.length; g++) {
        if (e[g].files != null && e[g].files.length > 0) {
            f.push(e[g].files[0])
        }
    }
    var d = new Object();
    d.jobHunterId = b;
    d.productUrl = $("#producturl").val();
    if (f.length > 0) {
        uploadFiles(f, "/page/demo/upload", "hunterphoto", b, function (l) {
            var h = new Array("", "", "", "", "");
            var k = 0;
            for (var m = 0; m < e.length; m++) {
                if (e[m].files != null && e[m].files.length > 0) {
                    var j = JSON.parse(l[k]);
                    h[m] = j.file_path;
                    e[m].outerHTML = e[m].outerHTML;
                    e.eq(m).prev().children("img").attr("value", j.file_path);
                    k++
                } else {
                    h[m] = e.eq(m).prev().children("img").attr("value")
                }
            }
            d.productPic1 = h[0];
            d.productPic2 = h[1];
            d.productPic3 = h[2];
            d.productPic4 = h[3];
            d.productPic5 = h[4];
            saveProducts(d)
        })
    } else {
        var a = new Array("", "", "", "", "");
        for (var g = 0; g < e.length; g++) {
            a[g] = e.eq(g).prev().children("img").attr("value")
        }
        d.productPic1 = a[0];
        d.productPic2 = a[1];
        d.productPic3 = a[2];
        d.productPic4 = a[3];
        d.productPic5 = a[4];
        saveProducts(d)
    }
}
function plusPhoto(b) {
    $("#photoPlusBtn").before('<div class="productpic" style="position:relative">\n                <img src="' + b + '" value="' + b + '" class="productpic">\n                    <div><span class="circledel fa fa-remove"></span></div>\n                </div>\n                <input type="file" accept="image/*" class="margint10 marginb10">');
    var a = $("#photoPlusBtn").prev();
    var c = a.prev();
    if ($("#photoList div img").size() >= 5) {
        $("#photoPlusBtn").hide()
    }
    c.children("div").click(function () {
        $(this).parent().next().remove();
        $(this).parent().remove();
        if ($("#photoList div img").size() < 5) {
            $("#photoPlusBtn").show()
        }
    });
    a.change(function () {
        checkFile(this, 4028, this.previousElementSibling.firstElementChild, 400, 200, "")
    })
}
function queryHunterBasic(b) {
    var a = "/page/demo/jobHunter/complete?jobHunterId=" + b;
    $.post(a, null, function (d) {
        var c = 5;
        var f = 0;
        var e = $.parseJSON(d);
        if (e.job_hunter_detail == "ok") {
            f += 1
        }
        if (e.job_hunter_edu_experience_list == "ok") {
            f += 1
        }
        if (e.job_hunter_expect_list == "ok") {
            f += 1
        }
        if (e.job_hunter_train_experience_list == "ok") {
            f += 1
        }
        if (e.job_hunter_work_experience_list == "ok") {
            f += 1
        }
        $("#percentval").text(f / 5 * 100 + "%");
        $("#percentimg").css("width", (f / 5) * 100 + "%");
        queryHunterBasicInfo(b);
        queryExpect(b);
        queryHunterWorkExperience(b);
        queryHunterEduExperience(b);
        queryHunterTrainExperience(b)
    })
}
function saveDepict(b, a) {
    var c = new Object();
    c.jobHunterId = a;
    c.jobHunterDepict = $("#introduce textarea").val();
    saveProducts(c)
}
function queryHunterBasicInfo(b) {
    var a = $("#jobHunterAvatarPath").parent().find("input[type=file]");
    $("#avatarInputId").change(function () {
        checkComposeImgFile(this, 500, document.getElementById("jobHunterAvatarPath"), 150, 150, "")
    });
    url = "/page/demo/jobHunter/jobhunterbasic?jobHunterId=" + b;
    $.post(url, null, function (e) {
        var d = e.job_hunter_detail;
        if (d != null) {
            if (regular(d.jobHunterCurrentStatus) != "") {
                $("#currentStatusList").find(".placeholder").text(d.jobHunterCurrentStatus)
            }
            queryCurrentStatusList();
            if (d.jobHunterAvatarPath != null) {
                $("#jobHunterAvatarPath").attr("src", "" + d.jobHunterAvatarPath);
                $("#jobHunterAvatarPath").attr("value", d.jobHunterAvatarPath)
            }
            setRadio($('input[name="inputHunterSex"]'), d.jobHunterSex);
            $("#inputName").attr("value", d.jobHunterName);
            var c = d.jobHunterBirthday;
            if (d.jobHunterBirthday != null && d.jobHunterBirthday != "") {
                setDateTime($("#inputBirthday input"), d.jobHunterBirthday)
            }
            if (d.jobHunterCurrentProvince != null && d.jobHunterCurrentProvince != "") {
                $("#provinceList").find(".placeholder").text(d.jobHunterCurrentProvince)
            }
            if (d.jobHunterCurrentCity != null && d.jobHunterCurrentCity != "") {
                $("#cityList").find(".placeholder").text(d.jobHunterCurrentCity)
            }
            if (d.jobHunterCurrentDistrict != null && d.jobHunterCurrentDistrict != "") {
                $("#areaList").find(".placeholder").text(d.jobHunterCurrentDistrict)
            }
            $("#specialty").val(d.jobHunterSpecialty);
            queryProvince();
            $("#phonenum").val(d.jobHunterTel);
            $("#introduce textarea").val(d.jobHunterDepict);
            $("#introduce textarea").next().find("span").text((500 - $("#introduce textarea").val().length));
            $("#photoPlusBtn").click(function () {
                plusPhoto("")
            });
            if (regular(d.productPic1) != "") {
                plusPhoto(d.productPic1)
            }
            if (regular(d.productPic2) != "") {
                plusPhoto(d.productPic2)
            }
            if (regular(d.productPic3) != "") {
                plusPhoto(d.productPic3)
            }
            if (regular(d.productPic4) != "") {
                plusPhoto(d.productPic4)
            }
            if (regular(d.productPic5) != "") {
                plusPhoto(d.productPic5)
            }
            $("#producturl").val(d.productUrl)
        }
    }, "json");
    queryMultiSpecialty()
}
function submitBasicInfoForm(a) {
    a.jobHunterName = $("#inputName").val();
    a.jobHunterSex = getRadio($('input[name="inputHunterSex"]'));
    a.jobHunterBirthday = $("#inputBirthday input").val();
    a.jobHunterCurrentStatus = $("#currentStatusList").find(".placeholder").text();
    a.jobHunterCurrentProvince = $("#provinceList").find(".placeholder").text();
    a.jobHunterCurrentCity = $("#cityList").find(".placeholder").text();
    a.jobHunterCurrentDistrict = $("#areaList").find(".placeholder").text();
    $.ajax({type: "post", url: "/page/demo/jobHunter/jobhunterbasicw", data: a, dataType: "json", success: function (b) {
        if (b.retMsg == "success") {
            $.simplyToast("保存成功!", "success")
        } else {
            $.simplyToast("保存失败", "danger")
        }
    }}, "json")
}
function saveBasicInfo(c, b) {
    var e = $(c).parents().find("#basicinfo");
    if (!formValidCheck(e)) {
        return false
    }
    var a = $("#jobHunterAvatarPath").parent().find("input[type=file]");
    if (a[0].files != null && a[0].files.length > 0) {
        uploadFile(a[0].files[0], "/page/demo/upload", "hunteravatar", b, 1, function (h) {
            var g = JSON.parse(h);
            $("#jobHunterAvatarPath").attr("value", g.file_path);
            var f = new Object();
            f.jobHunterId = b;
            f.jobHunterAvatarPath = g.file_path;
            submitBasicInfoForm(f)
        })
    } else {
        var d = new Object();
        d.jobHunterAvatarPath = $("#jobHunterAvatarPath").attr("value");
        d.jobHunterId = b;
        submitBasicInfoForm(d)
    }
}
function queryExpect(b) {
    var a = "/page/demo/jobHunter/jobhunterexpect?jobHunterId=" + b;
    $.ajax({url: a, dataType: "json", success: function (d) {
        var e = d.job_hunter_expect_list;
        if (e != null && e.length > 0) {
            var c = e[0];
            $("#expectjob").val(c.expectJob);
            if (regular(c.expectSalary) != "") {
                $("#salaryList").find(".placeholder").text(c.expectSalary)
            }
            $("#expectplace").val(c.city);
            $("#workpattern").val(c.workPattern)
        }
    }});
    queryMultiJob();
    queryMultiCity();
    querySalaryList()
}
function putExpJobToInput(a) {
    var b = getPopVal(a);
    $("#expectjob").val(b)
}
function putSpecialtyToInput(a) {
    var b = getPopVal(a);
    $("#specialty").val(b)
}
function putExpPlaceToInput(a) {
    var b = getPopVal(a);
    $("#expectplace").val(b)
}
function getPopVal(a) {
    var b = "";
    $(a).parents(".modal-content").find(".modalselected").find("a").each(function () {
        b += $(this).text() + ","
    });
    if (b.length > 1) {
        b = b.substring(0, b.length - 1)
    }
    return b
}
function saveExpect(b, a) {
    var d = $(b).parents().find("#workexpect");
    if (!formValidCheck(d)) {
        return false
    }
    var c = new Object();
    c.city = $("#expectplace").val();
    c.workPattern = $("#workpattern").find("option:selected").text();
    c.expectJob = $("#expectjob").val();
    c.expectSalary = $("#salaryList").find(".placeholder").text();
    if (c.expectSalary == "期望薪水") {
        c.expectSalary = ""
    }
    c.jobHunterId = a;
    $.ajax({type: "post", url: "/page/demo/jobHunter/addexpect", data: c, dataType: "json", success: function (e) {
        if (e.retMsg == "success") {
            $.simplyToast("保存成功!", "success")
        } else {
            $.simplyToast("保存失败", "success")
        }
        $(".woo-main-body").data("jobHunterExpectId", e.last_insert_id)
    }}, "json")
}
function savespecialty(b, a) {
    var c = new Object();
    c.jobHunterSpecialty = $("#specialty").val();
    c.jobHunterId = a;
    $.ajax({type: "post", url: "/page/demo/jobHunter/jobhunterbasicw", data: c, dataType: "json", success: function (d) {
        if (d.retMsg == "success") {
            $.simplyToast("保存成功!", "success")
        } else {
            $.simplyToast("保存失败", "danger")
        }
    }}, "json")
}
function delWorkExp(c, b) {
    $(c).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (b != undefined) {
        var a = new Object();
        a.jobHunterWorkExperienceId = b;
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhunterworkexperienced", data: a, dataType: "json", success: function (d) {
            if (d.retMsg == "success") {
                $.simplyToast("删除成功!", "success")
            } else {
                $.simplyToast("删除失败", "fail")
            }
        }}, "json")
    }
}
function saveworkexperience(b, a) {
    var d = $(b).parents(".item");
    var c = new Object();
    c.employerName = d.find(".companyname").val();
    c.jobName = d.find(".jobName").val();
    c.beginTime = d.find(".begintime").val();
    c.endTime = d.find(".endtime").val();
    c.workDesc = d.find(".workdesc").val();
    c.salary = d.find(".salary").val();
    if (d.attr("data") == null || d.attr("data") == "") {
        c.jobHunterId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/addworkexperience", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success");
                changeBtnStatus(this, "work_experience_btn")
            } else {
                $.simplyToast("保存失败", "fail")
            }
            d.attr("data", e.last_insert_id)
        }}, "json")
    } else {
        c.jobHunterWorkExperienceId = d.attr("data");
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhunterworkexperiencew", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
        }}, "json")
    }
}
function addWorkExperience(a, c, b) {
    var e = "";
    if (a != undefined) {
        e = a.jobHunterWorkExperienceId
    }
    if (b == undefined) {
        b = (new Date()).getMilliseconds()
    }
    $("#workexperience").children().last().before('<div class="item marginb10" data="' + e + '">\n        <div class="form-group margint10">\n            <div class="row">\n                <label class="col-md-2 control-label">公司名称</label>\n                <input class="col-md-6 companyname">\n                </div>\n            </div>\n            <div class="form-group">\n                <div class="row">\n                    <label class="col-md-2 control-label">职位</label>\n                    <input class="col-md-6 jobname">\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">在职时间</label>\n\n                        <div class="form-inline">\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control begintime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="开始日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control endtime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="结束日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                        </div>\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">工作描述</label>\n\n                        <div class="col-md-10 padding0">\n                            <textarea placeholder="工作描述" class="valid textarea workdesc"></textarea>\n <div class="word_count">你还可以输入 <span>500</span> 字</div>                        </div>\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">月薪</label>\n                        <input class="col-md-6 salary">\n                        </div>\n                    </div>\n        <div class="row">\n            <div class="col-md-offset-9 col-md-3 paddingt10 paddingb10">\n                <ul class="list-inline">\n                    <li>\n                        <button class="btn btn-primary"\n                        type="submit" onclick=\'saveworkexperience(this, ' + c + ')\'>保存\n                        </button>\n                    </li>\n                    <li>\n                        <button class="btn btn-secondary" data-toggle="modal" data-target="#workexp' + b + '"\n                        type="submit">删除\n                        </button>\n                        <div class="modal fade" id="workexp' + b + '" tabindex="-1" role="dialog"\n                        aria-hidden="true">\n                            <div class="modal-dialog">\n                                <div class="modal-content">\n                                    <div class="modal-header">\n                                        <button type="button" class="close" data-dismiss="modal"><span\n                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>\n                                        </button>\n                                        <h4 class="modal-title fontem">选择</h4>\n                                    </div>\n                                    <div class="modal-body">\n                                        <p>确定要删除吗？</p>\n                                    </div>\n                                    <div class="modal-footer">\n                                        <button type="button" data-dismiss="modal" class="btn btn-primary"\n                                        onclick="delWorkExp(this, ' + e + ')">确定\n                                        </button>\n                                    </div>\n                                </div>\n                            </div>\n                        </div>\n                    </li>\n                </ul>\n            </div>\n        </div>\n                </div>');
    var d = $("#workexperience").children(".item").last();
    if (a != null) {
        d.find(".companyname").val(a.employerName);
        d.find(".jobname").val(a.jobName);
        setDateTime(d.find(".begintime"), a.beginTime);
        setDateTime(d.find(".endtime"), a.endTime);
        d.find(".workdesc").val(a.workDesc);
        d.find(".workdesc").next().find("span").text((500 - a.workDesc.length));
        d.find(".salary").val(a.salary)
    }
    hideErrors();
    bindTextareaCheck()
}
function bindTextareaCheck() {
    $("textarea").bind("input propertychange", function () {
        checkLength(this, 500);
        $(this).next().find("span").text((500 - this.value.length))
    })
}
function queryHunterWorkExperience(b) {
    var a = "/page/demo/jobHunter/jobhunterworkexperience?jobHunterId=" + b;
    $.post(a, null, function (e) {
        var d = e.job_hunter_work_experience_list;
        if (d != null && d.length > 0) {
            for (var c = 0; c < d.length; ++c) {
                addWorkExperience(d[c], d[c].jobHunterId, c)
            }
        }
    }, "json")
}
function delEduExp(c, a) {
    $(c).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (a != undefined) {
        var b = new Object();
        b.jobHunterEduExperienceId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntereduexperienced", data: b, dataType: "json", success: function (d) {
            if (d.retMsg == "success") {
                $.simplyToast("删除成功!", "success")
            } else {
                $.simplyToast("删除失败", "fail")
            }
        }}, "json")
    }
}
function saveeduexperience(b, a) {
    var d = $(b).parents(".item");
    var c = new Object();
    c.college = d.find(".college").val();
    c.major = d.find(".major").val();
    c.beginTime = d.find(".begintime").val();
    c.endTime = d.find(".endtime").val();
    c.bachelorDegree = d.find(".bachelorDegreeList .placeholder").text();
    if (d.attr("data") == null || d.attr("data") == "") {
        c.jobHunterId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/addedu", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
            d.attr("data", e.last_insert_id)
        }}, "json")
    } else {
        c.jobHunterEduExperienceId = d.attr("data");
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntereduexperiencew", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
        }}, "json")
    }
}
function addEduExperience(d, b, a) {
    var e = "";
    if (d != undefined) {
        e = d.jobHunterEduExperienceId
    }
    if (a == undefined) {
        a = (new Date()).getMilliseconds()
    }
    $("#eduexperience").children().last().before('<div class="item marginb10" data="' + e + '">\n        <div class="form-group margint10">\n            <div class="row">\n                <label class="col-md-2 control-label">学校名称</label>\n                <input class="col-md-6 college">\n                </div>\n            </div>\n            <div class="form-group">\n                <div class="row">\n                    <label class="col-md-2 control-label">专业</label>\n                    <input class="col-md-6 major">\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">就读时间</label>\n\n                        <div class="form-inline">\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control begintime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="开始日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control endtime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="结束日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                        </div>\n                    </div>\n                </div>\n    <div class="form-group">\n        <div class="row">\n            <div class="col-md-2 control-label"><em class="clrred">*</em> <label>学历</label></div>\n            <div class="col-md-6 padding0">\n                <div class="bachelorDegreeList btn-group woo-btn-dropdown">\n                    <button type="button" class="woo-check-drop-down btn btn-primary dropdown-toggle"\n                            data-toggle="dropdown" aria-expanded="false">\n                        <span class="placeholder">选择学历</span><span class="imageback downarrow size13"></span></button>\n                    <ul class="dropdown-menu" role="menu">\n                    </ul>\n                </div>\n                <div class="woo-error">请选择学历</div>\n            </div>\n        </div>\n    </div>        <div class="row">\n            <div class="col-md-offset-9 col-md-3 paddingt10 paddingb10">\n                <ul class="list-inline">\n                    <li>\n                        <button class="btn btn-primary"\n                        type="submit" onclick=\'saveeduexperience(this, ' + b + ')\'>保存\n                        </button>\n                    </li>\n                    <li>\n                        <button class="btn btn-secondary" data-toggle="modal" data-target="#eduexp' + a + '"\n                        type="submit">删除\n                        </button>\n                        <div class="modal fade" id="eduexp' + a + '" tabindex="-1" role="dialog"\n                        aria-hidden="true">\n                            <div class="modal-dialog">\n                                <div class="modal-content">\n                                    <div class="modal-header">\n                                        <button type="button" class="close" data-dismiss="modal"><span\n                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>\n                                        </button>\n                                        <h4 class="modal-title fontem">选择</h4>\n                                    </div>\n                                    <div class="modal-body">\n                                        <p>确定要删除吗？</p>\n                                    </div>\n                                    <div class="modal-footer">\n                                        <button type="button" data-dismiss="modal" class="btn btn-primary"\n                                        onclick="delEduExp(this, ' + e + ')">确定\n                                        </button>\n                                    </div>\n                                </div>\n                            </div>\n                        </div>\n                    </li>\n                </ul>\n            </div>\n        </div>\n                </div>');
    var c = $("#eduexperience").children(".item").last();
    if (d != null) {
        c.find(".college").val(d.college);
        c.find(".major").val(d.major);
        setDateTime(c.find(".begintime"), d.beginTime);
        setDateTime(c.find(".endtime"), d.endTime);
        c.find(".bachelorDegreeList .placeholder").text(d.bachelorDegree)
    }
    hideErrors();
    queryBachelorDegreeListByNode(c.find(".bachelorDegreeList"))
}
function queryHunterEduExperience(b) {
    var a = "/page/demo/jobHunter/jobhuntereduexperience?jobHunterId=" + b;
    $.post(a, null, function (d) {
        var e = d.job_hunter_edu_experience_list;
        if (e != null && e.length > 0) {
            for (var c = 0; c < e.length; ++c) {
                addEduExperience(e[c], e[c].jobHunterId, c)
            }
        }
    }, "json")
}
function delTrainExp(c, a) {
    $(c).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (a != undefined) {
        var b = new Object();
        b.jobHunterTrainExperienceId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntertrainexperienced", data: b, dataType: "json", success: function (d) {
            if (d.retMsg == "success") {
                $.simplyToast("删除成功!", "success")
            } else {
                $.simplyToast("删除失败", "fail")
            }
        }}, "json")
    }
}
function savetrainexperience(b, a) {
    var d = $(b).parents(".item");
    var c = new Object();
    c.courseName = d.find(".coursename").val();
    c.trainInstitutionName = d.find(".traininstitutionname").val();
    c.certificateName = d.find(".certificatename").val();
    c.trainBeginDate = d.find(".begintime").val();
    c.trainEndDate = d.find(".endtime").val();
    c.trainDesc = d.find(".traindesc").val();
    if (d.attr("data") == null || d.attr("data") == "") {
        c.jobHunterId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/addtrainexperience", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
            d.attr("data", e.last_insert_id)
        }}, "json")
    } else {
        c.jobHunterTrainExperienceId = d.attr("data");
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntertrainexperiencew", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
        }}, "json")
    }
}
function addTrainExperience(d, b, a) {
    var e = "";
    if (d != undefined) {
        e = d.jobHunterTrainExperienceId
    }
    if (a == undefined) {
        a = (new Date()).getMilliseconds()
    }
    $("#trainexperience").children().last().before('<div class="item marginb10" data="' + e + '">\n        <div class="form-group margint10">\n            <div class="row">\n                <label class="col-md-2 control-label">培训课程</label>\n                <input class="col-md-6 coursename">\n                </div>\n            </div>\n            <div class="form-group">\n                <div class="row">\n                    <label class="col-md-2 control-label">培训机构</label>\n                    <input class="col-md-6 traininstitutionname">\n                    </div>\n                </div>\n            <div class="form-group">\n                <div class="row">\n                    <label class="col-md-2 control-label">所获证书</label>\n                    <input class="col-md-6 certificatename">\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">培训时间</label>\n\n                        <div class="form-inline">\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control begintime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="开始日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control endtime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="结束日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                        </div>\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">培训描述</label>\n\n                        <div class="col-md-10 padding0">\n                            <textarea placeholder="培训描述" class="valid textarea traindesc"></textarea>\n <div class="word_count">你还可以输入 <span>500</span> 字</div>                        </div>\n                    </div>\n                </div>\n        <div class="row">\n            <div class="col-md-offset-9 col-md-3 paddingt10 paddingb10">\n                <ul class="list-inline">\n                    <li>\n                        <button class="btn btn-primary"                        type="submit" onclick=\'savetrainexperience(this, ' + b + ')\'>保存\n                        </button>\n                    </li>\n                    <li>\n                        <button class="btn btn-secondary" data-toggle="modal" data-target="#trainexp' + a + '"\n                        type="submit">删除\n                        </button>\n                        <div class="modal fade" id="trainexp' + a + '" tabindex="-1" role="dialog"\n                        aria-hidden="true">\n                            <div class="modal-dialog">\n                                <div class="modal-content">\n                                    <div class="modal-header">\n                                        <button type="button" class="close" data-dismiss="modal"><span\n                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>\n                                        </button>\n                                        <h4 class="modal-title fontem">选择</h4>\n                                    </div>\n                                    <div class="modal-body">\n                                        <p>确定要删除吗？</p>\n                                    </div>\n                                    <div class="modal-footer">\n                                        <button type="button" data-dismiss="modal" class="btn btn-primary"\n                                        onclick="delTrainExp(this, ' + e + ')">确定\n                                        </button>\n                                    </div>\n                                </div>\n                            </div>\n                        </div>\n                    </li>\n                </ul>\n            </div>\n        </div>\n                </div>');
    var c = $("#trainexperience").children(".item").last();
    if (d != null) {
        c.find(".coursename").val(d.courseName);
        c.find(".traininstitutionname").val(d.trainInstitutionName);
        setDateTime(c.find(".begintime"), d.trainBeginDate);
        setDateTime(c.find(".endtime"), d.trainEndDate);
        c.find(".traindesc").val(d.trainDesc)
    }
    hideErrors();
    bindTextareaCheck()
}
function queryHunterTrainExperience(b) {
    var a = "/page/demo/jobHunter/jobhuntertrainexperience?jobHunterId=" + b;
    $.post(a, null, function (e) {
        var d = e.job_hunter_train_experience_list;
        if (d != null && d.length > 0) {
            for (var c = 0; c < d.length; ++c) {
                addTrainExperience(d[c], d[c].jobHunterId, c)
            }
        }
    }, "json")
}
function delProduct(c, a) {
    $(c).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (a != undefined) {
        var b = new Object();
        b.jobHunterTrainExperienceId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntertrainexperienced", data: b, dataType: "json", success: function (d) {
            if (d.retMsg == "success") {
                $.simplyToast("删除成功!", "success")
            } else {
                $.simplyToast("删除失败", "fail")
            }
        }}, "json")
    }
}
function saveproduct(b, a) {
    var d = $(b).parents(".item");
    var c = new Object();
    c.courseName = d.find(".coursename").val();
    c.trainInstitutionName = d.find(".traininstitutionname").val();
    c.trainBeginDate = d.find(".begintime").val();
    c.trainEndDate = d.find(".endtime").val();
    c.trainDesc = d.find(".traindesc").val();
    if (d.attr("data") == null || d.attr("data") == "") {
        c.jobHunterId = a;
        $.ajax({type: "post", url: "/page/demo/jobHunter/addtrainexperience", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
            d.attr("data", e.last_insert_id)
        }}, "json")
    } else {
        c.jobHunterTrainExperienceId = d.attr("data");
        $.ajax({type: "post", url: "/page/demo/jobHunter/jobhuntertrainexperiencew", data: c, dataType: "json", success: function (e) {
            if (e.retMsg == "success") {
                $.simplyToast("保存成功!", "success")
            } else {
                $.simplyToast("保存失败", "fail")
            }
        }}, "json")
    }
}
function addProduct(d, b, a) {
    var e = "";
    if (d != undefined) {
        e = d.jobHunterTrainExperienceId
    }
    if (a == undefined) {
        a = (new Date()).getMilliseconds()
    }
    $("#trainexperience").children().last().before('<div class="item marginb10" data="' + e + '">\n        <div class="form-group margint10">\n            <div class="row">\n                <label class="col-md-2 control-label">培训课程</label>\n                <input class="col-md-6 coursename">\n                </div>\n            </div>\n            <div class="form-group">\n                <div class="row">\n                    <label class="col-md-2 control-label">培训机构</label>\n                    <input class="col-md-6 traininstitutionname">\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">培训时间</label>\n\n                        <div class="form-inline">\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control begintime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="开始日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                            <div class="col-md-3 input-group date datetimepicker-cls">\n                                <input type="text" class="woo-check-input form-control endtime"\n                                data-date-format="YYYY/MM/DD"\n                                placeholder="结束日期"/>\n                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>\n                            </div>\n                        </div>\n                    </div>\n                </div>\n                <div class="form-group">\n                    <div class="row">\n                        <label class="col-md-2 control-label">培训描述</label>\n\n                        <div class="col-md-10 padding0">\n                            <textarea placeholder="培训描述" class="valid textarea traindesc"></textarea>\n <div class="word_count">你还可以输入 <span>500</span> 字</div>                        </div>\n                    </div>\n                </div>\n        <div class="row">\n            <div class="col-md-offset-9 col-md-3 paddingt10 paddingb10">\n                <ul class="list-inline">\n                    <li>\n                        <button class="btn btn-primary"\n                        type="submit" onclick=\'savetrainexperience(this, ' + b + ')\'>保存\n                        </button>\n                    </li>\n                    <li>\n                        <button class="btn btn-secondary" data-toggle="modal" data-target="#trainexp' + a + '"\n                        type="submit">删除\n                        </button>\n                        <div class="modal fade" id="trainexp' + a + '" tabindex="-1" role="dialog"\n                        aria-hidden="true">\n                            <div class="modal-dialog">\n                                <div class="modal-content">\n                                    <div class="modal-header">\n                                        <button type="button" class="close" data-dismiss="modal"><span\n                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>\n                                        </button>\n                                        <h4 class="modal-title fontem">选择</h4>\n                                    </div>\n                                    <div class="modal-body">\n                                        <p>确定要删除吗？</p>\n                                    </div>\n                                    <div class="modal-footer">\n                                        <button type="button" data-dismiss="modal" class="btn btn-primary"\n                                        onclick="delTrainExp(this, ' + e + ')">确定\n                                        </button>\n                                    </div>\n                                </div>\n                            </div>\n                        </div>\n                    </li>\n                </ul>\n            </div>\n        </div>\n                </div>');
    var c = $("#trainexperience").children(".item").last();
    if (d != null) {
        c.find(".coursename").val(d.courseName);
        c.find(".traininstitutionname").val(d.trainInstitutionName);
        setDateTime(c.find(".begintime"), d.trainBeginDate);
        setDateTime(c.find(".endtime"), d.trainEndDate);
        c.find(".traindesc").val(d.trainDesc)
    }
    hideErrors();
    bindTextareaCheck()
}
function queryHunterdpict() {
    $("#majorId").val(regular(value.jobHunterMajor));
    $("#collegeId").val(regular(value.jobHunterCollege));
    $("#occupationId").val(regular(value.jobHunterLastOccupation));
    $("#employerId").val(regular(value.jobHunterLastEmployer));
    $("#photoPlusBtn").click(function () {
        plusPhoto("")
    });
    jsonData.jobHunterDepict = $("#selfProfile").val();
    jsonData.jobHunterBachelorDegree = $("#bachelorDegreeList").find(".placeholder").text();
    if (jsonData.jobHunterBachelorDegree == "选择当前学历") {
        jsonData.jobHunterBachelorDegree = ""
    }
    jsonData.jobHunterStartWorkYear = $("#startWorkYearList").find(".placeholder").text();
    if (jsonData.jobHunterStartWorkYear == "选择开始工作年份") {
        jsonData.jobHunterStartWorkYear = ""
    }
    jsonData.jobHunterMajor = $("#majorId").val();
    jsonData.jobHunterCollege = $("#collegeId").val();
    jsonData.jobHunterLastOccupation = $("#occupationId").val();
    jsonData.jobHunterLastEmployer = $("#employerId").val();
    jsonData.jobHunterSpecialty = getMultiSelect($("#woo-specialty-select-id"));
    if (jsonData.jobHunterSpecialty.indexOf("选择") > 0) {
        jsonData.jobHunterSpecialty = ""
    }
}
function importResume(a, c) {
    if ($("#importFileId").val() == "") {
        $.simplyToast("请选择htm或者mht文件", "danger");
        return
    }
    var b = new FormData();
    b.append("Filedata", $("#importFileId")[0].files[0]);
    b.append("type", $('input[name="resumeTypeRadio"]:checked').val());
    b.append("id", c);
    $.ajax({type: "POST", url: "/page/demo/importresume", data: b, processData: false, contentType: false, success: function (d) {
        var e = $.parseJSON(d);
        if (e.retCode == "fail") {
            $.simplyToast(e.retMsg, "danger")
        } else {
            $("body").removeClass("modal-open").css("padding-right", "inherit");
            $("#importResumeModal").removeClass("in");
            $("#importBtnId").text("已导入简历");
            $("#importBtnId").attr("disabled", true);
            $.simplyToast("成功导入简历", "success");
            queryHunterBasic(c);
            $.cookie("import_from_other", value.importFromOther, {expires: 1})
        }
    }})
}
function setSlide() {
    var d = [
        {$Duration: 1200, $Opacity: 2}
    ];
    var c = {$AutoPlay: true, $AutoPlaySteps: 1, $AutoPlayInterval: 3000, $PauseOnHover: 1, $ArrowKeyNavigation: true, $SlideDuration: 500, $MinDragOffsetToSlide: 20, $SlideWidth: 682, $SlideHeight: 300, $SlideSpacing: 0, $DisplayPieces: 1, $ParkingPosition: 0, $UISearchMode: 1, $PlayOrientation: 1, $DragOrientation: 3, $SlideshowOptions: {$Class: $JssorSlideshowRunner$, $Transitions: d, $TransitionsOrder: 1, $ShowLink: true}, $BulletNavigatorOptions: {$Class: $JssorBulletNavigator$, $ChanceToShow: 2, $AutoCenter: 1, $Steps: 1, $Lanes: 1, $SpacingX: 10, $SpacingY: 10, $Orientation: 1}, $ArrowNavigatorOptions: {$Class: $JssorArrowNavigator$, $ChanceToShow: 2, $Steps: 1}};
    var a = new $JssorSlider$("slider1_container", c);

    function b() {
        var e = a.$Elmt.parentNode.clientWidth;
        if (e) {
            a.$ScaleWidth(Math.min(e, 682))
        } else {
            window.setTimeout(b, 30)
        }
    }

    b();
    $(window).bind("load", b);
    $(window).bind("resize", b);
    $(window).bind("orientationchange", b)
}
var searchCity = null;
var searchClassPropertyId = null;
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
    var b = new Date();
    b.setDate(b.getDate() - 1);
    var a = "/page/demo/employer/hotemployers";
    $.post(a, null, function (h) {
        var g = h.employer_hot;
        $("#newsList").html("");
        for (var e = 0; e < Math.min(g.length, 10); e++) {
            var f = "";
            if (g[e].logoPath == undefined) {
                f = "/img/companydefault.png"
            } else {
                f = g[e].logoPath
            }
            var d = "";
            if (e < 3) {
                d = "clrred"
            }
            var c = '<li class="marginb10"><a class="' + d + '" href="/jsp/employer/employer_detail_r.jsp?employerId=' + g[e].employerId + '" target="_blank">' + g[e].employerName + "</a></li>";
            $("#newsList").append(c)
        }
    }, "json")
}
function addHotTimes(e, d, c, a) {
    var b = {employerId: e, employerName: d, logoPath: c, jobId: a};
    $.ajax({type: "POST", dataType: "json", data: $.param(b), url: "/page/demo/employer/addtimes", success: function (f) {
    }});
    window.open("/jsp/job/job_detail_r.jsp?jobId=" + a, "_blank")
}
function getQqSection(a) {
    return"<a class='label label-warning clrfff marginl10' href='tencent://Message/?Uin=" + a + "&websiteName=q-zone.qq.com&Menu=yes'>QQ咨询</a>"
}
function setJobList(a) {
    $.post(a, null, function (g) {
        var m = g.job_list;
        $("#pagination").html(g.paginationInfo);
        $("#tableList").html("");
        if (m != null && m.length > 0) {
            var n = null;
            for (var h = 0; h < m.length; h++) {
                var r = "";
                if (m[h].logoPath == undefined) {
                    r = "/img/companydefault.png"
                } else {
                    r = m[h].logoPath
                }
                var p = 'addHotTimes("' + m[h].employerId + '","' + m[h].employerName + '","' + r + '","' + m[h].jobId + '")';
                var e = '<li><ul class="list-inline">';
                if (m[h].tag1 != null && m[h].tag1 != "") {
                    e = e + '<li class="tag2"><span>' + m[h].tag1 + "</span></li>"
                }
                if (m[h].tag2 != null && m[h].tag2 != "") {
                    e = e + '<li class="tag2"><span>' + m[h].tag2 + "</span></li>"
                }
                if (m[h].tag3 != null && m[h].tag3 != "") {
                    e = e + '<li class="tag2"><span>' + m[h].tag3 + "</span></li>"
                }
                if (m[h].tag4 != null && m[h].tag4 != "") {
                    e = e + '<li class="tag2"><span>' + m[h].tag4 + "</span></li>"
                }
                if (m[h].tag5 != null && m[h].tag5 != "") {
                    e = e + '<li class="tag2"><span>' + m[h].tag5 + "</span></li>"
                }
                e = e + "</ul></li>";
                var q = "";
                if (m[h].questionnaireId != null && m[h].questionnaireOn == 1) {
                    q = '<i class="fa fa-edit marginl10"></i>'
                }
                var j = m[h].updateTime;
                if (j != null && j.length > 10) {
                    j = j.substr(0, 10)
                }
                var d = "";
                if (m[h].district == undefined || m[h].district === "") {
                    d = m[h].city
                } else {
                    d = m[h].city + "-" + m[h].district
                }
                var l = '<div class="box viewport-flip" title="点击翻面"><div class="list flip in a"><img class="circle sizew64 sizeh64" src="' + r + '" alt="' + m[h].employerName + '" /></div></div>';
                var b = "";
                if (regular(m[h].website) != "" && m[h].websiteShow == 1) {
                    if (m[h].website.indexOf("http") < 0 && m[h].website.indexOf("https:") < 0) {
                        m[h].website = "http://" + m[h].website
                    }
                    b = "<a target='_blank' href='" + m[h].website + "' class='label label-warning clrfff'>店铺</a>"
                }
                var f = "";
                if (m[h].qq != 0 && m[h].qq != null && !isMobile()) {
                    f = getQqSection(m[h].qq)
                }
                var o = salaryVal(m[h].salaryBegin, m[h].salaryEnd);
                var k = '<div class="woolistitem blockbackground " ><div class="row"><div class="hidden-sm hidden-xs col-md-2"><a class="media-left" target=\'_blank\' href=\'/jsp/employer/employer_detail_r.jsp?employerId=' + m[h].employerId + "' >" + l + '</a></div><div class="col-lg-5 col-md-5 col-sm-7 col-xs-12 job-title"><h5><a class="font16 clr440062 letterspace1 fontem"  href=\'javascript:void(0);\' onclick=\'' + p + "'>" + m[h].jobName + q + "</a></h5><ul class=\"list-inline\"><li class='clr440062'><i class='fa fa-credit-card'></i> </li><li><a href=\"javascript:;\"> " + m[h].employerName + '</a></li></ul><ul class="list-unstyled">' + e + '</ul></div><div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 job-location"><h5 class=\'clrec6941 font15 fontem\'><i class="fa fa-cny clr440062"></i>月薪&nbsp;' + o + '</h5><p><i class="fa fa-map-marker clr440062"></i>' + d + '</p><p><ul class="list-inline"><li>' + b + "</li><li>" + f + '</li></ul> </p></div></div><div class=\'list-bottom-right\'><ul class="list-inline clra6a6a6"><li><i class="fa fa-comment-o"></i><span>' + (m[h].jobCommentCnt == undefined ? 0 : m[h].jobCommentCnt) + '</span></li><li><i class="fa fa-thumbs-o-up"></i><span>' + (m[h].jobPraisedCnt == undefined ? 0 : m[h].jobPraisedCnt) + "</span></li> </ul></div></div>";
                var c = document.createElement("li");
                c.className = "list-group-item";
                c.innerHTML = k;
                $("#tableList")[0].appendChild(c);
                queryAssess(m[h].jobId, $(c).find(".star"))
            }
        }
        $("#spinerId").css("visibility", "hidden")
    }, "json")
}
function queryJob(a) {
    $("#spinerId").css("visibility", "inherit");
    var c = encodeURI(getData(a)).replace(/\+/g, "%2B");
    var b = "/page/demo/job/jobpage" + c;
    setJobList(b)
}
function queryMainPageJob(a) {
    $("#spinerId").css("visibility", "inherit");
    var c = encodeURI(getData(a)).replace(/\+/g, "%2B");
    var b = "/page/demo/job/jobpagemainpage" + c;
    setJobList(b)
}
function queryRedu() {
    searchReduType = 1;
    $("#zonghe").removeClass("hint");
    $("#redu").addClass("hint");
    queryJob(1)
}
function queryZonghe() {
    searchReduType = 0;
    $("#redu").removeClass("hint");
    $("#zonghe").addClass("hint");
    queryJob(1)
}
function getData(a) {
    if (a == undefined || a == null || a == "") {
        a = 1
    }
    var b = "?pageIndex=" + a;
    var c = $("#searchName").val();
    if (c != null && c != "") {
        b = b + "&keyWord=" + c
    }
    if (searchCity != null && searchCity != "" && searchCity != "全国") {
        b = b + "&workplace=" + searchCity
    }
    if (searchClassPropertyId != null && searchClassPropertyId != "" && searchClassPropertyId != undefined) {
        b = b + "&jobMainType=" + searchClassPropertyId
    }
    if (searchNameType != null && searchNameType != "" && searchNameType != undefined) {
        b = b + "&keyWordType=" + searchNameType
    }
    if (searchSaleType != null && searchSaleType != "" && searchSaleType != undefined) {
        b = b + "&saleType=" + searchSaleType
    }
    if (searchEduReq != null && searchEduReq != "" && searchEduReq != undefined) {
        b = b + "&eduReq=" + searchEduReq
    }
    if (searchExpReq != null && searchExpReq != "" && searchExpReq != undefined) {
        b = b + "&expReq=" + searchExpReq
    }
    if (searchEmployerScale != null && searchEmployerScale != "" && searchEmployerScale != undefined) {
        b = b + "&employerScale=" + searchEmployerScale
    }
    if (searchWorkPattern != null && searchWorkPattern != "" && searchWorkPattern != undefined) {
        b = b + "&workPattern=" + searchWorkPattern
    }
    if (searchSalaryBegin != null && searchSalaryBegin != "" && searchSalaryBegin != undefined) {
        b = b + "&salaryBegin=" + searchSalaryBegin
    }
    if (searchSalaryEnd != null && searchSalaryEnd != "" && searchSalaryEnd != undefined) {
        b = b + "&salaryEnd=" + searchSalaryEnd
    }
    if (regular(searchReduType) != "" && searchReduType != 0) {
        b = b + "&redu=" + searchReduType
    }
    return b
}
function showInPage(a) {
    if (a == undefined) {
        return""
    }
    return a
}
function initSearchCondition() {
    initSearchConditionZhiWei();
    $.post("/page/demo/dic/cityMainPage", null, function (e) {
        var d = e.region_list;
        var b = "<option>全国</option>";
        for (var c = 0; c < d.length; c++) {
            b += "<option>" + d[c].regionName + "</option>"
        }
        $("#workPlace").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=11";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryXinZhi1">薪资:</li></div><div class="col-md-10">';
        for (var c = 0; c < d.length; c++) {
            if (d[c].classThresholdBegin === 0) {
                b = b + '<li><a href="javascript:;" onclick="queryXinZhi(this)" value="1">' + d[c].classThresholdEnd + "元以下</a></li>"
            } else {
                if (d[c].classThresholdEnd === "" || d[c].classThresholdEnd == undefined) {
                    b = b + '<li><a href="javascript:;" onclick="queryXinZhi(this)">' + d[c].classThresholdBegin + "元以上</a></li>"
                } else {
                    b = b + '<li><a href="javascript:;" onclick="queryXinZhi(this)">' + d[c].classThresholdBegin + "-" + d[c].classThresholdEnd + "元</a></li>"
                }
            }
        }
        b += "</div></div>";
        $("#queryXinZhi2").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=15";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="querySaleType1">热门行业:</li></div><div class="col-md-10">';
        for (var c = 0; c < d.length; c++) {
            b = b + '<li><a href="javascript:;" onclick="querySaleType(this)">' + d[c].classPropertyName + "</a></li>"
        }
        b += "</div></div>";
        $("#querySaleType2").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=19";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = "<option>不限</option>";
        for (var c = 0; c < d.length; c++) {
            if (d[c].classThresholdEnd === "" || d[c].classThresholdEnd == undefined) {
                b += "<option>" + d[c].classThresholdBegin + "人以上</option>"
            } else {
                b += "<option>" + d[c].classThresholdBegin + "-" + d[c].classThresholdEnd + "人</option>"
            }
        }
        $("#employerscaleInfo").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=18";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryGongzuo1">工作方式:</li></div><div class="col-md-10">';
        for (var c = 0; c < d.length; c++) {
            b = b + '<li><a href="javascript:;" onclick="queryGongzuo(this)">' + d[c].classPropertyName + "</a></li>"
        }
        b += "</div></div>";
        $("#queryGongzuo2").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=16";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryXueli1">学历:</li></div><div class="col-md-10">';
        for (var c = 0; c < d.length; c++) {
            b = b + '<li><a href="javascript:;" onclick="queryXueli(this)">' + d[c].classPropertyName + "</a></li>"
        }
        b += "</div></div>";
        $("#queryXueli2").html(b)
    }, "json");
    var a = "/page/demo/dic/common?classId=17";
    $.post(a, null, function (e) {
        var d = e.common_dictionary_list;
        var b = "<option>不限</option>";
        for (var c = 0; c < d.length; c++) {
            if (d[c].classThresholdBegin === 0) {
                b += "<option>" + d[c].classThresholdEnd + "年以下</option>"
            } else {
                if (d[c].classThresholdEnd === "" || d[c].classThresholdEnd == undefined) {
                    b += "<option>" + d[c].classThresholdBegin + "年以上</option>"
                } else {
                    b += "<option>" + d[c].classThresholdBegin + "-" + d[c].classThresholdEnd + "年</option>"
                }
            }
        }
        $("#nianxianInfo").html(b)
    }, "json")
}
function initSearchConditionZhiWei(b) {
    var a = "";
    if (b == undefined) {
        a = "/page/demo/dic/commonWithCodition1?classId=10";
        searchClassPropertyId = null
    } else {
        a = "/page/demo/dic/commonWithCodition2?parentClassId=" + b
    }
    $.post(a, null, function (f) {
        var e = f.common_dictionary_list;
        if (e == undefined || e.length == 0) {
            $("#queryZhiWei1").hide();
            $("#queryZhiWei2").hide()
        } else {
            var g = "职位分类:";
            if (b != undefined) {
                g = e[0].classTitleName + ":"
            }
            var c = '<div class="row"><div class="col-md-2"><li class="woo-class-title" id="queryZhiWei1">' + g + '</li></div><div class="col-md-10">';
            for (var d = 0; d < e.length; d++) {
                c = c + '<li><a href="javascript:;" onclick="queryZhiWei(this)" id="' + e[d].classPropertyId + '">' + e[d].classPropertyName + "</a></li>"
            }
            c += "</div></div>";
            $("#queryZhiWei2").html(c);
            $("#queryZhiWei1").show();
            $("#queryZhiWei2").show()
        }
    }, "json")
}
function queryAll() {
    initSearchConditionZhiWei();
    $("#queryZhiWei1").show();
    $("#queryZhiWei2").show();
    $("#allZhiWeiCondition1").html("");
    $("#allZhiWeiCondition2").find("a").click();
    queryJob(1)
}
function queryZhiWei(b) {
    var c = $(b).attr("id");
    initSearchConditionZhiWei(c);
    var a = '<a href="javascript:;" class=\'solidborder1 paddingl4r4 verticalmiddle\' id="' + c + '" onclick="notQueryZhiWei(this)">' + $(b).html() + "</a> ";
    $("#allZhiWeiCondition1").append(a);
    searchClassPropertyId = $(b).html();
    queryJob(1)
}
function notQueryZhiWei(a) {
    var b = $(a).attr("id");
    $("#allZhiWeiCondition1").find("a").each(function () {
        var c = $(this).attr("id");
        if (c != undefined && c.length > b.length) {
            $(this).remove()
        }
    });
    $("#allZhiWeiCondition2").find("a").click();
    initSearchConditionZhiWei(b);
    searchClassPropertyId = null;
    queryJob(1)
}
function queryXinZhi(a) {
    searchSalaryBegin = $("#salaryBegin").val();
    searchSalaryEnd = $("#salaryEnd").val();
    queryJob(1)
}
function querySaleType(b) {
    var a = '<a href="javascript:;" class="tag sizew120 verticalmiddle" onclick="notquerySaleType(this)">' + $(b).html() + "</a>";
    $("#allZhiWeiCondition2").append(a);
    $("#querySaleType1").hide();
    $("#querySaleType2").hide();
    searchSaleType = $(b).html();
    queryJob(1)
}
function notquerySaleType(a) {
    $(a).remove();
    $("#querySaleType1").show();
    $("#querySaleType2").show();
    searchSaleType = null;
    queryJob(1)
}
$("#employerscaleInfo").change(function () {
    searchEmployerScale = $(this).children("option:selected").val();
    if (searchEmployerScale == "不限") {
        searchEmployerScale = null
    }
    queryJob(1)
});
function queryGongzuo(b) {
    var a = '<a href="javascript:;" class="tag sizew120 verticalmiddle" onclick="notQueryGongzuo(this)">' + $(b).html() + "</a>";
    $("#allZhiWeiCondition2").append(a);
    $("#queryGongzuo1").hide();
    $("#queryGongzuo2").hide();
    searchWorkPattern = $(b).html();
    queryJob(1)
}
function notQueryGongzuo(a) {
    $(a).remove();
    $("#queryGongzuo1").show();
    $("#queryGongzuo2").show();
    searchWorkPattern = null;
    queryJob(1)
}
function queryXueli(b) {
    var a = '<a href="javascript:;" class="tag sizew120 verticalmiddle" onclick="notQueryXueli(this)">' + $(b).html() + "</a>";
    $("#allZhiWeiCondition2").append(a);
    $("#queryXueli1").hide();
    $("#queryXueli2").hide();
    searchEduReq = $(b).html();
    queryJob(1)
}
function notQueryXueli(a) {
    $(a).remove();
    $("#queryXueli1").show();
    $("#queryXueli2").show();
    searchEduReq = null;
    queryJob(1)
}
function bindNianXian() {
    $("#nianxianInfo").find("li").find("a").each(function (a) {
        $(this).bind("click", function () {
            $("#nianxianInfo").find("li").find("a").attr("class", "");
            $(this).attr("class", "woo-select-now");
            searchExpReq = $(th).html();
            queryJob(1)
        })
    })
}
$("#nianxianInfo").change(function () {
    searchExpReq = $(this).children("option:selected").val();
    if (searchExpReq == "不限") {
        searchExpReq = null
    }
    queryJob(1)
});
$("#workPlace").change(function () {
    searchCity = $(this).children("option:selected").val();
    if (searchCity == "不限") {
        searchCity = null
    }
    queryJob(1)
});
function queryReChi(a) {
    $("#searchName").val($(a).attr("value"));
    queryJob(1)
}
function queryKeyWord() {
    queryJob(1)
}
function setSearchNameType1() {
    searchNameType = "1"
}
function setSearchNameType2() {
    searchNameType = "2"
}
function queryAssess(a, c) {
    var b = new Object();
    b.jobId = a;
    $.ajax({type: "POST", dataType: "json", data: $.param(b), url: "/page/demo/job/jobassessr", success: function (e) {
        if (e.job_assess != null) {
            var m = 0;
            var h = 0;
            for (var f = 0; f < e.job_assess.length; ++f) {
                m += e.job_assess[f].interviewerNice;
                h += e.job_assess[f].descriptionMatch
            }
            var g = parseInt((m + h) / (2 * e.job_assess.length));
            var k = "<li class='font14'><span class='fa fa-star'></span></li>";
            var n = "<li class='font14'><span class='fa fa-star-half-o'></span></li>";
            var l = "";
            for (var d = 0; d < g / 20; ++d) {
                l += k
            }
            if (g % 20 > 10) {
                l += n
            }
            c.html(l)
        }
    }})
}
function queryExperienceYearList() {
    $.post("/page/demo/dic/common?classId=17", null, function (a) {
        setexprienceList(a, "experienceYearList", 1)
    }, "json")
}
function setexprienceList(e, f, c) {
    $("#" + f + " ul").html("");
    var d = e.common_dictionary_list;
    if (c == 1) {
        var a = '<li role="presentation" value="' + (d[0].classPropertyId - 1) + '"> <a role="menuitem" tabindex="1"" href="javascript:void(0);">选择</a></li>';
        $("#" + f + " ul").append(a)
    }
    for (var b = 0; b < d.length; b++) {
        if (d[b].classThresholdBegin === 0) {
            a = '<li role="presentation" value="' + d[b].classPropertyId + '">             <a role="menuitem" tabindex="' + (b + 2) + '" href="javascript:void(0);">' + d[b].classThresholdEnd + "年以下</a>         </li>"
        } else {
            if (d[b].classThresholdEnd === "" || d[b].classThresholdEnd == undefined) {
                a = '<li role="presentation" value="' + d[b].classPropertyId + '">             <a role="menuitem" tabindex="' + (b + 2) + '" href="javascript:void(0);">' + d[b].classThresholdBegin + "年以上</a>         </li>"
            } else {
                a = '<li role="presentation" value="' + d[b].classPropertyId + '">             <a role="menuitem" tabindex="' + (b + 2) + '" href="javascript:void(0);">' + d[b].classThresholdBegin + "-" + d[b].classThresholdEnd + "年</a>         </li>"
            }
        }
        $("#" + f + " ul").append(a)
    }
    new customDropDown($("#" + f), null)
}
function queryStartWorkYearList() {
    $.post("/page/demo/dic/common?classId=21", null, function (d) {
        var c = d.common_dictionary_list;
        $("#startWorkYearList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#startWorkYearList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '<li role="presentation" value="' + c[b].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classPropertyName + "</a>         </li>";
            $("#startWorkYearList ul").append(a)
        }
        new customDropDown($("#startWorkYearList"), null)
    }, "json")
}
function queryBachelorDegreeList() {
    $.post("/page/demo/dic/common?classId=16", null, function (d) {
        var c = d.common_dictionary_list;
        $("#bachelorDegreeList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#bachelorDegreeList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '<li role="presentation" value="' + c[b].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classPropertyName + "</a>         </li>";
            $("#bachelorDegreeList ul").append(a)
        }
        new customDropDown($("#bachelorDegreeList"), null)
    }, "json")
}
function queryBachelorDegreeListByNode(a) {
    $.post("/page/demo/dic/common?classId=16", null, function (e) {
        var d = e.common_dictionary_list;
        a.find("ul").html("");
        for (var c = 0; c < d.length; c++) {
            var b = '           <li role="presentation" value="' + d[c].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (c + 1) + '" href="javascript:void(0);">' + d[c].classPropertyName + "</a>         </li>";
            a.find("ul").append(b)
        }
        new customDropDown(a, null)
    }, "json")
}
function queryWorkPattern() {
    $.post("/page/demo/dic/common?classId=18", null, function (d) {
        $("#workPatternList ul").html("");
        var c = d.common_dictionary_list;
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#workPatternList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '<li role="presentation" value="' + c[b].classPropertyId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classPropertyName + "</a>         </li>";
            $("#workPatternList ul").append(a)
        }
        new customDropDown($("#workPatternList"), null)
    }, "json")
}
function queryCurrentStatusList() {
    $.post("/page/demo/dic/currentstatus", null, function (d) {
        var c = d.current_status_list;
        $("#currentStatusList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#currentStatusList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '           <li role="presentation" value="' + c[b].jobHunterCurrentStatusId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].status + "</a>         </li>";
            $("#currentStatusList ul").append(a)
        }
        new customDropDown($("#currentStatusList"), null)
    }, "json")
}
function queryProvince() {
    $.post("/page/demo/dic/province", null, function (f) {
        var e = f.region_list;
        $("#provinceList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#provinceList ul").append(a);
        for (var c = 0; c < e.length; c++) {
            a = '           <li role="presentation" value="' + e[c].regionCode + '">             <a role="menuitem" tabindex="' + (c + 1) + '" href="javascript:void(0);">' + e[c].regionName + "</a>         </li>";
            $("#provinceList ul").append(a)
        }
        new customDropDown($("#provinceList"), selectProvince);
        var b = $("#provinceList").find(".placeholder").text();
        var d = $("#provinceList ul > li:has(> a:contains(" + b + "))").attr("value");
        if (d != null && d != "") {
            queryCity(d.substring(0, 2))
        }
    }, "json")
}
function selectProvince(a) {
    if (a.text.indexOf("选择") < 0) {
        queryCity(a.val.substring(0, 2))
    }
    $("#cityList").find(".placeholder").text("选择城市");
    $("#areaList").find(".placeholder").text("选择地区");
    $("#areaList ul").html("")
}
function queryCity(a) {
    $.post("/page/demo/dic/city?provinceCode=" + a, null, function (g) {
        var f = g.region_list;
        $("#cityList ul").html("");
        var b;
        b = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#cityList ul").append(b);
        for (var d = 0; d < f.length; d++) {
            b = '           <li role="presentation" value="' + f[d].regionCode + '">             <a role="menuitem" tabindex="' + (d + 1) + '" href="javascript:void(0);">' + f[d].regionName + "</a>         </li>";
            $("#cityList ul").append(b)
        }
        new customDropDown($("#cityList"), selectCity);
        var c = $("#cityList").find(".placeholder").text();
        var e = $("#cityList ul > li:has(> a:contains(" + c + "))").attr("value");
        if (e != null && e != "") {
            queryArea(e.substring(0, 4))
        }
    }, "json")
}
function selectCity(a) {
    if (a.text.indexOf("选择") < 0) {
        queryArea(a.val.substring(0, 4))
    }
    $("#areaList").find(".placeholder").text("选择地区")
}
function queryArea(a) {
    $.post("/page/demo/dic/area?cityCode=" + a, null, function (e) {
        var d = e.region_list;
        $("#areaList ul").html("");
        var b;
        b = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#areaList ul").append(b);
        for (var c = 0; c < d.length; c++) {
            b = '           <li role="presentation" value="' + d[c].regionCode + '">             <a role="menuitem" tabindex="' + (c + 1) + '" href="javascript:void(0);">' + d[c].regionName + "</a>         </li>";
            $("#areaList ul").append(b)
        }
        new customDropDown($("#areaList"), null)
    }, "json")
}
function queryCreditList() {
    $.post("/page/demo/dic/common?classId=12", null, function (d) {
        var c = d.common_dictionary_list;
        $("#creditList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#creditList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '<li role="presentation" value="' + c[b].jobSearchClassId + '"><a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classPropertyName + "</a>         </li>";
            $("#creditList ul").append(a)
        }
        new customDropDown($("#creditList"), null)
    }, "json")
}
function queryScaleList() {
    $.post("/page/demo/dic/common?classId=19", null, function (d) {
        var c = d.common_dictionary_list;
        $("#scaleList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#scaleList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            if (c[b].classThresholdEnd === "" || c[b].classThresholdEnd == undefined) {
                a = '           <li role="presentation" value="' + c[b].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classThresholdBegin + "人以上</a>         </li>"
            } else {
                var a = '           <li role="presentation" value="' + c[b].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classThresholdBegin + "-" + c[b].classThresholdEnd + "人</a>         </li>"
            }
            $("#scaleList ul").append(a)
        }
        new customDropDown($("#scaleList"), null)
    }, "json")
}
function querySaleTypeList() {
    $.post("/page/demo/dic/common?classId=15", null, function (d) {
        var c = d.common_dictionary_list;
        $("#saleTypeList ul").html("");
        var a;
        a = '<li role="presentation" > <a href="javascript:void(0);">选择</a></li>';
        $("#saleTypeList ul").append(a);
        for (var b = 0; b < c.length; b++) {
            a = '           <li role="presentation" value="' + c[b].jobSearchClassId + '">             <a role="menuitem" tabindex="' + (b + 1) + '" href="javascript:void(0);">' + c[b].classPropertyName + "</a>         </li>";
            $("#saleTypeList ul").append(a)
        }
        new customDropDown($("#saleTypeList"), null)
    }, "json")
}
function querySalaryList() {
    $.post("/page/demo/dic/common?classId=11", null, function (c) {
        var b = c.common_dictionary_list;
        $("#salaryList ul").html("");
        for (var a = 0; a < b.length; a++) {
            if (b[a].classThresholdBegin === 0) {
                liInfo = '<li role="presentation" value="' + b[a].classPropertyId + '">             <a role="menuitem" tabindex="' + (a + 2) + '" href="javascript:void(0);">' + b[a].classThresholdEnd + "元以下</a>         </li>"
            } else {
                if (b[a].classThresholdEnd === "" || b[a].classThresholdEnd == undefined) {
                    liInfo = '<li role="presentation" value="' + b[a].classPropertyId + '">             <a role="menuitem" tabindex="' + (a + 2) + '" href="javascript:void(0);">' + b[a].classThresholdBegin + "元以上</a>         </li>"
                } else {
                    liInfo = '<li role="presentation" value="' + b[a].classPropertyId + '">             <a role="menuitem" tabindex="' + (a + 2) + '" href="javascript:void(0);">' + b[a].classThresholdBegin + "-" + b[a].classThresholdEnd + "元</a>         </li>"
                }
            }
            $("#salaryList ul").append(liInfo)
        }
        liInfo = '<li role="presentation" value="">             <a role="menuitem" tabindex="' + (b.length + 2) + '" href="javascript:void(0);">面议</a>         </li>';
        $("#salaryList ul").append(liInfo);
        new customDropDown($("#salaryList"), null)
    }, "json")
}
function queryzhiweiMain() {
    $.post("/page/demo/dic/commonWithCodition1?classId=10", null, function (f) {
        $("#zhiweiMainList ul").html("");
        var e = f.common_dictionary_list;
        for (var c = 0; c < e.length; c++) {
            var a = '           <li role="presentation" value="' + e[c].classPropertyId + '">             <a role="menuitem" tabindex="' + (c + 1) + '" href="javascript:void(0);">' + e[c].classPropertyName + "</a>         </li>";
            $("#zhiweiMainList ul").append(a)
        }
        var d = $("#zhiweiMainList").find(".placeholder").text();
        var b = $("#zhiweiMainList ul > li:has(> a:contains(" + d + "))").attr("value");
        if (b != null && b != "") {
            queryzhiweiSub(b)
        }
        new customDropDown($("#zhiweiMainList"), selectZhiweiMain)
    }, "json")
}
function queryzhiweiSub(a) {
    $.post("/page/demo/dic/commonWithCodition2?parentClassId=" + a, null, function (e) {
        $("#zhiweiSubList ul").html("");
        var d = e.common_dictionary_list;
        if (d == null) {
            $("#zhiweiSubList ul").append('<li><a href="javascript:void(0);">无</a></li>');
            return
        }
        for (var c = 0; c < d.length; c++) {
            var b = '           <li role="presentation" value="' + d[c].classPropertyId + '">             <a role="menuitem" tabindex="' + (c + 1) + '" href="javascript:void(0);">' + d[c].classPropertyName + "</a>         </li>";
            $("#zhiweiSubList ul").append(b)
        }
        new customDropDown($("#zhiweiSubList"), null)
    }, "json")
}
function enableMultiSelect(b, a) {
    b.multiselect();
    a.on("click", function () {
        b.find("option:selected").each(function () {
            $(this).prop("selected", false)
        });
        b.multiselect("refresh")
    })
}
function setMultiSelect(c, b) {
    if (b != null && b != "") {
        var a = b.split(",");
        for (var d in a) {
            c.find("option:contains(" + a[d].trim() + ")").each(function () {
                $(this).prop("selected", true)
            })
        }
    } else {
        c.find("option:selected").each(function () {
            $(this).prop("selected", false)
        })
    }
    c.multiselect("refresh")
}
function getMultiSelect(a) {
    return a.next().find("button").attr("title")
}
function queryMultiJob() {
    $.post("/page/demo/dic/common?classId=10", null, function (f) {
        var e = f.common_dictionary_list;
        var b = $("#expectjobcondition");
        var a = "";
        b.html("");
        var d = "";
        if (e != null) {
            for (var c = 0; c < e.length; c++) {
                if (e[c].haveSubClassProperty == 1) {
                    d = ' <div class="conditionitem clearfix"><div class="row"><div class="col-md-2"><li class="woo-class-title" data="' + e[c].classPropertyId + '">' + e[c].classPropertyName + ":</li></div>";
                    $.ajax({url: "/page/demo/dic/commonWithCodition2?parentClassId=" + e[c].classPropertyId, async: false, dataType: "json", success: function (j) {
                        d += ' <div class="col-md-10">';
                        var h = j.common_dictionary_list;
                        for (var g = 0; g < h.length; ++g) {
                            d += '<li><a href="javascript:;" onclick="addselect($(\'#selectexpectjobconditon\'), this)" data="' + h[g].classPropertyId + '">' + h[g].classPropertyName + "</a></li>"
                        }
                        b.append(d + "</div> </div> </div>");
                        d = ""
                    }})
                } else {
                    if (e[c].parentClassPropertyId == undefined) {
                        a += '<li><a href="javascript:;" onclick="addselect($(\'#selectexpectjobconditon\'), this)" data="' + e[c].classPropertyId + '">' + e[c].classPropertyName + "</a></li>"
                    }
                }
            }
            b.append(' <div class="conditionitem clearfix"><div class="row"><div class="col-md-2"><li class="woo-class-title">其他:</li></div><div class="col-md-10">' + a + "</div> </div> </div>")
        }
    }, "json")
}
function addselect(b, a) {
    var c = false;
    var d = $(a).text();
    $(a).css("color", "#a1a1a1");
    b.find("a").each(function () {
        if (d == $(this).text()) {
            c = true
        }
    });
    if (!c) {
        b.append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"onclick="removeselect(this)">' + d + "</a> </li>")
    }
}
function removeselect(a) {
    $(a).parent().remove()
}
function queryMultiCity() {
    $.post("/page/demo/dic/cityList", null, function (e) {
        var d = e.region_list;
        var b = $("#expectplacecondition");
        b.html("");
        if (d != null) {
            for (var c = 0; c < d.length; c++) {
                if (d[c].regionCode % 10000 == 0) {
                    b.append(' <div class="conditionitem clearfix"><div class="row"><div class="col-md-2"><li class="woo-class-title" data="' + d[c].regionCode / 10000 + '">' + d[c].regionName + ':</li></div><div class="col-md-10"></div></div></div>')
                } else {
                    var a = b.find("li[data=" + parseInt(d[c].regionCode / 10000) + "]");
                    a.parent().next().append(' <li><a href="javascript:;" onclick="addselect($(\'#selectexpectplaceconditon\'), this)" data="' + d[c].regionCode + '">' + d[c].regionName + "</a></li>")
                }
            }
        }
    }, "json")
}
function queryMultiSpecialty() {
    $.post("/page/demo/dic/common?classId=20", null, function (e) {
        var d = e.common_dictionary_list;
        var b = $("#specialitycondition");
        b.html("");
        if (d != null) {
            for (var c = 0; c < d.length; c++) {
                var a = b.find("li[data=" + d[c].classTitleName + "]");
                if (a.length == 0) {
                    b.append(' <div class="conditionitem clearfix"><div class="row"><div class="col-md-2"><li class="woo-class-title" data="' + d[c].classTitleName + '">' + d[c].classTitleName + ':</li></div><div class="col-md-10"></div></div></div>');
                    a = b.find("li[data=" + d[c].classTitleName + "]")
                }
                a.parent().next().append(' <li><a href="javascript:;" onclick="addselect($(\'#selectspecialityconditon\'), this)" data="' + d[c].classPropertyName + '">' + d[c].classPropertyName + "</a></li>")
            }
        }
    }, "json")
}
function queryEmployerjob(a, c) {
    var b = "/page/demo/job/employerjob?employerId=" + c;
    $.post(b, null, function (f) {
        var k = f.job_list;
        $("#jobList").html('<div class="font20 fontem"><label class="clr440062 marginl4r4"><i class="nameplate verticalmiddle"></i><span class="verticalmiddle">&nbsp;&nbsp;招聘职位</span></label></div><div class="clra6a6a6 floatright paddingl4r4 marginb10">该公司有 <span class="font20 fontem clrffa201 italic">' + k.length + "</span> 个职位正在发布</div>");
        for (var g = 0; g < k.length; g++) {
            var h = k[g].publishTime;
            var e = dayInterval(newDateAndTime(h), new Date());
            var m = "";
            if (k[g].workPattern != null) {
                m = k[g].workPattern
            }
            var l = salaryVal(k[g].salaryBegin, k[g].salaryEnd);
            var d = e == 0 ? "今天" : e + "天前发布";
            var j = '    <div class="sizemh74 clrbfff clearboth margin10"><div class="row"><div class="col-md-1 col-xs-1">        <label class="sizeh20 marginb0 clrb440062 sizemh74 padding0">&nbsp;</label></div>        <div class="col-md-8 col-xs-7">            <div class="margint15"><a class="clrffa201 font15 fontem" href="/jsp/job/job_detail_r.jsp?jobId=' + k[g].jobId + '">' + k[g].jobName + '</a> <span class="clra6a6a6"> [' + k[g].city + ' ]</span></div><div class="paddingt10 paddingb10">' + k[g].eduReq + " / " + m + " / 月薪&nbsp;" + l + '</div>        </div>        <div class="col-md-3  col-xs-3 fontem paddingt10 text-right paddingr25">' + d + "</li>        </div></div>    </div>";
            $("#jobList").append(j)
        }
    }, "json")
}
function queryHRRatio(b, a) {
    $.post("/page/demo/job/interact/statichr?employerId=" + b, null, function (e) {
        var c = 0;
        if (e.static_hr != null) {
            for (var d = 0; d < e.static_hr.length; ++d) {
                c += dayInterval(newDateAndTime(e.static_hr[d].deliverTime), newDateAndTime(e.static_hr[d].watchTime))
            }
            if (e.static_hr.length > 0) {
                var f = $(a).find(".hrratio");
                f.removeClass("clra6a6a6").removeClass("font20").addClass("clrb5d95f").addClass("font45");
                f.html(parseInt(c / e.static_hr.length) + '<span class="font15 clra6a6a6">天</span>')
            }
        }
    }, "json")
}
function queryEmployer(b) {
    var a = "/page/demo/employer/employerdetail?employerId=" + b;
    $.post(a, null, function (h) {
        var g = h.employer_detail;
        if (g == null) {
            $("div.row").remove()
        } else {
            $("#inputTel").attr("value", g.tel);
            $("#logoPreview").attr("src", regular(g.logoPath));
            $("#logoPreview").attr("alt", regular(g.employerName));
            $("#inputemployerDesc").html(g.employerDesc);
            $("#inputEmployer").text(g.employerName);
            if (regular(g.website) == "") {
                $("#webSiteId").css("display", "none")
            } else {
                if (g.website.indexOf("http") < 0 && g.website.indexOf("https:") < 0) {
                    g.website = "http://" + g.website
                }
                $("#webSiteId").attr("href", g.website)
            }
            $("#saleTypeId").text(g.saleType);
            $("#scaleId").text(g.employerScale);
            $("#placeId").html(g.province + "&nbsp;&nbsp;" + g.city);
            $("#placeDetailId").text(g.address);
            var d = "/page/demo/employer/store/read?employerId=" + b;
            $.post(d, null, function (k) {
                var j = k.employer_store;
                if (j != null) {
                    $("#webSiteId").text(j.taobaoStoreName);
                    $("#taobaoLevelId").text("  " + j.taobaoLevel)
                }
            }, "json");
            $("#tagList").html("");
            var f = '<li class="marginb10"><a class="tag  sizew120 verticalmiddle">';
            var e = "</a></li>";
            if (g.tag1 != null && g.tag1 != "") {
                $("#tagList").append(f + g.tag1 + e)
            }
            if (g.tag2 != null && g.tag2 != "") {
                $("#tagList").append(f + g.tag2 + e)
            }
            if (g.tag3 != null && g.tag3 != "") {
                $("#tagList").append(f + g.tag3 + e)
            }
            if (g.tag4 != null && g.tag4 != "") {
                $("#tagList").append(f + g.tag4 + e)
            }
            if (g.tag5 != null && g.tag5 != "") {
                $("#tagList").append(f + g.tag5 + e)
            }
            if (regular(g.product1Pic) != "" || regular(g.product2Pic) || regular(g.product3Pic) || regular(g.product4Pic) || regular(g.product5Pic)) {
                $("#employerdetail").before(' <div id="Carousel" class="carousel slide margint20" style="width: 100%;" data-ride="carousel"><ol class="carousel-indicators"></ol><div class="carousel-inner" role="listbox"></div></div>');
                var c = 0;
                if (g.product1Pic != null && g.product1Pic != "") {
                    $(".carousel-indicators").append('<li data-target="#Carousel" data-slide-to="' + c + '" class="active"></li>');
                    $(".carousel-inner").append('<div class="item active"><img style="width: 682px; height: 300px;" src="' + g.product1Pic + '" alt="..."></div>');
                    c++
                }
                if (g.product2Pic != null && g.product2Pic != "") {
                    $(".carousel-indicators").append('<li data-target="#Carousel" data-slide-to="' + c + '"></li>');
                    $(".carousel-inner").append('<div class="item"><img style="width: 682px; height: 300px;" src="' + g.product2Pic + '" alt="..."></div>');
                    c++
                }
                if (g.product3Pic != null && g.product3Pic != "") {
                    $(".carousel-indicators").append('<li data-target="#Carousel" data-slide-to="' + c + '"></li>');
                    $(".carousel-inner").append('<div class="item"><img style="width: 682px; height: 300px;" src="' + g.product3Pic + '" alt="..."></div>');
                    c++
                }
                if (g.product4Pic != null && g.product4Pic != "") {
                    $(".carousel-indicators").append('<li data-target="#Carousel" data-slide-to="' + c + '"></li>');
                    $(".carousel-inner").append('<div class="item"><img style="width: 682px; height: 300px;" src="' + g.product4Pic + '" alt="..."></div>');
                    c++
                }
                if (g.product5Pic != null && g.product5Pic != "") {
                    $(".carousel-indicators").append('<li data-target="#Carousel" data-slide-to="' + c + '"></li>');
                    $(".carousel-inner").append('<div class="item"><img style="width: 682px; height: 300px;" src="' + g.product5Pic + '" alt="..."></div>');
                    c++
                }
                $(".carousel").carousel({interval: 6000, wrap: false})
            }
        }
    }, "json")
}
function querysingleEmployerHot(a) {
    $.post("/page/demo/employer/singlehot?employerId=" + a, null, function (g) {
        var c = g.employer_hot;
        var f = new Array(c.day1times, c.day2times, c.day3times, c.day4times, c.day5times, c.day6times, c.day7times);
        var j = new Array();
        var e = 0;
        for (var d = 0; d < dateArray.length; ++d) {
            j[d] = f[(d + currentDay) % 7];
            e += j[d]
        }
        $("#total").text(e);
        var h = {labels: newDateArray, datasets: [
            {label: "My First dataset", fillColor: "rgba(255,162,1,0.2)", strokeColor: "rgba(255,162,1,1)", pointColor: "rgba(255,162,1,1)", pointStrokeColor: "#fff", pointHighlightFill: "#fff", pointHighlightStroke: "rgba(255,162,1,1)", data: j}
        ]};
        var b = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(b).Line(h, {responsive: true})
    }, "json")
}
var toolbarStart = "<ul class='stuff list-unstyled lineheight40'>";
var toolbar_moveup = "<li><a href='javascript:void(0);' onclick='moveUp(this);'  title='将此题上移'><span class='fa fa-arrow-up'></span><span>上移</span></a></li>";
var toolbar_movedown = "<li><a href='javascript:void(0);' onclick='moveDown(this);'  title='将此题下移'><span class='fa fa-arrow-down'></span><span>下移</span></a></li>";
var toolbar_del = "<li><a href='javascript:void(0);' onclick='deleteQ(this);'  title='删除此题'><span class='fa fa-remove'></span><span>删除</span></a></li>";
var toolbar_end = "<div style='clear:both;'></div></ul>";
var questiontitle = "<div class='qtitle clra6a6a6 paddingb10'><span class='index'></span><input class='tedit' name='question' value='多选题'></div><ul class='list-unstyled questionitem paddingl10'></ul>";
var checkbox_item = "<div class='row paddingl10 checkbox'><div class='col-md-1 text-right'><input type='checkbox'></div><div class='col-md-10 text-left'><textarea name='option' class='tedit'>选项</textarea></div><i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var radio_item = "<div class='row paddingl10 radio'><div class='col-md-1 text-right'><input type='radio'></div><div class='col-md-10 text-left'><textarea name='option' class='tedit'>选项</textarea></div><i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var picradio_item = "<div class='thumbnail'> <div><div style='position:relative;'><img class='tpic sizew150 sizeh150' src='/img/picdefault.png'><input type='file' accept='image/*' class='questionpic sizew150 sizeh150 transparent pointer' style='position: absolute;z-index: 2; top: 0px;'></div> <div class='radio'><label><input type='radio' name='optionsRadios' value='option1'><input class='l tedit' val=' 选项'></label></div></div><i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var piccheckbox_item = "<div class='thumbnail'> <div><div style='position:relative;'><img class='tpic sizew150 sizeh150' src='/img/picdefault.png'><input type='file' accept='image/*' class='questionpic sizew150 sizeh150 transparent pointer' style='position: absolute;z-index: 2; top: 0px;'></div> <div class='checkbox'><label><input type='checkbox' name='optionsRadios' value='option1'><input class='l tedit' val=' 选项'></label></div></div><i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var answer_item = "<li style='display: inherit;'><div class='paddingl10 paddingr25'><textarea class='form-control tedit' rows='3' placeholder='(此处填写答案)'></textarea></div></li>";
var add_toolbar = "<div class='operationh' onclick='additem(this)'><a href='javascript:;' style='display: block;'><i class='fa fa-plus'></i></a></div>";
var question_content = $("#questioncontent");
function moveUp(b) {
    var d = $(b).parents(".div_question");
    if (d.index() <= 0) {
        $.simplyToast("第一个无法上移！", "warning")
    } else {
        var c = d.index();
        d.prev().find(".index").text((c + 1) + " ");
        d.find(".index").text(c + " ");
        d.insertBefore(d.prev())
    }
}
function moveDown(b) {
    var d = $(b).parents(".div_question");
    if (d.index() + 1 >= d.parent().children().length) {
        $.simplyToast("最后一个无法下移！", "warning")
    } else {
        var c = d.index();
        d.next().find(".index").text((c + 1) + " ");
        d.find(".index").text((c + 2) + " ");
        d.insertAfter(d.next())
    }
}
function deleteQ(b) {
    var c = $(b).parents(".div_question");
    c.remove();
    $(".div_question").each(function (a) {
        $(this).find(".index").text((a + 1))
    })
}
function parseDom(b) {
    var a = document.createElement("li");
    a.innerHTML = b;
    return a
}
function additem(a) {
    var b = null;
    if ($(a).parents(".div_question").find(".questionitem").children().length > 4) {
        $.simplyToast("选项最多不能超过5个", "warning")
    } else {
        if ($(a).parents(".div_question").attr("type") == "radio") {
            b = $(a).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(radio_item))
        } else {
            if ($(a).parents(".div_question").attr("type") == "checkbox") {
                b = $(a).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(checkbox_item))
            } else {
                if ($(a).parents(".div_question").attr("type") == "piccheckbox") {
                    b = $(a).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(piccheckbox_item));
                    $(a).parents(".div_question").find(".questionpic").change(function (c) {
                        checkFile(this, 500, $(c.target).prev()[0], 150, 150, "")
                    })
                } else {
                    if ($(a).parents(".div_question").attr("type") == "picradio") {
                        b = $(a).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(picradio_item));
                        $(a).parents(".div_question").find(".questionpic").change(function (c) {
                            checkFile(this, 500, $(c.target).prev()[0], 150, 150, "")
                        })
                    }
                }
            }
        }
    }
    return b
}
function addNewQ(a) {
    a.item.appendChild(a.toolbar);
    a.item.appendChild(a.content);
    if (a.type == "radio") {
        $(a.item).find(".qtitle input").val("单选题");
        $(a.item).attr("type", "radio")
    } else {
        if (a.type == "checkbox") {
            $(a.item).find(".qtitle input").val("多选题");
            $(a.item).attr("type", "checkbox")
        } else {
            if (a.type == "piccheckbox") {
                $(a.content).find("ul").removeClass("list-unstyled").addClass("list-inline");
                $(a.item).find(".qtitle input").val("图片多选题");
                $(a.item).attr("type", "piccheckbox")
            } else {
                if (a.type == "picradio") {
                    $(a.content).find("ul").removeClass("list-unstyled").addClass("list-inline");
                    $(a.item).find(".qtitle input").val("图片单选题");
                    $(a.item).attr("type", "picradio")
                } else {
                    if (a.type == "answer") {
                        $(a.item).find(".qtitle input").val("问答题");
                        $(a.item).attr("type", "answer");
                        $(a.content).find(".questionitem").html(answer_item)
                    }
                }
            }
        }
    }
    question_content.append(a.item);
    $(a.item).bind("mouseenter", function () {
        if (a.type != "answer") {
            if ($(this).find(".operationh").size() == 0) {
                $(this).find(".content").append(add_toolbar);
                $(this).find(".glyphicon-trash").css("display", "")
            }
        }
    });
    $(a.item).bind("mouseleave", function () {
        if ($(this).find(".operationh").size() != 0) {
            $(".operationh").remove();
            $(this).find(".glyphicon-trash").css("display", "none")
        }
    })
}
function createQ(b) {
    var a = new Object();
    a.type = b;
    a.item = new Object();
    a.item = document.createElement("div");
    a.item.className = "div_question row margint30 bottomdashedborder paddingb20";
    a.toolbar = document.createElement("div");
    a.toolbar.className = "col-md-2 toolbar margint20";
    a.toolbar.innerHTML = toolbarStart + toolbar_moveup + toolbar_movedown + toolbar_del + toolbar_end;
    a.content = document.createElement("div");
    a.content.className = "col-md-10 content";
    a.content.innerHTML = questiontitle;
    jQuery(a.content).find(".index").text((jQuery(document).find(".div_question").length + 1) + " ");
    addNewQ(a);
    return a
}
function trash(a) {
    $(a).parents(".questionitem li").remove()
}
function questionnaireToJSON(a, b) {
    var d = new Object();
    d.title = $(a).find("#wenjuantitle").val();
    d.queslist = new Array();
    var c = false;
    $(a).find(".div_question").each(function () {
        var g = new Object();
        g.type = $(this).attr("type");
        g.index = $(this).find(".index").text();
        g.title = $(this).find(".qtitle .tedit").val();
        g.option = new Array();
        if (g.type == "picradio" || g.type == "piccheckbox") {
            if (b == "publish") {
                var e = new Object();
                $(this).find(".questionitem .thumbnail").each(function (h) {
                    if ($(this).find(".tpic").attr("src").indexOf("data:image") < 0) {
                        c = true;
                        return
                    }
                    if (h == 0) {
                        e.picSrc1 = $(this).find(".tpic").attr("src")
                    } else {
                        if (h == 1) {
                            e.picSrc2 = $(this).find(".tpic").attr("src")
                        } else {
                            if (h == 2) {
                                e.picSrc3 = $(this).find(".tpic").attr("src")
                            } else {
                                if (h == 3) {
                                    e.picSrc4 = $(this).find(".tpic").attr("src")
                                } else {
                                    if (h == 4) {
                                        e.picSrc5 = $(this).find(".tpic").attr("src")
                                    }
                                }
                            }
                        }
                    }
                });
                if (c) {
                    return
                }
                var f = null;
                $.ajax({type: "post", url: "/page/demo/questionnaire/wquespic", async: false, data: $.param(e), dataType: "json", success: function (h) {
                    f = h.questionnaire_pic_id
                }});
                $(this).find(".questionitem .thumbnail").each(function (h) {
                    g.option.push({src: f + " " + h, hint: $(this).find(".tedit").val()})
                })
            } else {
                if (b == "preview") {
                    $(this).find(".questionitem .thumbnail").each(function () {
                        g.option.push({src: $(this).find(".tpic").attr("src"), hint: $(this).find(".tedit").val()})
                    })
                }
            }
        } else {
            $(this).find(".questionitem .tedit").each(function () {
                g.option.push($(this).val())
            })
        }
        d.queslist.push(g)
    });
    if (c) {
        return -1
    }
    return JSON.stringify(d)
}
function createByData(f) {
    var e = $.parseJSON(f);
    $("#wenjuantitle").val(e.title);
    for (var d = 0; d < e.queslist.length; ++d) {
        var c = createQ(e.queslist[d].type);
        $(c.item).find(".qtitle input").val(e.queslist[d].title);
        if (e.queslist[d].type == "answer") {
        } else {
            if (e.queslist[d].type == "checkbox" || e.queslist[d].type == "radio") {
                for (var b = 0; b < e.queslist[d].option.length; ++b) {
                    var h = additem(c.item.firstChild);
                    $(h).find(".tedit").val(e.queslist[d].option[b])
                }
            } else {
                var g = null;
                for (var b = 0; b < e.queslist[d].option.length; ++b) {
                    if (b == 0) {
                        var a = e.queslist[d].option[0].src.split(" ")[0];
                        $.ajax({url: "/page/demo/questionnaire/rquespic", async: false, data: $.param({questionnairePicId: a}), dataType: "json", success: function (j) {
                            g = j.questionnaire_pic
                        }})
                    }
                    var h = additem(c.item.firstChild);
                    $(h).find(".l.tedit").val(e.queslist[d].option[b].hint);
                    if (b == 0) {
                        $(h).find("img").attr("src", g.picSrc1)
                    } else {
                        if (b == 1) {
                            $(h).find("img").attr("src", g.picSrc2)
                        } else {
                            if (b == 2) {
                                $(h).find("img").attr("src", g.picSrc3)
                            } else {
                                if (b == 3) {
                                    $(h).find("img").attr("src", g.picSrc4)
                                } else {
                                    if (b == 4) {
                                        $(h).find("img").attr("src", g.picSrc5)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
function onPreviewByData(c, g, e) {
    e.html("");
    e.append('<div class="text-center"><label class="tedit font20" id="previewwenjuantitle">' + c.title + "</label></div>");
    for (var d = 0; d < c.queslist.length; ++d) {
        var f = document.createElement("div");
        f.className = "pre_div_question row margint30";
        f.setAttribute("type", c.queslist[d].type);
        f.innerHTML = '<div class="col-md-10 content"><div class="qtitle clra6a6a6 paddingb10" ><span class="index">' + c.queslist[d].index + ' </span><label class="tedit" name="question">' + c.queslist[d].title + '</label></div><div class="paddingl10 questionitem"></div></div>';
        if (c.queslist[d].type == "answer") {
            $(f).find(".questionitem").append('<div class="paddingl10 paddingr25"><textarea class="form-control" rows="3" placeholder="(此处填写答案)"></textarea></div>')
        } else {
            if (c.queslist[d].type == "piccheckbox" || c.queslist[d].type == "picradio") {
                $(f).find(".questionitem").append('<div class="paddingl10"><ul class="list-inline"></ul></div>');
                if (g == "houtai") {
                    var k = null;
                    var a = "";
                    for (var b = 0; b < c.queslist[d].option.length; ++b) {
                        if (b == 0) {
                            var h = c.queslist[d].option[0].src.split(" ")[0];
                            $.ajax({url: "/page/demo/questionnaire/rquespic", async: false, data: $.param({questionnairePicId: h}), dataType: "json", success: function (j) {
                                k = j.questionnaire_pic
                            }})
                        }
                        if (b == 0) {
                            a = k.picSrc1
                        } else {
                            if (b == 1) {
                                a = k.picSrc2
                            } else {
                                if (b == 2) {
                                    a = k.picSrc3
                                } else {
                                    if (b == 3) {
                                        a = k.picSrc4
                                    } else {
                                        if (b == 4) {
                                            a = k.picSrc5
                                        }
                                    }
                                }
                            }
                        }
                        $(f).find(".questionitem ul").append('<li><div class="thumbnail"><div><img src="' + a + '" class="sizew150 sizeh150"></div><div class="' + c.queslist[d].type.replace("pic", "") + '"><label><input name="optionsRadios' + d + '" type="' + c.queslist[d].type.replace("pic", "") + '">&nbsp;' + c.queslist[d].option[b].hint + "</label></div></div></li>")
                    }
                } else {
                    for (var b = 0; b < c.queslist[d].option.length; ++b) {
                        $(f).find(".questionitem ul").append('<li><div class="thumbnail"><div><img src="' + c.queslist[d].option[b].src + '" class="sizew150 sizeh150"></div><div class="' + c.queslist[d].type.replace("pic", "") + '"><label><input name="optionsRadios' + d + '" type="' + c.queslist[d].type.replace("pic", "") + '">&nbsp;' + c.queslist[d].option[b].hint + "</label></div></div></li>")
                    }
                }
            } else {
                for (var b = 0; b < c.queslist[d].option.length; ++b) {
                    $(f).find(".questionitem").append('<div class="' + c.queslist[d].type + '"><label><input type="' + c.queslist[d].type + '" name="optionsRadios' + d + '">' + c.queslist[d].option[b] + "</label></div>")
                }
            }
        }
        e[0].appendChild(f)
    }
}
jQuery.cookie = function (b, j, m) {
    if (typeof j != "undefined") {
        m = m || {};
        if (j === null) {
            j = "";
            m.expires = -1
        }
        var e = "";
        if (m.expires && (typeof m.expires == "number" || m.expires.toUTCString)) {
            var f;
            if (typeof m.expires == "number") {
                f = new Date();
                f.setTime(f.getTime() + (m.expires * 24 * 60 * 60 * 1000))
            } else {
                f = m.expires
            }
            e = ";expires=" + f.toUTCString()
        }
        var l = m.path ? ";path=" + m.path : "";
        var g = m.domain ? ";domain=" + m.domain : "";
        var a = m.secure ? ";secure" : "";
        document.cookie = [b, "=", encodeURIComponent(j), e, l, g, a].join("")
    } else {
        var d = null;
        if (document.cookie && document.cookie != "") {
            var k = document.cookie.split(";");
            for (var h = 0; h < k.length; h++) {
                var c = jQuery.trim(k[h]);
                if (c.substring(0, b.length + 1) == (b + "=")) {
                    d = decodeURIComponent(c.substring(b.length + 1));
                    break
                }
            }
        }
        return d
    }
};
function bindRotate180(a, b) {
    $(a).rotate({bind: {click: function () {
        if ($(b).getRotateAngle() == 180) {
            $(b).rotate({animateTo: 0})
        } else {
            $(b).rotate({animateTo: 180})
        }
    }}})
}
function customDropDown(b, a) {
    this.dropdown = b;
    this.placeholder = this.dropdown.find(".placeholder");
    this.options = b.find("ul.dropdown-menu > li");
    this.val = "";
    this.index = -1;
    this.handler = a;
    this.initEvents()
}
customDropDown.prototype = {initEvents: function () {
    var a = this;
    a.options.on("click", function () {
        var b = $(this);
        a.text = b.find("a").text();
        a.val = b.attr("value");
        a.index = b.index();
        a.placeholder.text(a.text);
        if (a.text.indexOf("选择") >= 0) {
            showError(a.placeholder.parent())
        } else {
            hideError(a.placeholder.parent())
        }
        if (a.handler != null) {
            a.handler(a)
        }
    })
}, getText: function () {
    return this.text
}, getValue: function () {
    return this.val
}, getIndex: function () {
    return this.index
}};
$("body").on("mouseover", ".datetimepicker-cls", function () {
    $(this).datetimepicker({pickTime: false, language: "zh-CN"})
});
function regular(a) {
    if (a == undefined) {
        return""
    }
    return a
}
function getTimeIdentity() {
    var a = (new Date());
    return a.getMilliseconds() + a.getSeconds()
}
function isLeapYear(a) {
    if (a % 4 == 0 && a % 100 != 0) {
        return true
    } else {
        if (a % 400 == 0) {
            return true
        } else {
            return false
        }
    }
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "")
};
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "")
};
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "")
};
String.prototype.replaceAll = function (a, c, b) {
    if (!RegExp.prototype.isPrototypeOf(a)) {
        return this.replace(new RegExp(a, (b ? "gi" : "g")), c)
    } else {
        return this.replace(a, c)
    }
};
function setRadio(a, b) {
    for (i = 0; i < a.length; i++) {
        if (a[i].value == b) {
            a[i].checked = true
        }
    }
}
function getRadio(a) {
    for (i = 0; i < a.length; i++) {
        if (a[i].checked) {
            return a[i].value
        }
    }
    return""
}
function setDateTime(a, b) {
    if (b != null && a != null) {
        b = b.substring(0, b.indexOf(" "));
        b = b.replaceAll("-", "/");
        a.attr("value", b)
    }
}
function formatDateTime(a) {
    if (a != null) {
        a = a.substring(0, a.indexOf(" "));
        a = a.replaceAll("-", "/")
    }
    return a
}
function newDate(b) {
    b = b.split("-");
    var a = new Date();
    a.setUTCFullYear(b[0], b[1] - 1, b[2]);
    a.setUTCHours(0, 0, 0, 0);
    return a
}
function newDateAndTime(a) {
    if (a == undefined) {
        return null
    }
    var d = a.split(" ")[0].split("-");
    var c = a.split(" ")[1].split(":");
    var b = new Date();
    b.setFullYear(d[0], d[1] - 1, d[2]);
    b.setHours(c[0], c[1], c[2], 0);
    return b
}
function getDateStr(a) {
    return((a.getMonth() + 1) + "月" + a.getDate() + "日")
}
function getTimeStr(a) {
    return(a.getHours() + ":" + (a.getMinutes()))
}
Date.prototype.format = function (b) {
    var c = {"M+": this.getMonth() + 1, "d+": this.getDate(), "h+": this.getHours(), "m+": this.getMinutes(), "s+": this.getSeconds(), "q+": Math.floor((this.getMonth() + 3) / 3), S: this.getMilliseconds()};
    if (/(y+)/.test(b)) {
        b = b.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
    }
    for (var a in c) {
        if (new RegExp("(" + a + ")").test(b)) {
            b = b.replace(RegExp.$1, RegExp.$1.length == 1 ? c[a] : ("00" + c[a]).substr(("" + c[a]).length))
        }
    }
    return b
};
function dayInterval(a, b) {
    if (a == undefined || b == undefined) {
        return 0
    }
    return parseInt((b.getTime() - a.getTime()) / (1000 * 60 * 60 * 24))
}
function uploadFile(d, c, e, g, b, a) {
    var f = new FormData();
    f.append("Filedata", d);
    f.append("type", e);
    f.append("id", g);
    if (b != undefined) {
        f.append("index", b)
    }
    $.ajax({type: "post", url: c, data: f, processData: false, contentType: false, success: function (h) {
        if (a != null) {
            a(h)
        }
    }})
}
function uploadFileInner(c, g, e, b, d, f, a) {
    if (g >= 0 && g < e.length) {
        uploadFile(e[g], b, d, f, g, function (h) {
            c.push(h);
            uploadFileInner(c, g + 1, e, b, d, f, a);
            if (g == e.length - 1) {
                if (a != null) {
                    a(c)
                }
            }
        })
    }
}
function uploadFiles(e, c, d, f, a) {
    var b = new Array();
    uploadFileInner(b, 0, e, c, d, f, a);
    return b
}
function previewImage(c, b, f, e) {
    if (c.files && c.files[0]) {
        b.onload = function () {
            var j = clacImgZoomParam(f, e, b.offsetWidth, b.offsetHeight);
            b.width = j.width;
            b.height = j.height;
            b.style.marginLeft = j.left + "px";
            b.style.marginTop = j.top + "px"
        };
        var a = new FileReader();
        a.onload = function (j) {
            b.src = j.target.result
        };
        a.readAsDataURL(c.files[0])
    } else {
        var h = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        c.select();
        var g = document.selection.createRange().text;
        b.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = g;
        var d = clacImgZoomParam(f, e, b.offsetWidth, b.offsetHeight);
        status = ("rect:" + d.top + "," + d.left + "," + d.width + "," + d.height);
        div.innerHTML = "<div style='width:" + d.width + "px;height:" + d.height + "px;margin-top:" + d.top + "px;margin-left:" + d.left + "px;" + h + g + "\"'></div>"
    }
}
function clacImgZoomParam(d, c, b, a) {
    var e = {top: 0, left: 0, width: b, height: a};
    if (b > d || a > c) {
        rateWidth = b / d;
        rateHeight = a / c;
        if (rateWidth > rateHeight) {
            e.width = d;
            e.height = Math.round(a / rateWidth)
        } else {
            e.width = Math.round(b / rateHeight);
            e.height = c
        }
    }
    e.left = Math.round((d - e.width) / 2);
    e.top = Math.round((c - e.height) / 2);
    return e
}
function checkFile(a, e, h, c, l, k) {
    var f = $(a).val();
    var g = f.lastIndexOf(".");
    var d = f.substring(g, f.length).toUpperCase();
    if (d != ".BMP" && d != ".PNG" && d != ".GIF" && d != ".JPG" && d != ".JPEG") {
        $.simplyToast("图片限于bmp,png,gif,jpeg,jpg格式!", "danger");
        var b = $(a).prev();
        var j = $(a).clone();
        $(a).remove();
        j.insertAfter(b);
        $(j).change(function () {
            checkFile($(this), e, h, c, l, k)
        });
        $(h).attr("src", k);
        return false
    }
    if (a.files[0].size > e * 1024) {
        $.simplyToast("图片不能大于" + e + "K", "danger");
        var b = $(a).prev();
        var j = $(a).clone();
        $(a).remove();
        j.insertAfter(b);
        $(j).change(function () {
            checkFile(this, e, h, c, l, k)
        });
        $(h).attr("src", k);
        return false
    }
    previewImage(a, h, c, l);
    return true
}
function checkNoImageFile(g, b) {
    var d = $(g).val();
    var f = d.lastIndexOf(".");
    var e = d.substring(f, d.length).toUpperCase();
    if (e != ".BMP" && e != ".PNG" && e != ".GIF" && e != ".JPG" && e != ".JPEG") {
        $.simplyToast("图片限于bmp,png,gif,jpeg,jpg格式!", "danger");
        var a = $(g).next();
        var c = $(g).clone();
        $(g).remove();
        c.insertBefore(a);
        $(c).change(function () {
            checkNoImageFile(this, b)
        });
        return false
    }
    if (g.files[0].size > b * 1024) {
        $.simplyToast("图片不能大于" + b + "K", "danger");
        var a = $(g).next();
        var c = $(g).clone();
        $(g).remove();
        c.insertBefore(a);
        $(c).change(function () {
            checkNoImageFile(this, b)
        });
        return false
    }
    return true
}
function checkComposeImgFile(a, d, g, b, l, k) {
    var e = $(a).val();
    var f = e.lastIndexOf(".");
    var c = e.substring(f, e.length).toUpperCase();
    if (c != ".BMP" && c != ".PNG" && c != ".GIF" && c != ".JPG" && c != ".JPEG") {
        $.simplyToast("图片限于bmp,png,gif,jpeg,jpg格式!", "danger");
        var j = $(a).previousNode();
        var h = $(a).clone();
        $(a).remove();
        h.insertAfter(j);
        $(h).change(function () {
            checkFile($(this), d, g, b, l, k)
        });
        $(g).attr("src", k);
        return false
    }
    if (a.files[0].size > d * 1024) {
        $.simplyToast("图片不能大于" + d + "K", "danger");
        var j = $(a).previousNode();
        var h = $(a).clone();
        $(a).remove();
        h.insertAfter(j);
        $(h).change(function () {
            checkFile(this, d, g, b, l, k)
        });
        $(g).attr("src", k);
        return false
    }
    previewImage(a, g, b, l);
    return true
}
function plusTag(a) {
    $("#tagPlusBtn").before('        <div class="input-group woo-tags" >          <input class="form-control" placeholder="" value="' + a + '"                required="" maxlength=6 style=" visibility: inherit">          <span class="input-group-addon" style="padding: 0px"> <button class="fa fa-remove" style="background: transparent; height: 100%; border: 0px"></button></span>      </div>');
    if ($("#tagList div input").size() >= 5) {
        $("#tagPlusBtn").hide()
    }
    $("#tagList div span").click(function () {
        $(this).parent().remove();
        if ($("#tagList div input").size() < 5) {
            $("#tagPlusBtn").show()
        }
    })
}
function plusqq(a) {
    $("#qqPlusBtn").before('        <div class="input-group" >          <input id="qq" class="form-control" placeholder="qq号码" value="' + a + '"                required="" maxlength=11 style=" visibility: inherit">          <span class="input-group-addon" style="padding: 0px"> <button class="fa fa-remove" style="background: transparent; height: 100%; border: 0px"></button></span>      </div>');
    $("#qqPlusBtn").hide();
    $("#qqPlusBtn").parent().find("div span").click(function () {
        $(this).parent().remove();
        if ($("#qqPlusBtn").parent().find("div input").size() < 1) {
            $("#qqPlusBtn").show()
        }
    })
}
function calcusYear(b) {
    if (b == undefined) {
        return""
    }
    var e = b.substring(0, b.indexOf("-"));
    var c = new Date();
    var a = c.getFullYear();
    return a - parseInt(e)
}
function salaryVal(b, a) {
    var c = "";
    if (b == 0 && 0 == a) {
        c = "面议"
    } else {
        if (b == 0) {
            c = a + "元"
        } else {
            if (a == 0) {
                c = b + "元"
            } else {
                if (b == a) {
                    c = b + "元"
                } else {
                    c = b + "-" + a + "元"
                }
            }
        }
    }
    return c
}
function emailValid(b) {
    var a = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    if (!a.test(b)) {
        return false
    }
    return true
}
function checkValidCode(b) {
    var a = $("#validImgValId").val();
    if (b == $("#validImgValId").val()) {
        return true
    } else {
        return false
    }
}
function rand() {
    var a = "" + (Math.random() * 10000);
    a = a.substring(0, a.indexOf("."));
    switch (a.length) {
        case 1:
            a = "000" + a;
            break;
        case 2:
            a = "00" + a;
            break;
        case 3:
            a = "0" + a;
            break;
        default:
            a = a.substring(0, 4);
            break
    }
    return a
}
function setImgUrl() {
    var a = rand();
    $("#validImgId").attr("src", "/jsp/reg/code.jsp?rand=" + a);
    $("#validImgValId").val(a);
    return a
}
function setBarStatus() {
    $.ajax({type: "POST", dataType: "json", url: "/page/demo/getaccount", success: function (a) {
        if (a.userType == "hunter") {
            $("#nav-right").html('<li><a id="noticeId" class="woo-nav-item" href="/jsp/hunter/apply.jsp?jobHunterId=' + a.jobHunterId + '" title="通知"><span class="fa fa-flag font20"></span></a></li><li><a id="envelopeId" class="woo-nav-item" href="/chatlist.jsp?jobHunterId=' + a.jobHunterId + '&type=0" title="私信"><span class="fa fa-envelope font20"></span></a></li><li><a class="woo-nav-item" href="/jsp/hunter/favorite.jsp?jobHunterId=' + a.jobHunterId + '" title="收藏"><span class="fa fa-star font20"></span></a></li><li><li id="fat-menu" class="dropdown"><a id="drop3" href="#" class="woo-nav-item dropdown-toggle js-activated sizeh54" data-toggle="dropdown" aria-expanded="false"><img class="size20" src="' + regular(a.avatarPath == undefined ? "/img/avatar_male.png" : a.avatarPath) + '"></a><ul class="dropdown-menu" role="menu" aria-labelledby="drop3"><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/hunter/basic_info_w.jsp?jobHunterId=' + a.jobHunterId + '">我的简历</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/hunter/setting.jsp?jobHunterId=' + a.jobHunterId + '">设置</a></li><li role="presentation" class="divider"></li><li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="exit(\'hunter\')">退出</a></li></ul></li>');
            $(".js-activated").dropdownHover().dropdown();
            if (a.interactGot != 1) {
                $.ajax({type: "POST", dataType: "json", data: $.param({jobHunterId: a.jobHunterId}), url: "/page/demo/job/interact/hunter/myinteractjobs", success: function (d) {
                    var e = d.job_interact_list;
                    var b = 0;
                    for (var c = 0; c < e.length; ++c) {
                        if (e[c].employerStatusChanged != 0) {
                            ++b
                        }
                    }
                    if (b > 0) {
                        $("#noticeId").append('<span class="badge woo-pic-badage">' + b + "</span>")
                    }
                }})
            }
            if (a.envelopeGot != 1) {
                $.ajax({type: "POST", dataType: "json", data: $.param({jobHunterId: a.jobHunterId}), url: "/page/demo/job/envelope/getunreadnumbyhunterid", success: function (c) {
                    var b = c.envelope_unread_num;
                    if (b > 0) {
                        $("#envelopeId").append('<span class="badge woo-pic-badage">' + b + "</span>")
                    }
                }})
            }
        } else {
            if (a.userType == "employer") {
                $("#nav-right").html('<li><a id="noticeId" class="woo-nav-item" href="/jsp/employer/employer_jobs.jsp?employerId=' + a.employerId + '" title="通知"><span class="fa fa-flag font20"></span></a></li><li><a  id="envelopeId" class="woo-nav-item" href="/chatlist.jsp?employerId=' + a.employerId + '&type=1" title="私信"><span class="fa fa-envelope font20"></span></a></li><li id="fat-menu" class="dropdown"><a id="drop3" href="#" class="woo-nav-item dropdown-toggle js-activated sizeh54" data-toggle="dropdown" aria-expanded="false"><img class="size20" src="' + regular(a.logoPath == undefined ? "/img/companydefault.png" : a.logoPath) + '"></a><ul class="dropdown-menu" role="menu" aria-labelledby="drop3"><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/job_detail_w.jsp?employerId=' + a.employerId + '">发布职位</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/employer_detail_w.jsp?employerId=' + a.employerId + '">企业资料</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/resume_search.jsp?employerId=' + a.employerId + '">简历搜索</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/employer_jobs.jsp?employerId=' + a.employerId + '">已发布职位</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/favorite.jsp?employerId=' + a.employerId + '">收藏的简历</a></li><li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/setting.jsp?employerId=' + a.employerId + '">账号设置</a></li><li role="presentation" class="divider"></li><li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="exit(\'employer\')">退出</a></li></ul></li>');
                $(".js-activated").dropdownHover().dropdown();
                $.ajax({type: "POST", dataType: "json", data: $.param({employerId: a.employerId}), url: "/page/demo/job/interact/employer/myinteractjobs", success: function (f) {
                    var j = f.job_interact_list;
                    var b = 0;
                    var e = 0;
                    var d = 0;
                    if (j != null) {
                        for (var c = 0; c < j.length; ++c) {
                            if (j[c].hunterStatusChanged != 0) {
                                ++b
                            }
                            var h = dayInterval(newDateAndTime(j[c].hunterUpdateTime), new Date());
                            if (j[c].hunterStatus == 3 && j[c].employerStatusChanged == 0 && j[c].joinFeedback == 0 && h >= 1) {
                                e++
                            }
                            var g = dayInterval(newDateAndTime(j[c].employerUpdateTime), new Date());
                            if (j[c].joinFeedback == 1 && j[c].acceptFeedback == 0 && g >= 1) {
                                d++
                            }
                        }
                    }
                    if (f.interactGot != 1) {
                        if (b > 0) {
                            $("#noticeId").append('<span class="badge woo-pic-badage">' + b + "</span>")
                        }
                    }
                    if (e > 0) {
                        $("#nav-right").prepend('<li><a class="woo-nav-item" title=\'反馈\' href="/jsp/employer/job_petitioners.jsp?feedback=1&employerId=' + j[0].employerId + '"><span class=\'fa fa-bullhorn font20\'></span><span class="badge woo-pic-badage">' + e + "</span></a></li>")
                    } else {
                        if (d > 0) {
                            $("#nav-right").prepend('<li><a class="woo-nav-item" title=\'反馈\' href="/jsp/employer/job_petitioners.jsp?feedback=2&employerId=' + j[0].employerId + '"><span class=\'fa fa-bullhorn font20\'></span><span class="badge woo-pic-badage">' + d + "</span></a></li>")
                        }
                    }
                }});
                if (a.envelopeGot != 1) {
                    $.ajax({type: "POST", dataType: "json", data: $.param({jobHunterId: a.jobHunterId}), url: "/page/demo/job/envelope/getunreadnumbyemployerid", success: function (c) {
                        var b = c.envelope_unread_num;
                        if (b > 0) {
                            $("#envelopeId").append('<span class="badge woo-pic-badage">' + b + "</span>")
                        }
                    }})
                }
            } else {
                $("#nav-right").html('<li><a class="woo-nav-item font15" href="/login.jsp">登录</a></li><li><a class="woo-nav-item font15" href="/jsp/reg/reg.jsp">注册</a></li>')
            }
        }
    }})
}
function exitResetCookie() {
    $.cookie("a", null, {path: "/", domain: "maojuren.com"});
    $.cookie("b", null, {path: "/", domain: "maojuren.com"});
    $.cookie("d", null, {path: "/", domain: "maojuren.com"});
    $.cookie("e", null, {path: "/", domain: "maojuren.com"});
    $.cookie("c", null, {path: "/", domain: "maojuren.com"});
    $("#nav-right").html('<li><a class="woo-nav-item font15" href="/login.jsp">登录</a></li><li><a class="woo-nav-item font15" href="/jsp/reg/reg.jsp">注册</a></li>')
}
function exit(a) {
    if (a == "hunter") {
        $.ajax({type: "POST", dataType: "json", url: "http://www.maojuren.com/page/demo/jobHunter/logout", success: function (b) {
            exitResetCookie()
        }})
    } else {
        $.ajax({type: "POST", dataType: "json", url: "http://www.maojuren.com/page/demo/employer/logout", success: function (b) {
            exitResetCookie()
        }})
    }
}
function login(e, a) {
    if (!formValidCheck()) {
        return false
    }
    var f;
    var d;
    if (a == null) {
        f = $(".main-navigation .active").attr("value");
        var c = $("#account_ipt").val();
        var b = $.md5($("#password_ipt").val());
        d = $(".loginaccounterr");
        a = new Object();
        if (f == 1) {
            a.jobHunterEmail = c;
            a.jobHunterPassword = b
        } else {
            a.hrEmail = c;
            a.password = b
        }
    } else {
        f = a.userType
    }
    if (f == 1) {
        $.ajax({type: "POST", dataType: "json", async: false, data: $.param(a), url: "/page/demo/jobHunter/login", success: function (h) {
            if (h.retCode == "fail") {
                if (d != undefined) {
                    d.show()
                }
                return false
            }
            var g = h.job_hunter_detail;
            $.cookie("a", g.jobHunterEmail, {expires: 3, path: "/", domain: "maojuren.com"});
            $.cookie("b", g.jobHunterPassword, {expires: 3, path: "/", domain: "maojuren.com"});
            $.cookie("c", "hunter", {expires: 3, path: "/", domain: "maojuren.com"});
            $("form").fadeOut(500);
            $(".wrapper").addClass("form-success");
            window.setTimeout(_goToUrl(e), 500)
        }})
    } else {
        $.ajax({type: "POST", dataType: "json", data: $.param(a), url: "/page/demo/employer/login", success: function (h) {
            if (h.retCode == "fail") {
                if (d != undefined) {
                    d.show()
                }
                return false
            }
            var g = h.employer_detail;
            $.cookie("d", g.hrEmail, {expires: 3, path: "/", domain: "maojuren.com"});
            $.cookie("e", g.password, {expires: 3, path: "/", domain: "maojuren.com"});
            $.cookie("c", "employer", {expires: 3, path: "/", domain: "maojuren.com"});
            $("form").fadeOut(500);
            $(".wrapper").addClass("form-success");
            window.setTimeout(_goToUrl(e), 500)
        }})
    }
}
function goToUrl(a) {
    if (a == "null" || a.indexOf("login") > 0 || a.indexOf("job_detail_r") < 0) {
        window.location.href = "/index.jsp"
    } else {
        window.location.href = a
    }
}
function _goToUrl(a) {
    return function () {
        goToUrl(a)
    }
}
function goSingleLoginUrl(a) {
    var b = $(".main-navigation .active").attr("value");
    if (b == 1) {
        $.cookie("c", "hunter", {expires: 3, path: "/", domain: "maojuren.com"})
    } else {
        if (b == 2) {
            $.cookie("c", "employer", {expires: 3, path: "/", domain: "maojuren.com"})
        }
    }
    window.location.href = "/page/demo/connet?type=" + a
}
function isMobile() {
    var b = navigator.userAgent;
    var a = b.indexOf("Android");
    a = Math.max(a, b.indexOf("iPhone"));
    if (a >= 0) {
        return true
    } else {
        return false
    }
}
function emailUrl(a) {
    window.location.href = "http://mail." + a.substr(a.indexOf("@") + 1)
}
function setNotSelectedValueToNull(a) {
    if (a == undefined) {
        a = ""
    } else {
        if (a.indexOf("选择") >= 0) {
            a = ""
        }
    }
    return a
}
function getExperienceByStartWorkYear(c) {
    var b;
    if (c == undefined) {
        return""
    }
    if (c == "应届毕业生" || c == "在校生") {
        b = c + " / "
    } else {
        if (c.indexOf("以前") >= 0) {
            b = "13年以上工作经验"
        } else {
            var a = new Date();
            b = (a.getFullYear() - c) + "年工作经验/ "
        }
    }
    return b
}
$(function () {
    $(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() > 100) {
                $("#back-to-top").fadeIn(1000)
            } else {
                $("#back-to-top").fadeOut(1000)
            }
        });
        $("#back-to-top").click(function () {
            $("body,html").animate({scrollTop: 0}, 500);
            return false
        })
    })
});
$(function () {
    $(function () {
        $(document).ready(function () {
            $(".rightbar li").click(function () {
                $(".rightbar li").removeClass("active");
                $(this).addClass("active")
            });
            $(".bottombar li").click(function () {
                $(".bottombar li").removeClass("active");
                $(this).addClass("active")
            })
        })
    })
});
function checkLength(b, a) {
    iCount = b.value.length;
    if (iCount <= a) {
        b.size = iCount + 2
    } else {
        alert("请不要超过" + a)
    }
}
$(function () {
    var a = {html: true, placement: "left", trigger: "hover", content: '<img src="/img/maojurencode.jpg" class="sizew150 sizeh150">'};
    $("#codemain").popover(a)
});