package ru.korvin.dominion.mechanic.baseObject.creature;

import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;

public class Player extends Creature {
    protected String name;

    public Player(String name, Sex sex, Race race, long money,
                  int hp, int mp, int energy,
                  int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        super(0, 0, sex, race, new Stats(hp, mp, energy, strength, dexterity, constitution, intelligence, wisdom, charisma), new SkillList());
        this.name = name;
        this.money = money;
    }
}
