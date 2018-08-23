package com.hhtxproject.piafriendscollege.NavFragment.Login.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * Created by Respectupper on 2018/4/24.
 */

public class CheckLogin {

    private SharedPreferences mSharedPreferences;

    public CheckLogin(SharedPreferences mSharedPreferences){
        this.mSharedPreferences = mSharedPreferences;
    }
    public boolean cheak(){
        if (!"NULL".equals(mSharedPreferences.getString("Telephone","NULL"))&&!"NULL".equals(mSharedPreferences.getString("Password","NULL"))){
            Log.i("shareInfo1",mSharedPreferences.getString("Telephone","NULL")+","+mSharedPreferences.getString("Password","NULL"));
            return true;
        }
        return false;
    }
}
