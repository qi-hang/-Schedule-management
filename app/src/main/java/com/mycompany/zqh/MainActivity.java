package com.mycompany.zqh;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Log log;
    int themeType;
    String addedTextString;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;

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
        setContentView(R.layout.activity_main);
        ;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Resources resource = getResources();
        ColorStateList csl = resource.getColorStateList(R.color.selector_color);
        navView.setItemTextColor(csl);
        log.v("pia",getClipContent());

        //GangUpInvite(getBaseContext());
       /* ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (null != manager) {
            ClipData clipData = manager.getPrimaryClip();
            if(null!= clipData&&clipData.getItemCount()>0){
                //huooqu4
                ClipData.Item item =clipData.getItemAt(0);
                log.v("pia","11");
                if(null!=item){
                    String content =item.getText().toString();
                    log.v("pia",content);
                }
            }
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller=Navigation.findNavController(this,R.id.nav_host_fragment);
        return controller.navigateUp();
    }

    /**
     * 获取系统剪贴板内容
     */
    public String getClipContent() {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(addedText);
                if (!TextUtils.isEmpty(addedTextString)) {
                    return addedTextString;
                }
            }
        }
        return "";
    }
}
