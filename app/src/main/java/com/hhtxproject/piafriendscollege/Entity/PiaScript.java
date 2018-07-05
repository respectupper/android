package com.hhtxproject.piafriendscollege.Entity;

/**
 * Created by Respectupper on 2018/4/25.
 */

public class PiaScript {
    private int id;
    private PiaUser piaUser;
    private String scriptName;
    private String scriptClass;
    private String scriptAvatar;
    private String scriptImgAvatar;
    private int scriptBrowse;
    private String scriptIntroduce;
    private int scriptPeopleCount;
    private String scriptRole;
    private String updateAt;
    private String createAt;
    private int type;
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public PiaUser getPiaUser() {
        return piaUser;
    }

    public void setPiaUser(PiaUser piaUser) {
        this.piaUser = piaUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptClass() {
        return scriptClass;
    }

    public void setScriptClass(String scriptClass) {
        this.scriptClass = scriptClass;
    }

    public String getScriptAvatar() {
        return scriptAvatar;
    }

    public void setScriptAvatar(String scriptAvatar) {
        this.scriptAvatar = scriptAvatar;
    }

    public String getScriptImgAvatar() {
        return scriptImgAvatar;
    }

    public void setScriptImgAvatar(String scriptImgAvatar) {
        this.scriptImgAvatar = scriptImgAvatar;
    }

    public int getScriptBrowse() {
        return scriptBrowse;
    }

    public void setScriptBrowse(int scriptBrowse) {
        this.scriptBrowse = scriptBrowse;
    }

    public String getScriptIntroduce() {
        return scriptIntroduce;
    }

    public void setScriptIntroduce(String scriptIntroduce) {
        this.scriptIntroduce = scriptIntroduce;
    }

    public int getScriptPeopleCount() {
        return scriptPeopleCount;
    }

    public void setScriptPeopleCount(int scriptPeopleCount) {
        this.scriptPeopleCount = scriptPeopleCount;
    }

    public String getScriptRole() {
        return scriptRole;
    }

    public void setScriptRole(String scriptRole) {
        this.scriptRole = scriptRole;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
