package com.william.jifanghelpdesk.model;

import com.alibaba.fastjson.JSON;
import com.william.jifanghelpdesk.bean.User;
import com.william.jifanghelpdesk.utils.http.Constans;
import com.william.jifanghelpdesk.utils.http.OkHttpUtils;
import com.william.jifanghelpdesk.utils.sp.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel {
    FormBody.Builder body = new FormBody.Builder();

    public int loginState = 0; // 验证结果状态标记
    public static final int LOGIN_TRUE = 1;
    public static final int LOGIN_FLASE = 2;

    public int post(final String username, final String password){
        class RunThread implements Runnable {
            @Override
            public void run() {
                body.add("username", username);
                body.add("password", password);
                try {
                    OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
                    Request request = new Request.Builder()
                            .url(Constans.Base_Url + Constans.Login_Uri)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "*/*")
                            .post(body.build())
                            .build();
                    Response response = okHttpUtils.initOkHttp().newCall(request).execute();
                    String responseData = response.body().string();
                    int code = response.code();
                    if (code == 200) {
                        User person = JSON.parseObject(responseData, User.class);
                        person.setUser_name(username);
                        person.setPassword(password);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(person.getUSER_NAME(), person.getUser_name());
                        map.put(person.getPASSWORD(), person.getPassword());
                        map.put(person.getACCESS_TOKEN(), person.getAccess_token());
                        SharedPreferencesUtils.getInstance().putMap(person.getUser_name(), map);
                        loginState = LOGIN_TRUE;
                    } else {
                        loginState = LOGIN_FLASE;
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
        return loginState;
    }

    public int getAutoCode(String CODE) {
        return SharedPreferencesUtils.getInstance().getInt(CODE, 0);
    }

    public String getAutoUser(String USER) {
        return SharedPreferencesUtils.getInstance().getString(USER, "");
    }

    public Map<String, String> getUserInfo(String username) {
        return SharedPreferencesUtils.getInstance().getMap(username);
    }

    public String getUserPSW(String username, String PASSWORD) {
        Map<String, String> userInfo = getUserInfo(username);
        return userInfo.get(PASSWORD);
    }

    public void setAuto(String USER, String username, String CODE) {
        SharedPreferencesUtils.getInstance().putString(USER, username);
        SharedPreferencesUtils.getInstance().putInt(CODE, 1);
    }

    public void removeAuto(String username, String CODE) {
        SharedPreferencesUtils.getInstance().remove(username);
        SharedPreferencesUtils.getInstance().putInt(CODE, 0);
    }
}

