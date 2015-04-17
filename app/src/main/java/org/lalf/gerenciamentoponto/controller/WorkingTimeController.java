package org.lalf.gerenciamentoponto.controller;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import org.lalf.gerenciamentoponto.Record;
import org.lalf.gerenciamentoponto.service.WorkingTimeReceiver;

import java.util.List;

/**
 * Created by lalf on 4/17/2015.
 */
public class WorkingTimeController {

    private long breakTimeAlert = 1*60*1000;//55*60*1000;
    private List<Record> mRecords;
    private Activity mActivity;
    private PendingIntent mPendingIntent;

    public WorkingTimeController(Activity activity, List<Record> records) {
        mRecords = records;
        mActivity = activity;
    }

    public void check() {

        if (true /*mRecords.size() == 2*/) {
            //long timeToEndLunch = mRecords.get(1).getDateTime().getMillis() + breakTimeAlert;
            long timeToEndLunch = System.currentTimeMillis();

            Intent myIntent = new Intent(mActivity, WorkingTimeReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(mActivity, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager) mActivity.getSystemService(mActivity.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, timeToEndLunch+breakTimeAlert, mPendingIntent);
        }

    }

}
