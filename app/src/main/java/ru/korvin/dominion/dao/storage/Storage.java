package ru.korvin.dominion.dao.storage;

import android.content.Context;

import java.util.List;

import ru.korvin.dominion.dao.storage.shell.SaveRecord;
import ru.korvin.dominion.mechanic.baseObject.state.State;

public class Storage {

    public List<SaveRecord> getSaves() {
        return null;
    }

    public State load(SaveRecord record) {
        return null;
    }

    public void save(State state) {
    }


    private DB mDB;

    public Storage(Context context) {
        this.mDB = new DB(context);
    }
}
