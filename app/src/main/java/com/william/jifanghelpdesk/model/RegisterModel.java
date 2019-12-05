package com.william.jifanghelpdesk.model;

import com.william.jifanghelpdesk.utils.http.Constans;
import com.william.jifanghelpdesk.utils.http.OkHttpUtils;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModel {
    FormBody.Builder body = new FormBody.Builder();

    private int registerState = 0; // 验证结果状态标记
    public static final int REGISTER_TRUE = 1;
    public static final int REGISTER_FLASE = 2;

    public int post(final String email, final String first_name, final String last_name, final String password) {
        class RunThread implements Runnable {
            @Override
            public void run() {
                body.add("email", email);
                body.add("first_name", first_name);
                body.add("last_name", last_name);
                body.add("password", password);
                try {
                    OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
                    Request request = new Request.Builder()
                            .url(Constans.Base_Url + Constans.Registration_Url)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "*/*")
                            .post(body.build())
                            .build();
                    Response response = okHttpUtils.initOkHttp().newCall(request).execute();
                    int code = response.code();
                    if (code == 200) {
                        registerState = REGISTER_TRUE;
                    } else {
                        registerState = REGISTER_FLASE;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Thread run = new Thread(new RunThread());
        run.setPriority(Thread.MAX_PRIORITY);
        run.start();
        try {
            run.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerState;
    }
}
