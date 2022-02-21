package com.mycompany.zqh;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "dmonth")
    private int dmonth;
    @ColumnInfo(name = "dday")
    private int dday;
    @ColumnInfo(name = "hhour")
    private int hhour;
    @ColumnInfo(name = "mminute")
    private int mminute;
    @ColumnInfo(name = "neirong")
    private String neirong;
    @ColumnInfo(name ="if_today")
    private boolean iftoday;
    @ColumnInfo(name = "tag")
    private  int ttag;
    @ColumnInfo(name = "ifplus")
    private boolean ifplus;
    @ColumnInfo(name = "money")
    private double money;

    public boolean isIfplus() {
        return ifplus;
    }

    public double getMoney() {
        return money;
    }

    public int getTtag() {
        return ttag;
    }

    public boolean isIftoday() {
        return iftoday;
    }

    public void setIftoday(boolean iftoday) {
        this.iftoday = iftoday;
    }

    public Word(int dmonth, int dday, int hhour, int mminute, String neirong,int ttag,boolean ifplus,double money) {
        this.dmonth = dmonth;
        this.dday = dday;
        this.hhour = hhour;
        this.mminute = mminute;
        this.neirong = neirong;
        this.ttag = ttag;
        this.ifplus =ifplus;
        this.money =money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDmonth() {
        return dmonth;
    }

    public int getDday() {
        return dday;
    }

    public int getHhour() {
        return hhour;
    }

    public int getMminute() {
        return mminute;
    }

    public String getNeirong() {
        return neirong;
    }

}
