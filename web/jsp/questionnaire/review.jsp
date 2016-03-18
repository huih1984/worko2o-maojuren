<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="zh-CN">
<head>
    <title>问卷</title>
    <link href="/css/worko2o-questionnaire.css" rel="stylesheet">
    <%@ include file="/jsp/comm/header.jsp" %>
</head>
<body>
<%@ include file="/jsp/comm/comm_top.jsp" %>
<div id="content" class="container sizew600 main margint64 solidborder1">
    <div class="text-center"><label class="tedit font20" id="previewwenjuantitle"></label></div>
</div>
<%@ include file="/jsp/comm/comm_js.jsp" %>
<script type="text/javascript" src="/js/worko2o-question.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var opener = window.opener;
        if (opener) {
            var oDom = opener.document;
            var data = $.parseJSON(questionnaireToJSON(oDom));
            $("#previewwenjuantitle").text(data.title);
            for (var i = 0; i < data.queslist.length; ++i) {
                var questionItem = document.createElement('div');
                questionItem.className = "div_question row margint30";
                questionItem.innerHTML = '<div class="col-md-10 content">' +
                        '<div class="qtitle clra6a6a6 paddingb10">' +
                        '<span class="index">' +
                        data.queslist[i].index +
                        ' </span>' +
                        '<label class="tedit" name="question">' +
                        data.queslist[i].title +
                        '</label></div>' +
                        '<div class="paddingl10 questionitem">' +
                        '</div></div>';
                if (data.queslist[i].type == 'answer') {
                    $(questionItem).find(".questionitem").append(
                            '<div class="paddingl10 paddingr25">' +
                                    '<textarea class="form-control" rows="3" placeholder="(此处填写答案)"></textarea>' +
                                    '</div>')
                } else if (data.queslist[i].type == 'piccheckbox' || data.queslist[i].type == 'picradio') {
                    $(questionItem).find(".questionitem").append(
                            '<div class="paddingl10">' +
                                    '<ul class="list-inline">' +
                                    '</ul>' +
                                    '</div>');
                    for (var j = 0; j < data.queslist[i].option.length; ++j) {
                        $(questionItem).find(".questionitem ul").append('<li><div class="thumbnail">' +
                                '<div><img src="' +
                                data.queslist[i].option[j].src +
                                '" class="sizew150 sizeh150"></div>' +
                                '<div class="' +
                                data.queslist[i].type.replace("pic", "") +
                                '">' +
                                '<label>' +
                                '<input name="optionsRadios" type="' +
                                data.queslist[i].type.replace("pic", "") +
                                '">&nbsp;' +
                                data.queslist[i].option[j].hint +
                                '</label>' +
                                '</div>' +
                                '</div></li>')
                    }
                    ;
                } else {
                    for (var j = 0; j < data.queslist[i].option.length; ++j) {
                        $(questionItem).find(".questionitem").append(
                                '<div class="' +
                                        data.queslist[i].type +
                                        '"><label><input type="' +
                                        data.queslist[i].type +
                                        '" name="optionsRadios">' +
                                        data.queslist[i].option[j] +
                                        '</label></div>')
                    }
                }
                $("#content")[0].appendChild(questionItem);
            }
        }
    });
</script>

</body>
</html>