package com.example.ytian.young.moduel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ytian.young.moduel.dao.ContactTable;

/**
 * Created by YTian on 2017/8/15.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        创建联系人表
        db.execSQL(ContactTable.CREATE_TAB);
//            创建邀请信息表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
