package com.example.administrator.learning.home.common;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24 0024.
 */

public class homeEntey {
    private List<HashMap> list;//computer
    private List<HashMap> listHistory;//history

    public List<HashMap> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<HashMap> listHistory) {
        this.listHistory = listHistory;
    }

    private int statu;

    public List<HashMap> getList() {
        return list;
    }

    public void setList(List<HashMap> list) {
        this.list = list;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }
}
