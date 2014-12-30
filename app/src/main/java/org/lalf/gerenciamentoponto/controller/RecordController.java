package org.lalf.gerenciamentoponto.controller;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lalf on 12/29/2014.
 */
public class RecordController implements Controller {

    private Context mContext;

    public RecordController(Context context) {
        mContext = context;
    }

    public void insertTimeCheck(int hour, int min) {
        String day = new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
        String time = (hour > 9 ? hour : "0" + hour) + ":" +  (min > 9 ? min : "0" + min) + ":00:000";
        System.out.println("DATATIME: " + day + time);
    }
}
