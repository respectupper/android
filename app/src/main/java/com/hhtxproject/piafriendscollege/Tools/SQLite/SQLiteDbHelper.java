package com.hhtxproject.piafriendscollege.Tools.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Respectupper on 2018/8/8.
 */

public class SQLiteDbHelper extends SQLiteOpenHelper {
    public SQLiteDbHelper(Context context) {
        super(context, "Script.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stu_table="create table script(_id integer primary key autoincrement,simple_data text,people_data text,content text)";
//执行SQL语句
        sqLiteDatabase.execSQL(stu_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {

            //或者这样写
            String query = String.format("PRAGMA foreign_keys = %s", "ON");
            db.execSQL(query);
        }
    }
}
