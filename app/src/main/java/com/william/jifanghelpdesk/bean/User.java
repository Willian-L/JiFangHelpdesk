package com.william.jifanghelpdesk.bean;

import com.google.gson.JsonObject;

public class User {
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "User{" +
                "access_token='" + access_token + '\'' +
                '}';
    }

    private String access_token;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String user_name;
    private String password;

    public String getUSER_NAME() {
        return USER_NAME;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }
    public String getAUTO_USER() {
        return AUTO_USER;
    }
    public String getAUTO_CODE() {
        return AUTO_CODE;
    }

    private final String USER_NAME = "USERNAME";
    private final String PASSWORD = "PASSWORD";
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";
    // 自动登录识别KEY
    public final String AUTO_USER = "AUTO_USER";
    public final String AUTO_CODE = "AUTO_CODE";
}
