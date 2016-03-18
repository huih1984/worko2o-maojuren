package com.delta.worko2o.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.mvc.Mvcs;

/**
 * <Description> <br>
 * 根据数据库表生成javabean
 *
 * @author xuyh<br>
 * @version 1.0<br>
 * @CreateDate 2014年11月10日 <br>
 * @see com.delta.worko2o.util <br>
 */
public class DBTable2JavaBeanUtil extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String TITLE = "根据数据库生成javabean小工具";

    private String[] filedNames = new String[]{"表名", "包名", "输出目录", "模板文件"};

    private JTextField[] textFields;

    private JLabel[] tips;

    private DataSource dataSource;

    private String template;

    private DateFormat dateFormat;

    /**
     * 下划线
     */
    private final char UNDERLINE = '_';

    /**
     * 空白
     */
    private final String BLANK = "";

    public DBTable2JavaBeanUtil() {
        dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        textFields = new JTextField[filedNames.length];
        tips = new JLabel[filedNames.length];

        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 540, 100 + 30 * filedNames.length);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel label = null;
        JTextField textField = null;
        for (int i = 0; i < filedNames.length; i++) {
            label = new JLabel(filedNames[i]);
            label.setBounds(40, 13 + (i * 30), 50, 15);
            panel.add(label);

            textField = new JTextField();
            textField.setBounds(120, 13 + (i * 30), 250, 20);
            textFields[i] = textField;
            panel.add(textField);

            label = new JLabel("");
            label.setBounds(380, 13 + (i * 30), 150, 15);
            label.setForeground(Color.RED);
            tips[i] = label;
            panel.add(label);
        }

        setDefaultValue();

        JButton button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    export();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setBounds(145, 20 + 30 * filedNames.length, 93, 23);
        panel.add(button);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    private void setDefaultValue() {
        tips[0].setText("表名不填则导出所有的表");
//		textFields[1].setText("com.delta.worko2o.model");
//		ClassLoader classLoader = this.getClass().getClassLoader();
//		textFields[2].setText(StringUtils.replace(classLoader.getResource("")
//				.getPath(), "/build/classes/", "/src/"));
//		textFields[3].setText(StringUtils.replace(classLoader.getResource("")
//				.getPath(), "/build/classes/",
//				"/src/com/delta/worko2o/util/template"));

        textFields[1].setText("com.delta.worko2o.model");
        ClassLoader classLoader = this.getClass().getClassLoader();
        textFields[2].setText("/F:/svn_worko2o/worko2oForIdea/worko2o/src");
        textFields[3].setText("/F:/svn_worko2o/worko2oForIdea/worko2o/src/com/delta/worko2o/util/template");
    }

    public void export() throws Exception {

        String tablename = textFields[0].getText();
        String packname = textFields[1].getText();
        String dirstr = textFields[2].getText();// 空表示当前目录
        String tempPath = textFields[3].getText();

        if (StringUtil.isEmpty(tempPath) || !new File(tempPath).exists()) {
            tips[3].setText("大侠你的模板文件呢？");
            return;
        } else {
            tips[3].setText(BLANK);
        }

        if (StringUtil.isEmpty(dirstr)) {
            tips[2].setText("给你导到根目录去了");
            dirstr = BLANK;
        } else {
            tips[2].setText(BLANK);
        }

        if (StringUtil.isEmpty(packname)) {
            tips[1].setText("w靠，你居然不写包名");
            packname = BLANK;
        } else {
            tips[1].setText(BLANK);
        }

        File dir = new File(dirstr + "/"
                + StringUtils.replace(packname, ".", "/"));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String outputdir = dir.getAbsolutePath();// bean的生成目录

        Connection conn = null;

        try {
            template = readFile(tempPath);
            conn = dataSource.getConnection();
            if (StringUtil.isEmpty(tablename)) {
                parseAllTable(conn, packname, outputdir);
            } else {
                parseTableByShowCreate(conn, tablename, packname, outputdir);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    /**
     * 开始处理生成所有表 如果不传入表名，表示将数据库中所有表生成bean; 可以指定表名生成bean;
     *
     * @throws java.io.IOException
     */
    public void parseAllTable(Connection conn, String packname, String outputdir)
            throws Exception {
        ResultSet rs = null;
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getTables(conn.getCatalog(), null, null,
                    new String[]{"TABLE"});

            while (rs.next()) {
                parseTableByShowCreate(conn, rs.getString("TABLE_NAME"),
                        packname, outputdir);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    /**
     * 通过 mysql的 show create table TABLE_NAME逆向生成Bean;
     *
     * @param conn
     * @param tablename
     * @param outputdir
     * @param packname
     * @throws java.io.IOException
     */
    public void parseTableByShowCreate(Connection conn, String tablename,
                                       String packname, String outputdir) throws Exception {
        String className = toCamelCase(tablename).substring(1);// 去掉t前缀
        tablename = StringUtils.upperCase(tablename);
        File file = new File(outputdir + "/" + className + ".java");
//		if (file.exists()) {
//			System.out.println("文件已经存在，请删除后在生成。" + file.getAbsoluteFile());
//			return;
//		}
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from " + tablename
                    + " where 1 = 2");
            ResultSetMetaData metaData = ps.executeQuery().getMetaData();

            StringBuilder fields = new StringBuilder();
            StringBuilder methods = new StringBuilder();

            String pkColum = null;
            ResultSet rs = conn.getMetaData().getPrimaryKeys(conn.getCatalog(),
                    null, tablename);
            int index = 0;
            while (rs.next()) {
                if (index++ > 0) {
                    pkColum = null;
                    break;
                }
                pkColum = rs.getString(4);
            }

            for (int i = 0, size = metaData.getColumnCount(); i < size; i++) {
                String cmt = metaData.getColumnLabel(i + 1);
                String field = toCamelCase(cmt);
                String type = typeTrans(metaData.getColumnTypeName(i + 1));
                fields.append('\n').append(
                        getFieldStr(field, type, cmt, pkColum));
                methods.append('\n').append(getMethodStr(field, type));
            }

            writeFile(StringUtils.replaceEach(template, new String[]{
                    "${PACKAGE}", "${CLASSNAME}", "${CODE}", "${TABLENAME}",
                    "${DATE}", "${TABLE}"}, new String[]{
                    packname,
                    className,
                    fields.append(methods).toString(),
                    tablename,
                    dateFormat.format(new Date()),
                    StringUtil.isEmpty(pkColum) ? BLANK : "@Table(\""
                            + tablename + "\")"}), file);
            System.out.println("生成文件成功。" + file.getAbsoluteFile());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * @param type
     * @return
     */
    private String getMethodStr(String field, String type) {
        StringBuilder get = new StringBuilder('\n');
        get.append("    ").append("public ").append(type).append(" ");
        if (type.equals("boolean")) {
            get.append(field);
        } else {
            get.append("get");
            get.append(upperFirestChar(field));
        }
        get.append("() {").append("\r\n        return this.").append(field)
                .append(";\r\n    }\r\n");

        StringBuilder set = new StringBuilder("\n");
        set.append("    public void ");
        if (type.equals("boolean")) {
            set.append(field);
        } else {
            set.append("set");
            set.append(upperFirestChar(field));
        }
        set.append("(").append(type).append(" ").append(field)
                .append(") {\r\n        this.").append(field).append(" = ")
                .append(field).append(";\r\n    }\r\n");
        get.append(set);
        return get.toString();
    }

    private String upperFirestChar(String src) {
        return src.substring(0, 1).toUpperCase().concat(src.substring(1));
    }

    private String getFieldStr(String field, String type, String cmt,
                               String pkColum) {
        StringBuilder sb = new StringBuilder('\n');
        sb.append("    ").append("/** ").append(cmt).append(" */").append('\n');
        if (cmt.equals(pkColum)) {
            if ("Integer".equals(type) || "Long".equals(type)) {
                sb.append("    ").append("@Id").append('\n');
            } else if ("String".equals(type)) {
                sb.append("    ").append("@Name").append('\n');
            }

        }
        sb.append("    ").append("@Column(value = \"").append(cmt)
                .append("\")").append('\n');
        sb.append("    ").append("private ").append(type).append(" ")
                .append(field).append(';').append('\n');
        return sb.toString();
    }

    /**
     * mysql的类型转换到java 类型参考文章
     * http://hi.baidu.com/wwtvanessa/blog/item/9fe555945a07bd16d31b70cd.html
     */
    private String typeTrans(String type) {
        if ("TINYINT".equals(type) || "INTEGER".equals(type)
                || "INT".equals(type)) {
            return "Integer";
        } else if ("LONG".equals(type)) {
            return "Long";
        } else if ("FLOAT".equals(type) || "DOUBLE".equals(type)) {
            return "Double";
        } else if ("DATE".equals(type) || "TIME".equals(type)
                || "DATETIME".equals(type) || "TIMESTAMP".equals(type)) {
            return "java.util.Date";
        } else if (type.contains("BINARY") || type.contains("BLOB")) {
            return "byte[]";
        } else {
            return "String";
        }
    }

    /**
     * 转变成小驼峰命名
     *
     * @param s
     * @return
     */
    private String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == UNDERLINE) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 取数据源
     *
     * @return
     */
    private static DataSource getDs() {
//        Ioc ioc = Mvcs.ctx().getDefaultIoc();
        Ioc ioc = new NutIoc(new JsonLoader("ioc/dataSource.js"));
        return ioc.get(DataSource.class);
    }

    /**
     * 读文件
     *
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            BufferedReader in = null;
            String line = null;
            try {
                in = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return sb.toString();
            } catch (Exception e) {
                throw new IOException(e);
            } finally {
                if (in != null) {
                    in.close();
                }
            }

        }
        return null;
    }

    /**
     * 写文件
     *
     * @param contents
     * @param file
     * @throws java.io.IOException
     */
    public static void writeFile(String contents, File file) throws IOException {
        if (file != null) {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(file));
                out.write(contents);
                out.flush();
            } catch (Exception e) {
                throw new IOException(e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        DBTable2JavaBeanUtil frame = new DBTable2JavaBeanUtil();
                        frame.dataSource = getDs();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
