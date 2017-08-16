package com.example.ytian.young.moduel.dao;

/**
 * Created by YTian on 2017/8/14.
 */

public class UserAccountTable {
    public static final String TABLE_NAME = "table_account";
    public static final String COL_NAME = "username";
    public static final String COL_HXID = "hxId";
    public static final String COL_NICK = "nick";
    public static final String COL_PHOTO = "photo";

    public static final String CREATE_TAB = "create table "
            + TABLE_NAME + "  ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_NICK + " text,"
            + COL_PHOTO + " text);";
}
