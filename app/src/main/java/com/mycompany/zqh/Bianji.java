package com.mycompany.zqh;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;
import java.util.Objects;

public class Bianji extends AppCompatActivity {
    Spinner biaoqian;
    private int themeType;
    boolean ifplus=true;
    WordViewModel wordViewModel;
    double money =0.00;
    Integer yue=-1,ri=-1,shi=-1,fen=-1,tag=1;
    String neirong;
    EditText editText,editText2;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    ImageView imageButton1,imageButton2,imageView3;
    ConstraintLayout constraintLayout1,constraintLayout2;
    TextView textView1,textView2;
    int qwq=0;
    int dd;
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
        setContentView(R.layout.edit_layout);
        Intent intent =getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText =findViewById(R.id.editText);
        biaoqian =findViewById(R.id.spinner2);
        imageButton1 = findViewById(R.id.datebutton);
        imageButton2 = findViewById(R.id.timebutton);
        constraintLayout1 =findViewById(R.id.datebb);
        constraintLayout2 =findViewById(R.id.timebb);
        editText2 =findViewById(R.id.textViewmoney);
        imageView3 =findViewById(R.id.moneybutton);

        editText.setText(intent.getStringExtra("nn"));
        getSupportActionBar().setTitle("????????????");
        dd =intent.getIntExtra("5",-1);
        tag =intent.getIntExtra("6",1);
        ifplus =intent.getBooleanExtra("8",true);
        money =intent.getDoubleExtra("7",0);
            editText2.setText(String.valueOf(money));
        if(intent.getIntExtra("1",-1)!=-1){
            textView1.setText(intent.getIntExtra("1",-1)+"-"+intent.getIntExtra("2",-1));
            yue = intent.getIntExtra("1",-1);
            ri = intent.getIntExtra("2",-1);
        }
         if(intent.getIntExtra("3",-1)!=-1){
             shi = intent.getIntExtra("3",-1);
             fen = intent.getIntExtra("4",-1);
             textView2.setText(shi+":"+fen);
             if(fen/10==0){
                 textView2.setText(shi+":0"+fen);
             }
         }
         neirong =intent.getStringExtra("nn");
         TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                neirong=editText.getText().toString().trim();
                qwq=1;
                //button.setEnabled(!neirong.isEmpty()&&yue!=0&&ri!=0);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        editText.addTextChangedListener(textWatcher);
        textView1.addTextChangedListener(textWatcher);

        String[] mItems =getResources().getStringArray(R.array.tag);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
//???????????????
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        biaoqian.setAdapter(adapter);
        if(tag!=5){
            biaoqian.setSelection(tag-1);
        }else{
            biaoqian.setSelection(2);
            ifplus=false;
            imageView3.setImageResource(R.drawable.ic_remove_black_24dp);
        }
        biaoqian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==2){
                    textView2.setVisibility(View.GONE);
                    imageButton2.setVisibility(View.GONE);
                    constraintLayout2.setVisibility(View.GONE);
                    imageView3.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);
                }
                if(position!=2){
                    tag=position+1;
                    textView2.setVisibility(View.VISIBLE);
                    imageButton2.setVisibility(View.VISIBLE);
                    constraintLayout2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdate();
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdate();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settime();
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settime();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ifplus){
                    imageView3.setImageResource(R.drawable.ic_remove_black_24dp);
                    tag=5;
                    ifplus=false;
                }else{
                    imageView3.setImageResource(R.drawable.ic_add_black_24dp);
                    tag=3;
                    ifplus =true;
                }
            }
        });
    }
    private  void setdate(){
        Calendar calendar =Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yue = month+1;
                ri = dayOfMonth;
                String time =/*String.valueOf(year)+"-"+*/String.valueOf(yue)+"-"+Integer.toString(dayOfMonth);
                textView1.setText(time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar. MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        Objects.requireNonNull(datePickerDialog.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private  void settime(){
        Calendar calendar =Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                shi =hour;
                fen =minute;
                String ttime;
                if(minute/10>=1){
                    ttime =Integer.toString(hour)+":"+Integer.toString(minute);
                }else {
                    ttime =Integer.toString(hour)+":"+"0"+Integer.toString(minute);
                }
                textView2.setText(ttime);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar. MINUTE),
                true);
        timePickerDialog.show();
        Objects.requireNonNull(timePickerDialog.getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_no:
                if(qwq==0){
                    finish();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("??????????????????");
                    builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                    builder.show();
                }
                break;
            case R.id.action_yes:
                Word wr;
                wr = new Word(yue,ri,shi,fen,neirong,tag,ifplus,money);
                wr.setId(dd);
                wordViewModel.deleteWords(wr);
                //wordRepository.deleteWords(,null,null);
                Word word;
                    if(!TextUtils.isEmpty(editText2.getText())){
                        money=Double.parseDouble(editText2.getText().toString());
                     }
                    word = new Word(yue,ri,shi,fen,neirong,tag,ifplus,money);
                    wordViewModel.insertWords(word);
                    finish();
        }
        if(item.getItemId()==android.R.id.home){
            if(qwq==0){
                finish();
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("??????????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
