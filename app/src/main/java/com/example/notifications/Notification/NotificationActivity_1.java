package com.example.notifications.Notification;

import android.os.Bundle;
import android.view.View;

public class NotificationActivity_1 extends NotificationActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    NotificationModule notificationModule = new NotificationModule(100, 0, this.getClass(), 0);

    boolean doOnce = true;

    @Override
    public void onClickYes(View view) {
        super.onClickYes(view);
        if (doOnce) {
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
