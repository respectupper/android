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
    private String telephone = "";
    private boolean isLogin = false;
    private SharedPreferences mSharedPreferences;

    public void setmSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public JSONObject getGlobalVariable() {
        return jsonData;
    }

    public void setGlobalVariable(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

}
