package com.delta.worko2o.action;

import com.delta.worko2o.model.Envelope;
import com.delta.worko2o.util.DateUtil;
import com.delta.worko2o.util.URLUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Messages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if ("loginRoom".equals(action)) { // 登录时，写入系统公告
            this.loginRoom(request, response);
        } else if ("sendMessage".equals(action)) {
            this.sendMessages(request, response);
        } else if ("getMessages".equals(action)) {
            this.getMessages(request, response);
        }
    }

    public void loginRoom(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        try {

            ServletContext application = getServletContext();
            String roomNo = "room" + request.getSession().getAttribute("jobHunterId") + "" + request.getSession().getAttribute("employerId");


            String employersStr = URLUtil.sendPost("", "http://www.maojuren.com/page/demo/job/envelope/getbyid?jobHunterId=" + request.getSession().getAttribute("jobHunterId") + "&employerId=" + request.getSession().getAttribute("employerId"));
            JSONObject jsonObject1 = JSONObject.fromObject(employersStr);
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("envelope");
            List<Envelope> list1 = (List<Envelope>) JSONArray.toList(jsonArray1, Envelope.class);

            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sourceMessage = "";
            for (int i = 0; i < list1.size(); ++i) {

                URLUtil.sendPost("", "http://www.maojuren.com/page/demo/job/envelope/modenvelope?envelopeId=" + list1.get(i).getEnvelopeId() + "&unread=0");

                if (list1.get(i).getAuthor() == 0) {
                    sourceMessage += "<div class=\"marginl10\"><div class=\"clra6a6a6\">" + request.getSession().getAttribute("jobHunterName")
                            + " <span>&nbsp;&nbsp;" + dft.format(list1.get(i).getPublishTime()) + "</span><div class=\"clr1b1b1b\">" +
                            list1.get(i).getContent() +
                            "</div></div></div>";
                } else {
                    sourceMessage += "<div class=\"marginl10\"><div class=\"clra6a6a6\">" + request.getSession().getAttribute("employerName")
                            + " <span>&nbsp;&nbsp;" + dft.format(list1.get(i).getPublishTime()) + "</span><div class=\"clr440062\">" +
                            list1.get(i).getContent() +
                            "</div></div></div>";
                }
            }
            sourceMessage += "<h4 class='text-center clra6a6a6 bottomdashedborder'>历史记录</h4>";
            application.setAttribute(roomNo, sourceMessage);

            request.getRequestDispatcher("/jsp/chat/login_ok.jsp").forward(request,
                    response);
        } catch (Exception ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void getMessages(HttpServletRequest request,
                            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ServletContext application = getServletContext();
            String roomNo = "room" + request.getSession().getAttribute("jobHunterId") + "" + request.getSession().getAttribute("employerId");
            Object msg = application.getAttribute(roomNo);
            if (null != msg) {
                String msgStr = (String) msg;
                response.getWriter().write(msgStr);
                response.getWriter().flush();
            }
        } catch (Exception ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    public void sendMessages(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Random random = new Random();
        String content = request.getParameter("content");
        String sendTime = new Date().toLocaleString();

        ServletContext application = getServletContext();
        String sourceMessage = "";
        String roomNo = "room" + request.getSession().getAttribute("jobHunterId") + "" + request.getSession().getAttribute("employerId");
        if (null != application.getAttribute(roomNo)) {
            sourceMessage = application.getAttribute(roomNo).toString();
        }

        try {
            int userType = "hunter".equals(request.getSession().getAttribute("userType")) ? 0 : 1;

            if (userType == 0) {
                HttpSession session = request.getSession();
                content = "<div class=\"marginl10\"><div class=\"clra6a6a6\">" + request.getSession().getAttribute("jobHunterName")
                        + " <span>&nbsp;&nbsp;" + sendTime + "</span><div class=\"clr1b1b1b\">" +
                        content +
                        "</div></div></div>";
            } else {
                content = "<div class=\"marginl10\"><div class=\"clra6a6a6\">" + request.getSession().getAttribute("employerName")
                        + " <span>&nbsp;&nbsp;" + sendTime + "</span><div class=\"clr440062\">" +
                        content +
                        "</div></div></div>";
            }
            sourceMessage += content;
            application.setAttribute(roomNo, sourceMessage);
            response.getWriter().write(sourceMessage);
            response.getWriter().flush();

            Ioc ioc = new NutIoc(new JsonLoader("ioc/dataSource.js"));
            Dao dao = new NutDao(ioc.get(DataSource.class));
            Envelope envelope = new Envelope();
            envelope.setAuthor(userType);
            envelope.setEmployerId(Integer.valueOf((String) request.getSession().getAttribute("employerId")));
            envelope.setJobHunterId(Integer.valueOf((String) request.getSession().getAttribute("jobHunterId")));
            envelope.setContent(request.getParameter("content"));
            envelope.setUnread(1);
            envelope.setPublishTime(DateUtil.getNowDate());
            dao.insert(envelope);
        } catch (Exception ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
