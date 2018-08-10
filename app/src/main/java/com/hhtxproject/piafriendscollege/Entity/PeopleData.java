package com.hhtxproject.piafriendscollege.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Respectupper on 2018/6/13.
 */

public class PeopleData implements Serializable {
    private String name;
    private Integer sex;
    private Integer BG;
    private List<PeopleData> list;

    public List<PeopleData> getList() {
        return list;
    }

    public void setList(List<PeopleData> list) {
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
