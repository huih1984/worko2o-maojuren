<script type="text/javascript">
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
            (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
                    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
                            (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
                                    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    //以下进行测试
    if (Sys.ie && Sys.ie <= 6) document.write('您目前使用的是IE: ' + Sys.ie + '，推荐您升级IE到版本10以上再打开网站，<a href="http://browsehappy.com/">去升级</a>');
    //    if (Sys.firefox) document.write('Firefox: ' + Sys.firefox);
    //    if (Sys.chrome) document.write('Chrome: ' + Sys.chrome);
    //    if (Sys.opera) document.write('Opera: ' + Sys.opera);
    //    if (Sys.safari) document.write('Safari: ' + Sys.safari);

</script>


<script src="/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="/js/respond.min.js"></script>
<script src="/js/html5shiv.min.js"></script>
<![endif]-->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>

<script src="/js/bootstrap-multiselect.js"></script>
<script src="/js/moment.js"></script>
<script src="/js/bootstrap-datetimepicker.js"></script>
<script src="/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/js/bootstrap-simply-toast.min.js"></script>
<script src="/js/jquery.md5.js"></script>
<script src="/js/jQueryRotate.2.2.js"></script>
<script src="/js/bootstrap-hover-dropdown.min.js"></script>
<%--<script src="/js/jquery.colorbox-min.js"></script>--%>
<script src="//cdn.bootcss.com/Chart.js/1.0.2/Chart.min.js"></script>
<script src="/js/worko2o-min.js"></script>
<%--<script src="/js/worko2o.js"></script>--%>
<%--<script src="/js/worko2o-employer.js"></script>--%>
<%--<script src="/js/worko2o-form-check.js"></script>--%>
<%--<script src="/js/worko2o-hunter-resume.js"></script>--%>
<%--<script src="/js/worko2o-jssor.js"></script>--%>
<%--<script src="/js/worko2o-main-page.js"></script>--%>
<%--<script src="/js/worko2o-query.js"></script>--%>
<%--<script src="/js/worko2o-hunterinfo.js"></script>--%>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?e58dbfbcbcb33e380bc3f7d29e18ec5b";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

<script type="text/javascript">
    $(document).ready(function () {
        setBarStatus();
        $("#navbar .nav>li>a").removeClass("active");
        //var id = $("#navbar .nav>li>a").id;
        if (window.location.href.indexOf("e_business") > 0) {
            $("#infoNewsId").addClass("active");
        } else {
            $("#mainpageId").addClass("active");
        }
    });

</script>