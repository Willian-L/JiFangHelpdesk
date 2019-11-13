package com.william.jifanghelpdesk.Utils;

import com.william.jifanghelpdesk.bean.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiUrl {
    @Headers(Constans.Content_Type)
    @POST(Constans.Login_Uri)
    Call<ResponseLogin> postUser(@Field("username") String username, @Field("password") String password);
}
