var ioc = {

    /**
     * 数据源配置:使用德鲁伊数据源
     */
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            driverClassName: "org.gjt.mm.mysql.Driver",
            url: "jdbc:mysql://127.0.0.1:3306/worko2o",
            username: "root",
            password: "03-0627182.delta",
            filters: "stat,log4j",
            proxyFilters: [
                {refer: 'statFilter'}
            ]
        }
    },

    statFilter: {
        type: "com.alibaba.druid.filter.stat.StatFilter",
        fields: {
            slowSqlMillis: "1000",
            logSlowSql: "true"
        }
    },

    /**
     * dao注入,代码中直接使用即可
     */
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [
            {refer: 'dataSource'},
            {refer: "sqls"}
        ]
    },

    /**
     * 自定义sql的注入,NutDao中直接引用即可
     */
    sqls: {
        type: 'org.nutz.dao.impl.FileSqlManager',
        args: [ 'sqls' ],
        fields: {
            regex: '^.*[.]sqls$'
        }
    },

    utils: {
        type: 'com.delta.worko2o.util.FileUtil',
        fields: {
            sc: {app: '$servlet'}   // 将 ServletContext 对象注入 MyUtils
        }
    },

    tmpFilePool: {
        type: 'org.nutz.filepool.NutFilePool',
        // 临时文件最大个数为 1000 个
        args: [
            {java: '$utils.getPath("/WEB-INF/tmp")'},
            1000
        ]
//        args: ["D:/Program Files/apache-tomcat-7.0.42/webapps/worko2o/upload", 1000]
    },
    uploadFileContext: {
        type: 'org.nutz.mvc.upload.UploadingContext',
        singleton: false,
        args: [
            { refer: 'tmpFilePool' }
        ],
        fields: {
            // 是否忽略空文件, 默认为 false
            ignoreNull: true,
            // 单个文件最大尺寸(大约的值，单位为字节，即 1048576 为 1M)
            maxFileSize: 1048576,
            // 正则表达式匹配可以支持的文件名
            nameFilter: '^(.+[.])(gif|jpg|png)$'
        }
    },

    myUpload: {
        type: 'org.nutz.mvc.upload.UploadAdaptor',
        singleton: false,
        args: [
            { refer: 'uploadFileContext' }
        ]
    }

}
