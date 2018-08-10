package com.hhtxproject.piafriendscollege.Entity;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Respectupper on 2018/6/6.
 */

public class SimpleData implements Serializable {

    private int scriptId;
    private String name;
    private PiaUser userId;
    private String type;
    private int number;
    private String title;
    private String introduce;
    private String imagePath;
    private String imageUri;
    private String imageAvatar;
    private int scriptBrowse;

    public int getScriptBrowse() {
        return scriptBrowse;
    }

    public void setScriptBrowse(int scriptBrowse) {
        this.scriptBrowse = scriptBrowse;
    }

    public int getScriptId() {
        return scriptId;
    }

    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    public PiaUser getUserId() {
        return userId;
    }

    public void setUserId(PiaUser userId) {
        this.userId = userId;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public SimpleData(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
