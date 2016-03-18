package com.delta.worko2o.action;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delta.worko2o.model.*;
import com.delta.worko2o.service.api.JobHunterServiceApi;
import com.delta.worko2o.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.delta.worko2o.constants.RetConstants;

import sun.misc.BASE64Decoder;


/**
 * <Description> <br>
 * 消息处理类
 *
 * @author xuyh<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月17日 <br>
 * @see com.delta.worko2o.action <br>
 */
@IocBean(singleton = false)
public class UploadAction extends BaseAction {

    /**
     * logger
     */
    public static final Log log = Logs.get();

    @Inject
    private JobHunterServiceApi jobHunterService;

    final private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd");

    final private static SimpleDateFormat FILEMANAGE_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd");

    public String upload(TempFile f, String rootPath, String subDir, String fileName) {

        //String contextPathString = context.getContextPath();
        String classBasePath = getClass().getClassLoader().getResource("").getPath();
        String uploadBasePathString = classBasePath + "../../upload/";
        //String uploadBasePathString = rootPath + "/upload/";
        String fileContextPathString = "/upload/";

//        UUID uuid = UUID.randomUUID();
        Date currDate = new Date();

        String fileDir = uploadBasePathString + subDir + "/"
                + FILEMANAGE_DATE_FORMAT.format(currDate) + "/";
        fileContextPathString += subDir + "/"
                + FILEMANAGE_DATE_FORMAT.format(currDate) + "/";

        File dir = new File(fileDir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }

        String fileExt = f.getMeta().getFileExtension();
        String filePath = fileDir + fileName + fileExt;
        fileContextPathString += fileName + fileExt;

        //f.getFile().renameTo(new File("D:\\worko2o\\main-branch\\worko2o\\WebContent\\upload\\employerlogo\\" + f.getFile().getName()));
        if (!f.getFile().renameTo(new File(filePath))) {
            return null;
        }
        return fileContextPathString;
    }

    @At("/page/demo/upload")
//    @AdaptBy(type = UploadAdaptor.class, args = {
//        "ioc:myUpload"
//    })
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public Map<String, Object> upload(@Param("Filedata") TempFile f, @Param("type") String type,
                                      @Param("id") Integer id, @Param("index") Integer index, HttpServletRequest req, ServletContext context) {

        log.debug("[upload] begin");
        String rootPath = req.getContextPath();
        String fileContextPathString;
        if (index == null) {
            fileContextPathString = upload(f, rootPath, type + "/" + id, "1");
        } else {
            fileContextPathString = upload(f, rootPath, type + "/" + id, "" + index);
        }
        if (fileContextPathString == null) {
            setRetCodeAndMsg(RetConstants.VAL_FAIL, "upload " + type + " fail");
        } else {
            setRetInfo(RetConstants.KEY_UPLOAD_FILEPATH, fileContextPathString);
        }

        log.debug("[upload] end");

        return retMap;
    }

    Boolean saveImgFromUrl(String urlStr, JobHunter jobHunter) {
        BufferedInputStream in;
        FileOutputStream file;
        String filePath = null;
        String ext = null;
        String classBasePath = getClass().getClassLoader().getResource("").getPath();
        String fileDir = classBasePath.substring(0, classBasePath.lastIndexOf("/"));
        fileDir = fileDir.substring(0, fileDir.lastIndexOf("/"));
        fileDir = fileDir.substring(0, fileDir.lastIndexOf("/"));
        ext = urlStr.substring(urlStr.lastIndexOf("."));
        filePath = "/upload/hunteravatar/" + jobHunter.getJobHunterId() + "/" + FILEMANAGE_DATE_FORMAT.format(new Date());// 图片存储的位置
        File dir = new File(fileDir + filePath);
        log.debug("filePath " + fileDir + filePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return false;
            }
        }
        try {
            URL url = new URL(urlStr);
            in = new BufferedInputStream(url.openStream());
            file = new FileOutputStream(new File(fileDir + filePath + "/1" + ext));
            int t;
            while ((t = in.read()) != -1) {
                file.write(t);
            }
            file.close();
            in.close();
            jobHunter.setJobHunterAvatarPath(filePath + "/1" + ext);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @At("/page/demo/importresume")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp1"})
    public Map<String, Object> importResume(@Param("Filedata") TempFile f, @Param("id") Integer id, @Param("type") Integer fileType) {
        log.debug("[importResume] begin");
        try {
            JobHunter jobHunter = jobHunterService.qryJobHunter(id);
            jobHunter.setImportFromOther(1);
            JobHunterExpect jobHunterExpect = new JobHunterExpect();
            jobHunterExpect.setJobHunterId(id);
            JobHunterWorkExperience jobHunterWorkExperience = null;
            JobHunterEduExperience jobHunterEduExperience = null;
            JobHunterTrainExperience jobHunterTrainExperience = null;
            if (fileType == 0) {
                String ext = f.getMeta().getFileExtension();
                if (!ext.equals(".htm")) {
                    setRetCodeAndMsg(RetConstants.VAL_FAIL, "文件格式错误，请下载htm格式");
                    return retMap;
                }
                Document doc = Jsoup.parse(f.getFile(), "UTF-8", "http://www.maojuren.com/");
                Elements summary = doc.select(".summary");
                Elements details = doc.select(".details dt> h5");
                String urlStr = summary.get(0).child(0).attr("src");
                saveImgFromUrl(urlStr, jobHunter);
                jobHunter.setJobHunterSex(summary.get(0).textNodes().get(2).text().trim());
                jobHunter.setJobHunterName(summary.get(0).child(1).text());
                String birth = summary.get(0).textNodes().get(4).text().trim();
                jobHunter.setJobHunterBirthday(DateUtil.getDate(birth.substring(0, birth.length() - 1), "yyyy年MM月"));
//            String currentPlace = summary.get(0).textNodes().get(6).text().trim();

                for (Element element : details) {
                    if (element.text().equals("自我评价") && !element.parent().toString().contains("display:none")) {
                        String val = element.parent().nextElementSibling().child(0).text();
                        jobHunter.setJobHunterDepict(val);
                    } else if (element.text().equals("求职意向") && !element.parent().toString().contains("display:none")) {
                        Element ulElement = element.parent().nextElementSibling().child(0);
                        jobHunterExpect.setWorkPattern(ulElement.child(0).text());
                        jobHunterExpect.setExpectJob(ulElement.child(1).text());
                        jobHunterExpect.setCity(ulElement.child(3).text());
                        if (ulElement.child(4).text().contains("1000元/")) {
                            jobHunterExpect.setExpectSalary("1000元以下");
                        } else if (ulElement.child(4).text().contains("1000元-2000元")) {
                            jobHunterExpect.setExpectSalary("1000-2000元");
                        } else if (ulElement.child(4).text().contains("2001元-4000元")) {
                            jobHunterExpect.setExpectSalary("3000-4000元");
                        } else if (ulElement.child(4).text().contains("4001元-6000元")) {
                            jobHunterExpect.setExpectSalary("5000-6000元");
                        } else if (ulElement.child(4).text().contains("6001元-8000元")) {
                            jobHunterExpect.setExpectSalary("6000-8000元");
                        } else if (ulElement.child(4).text().contains("8001元-10000元")) {
                            jobHunterExpect.setExpectSalary("8000-10000元");
                        } else if (ulElement.child(4).text().contains("10001元-15000元")) {
                            jobHunterExpect.setExpectSalary("10000-12000元");
                        } else {
                            jobHunterExpect.setExpectSalary("15000元以上");
                        }
                        if (ulElement.child(5).text().contains("我目前在职，正考虑换个新环境")) {
                            jobHunter.setJobHunterCurrentStatus("一个月内到岗");
                        } else if (ulElement.child(5).text().contains("立即上岗")) {
                            jobHunter.setJobHunterCurrentStatus("即时到岗");
                        } else if (ulElement.child(5).text().contains("可以考虑")) {
                            jobHunter.setJobHunterCurrentStatus("观望，有好的工作机会再考虑");
                        } else {
                            jobHunter.setJobHunterCurrentStatus("暂无跳槽打算");
                        }
                        try {
                            jobHunterService.addJobHunterExpect(jobHunterExpect);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (element.text().equals("工作经历") && !element.parent().toString().contains("display:none")) {
                        Element ulElement = element.parent().nextElementSibling().child(0);
                        Elements experienceElements = ulElement.select(".work-experience");
                        for (Element experienceElement : experienceElements) {
                            try {
                                jobHunterWorkExperience = new JobHunterWorkExperience();
                                jobHunterWorkExperience.setJobHunterId(id);
                                String[] dates = experienceElement.child(0).text().split("--");
                                jobHunterWorkExperience.setBeginTime(DateUtil.getDate(dates[0].trim(), "yyyy/MM"));
                                jobHunterWorkExperience.setEndTime(DateUtil.getDate(dates[1].trim(), "yyyy/MM"));
                                jobHunterWorkExperience.setEmployerName(experienceElement.child(1).textNodes().get(0).text());
                                jobHunterWorkExperience.setJobName(experienceElement.child(1).textNodes().get(2).text());
                                jobHunterWorkExperience.setWorkDesc(experienceElement.child(3).text());

                                jobHunterService.addJobHunterWorkExperience(jobHunterWorkExperience);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (element.text().equals("教育经历") && !element.parent().toString().contains("display:none")) {
                        Element ulElement = element.parent().nextElementSibling().child(0);
                        Elements eduElements = ulElement.select(".education-background");
                        for (Element eduElement : eduElements) {
                            try {
                                jobHunterEduExperience = new JobHunterEduExperience();
                                jobHunterEduExperience.setJobHunterId(id);
                                String[] dates = eduElement.child(0).text().split("--");
                                jobHunterEduExperience.setBeginTime(DateUtil.getDate(dates[0].trim(), "yyyy/MM"));
                                jobHunterEduExperience.setEndTime(DateUtil.getDate(dates[1].trim(), "yyyy/MM"));
                                jobHunterEduExperience.setCollege(eduElement.child(1).textNodes().get(0).text());
                                jobHunterEduExperience.setMajor(eduElement.child(1).textNodes().get(1).text());
                                jobHunterEduExperience.setBachelorDegree(eduElement.child(1).textNodes().get(2).text());

                                jobHunterService.addJobHunterEduExperience(jobHunterEduExperience);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (element.text().equals("培训经历") && !element.parent().toString().contains("display:none")) {
                        Element ulElement = element.parent().nextElementSibling().child(0);
                        Elements eduElements = ulElement.select(".training");
                        for (Element eduElement : eduElements) {
                            try {
                                jobHunterTrainExperience = new JobHunterTrainExperience();
                                jobHunterTrainExperience.setJobHunterId(id);
                                String[] dates = eduElement.child(0).text().split("--");
                                jobHunterTrainExperience.setTrainBeginDate(DateUtil.getDate(dates[0].trim(), "yyyy/MM"));
                                jobHunterTrainExperience.setTrainEndDate(DateUtil.getDate(dates[1].trim(), "yyyy/MM"));
                                jobHunterTrainExperience.setTrainInstitutionName(eduElement.child(1).text());
                                jobHunterTrainExperience.setCourseName(eduElement.child(3).textNodes().get(0).text());
                                jobHunterTrainExperience.setCertificateName(eduElement.child(4).textNodes().get(0).text());
                                jobHunterTrainExperience.setTrainDesc(eduElement.child(6).textNodes().get(0).text());

                                jobHunterService.addJobHunterTrainExperience(jobHunterTrainExperience);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                jobHunterService.modJobHunter(jobHunter, true);
            } else {
                if (!f.getMeta().getFileExtension().equals(".mht")) {
                    setRetCodeAndMsg(RetConstants.VAL_FAIL, "文件格式错误，请下载mht格式");
                    return retMap;
                }
                BASE64Decoder decoder = new BASE64Decoder();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(f.getFile()));
                    String allFileStr = "";
                    String tempString = null;
                    int line = 1;
                    // 一次读入一行，直到读入null为文件结束
                    while ((tempString = reader.readLine()) != null) {
                        allFileStr += tempString;
                        line++;
                    }
                    allFileStr = allFileStr.substring(allFileStr.indexOf("Content-Location: tmp.html") + "Content-Location: tmp.html".length());
//                    allFileStr = allFileStr.substring(0, allFileStr.lastIndexOf("\t"));
//                    allFileStr.replace("\t","");
                    byte[] b = decoder.decodeBuffer(allFileStr);
                    String parsed = new String(b, "gb2312");
                    Document doc = Jsoup.parse(parsed);
                    Elements elements = doc.select("tbody");
                    Element nameElement = null;
                    for (Element element : elements) {
                        nameElement = element.getElementsByAttributeValueContaining("style", "25px").first();
                        if (nameElement != null) {
                            break;
                        }
                    }
                    jobHunter.setJobHunterName(nameElement.text());
                    Element element = nameElement.parent().parent().nextElementSibling();
                    Element element2 = element.select("tbody").get(0);
                    Elements elements1 = element2.select("td");
                    String tempVal = elements1.get(0).text();
                    String[] values = tempVal.split("\\|");
                    System.out.println(values[1]);
                    jobHunter.setJobHunterSex(values[1].replace(" ", ""));
                    String birthDayStr = values[2].substring(values[2].indexOf("(") + 1, values[2].indexOf(")"));
                    jobHunter.setJobHunterBirthday(DateUtil.getDate(birthDayStr, "yyyy年MM月dd日"));

                    String urlStr = element2.select("img").get(0).attr("src");
                    saveImgFromUrl(urlStr, jobHunter);
                    Element recentElement = element.parent().parent().nextElementSibling();
                    Elements recentTdElements = recentElement.select("td");
                    jobHunter.setJobHunterLastEmployer(recentTdElements.get(3).text());
                    jobHunter.setJobHunterLastOccupation(recentTdElements.get(7).text());
                    jobHunter.setJobHunterMajor(recentTdElements.get(14).text());
                    jobHunter.setJobHunterCollege(recentTdElements.get(16).text());
                    Element introduceElement = recentElement.nextElementSibling().nextElementSibling().nextElementSibling();
                    if (introduceElement.select("tr").size() != 0) {
                        jobHunter.setJobHunterDepict(introduceElement.select("tr").get(3).text());
                    }
                    Element expectElement = introduceElement.nextElementSibling();
                    String tempStr = expectElement.select("tr").get(3).select("span").get(0).text();
                    if (tempStr.contains("即时")) {
                        jobHunter.setJobHunterCurrentStatus("即时到岗");
                    } else if (tempStr.contains("一周")) {
                        jobHunter.setJobHunterCurrentStatus("一周内到岗");
                    } else if (tempStr.contains("一个月")) {
                        jobHunter.setJobHunterCurrentStatus("一个月内到岗");
                    } else {
                        jobHunter.setJobHunterCurrentStatus("观望，有好的工作机会再考虑");
                    }
                    jobHunterExpect.setWorkPattern(expectElement.select("tr").get(3).select("span").get(1).text());
                    jobHunterExpect.setCity(expectElement.select("tr").get(3).select("span").get(3).text());
                    tempStr = expectElement.select("tr").get(3).select("span").get(4).text();
                    if (tempStr.contains("1500以下")) {
                        jobHunterExpect.setExpectSalary("1000元以下");
                    } else if (tempStr.contains("1500-1999")) {
                        jobHunterExpect.setExpectSalary("1000-2000元");
                    } else if (tempStr.contains("2000-2999")) {
                        jobHunterExpect.setExpectSalary("2000-3000元");
                    } else if (tempStr.contains("3000-4499")) {
                        jobHunterExpect.setExpectSalary("4000-5000元");
                    } else if (tempStr.contains("4500-5999")) {
                        jobHunterExpect.setExpectSalary("5000-6000元");
                    } else if (tempStr.contains("6000-7999")) {
                        jobHunterExpect.setExpectSalary("6000-8000元");
                    } else if (tempStr.contains("8000-9999")) {
                        jobHunterExpect.setExpectSalary("8000-10000元");
                    } else if (tempStr.contains("面议")) {
                        jobHunterExpect.setExpectSalary("面议");
                    } else {
                        jobHunterExpect.setExpectSalary("15000元以上");
                    }
                    jobHunterExpect.setExpectJob(expectElement.select("tr").get(3).select("span").get(5).text());
                    jobHunterService.addJobHunterExpect(jobHunterExpect);
                    Element otherInfoElement = expectElement.nextElementSibling();
                    Elements workExperienceElements = otherInfoElement.child(0).child(3).child(0).child(0).child(0).children();
                    for (int i = 0; i < ((workExperienceElements.size() == 1) ? 1 : (workExperienceElements.size() + 1) / 5); ++i) {
                        try {
                            jobHunterWorkExperience = new JobHunterWorkExperience();
                            jobHunterWorkExperience.setJobHunterId(id);
                            String innerTempStr = workExperienceElements.get(i * 5).text();
                            jobHunterWorkExperience.setEmployerName(innerTempStr.substring(innerTempStr.indexOf("：") + 1, innerTempStr.indexOf("[")));
                            String dateStr = innerTempStr.substring(0, innerTempStr.indexOf("："));
                            dateStr = dateStr.replace(" ", "");
                            String[] dateStrA = dateStr.split("--");
                            jobHunterWorkExperience.setBeginTime(DateUtil.getDate(dateStrA[0], "yyyy/MM"));
                            jobHunterWorkExperience.setEndTime(DateUtil.getDate(dateStrA[1], "yyyy/MM"));
                            jobHunterWorkExperience.setJobName(workExperienceElements.get(i * 5 + 2).text());
                            jobHunterWorkExperience.setWorkDesc(workExperienceElements.get(i * 5 + 3).text());
                            jobHunterService.addJobHunterWorkExperience(jobHunterWorkExperience);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Element tempElement = null;
                    for (Element tempElementt : otherInfoElement.child(0).children()) {
                        if (tempElementt.text().equals("教育经历")) {
                            tempElement = tempElementt;
                            break;
                        }
                    }
                    if (tempElement != null) {
                        Element eduExperienceElement = tempElement.nextElementSibling().nextElementSibling().nextElementSibling();
                        Elements eduExperienceElements = eduExperienceElement.child(0).child(0).child(0).children();
                        for (int i = 0; i < (eduExperienceElements.size() + 1) / 2; ++i) {
                            try {
                                jobHunterEduExperience = new JobHunterEduExperience();
                                jobHunterEduExperience.setJobHunterId(id);
                                jobHunterEduExperience.setCollege(eduExperienceElements.get(i * 2).child(1).text());
                                jobHunterEduExperience.setBachelorDegree(eduExperienceElements.get(i * 2).child(3).text());
                                jobHunterEduExperience.setMajor(eduExperienceElements.get(i * 2).child(2).text());
                                tempStr = eduExperienceElements.get(i * 2).child(0).text();
                                tempStr = tempStr.replace(" ", "");
                                String[] dateStrA = tempStr.split("--");
                                jobHunterEduExperience.setBeginTime(DateUtil.getDate(dateStrA[0], "yyyy/MM"));
                                jobHunterEduExperience.setEndTime(DateUtil.getDate(dateStrA[1], "yyyy/MM"));
                                jobHunterService.addJobHunterEduExperience(jobHunterEduExperience);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    tempElement = null;
                    for (Element tempElementt : otherInfoElement.child(0).children()) {
                        if (tempElementt.text().equals("培训经历")) {
                            tempElement = tempElementt;
                            break;
                        }
                    }

                    if (tempElement != null) {
                        Element trainExperienceElement = tempElement.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling();
                        Elements trainExperienceElements = trainExperienceElement.child(0).child(0).child(0).children();
                        for (int i = 0; i < ((trainExperienceElements.size() == 1) ? 1 : (trainExperienceElements.size() + 1) / 3); ++i) {
                            try {
                                jobHunterTrainExperience = new JobHunterTrainExperience();
                                jobHunterTrainExperience.setTrainDesc(trainExperienceElements.get(i * 3 + 1).text());
                                jobHunterTrainExperience.setJobHunterId(id);
                                jobHunterTrainExperience.setTrainInstitutionName(trainExperienceElements.get(i * 3).child(1).text());
                                jobHunterTrainExperience.setCourseName(trainExperienceElements.get(i * 3).child(2).text());
                                jobHunterTrainExperience.setCertificateName(trainExperienceElements.get(i * 3).child(3).text());
                                tempStr = trainExperienceElements.get(i * 3).child(0).text();
                                tempStr = tempStr.replace(" ", "").replace("：", "");
                                String[] dateStrA = tempStr.split("--");
                                jobHunterTrainExperience.setTrainBeginDate(DateUtil.getDate(dateStrA[0], "yyyy/MM"));
                                jobHunterTrainExperience.setTrainEndDate(DateUtil.getDate(dateStrA[1], "yyyy/MM"));
                                jobHunterService.addJobHunterTrainExperience(jobHunterTrainExperience);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    jobHunterService.modJobHunter(jobHunter, true);
                } catch (Exception e) {
                    return null;
                }
            }
            f.getFile().delete();
        } catch (Exception e) {
            f.getFile().delete();
            e.printStackTrace();
        }
        log.debug("[importResume] end");
        return retMap;
    }

}
