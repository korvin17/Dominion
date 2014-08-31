package ru.korvin.dominion.dao;

import android.content.Context;
import android.content.SharedPreferences;

import ru.korvin.dominion.dao.storage.DB;
import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.dao.storage.Storage;

public class GameFacade {

    public GameFacade(Context context, SharedPreferences preferences) {
        this.server = new Server();
        this.storage = new Storage();
        this.dataBase = new DB(context);
        this.preferences = preferences;
    }

    public boolean init() {
        //this.preferences.getBoolean();
        return false;
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
