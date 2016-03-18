package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;

/**
 * <Description> DJANGO_SESSION的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-29-02 02:29:24 <br>
 * @since V1.0<br>
 */
@Table("DJANGO_SESSION")
public class jangoSession {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * session_key
     */
    @Name
    @Column(value = "session_key")
    private String sessionKey;

    /**
     * session_data
     */
    @Column(value = "session_data")
    private String sessionData;

    /**
     * expire_date
     */
    @Column(value = "expire_date")
    private java.util.Date expireDate;

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionData() {
        return this.sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }

    public java.util.Date getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
    }

}
