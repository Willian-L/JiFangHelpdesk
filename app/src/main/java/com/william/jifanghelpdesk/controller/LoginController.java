package com.william.jifanghelpdesk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.william.jifanghelpdesk.bean.User;
import com.william.jifanghelpdesk.model.LoginModel;

import java.util.Map;

public class LoginController {

    private User user = new User();
    private LoginModel model = new LoginModel();

    private short record = 0; // 登录失败次数

    private boolean result = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LoginModel.LOGIN_TRUE:
                    Log.d("login", "Login true");
                    result = true;
                    break;
                case LoginModel.LOGIN_FLASE:
                    Log.d("login", "Login false");
                    result = false;
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 自动登录检查
     */
    public int checkLogin() {
        int result = 0; // 自动登录结果标记（0：不存在该账号   1：存在账号但密码错误   2：账号密码正确）
        int auto_code = model.getAutoCode(user.AUTO_CODE);
        if (auto_code == 1) {
            user.setUser_name(model.getAutoUser(user.AUTO_USER));
            user.setPassword(model.getUserPSW(user.getUser_name(), user.getPASSWORD()));
//            LoginJudge(user.getUser_name(), user.getPassword());
            Log.d("auto", "checkLogin: "+ user.getUser_name()+ user.getPassword());
            if (LoginJudge(user.getUser_name(), user.getPassword()) == true) {
                Log.d("auto", "checkLogin: ???");
                result = 2;
            } else {
                model.removeAuto(user.getUser_name(), user.AUTO_CODE);
                Log.d("auto", "checkLogin: !!!");
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

//    public class postTask extends AsyncTask<String, Integer, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            if (model.post(strings[0], strings[1])) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            if (aBoolean == true) {
//                model.setAuto(user.AUTO_USER, user.getUser_name(), user.AUTO_CODE);
//                record = -1;
//            } else {
//                record += 1;
//            }
//        }
//    }

    /**
     * 联网登录验证
     *
     * @param username
     * @param password
     * @return
     */
    private boolean LoginJudge(String username, String password) {
        model.setHandler(handler);
        model.post(username, password);
        if (result == true) {
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
    public String AutoFill(String username) {
        user.setUser_name(username);
        String password = model.getUserPSW(user.getUser_name(), user.getPASSWORD());
        Log.i("password", password + "");
//        Map<String, String> map = model.getUserInfo(user.getUser_name());
        if (password != null) {
//            user.setPassword(model.getUserPSW(user.getUser_name(),user.getPASSWORD()));
            if (!LoginJudge(user.getUser_name(), password)) {
                password = null;
                return password;
            }else{
                return password;
            }
        } else {
            password = null;
            return password;
        }
    }
}
