package org.lalf.gerenciamentoponto.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.lalf.gerenciamentoponto.Record;
import org.lalf.gerenciamentoponto.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalf on 12/30/2014.
 */
public class RecordDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_CHECKDATATIME};


    public RecordDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Record createRecord(String dataTime) {
        ContentValues values = new ContentValues();
        System.out.println("createRecord -> " + dataTime);
        values.put(SQLiteHelper.COLUMN_CHECKDATATIME, dataTime);
        long insertId = database.insert(SQLiteHelper.TABLE_RECORDS, "", values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_RECORDS,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Record newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public void deleteRecord(Record record) {
        long id = record.getId();
        database.delete(SQLiteHelper.TABLE_RECORDS, SQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<Record>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_RECORDS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Record record = cursorToRecord(cursor);
            records.add(record);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return records;
    }

    public List<Record> getRecordsByDay(String day) {
        List<Record> records = new ArrayList<Record>();

        String[] whereArgs = {day + "%"};

        Cursor cursor = database.query(SQLiteHelper.TABLE_RECORDS,
                allColumns, SQLiteHelper.COLUMN_CHECKDATATIME + " LIKE ? ", whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Record record = cursorToRecord(cursor);
            records.add(record);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return records;
    }

    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record();
        record.setId(cursor.getLong(0));
        record.setDataTime(cursor.getString(1));
        record.setDateTime(DateUtil.recordToDateTime(cursor.getString(1)));
        return record;
    }
}
