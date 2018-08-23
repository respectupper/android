package com.hhtxproject.piafriendscollege.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

/**
 * Created by Respectupper on 2018/4/23.
 */

public class PApplication extends Application {
    private JSONObject jsonData = null;
    private int userId;
    private boolean isLogin = false;
    private SharedPreferences mSharedPreferences;

    public void setmSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SharedPreferences getmSharedPreferences() {
        return mSharedPreferences;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    public void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    public JSONObject getGlobalVariable() {
        return jsonData;
    }

    public void setGlobalVariable(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

}
