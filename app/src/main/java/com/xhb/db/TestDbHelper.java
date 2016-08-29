package com.xhb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by onlystar on 2016/8/11.
 */

public class TestDbHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "user.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "user";

    public TestDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " create table " + TABLE_NAME + " ( " +
                " name varchar(20)  , " +
                " age int not null  ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
