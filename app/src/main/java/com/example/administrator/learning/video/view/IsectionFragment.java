package com.example.administrator.learning.video.view;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.video.common.bean.onRebulidList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/23 0023.
 */

public interface IsectionFragment {
     void showData(List<sectionBean> list, int jsonlength, List<HashMap> listlist);
     void showDataer(List<sectionBean> list);
     void ShowToast(String say);
}
