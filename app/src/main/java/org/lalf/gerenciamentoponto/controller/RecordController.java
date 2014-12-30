package org.lalf.gerenciamentoponto.controller;

import android.content.Context;

import org.lalf.gerenciamentoponto.Record;
import org.lalf.gerenciamentoponto.persistence.RecordDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String day = new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
        String time = (hour > 9 ? hour : "0" + hour) + ":" +  (min > 9 ? min : "0" + min);
        System.out.println("DATATIME: " + day + time);
        recordDataSource.createRecord(day + time);
    }

    public List<Record> getAllRecords() {
        return recordDataSource.getAllRecords();
    }
}
