package com.example.administrator.learning.common.severs;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.learning.home.view.IDowmloadFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/20 0020.
 */

public class DownLoadSevers extends Service{

    private String currenter;
    private String totaler;
    private Boolean isRund = true;
    private String title;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new myBinder();
    }



    public class myBinder extends Binder{
        public String sendTest(){
            return "测试数据";
        }
        public DownLoadSevers sendData(){
            return DownLoadSevers.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        //启动下载任务
        new DownLoadTask(url);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        while (isRund){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mySend!=null){
                mySend.onDataChange(totaler,currenter);
            }
            //发送广播
            Intent intent=new Intent();
            intent.putExtra("totaler", totaler);
            intent.putExtra("currenter",currenter);
            intent.putExtra("title",title);
            intent.setAction("com.ljq.activity.CountService");
            sendBroadcast(intent);
        }


        super.onCreate();
    }

    private class DownLoadTask{
        String PathURL;
        public DownLoadTask(String pathURL){
            this.PathURL = pathURL;
        }
        /**
         * 下载视频
         */
        private void downloadVideo() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+getPackageName()+File.separator;
                // mDownloadUrl为JSON从服务器端解析出来的下载地址
                RequestParams requestParams = new RequestParams(PathURL);
                // 为RequestParams设置文件下载后的保存路径
                requestParams.setSaveFilePath(path);
                // 下载完成后自动为文件命名
                requestParams.setAutoRename(true);
                x.http().get(requestParams, new Callback.ProgressCallback<File>() {

                    @Override
                    public void onSuccess(File result) {
                        Log.i("onSuccess", "下载成功");
                        isRund=false;
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i("onError", "下载失败");
                        isRund=false;
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        isRund=false;
                        Log.i("onCancelled", "取消下载");
                    }

                    @Override
                    public void onFinished() {
                        Log.i("onFinished", "结束下载");
                        isRund=false;
                    }

                    @Override
                    public void onWaiting() {
                        // 网络请求开始的时候调用
                        Log.i("onWaiting", "等待下载");
                    }

                    @Override
                    public void onStarted() {
                        // 下载的时候不断回调的方法
                        Log.i("onStarted", "开始下载");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        // 当前的下载进度和文件总大小
                        Log.i("onLoading", "正在下载中......");
                        totaler = total+"";
                        currenter = current+"";
                        if (isDownloading = true){
                            isRund=false;
                            onDestroy();
                            return;
                        }
                    }
                });
            }
        }
    }
    private mySend mySend=null;

    public DownLoadSevers.mySend getMySend() {
        return mySend;
    }

    public void setMySend(DownLoadSevers.mySend mySend) {
        this.mySend = mySend;
    }

    public interface mySend{
        void onDataChange(String  total,String current);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DownloadService", "onDestroy......");

    }

}
