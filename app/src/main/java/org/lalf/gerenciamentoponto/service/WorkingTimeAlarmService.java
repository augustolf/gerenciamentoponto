package org.lalf.gerenciamentoponto.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import org.lalf.gerenciamentoponto.MainActivity;
import org.lalf.gerenciamentoponto.R;

/**
 * Created by lalf on 4/17/2015.
 */
public class WorkingTimeAlarmService extends IntentService {

    private static final int NOTIFICATION_ID = 1;

    public WorkingTimeAlarmService() {
        super("WorkingTimeAlarmService");
    }

    private NotificationManager mNotificationManager;

    @Override
    protected void onHandleIntent(Intent intent) {
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(getString(R.string.break_time_is_ending))
                        .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Seu tempo de almoço está acabando."))
                        .setContentText("Vá bater o ponto!");

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
