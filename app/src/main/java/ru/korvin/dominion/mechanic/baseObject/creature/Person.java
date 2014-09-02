package ru.korvin.dominion.mechanic.baseObject.creature;


import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Person extends Creature implements Serializable {
    public Person(int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        super(nameId, sex, race, stats, skills);
    }

    public Progress Rest() {
        return null;
    }


}
