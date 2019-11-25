package com.william.jifanghelpdesk.common.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.william.jifanghelpdesk.R;
import com.william.jifanghelpdesk.Utils.LoginPost;
import com.william.jifanghelpdesk.Utils.SharedPreferencesUtils;
import com.william.jifanghelpdesk.bean.User;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtAccount, edtPassword;

    private int record = 0;

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        checkLogin();

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
                user.setUser_name(edtAccount.getText().toString().trim());
                System.out.println(user.getUser_name());
                Map<String, String> map = SharedPreferencesUtils.getInstance().getMap(user.getUser_name());
                if (map.size() > 0) {
                    user.setPassword(map.get(user.getPASSWORD()));
                    edtPassword.setText(user.getPassword());
                } else {
                    edtPassword.setText(null);
                }
                System.out.println(map.get(user.getPASSWORD()));
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
                user.setPassword(edtPassword.getText().toString().trim());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void checkLogin() {
        int auto_code = SharedPreferencesUtils.getInstance().getInt(SharedPreferencesUtils.AUTO_CODE, 0);
        if (auto_code == 1){
            user.setUser_name(SharedPreferencesUtils.getInstance().getString(SharedPreferencesUtils.AUTO_USER, ""));
            edtAccount.setText(user.getUser_name());
            Map<String, String> map = SharedPreferencesUtils.getInstance().getMap(user.getUser_name());
            user.setPassword(map.get(user.getPASSWORD()));
            System.out.println(user.getUser_name());
            System.out.println(user.getPassword());
            if (LoginJudge()){
                Intent intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
            }else {
                SharedPreferencesUtils.getInstance().remove(user.getUser_name());
                SharedPreferencesUtils.getInstance().putInt(SharedPreferencesUtils.AUTO_CODE, 0);
                Toast.makeText(getApplicationContext(), "密码已失效，请重新输入！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Login() {
        if (LoginJudge() == true) {
            SharedPreferencesUtils.getInstance().putString(SharedPreferencesUtils.AUTO_USER, user.getUser_name());
            SharedPreferencesUtils.getInstance().putInt(SharedPreferencesUtils.AUTO_CODE, 1);
            Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        } else {
            record += 1;
            if (record < 3) {
                Toast.makeText(getApplicationContext(), "账号或密码输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
            } else {
                showRegisterDialog();
            }
        }
    }

    private boolean LoginJudge() {
        boolean result = false;
        LoginPost login = new LoginPost();
        result = login.loginPost(user.getUser_name(), user.getPassword());
        Log.i("login",""+result);
        Log.i("login","user"+user.getUser_name());
        Log.i("login","pass"+user.getPassword());
        return result;
    }

    /**
     * Dialog
     */
    private void showRegisterDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("您已经连续 " + record + " 次输入错误");
        alertDialog.setMessage("是否需要重置密码?");
        alertDialog.setPositiveButton("是",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toRegister();
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

    private void toRegister() {
    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login_ensure);
        edtAccount = findViewById(R.id.edt_login_account);
        edtPassword = findViewById(R.id.edt_login_password);
    }

}
