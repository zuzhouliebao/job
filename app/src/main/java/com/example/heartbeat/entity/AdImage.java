package com.example.heartbeat.entity;

import java.io.Serializable;

public class AdImage implements Serializable {
    private int id;
    private String img;
    private String desc;

    public AdImage() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
