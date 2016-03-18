<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>个人简历</title>
    <%@ include file="/jsp/comm/header.jsp" %>

<body>

<%@ include file="/jsp/comm/comm_top.jsp" %>

<div class="container main margint64 marginb64">
<div class="col-md-8">
<div id="basicinfo" class="form-horizontal">
    <div class="paddingl10 clrbedeaf4">
        <div class="row">
            <div class="col-md-offset-10 col-md-2 paddingt10">
                <button class="btn btn-primary" type="submit" onclick='saveBasicInfo(this, ${param.jobHunterId})'>保存
                </button>
            </div>
        </div>
        <div class="row margint10">
            <div class="col-md-3">
                <div class="halftransparent" style="position:relative;">
                    <img id="jobHunterAvatarPath" class="circle sizew150 sizeh150"
                         src="/img/avatar_default.jpg">
                    <input type="file" id="avatarInputId" accept="image/*"
                           class="circle sizew150 sizeh150 transparent pointer"
                           style="position: absolute;z-index: 2; top: 0px;">
                </div>
            </div>
            <div class="col-md-9">
                <div class="form-group">
                    <div class="col-md-3 control-label">
                        <em class="clrred">*</em> <label>姓名</label>
                    </div>
                    <div class="col-md-7">
                        <input id="inputName" class="woo-check-input form-control" placeholder="姓名"
                               value="" required maxlength=40>

                        <div class="woo-error">请填写姓名</div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-3 control-label">
                        <em class="clrred">*</em> <label>出生日期</label>
                    </div>
                    <div class="col-md-7">
                        <div class='input-group date datetimepicker-cls'
                             id='inputBirthday'>
                            <input type='text' class="woo-check-input form-control"
                                   data-date-format="YYYY/MM/DD"
                                   placeholder="出生日期"/>
                                    <span class="input-group-addon">
						                        <span class="fa fa-calendar"></span>
					                </span>
                        </div>
                        <div class="woo-error">请填写出生日期</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3 control-label">
                        <em class="clrred">*</em> <label>手机</label>
                    </div>
                    <div class="col-md-7">
                        <input id='phonenum' class="form-control woo-check-input" placeholder="请输入联系电话" required=""
                               pattern="[0-9]{7,13}">

                        <div class="woo-error">请输入手机号</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3 control-label">
                        <em class="clrred">*</em> <label>当前所在地</label>
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
                    <div class="col-md-3 control-label">
                        <label>当前状态</label>
                    </div>
                    <div class="col-md-7">
                        <div id="currentStatusList" class="btn-group woo-btn-dropdown">
                            <button type="button" class="btn dropdown-toggle woo-check-drop-down"
                                    data-toggle="dropdown"
                                    aria-expanded="false">
                                <span class="placeholder">选择当前状态</span>

                                <div class="padding0 textleft verticalmiddle"><span
                                        class="imageback downarrow size13"></span></div>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3 control-label">
                        <label>性别</label>
                    </div>
                    <div class="col-md-7">
                        <div class="radio">
                            <label class="radio-inline">
                                <input type="radio" name="inputHunterSex" value="男"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inputHunterSex" value="女"> 女
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="workexpect" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-list-alt font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 职业意向</span>
        </div>
        <div class="col-md-offset-4 col-md-2 paddingt10">
            <button class="btn btn-primary"
                    type="submit" onclick='saveExpect(this, ${param.jobHunterId})'>保存
            </button>
        </div>
    </div>
    <div class="form-group margint10">
        <div class="row">
            <div class="col-md-2 control-label"><em class="clrred">*</em> <label>期望职位</label></div>
            <input id="expectjob" class="col-md-6 woo-check-input" data-toggle="modal"
                   data-target="#expectjobmodal">

            <div class="woo-error">请填写期望职位</div>
            <div class="modal fade" id="expectjobmodal" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title fontem">选择</h4>
                        </div>
                        <div class="modal-body">
                            <ul id="selectexpectjobconditon" class="modalselected list-inline marginb10">
                            </ul>
                            <div class="input-group">
                                <input type="text" maxlength="20" class="form-control" autocomplete="off"
                                       id="useraddexpectjob">
                               <span class="input-group-btn">
        <button class="btn btn-primary" type="button" onclick="adduserExpectJob(this)">添加</button>
      </span>
                            </div>
                            <div id="expectjobcondition" class="conditiongroup collapse in clearboth">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" class="btn btn-primary"
                                    onclick="putExpJobToInput(this)">确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-2 control-label"><em class="clrred">*</em> <label>期望地点</label></div>
            <input id="expectplace" class="col-md-6 woo-check-input" data-toggle="modal"
                   data-target="#expectplacemodal">

            <div class="woo-error">请填写期望地点</div>
            <div class="modal fade" id="expectplacemodal" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title fontem">选择</h4>
                        </div>
                        <div class="modal-body">
                            <ul id="selectexpectplaceconditon" class="modalselected list-inline marginb10">
                            </ul>
                            <div id="expectplacecondition" class="conditiongroup sizemh260 scroll collapse in clearboth">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" class="btn btn-primary"
                                    onclick="putExpPlaceToInput(this)">确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-2 control-label"><label>类型</label></div>
            <div class="col-md-6 padding0">
                <select id="workpattern" class="form-control padding0 clrbda6a6a6">
                    <option value="全职">全职</option>
                    <option value="兼职">兼职</option>
                    <option value="实习">实习</option>
                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <div class="col-md-2 control-label"><em class="clrred">*</em> <label>期望月薪</label></div>
            <div class="form-group">
                <div id="salaryList" class="btn-group woo-btn-dropdown">
                    <button type="button" class="btn dropdown-toggle woo-check-drop-down" data-toggle="dropdown"
                            aria-expanded="false">
                        <span class="placeholder">期望薪水</span>
                        <span class="imageback downarrow size13"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                    </ul>
                    <div class="woo-error">请填写期望薪水</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="workexperience" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-briefcase font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 工作经历</span>
        </div>
    </div>
    <div class="clra6a6a6 text-center pointer clra6a6a6 text-center pointer solidborder1 paddingt10 paddingb10 marginr10"
         onclick="addWorkExperience(null, ${param.jobHunterId}, null)"><span class="plus verticalmiddle"></span>&nbsp;&nbsp;添加工作经历
    </div>
</div>

<div id="eduexperience" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-book font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 教育经历</span>
        </div>
    </div>
    <div class="clra6a6a6 text-center pointer clra6a6a6 text-center pointer solidborder1 paddingt10 paddingb10 marginr10"
         onclick="addEduExperience(null, ${param.jobHunterId}, null)"><span class="plus verticalmiddle"></span>&nbsp;&nbsp;添加教育经历
    </div>
</div>

<div id="trainexperience" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-signal font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 培训经历</span>
        </div>
    </div>
    <div class="clra6a6a6 text-center pointer clra6a6a6 text-center pointer solidborder1 paddingt10 paddingb10 marginr10"
         onclick="addTrainExperience(null, ${param.jobHunterId}, null)"><span class="plus verticalmiddle"></span>&nbsp;&nbsp;添加培训经历
    </div>
</div>
<div id="tecspecial" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="glyphicon glyphicon-bookmark font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 擅长技能</span>
        </div>
        <div class="col-md-offset-4 col-md-2 paddingt10">
            <button class="btn btn-primary"
                    type="submit" onclick='savespecialty(this, ${param.jobHunterId})'>保存
            </button>
        </div>
    </div>
    <div class="form-group margint10">
        <div class="row">
            <div class="col-md-2 control-label"><em class="clrred">*</em> <label>技能内容</label></div>
            <input id="specialty" class="col-md-6" data-toggle="modal"
                   data-target="#specialitymodal">

            <div class="modal fade" id="specialitymodal" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                            </button>
                            <h4 class="modal-title fontem">选择</h4>
                        </div>
                        <div class="modal-body">
                            <ul id="selectspecialityconditon" class="modalselected list-inline marginb10">
                            </ul>
                            <div class="input-group">
                                <input type="text" maxlength="20" class="form-control" autocomplete="off"
                                       id="useraddspeciality">
                               <span class="input-group-btn">
        <button class="btn btn-primary" type="button" onclick="adduserSpeciality(this)">添加</button>
      </span>
                            </div>
                            <div id="specialitycondition" class="conditiongroup collapse in clearboth">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" class="btn btn-primary"
                                    onclick="putSpecialtyToInput(this)">确定
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="works" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-picture-o font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 作品展示</span>
        </div>
        <div class="col-md-offset-4 col-md-2 paddingt10">
            <button class="btn btn-primary"
                    type="submit" onclick='saveProduct(this, ${param.jobHunterId})'>保存
            </button>
        </div>
    </div>
    <div class="form-group margint10">
        <div class="row">
            <div class="col-md-offset-2">
                <div id="photoList">
                    <button id="photoPlusBtn" class="btn plus verticalmiddle"></button>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <label class="col-md-2 control-label">个人主页</label>
            <input id="producturl" class="col-md-6" class="btn margint20 sizew80">
        </div>
    </div>
</div>
<div id="introduce" class="form-horizontal margint10 paddingl10 paddingr10 paddingb10 blockbackground">
    <div class="row margint10">
        <div class="col-md-6">
            <i class="fa fa-heart font24 verticalmiddle"></i>
            <span class="fontem font24 verticalmiddle">&nbsp;&nbsp; 自我评价</span>
        </div>
        <div class="col-md-offset-4 col-md-2 paddingt10">
            <button class="btn btn-primary"
                    type="submit" onclick='saveDepict(this, ${param.jobHunterId})'>保存
            </button>
        </div>
    </div>
    <div class="form-group margint10">
        <div class="row">
            <label class="col-md-2 control-label">自我评价</label>

            <div class="col-md-10 padding0">
                <textarea placeholder="自我评价" class="valid textarea workdesc-cls"></textarea>
            </div>
        </div>
    </div>
</div>
</div>
<div class="col-md-3 col-md-offset-1">
    <div class="clrbedeaf4 paddingt10 paddingl4r4 paddingb10">
        简历完整度： <span id="percentval" class="clrred">5%</span>

        <div class="progress margint10">
            <div id="percentimg" class="progress-bar clrb8e44ad" role="progressbar" aria-valuenow="60"
                 aria-valuemin="0" aria-valuemax="100">
                <span class="sr-only">60% Complete</span>
            </div>
        </div>
        <ul class="list-unstyled">
            <li class="marginb10 text-center">
                <button id="importBtnId" class="btn btn-primary sizew150"
                        data-toggle="modal" data-target="#importResumeModal"
                        onclick=""><span class="fa fa-download"></span>&nbsp;导入简历
                </button>
                <div class="modal fade" id="importResumeModal" tabindex="-1" role="dialog"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title fontem">导入简历</h4>
                            </div>
                            <div class="modal-body fontem">
                                <div id="resumeTypeId" class="radio">
                                    <label class="radio-inline">
                                        <input type="radio" name="resumeTypeRadio" id="resumeTypeRadio1" value="0"
                                               checked>
                                        导入智联招聘简历
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="resumeTypeRadio" id="resumeTypeRadio2" value="1">
                                        导入前程无忧简历
                                    </label>
                                </div>
                                <input id="importFileId" class="margint10" accept="text/html|text/mht" type="file">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="importResume(this, ${param.jobHunterId})">开始导入
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="text-center">
                <button class="btn btn-green sizew150"
                        onclick="javascript:window.location.href='detail_r.jsp?jobHunterId=${param.jobHunterId}'">
                    预览
                </button>
            </li>
        </ul>
    </div>
    <ul class="list-unstyled rightbar margint20">
        <li class="marginb10"><a href="#basicinfo"><i
                class="glyphicon glyphicon-user marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">基本信息</span></a></li>
        <li class="marginb10"><a href="#workexpect"><i class="fa fa-list-alt marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10  font20 verticalmiddle">职业意向</span></a></li>
        <li class="marginb10"><a href="#workexperience"><i class="fa fa-briefcase marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">工作经历</span></a></li>
        <li class="marginb10"><a href="#eduexperience"><i class="fa fa-book marginl10 font20  verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">教育经历</span></a></li>
        <li class="marginb10"><a href="#trainexperience"><i class="fa fa-signal marginl10 font20  verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">培训经历</span></a></li>
        <li class="marginb10"><a href="#tecspecial"><i
                class="glyphicon glyphicon-bookmark marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">擅长技能</span></a></li>
        <li class="marginb10"><a href="#works"><i class="fa fa-picture-o marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle"> 作品展示</span></a></li>
        <li class="marginb10"><a href="#introduce"><i class="fa fa-heart marginl10 font20 verticalmiddle"></i>
            <span class="fontem marginl10 font20 verticalmiddle">自我评价</span></a></li>
    </ul>
</div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        queryHunterBasic(escape('${param.jobHunterId}'));

        $("button[type='submit']").click(function () {
            submit();
        });

        $("#selfProfile").bind('input propertychange', function () {
            checkLength(this, 500);
            $(this).next().find("span").text((500 - this.value.length));
        });
        hideErrors();
        if ($.cookie('import_from_other') == 1) {
            $("#importBtnId").remove();
        }
    });

    $('#expectjobmodal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var expectjob = $('#expectjob').val();
        var textarray = expectjob.split(",");
        var selectcondition = $('#selectexpectjobconditon');
        selectcondition.html('');
        for (var i = 0; i < textarray.length; ++i) {
            selectcondition.append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
                    + 'onclick="removeselect(this)">'
                    + textarray[i]
                    + '</a> </li>');
            if (textarray[i] == $(this).text()) {
                $(this).css("color", "#a1a1a1");
                break;
            }
        }
        modal.find(".conditionitem a").each(function () {
            for (var i = 0; i < textarray.length; ++i) {
                if (textarray[i] == $(this).text()) {
                    $(this).css("color", "#a1a1a1");
                    break;
                }
            }
        })
    })

    function adduserExpectJob(btn) {
        $('#selectexpectjobconditon').append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
                + 'onclick="removeselect(this)">'
                + $("#useraddexpectjob").val()
                + '</a> </li>');
        $("#useraddexpectjob").val("");
    }

    $('#expectplacemodal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var expectjob = $('#expectplace').val();
        var textarray = expectjob.split(",");
        var selectcondition = $('#selectexpectplaceconditon');
        selectcondition.html('');

        for (var i = 0; i < textarray.length; ++i) {
            selectcondition.append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
                    + 'onclick="removeselect(this)">'
                    + textarray[i]
                    + '</a> </li>');
        }

        modal.find(".conditionitem a").each(function () {
            for (var i = 0; i < textarray.length; ++i) {
                if (textarray[i] == $(this).text()) {
                    $(this).css("color", "#a1a1a1");
                    break;
                }
            }
        });
    })

    $('#specialitymodal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var expectjob = $('#specialty').val();
        var textarray = expectjob.split(",");
        var selectcondition = $('#selectspecialityconditon');
        selectcondition.html('');
        for (var i = 0; i < textarray.length; ++i) {
            selectcondition.append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
                    + 'onclick="removeselect(this)">'
                    + textarray[i]
                    + '</a> </li>');
        }

        modal.find(".conditionitem a").each(function () {
            for (var i = 0; i < textarray.length; ++i) {
                if (textarray[i] == $(this).text()) {
                    $(this).css("color", "#a1a1a1");
                    break;
                }
            }
        });
    })
    function adduserSpeciality(btn) {
        $('#selectspecialityconditon').append('<li><a href="javascript:;" class="tag sizew120 marginb10 verticalmiddle"'
                + 'onclick="removeselect(this)">'
                + $("#useraddspeciality").val()
                + '</a> </li>');
        $("#useraddspeciality").val("");
    }
</script>
<script>
    $('body').on('mouseover', '.datetimepicker-cls', function () {
        $(this).datetimepicker({
            pickTime: false,
            language: 'zh-CN'
        });
    });

    $(window).on('beforeunload', function (event) {
        var message = '离开前请确认所有数据都已保存';
        event.returnValue = message;
        return message;
    });
</script>
</body>
</html>