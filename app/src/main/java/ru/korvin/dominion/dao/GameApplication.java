package ru.korvin.dominion.dao;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Calendar;

import ru.korvin.dominion.dao.storage.DB;
import ru.korvin.dominion.dao.storage.Storage;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.server.Server;

public class GameApplication extends Application {
    private static GameApplication defaultGameApplication;
    private static final String PREFERENCES = "game preferences";

    @Override
    public void onCreate() {
        super.onCreate();
        this.server = new Server();
        this.storage = new Storage();
        this.dataBase = new DB(getApplicationContext());
        this.preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        defaultGameApplication = this;
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


    private static final long INITIAL_MONEY = 1000;
    private static final int INITIAL_HP = 100;
    private static final int INITIAL_MP = 100;
    private static final int INITIAL_ENERGY = 100;
    private static final int INITIAL_ABILITY = 10;


    private static final int INITIAL_YEAR = 1308;
    private static final int INITIAL_MONTH = Calendar.NOVEMBER;
    private static final int INITIAL_DAY = 16;


    public void init(String playerName, Sex sex, Race race) {
        Player player = new Player(playerName, sex, race, INITIAL_MONEY,
                INITIAL_HP, INITIAL_MP, INITIAL_ENERGY,
                INITIAL_ABILITY, INITIAL_ABILITY, INITIAL_ABILITY, INITIAL_ABILITY, INITIAL_ABILITY, INITIAL_ABILITY);
        Castle castle = new Castle();
        server.initState(player, castle, INITIAL_YEAR, INITIAL_MONTH, INITIAL_DAY);
        this.setFirstLaunch(false);
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

    public static GameApplication getDefaultGameApplication() {
        return defaultGameApplication;
    }
}
