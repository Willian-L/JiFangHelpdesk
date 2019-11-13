package com.william.jifanghelpdesk.bean;

public class ResponseLogin {
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "access_token='" + access_token + '\'' +
                '}';
    }

    private String access_token;
}
