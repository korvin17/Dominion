package ru.korvin.dominion.mechanic.server;

import java.util.Calendar;

public class GameConst {
    public static final int STR_HP_FACTOR = 10;
    public static final int DEX_ENERGY_FACTOR = 10;
    public static final int WIS_MP_FACTOR = 10;

    public static final long INITIAL_MONEY = 1000;
    public static final int INITIAL_HP = 100;
    public static final int INITIAL_MP = 100;
    public static final int INITIAL_ENERGY = 100;
    public static final int INITIAL_ABILITY = 10;
    public static final int INITIAL_YEAR = 1308;
    public static final int INITIAL_MONTH = Calendar.NOVEMBER;
    public static final int INITIAL_DAY = 16;


    public static final int MAX_MOB_IN_ROW = 5;
    public static final double DUNGEON_REWARD_MIN = 100;
    public static final double DUNGEON_REWARD_POW_FACTOR = 1.5;
    public static final int INVALID_TARGET = Integer.MIN_VALUE;

    public static final String ARG_SLAVE_ID = "slave_id";
}
