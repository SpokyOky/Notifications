package com.example.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.notifications.Notification.NotificationModule;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    NotificationModule notificationModule = new NotificationModule(false, 10.0);


    public void onClickStart(View view) {
        notificationModule.startNotifyRepeating(this);

    }

    public void onClickStop(View view) {
        notificationModule.stopNotifyRepeating(this);
    }

    public void onClickSetStrings(View view) {

    }
}
