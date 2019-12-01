package com.william.jifanghelpdesk.model;

import okhttp3.FormBody;

public class RegisterModel {
    FormBody.Builder body = new FormBody.Builder();

    public void post(String email, String first_name, String last_name, String password){
        body.add("email", email);
        body.add("first_name", first_name);
        body.add("last_name", last_name);
        body.add("password", password);
    }
}
