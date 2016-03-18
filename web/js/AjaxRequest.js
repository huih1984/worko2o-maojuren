var net = new Object();		//定义一个全局变量net
//编写构造函数
net.AjaxRequest = function (url, onload, onerror, ontimeout, method, params) {
    this.req = null;
    this.ontimeout = ontimeout;
    this.onload = onload;
    this.onerror = (onerror) ? onerror : this.defaultError;
    this.loadDate(url, method, params);
}
//编写用于初始化XMLHttpRequest对象并指定处理函数，最后发送HTTP请求的方法
net.AjaxRequest.prototype.loadDate = function (url, method, params) {
    if (!method) {
        method = "GET";
    }
    if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (this.req) {
        this.req.timeout = 10000;
        this.req.ontimeout = function () {
            this.ontimeout();
        };

        try {
            var loader = this;
            this.req.onreadystatechange = function () {
                net.AjaxRequest.onReadyState.call(loader);
            }
            this.req.open(method, url, true);
            if (method == "POST") {
                this.req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            }
            this.req.send(params);
        } catch (err) {
            this.onerror(this);
        }
    }
}

//重构回调函数
net.AjaxRequest.onReadyState = function () {
    var req = this.req;
    var ready = req.readyState;
    if (ready == 4) {
        if (req.status == 200) {
            this.onload.call(this);
        } else if (req.status == 0) {

        } else {
            this.onerror(this);
        }
    }
}
//重构默认的错误处理函数
net.AjaxRequest.prototype.defaultError = function () {
    alert("错误数据\n\n回调状态:" + this.req.readyState + "\n状态: " + this.req.status);
}