package com.delta.worko2o.action;

import net.sf.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-7-26
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public class EnvelopeChatListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("id") + "" + req.getParameter("type");
        JSONObject json = new JSONObject();
        ServletContext application = this.getServletContext();
        HashMap hashMap = (HashMap) application.getAttribute("userlist");
        if (hashMap.get(key) != null) {
            json.put("online", 1);
        } else {
            json.put("online", 0);
        }
        resp.getWriter().print(json.toString());
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
