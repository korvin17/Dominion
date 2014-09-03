package ru.korvin.dominion.mechanic.baseObject.creature;


import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Person extends Creature {
    public Person(int id, int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        super(id, nameId, sex, race, stats, skills);
    }

    public Progress Rest() {
        return null;
    }

    @Override
    public String toString() {
        return "test name";
    }
}
