function saveProducts(jsonData) {
    $.ajax({
        type: "post",
        url: '/page/demo/jobHunter/jobhunterbasicw',
        data: jsonData,
        dataType: "json",
        success: function (msg) {
            if (msg.retMsg == "success") {
                $.simplyToast('保存成功!', 'success');
            } else {
                $.simplyToast('保存失败', 'danger');
            }
        }
    }, "json");
}

function saveProduct(btn, jobHunterId) {
    var files = new Array();
    var photoList = $("#photoList input");

    for (var pos = 0; pos < photoList.length; pos++) {
        if (photoList[pos].files != null && photoList[pos].files.length > 0) {
            files.push(photoList[pos].files[0]);
        }
    }
    ;
    var jsonData = new Object();
    jsonData.jobHunterId = jobHunterId;
    jsonData.productUrl = $("#producturl").val();
    if (files.length > 0) {
        uploadFiles(files, "/page/demo/upload", "hunterphoto", jobHunterId, function (msg) {

            var urlList = new Array("", "", "", "", "");
            var posMsg = 0;

            for (var pos = 0; pos < photoList.length; pos++) {
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

            jsonData.productPic1 = urlList[0];
            jsonData.productPic2 = urlList[1];
            jsonData.productPic3 = urlList[2];
            jsonData.productPic4 = urlList[3];
            jsonData.productPic5 = urlList[4];
            saveProducts(jsonData);
        });
    } else {
        var urlList = new Array("", "", "", "", "");
        for (var pos = 0; pos < photoList.length; pos++) {
            urlList[pos] = photoList.eq(pos).prev().children("img").attr("value");
        }

        jsonData.productPic1 = urlList[0];
        jsonData.productPic2 = urlList[1];
        jsonData.productPic3 = urlList[2];
        jsonData.productPic4 = urlList[3];
        jsonData.productPic5 = urlList[4];
        saveProducts(jsonData);
    }
}

function plusPhoto(url) {
    $("#photoPlusBtn")
        .before(
            "<div class=\"productpic\" style=\"position:relative\">\n" +
                "                <img src=\"" + url + "\" value=\"" + url + "\" class=\"productpic\">\n" +
                "                    <div><span class=\"circledel fa fa-remove\"></span></div>\n" +
                "                </div>\n" +
                "                <input type=\"file\" accept=\"image/*\" class=\"margint10 marginb10\">");

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
        checkFile(this, 4028, this.previousElementSibling.firstElementChild, 400, 200, "");
    });
}

function queryHunterBasic(jobHunterId) {
    var url = '/page/demo/jobHunter/complete?jobHunterId=' + jobHunterId;
    $.post(url, null, function (json) {
        var perc = 5;
        var percdec = 0;
        var data = $.parseJSON(json);
        if (data.job_hunter_detail == "ok") {
            percdec += 1;
        }
        if (data.job_hunter_edu_experience_list == "ok") {
            percdec += 1;
        }
        if (data.job_hunter_expect_list == "ok") {
            percdec += 1;
        }
        if (data.job_hunter_train_experience_list == "ok") {
            percdec += 1;
        }
        if (data.job_hunter_work_experience_list == "ok") {
            percdec += 1;
        }
        $("#percentval").text(percdec / 5 * 100 + "%");
        $("#percentimg").css("width", (percdec / 5) * 100 + '%');
        queryHunterBasicInfo(jobHunterId);
        queryExpect(jobHunterId);
        queryHunterWorkExperience(jobHunterId);
        queryHunterEduExperience(jobHunterId);
        queryHunterTrainExperience(jobHunterId);
    });
}
function saveDepict(btn, jobHunterId) {
    var jsonData = new Object();
    jsonData.jobHunterId = jobHunterId;
    jsonData.jobHunterDepict = $("#introduce textarea").val();
    saveProducts(jsonData);
}

function queryHunterBasicInfo(jobHunterId) {
    var avatarPath = $("#jobHunterAvatarPath").parent().find("input[type=file]");
    $("#avatarInputId").change(function () {
        checkComposeImgFile(this, 500, document.getElementById("jobHunterAvatarPath"), 150, 150, "");
    });
    url = '/page/demo/jobHunter/jobhunterbasic?jobHunterId=' + jobHunterId;
    $.post(url, null, function (data) {
        var value = data.job_hunter_detail;

        if (value != null) {
            if (regular(value.jobHunterCurrentStatus) != '') {
                $("#currentStatusList").find(".placeholder").text(value.jobHunterCurrentStatus);
            }
            queryCurrentStatusList();

            if (value.jobHunterAvatarPath != null) {
                $('#jobHunterAvatarPath').attr("src", "" + value.jobHunterAvatarPath);
                $('#jobHunterAvatarPath').attr("value", value.jobHunterAvatarPath);
            }
            setRadio($('input[name="inputHunterSex"]'), value.jobHunterSex);
            $('#inputName').attr("value", value.jobHunterName);

            var jobHunterBirthday = value.jobHunterBirthday;
            if (value.jobHunterBirthday != null && value.jobHunterBirthday != '') {
                setDateTime($("#inputBirthday input"), value.jobHunterBirthday);
            }

            if (value.jobHunterCurrentProvince != null && value.jobHunterCurrentProvince != '') {
                $("#provinceList").find(".placeholder").text(value.jobHunterCurrentProvince);
            }
            if (value.jobHunterCurrentCity != null && value.jobHunterCurrentCity != '') {
                $("#cityList").find(".placeholder").text(value.jobHunterCurrentCity);
            }
            if (value.jobHunterCurrentDistrict != null && value.jobHunterCurrentDistrict != '') {
                $("#areaList").find(".placeholder").text(value.jobHunterCurrentDistrict);
            }
            $("#specialty").val(value.jobHunterSpecialty);
            queryProvince();
            $("#phonenum").val(value.jobHunterTel);

            $("#introduce textarea").val(value.jobHunterDepict);
            $("#introduce textarea").next().find("span").text((500 - $("#introduce textarea").val().length));
            $("#photoPlusBtn").click(function () {
                plusPhoto("");
            });

            if (regular(value.productPic1) != '') {
                plusPhoto(value.productPic1);
            }
            if (regular(value.productPic2) != '') {
                plusPhoto(value.productPic2);
            }
            if (regular(value.productPic3) != '') {
                plusPhoto(value.productPic3);
            }
            if (regular(value.productPic4) != '') {
                plusPhoto(value.productPic4);
            }
            if (regular(value.productPic5) != '') {
                plusPhoto(value.productPic5);
            }
            $("#producturl").val(value.productUrl);
        }
    }, "json");
    queryMultiSpecialty();
}

function submitBasicInfoForm(jsonData) {
    jsonData.jobHunterName = $('#inputName').val();
    jsonData.jobHunterSex = getRadio($('input[name="inputHunterSex"]'));
    jsonData.jobHunterBirthday = $('#inputBirthday input').val();

    jsonData.jobHunterCurrentStatus = $("#currentStatusList").find(".placeholder").text();
    jsonData.jobHunterCurrentProvince = $("#provinceList").find(".placeholder").text();
    jsonData.jobHunterCurrentCity = $("#cityList").find(".placeholder").text();
    jsonData.jobHunterCurrentDistrict = $("#areaList").find(".placeholder").text();

    $.ajax({
        type: "post",
        url: '/page/demo/jobHunter/jobhunterbasicw',
        data: jsonData,
        dataType: "json",
        success: function (msg) {
            if (msg.retMsg == "success") {
                $.simplyToast('保存成功!', 'success');
            } else {
                $.simplyToast('保存失败', 'danger');
            }
        }
    }, "json");
}

function saveBasicInfo(btn, jobHunterId) {
    var form = $(btn).parents().find("#basicinfo");
    if (!formValidCheck(form))return false;
    var avatarPath = $("#jobHunterAvatarPath").parent().find("input[type=file]");
    if (avatarPath[0].files != null && avatarPath[0].files.length > 0) {

        uploadFile(avatarPath[0].files[0], "/page/demo/upload", "hunteravatar", jobHunterId, 1, function (msg) {

            var data = JSON.parse(msg);
            $("#jobHunterAvatarPath").attr("value", data.file_path);
            var jsonData = new Object();
            jsonData.jobHunterId = jobHunterId;
            jsonData.jobHunterAvatarPath = data.file_path;
            submitBasicInfoForm(jsonData);
        });
    } else {
        var jsonData = new Object();
        jsonData.jobHunterAvatarPath = $("#jobHunterAvatarPath").attr("value");
        jsonData.jobHunterId = jobHunterId;
        submitBasicInfoForm(jsonData);
    }
}

function queryExpect(jobHunterId) {
    var url = '/page/demo/jobHunter/jobhunterexpect?jobHunterId=' + jobHunterId;
    $.ajax({url: url, dataType: 'json', success: function (data) {
        var job_hunter_expect_list = data.job_hunter_expect_list;
        if (job_hunter_expect_list != null && job_hunter_expect_list.length > 0) {
            var job_hunter_expect = job_hunter_expect_list[0];
            $('#expectjob').val(job_hunter_expect.expectJob);
            if (regular(job_hunter_expect.expectSalary) != '') {
                $("#salaryList").find(".placeholder").text(job_hunter_expect.expectSalary);
            }
            $('#expectplace').val(job_hunter_expect.city);
            $('#workpattern').val(job_hunter_expect.workPattern);
        }
    }
    });
    queryMultiJob();//expectval
    queryMultiCity();
    querySalaryList();
}

function putExpJobToInput(btn) {
    var val = getPopVal(btn);
    $('#expectjob').val(val);
}

function putSpecialtyToInput(btn) {
    var val = getPopVal(btn);
    $('#specialty').val(val);
}

function putExpPlaceToInput(btn) {
    var val = getPopVal(btn);
    $('#expectplace').val(val);
}

function getPopVal(btn) {
    var val = '';
    $(btn).parents(".modal-content").find(".modalselected").find("a").each(function () {
        val += $(this).text() + ",";
    })
    if (val.length > 1) {
        val = val.substring(0, val.length - 1);
    }
    return val;
}

function saveExpect(btn, jobHunterId) {
    var form = $(btn).parents().find("#workexpect");
    if (!formValidCheck(form))return false;
    var jsonData = new Object();
    jsonData.city = $('#expectplace').val();
    jsonData.workPattern = $("#workpattern").find("option:selected").text();
    jsonData.expectJob = $('#expectjob').val();

    jsonData.expectSalary = $("#salaryList").find(".placeholder").text();
    if (jsonData.expectSalary == '期望薪水') {
        jsonData.expectSalary = "";
    }
    jsonData.jobHunterId = jobHunterId;
    $.ajax({
        type: "post",
        url: '/page/demo/jobHunter/addexpect',
        data: jsonData,
        dataType: "json",
        success: function (msg) {
            if (msg.retMsg == "success") {
                $.simplyToast('保存成功!', 'success');
            } else {
                $.simplyToast('保存失败', 'success');
            }

            $('.woo-main-body').data('jobHunterExpectId', msg.last_insert_id);
        }
    }, "json");
}

function savespecialty(btn, jobHunterId) {
    var jsonData = new Object();
    jsonData.jobHunterSpecialty = $('#specialty').val();
    jsonData.jobHunterId = jobHunterId;
    $.ajax({
        type: "post",
        url: '/page/demo/jobHunter/jobhunterbasicw',
        data: jsonData,
        dataType: "json",
        success: function (msg) {
            if (msg.retMsg == "success") {
                $.simplyToast('保存成功!', 'success');
            } else {
                $.simplyToast('保存失败', 'danger');
            }
        }
    }, "json");
}

function delWorkExp(ele, jobHunterWorkExperienceId) {
    $(ele).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (jobHunterWorkExperienceId != undefined) {
        var jsonData = new Object();
        jsonData.jobHunterWorkExperienceId = jobHunterWorkExperienceId;

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhunterworkexperienced',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('删除成功!', 'success');
                } else {
                    $.simplyToast('删除失败', 'fail');
                }
            }
        }, "json");
    }
}

function saveworkexperience(btn, jobHunterId) {
    var item = $(btn).parents(".item");
    var jsonData = new Object();
    jsonData.employerName = item.find(".companyname").val();
    jsonData.jobName = item.find(".jobName").val();
    jsonData.beginTime = item.find(".begintime").val();
    jsonData.endTime = item.find(".endtime").val();
    jsonData.workDesc = item.find(".workdesc").val();
    jsonData.salary = item.find(".salary").val();

    if (item.attr("data") == null || item.attr("data") == '') {
        jsonData.jobHunterId = jobHunterId;
        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/addworkexperience',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                    changeBtnStatus(this, "work_experience_btn");
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
                item.attr("data", msg.last_insert_id);
            }
        }, "json");
    } else {
        jsonData.jobHunterWorkExperienceId = item.attr("data");

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhunterworkexperiencew',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
            }
        }, "json");
    }
}

function addWorkExperience(job_hunter_work_experience, jobHunterId, index) {
    var expId = "";
    if (job_hunter_work_experience != undefined) {
        expId = job_hunter_work_experience.jobHunterWorkExperienceId;
    }
    if (index == undefined) {
        index = (new Date()).getMilliseconds();
    }
    $("#workexperience").children().last().before(
        "<div class=\"item marginb10\" data=\"" +
            expId +
            "\">\n" +
            "        <div class=\"form-group margint10\">\n" +
            "            <div class=\"row\">\n" +
            "                <label class=\"col-md-2 control-label\">公司名称</label>\n" +
            "                <input class=\"col-md-6 companyname\">\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"row\">\n" +
            "                    <label class=\"col-md-2 control-label\">职位</label>\n" +
            "                    <input class=\"col-md-6 jobname\">\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">在职时间</label>\n" +
            "\n" +
            "                        <div class=\"form-inline\">\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control begintime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"开始日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control endtime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"结束日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">工作描述</label>\n" +
            "\n" +
            "                        <div class=\"col-md-10 padding0\">\n" +
            "                            <textarea placeholder=\"工作描述\" class=\"valid textarea workdesc\"></textarea>\n" +
            " <div class=\"word_count\">你还可以输入 <span>500</span> 字</div>" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">月薪</label>\n" +
            "                        <input class=\"col-md-6 salary\">\n" +
            "                        </div>\n" +
            "                    </div>\n" +

            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-offset-9 col-md-3 paddingt10 paddingb10\">\n" +
            "                <ul class=\"list-inline\">\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-primary\"\n" +
            "                        type=\"submit\" onclick='saveworkexperience(this, " +
            jobHunterId +
            ")'>保存\n" +
            "                        </button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-secondary\" data-toggle=\"modal\" data-target=\"#workexp" +
            index +
            "\"\n" +
            "                        type=\"submit\">删除\n" +
            "                        </button>\n" +
            "                        <div class=\"modal fade\" id=\"workexp" +
            index +
            "\" tabindex=\"-1\" role=\"dialog\"\n" +
            "                        aria-hidden=\"true\">\n" +
            "                            <div class=\"modal-dialog\">\n" +
            "                                <div class=\"modal-content\">\n" +
            "                                    <div class=\"modal-header\">\n" +
            "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span\n" +
            "                                        aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>\n" +
            "                                        </button>\n" +
            "                                        <h4 class=\"modal-title fontem\">选择</h4>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-body\">\n" +
            "                                        <p>确定要删除吗？</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-footer\">\n" +
            "                                        <button type=\"button\" data-dismiss=\"modal\" class=\"btn btn-primary\"\n" +
            "                                        onclick=\"delWorkExp(this, " +
            expId +
            ")\">确定\n" +
            "                                        </button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "                </div>");
    var newNode = $("#workexperience").children(".item").last();
    if (job_hunter_work_experience != null) {
        newNode.find(".companyname").val(job_hunter_work_experience.employerName);
        newNode.find(".jobname").val(job_hunter_work_experience.jobName);
        setDateTime(newNode.find(".begintime"), job_hunter_work_experience.beginTime);
        setDateTime(newNode.find(".endtime"), job_hunter_work_experience.endTime);
        newNode.find(".workdesc").val(job_hunter_work_experience.workDesc);
        newNode.find(".workdesc").next().find("span").text((500 - job_hunter_work_experience.workDesc.length));
        newNode.find(".salary").val(job_hunter_work_experience.salary);
    }
    hideErrors();
    bindTextareaCheck();
}

function bindTextareaCheck() {
    $("textarea").bind('input propertychange', function () {
        checkLength(this, 500);
        $(this).next().find("span").text((500 - this.value.length));
    });
}

function queryHunterWorkExperience(jobHunterId) {
    var url = '/page/demo/jobHunter/jobhunterworkexperience?jobHunterId=' + jobHunterId;
    $.post(url, null, function (data) {
        var job_hunter_work_experience_list = data.job_hunter_work_experience_list;
        if (job_hunter_work_experience_list != null && job_hunter_work_experience_list.length > 0) {
            for (var i = 0; i < job_hunter_work_experience_list.length; ++i) {
                addWorkExperience(job_hunter_work_experience_list[i], job_hunter_work_experience_list[i].jobHunterId, i);
            }
        }
    }, "json");
}

function delEduExp(ele, jobHunterEduExperienceId) {
    $(ele).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (jobHunterEduExperienceId != undefined) {
        var jsonData = new Object();
        jsonData.jobHunterEduExperienceId = jobHunterEduExperienceId;

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntereduexperienced',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('删除成功!', 'success');
                } else {
                    $.simplyToast('删除失败', 'fail');
                }
            }
        }, "json");
    }
}

function saveeduexperience(btn, jobHunterId) {
    var item = $(btn).parents(".item");
    var jsonData = new Object();
    jsonData.college = item.find(".college").val();
    jsonData.major = item.find(".major").val();
    jsonData.beginTime = item.find(".begintime").val();
    jsonData.endTime = item.find(".endtime").val();
    jsonData.bachelorDegree = item.find(".bachelorDegreeList .placeholder").text();

    if (item.attr("data") == null || item.attr("data") == '') {
        jsonData.jobHunterId = jobHunterId;
        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/addedu',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
                item.attr("data", msg.last_insert_id);
            }
        }, "json");
    } else {
        jsonData.jobHunterEduExperienceId = item.attr("data");

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntereduexperiencew',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
            }
        }, "json");
    }
}

function addEduExperience(job_hunter_edu_experience, jobHunterId, index) {
    var expId = "";
    if (job_hunter_edu_experience != undefined) {
        expId = job_hunter_edu_experience.jobHunterEduExperienceId;
    }
    if (index == undefined) {
        index = (new Date()).getMilliseconds();
    }


    $("#eduexperience").children().last().before(
        "<div class=\"item marginb10\" data=\"" +
            expId +
            "\">\n" +
            "        <div class=\"form-group margint10\">\n" +
            "            <div class=\"row\">\n" +
            "                <label class=\"col-md-2 control-label\">学校名称</label>\n" +
            "                <input class=\"col-md-6 college\">\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"row\">\n" +
            "                    <label class=\"col-md-2 control-label\">专业</label>\n" +
            "                    <input class=\"col-md-6 major\">\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">就读时间</label>\n" +
            "\n" +
            "                        <div class=\"form-inline\">\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control begintime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"开始日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control endtime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"结束日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "    <div class=\"form-group\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-2 control-label\"><em class=\"clrred\">*</em> <label>学历</label></div>\n" +
            "            <div class=\"col-md-6 padding0\">\n" +
            "                <div class=\"bachelorDegreeList btn-group woo-btn-dropdown\">\n" +
            "                    <button type=\"button\" class=\"woo-check-drop-down btn btn-primary dropdown-toggle\"\n" +
            "                            data-toggle=\"dropdown\" aria-expanded=\"false\">\n" +
            "                        <span class=\"placeholder\">选择学历</span><span class=\"imageback downarrow size13\"></span></button>\n" +
            "                    <ul class=\"dropdown-menu\" role=\"menu\">\n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "                <div class=\"woo-error\">请选择学历</div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-offset-9 col-md-3 paddingt10 paddingb10\">\n" +
            "                <ul class=\"list-inline\">\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-primary\"\n" +
            "                        type=\"submit\" onclick='saveeduexperience(this, " +
            jobHunterId +
            ")'>保存\n" +
            "                        </button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-secondary\" data-toggle=\"modal\" data-target=\"#eduexp" +
            index +
            "\"\n" +
            "                        type=\"submit\">删除\n" +
            "                        </button>\n" +
            "                        <div class=\"modal fade\" id=\"eduexp" +
            index +
            "\" tabindex=\"-1\" role=\"dialog\"\n" +
            "                        aria-hidden=\"true\">\n" +
            "                            <div class=\"modal-dialog\">\n" +
            "                                <div class=\"modal-content\">\n" +
            "                                    <div class=\"modal-header\">\n" +
            "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span\n" +
            "                                        aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>\n" +
            "                                        </button>\n" +
            "                                        <h4 class=\"modal-title fontem\">选择</h4>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-body\">\n" +
            "                                        <p>确定要删除吗？</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-footer\">\n" +
            "                                        <button type=\"button\" data-dismiss=\"modal\" class=\"btn btn-primary\"\n" +
            "                                        onclick=\"delEduExp(this, " +
            expId +
            ")\">确定\n" +
            "                                        </button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "                </div>");
    var newNode = $("#eduexperience").children(".item").last();
    if (job_hunter_edu_experience != null) {
        newNode.find(".college").val(job_hunter_edu_experience.college);
        newNode.find(".major").val(job_hunter_edu_experience.major);
        setDateTime(newNode.find(".begintime"), job_hunter_edu_experience.beginTime);
        setDateTime(newNode.find(".endtime"), job_hunter_edu_experience.endTime);
        newNode.find(".bachelorDegreeList .placeholder").text(job_hunter_edu_experience.bachelorDegree);
    }
    hideErrors();
    queryBachelorDegreeListByNode(newNode.find(".bachelorDegreeList"));
}

function queryHunterEduExperience(jobHunterId) {
    var url = '/page/demo/jobHunter/jobhuntereduexperience?jobHunterId=' + jobHunterId;
    $.post(url, null, function (data) {
        var job_hunter_edu_experience_list = data.job_hunter_edu_experience_list;
        if (job_hunter_edu_experience_list != null && job_hunter_edu_experience_list.length > 0) {
            for (var i = 0; i < job_hunter_edu_experience_list.length; ++i) {
                addEduExperience(job_hunter_edu_experience_list[i], job_hunter_edu_experience_list[i].jobHunterId, i);
            }
        }
    }, "json");
}


function delTrainExp(ele, jobHunterTrainExperienceId) {
    $(ele).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (jobHunterTrainExperienceId != undefined) {
        var jsonData = new Object();
        jsonData.jobHunterTrainExperienceId = jobHunterTrainExperienceId;

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntertrainexperienced',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('删除成功!', 'success');
                } else {
                    $.simplyToast('删除失败', 'fail');
                }
            }
        }, "json");
    }
}

function savetrainexperience(btn, jobHunterId) {
    var item = $(btn).parents(".item");
    var jsonData = new Object();
    jsonData.courseName = item.find(".coursename").val();
    jsonData.trainInstitutionName = item.find(".traininstitutionname").val();
    jsonData.certificateName = item.find(".certificatename").val();
    jsonData.trainBeginDate = item.find(".begintime").val();
    jsonData.trainEndDate = item.find(".endtime").val();
    jsonData.trainDesc = item.find(".traindesc").val();

    if (item.attr("data") == null || item.attr("data") == '') {
        jsonData.jobHunterId = jobHunterId;
        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/addtrainexperience',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
                item.attr("data", msg.last_insert_id);
            }
        }, "json");
    } else {
        jsonData.jobHunterTrainExperienceId = item.attr("data");

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntertrainexperiencew',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
            }
        }, "json");
    }
}

function addTrainExperience(job_hunter_train_experience, jobHunterId, index) {
    var expId = "";
    if (job_hunter_train_experience != undefined) {
        expId = job_hunter_train_experience.jobHunterTrainExperienceId;
    }
    if (index == undefined) {
        index = (new Date()).getMilliseconds();
    }

    $("#trainexperience").children().last().before(
        "<div class=\"item marginb10\" data=\"" +
            expId +
            "\">\n" +
            "        <div class=\"form-group margint10\">\n" +
            "            <div class=\"row\">\n" +
            "                <label class=\"col-md-2 control-label\">培训课程</label>\n" +
            "                <input class=\"col-md-6 coursename\">\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"row\">\n" +
            "                    <label class=\"col-md-2 control-label\">培训机构</label>\n" +
            "                    <input class=\"col-md-6 traininstitutionname\">\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"row\">\n" +
            "                    <label class=\"col-md-2 control-label\">所获证书</label>\n" +
            "                    <input class=\"col-md-6 certificatename\">\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">培训时间</label>\n" +
            "\n" +
            "                        <div class=\"form-inline\">\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control begintime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"开始日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control endtime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"结束日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">培训描述</label>\n" +
            "\n" +
            "                        <div class=\"col-md-10 padding0\">\n" +
            "                            <textarea placeholder=\"培训描述\" class=\"valid textarea traindesc\"></textarea>\n" +
            " <div class=\"word_count\">你还可以输入 <span>500</span> 字</div>" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-offset-9 col-md-3 paddingt10 paddingb10\">\n" +
            "                <ul class=\"list-inline\">\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-primary\"" +
            "                        type=\"submit\" onclick='savetrainexperience(this, " +
            jobHunterId +
            ")'>保存\n" +
            "                        </button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-secondary\" data-toggle=\"modal\" data-target=\"#trainexp" +
            index +
            "\"\n" +
            "                        type=\"submit\">删除\n" +
            "                        </button>\n" +
            "                        <div class=\"modal fade\" id=\"trainexp" +
            index +
            "\" tabindex=\"-1\" role=\"dialog\"\n" +
            "                        aria-hidden=\"true\">\n" +
            "                            <div class=\"modal-dialog\">\n" +
            "                                <div class=\"modal-content\">\n" +
            "                                    <div class=\"modal-header\">\n" +
            "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span\n" +
            "                                        aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>\n" +
            "                                        </button>\n" +
            "                                        <h4 class=\"modal-title fontem\">选择</h4>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-body\">\n" +
            "                                        <p>确定要删除吗？</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-footer\">\n" +
            "                                        <button type=\"button\" data-dismiss=\"modal\" class=\"btn btn-primary\"\n" +
            "                                        onclick=\"delTrainExp(this, " +
            expId +
            ")\">确定\n" +
            "                                        </button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "                </div>");
    var newNode = $("#trainexperience").children(".item").last();
    if (job_hunter_train_experience != null) {
        newNode.find(".coursename").val(job_hunter_train_experience.courseName);
        newNode.find(".traininstitutionname").val(job_hunter_train_experience.trainInstitutionName);
        setDateTime(newNode.find(".begintime"), job_hunter_train_experience.trainBeginDate);
        setDateTime(newNode.find(".endtime"), job_hunter_train_experience.trainEndDate);
        newNode.find(".traindesc").val(job_hunter_train_experience.trainDesc);
    }
    hideErrors();
    bindTextareaCheck();
}

function queryHunterTrainExperience(jobHunterId) {
    var url = '/page/demo/jobHunter/jobhuntertrainexperience?jobHunterId=' + jobHunterId;
    $.post(url, null, function (data) {
        var job_hunter_train_experience_list = data.job_hunter_train_experience_list;
        if (job_hunter_train_experience_list != null && job_hunter_train_experience_list.length > 0) {
            for (var i = 0; i < job_hunter_train_experience_list.length; ++i) {
                addTrainExperience(job_hunter_train_experience_list[i], job_hunter_train_experience_list[i].jobHunterId, i);
            }
        }
    }, "json");
}


function delProduct(ele, jobHunterTrainExperienceId) {
    $(ele).parents(".item").remove();
    $("body").removeClass("modal-open").css("padding-right", "inherit");
    if (jobHunterTrainExperienceId != undefined) {
        var jsonData = new Object();
        jsonData.jobHunterTrainExperienceId = jobHunterTrainExperienceId;

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntertrainexperienced',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('删除成功!', 'success');
                } else {
                    $.simplyToast('删除失败', 'fail');
                }
            }
        }, "json");
    }
}

function saveproduct(btn, jobHunterId) {
    var item = $(btn).parents(".item");
    var jsonData = new Object();
    jsonData.courseName = item.find(".coursename").val();
    jsonData.trainInstitutionName = item.find(".traininstitutionname").val();
    jsonData.trainBeginDate = item.find(".begintime").val();
    jsonData.trainEndDate = item.find(".endtime").val();
    jsonData.trainDesc = item.find(".traindesc").val();

    if (item.attr("data") == null || item.attr("data") == '') {
        jsonData.jobHunterId = jobHunterId;
        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/addtrainexperience',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
                item.attr("data", msg.last_insert_id);
            }
        }, "json");
    } else {
        jsonData.jobHunterTrainExperienceId = item.attr("data");

        $.ajax({
            type: "post",
            url: '/page/demo/jobHunter/jobhuntertrainexperiencew',
            data: jsonData,
            dataType: "json",
            success: function (msg) {
                if (msg.retMsg == "success") {
                    $.simplyToast('保存成功!', 'success');
                } else {
                    $.simplyToast('保存失败', 'fail');
                }
            }
        }, "json");
    }
}

function addProduct(job_hunter_train_experience, jobHunterId, index) {
    var expId = "";
    if (job_hunter_train_experience != undefined) {
        expId = job_hunter_train_experience.jobHunterTrainExperienceId;
    }
    if (index == undefined) {
        index = (new Date()).getMilliseconds();
    }

    $("#trainexperience").children().last().before(
        "<div class=\"item marginb10\" data=\"" +
            expId +
            "\">\n" +
            "        <div class=\"form-group margint10\">\n" +
            "            <div class=\"row\">\n" +
            "                <label class=\"col-md-2 control-label\">培训课程</label>\n" +
            "                <input class=\"col-md-6 coursename\">\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"row\">\n" +
            "                    <label class=\"col-md-2 control-label\">培训机构</label>\n" +
            "                    <input class=\"col-md-6 traininstitutionname\">\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">培训时间</label>\n" +
            "\n" +
            "                        <div class=\"form-inline\">\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control begintime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"开始日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-3 input-group date datetimepicker-cls\">\n" +
            "                                <input type=\"text\" class=\"woo-check-input form-control endtime\"\n" +
            "                                data-date-format=\"YYYY/MM/DD\"\n" +
            "                                placeholder=\"结束日期\"/>\n" +
            "                                <span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"row\">\n" +
            "                        <label class=\"col-md-2 control-label\">培训描述</label>\n" +
            "\n" +
            "                        <div class=\"col-md-10 padding0\">\n" +
            "                            <textarea placeholder=\"培训描述\" class=\"valid textarea traindesc\"></textarea>\n" +
            " <div class=\"word_count\">你还可以输入 <span>500</span> 字</div>" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-offset-9 col-md-3 paddingt10 paddingb10\">\n" +
            "                <ul class=\"list-inline\">\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-primary\"\n" +
            "                        type=\"submit\" onclick='savetrainexperience(this, " +
            jobHunterId +
            ")'>保存\n" +
            "                        </button>\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <button class=\"btn btn-secondary\" data-toggle=\"modal\" data-target=\"#trainexp" +
            index +
            "\"\n" +
            "                        type=\"submit\">删除\n" +
            "                        </button>\n" +
            "                        <div class=\"modal fade\" id=\"trainexp" +
            index +
            "\" tabindex=\"-1\" role=\"dialog\"\n" +
            "                        aria-hidden=\"true\">\n" +
            "                            <div class=\"modal-dialog\">\n" +
            "                                <div class=\"modal-content\">\n" +
            "                                    <div class=\"modal-header\">\n" +
            "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span\n" +
            "                                        aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>\n" +
            "                                        </button>\n" +
            "                                        <h4 class=\"modal-title fontem\">选择</h4>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-body\">\n" +
            "                                        <p>确定要删除吗？</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"modal-footer\">\n" +
            "                                        <button type=\"button\" data-dismiss=\"modal\" class=\"btn btn-primary\"\n" +
            "                                        onclick=\"delTrainExp(this, " +
            expId +
            ")\">确定\n" +
            "                                        </button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "                </div>");
    var newNode = $("#trainexperience").children(".item").last();
    if (job_hunter_train_experience != null) {
        newNode.find(".coursename").val(job_hunter_train_experience.courseName);
        newNode.find(".traininstitutionname").val(job_hunter_train_experience.trainInstitutionName);
        setDateTime(newNode.find(".begintime"), job_hunter_train_experience.trainBeginDate);
        setDateTime(newNode.find(".endtime"), job_hunter_train_experience.trainEndDate);
        newNode.find(".traindesc").val(job_hunter_train_experience.trainDesc);
    }
    hideErrors();
    bindTextareaCheck();
}

function queryHunterdpict() {
    $("#majorId").val(regular(value.jobHunterMajor));
    $("#collegeId").val(regular(value.jobHunterCollege));
    $("#occupationId").val(regular(value.jobHunterLastOccupation));
    $("#employerId").val(regular(value.jobHunterLastEmployer));
    $("#photoPlusBtn").click(function () {
        plusPhoto("");
    });

    jsonData.jobHunterDepict = $('#selfProfile').val();
    jsonData.jobHunterBachelorDegree = $("#bachelorDegreeList").find(".placeholder")
        .text();
    if (jsonData.jobHunterBachelorDegree == '选择当前学历') {
        jsonData.jobHunterBachelorDegree = "";
    }
    jsonData.jobHunterStartWorkYear = $("#startWorkYearList").find(".placeholder")
        .text();
    if (jsonData.jobHunterStartWorkYear == '选择开始工作年份') {
        jsonData.jobHunterStartWorkYear = "";
    }

    jsonData.jobHunterMajor = $("#majorId").val();
    jsonData.jobHunterCollege = $("#collegeId").val();
    jsonData.jobHunterLastOccupation = $("#occupationId").val();
    jsonData.jobHunterLastEmployer = $("#employerId").val();
    jsonData.jobHunterSpecialty = getMultiSelect($('#woo-specialty-select-id'));
    if (jsonData.jobHunterSpecialty.indexOf("选择") > 0) {
        jsonData.jobHunterSpecialty = "";
    }
}


function importResume(btn, id) {
    if ($("#importFileId").val() == "") {
        $.simplyToast("请选择htm或者mht文件", "danger");
        return;
    }
    var formData = new FormData();
    formData.append("Filedata", $("#importFileId")[0].files[0]);
    formData.append("type", $('input[name="resumeTypeRadio"]:checked').val());
    formData.append("id", id);
    $.ajax({
        type: "POST",
        url: "/page/demo/importresume",
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (json) {
            var data = $.parseJSON(json);
            if (data.retCode == "fail") {
                $.simplyToast(data.retMsg, "danger");
            } else {
                $("body").removeClass("modal-open").css("padding-right", "inherit");
                $("#importResumeModal").removeClass("in");
                $("#importBtnId").text("已导入简历");
                $("#importBtnId").attr("disabled", true);
                $.simplyToast("成功导入简历", "success");
                queryHunterBasic(id);
                $.cookie('import_from_other', value.importFromOther, {expires: 1 });
            }
        }
    });
}


