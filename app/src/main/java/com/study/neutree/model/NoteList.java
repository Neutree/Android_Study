package com.study.neutree.model;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by Neutree on 2015-10-28.
 */
public class NoteList {
    private String title,userName;
    private String loveNum;
    private String lastEditTime;
    private Drawable ItemImage;

    public NoteList(Drawable itemImage,String title, String userName, String loveNum,  String lastEditTime) {
        this.title = title;
        this.userName = userName;
        this.loveNum = loveNum;
        this.ItemImage = itemImage;
        this.lastEditTime = lastEditTime;
    }

    public Drawable getItemImage() {
        return ItemImage;
    }

    public void setItemImage(Drawable itemImage) {
        ItemImage = itemImage;
    }

    public NoteList( String title, String userName, String loveNum, String lastEditTime) {
        this.userName = userName;
        this.title = title;
        this.loveNum = loveNum;
        this.lastEditTime = lastEditTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLoveNum(String loveNum) {
        this.loveNum = loveNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getLoveNum() {
        return loveNum;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }
}
