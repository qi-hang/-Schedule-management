package com.mycompany.zqh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class MyAdapter extends ListAdapter<Word,MyAdapter.MyViewHolder> {
    boolean longclick;
    //private static final String TAG = "808";
    Integer mMonth, mDay;
    String he;
    int i;
    Calendar calendar;
    private Context context;
    WordViewModel wordViewModel;
    private Boolean choose = true;//长按状态是判断当前item是否被选中
     public MyAdapter(boolean longclick,WordViewModel wordViewModel,int i,Context context) {
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getId() == newItem.getId();
            }
            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return (oldItem.getDday() == (newItem.getDday()) && oldItem.getNeirong().equals(newItem.getNeirong()) && oldItem.getMminute() == (newItem.getMminute()) && oldItem.getDmonth() == (newItem.getDmonth()) && oldItem.getHhour() == (newItem.getHhour()));
            }
        });
        this.longclick = longclick;
        this.wordViewModel = wordViewModel;
        this.i=i;
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Word word = getItem(position);
        if(i!=-1){
            if(word.getId()==-1)holder.imageView.setVisibility(View.VISIBLE);
            holder.nr.setText(String.valueOf(word.getNeirong()));
            Calendar calendar = Calendar.getInstance();
            mMonth = calendar.get(Calendar.MONTH) + 1;        //获取日期的月
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            holder.time.setText(String.valueOf(word.getHhour()) + ":" + String.valueOf(word.getMminute()));
            holder.date.setText(String.valueOf(word.getDmonth()) + "-" + String.valueOf(word.getDday()));
            if (mMonth == word.getDmonth() && mDay == word.getDday()) {
                holder.date.setVisibility(View.GONE);
                if (word.getHhour() == -1) {
                    holder.time.setText("今天");
                    holder.time.setVisibility(View.VISIBLE);
                } else {
                    if (word.getMminute() / 10 == 0) {
                        holder.time.setText(word.getHhour() + ":0" + word.getMminute());
                    }
                    holder.time.setVisibility(View.VISIBLE);
                }
            } else {
                holder.time.setVisibility(View.GONE);
                if (word.getDday() == -1) {
                    holder.date.setVisibility(View.GONE);
                } else {
                    holder.date.setVisibility(View.VISIBLE);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     holder.imageView.setVisibility(View.VISIBLE);
                }
            });
        }
        holder.nr.setText(String.valueOf(word.getNeirong()));
        Calendar calendar = Calendar.getInstance();
        mMonth = calendar.get(Calendar.MONTH) + 1;        //获取日期的月
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        holder.time.setText(String.valueOf(word.getHhour()) + ":" + String.valueOf(word.getMminute()));
        holder.date.setText(String.valueOf(word.getDmonth()) + "-" + String.valueOf(word.getDday()));
        if(word.getTtag()==2){
            holder.constraintLayout.setBackgroundResource(R.color.xuexi);
        }
        if(word.getTtag()==3){
            holder.constraintLayout.setBackgroundResource(R.color.licaijia);
        }
        if(word.getTtag()==4){
            holder.constraintLayout.setBackgroundResource(R.color.shenghuo);
        }
        if(word.getTtag()==5){
            holder.constraintLayout.setBackgroundResource(R.color.licaijian);
        }
        if (mMonth == word.getDmonth() && mDay == word.getDday()) {
            holder.date.setVisibility(View.GONE);
            if (word.getHhour() == -1) {
                holder.time.setText("今天");
                holder.time.setVisibility(View.VISIBLE);
            } else {
                if (word.getMminute() / 10 == 0) {
                    holder.time.setText(word.getHhour() + ":0" + word.getMminute());
                }
                holder.time.setVisibility(View.VISIBLE);
            }
        } else {
            holder.time.setVisibility(View.GONE);
            if (word.getDday() == -1) {
                holder.date.setVisibility(View.GONE);
            } else {
                holder.date.setVisibility(View.VISIBLE);
            }
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Bianji.class);
                    intent.putExtra("nn", word.getNeirong());
                    intent.putExtra("1", word.getDmonth());
                    intent.putExtra("2", word.getDday());
                    intent.putExtra("3", word.getHhour());
                    intent.putExtra("4", word.getMminute());
                    intent.putExtra("5", word.getId());
                    intent.putExtra("6", word.getTtag());
                    intent.putExtra("7", word.getMoney());
                    intent.putExtra("8", word.isIfplus());
                    v.getContext().startActivity(intent);
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
                            word.setId(word.getId());
                            wordViewModel.deleteWords(word);
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
        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.w(TAG, "warning" + 1);
                word.setId(word.getId());
                //wordViewModel.deleteWords(word);
                return true;
            }
        };*/
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView nr, time, date;
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nr = itemView.findViewById(R.id.nrview);
            time = itemView.findViewById(R.id.timeview);
            date = itemView.findViewById(R.id.dateview);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout =itemView.findViewById(R.id.back);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
