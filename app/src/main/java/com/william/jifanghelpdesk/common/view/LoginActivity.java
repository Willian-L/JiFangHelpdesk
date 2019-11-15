package com.william.jifanghelpdesk.common.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.william.jifanghelpdesk.R;
import com.william.jifanghelpdesk.Utils.LoginPost;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtAccount, edtPassword;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        edtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!TextUtils.isEmpty(edtPassword.getText().toString()) && count > 0) {
//                    btnLogin.setEnabled(true);
//                    btnLogin.setBackgroundResource(R.drawable.btn_login_true);
//                } else {
//                    btnLogin.setEnabled(false);
//                    btnLogin.setBackgroundResource(R.drawable.btn_login_false);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!TextUtils.isEmpty(edtAccount.getText().toString()) && count > 0) {
//                    btnLogin.setEnabled(true);
//                    btnLogin.setBackgroundResource(R.drawable.btn_login_true);
//                } else {
//                    btnLogin.setEnabled(false);
//                    btnLogin.setBackgroundResource(R.drawable.btn_login_false);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        username = edtAccount.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        LoginPost.loginPost(username,password);
    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login_ensure);
        edtAccount = findViewById(R.id.edt_login_account);
        edtPassword = findViewById(R.id.edt_login_password);
    }

}
