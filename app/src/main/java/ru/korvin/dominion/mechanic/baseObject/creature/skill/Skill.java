package ru.korvin.dominion.mechanic.baseObject.creature.skill;

import java.io.Serializable;

public abstract class Skill implements Serializable {
    public abstract int getNameId();

    protected int level;
    protected long exp;
    protected int id;

    public void addExp(int exp) {
        this.exp += exp;
        int nextLevel = expForNextLevel();
        while (this.exp > nextLevel) {
            this.exp -= nextLevel;
            this.level++;
            nextLevel = expForNextLevel();
        }
    }

    ;

    public int expForNextLevel() {
        return (int) Math.pow(2, level);
    }
}
