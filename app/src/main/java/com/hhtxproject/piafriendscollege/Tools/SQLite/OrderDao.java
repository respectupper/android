package com.hhtxproject.piafriendscollege.Tools.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Respectupper on 2018/8/8.
 */

public class OrderDao {

    public SQLiteDatabase getDatabase() {
        return database;
    }

    private SQLiteDatabase database;

    public OrderDao(Context context){
        SQLiteDbHelper helper = new SQLiteDbHelper(context);
        database = helper.getWritableDatabase();
    }
}
