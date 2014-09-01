package ru.korvin.dominion.mechanic.gameObject.creature.simle.mob;

import ru.korvin.dominion.mechanic.baseObject.creature.Creature;

public class Rat extends Creature {

    public Rat() {
        this.ability.hp = 10;
        this.ability.maxHp = 10;
    }

    @Override
    public int getAttack() {
        return 3;
    }

    @Override
    public int getHeal() {
        return 1;
    }
}
