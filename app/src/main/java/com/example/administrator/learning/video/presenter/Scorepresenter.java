package com.example.administrator.learning.video.presenter;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.Scorerequset;
import com.example.administrator.learning.video.common.bean.onscore;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IScoreview;

/**
 * Created by 88888888 on 2018/11/18.
 */

public class Scorepresenter implements IScorePresenter{
    private IVideoManger manger;
    private IScoreview view;

    public Scorepresenter(IVideoManger iscoreImp,IScoreview view) {
        this.manger=iscoreImp;
        this.view = view;
    }

    @Override
    public void regist(onscore score) {
        manger.regist(score);

    }
    //写到用RDO返回数据到了这里
    @Rx_Register
    public void register(Scorerequset scorerequset){
        if(scorerequset.getScod()==200){
            view.say("成绩保存成功");
        }
        if(scorerequset.getScod()==400){
            view.say("成绩保存成功");
        }
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
        view = null;
    }
}
