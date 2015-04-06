package org.lalf.gerenciamentoponto;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalf on 04/04/2015.
 */
public class MyChronometer extends Chronometer {

    private long msElapsed;
    private long baseTime;
    private long dataStarted;
    public boolean isRunning = false;
    List<Record> records = new ArrayList<Record>();

    public MyChronometer(Context context) {
        super(context);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyChronometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public long getMsElapsed() {
        return msElapsed;
    }

    public void setMsElapsed(long ms) {
        setBase(getBase() - ms);
        DateTime dt = new DateTime(getBase());
        Log.d("Luiz", "TEMPO CRONOMETRO: " + dt.getYear() + "-" + dt.getMonthOfYear() + "-" + dt.getDayOfMonth() + " " + dt.getHourOfDay() + ":" + dt.getMinuteOfHour() + ":" + dt.getSecondOfMinute());
        msElapsed  = ms;
    }

    @Override
    public void start() {
        super.start();
        /*setBase(SystemClock.elapsedRealtime() - msElapsed);
        isRunning = true;*/
    }

    @Override
    public void stop() {
        super.stop();
        /*if(isRunning) {
            msElapsed = SystemClock.elapsedRealtime() - this.getBase();
        }
        isRunning = false;*/
    }

    public void update(List<Record> list) {
        records = list;
        int size = records.size();
        if (size == 1) { // se for o primero
            DateTime dt = records.get(0).getDateTime();
            dataStarted = SystemClock.elapsedRealtime() + (dt.getMillis() - System.currentTimeMillis());
            Log.d("Luiz", "Base do Cronometro: " + dt.getYear() + "-" + dt.getMonthOfYear() + "-" + dt.getDayOfMonth() + " " + dt.getHourOfDay() + ":" + dt.getMinuteOfHour() + ":" + dt.getSecondOfMinute() + " millis: " + dataStarted + " " );
            Log.d("Luiz", "millis: " + dataStarted + " elapsedRealtime: " + SystemClock.elapsedRealtime() + " currentTimeMillis: " + System.currentTimeMillis());
            setBase(dataStarted);
            start();
        } else if (size % 2 != 0) { // se não for o primeiro e for impar
            int last = size - 1;
            dataStarted = dataStarted - (records.get(last).getDateTime().getMillis() - records.get(last-1).getDateTime().getMillis());
            setBase(dataStarted);
            start();
        } else {
            stop();
        }

    }
}
