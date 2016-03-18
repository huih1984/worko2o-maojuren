package com.delta.worko2o.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;


public class PropertiesUtil {


    public static Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();
    public static Map<String, Properties> props = new HashMap<String, Properties>();

    /**
     * 将文件读取到Map中
     *
     * @param name
     * @return
     */
    private static ResourceBundle load(String name) {
        ResourceBundle res = bundles.get(name);

        try {
            if (res == null) {
                res = ResourceBundle.getBundle(name);
                if (res == null) {
                    res = ResourceBundle.getBundle(name, null, PropertiesUtil.class.getClassLoader());
                }
                if (null != res) {
                    bundles.put(name, res);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据完整的限定类名和属性的名称获取配置的属性值
     *
     * @param baseName
     * @param key
     * @return String
     */
    public static String get(String baseName, String key) {
        return load(baseName).getString(key);

    }
}
