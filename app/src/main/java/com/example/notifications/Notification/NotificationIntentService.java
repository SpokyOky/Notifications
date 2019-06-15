package com.example.notifications.Notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.notifications.MainActivity;

import java.util.concurrent.TimeUnit;

public class NotificationIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 213;

    private final String TAG = "NotifIntentServiceLog";
    public NotificationIntentService(){
        super("NotificationIntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
       /* int time = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("task");
        Log.d(TAG, "onHandleIntent start: " + label);
        try{
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d(TAG, "onHandleIntent end: " + label);*/

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("My Title");
        builder.setContentText("This is the Body");
     //   builder.setSmallIcon(R.drawable.whatever);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
