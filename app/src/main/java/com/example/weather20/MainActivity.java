package com.example.weather20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private ImageButton StartBtn = null;
    private ImageButton StopBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartBtn = (ImageButton) findViewById(R.id.StartBtn);
        StopBtn = (ImageButton) findViewById(R.id.StopBtn);

        //定义StartBtn为音乐开始按键
        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });

        //定义StopBtn为音乐停止按键
        StopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);
            }
        });

    }


    //背景音乐
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);

        }
    }

    //开始播放音乐
    public void start(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    //停止音乐
    public void stop(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        stopService(intent);
    }
}
