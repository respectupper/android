package com.hhtxproject.piafriendscollege.Entity;

/**
 * Created by Respectupper on 2018/4/23.
 */

public class PiaUser {

    private int id;
    private String username;
    private String avatar;
    private String text;
    private int funcount;
    private int coin;
    private int followcount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowcount() {
        return followcount;
    }

    public void setFollowcount(int followcount) {
        this.followcount = followcount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFuncount() {
        return funcount;
    }

    public void setFuncount(int funcount) {
        this.funcount = funcount;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
