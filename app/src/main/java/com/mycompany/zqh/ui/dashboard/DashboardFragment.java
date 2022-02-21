package com.mycompany.zqh.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mycompany.zqh.EditActivity;
import com.mycompany.zqh.MyAdapter;
import com.mycompany.zqh.R;
import com.mycompany.zqh.Word;
import com.mycompany.zqh.WordViewModel;

import java.util.Calendar;
import java.util.List;

public class DashboardFragment extends Fragment {
    int tomonth,today;
    CalendarView calendarView;
    ImageView imageView;
    WordViewModel wordViewModel;
    TextView textView;
    Toast toast;
    Log log;
    private WordViewModel wordViewMode3;
    RecyclerView recyclerView;
    int mmonth,dday;
    MyAdapter myAdapter1;
    private LiveData<List<Word>> fillteredwords;
    FloatingActionButton floatingActionButton;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        wordViewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        final FloatingActionButton floatingActionButton = root.findViewById(R.id.fab);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final ImageView imageView =root.findViewById(R.id.imageView3);
        final TextView textView = root.findViewById(R.id.textView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//
        myAdapter1 = new MyAdapter(false,wordViewModel,-1,DashboardFragment.this.getContext());
        recyclerView.setAdapter(myAdapter1);
        int temp =myAdapter1.getItemCount();
        Calendar calendar = Calendar.getInstance();
        tomonth = calendar.get(Calendar.MONTH) + 1;
        mmonth=tomonth;//获取日期的月
        today = calendar.get(Calendar.DAY_OF_MONTH);
        dday =today;
        fillteredwords = wordViewModel.riqi(tomonth,today);
        fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        myAdapter1.submitList(words);
                    }
                });

        final CalendarView calendarView = root.findViewById(R.id.cal);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                mmonth =month+1;
                dday =dayOfMonth;
                Integer qq = month+1;
                fillteredwords.removeObservers(requireActivity());
                fillteredwords =wordViewModel.riqi(qq,dayOfMonth);
                fillteredwords.observe(requireActivity(), new Observer<List<Word>>() {
                            @Override
                            public void onChanged(List<Word> words) {
                                myAdapter1.submitList(words);
                                int temp =myAdapter1.getItemCount();
                                log.v("666",String.valueOf(temp));
                                /*if(temp==0){
                                    imageView.setVisibility(View.VISIBLE);
                                    textView.setVisibility(View.VISIBLE);
                                }
                                else{
                                    imageView.setVisibility(View.INVISIBLE);
                                    textView.setVisibility(View.INVISIBLE);
                                }*/
                            }
                        });
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(new Intent(getActivity(), EditActivity.class));
                intent.putExtra("1",mmonth);
                intent.putExtra("2",dday);
                v.getContext().startActivity(intent);
            }
        });
        return root;
    }
}