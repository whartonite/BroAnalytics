package com.example.joe.broanalytics;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BA.db";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CAT_TABLE);
        db.execSQL(SQL_CREATE_DATA_TABLE);
        db.execSQL(SQL_CREATE_ACT_TABLE);
        addCategory("Lifting");
        //addActivity()
        addCategory("Cardio");
        addCategory("Food");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean updateData(String[] ss, String date, String act, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD1, ss[0]);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD2, ss[1]);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_DATE, date);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_ACTIVITY, act);
        boolean successful = false;
        try
        {
            successful = db.update(CategoriesContract.FeedEntry3.TABLE_NAME, v, SQL_UPDATE + id, null) > 1;
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        db.close();
        return successful;

    }

    public boolean addData(String[] ss, String date, String act) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD1, ss[0]);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD2, ss[1]);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_DATE, date);
        v.put(CategoriesContract.FeedEntry3.COLUMN_NAME_ACTIVITY, act);
        boolean successful = false;
        try
        {
            successful = db.insert(CategoriesContract.FeedEntry3.TABLE_NAME, null, v) > 0;
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        db.close();
        return successful;
    }

    public boolean addCategory(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(CategoriesContract.FeedEntry1.COLUMN_NAME_NAME, s);
        boolean successful = false;
        try
        {
            successful = db.insert(CategoriesContract.FeedEntry1.TABLE_NAME, null, v) > 0;
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        db.close();
        return successful;
    }

    public boolean addActivity(String s, String f1, String f2, String cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(CategoriesContract.FeedEntry2.COLUMN_NAME_NAME, s);
        v.put(CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD1_NAME, f1);
        v.put(CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD2_NAME, f2);
        v.put(CategoriesContract.FeedEntry2.COLUMN_NAME_CATEGORY, cat);
        boolean successful = false;
        try
        {
            successful = db.insert(CategoriesContract.FeedEntry2.TABLE_NAME, null, v) > 0;
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        db.close();
        return successful;
    }

    public String[] getCategories() {
        List<String> ss = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = db.rawQuery(SQL_GET_CATEGORIES, null);
        while (cursor1.moveToNext()) {
            ss.add(cursor1.getString(0));
        }
        db.close();
        return Arrays.asList(ss.toArray()).toArray(new String[ss.toArray().length]);
    }

    public String[] getActivities(String Category) {
        List<String> ss = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = db.rawQuery(SQL_GET_ACTIVITIES + Category+"'", null);
        while (cursor1.moveToNext()) {
            ss.add(cursor1.getString(0));
        }
        db.close();
        return Arrays.asList(ss.toArray()).toArray(new String[ss.toArray().length]);
    }

    public int dataExists(String activity, String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = db.rawQuery(SQL_DATA_EXISTS1 + activity + "'" + SQL_DATA_EXISTS2 + date + "'", null);
        return cursor1.getCount();
    }

    public String[] getFields(String activity, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] ss = new String[2];
        Cursor cursor1 = db.rawQuery(SQL_GET_FIELDS1 + activity + "'", null);
        int i = 0;
        while (cursor1.moveToNext()) {
            ss[i] = cursor1.getString(0);
            i++;
        }
        return ss;
    }

    private static final String SQL_CREATE_DATA_TABLE =
            "CREATE TABLE " + CategoriesContract.FeedEntry3.TABLE_NAME + " (" +
                    CategoriesContract.FeedEntry3._ID + " INTEGER PRIMARY KEY," +
                    CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD1 + " TEXT," +
                    CategoriesContract.FeedEntry3.COLUMN_NAME_FIELD2 + " TEXT, " +
                    CategoriesContract.FeedEntry3.COLUMN_NAME_DATE + " TEXT, " +
                    CategoriesContract.FeedEntry3.COLUMN_NAME_ACTIVITY + " TEXT)";

    private static final String SQL_CREATE_CAT_TABLE =
            "CREATE TABLE " + CategoriesContract.FeedEntry1.TABLE_NAME + " (" +
                    CategoriesContract.FeedEntry1._ID + " INTEGER PRIMARY KEY," +
                    CategoriesContract.FeedEntry1.COLUMN_NAME_NAME + " TEXT)";

    private static final String SQL_CREATE_ACT_TABLE =
            "CREATE TABLE " + CategoriesContract.FeedEntry2.TABLE_NAME + " (" +
                    CategoriesContract.FeedEntry2._ID + " INTEGER PRIMARY KEY," +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_NAME + " TEXT, " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD1_NAME + " TEXT, " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD2_NAME + " TEXT, " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_CATEGORY + " TEXT)";
    ;

    private static final String SQL_CREATE_TODO_TABLE =
            "CREATE TABLE " + CategoriesContract.FeedEntry4.TABLE_NAME + " (" +
                    CategoriesContract.FeedEntry4._ID + " INTEGER PRIMARY KEY," +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_NAME + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_PRIORITY + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_CREATION_DATE + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_DUE_DATE + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_ACTIVE + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_OCCURRENCE + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_REPEAT + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_REPEAT_UNTIL + " TEXT, " +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_STORE + " TEXT)";
    ;

    private static final String SQL_CREATE_TODO_COMPLETED_TABLE =
            "CREATE TABLE " + CategoriesContract.FeedEntry5.TABLE_NAME + " (" +
                    CategoriesContract.FeedEntry5._ID + " INTEGER PRIMARY KEY," +
                    CategoriesContract.FeedEntry5.COLUMN_NAME_NAME + " TEXT, " +
                    CategoriesContract.FeedEntry5.COLUMN_NAME_Completion_DATE + " TEXT, " +
                    CategoriesContract.FeedEntry5.COLUMN_NAME_NOTES + " TEXT)";
    ;

    private static final String SQL_GET_ACTIVE =
            "SELECT" + CategoriesContract.FeedEntry4.COLUMN_NAME_NAME + "FROM" +
                    CategoriesContract.FeedEntry4.TABLE_NAME + "WHERE" +
                    CategoriesContract.FeedEntry4.COLUMN_NAME_ACTIVE + "='";

    private static final String SQL_GET_ACTIVITIES =
            "SELECT " + CategoriesContract.FeedEntry2.COLUMN_NAME_NAME + " FROM " +
                    CategoriesContract.FeedEntry2.TABLE_NAME + " WHERE " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_CATEGORY + "='";



    private static final String SQL_GET_CATEGORIES =
            "SELECT " + CategoriesContract.FeedEntry1.COLUMN_NAME_NAME + " FROM " +
                    CategoriesContract.FeedEntry1.TABLE_NAME;

    private static String SQL_DATA_EXISTS1 =
            "SELECT " + CategoriesContract.FeedEntry3._ID + " FROM " +
                    CategoriesContract.FeedEntry3.TABLE_NAME + " WHERE " +
                    CategoriesContract.FeedEntry3.COLUMN_NAME_ACTIVITY + "='";

    private static String SQL_DATA_EXISTS2 = " AND " +
            CategoriesContract.FeedEntry3.COLUMN_NAME_DATE + "='";

    private static final String SQL_UPDATE =
            "SELECT " + CategoriesContract.FeedEntry3._ID + " FROM " +
                    CategoriesContract.FeedEntry3.TABLE_NAME +
                    " WHERE " + CategoriesContract.FeedEntry3._ID + "=";

    private static final String SQL_GET_FIELDS1 =
            "SELECT " + CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD1_NAME + " , " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_FIELD2_NAME + " FROM " +
                    CategoriesContract.FeedEntry2.TABLE_NAME + " WHERE " +
                    CategoriesContract.FeedEntry2.COLUMN_NAME_NAME + "='";
}
