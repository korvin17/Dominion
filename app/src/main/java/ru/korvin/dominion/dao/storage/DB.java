package ru.korvin.dominion.dao.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import ru.korvin.dominion.dao.storage.shell.SaveRecord;
import ru.korvin.dominion.mechanic.baseObject.state.State;
import ru.korvin.dominion.mechanic.baseObject.state.player.Player;


public class DB {

    private static final String TABLE_NAME_SAVE = "save";
    private static final String COLUMN_NAME_ID = "_id";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_SAVE = "save";


    public void saveState(State state) {
        SaveRecord saveRecord = new SaveRecord("", new Date(), state);
        SQLiteDatabase base = mDBHelper.getWritableDatabase();
        ContentValues param = new ContentValues();
        param.put(COLUMN_NAME_NAME, saveRecord.getName());
        param.put(COLUMN_NAME_DATE, saveRecord.getDate().getTime());
        param.put(COLUMN_NAME_SAVE, saveRecord.getData());
        if (saveRecord.getId() != -1)
            base.update(TABLE_NAME_SAVE, null, " id = ?", new String[]{Long.toString(saveRecord.getId())});
        else
            base.insert(TABLE_NAME_SAVE, null, param);
        base.close();
    }

    private static final String[] SELECT_ALL_SAVE_COLUNM = new String[]{COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_DATE, COLUMN_NAME_SAVE};

    public Cursor getAllState() {
        SQLiteDatabase base = mDBHelper.getReadableDatabase();
        return base.query(TABLE_NAME_SAVE, SELECT_ALL_SAVE_COLUNM, null, null, null, null, COLUMN_NAME_DATE);
    }

    private DBHelper mDBHelper;

    public DB(Context contex) {
        this.mDBHelper = new DBHelper(contex);
    }

    public void close() {
        mDBHelper.close();
    }

    private class DBHelper extends SQLiteOpenHelper {
        private static final int version = 2;
        private static final String dbName = "db.sqlite";

        public DBHelper(Context context) {
            super(context, dbName, null, DBHelper.version);
        }

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
            db.delete(TABLE_NAME_SAVE, null, null);
        }
    }
}
