package com.example.notifications.Notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

public class NotificationModule {
    private long repeatIntervalMillis;
    private boolean repeating;
    private long beginMillis;
    private long endMillis = -1;
    private long repeatAfterMillis = -1;
    private Class<? extends Activity> notificationActivity;
    private int REQUEST_CODE = 0;

    //to stop
    public NotificationModule(Context context, int REQUEST_CODE){
        this.REQUEST_CODE = REQUEST_CODE;
        stopNotify(context);
    }
    public NotificationModule(long beginMillis, long repeatIntervalMillis,
                              Class<? extends Activity> notificationActivity, int REQUEST_CODE){
        this.beginMillis = beginMillis;
        this.repeatIntervalMillis = repeatIntervalMillis;
        this.repeating = true;
        this.notificationActivity = notificationActivity;
        this.REQUEST_CODE = REQUEST_CODE;
        if (this.repeatIntervalMillis <= 0) {
            this.repeatIntervalMillis = -1;
            this.repeating = false;
        }
    }

    public NotificationModule(long beginMillis, double repeatIntervalSeconds,
                              Class<? extends Activity> notificationActivity, int REQUEST_CODE){
        this.beginMillis = beginMillis;
        this.repeatIntervalMillis = (long) Math.floor(repeatIntervalSeconds * 1000);
        this.repeating = true;
        this.notificationActivity = notificationActivity;
        this.REQUEST_CODE = REQUEST_CODE;
        if (this.repeatIntervalMillis <= 0){
            this.repeatIntervalMillis = -1;
            this.repeating = false;
        }

    }

    public NotificationModule(Date beginDate, Date repeatIntervalDate,
                              Class<? extends Activity> notificationActivity, int REQUEST_CODE){
        this.repeating = true;
        this.beginMillis = beginDate.getTime();
        this.repeatIntervalMillis = repeatIntervalDate.getTime();
        this.notificationActivity = notificationActivity;
        this.REQUEST_CODE = REQUEST_CODE;
        if (this.repeatIntervalMillis <= 0){
            this.repeatIntervalMillis = -1;
            this.repeating = false;
        }
    }

    public NotificationModule(Date beginDate, Date repeatIntervalDate, Date endDate,
                              Class<? extends Activity> notificationActivity, int REQUEST_CODE){
        this.repeating = true;
        this.repeatIntervalMillis = repeatIntervalDate.getTime();
        this.beginMillis = beginDate.getTime();
        this.endMillis = endDate.getTime();
        this.notificationActivity = notificationActivity;
        this.REQUEST_CODE = REQUEST_CODE;
        if (this.repeatIntervalMillis <= 0){
            this.repeatIntervalMillis = -1;
            this.repeating = false;
        }
    }

    public NotificationModule(Date beginDate, Date repeatIntervalDate, Date endDate, Date repeatAfterDate,
                              Class<? extends Activity> notificationActivity, int REQUEST_CODE){
        this.repeating = true;
        this.repeatIntervalMillis = repeatIntervalDate.getTime();
        this.beginMillis = beginDate.getTime();
        this.endMillis = endDate.getTime();
        this.repeatAfterMillis = repeatAfterDate.getTime();
        this.notificationActivity = notificationActivity;
        this.REQUEST_CODE = REQUEST_CODE;
        if (this.repeatIntervalMillis <= 0){
            this.repeatIntervalMillis = -1;
            this.repeating = false;
        }
    }

    public void startNotify(Context context){
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.putExtra("NOTIFICATION_ACTIVITY", notificationActivity);
        notificationIntent.putExtra("END_MILLIS", endMillis);
        notificationIntent.putExtra("REQUEST_CODE", REQUEST_CODE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (repeating) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    beginMillis, repeatIntervalMillis, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, beginMillis, pendingIntent);
        }
    }

    public void stopNotify(Context context){
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}