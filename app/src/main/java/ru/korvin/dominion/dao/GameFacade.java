package ru.korvin.dominion.dao;

import android.content.Context;

import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.dao.storage.Storage;

public class GameFacade {

    public GameFacade(Context context) {
        this.storage = new Storage(context);
    }

    private Storage storage;
    public Storage getStorage() {
        return storage;
    }

    private Server server;
    public Server getServer() {
        return server;
    }
}
