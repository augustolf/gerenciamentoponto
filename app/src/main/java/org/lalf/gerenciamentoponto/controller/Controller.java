package org.lalf.gerenciamentoponto.controller;

import org.lalf.gerenciamentoponto.Record;

import java.util.List;

/**
 * Created by lalf on 12/29/2014.
 */
public interface Controller {

    public void open();
    public void close();
    public void insertTimeCheck(int hour, int min);
    public List<Record> getAllRecords();
    public List<Record> getRecordsToDay();
    public long getSpentTimeToDay();
}
