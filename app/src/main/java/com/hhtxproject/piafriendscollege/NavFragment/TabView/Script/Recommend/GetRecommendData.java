package com.hhtxproject.piafriendscollege.NavFragment.TabView.Script.Recommend;

import android.os.Message;
import android.util.Log;

import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.PiaUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Respectupper on 2018/5/28.
 */

public class GetRecommendData {
    private List<PiaScript> listData;

    public GetRecommendData(Message msg){
        try {
            JSONObject json = new JSONObject(msg.obj.toString());
            JSONArray array = json.getJSONArray("script_List_info");
            List<PiaScript> data = new ArrayList<>();
            for (int i = 0;i<array.length();i++){
                PiaScript script = new PiaScript();
                script.setId(array.getJSONObject(i).optInt("script_id"));

                JSONObject jsUser = new JSONObject(array.getJSONObject(i).optString("script_user"));
                JSONObject jsonUser = jsUser.getJSONObject("userInfo");

                PiaUser user = new PiaUser();
                user.setUsername(URLDecoder.decode(jsonUser.optString("pia_username"), "utf-8"));
                user.setAvatar(URLDecoder.decode(jsonUser.optString("pia_avatar"), "utf-8"));
                script.setPiaUser(user);

                script.setScriptName(URLDecoder.decode(array.getJSONObject(i).optString("script_name"), "utf-8"));
                script.setScriptClass(URLDecoder.decode(array.getJSONObject(i).optString("script_class"), "utf-8"));
                script.setScriptAvatar(URLDecoder.decode(array.getJSONObject(i).optString("script_avatar"), "utf-8"));
                script.setScriptImgAvatar(URLDecoder.decode(array.getJSONObject(i).optString("script_img_avatar"), "utf-8"));
                script.setScriptBrowse(array.getJSONObject(i).optInt("script_browse"));
                script.setScriptIntroduce(URLDecoder.decode(array.getJSONObject(i).optString("script_introduce"), "utf-8"));
                script.setScriptPeopleCount(array.getJSONObject(i).optInt("script_people_count"));
               script.setScriptRole(URLDecoder.decode(array.getJSONObject(i).optString("script_role"), "utf-8"));
//                JSONObject role = new JSONObject(URLDecoder.decode(array.getJSONObject(i).optString("script_role")));
                script.setUpdateAt(array.getJSONObject(i).optString("updatedAt"));
                script.setCreateAt(array.getJSONObject(i).optString("createdAt"));
                data.add(script);
            }
            setListData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<PiaScript> getListData() {
        return listData;
    }

    private void setListData(List<PiaScript> listData) {
        this.listData = listData;
    }
}
