package org.lalf.gerenciamentoponto;

import android.os.CountDownTimer;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lalf on 12/31/2014.
 */
public class TimeCounter extends CountDownTimer {

    private static final long BASE_TIME = 24*60*60*1000;

    private TimeCounterTickListener mListener;
    private boolean counting = false;
    private long timeSpent = 0;
    private long timepaused = 0;

    public TimeCounter() {
        super(BASE_TIME, 1000);
    }

    public TimeCounter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long timeSpent = -(millisUntilFinished - BASE_TIME);
        mListener.onTick(timeToString(timeSpent));
    }

    @Override
    public void onFinish() {
        cancel();
    }

    public void updateRecordList(List<Record> records) {
        if (records.isEmpty()) {

        } else  if (records.size() == 1) {
            String time = records.get(0).getDataTime();
            Seconds diff = Seconds.secondsBetween(new DateTime(), new DateTime());
        }


        for (int i = 0; i < records.size(); i++) {

        }

    }

    public void setTimeCounterTickListener(TimeCounterTickListener listener) {
        mListener = listener;
    }

    public interface TimeCounterTickListener {
        public void onTick(String time);
    }

    public void play() {
        counting = true;
    }

    public void pause() {
        counting = false;
    }

    public boolean isCounting() {
        return counting;
    }

    private String timeToString(long timeSpent) {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeSpent),
                TimeUnit.MILLISECONDS.toMinutes(timeSpent) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeSpent)),
                TimeUnit.MILLISECONDS.toSeconds(timeSpent) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeSpent)));
    }

}