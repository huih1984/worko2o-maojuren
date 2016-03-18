package com.delta.worko2o.service.impl;

import com.delta.worko2o.model.Employer;
import com.delta.worko2o.model.JobHunter;
import com.delta.worko2o.model.User;
import com.delta.worko2o.service.api.EmailValidServiceApi;
import com.delta.worko2o.util.Emailutil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Calendar;
import java.util.List;

/**
 * @author hh
 */
@IocBean(name = "emailValidService")
public class EmailValidServiceImpl implements EmailValidServiceApi {

    public static final Log log = Logs.get();

    @Inject
    public Dao dao;

    @Override
    public void addUser(User user) {
        log.debug("[emailValidService]-[addUser]-begin");

//        user.setToken(CipherUtil.generatePassword(user.getEmail()
//                + user.getPassword()));
        Integer now = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
        user.setRegtime(now);
        user.setTokenExptime(now + 86400);
        dao.insert(user);

        log.debug("[emailValidService]-[addUser]-end");

    }

    @Override
    public void modUser(String token, boolean ignoreNull) {

        log.debug("[emailValidService]-[modUser]-begin");

		/*if (ignoreNull) {
            Daos.ext(dao, FieldFilter.create(Employer.class, true))
					.update(user);
		} else {
			dao.update(user);
		}*/
        Chain chain = Chain.make("status", 1);
        Condition c = Cnd.where("token", "=", token);
        dao.update("T_USER", chain, c);


        log.debug("[emailValidService]-[modUser]-end");

    }

    @Override
    public User qryUser(User user) {
        log.debug("[emailValidService]-[qryUser]-begin");

        Criteria cri = Cnd.cri();
        cri.where().andEquals("token", user.getToken());
        cri.where().andEquals("email", user.getEmail());
        cri.where().andEquals("userType", user.getUserType());

        List<User> tempList = dao.query(User.class, cri, null);

        log.debug("[emailValidService]-[qryUser]-end");

        if (Lang.isEmpty(tempList)) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    @Override
    public void deleteUser(User user) {

        log.debug("[emailValidService]-[deleteUser]-begin");

        dao.delete(user);

        log.debug("[emailValidService]-[deleteUser]-end");

    }

    class MyThread extends Thread {

        String content;
        User user;

        MyThread(User user) {
            this.user = user;
            String url = null;
            String thirdOpenIdStr = "";
            if (user.getTencentId() != null) {
                thirdOpenIdStr = "&tencentId=" + user.getTencentId();
            } else if (user.getSinaId() != null) {
                thirdOpenIdStr = "&sinaId=" + user.getSinaId();
            } else if (user.getWeixinId() != null) {
                thirdOpenIdStr = "&weixinId=" + user.getWeixinId();
            }
            if (user.getUserType() == 1) {
                url = "http://www.maojuren.com/jsp/reg/hunter_reg_step2.jsp?email=" + user.getEmail() + "&password=" + user.getPassword() + thirdOpenIdStr;
            } else {
                url = "http://www.maojuren.com/jsp/reg/employer_reg_step2.jsp?email=" + user.getEmail() + "&password=" + user.getPassword() + thirdOpenIdStr;
            }

            content = "<!DOCTYPE html>\n" +
                    "<!-- saved from url=(0038)http://v3.bootcss.com/examples/signin/ -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"mmsgLetter\"\n" +
                    "     style=\"width:580px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
                    "    <div class=\"mmsgLetterHeader\"\n" +
                    "         style=\"height:23px;background:url(" +
                    "http://www.maojuren.com/img/mmsgletter_2_bg_topline.png" +
                    ") repeat-x 0 0;\">\n" +
                    "    </div>\n" +
                    "    <div class=\"mmsgLetterContent\">" +
                    "<div style=\"text-align:right;\"><img style=\"width:200px;\" src=\"http://www.maojuren.com/img/logo.png\"></img></div>" +
                    "        <div>\n" +
                    "            <p>你好!</p>\n" +
                    "            <p>\n" +
                    "                感谢你注册猫举人网。 <br>\n" +
                    "                你的登录邮箱为：<a id='mailId' href=\"mailto:" +
                    user.getEmail() +
                    "\">" +
                    user.getEmail() +
                    "</a>。请点击以下链接激活帐号：\n" +
                    "            </p>\n" +
                    "\n" +
                    "            <p style=\"word-wrap:break-word;word-break:break-all;\">\n" +
                    "                <a href=\"" +
                    url +
                    "\" target=\"_blank\">" +
                    url +
                    "</a>\n" +
                    "            </p>\n" +
                    "\n" +
                    "            <p>\n" +
                    "                如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入猫举人网。 （该链接在24小时内有效，24小时后需要重新注册）\n" +
                    "\n" +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

        }

        @Override
        public void run() {
            Emailutil sendMail = new Emailutil();
            String type = "1";
            sendMail.send(user.getEmail(), content, type);
        }
    }

    class MyThread2 extends Thread {

        String content;
        User user;

        MyThread2(User user) {
            this.user = user;
            String url = null;
            url = "http://www.maojuren.com/jsp/password/reset.jsp?userType=" + user.getUserType() + "&email=" + user.getEmail() + "&token=" + user.getToken();


            content = "<!DOCTYPE html>\n" +
                    "<!-- saved from url=(0038)http://v3.bootcss.com/examples/signin/ -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"mmsgLetter\"\n" +
                    "     style=\"width:580px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
                    "    <div class=\"mmsgLetterHeader\"\n" +
                    "         style=\"height:23px;background:url(" +
                    "http://www.maojuren.com/img/mmsgletter_2_bg_topline.png" +
                    ") repeat-x 0 0;\">\n" +
                    "    </div>\n" +
                    "    <div class=\"mmsgLetterContent\">\n" +
                    "<div style=\"text-align:right;\"><img style=\"width:200px;\" src=\"http://www.maojuren.com/img/logo.png\"></img></div>" +
                    "        <div>\n" +
                    "            <p>你好!</p>\n" +
                    "            <p>\n" +
                    "                感谢你注册猫举人网。 <br>\n" +
                    "                你的登录邮箱为：<a id='mailId' href=\"mailto:" +
                    user.getEmail() +
                    "\">" +
                    user.getEmail() +
                    "</a>。请点击以下链接重置密码：\n" +
                    "            </p>\n" +
                    "\n" +
                    "            <p style=\"word-wrap:break-word;word-break:break-all;\">\n" +
                    "                <a href=\"" +
                    url +
                    "\" target=\"_blank\">" +
                    url +
                    "</a>\n" +
                    "            </p>\n" +
                    "\n" +
                    "            <p>\n" +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

        }


        @Override
        public void run() {
            Emailutil sendMail = new Emailutil();
            String type = "2";
            sendMail.send(user.getEmail(), content, type);
        }

    }

    class MyThread3 extends Thread {

        String content;
        Employer employer;
        JobHunter jobHunter;
        String jobName;

        MyThread3(Employer employer, JobHunter jobHunter, String jobName) {
            this.employer = employer;
            this.jobHunter = jobHunter;
            this.jobName = jobName;
            String url = null;
            url = "http://www.maojuren.com/jsp/employer/employer_jobs.jsp?employerId=" + employer.getEmployerId();


            content = "<!DOCTYPE html>\n" +
                    "<!-- saved from url=(0038)http://v3.bootcss.com/examples/signin/ -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"mmsgLetter\"\n" +
                    "     style=\"width:580px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
                    "    <div class=\"mmsgLetterHeader\"\n" +
                    "         style=\"height:23px;background:url(" +
                    "http://www.maojuren.com/img/mmsgletter_2_bg_topline.png" +
                    ") repeat-x 0 0;\">\n" +
                    "    </div>\n" +
                    "    <div class=\"mmsgLetterContent\">\n" +
                    "<div style=\"text-align:right;\"><img style=\"width:200px;\" src=\"http://www.maojuren.com/img/logo.png\"></img></div>" +
                    "        <div>\n" +
                    "            <p>" +
                    employer.getEmployerName() +
                    "            , 你好!</p>\n" +
                    "            <p>\n" +
                    "                你发布的职位:  " +
                    jobName +
                    "  收到新的简历，请查阅 ^_^" +
                    "            <hr style=\"height:4px; border:none;border-top:4px double  #9ACD32;\"/><br>\n" +
                    "            </p>\n" +
                    "            <p style=\"word-wrap:break-word;word-break:break-all;width:520px;background:#EAFFBE; margin:auto;\">\n" +
                    "<br>\n " +
                    "&nbsp;&nbsp;   " +
                    jobHunter.getJobHunterName() +
                    "的简历<br>\n" + "&nbsp;&nbsp;    " +
                    jobHunter.getJobHunterName() + "        " + jobHunter.getJobHunterBachelorDegree() + "        " + jobHunter.getJobHunterCollege() +
                    "    <br>\n" +
                    "&nbsp;&nbsp;专业： " + jobHunter.getJobHunterMajor() +
                    "    <br>\n" +
                    "&nbsp;&nbsp;目前所在地： " + jobHunter.getJobHunterCurrentCity() +
                    "<br>\n" +
                    "&nbsp;&nbsp;最近工作： " + jobHunter.getJobHunterLastOccupation() + ", " + jobHunter.getJobHunterLastEmployer() +
                    "<br>\n" +
                    "&nbsp;&nbsp;目前状态： " + jobHunter.getJobHunterCurrentStatus() +
                    "<br>\n" +
                    "    " +
                    "                <a href=\"" +
                    url +
                    "\" target=\"_blank\">" +
                    "&nbsp;&nbsp;查看完整简历 " +
                    "</a><br>\n" +

                    "" +
                    "            </p>\n" +
                    "\n" +
                    "            <p>\n" +
                    "&nbsp;&nbsp; " +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

        }


        @Override
        public void run() {
            Emailutil sendMail = new Emailutil();
            String type = "3";
            sendMail.send(employer.getHrEmail(), content, type);
        }

    }

    class MyThread4 extends Thread {

        String content;
        Employer employer;

        MyThread4(Employer employer) {
            this.employer = employer;
            String url = null;
            url = "http://www.maojuren.com/login.jsp?employerId=" + employer.getEmployerId();

            content = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"mmsgLetter\"\n" +
                    "     style=\"width:580px;margin:0 auto;padding:10px;color:#333;background-color:#fff;border:0px solid #aaa;border-radius:5px;-webkit-box-shadow:3px 3px 10px #999;-moz-box-shadow:3px 3px 10px #999;box-shadow:3px 3px 10px #999;font-family:Verdana, sans-serif; \">\n" +
                    "    <div class=\"mmsgLetterHeader\"\n" +
                    "         style=\"height:23px;background:url(" +
                    "http://www.maojuren.com/img/mmsgletter_2_bg_topline.png" +
                    ") repeat-x 0 0;\">\n" +
                    "    </div>\n" +
                    "    <div class=\"mmsgLetterContent\">\n" +
                    "<div style=\"text-align:right;\"><img style=\"width:200px;\" src=\"http://www.maojuren.com/img/logo.png\"></img></div>" +
                    "        <div>\n" +
                    "            <p>\n" +
                    " 您的注册已经审核通过，请完善资料，即可发布职位 ^_^" +
                    "            <hr style=\"height:4px; border:none;border-top:4px double  #9ACD32;\"/><br>\n" +
                    "            </p>\n" +
                    "            <p style=\"word-wrap:break-word;word-break:break-all;width:520px;background:#EAFFBE; margin:auto;\">\n" +
                    "<br>\n " +
                    "&nbsp;&nbsp;   " +
                    "    " +
                    "                <a href=\"" +
                    url +
                    "\" target=\"_blank\">" +
                    "&nbsp;&nbsp;立即登录 " +
                    "</a><br>\n" +

                    "" +
                    "            </p>\n" +
                    "\n" +
                    "            <p>\n" +
                    "&nbsp;&nbsp; " +
                    "            </p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

        }


        @Override
        public void run() {
            Emailutil sendMail = new Emailutil();
            String type = "3";
            sendMail.send(employer.getHrEmail(), content, type);
        }

    }

    @Override
    public void sendEmail(User user) {
        log.debug("[emailValidService]-[sendEmail]-begin");
        MyThread myThread = new MyThread(user);
        myThread.start();
        log.debug("[emailValidService]-[sendEmail]-end");
    }


    @Override
    public void sendResetPasswordEmail(User user) {
        log.debug("[emailValidService]-[sendResetPasswordEmail]-begin");

        MyThread2 myThread2 = new MyThread2(user);
        myThread2.start();
        log.debug("[emailValidService]-[sendResetPasswordEmail]-end");
    }

    @Override
    public void sendInform(Employer employer, JobHunter jobHunter, String jobName) {
        log.debug("[emailValidService]-[sendEmail]-begin");

        MyThread3 myThread3 = new MyThread3(employer, jobHunter, jobName);
        myThread3.start();
        log.debug("[emailValidService]-[sendEmail]-end");

    }

    @Override
    public void sendCheckPassedEmail(Employer employer) {
        log.debug("[emailValidService]-[sendEmail]-begin");
        MyThread4 myThread4 = new MyThread4(employer);
        myThread4.start();
        log.debug("[emailValidService]-[sendEmail]-end");
    }
}
