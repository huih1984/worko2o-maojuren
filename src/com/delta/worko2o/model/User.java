package com.delta.worko2o.model;

import org.nutz.dao.entity.annotation.*;

/**
 * <Description> T_USER的Pojo<br>
 *
 * @author 工具生成<br>
 * @version 1.0<br>
 * @CreateDate 2015-39-08 04:39:14 <br>
 * @since V1.0<br>
 */
@Table("T_USER")
public class User {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(value = "id")
    private Integer id;

    /**
     * username
     */
    @Column(value = "username")
    private String username;

    /**
     * password
     */
    @Column(value = "password")
    private String password;

    /**
     * email
     */
    @Column(value = "email")
    private String email;

    /**
     * token
     */
    @Column(value = "token")
    private String token;

    /**
     * token_exptime
     */
    @Column(value = "token_exptime")
    private Integer tokenExptime;

    /**
     * status
     */
    @Default("0")
    @Column(value = "status")
    private Integer status;

    /**
     * regtime
     */
    @Column(value = "regtime")
    private Integer regtime;

    /**
     * user_type
     */
    @Default("0")
    @Column(value = "user_type")
    private Integer userType;

    /**
     * sina_id
     */
    @Column(value = "sina_id")
    private String sinaId;

    /**
     * tencent_id
     */
    @Column(value = "tencent_id")
    private String tencentId;

    /**
     * weixin_id
     */
    @Column(value = "weixin_id")
    private String weixinId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTokenExptime() {
        return this.tokenExptime;
    }

    public void setTokenExptime(Integer tokenExptime) {
        this.tokenExptime = tokenExptime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegtime() {
        return this.regtime;
    }

    public void setRegtime(Integer regtime) {
        this.regtime = regtime;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSinaId() {
        return sinaId;
    }

    public void setSinaId(String sinaId) {
        this.sinaId = sinaId;
    }

    public String getTencentId() {
        return tencentId;
    }

    public void setTencentId(String tencentId) {
        this.tencentId = tencentId;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }
}
