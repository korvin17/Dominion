package ru.korvin.dominion.mechanic.baseObject.creature;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.server.GameConst;

public class Stats {
    public int hp;
    public int maxHp;

    public int mp;
    public int maxMp;

    public int energy;
    public int maxEnergy;

    public int basic_strength;//(STR) – Сила
    public int basic_dexterity;//(DEX) – Ловкость
    public int basic_constitution;//(CON) – Телосложение
    public int basic_intelligence;//(INT) – Интеллект
    public int basic_wisdom;// (WIS) – Мудрость
    public int basic_charisma;//(CHA) – Харизма

    public Stats(int idStats) {
        int[] stats = GameApplication.getDefaultGameApplication().getResources().getIntArray(idStats);
        if (stats.length != 6) throw new RuntimeException();
        this.basic_strength = stats[0];
        this.basic_dexterity = stats[1];
        this.basic_constitution = stats[2];
        this.basic_intelligence = stats[3];
        this.basic_wisdom = stats[4];
        this.basic_charisma = stats[5];
        this.maxHp = this.basic_constitution * GameConst.STR_HP_FACTOR;
        this.maxEnergy = this.basic_dexterity * GameConst.DEX_ENERGY_FACTOR;
        this.maxMp = this.basic_wisdom * GameConst.WIS_MP_FACTOR;
    }

    public Stats(int maxHp, int maxMp, int maxEnergy, int basic_strength, int basic_dexterity, int basic_constitution, int basic_intelligence, int basic_wisdom, int basic_charisma) {
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.maxEnergy = maxEnergy;
        this.basic_strength = basic_strength;
        this.basic_dexterity = basic_dexterity;
        this.basic_constitution = basic_constitution;
        this.basic_intelligence = basic_intelligence;
        this.basic_wisdom = basic_wisdom;
        this.basic_charisma = basic_charisma;
        this.hp = this.maxHp;
        this.mp = this.maxMp;
        this.energy = this.maxEnergy;
    }
}
