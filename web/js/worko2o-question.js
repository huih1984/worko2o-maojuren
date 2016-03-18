var toolbarStart = "<ul class='stuff list-unstyled lineheight40'>";
var toolbar_moveup = "<li><a href='javascript:void(0);' onclick='moveUp(this);'  title='将此题上移'><span class='fa fa-arrow-up'></span><span>上移</span></a></li>";
var toolbar_movedown = "<li><a href='javascript:void(0);' onclick='moveDown(this);'  title='将此题下移'><span class='fa fa-arrow-down'></span><span>下移</span></a></li>";
var toolbar_del = "<li><a href='javascript:void(0);' onclick='deleteQ(this);'  title='删除此题'><span class='fa fa-remove'></span><span>删除</span></a></li>";
var toolbar_end = "<div style='clear:both;'></div></ul>";
var questiontitle = "<div class='qtitle clra6a6a6 paddingb10'><span class='index'></span><input class='tedit' name='question' value='多选题'></div>" +
    "<ul class='list-unstyled questionitem paddingl10'></ul>";
var checkbox_item = "<div class='row paddingl10 checkbox'><div class='col-md-1 text-right'><input type='checkbox'></div><div class='col-md-10 text-left'><textarea name='option' class='tedit'>选项</textarea></div>" +
    "<i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var radio_item = "<div class='row paddingl10 radio'><div class='col-md-1 text-right'><input type='radio'></div><div class='col-md-10 text-left'><textarea name='option' class='tedit'>选项</textarea></div>" +
    "<i class='fa fa-trash-o' onclick='trash(this)'></i></div>";

var picradio_item = "<div class='thumbnail'> <div>" +
    "<div style='position:relative;'>" +
    "<img class='tpic sizew150 sizeh150' src='/img/picdefault.png'>" +
    "<input type='file' accept='image/*' class='questionpic sizew150 sizeh150 transparent pointer' style='position: absolute;z-index: 2; top: 0px;'>" +
    "</div>" +
    " <div class='radio'><label><input type='radio' name='optionsRadios' value='option1'><input class='l tedit' val=' 选项'></label></div>" +
    "</div>" +
    "<i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var piccheckbox_item = "<div class='thumbnail'> <div>" +
    "<div style='position:relative;'>" +
    "<img class='tpic sizew150 sizeh150' src='/img/picdefault.png'>" +
    "<input type='file' accept='image/*' class='questionpic sizew150 sizeh150 transparent pointer' style='position: absolute;z-index: 2; top: 0px;'>" +
    "</div>" +
    " <div class='checkbox'><label><input type='checkbox' name='optionsRadios' value='option1'><input class='l tedit' val=' 选项'></label></div>" +
    "</div>" +
    "<i class='fa fa-trash-o' onclick='trash(this)'></i></div>";
var answer_item = "<li style='display: inherit;'><div class='paddingl10 paddingr25'>" +
    "<textarea class='form-control tedit' rows='3' placeholder='(此处填写答案)'></textarea>" +
    "</div></li>";
var add_toolbar = "<div class='operationh' onclick='additem(this)'><a href='javascript:;' style='display: block;'><i class='fa fa-plus'></i></a></div>";
var question_content = $("#questioncontent");

function moveUp(a) {
    var node = $(a).parents(".div_question");
    if (node.index() <= 0) {
        $.simplyToast("第一个无法上移！", "warning");
    } else {
        var index = node.index();
        node.prev().find('.index').text((index + 1) + ' ');
        node.find('.index').text(index + ' ');
        node.insertBefore(node.prev());
    }
}
function moveDown(a) {
    var node = $(a).parents(".div_question");
    if (node.index() + 1 >= node.parent().children().length) {
        $.simplyToast("最后一个无法下移！", "warning");
    } else {
        var index = node.index();
        node.next().find('.index').text((index + 1) + ' ');
        node.find('.index').text((index + 2) + ' ');
        node.insertAfter(node.next());
    }
}
function deleteQ(a) {
    var node = $(a).parents(".div_question");
    node.remove();
    $(".div_question").each(function (i) {
        $(this).find(".index").text((i + 1));
    })
}

function parseDom(arg) {
    var objE = document.createElement("li");
    objE.innerHTML = arg;
    return objE;
};

function additem(ele) {
    var optionitem = null;
    if ($(ele).parents(".div_question").find(".questionitem").children().length > 4) {
        $.simplyToast("选项最多不能超过5个", "warning");
    } else {
        if ($(ele).parents(".div_question").attr('type') == 'radio') {
            optionitem = $(ele).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(radio_item));
        } else if ($(ele).parents(".div_question").attr('type') == 'checkbox') {
            optionitem = $(ele).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(checkbox_item));
        } else if ($(ele).parents(".div_question").attr('type') == 'piccheckbox') {
            optionitem = $(ele).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(piccheckbox_item));
            $(ele).parents(".div_question").find(".questionpic").change(function (e) {
                checkFile(this, 500, $(e.target).prev()[0], 150, 150, "");
            });
        } else if ($(ele).parents(".div_question").attr('type') == 'picradio') {
            optionitem = $(ele).parents(".div_question").find(".questionitem")[0].appendChild(parseDom(picradio_item));
            $(ele).parents(".div_question").find(".questionpic").change(function (e) {
                checkFile(this, 500, $(e.target).prev()[0], 150, 150, "");
            });
        }
    }
    return optionitem;
}

function addNewQ(_node) {
    _node.item.appendChild(_node.toolbar);
    _node.item.appendChild(_node.content);
    if (_node.type == 'radio') {
        $(_node.item).find(".qtitle input").val('单选题');
        $(_node.item).attr('type', "radio");
    } else if (_node.type == 'checkbox') {
        $(_node.item).find(".qtitle input").val('多选题');
        $(_node.item).attr('type', "checkbox");
    } else if (_node.type == 'piccheckbox') {
        $(_node.content).find("ul").removeClass("list-unstyled").addClass("list-inline");
        $(_node.item).find(".qtitle input").val('图片多选题');
        $(_node.item).attr('type', "piccheckbox");
    } else if (_node.type == 'picradio') {
        $(_node.content).find("ul").removeClass("list-unstyled").addClass("list-inline");
        $(_node.item).find(".qtitle input").val('图片单选题');
        $(_node.item).attr('type', "picradio");
    } else if (_node.type == 'answer') {
        $(_node.item).find(".qtitle input").val('问答题');
        $(_node.item).attr('type', "answer");
        $(_node.content).find(".questionitem").html(answer_item);
    }
    question_content.append(_node.item);
    $(_node.item).bind("mouseenter", function () {
        if (_node.type != 'answer') {
            if ($(this).find(".operationh").size() == 0) {
                $(this).find(".content").append(add_toolbar);
                $(this).find(".glyphicon-trash").css("display", "");
            }
        }
    });
    $(_node.item).bind("mouseleave", function () {
        if ($(this).find(".operationh").size() != 0) {
            $(".operationh").remove();
            $(this).find(".glyphicon-trash").css("display", "none");
        }
    });
}

function createQ(type) {
    var _node = new Object();
    _node.type = type;
    _node.item = new Object();
    _node.item = document.createElement('div');
    _node.item.className = "div_question row margint30 bottomdashedborder paddingb20";
    _node.toolbar = document.createElement('div');
    _node.toolbar.className = 'col-md-2 toolbar margint20';
    _node.toolbar.innerHTML = toolbarStart + toolbar_moveup + toolbar_movedown + toolbar_del + toolbar_end;
    _node.content = document.createElement('div');
    _node.content.className = 'col-md-10 content';
    _node.content.innerHTML = questiontitle;
    jQuery(_node.content).find(".index").text((jQuery(document).find(".div_question").length + 1) + ' ');
    addNewQ(_node);
    return _node;
}

function trash(ele) {
    $(ele).parents(".questionitem li").remove();
}

function questionnaireToJSON(oDom, type) {
    var data = new Object();
    data.title = $(oDom).find("#wenjuantitle").val();
    data.queslist = new Array();
    var erro = false;
    $(oDom).find('.div_question').each(function () {
        var item = new Object();
        item.type = $(this).attr("type");
        item.index = $(this).find(".index").text();
        item.title = $(this).find(".qtitle .tedit").val();
        item.option = new Array();
        if (item.type == "picradio" || item.type == "piccheckbox") {
            if (type == 'publish') {
                var param_data = new Object();
                $(this).find('.questionitem .thumbnail').each(function (i) {
                    //还有图片没上传，直接返回
                    if ($(this).find(".tpic").attr("src").indexOf('data:image') < 0) {
                        erro = true;
                        return;
                    }
                    if (i == 0) {
                        param_data.picSrc1 = $(this).find(".tpic").attr("src");
                    } else if (i == 1) {
                        param_data.picSrc2 = $(this).find(".tpic").attr("src");
                    } else if (i == 2) {
                        param_data.picSrc3 = $(this).find(".tpic").attr("src");
                    } else if (i == 3) {
                        param_data.picSrc4 = $(this).find(".tpic").attr("src");
                    } else if (i == 4) {
                        param_data.picSrc5 = $(this).find(".tpic").attr("src");
                    }
                });
                if (erro) {
                    return;
                }
                var questionnairePicId = null;
                $.ajax({
                    type: 'post',
                    url: '/page/demo/questionnaire/wquespic',
                    async: false,
                    data: $.param(param_data),
                    dataType: 'json',
                    success: function (data) {
                        questionnairePicId = data.questionnaire_pic_id;
                    }
                });
                $(this).find('.questionitem .thumbnail').each(function (i) {
                    item.option.push({src: questionnairePicId + ' ' + i, hint: $(this).find(".tedit").val()});
                });
            } else if (type == 'preview') {
                $(this).find('.questionitem .thumbnail').each(function () {
                    item.option.push({src: $(this).find(".tpic").attr("src"), hint: $(this).find(".tedit").val()});
                });
            }
        } else {
            $(this).find('.questionitem .tedit').each(function () {
                item.option.push($(this).val());
            });
        }
        data.queslist.push(item);
    });
    if (erro) {
        return -1;
    }
    return JSON.stringify(data);
}

function createByData(jsondata) {
    var data = $.parseJSON(jsondata);
    $("#wenjuantitle").val(data.title);
    for (var i = 0; i < data.queslist.length; ++i) {
        var _node = createQ(data.queslist[i].type);
        $(_node.item).find(".qtitle input").val(data.queslist[i].title);
        if (data.queslist[i].type == 'answer') {
        } else if (data.queslist[i].type == 'checkbox' || data.queslist[i].type == 'radio') {
            for (var j = 0; j < data.queslist[i].option.length; ++j) {
                var optionitem = additem(_node.item.firstChild);
                $(optionitem).find(".tedit").val(data.queslist[i].option[j]);
            }
        } else {
            var imgsrc = null;
            for (var j = 0; j < data.queslist[i].option.length; ++j) {
                if (j == 0) {
                    var questionnairePicId = data.queslist[i].option[0].src.split(" ")[0];
                    $.ajax({
                        url: '/page/demo/questionnaire/rquespic',
                        async: false,
                        data: $.param({questionnairePicId: questionnairePicId}),
                        dataType: 'json',
                        success: function (data) {
                            imgsrc = data.questionnaire_pic;
                        }
                    });
                }

                var optionitem = additem(_node.item.firstChild);
                $(optionitem).find(".l.tedit").val(data.queslist[i].option[j].hint);
                if (j == 0) {
                    $(optionitem).find("img").attr("src", imgsrc.picSrc1);
                } else if (j == 1) {
                    $(optionitem).find("img").attr("src", imgsrc.picSrc2);
                } else if (j == 2) {
                    $(optionitem).find("img").attr("src", imgsrc.picSrc3);
                } else if (j == 3) {
                    $(optionitem).find("img").attr("src", imgsrc.picSrc4);
                } else if (j == 4) {
                    $(optionitem).find("img").attr("src", imgsrc.picSrc5);
                }
            }
        }
    }
}

function onPreviewByData(data, type, contentNode) {
    contentNode.html("");
    contentNode.append('<div class="text-center"><label class="tedit font20" id="previewwenjuantitle">' +
        data.title +
        '</label></div>');

    for (var i = 0; i < data.queslist.length; ++i) {
        var questionItem = document.createElement('div');
        questionItem.className = "pre_div_question row margint30";
        questionItem.setAttribute('type', data.queslist[i].type);
        questionItem.innerHTML = '<div class="col-md-10 content">' +
            '<div class="qtitle clra6a6a6 paddingb10" ' +
            '>' +
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
            if (type == 'houtai') {
                var imgsrc = null;
                var src = '';
                for (var j = 0; j < data.queslist[i].option.length; ++j) {
                    if (j == 0) {
                        var questionnairePicId = data.queslist[i].option[0].src.split(" ")[0];
                        $.ajax({
                            url: '/page/demo/questionnaire/rquespic',
                            async: false,
                            data: $.param({questionnairePicId: questionnairePicId}),
                            dataType: 'json',
                            success: function (data) {
                                imgsrc = data.questionnaire_pic;
                            }
                        });
                    }

                    if (j == 0) {
                        src = imgsrc.picSrc1;
                    } else if (j == 1) {
                        src = imgsrc.picSrc2;
                    } else if (j == 2) {
                        src = imgsrc.picSrc3;
                    } else if (j == 3) {
                        src = imgsrc.picSrc4;
                    } else if (j == 4) {
                        src = imgsrc.picSrc5;
                    }

                    $(questionItem).find(".questionitem ul").append('<li><div class="thumbnail">' +
                        '<div><img src="' +
                        src +
                        '" class="sizew150 sizeh150"></div>' +
                        '<div class="' +
                        data.queslist[i].type.replace("pic", "") +
                        '">' +
                        '<label>' +
                        '<input name="optionsRadios' +
                        i +
                        '" type="' +
                        data.queslist[i].type.replace("pic", "") +
                        '">&nbsp;' +
                        data.queslist[i].option[j].hint +
                        '</label>' +
                        '</div>' +
                        '</div></li>');
                }
            } else {
                for (var j = 0; j < data.queslist[i].option.length; ++j) {
                    $(questionItem).find(".questionitem ul").append('<li><div class="thumbnail">' +
                        '<div><img src="' +
                        data.queslist[i].option[j].src +
                        '" class="sizew150 sizeh150"></div>' +
                        '<div class="' +
                        data.queslist[i].type.replace("pic", "") +
                        '">' +
                        '<label>' +
                        '<input name="optionsRadios' +
                        i +
                        '" type="' +
                        data.queslist[i].type.replace("pic", "") +
                        '">&nbsp;' +
                        data.queslist[i].option[j].hint +
                        '</label>' +
                        '</div>' +
                        '</div></li>');
                }
            }
        } else {
            for (var j = 0; j < data.queslist[i].option.length; ++j) {
                $(questionItem).find(".questionitem").append(
                    '<div class="' +
                        data.queslist[i].type +
                        '"><label><input type="' +
                        data.queslist[i].type +
                        '" name="optionsRadios' +
                        i +
                        '">' +
                        data.queslist[i].option[j] +
                        '</label></div>');
            }
        }
        contentNode[0].appendChild(questionItem);
    }
}