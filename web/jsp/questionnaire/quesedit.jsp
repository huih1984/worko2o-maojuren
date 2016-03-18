<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.delta.worko2o.util.PropertiesUtil" %>
<html lang="zh-CN">
<head>
    <title>问卷</title>
    <link href="/css/worko2o-questionnaire.css" rel="stylesheet">
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div class="container main margint64">
    <div class="col-md-8">
        <div id="questioncontent">
            <div class="text-center"><input class="tedit font20" id="wenjuantitle" value="标题"></div>
        </div>
    </div>
    <div class="col-md-3 col-md-offset-1">
        <div style="position: fixed;">
            <button class="btn btn-green" data-toggle="modal" data-target="#myModal" onclick="onQuesPreview()">
                预览问卷
            </button>
            <button class="btn btn-primary" onclick="onQuesPublish()">发布问卷</button>
            <ul class="list-unstyled margint20 lineheight40 marginl10">
                <li><span class="spanQTxt clra6a6a6">此处添加题型</span></li>
                <li><a id="hrefChoice" href="javascript:createQ('radio');"><span
                        class="glyphicon glyphicon-record">
                        </span>&nbsp;<span class="spanQTxt">单选</span></a></li>
                <li><a href="javascript:createQ('checkbox');">
                    <span class="glyphicon glyphicon-check"></span>&nbsp;<span class="spanQTxt">多选</span></a>
                </li>
                <li><a href="javascript:createQ('picradio');"><span
                        class="glyphicon glyphicon-picture"></span>&nbsp;<span
                        class="spanQTxt">图片单选题</span></a>
                </li>
                <li><a href="javascript:createQ('piccheckbox');"><span
                        class="glyphicon glyphicon-tint"></span>&nbsp;<span
                        class="spanQTxt">图片多选题</span></a>
                </li>
                <li><a href="javascript:createQ('answer');">
                    <span class="glyphicon glyphicon-edit"></span>&nbsp;<span class="spanQTxt">问答题</span></a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header clrbfff">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
            </div>
            <div id="previewcontent" class="modal-body marginlp66 marginr30">

            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<%--<textarea id="xh_editor" name="xh_editor" name="content" class="textarea"--%>
<%--placeholder="请输入。。。" required="" style="height: 320px; width: 100%;">--%>
<%--</textarea>--%>
<div id="uploadList"></div>


<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript" src="/js/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="/js/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if ('${param.questionnaireId}' != '') {
            $.ajax({
                url: '/page/demo/questionnaire/rques',
                async: false,
                data: $.param({questionnaireId: '${param.questionnaireId}'}),
                dataType: 'json',
                success: function (data) {
                    createByData(data.questionnaire.content)
                }
            });
        }
//        初始化xhEditor编辑器插件
//        $('#xh_editor').xheditor({
//            tools:'simple',
//            skin:'default',
//            upImgUrl: "/uploadfileservlet",
//            upImgExt: "jpg,jpeg,gif,bmp,png",
//            onUpload:insertUpload
//        });
        //xbhEditor编辑器图片上传回调函数
        function insertUpload(msg) {
            var _msg = msg.toString();
            var _picture_name = _msg.substring(_msg.lastIndexOf("/") + 1);
            var _picture_path = Substring(_msg);
            var _str = "<input type='checkbox' name='_pictures' value='" + _picture_path + "' checked='checked' onclick='return false'/><label>" + _picture_name + "</label><br/>";
            $("#xh_editor").append(_msg);
            $("#uploadList").append(_str);
        }

        //处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
        function Substring(s) {
            return s.substring(s.substring(0, s.lastIndexOf("/")).lastIndexOf("/"), s.length);
        }
    });

    function onQuesPublish() {
        var param_data = new Object();
        param_data.jobId = '${param.jobId}';
        param_data.content = questionnaireToJSON(document, 'publish');
        if (param_data.content == -1) {
            $.simplyToast("请上传完图片再发布！", "warning");
            return;
        }
        if ('${param.questionnaireId}' != '') {
            param_data.questionnaireId = '${param.questionnaireId}';
            $.ajax({
                url: '/page/demo/questionnaire/mques',
                async: false,
                data: $.param(param_data),
                dataType: 'json',
                success: function (data) {
                    var opener = window.opener;
                    var oDom = opener.document;
                    $(oDom).find("#questionnaireid").text(data.questionnaire_id);
                    $(oDom).find("#questionnaireedit").text("修改问卷");
                    $(oDom).find("#questionnaireid").parent().css("display", "inline-block");
                    window.close();
                }
            });
        } else {
            $.ajax({
                url: '/page/demo/questionnaire/wques',
                async: false,
                data: $.param(param_data),
                dataType: 'json',
                success: function (data) {
                    var opener = window.opener;
                    var oDom = opener.document;
                    $(oDom).find("#questionnaireid").text(data.questionnaire_id);
                    $(oDom).find("#questionnaireedit").text("修改问卷");
                    $(oDom).find("#questionnaireid").parent().css("display", "inline-block");
                    window.close();
                }
            });
        }
    }

    function onQuesPreview() {
        var data = $.parseJSON(questionnaireToJSON(document, 'preview'));
        onPreviewByData(data, 'qianduan', $("#previewcontent"));
    }
</script>
</body>
</html>