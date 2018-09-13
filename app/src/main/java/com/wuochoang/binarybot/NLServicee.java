package com.wuochoang.binarybot;

/**
 * Created by HoangNQ on 31,August,2018
 */
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.common.Constant;

public class NLServicee extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private long previousPostTime;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("NotificationListener", "Created");

    }

    @Override
    public IBinder onBind(Intent mIntent) {
        IBinder mIBinder = super.onBind(mIntent);
        Log.d("NotificationListener", "Bindd");
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent mIntent) {
        boolean mOnUnbind = super.onUnbind(mIntent);
        Log.d("NotificationListener", "Unbind");
        try {
        } catch (Exception e) {
            Log.e(TAG, "Error during unbind", e);
        }
        return mOnUnbind;
    }



    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.d("NotificationListener","ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName()
                + "\t" + sbn.getTag() + "/t" + sbn.getPostTime());
        if (sbn.getPackageName().equals("net.metaquotes.metatrader4")) {
            if (sbn.getPostTime() < previousPostTime + 100) {
                Log.d("NotificationListener", "Called");
                Intent i = new Intent(Constant.INTENT);
                i.putExtras(sbn.getNotification().extras);
//                i.putExtra("Posttime", sbn.getPostTime());
                sendBroadcast(i);
            }
            previousPostTime = sbn.getPostTime();
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }


    @Override
    public void onListenerConnected() {
        Log.d("NotificationListener", "Service connected");
    }

}


