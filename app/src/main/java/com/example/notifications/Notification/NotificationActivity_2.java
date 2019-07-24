package com.example.notifications.Notification;

import android.os.Bundle;
import android.view.View;

public class NotificationActivity_2 extends NotificationActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    NotificationModule notificationModule;

    boolean doOnce = true;

    @Override
    public void onClickYes(View view) {
        super.onClickYes(view);
        if (doOnce) {
            notificationModule = new NotificationModule(100,
                    0, this.getClass(), NotificationIntentServiceExample.class, 0);
            notificationModule.startNotify(this);
            doOnce = false;
        }
    }

    @Override
    public void onClickNo(View view) {
        super.onClickNo(view);
        notificationModule.stopNotify(this);
    }
}
