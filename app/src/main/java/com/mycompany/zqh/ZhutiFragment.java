package com.mycompany.zqh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Context.MODE_PRIVATE;

public class ZhutiFragment extends Fragment {
    int themeType;
    ConstraintLayout constraintLayout1,constraintLayout2;
    BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root =  inflater.inflate(R.layout.fragment_zhuti, container, false);//View v=this.getView();
        bottomNavigationView=getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        constraintLayout1=root.findViewById(R.id.lan);
        constraintLayout2=root.findViewById(R.id.fen);
        themeType = getActivity().getSharedPreferences("theme", MODE_PRIVATE).getInt("themeType", 0);
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeType=0;
                getActivity().getSharedPreferences("theme", MODE_PRIVATE).edit().putInt("themeType", themeType).commit();
                getActivity().setTheme(R.style.AppTheme);
                getActivity().recreate();
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeType=1;
                getActivity().getSharedPreferences("theme", MODE_PRIVATE).edit().putInt("themeType", themeType).commit();
                getActivity().setTheme(R.style.fen);
                getActivity().recreate();
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("2233", "11 ");
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}