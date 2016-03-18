package com.delta.worko2o.util;


import javax.servlet.ServletContext;

/**
 * 用于上传ioc : myUpload.js
 */
public class FileUtil {
    private ServletContext sc;

    public String getPath(String path) {
        return sc.getRealPath(path);
    }
}