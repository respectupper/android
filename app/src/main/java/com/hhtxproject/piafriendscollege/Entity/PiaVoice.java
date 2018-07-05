package com.hhtxproject.piafriendscollege.Entity;

import com.hhtxproject.piafriendscollege.Entity.PiaScript;
import com.hhtxproject.piafriendscollege.Entity.PiaUser;

/**
 * Created by Respectupper on 2018/4/25.
 */

public class PiaVoice {
    private int id;
    private String imageAvatar;
    private PiaUser piaUser;
    private String voiceAvatar;
    private String playerClass;
    private int playerNum;
    private String voiceTag;
    private PiaUser playerOne;
    private PiaUser playerTwo;
    private PiaUser playerThree;
    private PiaUser playerFour;
    private int playerCount;
    private String playerIntroduce;
    private PiaScript piaScript;
    private String updatedAt;
    private String createAt;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PiaUser getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(PiaUser playerOne) {
        this.playerOne = playerOne;
    }

    public PiaUser getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(PiaUser playerTwo) {
        this.playerTwo = playerTwo;
    }

    public PiaUser getPlayerThree() {
        return playerThree;
    }

    public void setPlayerThree(PiaUser playerThree) {
        this.playerThree = playerThree;
    }

    public PiaUser getPlayerFour() {
        return playerFour;
    }

    public void setPlayerFour(PiaUser playerFour) {
        this.playerFour = playerFour;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public PiaUser getPiaUser() {
        return piaUser;
    }

    public void setPiaUser(PiaUser piaUser) {
        this.piaUser = piaUser;
    }

    public String getVoiceAvatar() {
        return voiceAvatar;
    }

    public void setVoiceAvatar(String voiceAvatar) {
        this.voiceAvatar = voiceAvatar;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getVoiceTag() {
        return voiceTag;
    }

    public void setVoiceTag(String voiceTag) {
        this.voiceTag = voiceTag;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getPlayerIntroduce() {
        return playerIntroduce;
    }

    public void setPlayerIntroduce(String playerIntroduce) {
        this.playerIntroduce = playerIntroduce;
    }

    public PiaScript getPiaScript() {
        return piaScript;
    }

    public void setPiaScript(PiaScript piaScript) {
        this.piaScript = piaScript;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
