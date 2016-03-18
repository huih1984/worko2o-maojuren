package com.delta.worko2o.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Emailutil {
    protected javax.mail.Session session = null;

    // 设置主机邮箱地址
//	String mailUser = ""; // 用户名
//	String host = "smtp.163.com"; //smtp协议
//	String pwd = ""; // 邮箱密码
    String mailUser = "server@maojuren.com"; // 用户名
    String host = "smtp.exmail.qq.com"; //smtp协议
    String pwd = "delta.worko2o"; // 邮箱密码

    String sender = "server@maojuren.com"; // 发送人邮箱
    String getter = ""; // 反馈邮箱

    public Emailutil() {
        /*属性*/
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smpt.port", "25");

        // session getInstance()
        session = javax.mail.Session.getInstance(props, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, pwd);
            }
        });

        //
        //session.setDebug(true);
    }

    public void send(String mailto, String content, String type) {
        try {
            this.getter = mailto;
            Message msg = new MimeMessage(session);
            // 设置发送人地址
            msg.setFrom(new InternetAddress(sender, "猫举人网"));
            // 设置发送地址
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getter));
            // 设置发送时间
            msg.setSentDate(new Date());
            // 设置发送内容
            String htmltext = content;
            msg.setContent(htmltext, "text/html;charset=utf-8");
            // 设置发送名称
            if (type == "1") {
                msg.setSubject("邮箱验证");
            } else if (type == "2") {
                msg.setSubject("密码重置");
            } else if (type == "3") {
                msg.setSubject("简历通知");
            } else {
                msg.setSubject("无题");
            }


            Transport transport = session.getTransport("smtp"); //获取发送协议
            transport.connect(host, mailUser, pwd);// 连接主机邮箱信息
            transport.send(msg); // 发送邮箱信息

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static String SelectEmailLink(String email) {
        String emailCut = email.substring(email.indexOf("@") + 1, email.indexOf("."));
        if (emailCut.equals("qq")) {
            return "https://mail.qq.com/";
        } else if (emailCut.equals("163")) {
            return "http://mail.163.com/";
        } else if (emailCut.equals("126")) {
            return "http://mail.126.com/";
        } else if (emailCut.equals("sina")) {
            return "http://mail.sina.com.cn/";
        } else if (emailCut.equals("yeah")) {
            return "http://www.yeah.net/";
        } else if (emailCut.equals("188")) {
            return "http://www.188.com/";
        } else if (emailCut.equals("sohu")) {
            return "http://mail.sohu.com/";
        } else if (emailCut.equals("yahoo")) {
            return "http://mail.cn.yahoo.com/";
        } else if (emailCut.equals("tom")) {
            return "http://mail.tom.com/";
        } else if (emailCut.equals("21cn")) {
            return "http://mail.21cn.com/";
        } else if (emailCut.equals("gmail")) {
            return "http://mail.gmail.com/";
        } else if (emailCut.equals("chinaren")) {
            return "http://mail.chinaren.com/";
        } else if (emailCut.equals("tianya")) {
            return "http://mail.tianya.cn/";
        } else if (emailCut.equals("sogou")) {
            return "http://mail.sogou.com/";
        } else if (emailCut.equals("189")) {
            return "http://webmail3.189.cn/webmail/";
        } else {
            return "http://www.baidu.com/s?wd=" + emailCut + "邮箱登陆";
        }
    }
}
