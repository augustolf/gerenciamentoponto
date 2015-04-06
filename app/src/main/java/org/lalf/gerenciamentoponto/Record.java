package org.lalf.gerenciamentoponto;

import org.joda.time.DateTime;

/**
 * Created by lalf on 12/30/2014.
 */
public class Record {

    private long id;
    private String dataTime;
    private DateTime dateTime;

    public Record() {
    }

    public Record(String dt) {
        dataTime = dt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getTime() {
        return dataTime.substring(11);
    }

    public int getYear() {
        return Integer.valueOf(dataTime.substring(0, 3));
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime =  dateTime;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

}
