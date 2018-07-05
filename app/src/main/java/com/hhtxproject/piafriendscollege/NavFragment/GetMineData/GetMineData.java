package com.hhtxproject.piafriendscollege.NavFragment.GetMineData;

import com.hhtxproject.piafriendscollege.Entity.PiaUser;

import org.json.JSONObject;

import java.net.URLDecoder;

/**
 * Created by Respectupper on 2018/6/5.
 */

public class GetMineData {


    private PiaUser user;
    public GetMineData (String msg, String tele){
        try {
            JSONObject json = new JSONObject(msg);
            JSONObject object = json.getJSONObject(tele);
            user = new PiaUser();
            user.setUsername(URLDecoder.decode(object.optString("pia_username"), "utf-8"));
            user.setText(URLDecoder.decode(object.optString("pia_text"), "utf-8"));
            user.setFuncount(object.optInt("pia_funcount"));
            user.setFollowcount(object.optInt("pia_followcount"));
            user.setCoin(object.optInt("pia_coin"));
            user.setAvatar(object.optString("pia_avatar"));
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
