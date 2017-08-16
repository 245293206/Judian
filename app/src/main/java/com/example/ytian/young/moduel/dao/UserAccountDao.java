package com.example.ytian.young.moduel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ytian.young.moduel.bean.UserInfo;
import com.example.ytian.young.moduel.db.UserAccountDB;

/**
 * Created by YTian on 2017/8/14.
 */
//用户帐号数据库的操作类
public class UserAccountDao {

    private UserAccountDB mHelper;

    public UserAccountDao(Context context) {
        mHelper = new UserAccountDB(context);
    }
    //添加用户到数据库
    public void addAccount(UserInfo user){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID, user.getHxId());
        values.put(UserAccountTable.COL_NAME, user.getUsername());
        values.put(UserAccountTable.COL_NICK, user.getNick());
        values.put(UserAccountTable.COL_PHOTO, user.getPhoto());
        db.replace(UserAccountTable.TABLE_NAME,null,values);
    }
    public UserInfo getAccountByHxId(String hxId){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " + UserAccountTable.TABLE_NAME + " where " + UserAccountTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql,new String[]{hxId});
        UserInfo userInfo = null;
        if (cursor.moveToNext()){
            userInfo = new UserInfo();
            userInfo.setHxId(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setUsername(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }
        cursor.close();
        return userInfo;
    }
}
