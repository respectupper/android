package com.hhtxproject.piafriendscollege.Entity;

/**
 * Created by Respectupper on 2018/4/25.
 */

public class PiaScript {

    private Integer id;
    private Integer userId;
    private String simpleData;
    private String peopleData;
    private String contentData;
    private char released;
    private char toexamined;
    private Integer browse;
    private String updateAt;
    private String createAt;

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(String simpleData) {
        this.simpleData = simpleData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPeopleData() {
        return peopleData;
    }

    public void setPeopleData(String peopleData) {
        this.peopleData = peopleData;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public char getReleased() {
        return released;
    }

    public void setReleased(char released) {
        this.released = released;
    }

    public char getToexamined() {
        return toexamined;
    }

    public void setToexamined(char toexamined) {
        this.toexamined = toexamined;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
