package ru.korvin.dominion.dao;

import android.app.Application;

public class GameApplication extends Application {
    private GameFacade gameFacade;

    private static final String PREFERENCES = "game preferences";
    @Override
    public void onCreate() {
        super.onCreate();
        gameFacade = new GameFacade(getApplicationContext(), getSharedPreferences(PREFERENCES, MODE_PRIVATE));
    }
    public GameFacade getGameFacade() {
        return gameFacade;
    }
}
