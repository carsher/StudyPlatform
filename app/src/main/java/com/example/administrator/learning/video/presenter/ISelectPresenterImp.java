package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.QlayEnter;
import com.example.administrator.learning.video.common.bean.onRebulidList;
import com.example.administrator.learning.video.common.bean.reList;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IsectionFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public class ISelectPresenterImp implements ISectionPresenter{
    private IsectionFragment view;
    private IVideoManger manger;

    public ISelectPresenterImp(IVideoManger manger, IsectionFragment view){
        this.manger = manger;
        this.view = view;
        Log.e("加载成功", "ISelectPresenterImp: ");
    }

    @Override
    public void register() {
        RxDao.getInstance().register(this);
    }

    @Override
    public void unregister() {
        RxDao.getInstance().unregister(this);

    }

    @Override
    public void onDestroy() {
        view=null;
    }

   // relister();


    @Override
    public void repuestVideo(QlayEnter enter) {
        manger.requestVideo(enter);
    }

    @Rx_Register
    public void onsendData(videoEnter entity){
        List<sectionBean> list = entity.getList();
        relister(entity.getJsonArrlength(),list);
        if(entity.getState() == CommonRespones.STATE_SUC_CODE){
            view.showData(list,entity.getJsonArrlength(),listList);
            view.showDataer(entity.getList());
        }else if(entity.getState() == CommonRespones.STATE_EEROER_CODE){
            view.ShowToast("网络请求失败，请重试");
        }
    }

   private List<HashMap> listList = new ArrayList<>();

    public void relister(int jsonLength,List<sectionBean> list){
        Log.e("RELISTER", "relister: "+jsonLength+"hhh"+"ffffffff");
        try{
            List<HashMap> relisters= new ArrayList<>();
            List<HashMap> relisters2 =  new ArrayList<>();

            for (int i=0;i<jsonLength;i++){
                HashMap map = new HashMap();
                sectionBean bea = list.get(i);
                map.put(reList.section_title,bea.getKnobble());
                map.put(reList.belong_course,bea.getCourseID());
                map.put(reList.learning,bea.getLearnNumber());
                map.put(reList.section_sequence,bea.getSequence());

                for (int j=0;j<Integer.parseInt(bea.getSmallsection());j++){
                    List<HashMap> listmap = bea.getList();
                    HashMap maper = listmap.get(j);
                    map.put(reList.id,maper.get("id"));
                    map.put(reList.viedo_length ,maper.get("viedo_length"));
                    map.put(reList.videopath,maper.get("videopath"));
                    map.put(reList.title,maper.get("title"));
                    map.put(reList.testid,maper.get("testid"));
                    map.put(reList.sid,maper.get("sid"));
                    map.put(reList.isread,maper.get("isread"));
                    relisters2.add(map);
                    listList.add(map);
                }
                relisters.add(map);
            }
   Log.e("fqqqqqqqqqqqqq",listList.size()+"ffffffff");
        }catch(Exception e){
            Log.e("errrr",e.getMessage()+"aaaaaaaaa");
        }
    }





}
