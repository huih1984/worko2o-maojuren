package com.delta.worko2o.menu;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 14-6-18
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */

import com.delta.worko2o.util.WeixinUtil;
import com.delta.worko2o.weixinModel.*;

/**
 * 菜单管理器类
 *
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
    // private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wxca61c6bb2f120004";
        // 第三方用户唯一凭证密钥
        String appSecret = "a1404f63ad4ae54e59e1e69185a2d7ea";

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result) {
                int i = 0;
                // printf("菜单创建成功！");
            } else {
                // log.info("菜单创建失败，错误码：" + result);
            }
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
//        ViewButton mainBtn1 = new ViewButton();
//        mainBtn1.setName("12");
//        mainBtn1.setType("view");

//        CommonButton mainBtn1 = new CommonButton();
//        mainBtn1.setName("今日宣讲会");
//        mainBtn1.setType("click");
//        mainBtn1.setKey("1");

        ViewButton btn1 = new ViewButton();
        btn1.setName("宣讲会");
        btn1.setType("view");
        btn1.setUrl("http://www.maojuren.com/mlogin.jsp");

        ViewButton btn2 = new ViewButton();
        btn2.setName("职位");
        btn2.setType("view");
        btn2.setUrl("http://www.maojuren.com/index.jsp");

        ViewButton btn3 = new ViewButton();
        btn3.setName("电商资讯");
        btn3.setType("view");
        btn3.setUrl("http://www.maojuren.com/jsp/e_business/e_business_news.jsp");

        Menu menu = new Menu();
        menu.setButton(new Button[]{btn1, btn2, btn3});


        return menu;
    }
}