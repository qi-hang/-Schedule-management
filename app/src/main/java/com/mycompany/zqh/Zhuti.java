package com.mycompany.zqh;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Zhuti extends AppCompatActivity {
    Log log;
    int themeType;
    ActionBar actionBar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themeType = getSharedPreferences("theme", MODE_PRIVATE).getInt("themeType", 0);
        switch (themeType){
            case 0:setTheme(R.style.AppTheme);
                break;
            case 1:setTheme(R.style.fen);
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("主题颜色");
        ConstraintLayout constraintLayout1,constraintLayout2;
        constraintLayout1=findViewById(R.id.lan);
        constraintLayout2=findViewById(R.id.fen);
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeType=0;
                getSharedPreferences("theme", MODE_PRIVATE).edit().putInt("themeType", themeType).commit();
                recreate();
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeType=1;
                getSharedPreferences("theme", MODE_PRIVATE).edit().putInt("themeType", themeType).commit();
                recreate();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}