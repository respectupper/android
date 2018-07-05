package com.hhtxproject.piafriendscollege.Entity.event;

/**
 * Created by Respectupper on 2018/6/16.
 */

public class SelectEvent {
    private int tag;
    private String [] text;

    public SelectEvent(int tag,String [] text){
        this.tag = tag;
        this.text = text;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }
}
