package com.example.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.notifications.Notification.NotificationActivity_1;
import com.example.notifications.Notification.NotificationActivity_2;
import com.example.notifications.Notification.NotificationIntentServiceExample;
import com.example.notifications.Notification.NotificationIntentService_1;
import com.example.notifications.Notification.NotificationModule;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    NotificationModule notificationModule;
    NotificationModule notificationModule2;
    int REQUEST_CODE = 0;

    public void onClickStart(View view) {
        Date interval = new Date(10000);
        Date begin = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis() + 40000);

        notificationModule = new NotificationModule(begin, interval, end,
                NotificationActivity_1.class, NotificationIntentServiceExample.class, REQUEST_CODE++);
        notificationModule.startNotify(this);

        end = new Date(System.currentTimeMillis() + 60000);
        notificationModule2 = new NotificationModule(begin, interval, end,
                NotificationActivity_2.class, NotificationIntentService_1.class, REQUEST_CODE++);
        notificationModule2.startNotify(this);
    }

    public void onClickStop(View view) {
        if (notificationModule != null)
            notificationModule.stopNotify(this);

        if (notificationModule2 != null)
            notificationModule2.stopNotify(this);
    }
}
