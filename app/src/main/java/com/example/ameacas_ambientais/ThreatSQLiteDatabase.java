package com.example.ameacas_ambientais;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ThreatSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "threats.db";
    public static final Integer DATABASE_VERSION = 10;
    private SQLiteDatabase db;

    public ThreatSQLiteDatabase(Context ctx){
        this.ctx = ctx;
        db = new ThreatSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class ThreatTable implements BaseColumns {
        public static final String TABLE_NAME = "threat";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESCRIPTION = "description";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_ADDRESS       + " TEXT, " +
                    COLUMN_DATE          + " TEXT, " +
                    COLUMN_DESCRIPTION   + " TEXT)";
            return sql;
        }
    }

    public Long addThreat(Threat t){
        ContentValues values = new ContentValues();
        values.put(ThreatTable.COLUMN_ADDRESS, t.getAddress());
        values.put(ThreatTable.COLUMN_DATE, t.getDate());
        values.put(ThreatTable.COLUMN_DESCRIPTION, t.getDescription());

        return db.insert(ThreatTable.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public Threat getThreat(Long id){
        String cols[] = {ThreatTable._ID, ThreatTable.COLUMN_ADDRESS, ThreatTable.COLUMN_DATE, ThreatTable.COLUMN_DESCRIPTION};
        String args[] = {id.toString()};
        Cursor cursor = db.query(ThreatTable.TABLE_NAME, cols, ThreatTable._ID+"=?", args, null, null, ThreatTable._ID);

        if(cursor.getCount() != 1){
            return null;
        }

        cursor.moveToNext();
        Threat t = new Threat();
        t.setId(cursor.getLong(cursor.getColumnIndex(ThreatTable._ID)));
        t.setAddress(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_ADDRESS)));
        t.setDate(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_DATE)));
        t.setDescription(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_DESCRIPTION)));

        return t;
    }

    @SuppressLint("Range")
    public List<Threat> getThreats(){
        String cols[] = {ThreatTable._ID, ThreatTable.COLUMN_ADDRESS, ThreatTable.COLUMN_DATE, ThreatTable.COLUMN_DESCRIPTION};
        Cursor cursor = db.query(ThreatTable.TABLE_NAME, cols, null, null, null, null, ThreatTable.COLUMN_DATE);
        List<Threat> threats = new ArrayList<>();
        Threat t;

        while(cursor.moveToNext()){
            t = new Threat();
            t.setId(cursor.getLong(cursor.getColumnIndex(ThreatTable._ID)));
            t.setAddress(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_ADDRESS)));
            t.setDate(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_DATE)));
            t.setDescription(cursor.getString(cursor.getColumnIndex(ThreatTable.COLUMN_DESCRIPTION)));
            threats.add(t);
        }

        return threats;
    }

    public Integer removeThreat(Threat t){
        String args[] = {t.getId().toString()};
        return db.delete(ThreatTable.TABLE_NAME, ThreatTable._ID + "=?", args);
    }

    public Integer updateThreat(Threat t){
        String args[] = {t.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(ThreatTable.COLUMN_ADDRESS, t.getAddress());
        values.put(ThreatTable.COLUMN_DATE, t.getDate());
        values.put(ThreatTable.COLUMN_DESCRIPTION, t.getDescription());
        return db.update(ThreatTable.TABLE_NAME, values, ThreatTable._ID + "=?", args);
    }

    private class ThreatSQLiteDatabaseHelper extends SQLiteOpenHelper {

        public ThreatSQLiteDatabaseHelper() {
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ThreatTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ThreatTable.TABLE_NAME);
            onCreate(db);
        }
    }
}
