package com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Recommend;

import android.os.Message;
import android.util.Log;

import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.PiaUser;
import com.hhtxproject.piafriendscollege.Entity.SimpleData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Respectupper on 2018/5/28.
 */

public class GetRecommendData {
    private List<SimpleData> listData;

    public GetRecommendData(Message msg){
        try {
            JSONObject json = new JSONObject(msg.obj.toString());
            JSONArray array = json.getJSONArray("script_list_info");
            listData = new ArrayList<>();
            for (int i = 0;i<array.length();i++){
                SimpleData data = new SimpleData();
                data.setScriptId(array.getJSONObject(i).optInt("script_id"));

                JSONObject jsonUser = new JSONObject(URLDecoder.decode(array.getJSONObject(i).optString("script_userId"), "utf-8"));
                JSONObject JUser = jsonUser.getJSONObject("userInfo");
                PiaUser user = new PiaUser();
                user.setId(JUser.optInt("pia_userId"));
                user.setUsername(URLDecoder.decode(JUser.optString("pia_username"), "utf-8"));
                user.setAvatar(URLDecoder.decode(JUser.optString("pia_avatar"), "utf-8"));

                JSONObject jsonScript = new JSONObject(URLDecoder.decode(array.getJSONObject(i).optString("script_simpleInfo"), "utf-8"));
                JSONObject arrayScript = jsonScript.getJSONObject("simple_data");
                data.setUserId(user);
                data.setName(URLDecoder.decode(arrayScript.optString("scriptName"), "utf-8"));
                data.setTitle(URLDecoder.decode(arrayScript.optString("scriptTitle"), "utf-8"));
                data.setIntroduce(URLDecoder.decode(arrayScript.optString("scriptIntroduce"), "utf-8"));
                data.setType(URLDecoder.decode(arrayScript.optString("scriptType"), "utf-8"));
                data.setNumber(arrayScript.optInt("scriptNumber"));

                data.setImageAvatar("http://"+URLDecoder.decode(arrayScript.optString("scriptImageAvatar"), "utf-8"));
                Log.i("avatar:",i+"|"+data.getImageAvatar());
                data.setScriptBrowse(array.getJSONObject(i).optInt("script_browse"));
                listData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<SimpleData> getListData() {
        return listData;
    }

    private void setListData(List<SimpleData> listData) {
        this.listData = listData;
    }
}
