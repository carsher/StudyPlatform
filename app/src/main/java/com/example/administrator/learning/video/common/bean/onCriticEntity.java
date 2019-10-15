package com.example.administrator.learning.video.common.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public class onCriticEntity {
    private int State;

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public List<HashMap> getList() {
        return list;
    }

    public void setList(List<HashMap> list) {
        this.list = list;
    }

    private List<HashMap> list;
}
