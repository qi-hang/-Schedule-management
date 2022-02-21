package com.mycompany.zqh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class StartActivity extends AppCompatActivity {
    Boolean mmodel2;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences =getSharedPreferences("model", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 =getSharedPreferences("table", Context.MODE_PRIVATE);
        Boolean mmodel = sharedPreferences.getBoolean("model",false);
        mmodel2 = sharedPreferences2.getBoolean("table",false);
        if(mmodel){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        // 注意：此处将setContentView()方法注释掉
        // setContentView(R.layout.activity_start);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLogin();
            }
        }, 150);
    }
    /*前往主页*/
    private void gotoLogin() {
        Intent intent = new Intent(StartActivity.this,MainActivity.class);
        Intent intent2 = new Intent(StartActivity.this,tableActivity.class);
        if(mmodel2){
            startActivity(intent2);
        }else{
            startActivity(intent);
        }
        finish();
        //取消界面跳转时的动画，使启动页的logo图片与注册、登录主页的logo图片完美衔接
        overridePendingTransition(0, 0);
    }
    @Override
    protected void onDestroy() {
        if (handler != null) {
            //If token is null, all callbacks and messages will be removed.
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}
