package com.mycompany.zqh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AboutActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    int themeType;
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
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("关于");
        constraintLayout = findViewById(R.id.guanyu);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=2773386785"));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, "未安装QQ或当前版本不支持", Toast.LENGTH_SHORT).show();
                }
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
