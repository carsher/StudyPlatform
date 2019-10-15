package com.example.administrator.learning.video.common.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public class PostEntiy {
    private int state;
    private List<HashMap> list;

    public List<HashMap> getList() {
        return list;
    }

    public void setList(List<HashMap> list) {
        this.list = list;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
