package com.mycompany.zqh.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mycompany.zqh.AboutActivity;
import com.mycompany.zqh.R;
import com.mycompany.zqh.WordViewModel;
import com.mycompany.zqh.tableActivity;

public class NotificationsFragment extends Fragment {
    Switch aSwitch;
    Toast toast;
    private WordViewModel wordViewModel;
    Toolbar toolbar;
    ConstraintLayout constraintLayout1,constraintLayout2,constraintLayout3;
    private NotificationsViewModel notificationsViewModel;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        final ConstraintLayout constraintLayout = root.findViewById(R.id.qingkong);
        final ConstraintLayout constraintLayout2=root.findViewById(R.id.biaoqian);
        final ConstraintLayout constraintLayout3=root.findViewById(R.id.guanyu);
        final ConstraintLayout constraintLayout4=root.findViewById(R.id.zhuti);
        final Switch aSwitch =root.findViewById(R.id.switch1);
        //sharedPreferences =getActivity().getSharedPreferences("model", 0);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("清空所有数据");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wordViewModel.deleteAllWords();
                            Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                    builder.show();
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(getActivity(), AboutActivity.class));
                startActivity(intent);
            }
        });
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(new Intent(getActivity(), Zhuti.class));
                //startActivity(intent);
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_notifications_to_zhutiFragment);
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(new Intent(getActivity(), GongjuActivity.class));
                //startActivity(intent);
                Toast toast=Toast.makeText(getActivity(),"开发中……",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(new Intent(getActivity(),tableActivity.class));
                startActivity(intent);
            }
        });
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }else{
            aSwitch.setChecked(false);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences sharedPreferences =getActivity().getSharedPreferences("model",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.remove("model");
                    editor.putBoolean("model",true);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    SharedPreferences sharedPreferences =getActivity().getSharedPreferences("model",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.remove("model");
                    editor.putBoolean("model",false);
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        return root;
    }

}