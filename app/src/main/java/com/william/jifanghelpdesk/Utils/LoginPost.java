package com.william.jifanghelpdesk.Utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class LoginPost {
    /**
     * 单例模式
     */
    private volatile static LoginPost loginPost;

    private LoginPost() {
    }

    public static LoginPost getLoginPost() {

        if (loginPost == null) {
            synchronized (LoginPost.class) {
                if (loginPost == null) {
                    loginPost = new LoginPost();
                }
            }
        }
        return loginPost;
    }

    public static Response loginPost(String username, String password) {
        FormBody.Builder body = new FormBody.Builder();
        body.add("username", username);
        body.add("password", password);

        Request request = new Request.Builder()
                .url(Constans.Base_Url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .post(body.build())
                .build();
        Response response = null;
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        try {
            response = okHttpUtils.initOkHttp().newCall(request).execute();

            System.out.println("123456 + " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
