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
import com.william.jifanghelpdesk.bean.RetrieveInfo;

public class RetrieveActivity extends AppCompatActivity {

    EditText edt_email;
    Button btn_sendEmail, btn_clean, btn_return;
    TextView txt_email, txt_true, txt_false;

    RetrieveInfo info = new RetrieveInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        init();
    }

    private void init(){
        edt_email = findViewById(R.id.edt_retrieve_email);
        btn_sendEmail = findViewById(R.id.btn_retrieve_sendEmail);
        btn_clean = findViewById(R.id.btn_retrieve_clean);
        btn_return = findViewById(R.id.btn_retrieve_return);
        txt_email = findViewById(R.id.txt_retrieve_email);
        txt_true = findViewById(R.id.txt_retrieve_true);
        txt_false = findViewById(R.id.txt_retrieve_false);

        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                info.setEmail(edt_email.getText().toString().trim());
                if (TextUtils.isEmpty(info.getEmail())) {
                    edt_email.setBackgroundResource(R.drawable.bg_edt_null);
                    txt_email.setVisibility(View.VISIBLE);
                    btn_sendEmail.setBackgroundResource(R.drawable.btn_false);
                } else {
                    edt_email.setBackgroundResource(R.drawable.bg_edt_normal);
                    txt_email.setVisibility(View.GONE);
                    btn_sendEmail.setBackgroundResource(R.drawable.btn_true);
                }
                if (count > -1){
                    btn_clean.setVisibility(View.GONE);
                    btn_sendEmail.setVisibility(View.VISIBLE);
                    txt_false.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void sendEmail(View view) {
        if (TextUtils.isEmpty(info.getEmail())){
            edt_email.setBackgroundResource(R.drawable.bg_edt_null);
            txt_email.setVisibility(View.VISIBLE);
        }else {
            txt_false.setVisibility(View.VISIBLE);
            btn_sendEmail.setVisibility(View.GONE);
            btn_clean.setVisibility(View.VISIBLE);
        }
    }

    public void clean(View view) {
        edt_email.setText(null);
        btn_clean.setVisibility(View.GONE);
        btn_sendEmail.setVisibility(View.VISIBLE);
    }

    public void returnLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
