package com.example.karim.rasedytask.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

    @Entity(tableName = "AdsDB",indices = @Index(value = {"picture"},unique = true))
public class AdsDB {
    @PrimaryKey(autoGenerate = true)
    public int AdsID;
    @ColumnInfo(name = "picture")
    public String picture;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "url")
    public String url;
    @ColumnInfo(name = "order")
    public String order;

    public int getAdsID() {
        return AdsID;
    }

    public void setAdsID(int adsID) {
        AdsID = adsID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public AdsDB( String picture, String title, String url, String order) {
        this.picture = picture;
        this.title = title;
        this.url = url;
        this.order = order;
    }
}
