package com.example.notifications.Notification;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver{
    private Class<? extends Activity> notificationActivity;
    private Class<? extends IntentService> notificationIntentService;
    private long endMillis = -1;
    private int REQUEST_CODE = 0;

    public NotificationReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        notificationActivity =
                (Class<? extends Activity>)extras.getSerializable("NOTIFICATION_ACTIVITY");
        notificationIntentService =
                (Class<? extends IntentService>)extras.getSerializable("NOTIFICATION_INTENT_SERVICE");
        endMillis = extras.getLong("END_MILLIS");
        REQUEST_CODE = extras.getInt("REQUEST_CODE");

        if ((endMillis == -1) || (endMillis >= System.currentTimeMillis())) {
            Log.d("NotificationReceiver", notificationActivity.getName());
            Log.d("NotificationReceiver", notificationIntentService.getName());

            Intent notificationIntent = new Intent(context, notificationIntentService);
            notificationIntent.putExtra("NOTIFICATION_ACTIVITY", notificationActivity);
            context.startService(notificationIntent);
        } else{
            NotificationModule notificationModule = new NotificationModule(context, REQUEST_CODE);//to stop
            Log.d("NotificationReceiver", "Stop");
        }
    }
}