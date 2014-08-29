package ru.korvin.dominion.mechanic.baseObject.creature;

import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.sex.Sex;


public class Creature implements Serializable {
    protected Sex sex;
    protected Race race;

    public Archetype archetype;

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

    public int getAttack() {
        return 10;
    }

    public int getDefence() {
        return 5;
    }

    public int getHeal() {
        return 2;
    }

    public void doDamage(float damage) {
        hp -= damage;
    }

    public int doHeal(int heal) {
        hp += heal;
        if (hp > maxHp) {
            int result = hp - maxHp;
            hp = maxHp;
            return result;
        } else {
            return 0;
        }
    }

    public boolean isDied() {
        return hp <= 0;
    }


    protected int money;
}
