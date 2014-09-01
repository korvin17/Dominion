package ru.korvin.dominion.dao;

import android.content.Context;
import android.content.SharedPreferences;

import ru.korvin.dominion.dao.storage.DB;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.dao.storage.Storage;

public class GameFacade {

    public GameFacade(Context context, SharedPreferences preferences) {
        this.server = new Server();
        this.storage = new Storage();
        this.dataBase = new DB(context);
        this.preferences = preferences;
    }


    private static final String PREFERENCE_IS_FIRST = "is_first";

    public boolean isFirstLaunch() {
        return this.preferences.getBoolean(PREFERENCE_IS_FIRST, true);
    }

    public void setFirstLaunch(boolean firstLaunch) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putBoolean(PREFERENCE_IS_FIRST, false);
        editor.commit();
    }

    public void init(String persname, Sex sex) {


        this.setFirstLaunch(false);
    }

    private Server server;
    private Storage storage;
    private DB dataBase;
    private SharedPreferences preferences;
    public Storage getStorage() {
        return storage;
    }
    public Server getServer() {
        return server;
    }

}
