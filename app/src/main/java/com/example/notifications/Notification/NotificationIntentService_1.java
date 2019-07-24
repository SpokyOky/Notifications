package com.example.notifications.Notification;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.notifications.R;

public class NotificationIntentService_1 extends IntentService {
    private static int NOTIFICATION_ID = 0;
    private final String TAG = "NotifyIntentService1";

    public NotificationIntentService_1(){
        super("NotificationIntentService_1");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Class<? extends Activity> notificationActivity =
                (Class<? extends Activity>)extras.getSerializable("NOTIFICATION_ACTIVITY");

        Log.d(TAG, notificationActivity.getName());

        Intent notificationIntent = new Intent(this, notificationActivity);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long when = System.currentTimeMillis();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Title_1")
                .setContentText("Text_1")
                //  .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background))
                .setAutoCancel(true)
                .setTicker("Ticker_1")
                .setWhen(when)
                .setShowWhen(true);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID++, builder.build());
    }
}