package com.hhtxproject.piafriendscollege.Entity.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Respectupper on 2018/6/13.
 */

public class PeopleDataEvent {
    private String name;
    private Integer sex;
    private Integer BG;

    public List<PeopleDataEvent> getSortList() {
        return sortList;
    }

    public void setSortList(List<PeopleDataEvent> sortList) {
        this.sortList = sortList;
    }

    private List<PeopleDataEvent> sortList;
    private List<PeopleDataEvent> list;
    public List<PeopleDataEvent> getList() {
        return list;
    }

    public void setList(List<PeopleDataEvent> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getBG() {
        return BG;
    }

    public void setBG(Integer BG) {
        this.BG = BG;
    }

}
