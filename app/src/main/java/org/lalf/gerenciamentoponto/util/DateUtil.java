package org.lalf.gerenciamentoponto.util;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.lalf.gerenciamentoponto.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalf on 03/04/2015.
 */
public class DateUtil {

    public static long diffBetweenRecordsInMillis(Record r1, Record r2) {
        DateTime dt1 = recordToDateTime(r1.getDataTime());
        DateTime dt2 = recordToDateTime(r2.getDataTime());
        Interval interval = new Interval(dt1, dt2);
        return  interval.toDurationMillis();
    }

    public static DateTime recordToDateTime(String dateTime) {
        //1234567890123456
        //2015-04-04 15:52
        //Log.d("Luiz", "recordToDateTime --------> " + dateTime);
        //2015-04-03 18:54
        String[] dateTimeSplit = dateTime.split(" ");
        String[] dateSplit = dateTimeSplit[0].split("-");
        String[] timeSplit = dateTimeSplit[1].split(":");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        int hour = Integer.parseInt(timeSplit[0]);
        int min = Integer.parseInt(timeSplit[1]);

        System.out.println("recordToDateTime: " + year + "-" + month + "-" + day + " " + hour + ":" + min);

        return new DateTime(year, month, day, hour, min, 0, 0);
    }

    public static DateTime now() {
        return new DateTime();//SystemClock.elapsedRealtime());
    }

    public static void main(String args[]) {
        /*DateTime d1 = new DateTime(2015, 3, 4, 8, 10, 0, 0);
        DateTime d2 = new DateTime(2015, 3, 4, 12, 15, 0, 0);
        DateTime d3 = new DateTime(2015, 3, 4, 14, 15, 0, 0);
        Period period1 = new Period(d1, d2);
        Period period2 = new Period(d2, d3);
        //System.out.println("Periodo: " + period1.getHours() + ":" + period1.getMinutes());
        DateTime now = now();
        //System.out.println("Now: " + now.getYear() + "-" + now.getMonthOfYear() + "-" + now.getDayOfMonth() + " " + now.getHourOfDay() + ":" + now.getMinuteOfHour());
        */

        //DateTime now = recordToDateTime("2015-04-03 18:44");
        //System.out.println("Now: " + now.getYear() + "-" + now.getMonthOfYear() + "-" + now.getDayOfMonth() + " " + now.getHourOfDay() + ":" + now.getMinuteOfHour());


        //recordToDateTime("2015-04-04 15:52");

        List<Record> records = new ArrayList<Record>();
        records.add(new Record("2015-04-05 10:03"));
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
        System.out.println("millis: " + millis + " mins: " + (millis/(60*1000)));
    }

}
