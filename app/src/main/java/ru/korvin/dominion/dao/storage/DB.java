package ru.korvin.dominion.dao.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import ru.korvin.dominion.dao.storage.shell.SaveRecord;
import ru.korvin.dominion.mechanic.server.state.State;


public class DB {

    private static final String TABLE_NAME_SAVE = "save";
    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_SAVE = "save";
    private static final String DECS = " desc";

    public long saveState(State state, String name) {
        return saveState(state, name, SaveRecord.WRONG_ID);
    }

    public long saveState(State state, String name, long id) {
        SaveRecord saveRecord = new SaveRecord(name, new Date(), state, id);
        SQLiteDatabase base = mDBHelper.getWritableDatabase();
        ContentValues param = new ContentValues();
        param.put(COLUMN_NAME_NAME, saveRecord.getName());
        param.put(COLUMN_NAME_DATE, saveRecord.getDate().getTime());
        param.put(COLUMN_NAME_SAVE, saveRecord.getData());
        long idSave;
        if (saveRecord.getId() != SaveRecord.WRONG_ID) {
            base.update(TABLE_NAME_SAVE, param, COLUMN_NAME_ID + " = ?", new String[]{Long.toString(saveRecord.getId())});
            idSave = id;
        } else
            idSave = base.insert(TABLE_NAME_SAVE, null, param);
        base.close();
        return idSave;
    }

    private static final String[] SELECT_SAVE_DATA = new String[]{COLUMN_NAME_NAME, COLUMN_NAME_DATE, COLUMN_NAME_SAVE};
    private static final String WHERE_ID = COLUMN_NAME_ID + "= ?";

    public SaveRecord getState(long id) {
        SQLiteDatabase base = mDBHelper.getReadableDatabase();
        Cursor cursor = base.query(TABLE_NAME_SAVE, SELECT_SAVE_DATA, WHERE_ID, new String[]{Long.toString(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            int id_name = cursor.getColumnIndex(COLUMN_NAME_NAME);
            int id_date = cursor.getColumnIndex(COLUMN_NAME_DATE);
            int id_save = cursor.getColumnIndex(COLUMN_NAME_SAVE);

            String name = cursor.getString(id_name);
            Date date = new Date(cursor.getLong(id_date));
            byte[] blob = cursor.getBlob(id_save);
            SaveRecord saveRecord = new SaveRecord(name, date, blob, id);
            cursor.close();
            base.close();
            return saveRecord;
        } else {
            cursor.close();
            base.close();
            throw new RuntimeException("не найденно сохранение");
        }
    }

    private static final String[] SELECT_ALL_SAVE_COLUNM = new String[]{COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_DATE};

    public Cursor getAllState() {
        SQLiteDatabase base = mDBHelper.getReadableDatabase();
        return base.query(TABLE_NAME_SAVE, SELECT_ALL_SAVE_COLUNM, null, null, null, null, COLUMN_NAME_DATE + DECS);
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
