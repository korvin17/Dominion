package ru.korvin.dominion.dao.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB {

    private static final String TABLE_NAME_SAVE = "save";
    private static final String COLUMN_NAME_ID = "_id";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_SAVE = "save";

    private DBHelper mDBHelper;

    public DB(Context contex) {
        this.mDBHelper = new DBHelper(contex);
    }

    public void close() {
        mDBHelper.close();
    }


    private class DBHelper extends SQLiteOpenHelper {
        private static final int version = 1;
        private static final String dbName = "db.sqlite";

        public DBHelper(Context context) {
            super(context, dbName, null, DBHelper.version);
        }


/*
        public List<SaveRecord> getSaves() {
            SQLiteDatabase database = getReadableDatabase();
            Cursor cursor = database.query(false, TABLE_NAME_SAVE, new String[]{COLUMN_NAME_ID, COLUMN_NAME_DATE, COLUMN_NAME_NAME}, null, null, null, null, COLUMN_NAME_DATE, null);


            database.close();
            return null;
        }*/


        private static final String CREATE_TABLE_SAVE = "create table " + TABLE_NAME_SAVE + " ( " +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_NAME + " TEXT," +
                COLUMN_NAME_DATE + " INTEGER," +
                COLUMN_NAME_SAVE + " BLOD" +
                ")";

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_SAVE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
