package com.delta.worko2o.util;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 15-9-2
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
public class SessionData {
    public String username;
    public String password;
    public String jobHunterId;
    public String current_user;
    public String avatarPath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobHunterId() {
        return jobHunterId;
    }

    public void setJobHunterId(String jobHunterId) {
        this.jobHunterId = jobHunterId;
    }

    public String getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(String current_user) {
        this.current_user = current_user;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

}
