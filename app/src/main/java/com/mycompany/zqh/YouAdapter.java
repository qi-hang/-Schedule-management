package com.mycompany.zqh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class YouAdapter extends ListAdapter<Lesson,YouAdapter.MyViewHolder> {
    Integer mMonth, mDay;
    Calendar calendar;
    private Context context;
    WordViewModel wordViewModel;
    private Boolean choose = true;//长按状态是判断当前item是否被选中
    public YouAdapter(WordViewModel wordViewModel,Context context) {
        super(new DiffUtil.ItemCallback<Lesson>() {
            @Override
            public boolean areItemsTheSame(@NonNull Lesson oldItem, @NonNull Lesson newItem) {
                return oldItem.getShuxun() == newItem.getShuxun();
            }  
            @Override
            public boolean areContentsTheSame(@NonNull Lesson oldItem, @NonNull Lesson newItem) {
                return (oldItem.getJindu() == (newItem.getJindu()) && oldItem.getBeizhu().equals(newItem.getBeizhu()) && oldItem.getSheng() == (newItem.getSheng())&&oldItem.getName().equals(newItem.getName()) );
        }
        });
        this.wordViewModel = wordViewModel;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cardmm, parent, false);
        return new MyViewHolder(itemView);
    }
                                              
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
            final Lesson lesson = getItem(position);
            holder.shunxu.setText(String.valueOf(lesson.getShuxun()));
            Calendar calendar = Calendar.getInstance();
            mMonth = calendar.get(Calendar.MONTH) + 1;        //获取日期的月
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            holder.biaoti.setText(String.valueOf(lesson.getName()));
            holder.shijian.setText(String.valueOf(lesson.getTime()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //holder.imageView.setVisibility(View.VISIBLE);
                }
            });
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*Intent intent = new Intent(v.getContext(), Bianji.class);
                intent.putExtra("nn", word.getNeirong());
                intent.putExtra("1", word.getDmonth());
                intent.putExtra("2", word.getDday());
                intent.putExtra("3", word.getHhour());
                intent.putExtra("4", word.getMminute());
                intent.putExtra("5", word.getId());
                intent.putExtra("6", word.getTtag());
                intent.putExtra("7", word.getMoney());
                intent.putExtra("8", word.isIfplus());
                v.getContext().startActivity(intent);*/
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //holder.imageView.setVisibility(View.VISIBLE);
                //wordViewModel.itt(word.getId());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("删除记录");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //word.setId(word.getId());
                        //wordViewModel.deleteWords(word);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ProgressBar progressBar;
        TextView shunxu,biaoti,shijian,shengyu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox2);
            progressBar = itemView.findViewById(R.id.progressBar2);
            shunxu = itemView.findViewById(R.id.shuzi);//
            biaoti = itemView.findViewById(R.id.mingcheng);//
            shijian =itemView.findViewById(R.id.shijianjian);
            shengyu =itemView.findViewById(R.id.shengyu);
        }
    }
}
