package com.hhtxproject.piafriendscollege.NavFragment.TabView.Voice.Recommend;

import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.PiaUser;
import com.hhtxproject.piafriendscollege.Entity.PiaVoice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Respectupper on 2018/5/28.
 */

public class GetRecommendData {
    private List<PiaVoice> listData;

    public GetRecommendData(String msg){
        try {
//            JSONObject json = new JSONObject(msg);
//            JSONArray array = json.getJSONArray("voice_List_info");
//            List<PiaVoice> data = new ArrayList<>();
//            for (int i = 0;i<array.length();i++){
//                JSONObject jsScript = new JSONObject(array.getJSONObject(i).optString("player_script"));
//                JSONObject jsonScript = jsScript.getJSONObject("scriptInfo");
//
//                PiaVoice voice = new PiaVoice();
//                PiaScript script = new PiaScript();
//
//                PiaUser pu = new PiaUser();
//                JSONObject jsUserScript = new JSONObject(jsonScript.optString("pia_user"));
//                JSONObject jsonUserScript = jsUserScript.getJSONObject("userInfo");
//
//                pu.setAvatar(jsonUserScript.optString("pia_avatar"));
//                pu.setUsername(URLDecoder.decode(jsonUserScript.optString("pia_username"), "utf-8"));
//                script.setPiaUser(pu);
//                script.setScriptName(URLDecoder.decode(jsonScript.optString("pia_script_name"), "utf-8"));
//                script.setScriptClass(URLDecoder.decode(jsonScript.optString("pia_script_class"), "utf-8"));
//                script.setScriptAvatar(jsonScript.optString("pia_script_avatar"));
//                voice.setId(array.getJSONObject(i).optInt("player_id"));
//                voice.setPiaScript(script);
//                voice.setImageAvatar(URLDecoder.decode(array.getJSONObject(i).optString("player_image_url"), "utf-8"));
//                voice.setPlayerClass(URLDecoder.decode(array.getJSONObject(i).optString("player_class"), "utf-8"));
//                voice.setPlayerCount(array.getJSONObject(i).optInt("player_count"));
//                voice.setPlayerIntroduce(URLDecoder.decode(array.getJSONObject(i).optString("player_introduce"), "utf-8"));
//                voice.setPlayerNum(array.getJSONObject(i).optInt("player_play_num"));
//                voice.setVoiceTag(URLDecoder.decode(array.getJSONObject(i).optString("player_tag"), "utf-8"));
//
//                switch (voice.getPlayerCount()){
//                    case 1:
//                        JSONObject jsUserOne1 = new JSONObject(array.getJSONObject(i).optString("player_partner_one"));
//                        JSONObject jsonUserOne1 = jsUserOne1.getJSONObject("userInfo");
//                        PiaUser userOne1 = new PiaUser();
//                        userOne1.setUsername(URLDecoder.decode(jsonUserOne1.optString("pia_username"), "utf-8"));
//                        userOne1.setAvatar(jsonUserOne1.optString("pia_avatar"));
//                        voice.setPlayerOne(userOne1);
//                        break;
//                    case 2:
//                        JSONObject jsUserOne2 = new JSONObject(array.getJSONObject(i).optString("player_partner_one"));
//                        JSONObject jsonUserOne2 = jsUserOne2.getJSONObject("userInfo");
//                        PiaUser userOne2 = new PiaUser();
//                        userOne2.setUsername(URLDecoder.decode(jsonUserOne2.optString("pia_username"), "utf-8"));
//                        userOne2.setAvatar(jsonUserOne2.optString("pia_avatar"));
//                        voice.setPlayerOne(userOne2);
//
//                        JSONObject jsUserTwo2 = new JSONObject(array.getJSONObject(i).optString("player_partner_two"));
//                        JSONObject jsonUserTwo2 = jsUserTwo2.getJSONObject("userInfo");
//                        PiaUser userTwo2 = new PiaUser();
//                        userTwo2.setUsername(URLDecoder.decode(jsonUserTwo2.optString("pia_username"), "utf-8"));
//                        userTwo2.setAvatar(jsonUserTwo2.optString("pia_avatar"));
//                        voice.setPlayerTwo(userTwo2);
//                        break;
//                    case 3:
//                        JSONObject jsUserOne3 = new JSONObject(array.getJSONObject(i).optString("player_partner_one"));
//                        JSONObject jsonUserOne3 = jsUserOne3.getJSONObject("userInfo");
//                        PiaUser userOne3 = new PiaUser();
//                        userOne3.setUsername(URLDecoder.decode(jsonUserOne3.optString("pia_username"), "utf-8"));
//                        userOne3.setAvatar(jsonUserOne3.optString("pia_avatar"));
//                        voice.setPlayerOne(userOne3);
//
//                        JSONObject jsUserTwo3 = new JSONObject(array.getJSONObject(i).optString("player_partner_two"));
//                        JSONObject jsonUserTwo3 = jsUserTwo3.getJSONObject("userInfo");
//                        PiaUser userTwo3 = new PiaUser();
//                        userTwo3.setUsername(URLDecoder.decode(jsonUserTwo3.optString("pia_username"), "utf-8"));
//                        userTwo3.setAvatar(jsonUserTwo3.optString("pia_avatar"));
//                        voice.setPlayerTwo(userTwo3);
//
//                        JSONObject jsUserThree3 = new JSONObject(array.getJSONObject(i).optString("player_partner_three"));
//                        JSONObject jsonUserThree3 = jsUserThree3.getJSONObject("userInfo");
//                        PiaUser userThree3 = new PiaUser();
//                        userThree3.setUsername(URLDecoder.decode(jsonUserThree3.optString("pia_username"), "utf-8"));
//                        userThree3.setAvatar(jsonUserThree3.optString("pia_avatar"));
//                        voice.setPlayerThree(userThree3);
//                        break;
//                    case 4:
//                        JSONObject jsUserOne4 = new JSONObject(array.getJSONObject(i).optString("player_partner_one"));
//                        JSONObject jsonUserOne4 = jsUserOne4.getJSONObject("userInfo");
//                        PiaUser userOne4 = new PiaUser();
//                        userOne4.setUsername(URLDecoder.decode(jsonUserOne4.optString("pia_username"), "utf-8"));
//                        userOne4.setAvatar(jsonUserOne4.optString("pia_avatar"));
//                        voice.setPlayerOne(userOne4);
//
//                        JSONObject jsUserTwo4 = new JSONObject(array.getJSONObject(i).optString("player_partner_two"));
//                        JSONObject jsonUserTwo4 = jsUserTwo4.getJSONObject("userInfo");
//                        PiaUser userTwo4 = new PiaUser();
//                        userTwo4.setUsername(URLDecoder.decode(jsonUserTwo4.optString("pia_username"), "utf-8"));
//                        userTwo4.setAvatar(jsonUserTwo4.optString("pia_avatar"));
//                        voice.setPlayerTwo(userTwo4);
//
//                        JSONObject jsUserThree4 = new JSONObject(array.getJSONObject(i).optString("player_partner_three"));
//                        JSONObject jsonUserThree4 = jsUserThree4.getJSONObject("userInfo");
//                        PiaUser userThree4 = new PiaUser();
//                        userThree4.setUsername(URLDecoder.decode(jsonUserThree4.optString("pia_username"), "utf-8"));
//                        userThree4.setAvatar(jsonUserThree4.optString("pia_avatar"));
//                        voice.setPlayerThree(userThree4);
//
//                        JSONObject jsUserFour4 = new JSONObject(array.getJSONObject(i).optString("player_partner_four"));
//                        JSONObject jsonUserFour4 = jsUserFour4.getJSONObject("userInfo");
//                        PiaUser userFour4 = new PiaUser();
//                        userFour4.setUsername(URLDecoder.decode(jsonUserFour4.optString("pia_username"), "utf-8"));
//                        userFour4.setAvatar(jsonUserFour4.optString("pia_avatar"));
//                        voice.setPlayerFour(userFour4);
//                        break;
//                }
//                voice.setType(array.getJSONObject(i).optInt("type"));
//                voice.setUpdatedAt(array.getJSONObject(i).optString("updatedAt"));
//                voice.setCreateAt(array.getJSONObject(i).optString("createdAt"));
//                data.add(voice);
//            }
//            setListData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<PiaVoice> getListData() {
        return listData;
    }

    public void setListData(List<PiaVoice> listData) {
        this.listData = listData;
    }
}
