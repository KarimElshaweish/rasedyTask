package com.example.karim.rasedytask.Model;

import com.google.gson.annotations.SerializedName;

public class AdsData {
        @SerializedName("picture")
        private String picture;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;
        @SerializedName("order")
        private String order;

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
}
