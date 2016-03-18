<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="col-md-3 col-md-offset-1">
    <div style="position: fixed;">
        <div class="paddingt10 paddingl4r4 paddingb10">
            <div><img id="fbzwId" src="/img/icon/qyzl.png" class="pointer marginb20"
                      onclick="javascript:window.location.href='/jsp/employer/employer_detail_w.jsp?employerId=${param.employerId}&employerName=${param.employerName}'">
            </div>
            <div><img id="cjjlId" src="/img/icon/fbzw.png" class="pointer marginb10"
                      onclick="javascript:window.location.href='/jsp/employer/job_detail_w.jsp?employerId=${param.employerId}&employerName=${param.employerName}'">
            </div>
        </div>
        <ul class="list-unstyled rightbar margint20">
            <li id="emjobspage" class="marginb10">
                <a href="employer_jobs.jsp?employerId=${param.employerId}&employerName=${param.employerName}">
                    <i class="fa fa-align-justify marginl10 font20 verticalmiddle"></i>
                    <span class="fontem marginl10 font20 verticalmiddle">已发布的职位</span></a></li>
            <li id="favoritepage" class="marginb10">
                <a href="favorite.jsp?employerId=${param.employerId}&employerName=${param.employerName}">
                    <i class="fa fa-star marginl10 font20 verticalmiddle"></i>
                    <span class="fontem marginl10 font20 verticalmiddle">收藏的简历</span></a></li>
            <li id="setpage" class="marginb10"><a
                    href="setting.jsp?employerId=${param.employerId}&employerName=${param.employerName}">
                <i class="fa fa-pencil marginl10 font20 verticalmiddle"></i>
                <span class="fontem marginl10 font20 verticalmiddle">账号设置</span></a></li>
            <li id="searchpage" class="marginb10"><a
                    href="resume_search.jsp?employerId=${param.employerId}&employerName=${param.employerName}">
                <i class="fa fa-search marginl10 font20 verticalmiddle"></i>
                <span class="fontem marginl10 font20 verticalmiddle"> 搜索简历</span></a></li>
        </ul>
    </div>
</div>