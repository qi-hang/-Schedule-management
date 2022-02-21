package com.mycompany.zqh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class tableActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    TextView textView11,textView21,textView31,textView41,textView51,textView61,textView71;
    //星期几
    private RelativeLayout day;
    Calendar calendar;
    Log log;
    //SQLite Helper类
    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "database.db", null, 1);

    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("课程表");
        setContentView(R.layout.activity_table);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayth=calendar.get(Calendar.DAY_OF_WEEK);
        int month=calendar.get(Calendar.MONTH);
        //log.v("pi", String.valueOf(dayth));
        textView1=findViewById(R.id.monday1);
        textView2=findViewById(R.id.monday2);
        textView3=findViewById(R.id.monday3);
        textView4=findViewById(R.id.monday4);
        textView5=findViewById(R.id.monday5);
        textView6=findViewById(R.id.monday6);
        textView7=findViewById(R.id.monday7);
        textView11=findViewById(R.id.month11);
        textView21=findViewById(R.id.month12);
        textView31=findViewById(R.id.month13);
        textView41=findViewById(R.id.month14);
        textView51=findViewById(R.id.month15);
        textView61=findViewById(R.id.month16);
        textView71=findViewById(R.id.month17);
        switch (dayth){
            case 2:textView1.setText(String.valueOf(day));
                textView2.setText(String.valueOf(day+1));
                textView3.setText(String.valueOf(day+2));
                textView4.setText(String.valueOf(day+3));
                textView5.setText(String.valueOf(day+4));
                textView6.setText(String.valueOf(day+5));
                textView7.setText(String.valueOf(day+6));
                textView1.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView11.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 3:textView1.setText(String.valueOf(day-1));
                textView2.setText(String.valueOf(day));
                textView3.setText(String.valueOf(day+1));
                textView4.setText(String.valueOf(day+2));
                textView5.setText(String.valueOf(day+3));
                textView6.setText(String.valueOf(day+4));
                textView7.setText(String.valueOf(day+5));
                textView2.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView21.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 4:textView1.setText(String.valueOf(day-2));
                textView2.setText(String.valueOf(day-1));
                textView3.setText(String.valueOf(day));
                textView4.setText(String.valueOf(day+1));
                textView5.setText(String.valueOf(day+2));
                textView6.setText(String.valueOf(day+3));
                textView7.setText(String.valueOf(day+4));
                textView3.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView31.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 5:textView1.setText(String.valueOf(day-3));
                textView2.setText(String.valueOf(day-2));
                textView3.setText(String.valueOf(day-1));
                textView4.setText(String.valueOf(day));
                textView5.setText(String.valueOf(day+1));
                textView6.setText(String.valueOf(day+2));
                textView7.setText(String.valueOf(day+3));
                textView4.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView41.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 6:textView1.setText(String.valueOf(day-4));
                textView2.setText(String.valueOf(day-3));
                textView3.setText(String.valueOf(day-2));
                textView4.setText(String.valueOf(day-1));
                textView5.setText(String.valueOf(day));
                textView6.setText(String.valueOf(day+1));
                textView7.setText(String.valueOf(day+2));
                textView5.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView51.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 7:textView1.setText(String.valueOf(day-5));
                textView2.setText(String.valueOf(day-4));
                textView3.setText(String.valueOf(day-3));
                textView4.setText(String.valueOf(day-2));
                textView5.setText(String.valueOf(day-1));
                textView6.setText(String.valueOf(day));
                textView7.setText(String.valueOf(day+1));
                textView6.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView61.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
            case 8:textView1.setText(String.valueOf(day-6));
                textView2.setText(String.valueOf(day-5));
                textView3.setText(String.valueOf(day-4));
                textView4.setText(String.valueOf(day-3));
                textView5.setText(String.valueOf(day-2));
                textView6.setText(String.valueOf(day-1));
                textView7.setText(String.valueOf(day));
                textView7.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                textView71.setBackgroundColor(Color.parseColor("#FFE5CFFF"));
                break;
        }
//获取系统时间
        //工具条
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //从数据库读取数据
        loadData();
    }

    //从数据库加载数据
    private void loadData() {
        ArrayList<Course> coursesList = new ArrayList<>(); //课程列表
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from courses", null);
        if (cursor.moveToFirst()) {
            do {
                coursesList.add(new Course(
                        cursor.getString(cursor.getColumnIndex("course_name")),
                        cursor.getString(cursor.getColumnIndex("teacher")),
                        cursor.getString(cursor.getColumnIndex("class_room")),
                        cursor.getInt(cursor.getColumnIndex("day")),
                        cursor.getInt(cursor.getColumnIndex("class_start")),
                        cursor.getInt(cursor.getColumnIndex("class_end"))));
            } while(cursor.moveToNext());
        }
        cursor.close();

        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course : coursesList) {
            createLeftView(course);
            createItemCourseView(course);
        }
    }

    //保存数据到数据库
    private void saveData(Course course) {
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL
                ("insert into courses(course_name, teacher, class_room, day, class_start, class_end) " + "values(?, ?, ?, ?, ?, ?)",
                        new String[] {course.getCourseName(),
                                course.getTeacher(),
                                course.getClassRoom(),
                                course.getDay()+"",
                                course.getStart()+"",
                                course.getEnd()+""}
                );
    }

    //创建"第几节数"视图
    private void createLeftView(Course course) {
        int endNumber = course.getEnd();
        if (endNumber > maxCoursesNumber) {
            for (int i = 0; i < endNumber-maxCoursesNumber; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.left_view, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110,180);
                view.setLayoutParams(params);

                TextView text = view.findViewById(R.id.class_number_text);
                text.setText(String.valueOf(++currentCoursesNumber));

                LinearLayout leftViewLayout = findViewById(R.id.left_view_layout);
                leftViewLayout.addView(view);
            }
            maxCoursesNumber = endNumber;
        }
    }

    //创建单个课程视图
    private void createItemCourseView(final Course course) {
        int getDay = course.getDay();
        if ((getDay < 1 || getDay > 7) || course.getStart() > course.getEnd())
            Toast.makeText(this, "星期几没写对,或课程结束时间比开始时间还早~~", Toast.LENGTH_LONG);
        else {
            int dayId = 0;
            switch (getDay) {
                case 1: dayId = R.id.monday; break;
                case 2: dayId = R.id.tuesday; break;
                case 3: dayId = R.id.wednesday; break;
                case 4: dayId = R.id.thursday; break;
                case 5: dayId = R.id.friday; break;
                case 6: dayId = R.id.saturday; break;
                case 7: dayId = R.id.weekday; break;
            }
            day = findViewById(dayId);

            int height = 180;
            final View v = LayoutInflater.from(this).inflate(R.layout.course_card, null); //加载单个课程布局
            v.setY(height * (course.getStart()-1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT,(course.getEnd()-course.getStart()+1)*height - 8); //设置布局高度,即跨多少节课
            v.setLayoutParams(params);
            TextView text = v.findViewById(R.id.text_view);
            text.setText(course.getCourseName() + "\n" + course.getTeacher() + "\n" + course.getClassRoom()); //显示课程名
            day.addView(v);
            //长按删除课程
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setVisibility(View.GONE);//先隐藏
                    day.removeView(v);//再移除课程视图
                    SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
                    sqLiteDatabase.execSQL("delete from courses where course_name = ?", new String[] {course.getCourseName()});
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_courses:
                Intent intent = new Intent(tableActivity.this, AddCourseActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.sett:
                Intent intent1 = new Intent(this, SettableActivity.class);
                startActivity(intent1);
                break;
            case R.id.home:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }
        if(item.getItemId()==android.R.id.home){
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表左边视图(节数)
            createLeftView(course);
            //创建课程表视图
            createItemCourseView(course);
            //存储数据到数据库
            saveData(course);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}