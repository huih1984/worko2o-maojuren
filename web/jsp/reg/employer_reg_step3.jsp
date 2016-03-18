<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.model.Employer" %>
<html lang="zh-CN">
<head>
    <title>注册</title>
    <%@ include file="/jsp/comm/header.jsp" %>
</head>

<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="horizontalmiddle">
        <div class="margint64">
            <table class="horizontalmiddle">
                <tbody class="banner">
                <tr>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">1</button>
                    </td>
                    <td>
                        <div class="line1 clrb8e44ad"></div>
                    </td>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">2</button>
                    </td>
                    <td>
                        <div class="line1 clrb8e44ad"></div>
                    </td>
                    <td>
                        <button class="clrb8e44ad btn circle size34 floatright" style="cursor:default">3</button>
                    </td>
                    <td>
                        <div class="line1 clrba6a6a6"></div>
                    </td>
                    <td>
                        <button class="clrba6a6a6 btn circle size34 floatright" style="cursor:default">4</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="margint20">
            <div class="row horizontalmiddle">
                <div class="col-md-3 ">
                    <h5 class="clra6a6a6 text-center">邮箱激活</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">企业名称及电话</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">身份证明资料</h5>
                </div>
                <div class="col-md-3">
                    <h5 class="clra6a6a6 text-center">注册成功</h5>
                </div>
            </div>
        </div>
    </div>
    <%
        String employerName = (String) request.getParameter("employerName");
        String tel = (String) request.getParameter("tel");
        Employer employer = (Employer) session.getAttribute("employer");
        if (employer != null) {
            employer.setEmployerName(employerName);
            employer.setTel(tel);
        }
    %>
    <div class="margint20 col-md-offset-1">
        <div action="employer_reg_step4.jsp" method="get" role="form">
            <div class="row">
                <div class="col-md-6">
                    <em class="clrred">*</em> <label style="">上传营业执照/手执身份证照：</label>
                    <label><span>请上传营业执照清晰彩色原件扫描件或数码照在有效期内且年检章齐全（当年成立的可无年检章） 由中国大陆工商局或市场监督管理局颁发</span></label>
                    <label><span>以左图方式双手持身份证，需免冠，五官清晰可见；照片内容真实有效，不得做任何修改；</span></label>
                    <label><span>支持.jpg .jpeg .bmp .gif格式照片，大小不超过2M</span></label>
                </div>
                <div class="col-md-6">
                    <img id="certificateImgId" class="sizew280h180" src="">
                    <input type="file" accept="image/*" class="margint20" id="wooInputFile">

                    <button class="btn btn-primary sizew210 margint20" type="submit" onclick="regSubmit()">下一步
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#wooInputFile").change(function () {
            checkFile(this, 2048, document.getElementById("wooInputFile").previousElementSibling, 280, 180, "");
        });
    });

    function submitForm(jsonData) {
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            data: jsonData,
            url: "/page/demo/employer/register",
            success: function (data) {
                document.location.href = "/jsp/reg/employer_reg_step4.jsp?employerId=" + data.last_insert_id;
            }
        });
    }

    function regSubmit() {
        var wooInputFile = $("#wooInputFile");
        if (wooInputFile[0].files != null && wooInputFile[0].files.length > 0) {
            var maxId = null;
            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                data: jsonData,
                url: "/page/demo/employer/maxid",
                success: function (data) {
                    maxId = data.max_id;
                }
            });
            uploadFile(wooInputFile[0].files[0], "/page/demo/upload", "certificate", maxId, 1, function (msg) {

                var data = JSON.parse(msg);

                wooInputFile[0].outerHTML = wooInputFile[0].outerHTML;
                $("#wooInputFile").prev().attr("value", data.file_path);

                var jsonData = new Object();
                jsonData.certificatePath = data.file_path;
                submitForm(jsonData);
            });
        } else {
            var certificatePath = $("#wooInputFile").prev().attr("value");
            if (certificatePath == null || certificatePath == '') {
                //alert('系统错误，文件获取的文件不存在');
            } else {
                var jsonData = new Object();
                jsonData.certificatePath = certificatePath;
                submitForm(jsonData);
            }
        }
    }
</script>
</body>
</html>