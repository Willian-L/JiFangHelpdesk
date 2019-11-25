package com.william.jifanghelpdesk.Utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.william.jifanghelpdesk.bean.User;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class LoginPost {
    FormBody.Builder body = new FormBody.Builder();

    private boolean result = false;

    public boolean loginPost(String username, String password) {
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
                result = true;
                User person = JSON.parseObject(responseData, User.class);
                person.setUser_name(username);
                person.setPassword(password);
                Map<String, String> map = new HashMap<String, String>();
                map.put(person.getUSER_NAME(), person.getUser_name());
                map.put(person.getPASSWORD(), person.getPassword());
                map.put(person.getACCESS_TOKEN(), person.getAccess_token());
                SharedPreferencesUtils.getInstance().putMap(person.getUser_name(), map);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}

