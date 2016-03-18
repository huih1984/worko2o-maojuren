/*
 * Created by Roy Dec 2, 2014 9:05:31 AM.                          
 * Copyright (c) 2000-2014 AnXunBen. All rights reserved. 
 */

package com.delta.worko2o.util;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class DwrMsg {

    public static void sendMsg(final String msg) {
        WebContext wctx = WebContextFactory.get();
        // System.out.println(wctx.getCurrentPage());
        Browser.withPage("/jsp/admin/approve.jsp", new Runnable() {
            public void run() {
                ScriptSessions.addFunctionCall("show", msg);
            }
        });

    }

}
