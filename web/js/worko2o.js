jQuery.cookie = function (name, value, options) {
    if (typeof value != 'undefined') {
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = ';expires=' + date.toUTCString();
        }
        var path = options.path ? ';path=' + options.path : '';
        var domain = options.domain ? ';domain=' + options.domain : '';
        var secure = options.secure ? ';secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

function bindRotate180(clkel, el) {
    $(clkel).rotate({
        bind: {
            click: function () {
                if ($(el).getRotateAngle() == 180) {
                    $(el).rotate({animateTo: 0});
                } else {
                    $(el).rotate({animateTo: 180});
                }
            }
//        ,
//        mouseout : function() {
//            $(this).rotate({animateTo:0});
//        }
        }

    });
}

function customDropDown(dropdown, handler) {
    this.dropdown = dropdown;
    this.placeholder = this.dropdown.find(".placeholder");
    this.options = dropdown.find("ul.dropdown-menu > li");
    this.val = '';
    this.index = -1;// 默认为-1;
    this.handler = handler;
    this.initEvents();
}

customDropDown.prototype = {
    initEvents: function () {
        var obj = this;

        // 点击下拉列表的选项
        obj.options.on("click", function () {
            var opt = $(this);
            obj.text = opt.find("a").text();
            obj.val = opt.attr("value");
            obj.index = opt.index();
            obj.placeholder.text(obj.text);
            if (obj.text.indexOf("选择") >= 0) {
                showError(obj.placeholder.parent());
            } else {
                hideError(obj.placeholder.parent());
            }
            if (obj.handler != null) {
                obj.handler(obj);
            }
        });
    },
    getText: function () {
        return this.text;
    },
    getValue: function () {
        return this.val;
    },
    getIndex: function () {
        return this.index;
    }
}

$('body').on('mouseover', '.datetimepicker-cls', function () {
    $(this).datetimepicker({
        pickTime: false,
        language: 'zh-CN'
    });
});

function regular(value) {
    if (value == undefined) {
        return "";
    }
    return value;
}

function getTimeIdentity() {
    var d = (new Date());
    return d.getMilliseconds() + d.getSeconds()
}
//
//function checkLength(obj, maxlength) {
//    if (obj.value.length > maxlength) {
//        obj.value = obj.value.substring(0, maxlength);
//    }
//}

function isLeapYear(iYear) {//是否是闰年
    if (iYear % 4 == 0 && iYear % 100 != 0) {
        return true;
    } else {
        if (iYear % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}

String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}

String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
}

function setRadio(radio, value) {
    for (i = 0; i < radio.length; i++) {
        if (radio[i].value == value) {
            radio[i].checked = true;
        }
    }
}

function getRadio(radio) {
    for (i = 0; i < radio.length; i++) {
        if (radio[i].checked) {
            return radio[i].value;
        }
    }
    return '';
}

function setDateTime(input, time) {
    if (time != null && input != null) {
        time = time.substring(0, time.indexOf(' '));
        time = time.replaceAll('-', '/');

        input.attr("value", time);
    }
}

function formatDateTime(time) {
    if (time != null) {
        time = time.substring(0, time.indexOf(' '));
        time = time.replaceAll('-', '/');
    }
    return time;
}


function newDate(str) {
    str = str.split('-');
    var date = new Date();
    date.setUTCFullYear(str[0], str[1] - 1, str[2]);
    date.setUTCHours(0, 0, 0, 0);
    return date;
}

function newDateAndTime(dateStr) {
    if (dateStr == undefined) {
        return null;
    }
    var ds = dateStr.split(" ")[0].split("-");
    var ts = dateStr.split(" ")[1].split(":");
    var r = new Date();
    r.setFullYear(ds[0], ds[1] - 1, ds[2]);
    r.setHours(ts[0], ts[1], ts[2], 0);
    return r;
}

function getDateStr(datetime) {
    return ((datetime.getMonth() + 1) + '月' + datetime.getDate() + '日');
}

function getTimeStr(datetime) {
    return (datetime.getHours() + ':' + (datetime.getMinutes()));
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

function dayInterval(datebegin, dateend) {
    if (datebegin == undefined || dateend == undefined) {
        return 0;
    }
    return parseInt((dateend.getTime() - datebegin.getTime()) / (1000 * 60 * 60 * 24));
}

function uploadFile(file, url, type, id, index, hander) {

    var formData = new FormData();
    formData.append("Filedata", file);
    formData.append("type", type);
    formData.append("id", id);
    if (index != undefined) {
        formData.append("index", index);
    }

    $.ajax({
        type: "post",
        url: url,
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (msg) {
            if (hander != null) {
                hander(msg);
            }
        }
    });
}

function uploadFileInner(ret, pos, files, url, type, id, hander) {
    if (pos >= 0 && pos < files.length) {
        uploadFile(files[pos], url, type, id, pos, function (msg) {
            ret.push(msg);
            uploadFileInner(ret, pos + 1, files, url, type, id, hander);
            if (pos == files.length - 1) {
                if (hander != null) {
                    hander(ret);
                }
            }
        })
    }
}

function uploadFiles(files, url, type, id, hander) {
    var ret = new Array();

    uploadFileInner(ret, 0, files, url, type, id, hander);

    return ret;
}

function previewImage(file, img, maxWidth, maxHeight) {
    if (file.files && file.files[0]) {
        //div.innerHTML = div.innerHTML;
        //var img = document.getElementById('imghead');

        img.onload = function () {
            var rect = clacImgZoomParam(maxWidth, maxHeight,
                img.offsetWidth, img.offsetHeight);

            //alert("-offsetWidth--+" + img.offsetWidth);
            //alert("--offsetHeight-+" + img.offsetHeight);

            img.width = rect.width;
            img.height = rect.height;

            img.style.marginLeft = rect.left + 'px';
            img.style.marginTop = rect.top + 'px';

        }

        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }

        reader.readAsDataURL(file.files[0]);
    }
    else {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';

        file.select();

        var src = document.selection.createRange().text;

        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;

        var rect = clacImgZoomParam(maxWidth, maxHeight, img.offsetWidth,
            img.offsetHeight);

        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
            + ',' + rect.height);

        div.innerHTML = "<div style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
    }
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {

    var param = {
        top: 0,
        left: 0,
        width: width,
        height: height
    };

    if (width > maxWidth || height > maxHeight) {

        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);

    return param;
}


function checkFile(inputBtn, sizeLimit, preview, width, height, defaultImg) {
    var filePath = $(inputBtn).val();
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        $.simplyToast('图片限于bmp,png,gif,jpeg,jpg格式!', 'danger');
        var prevNode = $(inputBtn).prev();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertAfter(prevNode);
        $(cfile).change(function () {
            checkFile($(this), sizeLimit, preview, width, height, defaultImg);
        });
        $(preview).attr("src", defaultImg);
        return false;
    }
    if (inputBtn.files[0].size > sizeLimit * 1024) {
        $.simplyToast("图片不能大于" + sizeLimit + "K", 'danger');
        var prevNode = $(inputBtn).prev();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertAfter(prevNode);
        $(cfile).change(function () {
            checkFile(this, sizeLimit, preview, width, height, defaultImg);
        });
        $(preview).attr("src", defaultImg);
        return false;
    }
    previewImage(inputBtn, preview, width, height);
    return true;
}

function checkNoImageFile(inputBtn, sizeLimit) {
    var filePath = $(inputBtn).val();
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        $.simplyToast('图片限于bmp,png,gif,jpeg,jpg格式!', 'danger');
        var nextNode = $(inputBtn).next();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertBefore(nextNode);
        $(cfile).change(function () {
            checkNoImageFile(this, sizeLimit);
        });
        return false;
    }
    if (inputBtn.files[0].size > sizeLimit * 1024) {
        $.simplyToast("图片不能大于" + sizeLimit + "K", 'danger');
        var nextNode = $(inputBtn).next();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertBefore(nextNode);
        $(cfile).change(function () {
            checkNoImageFile(this, sizeLimit);
        });
        return false;
    }
    return true;
}

function checkComposeImgFile(inputBtn, sizeLimit, preview, width, height, defaultImg) {
    var filePath = $(inputBtn).val();
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        $.simplyToast('图片限于bmp,png,gif,jpeg,jpg格式!', 'danger');
        var previousNode = $(inputBtn).previousNode();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertAfter(previousNode);
        $(cfile).change(function () {
            checkFile($(this), sizeLimit, preview, width, height, defaultImg);
        });
        $(preview).attr("src", defaultImg);
        return false;
    }
    if (inputBtn.files[0].size > sizeLimit * 1024) {
        $.simplyToast("图片不能大于" + sizeLimit + "K", 'danger');
        var previousNode = $(inputBtn).previousNode();
        var cfile = $(inputBtn).clone();  //复制当前file
        $(inputBtn).remove();     //移除当前file
        cfile.insertAfter(previousNode);
        $(cfile).change(function () {
            checkFile(this, sizeLimit, preview, width, height, defaultImg);
        });
        $(preview).attr("src", defaultImg);
        return false;
    }
    previewImage(inputBtn, preview, width, height);
    return true;
}

function plusTag(value) {
    $("#tagPlusBtn")
        .before(
            '        <div class="input-group woo-tags" >'
                + '          <input class="form-control" placeholder="" value="' + value + '"'
                + '                required="" maxlength=6 style=" visibility: inherit">'
                + '          <span class="input-group-addon" style="padding: 0px"> <button class="fa fa-remove" style="background: transparent; height: 100%; border: 0px"></button></span>'
                + '      </div>');

    if ($("#tagList div input").size() >= 5) {
        $("#tagPlusBtn").hide();
    }

    $("#tagList div span").click(function () {
        $(this).parent().remove();

        if ($("#tagList div input").size() < 5) {
            $("#tagPlusBtn").show();
        }
    });
}

function plusqq(value) {
    $("#qqPlusBtn")
        .before(
            '        <div class="input-group" >'
                + '          <input id="qq" class="form-control" placeholder="qq号码" value="' + value + '"'
                + '                required="" maxlength=11 style=" visibility: inherit">'
                + '          <span class="input-group-addon" style="padding: 0px"> <button class="fa fa-remove" style="background: transparent; height: 100%; border: 0px"></button></span>'
                + '      </div>');

    $("#qqPlusBtn").hide();

    $("#qqPlusBtn").parent().find("div span").click(function () {
        $(this).parent().remove();

        if ($("#qqPlusBtn").parent().find("div input").size() < 1) {
            $("#qqPlusBtn").show();
        }
    });
}

function calcusYear(value) {
    if (value == undefined) {
        return "";
    }
    var birth = value.substring(0, value.indexOf('-'));
    var d = new Date();
    var vYear = d.getFullYear();
    return vYear - parseInt(birth);
}


function salaryVal(salaryBegin, salaryEnd) {
    var salary = '';
    if (salaryBegin == 0 && 0 == salaryEnd) {
        salary = '面议';
    } else if (salaryBegin == 0) {
        salary = salaryEnd + "元";
    } else if (salaryEnd == 0) {
        salary = salaryBegin + "元";
    } else if (salaryBegin == salaryEnd) {
        salary = salaryBegin + "元";
    } else {
        salary = salaryBegin
            + "-"
            + salaryEnd + "元";
    }
    return salary;
}

function emailValid(val) {
//    var search_str = /^w+((-w+)|(.w+))*@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)*.[A-Za-z0-9]+$/;
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    if (!pattern.test(val)) {
        return false;
    }
    return true;
}

function checkValidCode(val) {
    var temp = $("#validImgValId").val();
    if (val == $("#validImgValId").val()) {
        return true;
    } else {

        return false;
    }
}

function rand() {
    var rand = "" + (Math.random() * 10000);
    rand = rand.substring(0, rand.indexOf("."));
    switch (rand.length) {
        case 1:
            rand = "000" + rand;
            break;
        case 2:
            rand = "00" + rand;
            break;
        case 3:
            rand = "0" + rand;
            break;
        default:
            rand = rand.substring(0, 4);
            break;
    }
    return rand;
}

function setImgUrl() {
    var vall = rand();

    $("#validImgId").attr("src", "/jsp/reg/code.jsp?rand=" + vall);
    $("#validImgValId").val(vall);
    return vall;
}

function setBarStatus() {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/page/demo/getaccount",
        success: function (data) {
            if (data.userType == 'hunter') {
//                $("#nav-right").html("");
                $("#nav-right").html(
                    '<li><a id="noticeId" class="woo-nav-item" href="/jsp/hunter/apply.jsp?jobHunterId=' +
                        data.jobHunterId +
                        '" title="通知"><span class="fa fa-flag font20"></span>' +
                        '</a></li>' +
                        '<li><a id="envelopeId" class="woo-nav-item" href="/chatlist.jsp?jobHunterId=' +
                        data.jobHunterId +
                        '&type=0" title="私信"><span class="fa fa-envelope font20"></span>' +
                        '</a></li>' +
                        '<li><a class="woo-nav-item" href="/jsp/hunter/favorite.jsp?jobHunterId=' +
                        data.jobHunterId +
                        '" title="收藏"><span class="fa fa-star font20"></span></a></li>' +
                        '<li>' +
                        '<li id="fat-menu" class="dropdown">' +
                        '<a id="drop3" href="#" class="woo-nav-item dropdown-toggle js-activated sizeh54" data-toggle="dropdown" aria-expanded="false">' +
                        '<img class="size20" src="' +
                        regular(data.avatarPath == undefined ? "/img/avatar_male.png" : data.avatarPath) +
                        '">' +
                        '</a>' +
                        '<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/hunter/basic_info_w.jsp?jobHunterId=' +
                        data.jobHunterId +
                        '">我的简历</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/hunter/setting.jsp?jobHunterId=' +
                        data.jobHunterId +
                        '">设置</a></li>' +
                        '<li role="presentation" class="divider"></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="exit(\'hunter\')">退出</a></li>' +
                        '</ul>' +
                        '</li>');
                $('.js-activated').dropdownHover().dropdown();
                if (data.interactGot != 1) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        data: $.param({jobHunterId: data.jobHunterId}),
                        url: "/page/demo/job/interact/hunter/myinteractjobs",
                        success: function (data) {
                            var jIList = data.job_interact_list;
                            var num = 0;
                            for (var i = 0; i < jIList.length; ++i) {
                                if (jIList[i].employerStatusChanged != 0) {
                                    ++num;
                                }
                            }
                            if (num > 0) {
                                $("#noticeId").append("<span class=\"badge woo-pic-badage\">" + num + "</span>");
                            }
                        }
                    });
                }

                if (data.envelopeGot != 1) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        data: $.param({jobHunterId: data.jobHunterId}),
                        url: "/page/demo/job/envelope/getunreadnumbyhunterid",
                        success: function (data) {
                            var num = data.envelope_unread_num;
                            if (num > 0) {
                                $("#envelopeId").append("<span class=\"badge woo-pic-badage\">" + num + "</span>");
                            }
                        }
                    });
                }
            } else if (data.userType == 'employer') {
                $("#nav-right").html(
                    '<li><a id="noticeId" class="woo-nav-item" href="/jsp/employer/employer_jobs.jsp?employerId=' +
                        data.employerId +
                        '" title="通知"><span class="fa fa-flag font20"></span>' +
                        '</a></li>' +
                        '<li><a  id="envelopeId" class="woo-nav-item" href="/chatlist.jsp?employerId=' +
                        data.employerId +
                        '&type=1" title="私信"><span class="fa fa-envelope font20"></span>' +
                        '</a></li>' +
                        '<li id="fat-menu" class="dropdown">' +
                        '<a id="drop3" href="#" class="woo-nav-item dropdown-toggle js-activated sizeh54" data-toggle="dropdown" aria-expanded="false">' +
                        '<img class="size20" src="' +
                        regular(data.logoPath == undefined ? "/img/companydefault.png" : data.logoPath) +
                        '">' +
                        '</a>' +
                        '<ul class="dropdown-menu" role="menu" aria-labelledby="drop3">' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/job_detail_w.jsp?employerId=' +
                        data.employerId +
                        '">发布职位</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/employer_detail_w.jsp?employerId=' +
                        data.employerId +
                        '">企业资料</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/resume_search.jsp?employerId=' +
                        data.employerId +
                        '">简历搜索</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/employer_jobs.jsp?employerId=' +
                        data.employerId +
                        '">已发布职位</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/favorite.jsp?employerId=' +
                        data.employerId +
                        '">收藏的简历</a></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="/jsp/employer/setting.jsp?employerId=' +
                        data.employerId +
                        '">账号设置</a></li>' +
                        '<li role="presentation" class="divider"></li>' +
                        '<li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="exit(\'employer\')">退出</a></li>' +
                        '</ul>' +
                        '</li>');
                $('.js-activated').dropdownHover().dropdown();
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    data: $.param({employerId: data.employerId}),
                    url: "/page/demo/job/interact/employer/myinteractjobs",
                    success: function (data) {
                        var jIList = data.job_interact_list;
                        var num = 0;
                        var joinNum = 0;
                        var acceptNum = 0;
                        if (jIList != null) {
                            for (var i = 0; i < jIList.length; ++i) {
                                if (jIList[i].hunterStatusChanged != 0) {
                                    ++num;
                                }
                                var dayI = dayInterval(newDateAndTime(jIList[i].hunterUpdateTime), new Date());
                                if (jIList[i].hunterStatus == 3 && jIList[i].employerStatusChanged == 0 && jIList[i].joinFeedback == 0 &&
                                    dayI >= 1) {
                                    joinNum++;
                                }
                                var dayI2 = dayInterval(newDateAndTime(jIList[i].employerUpdateTime), new Date());
                                if (jIList[i].joinFeedback == 1 && jIList[i].acceptFeedback == 0 &&
                                    dayI2 >= 1) {
                                    acceptNum++;
                                }
                            }
                        }

                        if (data.interactGot != 1) {
                            if (num > 0) {
                                $("#noticeId").append("<span class=\"badge woo-pic-badage\">" + num + "</span>");
                            }
                        }
                        if (joinNum > 0) {
                            $("#nav-right").prepend("<li><a class=\"woo-nav-item\" title='反馈' href=\"/jsp/employer/job_petitioners.jsp?feedback=1&employerId=" +
                                jIList[0].employerId +
                                "\"><span class='fa fa-bullhorn font20'></span><span class=\"badge woo-pic-badage\">" + joinNum + "</span></a></li>")
                        } else if (acceptNum > 0) {
                            $("#nav-right").prepend("<li><a class=\"woo-nav-item\" title='反馈' href=\"/jsp/employer/job_petitioners.jsp?feedback=2&employerId=" +
                                jIList[0].employerId +
                                "\"><span class='fa fa-bullhorn font20'></span><span class=\"badge woo-pic-badage\">" + acceptNum + "</span></a></li>")
                        }
                    }
                });

                if (data.envelopeGot != 1) {
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        data: $.param({jobHunterId: data.jobHunterId}),
                        url: "/page/demo/job/envelope/getunreadnumbyemployerid",
                        success: function (data) {
                            var num = data.envelope_unread_num;
                            if (num > 0) {
                                $("#envelopeId").append("<span class=\"badge woo-pic-badage\">" + num + "</span>");
                            }
                        }
                    });
                }
            } else {
                $("#nav-right").html('<li><a class="woo-nav-item font15" href="/login.jsp">登录</a></li>' +
                    '<li><a class="woo-nav-item font15" href="/jsp/reg/reg.jsp">注册</a></li>');
            }
        }
    });
}

//$("body").on("mouseenter","#drop3", function(){
//    $('.dropdown').addClass("open");
//});
//
//$("body").on("mouseleave","#drop3", function(){
//    $('.dropdown').removeClass("open");
//});


function exitResetCookie() {
    $.cookie('a', null, {path: "/", domain: "maojuren.com" });
    $.cookie('b', null, {path: "/", domain: "maojuren.com" });
    $.cookie('d', null, {path: "/", domain: "maojuren.com" });
    $.cookie('e', null, {path: "/", domain: "maojuren.com" });
    $.cookie('c', null, {path: "/", domain: "maojuren.com" });
    $("#nav-right").html('<li><a class="woo-nav-item font15" href="/login.jsp">登录</a></li>' +
        '<li><a class="woo-nav-item font15" href="/jsp/reg/reg.jsp">注册</a></li>');
}

function exit(usertype) {
    if (usertype == 'hunter') {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://www.maojuren.com/page/demo/jobHunter/logout",
            success: function (data) {
                exitResetCookie();
            }
        });

    } else {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://www.maojuren.com/page/demo/employer/logout",
            success: function (data) {
                exitResetCookie();
            }
        });
    }
}

function login(redirectUrl, param_data) {
    if (!formValidCheck())return false;
    var userType;
    var inperr;
    if (param_data == null) {
        userType = $('.main-navigation .active').attr("value");
        var account = $("#account_ipt").val();
        var password = $.md5($("#password_ipt").val());
        inperr = $(".loginaccounterr");
        param_data = new Object();
        if (userType == 1) {
            param_data.jobHunterEmail = account;
            param_data.jobHunterPassword = password;
        } else {
            param_data.hrEmail = account;
            param_data.password = password;
        }
    } else {
        userType = param_data.userType;
    }

    if (userType == 1) {

        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            data: $.param(param_data),
            url: "/page/demo/jobHunter/login",
            success: function (data) {
                if (data.retCode == "fail") {
                    if (inperr != undefined) {
                        inperr.show();
                    }
                    return false;
                }
                var value = data.job_hunter_detail;
                $.cookie('a', value.jobHunterEmail, { expires: 3, path: "/", domain: "maojuren.com" });
                $.cookie('b', value.jobHunterPassword, { expires: 3, path: "/", domain: "maojuren.com" });
                $.cookie('c', 'hunter', { expires: 3, path: "/", domain: "maojuren.com" });
                $('form').fadeOut(500);
                $('.wrapper').addClass('form-success');
                window.setTimeout(_goToUrl(redirectUrl), 500);

            }
        });
    } else {
        $.ajax({
            type: "POST",
            dataType: "json",
            data: $.param(param_data),
            url: "/page/demo/employer/login",
            success: function (data) {
                if (data.retCode == "fail") {
                    if (inperr != undefined) {
                        inperr.show();
                    }
                    return false;
                }
                var value = data.employer_detail;
                $.cookie('d', value.hrEmail, { expires: 3, path: "/", domain: "maojuren.com" });
                $.cookie('e', value.password, { expires: 3, path: "/", domain: "maojuren.com" });
                $.cookie('c', 'employer', { expires: 3, path: "/", domain: "maojuren.com" });
                $('form').fadeOut(500);
                $('.wrapper').addClass('form-success');
                window.setTimeout(_goToUrl(redirectUrl), 500);
            }
        });
    }
}

function goToUrl(redirectUrl) {
    if (redirectUrl == 'null' || redirectUrl.indexOf("login") > 0 || redirectUrl.indexOf("job_detail_r") < 0) {
        window.location.href = "/index.jsp";
    } else {
        window.location.href = redirectUrl;
    }
}

function _goToUrl(redirectUrl) {
    return function () {
        goToUrl(redirectUrl);
    }
}

function goSingleLoginUrl(type) {
    var userType = $('.main-navigation .active').attr("value");
    if (userType == 1) {
        $.cookie('c', 'hunter', { expires: 3, path: "/", domain: "maojuren.com" });
    } else if (userType == 2) {
        $.cookie('c', 'employer', { expires: 3, path: "/", domain: "maojuren.com" });
    }
    window.location.href = "/page/demo/connet?type=" + type;
}

function isMobile() {
    var userAgent = navigator.userAgent;
    var index = userAgent.indexOf("Android");
    index = Math.max(index, userAgent.indexOf("iPhone"));
    if (index >= 0) {
        return true;
    } else {
        return false;
    }
}

function emailUrl(email) {
    window.location.href = 'http://mail.' + email.substr(email.indexOf("@") + 1);
}

function setNotSelectedValueToNull(val) {
    if (val == undefined) {
        val = '';
    } else if (val.indexOf("选择") >= 0) {
        val = '';
    }
    return val;
}

function getExperienceByStartWorkYear(val) {
    var experienceYear;
    if (val == undefined) {
        return '';
    }
    if (val == '应届毕业生' || val == '在校生') {
        experienceYear = val + ' / ';
    } else if (val.indexOf("以前") >= 0) {
        experienceYear = '13年以上工作经验';
    } else {
        var date = new Date();
        experienceYear = (date.getFullYear() - val) + '年工作经验/ ';
    }
    return experienceYear;
}

$(function () {
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
    $(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() > 100) {
                $("#back-to-top").fadeIn(1000);
            }
            else {
                $("#back-to-top").fadeOut(1000);
            }
        });

        //当点击跳转链接后，回到页面顶部位置

        $("#back-to-top").click(function () {
            $('body,html').animate({scrollTop: 0}, 500);
            return false;
        });
    });
});


$(function () {
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
    $(function () {
        $(document).ready(function () {
            $(".rightbar li").click(function () {
                $(".rightbar li").removeClass("active");
                $(this).addClass("active");
            });
            $(".bottombar li").click(function () {
                $(".bottombar li").removeClass("active");
                $(this).addClass("active");
            });
        })
    });
});

//input自动增长
function checkLength(which, maxlen) {
//    var maxchar = 200;
    iCount = which.value.length;
    //.replace(/[^\u0000-\u00ff]/g, "aa")
    if (iCount <= maxlen) {
        which.size = iCount + 2;
    }
    else alert("请不要超过" + maxlen);
}


$(function () {
    var options = {
        html: true,
        placement: 'left',
        trigger: 'hover',
        content: '<img src="/img/maojurencode.jpg" class="sizew150 sizeh150">'
    }
    $("#codemain").popover(options);
});
