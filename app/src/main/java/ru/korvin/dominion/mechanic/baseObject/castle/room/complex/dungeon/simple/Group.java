package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.simple;

import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.util.Util;

public class Group {

    Creature[] tank = new Creature[0];
    Creature[] dd = new Creature[0];
    Creature[] healers = new Creature[0];


    public int getDamage() {
        int result = 0;
        for (Creature creature : tank) {
            result += getRandomAttack(creature.getAttack()) / 3;
        }
        for (Creature creature : dd) {
            result += getRandomAttack(creature.getAttack());
        }
        return result;
    }

    public int getHeal() {
        int result = 0;
        for (Creature creature : tank) {
            result += getRandomHeal(creature.getHeal() / 3);
        }
        for (Creature creature : healers) {
            result += getRandomHeal(creature.getHeal());
        }
        return result;
    }

    public static final int COUNT_ATTEMT = 3;
    private static final int ZERO = 0;
    private static final int FULL_PERCENT = 100;
    private static final int UNLIMITE = Integer.MAX_VALUE;

    private static final int MIN_ATTACK = -20;
    private static final int MAX_ATTACK_FACTOR = 2;

    private static int getRandomAttack(int maxDamage) {
        return Util.getRandomValue(MIN_ATTACK, MIN_ATTACK, maxDamage * MAX_ATTACK_FACTOR, COUNT_ATTEMT, ZERO, UNLIMITE);
    }

    private static final int MIN_HEAL = -20;
    private static final float MAX_HEAL_FACTOR = 1.5f;

    private static int getRandomHeal(int maxHeal) {
        return Util.getRandomValue(MIN_HEAL, MIN_HEAL, (int) (maxHeal * MAX_HEAL_FACTOR), COUNT_ATTEMT, ZERO, UNLIMITE);
    }

    private static final int TANK_INITIATE = 20;
    private static final int TANK_MIN = -30;
    private static final int TANK_MAX = 100;

    private static float getRandomTankDamagePercent() {
        return Util.getRandomValue(TANK_INITIATE, TANK_MIN, TANK_MAX, COUNT_ATTEMT, ZERO, FULL_PERCENT) / 100f;
    }

    private static float getRandomDDDamagePercent() {
        return Util.getRandomValue(ZERO, ZERO, FULL_PERCENT, COUNT_ATTEMT, ZERO, FULL_PERCENT) / 100f;
    }

    public void doDamage(int damage) {
        float tankPercent = getRandomTankDamagePercent();
        float ddPercent = (1 - tankPercent) * getRandomDDDamagePercent();
        float healPercent = 1 - tankPercent - ddPercent;
        for (Creature creature : tank) {
            creature.doDamage(damage * tankPercent);
        }
        for (Creature creature : dd) {
            creature.doDamage(damage * ddPercent);
        }
        for (Creature creature : healers) {
            creature.doDamage(damage * healPercent);
        }
    }

    public void doHeal(int heal) {
        int ostato_heal = heal;
        for (Creature creature : tank) {
            creature.doHeal(ostato_heal);
        }
        for (Creature creature : dd) {
            creature.doHeal(ostato_heal);
        }
        for (Creature creature : healers) {
            creature.doHeal(ostato_heal);
        }
    }

    public boolean isDied() {
        for (Creature creature : tank) {
            if (!creature.isDied()) return false;
        }
        for (Creature creature : dd) {
            if (!creature.isDied()) return false;
        }
        for (Creature creature : healers) {
            if (!creature.isDied()) return false;
        }
        return true;
    }

    public boolean isAlive() {
        return !this.isDied();
    }
}
