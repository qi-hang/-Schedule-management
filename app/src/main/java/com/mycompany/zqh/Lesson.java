package com.mycompany.zqh;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int shuxun;
    @ColumnInfo(name = "jindu")
    private float jindu;
    @ColumnInfo(name = "time")
    private int time;
    @ColumnInfo(name = "ifdaka")
    private boolean ifdaka;
    @ColumnInfo(name = "sheng")
    private int sheng;
    @ColumnInfo(name = "beizhu")
    private String beizhu;
    @ColumnInfo(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShuxun() {
        return shuxun;
    }

    public void setShuxun(int shuxun) {
        this.shuxun = shuxun;
    }

    public float getJindu() {
        return jindu;
    }

    public void setJindu(float jindu) {
        this.jindu = jindu;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isIfdaka() {
        return ifdaka;
    }

    public void setIfdaka(boolean ifdaka) {
        this.ifdaka = ifdaka;
    }

    public int getSheng() {
        return sheng;
    }

    public void setSheng(int sheng) {
        this.sheng = sheng;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Lesson(float jindu, int time, boolean ifdaka, int sheng, String beizhu,String name) {
        this.jindu = jindu;
        this.time = time;
        this.ifdaka = ifdaka;
        this.sheng = sheng;
        this.beizhu = beizhu;
        this.name =name;
    }
}
