<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="footer hidden-xs margint100">
    <ul class="list-inline paddingt10">
        <li><a href="/jsp/rule/rule.jsp" class="marginr10">积分说明</a></li>
        <li><a href="#" class="marginr10">隐私设置</a></li>
        <li><a href="#" class="marginr10">联系我们</a></li>
        <li><a href="http://www.miibeian.gov.cn/" target="_blank">2015－2016 苏ICP备15023322-2号</a></li>
        <%--<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256412237'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1256412237%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>--%>
    </ul>
    <ul class="list-inline">
        <li><a href="#" class="marginr10">合作单位：</a></li>
        <li><a href="http://www.qingmengjia.com" target="_blank">
            <img src="/img/qingmengjia.png" style="height: 25px" alt="青梦家">
        </a>
        </li>
        <li><a href="http://nj.tedu.cn/?pz" target="_blank">
            <img src="/img/danei.jpg" style="height: 25px" alt="达内培训">
        </a>
        </li>
        <li><a target="_blank">
            <img src="/img/HR.jpg" style="height: 25px" alt="HR公会">
        </a>
        </li>

    </ul>
    <div>
        <dl class="marginb0">
            <dd>
                <label class="clra6a6a6">友情链接</label>
                <a href="http://www.lagou.com/" target="_blank">拉勾网</a> <span>|</span>
                <a href="http://www.jobtong.com/" target="_blank">周伯通</a> <span>|</span>
                <a href="http://www.kanzhun.com/" target="_blank">看准网</a> <span>|</span>
                <a href="http://www.025zp.com/" target="_blank">南京人才网</a> <span>|</span>
                <a href="http://www.jinmenrc.com/" target="_blank">天津人才网</a><span>|</span>
                <a href="http://www.hunt007.com/" target="_blank">广州人才网</a> <span>|</span>
                <a href="http://www.xuzhoujob.com/" target="_blank">徐州英才网</a> <span>|</span>
                <a href="http://www.0755rc.com" target="_blank">深圳人才网</a> <span>|</span>
                <a href="http://www.uisdc.com/" target="_blank">优秀网页设计</a> <span>|</span>
                <a href="http://www.91job.com" target="_blank">义乌人才网</a> <span>|</span>
                <a href="http://z.paidai.com/" target="_blank">派代招聘</a> <span>|</span>
                <a href="http://zhaopin.taobao.com/index.htm" target="_blank">淘工作</a>
            </dd>
        </dl>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        if (isMobile()) {
            $(".footer").remove();
        }
    });
</script>