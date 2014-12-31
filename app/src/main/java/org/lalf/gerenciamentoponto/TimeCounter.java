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

    private TimeCounterTickListener mListener;

    public TimeCounter() {
        super(8*60*60*1000, 1000);
    }

    public TimeCounter(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        System.out.println("millisUntilFinished|: " + millisUntilFinished);

        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

        System.out.println("hms: " + hms);
        mListener.onTick(hms);
    }

    @Override
    public void onFinish() {
        cancel();
    }

    public void updateRecordList(List<Record> records) {

        if (records.isEmpty()) {

        } if (records.size() == 1) {
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

}
