package ru.korvin.dominion.mechanic.baseObject.state.player;

import ru.korvin.dominion.mechanic.baseObject.creature.Ability;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;

public class Player extends Creature {
    public Player(String name, Sex sex, Race race, long money,
                  int hp, int mp, int energy,
                  int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.sex = sex;
        this.money = money;
        this.race = race;
        this.ability = new Ability(hp, mp, energy, strength, dexterity, constitution, intelligence, wisdom, charisma);
    }
}
