package com.hhtxproject.piafriendscollege.Entity.event;

import java.util.List;

/**
 * Created by Respectupper on 2018/8/3.
 */

public class ContentEvent {
    private String content;
    private int pointer;
    private List<ContentEvent> list;

    public List<ContentEvent> getList() {
        return list;
    }

    public void setList(List<ContentEvent> list) {
        this.list = list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
