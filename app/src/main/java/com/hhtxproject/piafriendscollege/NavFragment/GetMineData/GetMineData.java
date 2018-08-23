package com.hhtxproject.piafriendscollege.NavFragment.GetMineData;

import android.util.Log;

import com.hhtxproject.piafriendscollege.Entity.PiaUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;

/**
 * Created by Respectupper on 2018/6/5.
 */

public class GetMineData {


    private PiaUser user;
    public GetMineData (String msg){
        try {
            JSONObject json = new JSONObject(msg);
            user = new PiaUser();
            user.setUsername(URLDecoder.decode(json.optString("piaUsername"), "utf-8"));
            user.setText(URLDecoder.decode(json.optString("piaText"), "utf-8"));
            user.setFuncount(json.optInt("piaFuns"));
            user.setFollowcount(json.optInt("piaFollows"));
            user.setCoin(json.optInt("piaCoin"));
            user.setAvatar(json.optString("piaAvatar"));
            setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PiaUser getUser() {
        return user;
    }

    public void setUser(PiaUser user) {
        this.user = user;
    }
}
