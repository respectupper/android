package com.hhtxproject.piafriendscollege.Entity.event;

/**
 * Created by Respectupper on 2018/6/6.
 */

public class SimpleDataEvent {

    private String name;
    private String aclass;
    private int number;
    private String title;
    private String introduce;

    public SimpleDataEvent(){

    }

    public SimpleDataEvent(String name, String aclass,int number,String title,String introduce){
        this.name = name;
        this.aclass = aclass;
        this.number = number;
        this.title = title;
        this.introduce = introduce;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
