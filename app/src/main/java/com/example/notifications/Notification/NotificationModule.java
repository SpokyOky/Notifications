package com.example.notifications.Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationModule {
    public long intervalMillis;
    public boolean repeating;

    public NotificationModule(boolean repeating, long intervalMillis){
        this.repeating = repeating;
        this.intervalMillis = intervalMillis;
    }

    public NotificationModule(boolean repeating, double intervalSeconds){
        this.repeating = repeating;
        this.intervalMillis = (long) Math.floor(intervalSeconds * 1000);
    }

    public void startNotifyRepeating(Context context){
        Intent notifyIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (repeating) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                    intervalMillis, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + intervalMillis, pendingIntent);
        }
    }

    public void stopNotifyRepeating(Context context){
        Intent notifyIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }


}
