package com.example.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.notifications.Notification.NotificationIntentService;
import com.example.notifications.Notification.NotificationModule;
import com.example.notifications.Notification.NotificationReceiver;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Заголовок")
                .setContentText("Текст уведомления")
              //  .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background))
                .setTicker("Строка состояния")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void onClickStop(View view) {
    }






/*    public void work(){



        Intent notifyIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 10, pendingIntent);
    }*/
}
