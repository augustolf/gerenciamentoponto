package org.lalf.gerenciamentoponto.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lalf on 12/29/2014.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHECKDATATIME = "checkdatatime";

    private static final String DATABASE_NAME = "ponto.db";
    private static final int DATABASE_VERSION = 16;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RECORDS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CHECKDATATIME + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

}