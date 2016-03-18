package com.delta.worko2o.action; /**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-12
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */


import com.delta.worko2o.util.SignUtil;
import com.tencent.CoreService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 核心请求处理类
 *
 * @author liufeng
 * @date 2013-05-18
 */
public class CoreServlet extends HttpServlet {


    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = (new CoreService()).processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}

