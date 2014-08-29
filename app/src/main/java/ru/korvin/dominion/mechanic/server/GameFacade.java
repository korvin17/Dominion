package ru.korvin.dominion.mechanic.server;

import ru.korvin.dominion.mechanic.storage.Storage;

public class GameFacade {
    private static GameFacade defaultFacade;

    public static GameFacade getDefaultFacade() {
        return defaultFacade;
    }

    private Storage storage;

    public Storage getStorage() {
        return storage;
    }
}
