package org.lalf.gerenciamentoponto.util;

/**
 * Created by lalf on 4/17/2015.
 */
public class HourMin {

    int hour;
    int min;

    HourMin(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

}
