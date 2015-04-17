package org.lalf.gerenciamentoponto.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by lalf on 4/17/2015.
 */
public class WorkingTimeReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, WorkingTimeAlarmService.class);
        startWakefulService(context, service);
    }
}
