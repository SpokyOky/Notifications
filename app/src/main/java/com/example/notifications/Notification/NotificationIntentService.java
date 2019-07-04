package com.example.notifications.Notification;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.notifications.R;

public class NotificationIntentService extends IntentService {
    private static int NOTIFICATION_ID = 0;

    private final String TAG = "NotifyIntentServiceLog";
    private Class<? extends Activity> notificationActivity;

    public NotificationIntentService(){
        super("NotificationIntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        notificationActivity = (Class<? extends Activity>)extras.getSerializable("NOTIFICATION_ACTIVITY");

        Log.d("NotifyIntentService", notificationActivity.getName());

        Intent notificationIntent = new Intent(this, notificationActivity);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

      //  Resources res = this.getResources();
        long when = System.currentTimeMillis();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Title")
                .setContentText("Text")
                //  .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background))
                .setAutoCancel(true)
                .setTicker("Ticker")
                .setWhen(when)
                .setShowWhen(true);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID++, builder.build());
    }
}
