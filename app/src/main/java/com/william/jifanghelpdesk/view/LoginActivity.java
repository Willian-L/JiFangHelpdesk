package com.william.jifanghelpdesk.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.william.jifanghelpdesk.R;
import com.william.jifanghelpdesk.bean.User;
import com.william.jifanghelpdesk.controller.LoginController;
import com.william.jifanghelpdesk.utils.log.CrashHandlerUtil;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;

    private User user = new User();
    private LoginController controller = new LoginController();

    private short record = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CrashHandlerUtil.getInstance().init();

        init();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /**
         * 自动登录验证
         */
        switch (controller.checkLogin()) {
            case 0:
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "密码已失效，请重新输入！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
                break;
        }

        edtUsername.addTextChangedListener(new TextWatcher() {
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
                String username = edtUsername.getText().toString().trim();
                if (username != "") {
                    if (controller.AutoFill(username)) {
                        edtPassword.setText(user.getPassword());
                    } else {
                        edtPassword.setText(null);
                    }
                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!TextUtils.isEmpty(edtUsername.getText().toString()) && count > 0) {
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
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                record = controller.Login(username, password);
                if (record == -1) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
//                    startActivity(intent);
                } else if (record >= 3) {
                    showRegisterDialog(record);
                } else {
                    Toast.makeText(getApplicationContext(), "账号或密码输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 密码错误提示（错误3次以上）
     */
    private void showRegisterDialog(int record) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("您已经连续 " + record + " 次输入错误");
        alertDialog.setMessage("是否需要重置密码?");
        alertDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        toRegister();
                    }
                });
        alertDialog.setNegativeButton("否",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialog.show();
    }

    private void toRegister(){

    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login_ensure);
        edtUsername = findViewById(R.id.edt_login_username);
        edtPassword = findViewById(R.id.edt_login_password);
    }

}
