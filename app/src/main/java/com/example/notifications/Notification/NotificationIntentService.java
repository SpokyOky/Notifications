package com.example.notifications.Notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.notifications.MainActivity;
import com.example.notifications.R;

import java.util.concurrent.TimeUnit;

public class NotificationIntentService extends IntentService {
    private static int NOTIFICATION_ID = 0;

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
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Resources res = this.getResources();
        long when = System.currentTimeMillis();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                //  .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background))
                .setTicker(getString(R.string.notification_ticker))
                .setWhen(when)
                .setShowWhen(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID++, builder.build());
    }
}
