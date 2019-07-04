package com.example.notifications.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.notifications.R;

public class NotificationActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }



    public void onClickYes(View view) {
    }

    public void onClickNo(View view) {
    }
}
