<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>设置</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="col-md-8">
        <ul id="setting_type" class="nav nav-tabs">
            <li value="1" id="pwdId" role="presentation" class="active"><a href="#pwdContentId">修改密码</a></li>
            <li value="2" id="instructionId" role="presentation"><a href="#instructionContentId">使用说明</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div role="tabpanel" class="tab-pane clrbedeaf4 in active" id="pwdContentId" aria-labelledby="pwdId">
                <div class="sizew400 marginb64 paddingt20 paddingb20">
                    <div role="form" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-4">当前密码:</label>

                            <div class="col-md-8">
                                <input id="oldpwdId" type="password" class="form-control"
                                       placeholder="请输入密码" required=""
                                       autofocus="">
                                <label id="currentErrorId" class="woo-error">当前密码输入错误</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">输入新密码:</label>

                            <div class="col-md-8">
                                <input id="spwdId" type="password" class="form-control" placeholder="请输入密码"
                                       required="" id="password" name="password" onBlur="validPassword()">

                                <div id="reg_password_error"
                                     class="woo-normal-margin-left woo-small-font-size woo-big-margin-bottom"
                                     style="color: red; display: none">
                                    <b id="regPassword_error_span_i">密码至少为6位，区分大小写</b>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">再次输入新密码</label>

                            <div class="col-md-8">
                                <input id="sspwdId" type="password" class="form-control"
                                       placeholder="确认密码" required="" autofocus="">

                                <div id="errorId1" class="woo-error">请保证确认密码和输入密码一致</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">验证码:</label>

                            <div class="col-md-8">
                                <input id="validCodeId" class="form-control" type=text name=rand maxlength=4 value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <img id="validImgId" border=0 src="#">

                                <div>
                                    <a style="cursor:hand; font-size: 14px" onclick="setImgUrl()">看不清换一张</a>
                                    <input type="hidden" id="validImgValId">
                                </div>
                                <label id="errorLblId" class="woo-error">验证码错误</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button id="submitId" class="btn btn-primary" type="submit"
                                        onclick="submit()">
                                    保&nbsp;&nbsp;存
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="instructionContentId" aria-labelledby="instructionId">
                <div class="woo-form woo-form-max-width-2">
                    <h4 class="fontem">用户信息反馈：</h4>

                    <p>猫举人网提供了对求职者行为进行反馈的环节。</p>
                    <ol>
                        <li>应约率：在对方接受了面试邀请后，对方是否在接收到您的电话邀请后，确实去面试了，需要您给予确认，并统计总的邀请数量计算应约率。</li>
                        <li>跳失率：已经给应聘者发放了offer，但是结果对方没有来上班，HR在网站会对事实进行确认，增加对方的跳失率。</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="employer_nav.jsp" %>
</div>
<%@ include file="/jsp/comm/comm_foot.jsp" %>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#setpage").addClass("active");
        $('#setting_type li>a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
        setImgUrl();
        hideErrors();
    });

    function submit() {
        if (checkValidCode($("#validCodeId").val()) && $("#spwdId").val() == $("#sspwdId").val()) {
            var password = $("#spwdId").val();
            var md5password = $.md5(password);
            var param_data = {
                oldPassword: $.md5($("#oldpwdId").val()),
                hrEmail: '${sessionScope.username}',
                password: md5password
            }
            $.post('/page/demo/employer/resetpassword', $.param(param_data), function (data) {
                if ($.parseJSON(data).retCode == 'fail') {
                    $("#currentErrorId").show();
                } else {
                    $("#currentErrorId").hide();
                    //修改密码
                    $.simplyToast("保存成功！", "success");
                }
            });
        } else {
            if ($("#spwdId").val() != $("#sspwdId").val()) {
                $("#errorId1").show();
            } else {
                $("#errorLblId").show();
            }
        }
    }

    function validPassword() {
        if ($("#spwdId").val().length < 6) {
            $('#reg_password_error').show();
            $('#submitId').attr("disabled", true);
        } else {
            $('#reg_password_error').hide();
            $('#submitId').attr("disabled", false);
        }
    }
</script>
</body>
</html>