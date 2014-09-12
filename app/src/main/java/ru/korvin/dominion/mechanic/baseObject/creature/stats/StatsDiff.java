package ru.korvin.dominion.mechanic.baseObject.creature.stats;

import java.io.Serializable;

//сделсь полузахордкожены
public class StatsDiff implements Serializable{
    public int hp;
    public int energy;

    public void add(StatsDiff statsDiff) {
        if (statsDiff == null)
            return;
        ;
        this.hp += statsDiff.hp;
        this.energy += statsDiff.energy;
    }
}
