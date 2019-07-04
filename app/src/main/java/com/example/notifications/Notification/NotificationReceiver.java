package com.example.notifications.Notification;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.notifications.MainActivity;

public class NotificationReceiver extends BroadcastReceiver{
    private Class<? extends Activity> notificationActivity;
    private long endMillis = -1;
    private int REQUEST_CODE = 0;

    public NotificationReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        notificationActivity = (Class<? extends Activity>)extras.getSerializable("NOTIFICATION_ACTIVITY");
        endMillis = extras.getLong("END_MILLIS");
        REQUEST_CODE = extras.getInt("REQUEST_CODE");

        Log.d("NotificationReceiver", notificationActivity.getName());
        if ((endMillis == -1) || (endMillis >= System.currentTimeMillis())) {
            Intent notifyIntent = new Intent(context, NotificationIntentService.class);
            notifyIntent.putExtra("NOTIFICATION_ACTIVITY", notificationActivity);
            context.startService(notifyIntent);
        } else{
            NotificationModule notificationModule = new NotificationModule(context, REQUEST_CODE);
        }
    }
}