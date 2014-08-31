package ru.korvin.dominion.dao;

import android.app.Application;

public class GameApplication extends Application {
    private GameFacade gameFacade;


    @Override
    public void onCreate() {
        super.onCreate();


    }

    public GameFacade getGameFacade() {
        return gameFacade;
    }
}
