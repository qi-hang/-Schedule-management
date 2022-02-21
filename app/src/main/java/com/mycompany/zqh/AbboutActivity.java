package com.mycompany.zqh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AbboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("新建日程");
        setContentView(R.layout.activity_abbout);

    }
}