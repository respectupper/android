package com.hhtxproject.piafriendscollege.Entity.event;

import java.util.List;

/**
 * Created by Respectupper on 2018/6/13.
 */

public class PeopleDataEvent {
    private List<String> name;
    private List<String> introduce;
    private List<Integer> sex;
    private List<Integer> BH;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getIntroduce() {
        return introduce;
    }

    public void setIntroduce(List<String> introduce) {
        this.introduce = introduce;
    }

    public List<Integer> getSex() {
        return sex;
    }

    public void setSex(List<Integer> sex) {
        this.sex = sex;
    }

    public List<Integer> getBH() {
        return BH;
    }

    public void setBH(List<Integer> BH) {
        this.BH = BH;
    }
}
