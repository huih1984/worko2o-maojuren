package com.delta.worko2o.action;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-8-6
 * Time: 上午11:43
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

/**
 * xhEditor文件上传的Java - Servlet实现.
 *
 * @author easinchu
 */
@SuppressWarnings({"serial", "deprecation"})
public class UploadFileServlet extends HttpServlet {

//    @SuppressWarnings({"unchecked"})
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {

//        PrintWriter out = response.getWriter();
//        //定义上载文件的最大字节
//        int MAX_SIZE = 50 * 1024;
//        // 创建根路径的保存变量
//        String rootPath;
//        //声明文件读入类
//        DataInputStream in = null;
//        FileOutputStream fileOut = null;
//        //取得客户端的网络地址
//        String remoteAddr = request.getRemoteAddr();
//        //获得服务器的名字
//        String serverName = request.getServerName();
//
//        //取得互联网程序的绝对地址

//        String fileContextPathString = "/upload/";
////        String classBasePath = getClass().getClassLoader().getResource("").getPath();
//        String uploadBasePathString = classBasePath + "../../upload/";
//        Date currDate = new Date();
//
//        fileContextPathString += "question" + "/"
//                + new SimpleDateFormat("yyyyMMddHHmmss").format(currDate) + "/";
//
//
////创建文件的保存目录
//        rootPath = fileContextPathString;
//        //取得客户端上传的数据类型
//        String contentType = request.getContentType();
//        try {
//            if (contentType.indexOf("multipart/form-data") >= 0) {
////读入上传的数据
//                in = new DataInputStream(request.getInputStream());
//                int formDataLength = request.getContentLength();
//                if (formDataLength > MAX_SIZE) {
//                    out.println("<P>上传的文件字节数不可以超过" + MAX_SIZE + "</p>");
//                    return;
//                }
////保存上传文件的数据
//                byte dataBytes[] = new byte[formDataLength];
//                int byteRead = 0;
//                int totalBytesRead = 0;
////上传的数据保存在byte数组
//                while (totalBytesRead < formDataLength) {
//                    byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
//                    totalBytesRead += byteRead;
//                }
////根据byte数组创建字符串
//                String file = new String(dataBytes);
////out.println(file);
////取得上传的数据的文件名
//                String saveFile = file.substring(file.indexOf("filename=\"") + 10);
//                saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
//                saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
//                int lastIndex = contentType.lastIndexOf("=");
////取得数据的分隔字符串
//                String boundary = contentType.substring(lastIndex + 1, contentType.length());
////创建保存路径的文件名
//                String fileName = rootPath + saveFile;
////out.print(fileName);
//                int pos;
//                pos = file.indexOf("filename=\"");
//                pos = file.indexOf("\n", pos) + 1;
//                pos = file.indexOf("\n", pos) + 1;
//                pos = file.indexOf("\n", pos) + 1;
//                int boundaryLocation = file.indexOf(boundary, pos) - 4;
////out.println(boundaryLocation);
////取得文件数据的开始的位置
//                int startPos = ((file.substring(0, pos)).getBytes()).length;
////out.println(startPos);
////取得文件数据的结束的位置
//                int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
////out.println(endPos);
////检查上载文件是否存在
//                File checkFile = new File(fileName);
//                if (checkFile.exists()) {
//                    out.println("<p>" + saveFile + "文件已经存在.</p>");
//                }
////检查上载文件的目录是否存在
//                File fileDir = new File(rootPath);
//                if (!fileDir.exists()) {
//                    fileDir.mkdirs();
//                }
////创建文件的写出类
//                fileOut = new FileOutputStream(fileName);
////保存文件的数据
//                fileOut.write(dataBytes, startPos, (endPos - startPos));
//                fileOut.close();
//                out.println(saveFile + "文件成功上载.</p>");
//            } else {
//                String content = request.getContentType();
//                out.println("<p>上传的数据类型不是multipart/form-data</p>");
//            }
//        } catch (Exception ex) {
//            throw new ServletException(ex.getMessage());
//        }
//    }

    private static final long serialVersionUID = 1541334866883495283L;

    private static String baseDir = "/UploadFile/"; // 上传文件存储目录
    private static String fileExt = "jpg,jpeg,bmp,gif,png";
    private static Long maxSize = 0l;
    private static String dirType = "1"; // 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存


    public void init() throws ServletException {
        String classBasePath = getClass().getClassLoader().getResource("").getPath();
        String uploadBasePathString = classBasePath.replace("WEB-INF/classes/", "") + "upload/";
        uploadBasePathString += "question" + "/";
        File baseFile = new File(uploadBasePathString);
        try {
            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseDir = uploadBasePathString;
        /*获取文件类型参数*/
        fileExt = this.getInitParameter("fileExt");
        if (StringUtils.isBlank(fileExt)) fileExt = "jpg,jpeg,gif,bmp,png";

        String maxSize_str = this.getInitParameter("maxSize"); //获取文件大小参数
        if (StringUtils.isNotEmpty(maxSize_str)) {
            maxSize = new Long(maxSize_str);
        } else {
            maxSize = Long.valueOf("524288");
        }

        dirType = this.getInitParameter("dirType"); //获取文件目录类型参数

        if (StringUtils.isEmpty(dirType)) dirType = "1";
        if (",0,1,2,3,".indexOf("," + dirType + ",") < 0) dirType = "1";
    }


    // 上传文件数据处理过程

    @SuppressWarnings({"unchecked"})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        String err = "";
        String newFileName = "";

        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传
            try {
                String dispoString = request.getHeader("Content-Disposition");
                int iFindStart = dispoString.indexOf("name=\"") + 6;
                int iFindEnd = dispoString.indexOf("\"", iFindStart);

                iFindStart = dispoString.indexOf("filename=\"") + 10;
                iFindEnd = dispoString.indexOf("\"", iFindStart);
                String sFileName = dispoString.substring(iFindStart, iFindEnd);

                int i = request.getContentLength();
                byte buffer[] = new byte[i];
                int j = 0;
                while (j < i) { //获取表单的上传文件
                    int k = request.getInputStream().read(buffer, j, i - j);
                    j += k;
                }

                if (buffer.length == 0) { //文件是否为空
                    printInfo(response, "上传文件不能为空", "");
                    return;
                }
                if (maxSize > 0 && buffer.length > maxSize) { //检查文件大小
                    printInfo(response, "上传文件的大小超出限制", "");
                    return;
                }

                String filepathString = getSaveFilePath(sFileName, response);
//                if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型


                OutputStream out = new BufferedOutputStream(new FileOutputStream(filepathString, true));
                out.write(buffer);
                out.close();
                newFileName = filepathString;
//                response.getWriter().print("{filename:" +filepathString.substring(filepathString.indexOf("/upload")) + "}");
//                response.getWriter().flush();
//                response.getWriter().close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        } else {
//            DiskFileUpload upload = new DiskFileUpload();
//
//            try {
//                List<FileItem> items = upload.parseRequest(request);
//                Map<String, Serializable> fields = new HashMap<String, Serializable>();
//                Iterator<FileItem> iter = items.iterator();
//                while (iter.hasNext()) {
//                    FileItem item = (FileItem) iter.next();
//                    if (item.isFormField())
//                        fields.put(item.getFieldName(), item.getString());
//                    else
//                        fields.put(item.getFieldName(), item);
//                }
//                FileItem uploadFile = (FileItem) fields.get("filedata"); //获取表单的上传文件
//                String fileNameLong = uploadFile.getName(); //获取文件上传路径名称
//
//                if (uploadFile.getSize() == 0) { //文件是否为空
//                    printInfo(response, "上传文件不能为空", "");
//                    return;
//                }
//                if (maxSize > 0 && uploadFile.getSize() > maxSize) { //检查文件大小
//                    printInfo(response, "上传文件的大小超出限制", "");
//                    return;
//                }
//
//                String filepathString = getSaveFilePath(fileNameLong, response);
//                if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型
//
//                File savefile = new File( filepathString);
//                uploadFile.write(savefile); //存储上传文件
//
//                newFileName = filepathString;
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                newFileName = "";
//                err = "错误: " + ex.getMessage();
//            }
        }
        printInfo(response, err, newFileName);
    }

    public String getSaveFilePath(String sFileName, HttpServletResponse response) throws IOException {
        String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1); //获取文件扩展名

        if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0) { //检查文件类型
            printInfo(response, "不允许上传此类型的文件", "");
            return "不允许上传此类型的文件";
        }

        String fileFolder = ""; // 0:不建目录, 1:按天存入目录, 2:按月存入目录, 3:按扩展名存目录.建议使用按天存。
        if (dirType.equalsIgnoreCase("1")) fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (dirType.equalsIgnoreCase("2")) fileFolder = new SimpleDateFormat("yyyyMM").format(new Date());
        if (dirType.equalsIgnoreCase("3")) fileFolder = extensionName.toLowerCase();

        String saveDirPath = baseDir + fileFolder + "/"; //文件存储的相对路径
        File baseFile = new File(saveDirPath);
        try {
            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename = String.valueOf(new SimpleDateFormat("HHmmss").format(new Date())); //重命名文件

        return saveDirPath + filename + "." + extensionName;
    }


    // 使用I/O流输出 json格式的数据

    public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
        out.flush();
        out.close();
    }
}
