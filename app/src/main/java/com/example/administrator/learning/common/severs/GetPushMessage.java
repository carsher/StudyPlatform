package com.example.administrator.learning.common.severs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class GetPushMessage extends GTIntentService {
    public GetPushMessage() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String takid = msg.getTaskId();
        byte[] meg = msg.getPayload();
        String json = new String(meg);
        try {
            JSONObject jsonObject = new JSONObject(json);

            /*处理数据*/
          //  String touser = jsonObject.getString("touser");
          ///  String content = jsonObject.getString("content");
          //  String type = jsonObject.getString("type");

            Log.e("接收到的数据",json);

//                      if(type.equals("1")){
//                //教师通知的的类型
//                NotificationCompat.Builder mBuilder =
//                        new NotificationCompat.Builder(this)
//                                .setSmallIcon(R.drawable.icon)
//                                .setContentTitle("课前通知")
//                                .setContentText(content)
//                                .setDefaults(Notification.DEFAULT_ALL);
//                Intent resultIntent = new Intent(this, CoursePlanActivity.class);
//                resultIntent.putExtra("action", MainActivity.STARTCOURSE);
//                PendingIntent resultPendingIntent = PendingIntent.getActivity(
//                        this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//                mBuilder.setContentIntent(resultPendingIntent);
//                mBuilder.setAutoCancel(true);
//                Notification notification = mBuilder.build();
//                int mNotificationId = 001;
//
//                NotificationManager mNotifyMgr =
//                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                mNotifyMgr.notify(mNotificationId, notification);
//            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        /*获取到了cid*/
        SpUtils.putString(context, StaticCost.CID,clientid);
        Log.e("获取到的cid",clientid + "这货就是啊");
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {

    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
    }

}
