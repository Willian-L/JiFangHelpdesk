package com.william.jifanghelpdesk.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.william.jifanghelpdesk.R;
import com.william.jifanghelpdesk.utils.http.IsNetwordAvailable;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView img_loge, ima_logoText;

    private TimerTask task;
    private static final long DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // 开场动画顺序
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.start_logo_in);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.start_text_in);

        img_loge.setAnimation(animation1);
        animation3.setStartOffset(700);
        ima_logoText.setAnimation(animation3);

        automatic();
    }

    /**
     * Automatic switching after a certain time
     */
    private void automatic(){
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                IsNetwordAvailable netword = new IsNetwordAvailable();
                if (netword.check(getApplicationContext())){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"当前没有网络连接",Toast.LENGTH_LONG).show();
                }
            }
        };
        timer.schedule(task, DELAY);
    }

    private void init(){
        img_loge = findViewById(R.id.img_logo);
        ima_logoText = findViewById(R.id.img_logo_text);
    }
}
