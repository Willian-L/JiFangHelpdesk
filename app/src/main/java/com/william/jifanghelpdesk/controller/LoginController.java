package com.william.jifanghelpdesk.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.william.jifanghelpdesk.bean.User;
import com.william.jifanghelpdesk.context.MyApplication;
import com.william.jifanghelpdesk.model.LoginModel;
import com.william.jifanghelpdesk.utils.http.Constans;
import com.william.jifanghelpdesk.utils.http.OkHttpUtils;
import com.william.jifanghelpdesk.utils.sp.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public class LoginController {

    User user = new User();

    LoginModel model = new LoginModel();

    Context context = MyApplication.getInstance();

    private short record = 0; // 登录失败次数

    /**
     * 自动登录检查
     */
    public int checkLogin() {
        int result = 0; // 自动登录结果标记（0：不存在该账号   1：存在账号但密码错误   2：账号密码正确）
        int auto_code = model.getAutoCode(user.AUTO_CODE);
        if (auto_code == 1) {
            user.setUser_name(model.getAutoUser(user.AUTO_USER));
            user.setPassword(model.getUserPSW(user.getUser_name(), user.getPASSWORD()));
            if (LoginJudge(user.getUser_name(), user.getPassword())) {
                result = 2;
            } else {
                model.removeAuto(user.getUser_name(), user.AUTO_CODE);
                result = 1;
            }
        }
        return result;
    }

    /**
     * 登录逻辑
     *
     * @param username
     * @param password
     */
    public short Login(String username, String password) {
        user.setUser_name(username);
        user.setPassword(password);
        if (LoginJudge(user.getUser_name(), user.getPassword()) == true) {
            model.setAuto(user.AUTO_USER, user.getUser_name(), user.AUTO_CODE);
            record = -1;
        } else {
            record += 1;
        }
        return record;
    }

    public class postTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            if (model.post(strings[0], strings[1])) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true){

            }
        }
    }

    /**
     * 联网登录验证
     *
     * @param username
     * @param password
     * @return
     */
    private boolean LoginJudge(String username, String password) {
        if (model.post(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 密码自动填充
     *
     * @param username
     * @return
     */
    public boolean AutoFill(String username) {
        user.setUser_name(username);
        String password = model.getUserPSW(user.getUser_name(),user.getPASSWORD());
//        Map<String, String> map = model.getUserInfo(user.getUser_name());
        if (password != null) {
//            user.setPassword(model.getUserPSW(user.getUser_name(),user.getPASSWORD()));
            if (LoginJudge(user.getUser_name(), user.getPassword())) {
                return true;
            } else {
                password = null;
                return false;
            }
        } else {
            password = null;
            return false;
        }
    }
}
