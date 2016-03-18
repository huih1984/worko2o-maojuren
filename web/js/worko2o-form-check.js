function hideErrors() {
    $(".woo-error").each(function () {
        $(this).css("visibility", "hidden");
        $(this).css("display", "none");
    });
}

function showError(element) {
    $.simplyToast("您有必填项没填，请填完再提交！", "danger");
    var ff = element.parent().find(".woo-error");
    element.parent().find(".woo-error").css("visibility", "visible");
    element.parent().find(".woo-error").css("display", "inherit");
}

function hideError(element) {
    element.parent().find(".woo-error").css("visibility", "hidden");
    element.parent().find(".woo-error").css("display", "none");
}

function formValidCheck(form) {
    var canSubmit = true;
    if (form == undefined) {
        form = $("body");
    }
    form.find(".woo-check-drop-down").each(function () {
        var temp = $(this).find(".placeholder").text();
        if (temp.indexOf("选择") >= 0) {
            showError($(this));
            canSubmit = false;
        } else {
            hideError($(this));
        }
    });
    form.find(".woo-check-input").each(function () {
        var temp = $(this).val();
        if (temp == '') {
            showError($(this));
            canSubmit = false;
        } else {
            hideError($(this));
        }
    });
    return canSubmit;
}

function formValidCheckSub(rootNode) {
    var canSubmit = true;
    rootNode.find(".woo-check-drop-down").each(function () {
        var temp = $(this).find(".placeholder").text();
        if (temp.indexOf("选择") >= 0) {
            showError($(this));
            canSubmit = false;
        } else {
            hideError($(this));
        }
    });
    rootNode.find(".woo-check-input").each(function () {
        var temp = $(this).val();
        if (temp == '') {
            showError($(this));
            canSubmit = false;
        } else {
            hideError($(this));
        }
    });
    return canSubmit;
}