package com.hhtxproject.piafriendscollege.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Respectupper on 2018/8/3.
 */

public class ContentData implements Serializable {
    private String content;
    private int pointer;
    private List<ContentData> list;

    public List<ContentData> getList() {
        return list;
    }

    public void setList(List<ContentData> list) {
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
