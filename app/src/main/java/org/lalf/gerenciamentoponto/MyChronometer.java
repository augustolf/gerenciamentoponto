package org.lalf.gerenciamentoponto;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

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

    public void update(List<Record> list) {
        records = list;
        int size = records.size();

        setBase(getSpentTime());
        if (size % 2 == 0) {
            stop();
        } else {
            start();
        }

    }

    private long getSpentTime() {
        long spentTime = 0;
        long elapsedTime = SystemClock.elapsedRealtime();
        int count = 0;
        while(count < records.size()) {
            if (count + 1 < records.size()) {
                spentTime = spentTime + (records.get(count + 1).getDateTime().getMillis() - records.get(count).getDateTime().getMillis());
            } else {
                long elapsedRealtimeOffset = System.currentTimeMillis() - SystemClock.elapsedRealtime();
                elapsedTime = records.get(count).getDateTime().getMillis() - elapsedRealtimeOffset;
            }
            count+=2;
        }

        return elapsedTime - spentTime;

    }
}
