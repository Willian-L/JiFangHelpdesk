package com.william.jifanghelpdesk.controller;

import android.util.Log;

import com.william.jifanghelpdesk.bean.User;
import com.william.jifanghelpdesk.model.LoginModel;

public class LoginController {

    private User user = new User();
    private LoginModel model = new LoginModel();

    private short record = 0; // 登录失败次数

    /**
     * 自动登录检查
     */
    public int checkLogin(){
        int result = 0; // 自动登录结果标记（0：不存在该账号   1：存在账号但密码错误   2：账号密码正确）
        int auto_code = model.getAutoCode(user.getAUTO_CODE());
        if (auto_code == 1) {
            user.setUser_name(model.getAutoUser(user.getAUTO_USER()));
            user.setPassword(model.getUserPSW(user.getUser_name(), user.getPASSWORD()));
//            LoginJudge(user.getUser_name(), user.getPassword());
            Log.d("auto", "checkLogin: " + user.getUser_name() + user.getPassword());
            if (LoginJudge(user.getUser_name(), user.getPassword()) == true) {
                result = 2;
            } else {
                model.removeAuto(user.getUser_name(), user.getAUTO_CODE());
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
    public short Login(String username, String password){
        user.setUser_name(username);
        user.setPassword(password);
        if (LoginJudge(user.getUser_name(), user.getPassword()) == true) {
            model.setAuto(user.getAUTO_USER(), user.getUser_name(), user.getAUTO_CODE());
            record = -1;
        } else {
            record += 1;
        }
        return record;
    }

    /**
     * 联网登录验证
     *
     * @param username
     * @param password
     * @return
     */
    private boolean LoginJudge(String username, String password){
        int loginState = model.post(username, password);
        if (loginState == LoginModel.LOGIN_TRUE) {
            Log.i("loginState", "账号密码验证成功");
            return true;
        } else if (loginState == LoginModel.LOGIN_FLASE){
            Log.i("loginState", "账号密码验证失败");
            return false;
        }else {
            Log.i("loginState", "联网失败");
            return false;
        }
    }

    /**
     * 密码自动填充
     *
     * @param username
     * @return
     */
    public String AutoFill(String username){
        user.setUser_name(username);
        String password = model.getUserPSW(user.getUser_name(), user.getPASSWORD());
        Log.i("password", password + "");
        if (password != null) {
            if (!LoginJudge(user.getUser_name(), password)) {
                password = null;
                return password;
            } else {
                return password;
            }
        }
        return null;
    }
}
