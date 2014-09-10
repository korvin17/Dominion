package ru.korvin.dominion.dao;

import android.app.Application;
import android.content.SharedPreferences;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.storage.DB;
import ru.korvin.dominion.dao.storage.Storage;
import ru.korvin.dominion.dao.storage.shell.SaveRecord;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.server.event.Event;
import ru.korvin.dominion.mechanic.server.Server;

public class GameApplication extends Application {
    private static GameApplication defaultGameApplication;
    private static final String PREFERENCES = "game preferences";

    @Override
    public void onCreate() {
        super.onCreate();
        this.server = Server.getDefault();
        this.storage = new Storage();
        this.dataBase = new DB(getApplicationContext());
        this.preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        defaultGameApplication = this;
        if (!isFirstLaunch()) {
            long idAutoSave = this.getIdAutoSave();
            loadSave(idAutoSave);
        }
    }

    public void loadSave(long idSave) {
        SaveRecord record = this.dataBase.getState(idSave);
        this.server.setState(record.getState());
    }

    public long saveState(String name, long idSave) {
        return dataBase.saveState(server.getState(), name, idSave);
    }

    public long saveState(String name) {

        return dataBase.saveState(server.getState(), name);
    }

    private static final String PREFERENCE_IS_FIRST = "is_first";
    private static final String PREFERENCE_ID_AUTOSAVE = "autosave id";

    public boolean isFirstLaunch() {
        return this.preferences.getBoolean(PREFERENCE_IS_FIRST, true);
    }

    public long getIdAutoSave() {
        return this.preferences.getLong(PREFERENCE_ID_AUTOSAVE, SaveRecord.WRONG_ID);
    }

    public void setFirstLaunch(boolean firstLaunch, long autoSaveID) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putBoolean(PREFERENCE_IS_FIRST, false);
        editor.putLong(PREFERENCE_ID_AUTOSAVE, autoSaveID);
        editor.commit();
    }


    public void init(String playerName, Sex sex, Race race) {
        server.initState(playerName, sex, race);
        long id = saveState(getResources().getString(R.string.autosave));
        this.setFirstLaunch(false, id);
    }

    private Event event;

    public void initiateNewDay() {
        server.initiateNewDay();
        event = null;
    }

    public Event getEvent() {
        return event;
    }

    public Event nextEvent() {
        event = server.doStep();
        return event;
    }


    private Server server;
    private Storage storage;
    private DB dataBase;
    private SharedPreferences preferences;
    private GameApplication application;

    public Storage getStorage() {
        return storage;
    }

    public Server getServer() {
        return server;
    }

    public static Server getDefaultServer() {
        return getDefaultGameApplication().getServer();
    }

    public static GameApplication getDefaultGameApplication() {
        return defaultGameApplication;
    }


}
