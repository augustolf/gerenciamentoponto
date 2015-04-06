package org.lalf.gerenciamentoponto.controller;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;
import org.lalf.gerenciamentoponto.Record;
import org.lalf.gerenciamentoponto.persistence.RecordDataSource;
import org.lalf.gerenciamentoponto.util.DateUtil;

import java.util.List;

/**
 * Created by lalf on 12/29/2014.
 */
public class RecordController implements Controller {

    private Context mContext;
    private RecordDataSource recordDataSource;

    public RecordController(Context context) {
        mContext = context;
        recordDataSource = new RecordDataSource(mContext);
        open();
    }

    public void open() {
        recordDataSource.open();
    }

    public void close() {
        recordDataSource.close();
    }

    public void insertTimeCheck(int hour, int min) {
        DateTime dt = new DateTime();
        String time = (hour > 9 ? hour : "0" + hour) + ":" +  (min > 9 ? min : "0" + min);
        recordDataSource.createRecord(dt.toString("yyyy-MM-dd ") + time);
    }

    public List<Record> getAllRecords() {
        return recordDataSource.getAllRecords();
    }

    public List<Record> getRecordsToDay() {
        DateTime dt = new DateTime();
        //System.out.println("DATATIME: " + dt.toString("yyyy-MM-dd"));
        return recordDataSource.getRecordsByDay(dt.toString("yyyy-MM-dd"));
    }

    public long getSpentTimeToDay() {
        List<Record> records = getRecordsToDay();
        long millis = 0;

        for (int i = 0; i < records.size(); i++) {
            // se for tiver 1 registro ou o ultimo registro for impar
            if (records.size() == 1 || (records.size() == (i+1))) {
                // pega o registro e subtrai a hora atual
                millis += DateUtil.now().getMillis() - DateUtil.recordToDateTime(records.get(i).getDataTime()).getMillis();
            } else {
                // incremente o periodo do registro atual e posterior
                millis += DateUtil.recordToDateTime(records.get(i+1).getDataTime()).getMillis() - DateUtil.recordToDateTime(records.get(i).getDataTime()).getMillis();
            }
        }

        Log.d("Luiz", "getSpentTimeToDay:" + millis);
        return millis;
    }
}
