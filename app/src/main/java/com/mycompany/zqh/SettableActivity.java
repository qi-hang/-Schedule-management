package com.mycompany.zqh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettableActivity extends AppCompatActivity {
    Switch aSwitch;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences2 =getSharedPreferences("table", Context.MODE_PRIVATE);
        Boolean mmodel2 = sharedPreferences2.getBoolean("table",false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("设置");
        setContentView(R.layout.activity_settable);
        aSwitch=findViewById(R.id.switch2);
        if(mmodel2==true){
            aSwitch.setChecked(true);
        }else{
            aSwitch.setChecked(false);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences sharedPreferences =getSharedPreferences("table", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.remove("table");
                    editor.putBoolean("table",true);
                    editor.apply();
                }else{
                    SharedPreferences sharedPreferences =getSharedPreferences("table",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.remove("table");
                    editor.putBoolean("table",false);
                    editor.apply();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
