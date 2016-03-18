<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="col-md-3 col-md-offset-1">
    <div style="position: fixed;">
        <div class="paddingt10 paddingl4r4 paddingb10">
            <div><img id="fbzwId" src="/img/icon/cjjl.png" class="pointer marginb20"
                      onclick="javascript:window.location.href='basic_info_w.jsp?jobHunterId=${param.jobHunterId}'">
            </div>
        </div>
        <ul class="list-unstyled rightbar margint20">
            <li id="applypage" class="marginb10">
                <a href="apply.jsp?jobHunterId=${param.jobHunterId}">
                    <i class="fa fa-envelope marginl10 font20 verticalmiddle"></i>
                    <span class="fontem marginl10 font20 verticalmiddle">投递的职位</span></a></li>
            <li id="favoritepage" class="marginb10"><a href="favorite.jsp?jobHunterId=${param.jobHunterId}">
                <i class="fa fa-star marginl10 font20 verticalmiddle"></i>
                <span class="fontem marginl10 font20 verticalmiddle">收藏的职位</span></a>
            </li>
            <li id="setpage" class="marginb10"><a href="setting.jsp?jobHunterId=${param.jobHunterId}">
                <i class="fa fa-pencil marginl10 font20 verticalmiddle"></i>
                <span class="fontem marginl10 font20 verticalmiddle">账号设置</span></a></li>
        </ul>
    </div>
</div>