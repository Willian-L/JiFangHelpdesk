package com.william.jifanghelpdesk.controller;

import android.util.Log;

import com.william.jifanghelpdesk.model.RegisterModel;

public class RegisterController {

    private RegisterModel model = new RegisterModel();

    /**
     * 联网注册验证
     */
    public boolean RegisterJudge(String email, String first_name, String last_name, String password) {
        int loginState = model.post(email, first_name, last_name, password);
        if (loginState == RegisterModel.REGISTER_TRUE) {
            Log.i("loginState", "注册成功");
            return true;
        } else if (loginState == RegisterModel.REGISTER_FLASE) {
            Log.i("loginState", "注册失败");
            return false;
        } else {
            Log.i("loginState", "联网失败");
            return false;
        }
    }
}
