package com.william.jifanghelpdesk.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.william.jifanghelpdesk.R;
import com.william.jifanghelpdesk.bean.RegisterInfo;
import com.william.jifanghelpdesk.controller.RegisterController;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_ensure, btn_return, btn_clean;
    private EditText edt_first, edt_last, edt_email, edt_psw, edt_pswEnsure;
    private TextView txt_first, txt_last, txt_email, txt_psw, txt_pswEnsure_null, txt_pswEnsure_diff, txt_true, txt_false;

    private RegisterInfo info = new RegisterInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        edt_first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check();
                if (TextUtils.isEmpty(info.getFirstName())) {
                    edt_first.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_first.setVisibility(View.VISIBLE);
                } else {
                    edt_first.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_first.setVisibility(View.INVISIBLE);
                }
                if (count > -1) {
                    btn_clean.setVisibility(View.GONE);
                    btn_ensure.setVisibility(View.VISIBLE);
                    txt_true.setVisibility(View.GONE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_last.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check();
                if (TextUtils.isEmpty(info.getLastName())) {
                    edt_last.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_last.setVisibility(View.VISIBLE);
                } else {
                    edt_last.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_last.setVisibility(View.INVISIBLE);
                }
                if (count > -1) {
                    btn_clean.setVisibility(View.GONE);
                    btn_ensure.setVisibility(View.VISIBLE);
                    txt_true.setVisibility(View.GONE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check();
                if (TextUtils.isEmpty(info.getEmail())) {
                    edt_email.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_email.setVisibility(View.VISIBLE);
                } else {
                    edt_email.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_email.setVisibility(View.INVISIBLE);
                }
                if (count > -1) {
                    btn_clean.setVisibility(View.GONE);
                    btn_ensure.setVisibility(View.VISIBLE);
                    txt_true.setVisibility(View.GONE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check();
                if (TextUtils.isEmpty(info.getPassword())) {
                    edt_psw.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_psw.setVisibility(View.VISIBLE);
                } else {
                    edt_psw.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_psw.setVisibility(View.INVISIBLE);
                }
                if (count > -1) {
                    btn_clean.setVisibility(View.GONE);
                    btn_ensure.setVisibility(View.VISIBLE);
                    txt_true.setVisibility(View.GONE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_pswEnsure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check();
                if (TextUtils.isEmpty(info.getPswEnsure())) {
                    edt_pswEnsure.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_pswEnsure_null.setVisibility(View.VISIBLE);
                } else {
                    edt_pswEnsure.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_pswEnsure_null.setVisibility(View.INVISIBLE);
                    txt_pswEnsure_diff.setVisibility(View.GONE);
                }
                if (count > -1) {
                    btn_clean.setVisibility(View.GONE);
                    btn_ensure.setVisibility(View.VISIBLE);
                    txt_true.setVisibility(View.GONE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void init() {
        btn_ensure = findViewById(R.id.btn_register_ensure);
        btn_return = findViewById(R.id.btn_register_return);
        btn_clean = findViewById(R.id.btn_register_clean);
        edt_first = findViewById(R.id.edt_register_firstName);
        edt_last = findViewById(R.id.edt_register_lastName);
        edt_email = findViewById(R.id.edt_register_email);
        edt_psw = findViewById(R.id.edt_register_password);
        edt_pswEnsure = findViewById(R.id.edt_register_pswEnsure);
        txt_first = findViewById(R.id.txt_register_firstName);
        txt_last = findViewById(R.id.txt_register_lastName);
        txt_email = findViewById(R.id.txt_register_email);
        txt_psw = findViewById(R.id.txt_register_password);
        txt_pswEnsure_null = findViewById(R.id.txt_register_pswEnsure_null);
        txt_pswEnsure_diff = findViewById(R.id.txt_register_pswEnsure_diff);
        txt_true = findViewById(R.id.txt_register_true);
        txt_false = findViewById(R.id.txt_register_false);
    }

    private boolean check() {
        info.setFirstName(edt_first.getText().toString().trim());
        info.setLastName(edt_last.getText().toString().trim());
        info.setEmail(edt_email.getText().toString().trim());
        info.setPassword(edt_psw.getText().toString().trim());
        info.setPswEnsure(edt_pswEnsure.getText().toString().trim());
        if (TextUtils.isEmpty(info.getFirstName())
                || TextUtils.isEmpty(info.getLastName())
                || TextUtils.isEmpty(info.getEmail())
                || TextUtils.isEmpty(info.getPassword())
                || TextUtils.isEmpty(info.getPswEnsure())) {
            btn_ensure.setBackgroundResource(R.drawable.btn_false);
            return false;
        } else {
            btn_ensure.setBackgroundResource(R.drawable.btn_true);
            return true;
        }
    }

    public void ensure(View view) {
        if (TextUtils.isEmpty(info.getFirstName())) {
            edt_first.setBackgroundResource(R.drawable.bg_edt_null);
            txt_first.setVisibility(View.VISIBLE);
        } else {
            edt_first.setBackgroundResource(R.drawable.bg_edt_normal);
            txt_first.setVisibility(View.INVISIBLE);
        }
        if (TextUtils.isEmpty(info.getLastName())) {
            edt_last.setBackgroundResource(R.drawable.bg_edt_null);
            txt_last.setVisibility(View.VISIBLE);
        } else {
            edt_last.setBackgroundResource(R.drawable.bg_edt_normal);
            txt_last.setVisibility(View.INVISIBLE);
        }
        if (TextUtils.isEmpty(info.getEmail())) {
            edt_email.setBackgroundResource(R.drawable.bg_edt_null);
            txt_email.setVisibility(View.VISIBLE);
        } else {
            edt_email.setBackgroundResource(R.drawable.bg_edt_normal);
            txt_email.setVisibility(View.INVISIBLE);
        }
        if (TextUtils.isEmpty(info.getPassword())) {
            edt_psw.setBackgroundResource(R.drawable.bg_edt_null);
            txt_psw.setVisibility(View.VISIBLE);
        } else {
            edt_psw.setBackgroundResource(R.drawable.bg_edt_normal);
            txt_psw.setVisibility(View.INVISIBLE);
        }
        boolean check_psw = false;
        if (TextUtils.isEmpty(info.getPswEnsure())) {
            edt_pswEnsure.setBackgroundResource(R.drawable.bg_edt_null);
            txt_pswEnsure_null.setVisibility(View.VISIBLE);
            txt_pswEnsure_diff.setVisibility(View.GONE);
        } else if (!info.getPassword().equals(info.getPswEnsure())) {
            edt_pswEnsure.setBackgroundResource(R.drawable.bg_edt_null);
            txt_pswEnsure_null.setVisibility(View.GONE);
            txt_pswEnsure_diff.setVisibility(View.VISIBLE);
            check_psw = false;
        } else {
            edt_pswEnsure.setBackgroundResource(R.drawable.bg_edt_normal);
            txt_pswEnsure_null.setVisibility(View.INVISIBLE);
            txt_pswEnsure_diff.setVisibility(View.GONE);
            check_psw = true;
        }
        if (check() && check_psw) {
            RegisterController controller = new RegisterController();
            if (controller.RegisterJudge(info.getEmail(), info.getFirstName(), info.getLastName(), info.getPassword())) {
                txt_true.setVisibility(View.VISIBLE);
            } else {
                txt_false.setVisibility(View.VISIBLE);
                txt_true.setVisibility(View.GONE);
                btn_clean.setVisibility(View.VISIBLE);
                btn_ensure.setVisibility(View.GONE);
            }
        }
    }

    public void returnLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("username", edt_email.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    public void clean(View view) {
        edt_first.setText(null);
        edt_last.setText(null);
        edt_email.setText(null);
        edt_psw.setText(null);
        edt_pswEnsure.setText(null);
        btn_clean.setVisibility(View.GONE);
        btn_ensure.setVisibility(View.VISIBLE);
        txt_false.setVisibility(View.GONE);
    }
}
