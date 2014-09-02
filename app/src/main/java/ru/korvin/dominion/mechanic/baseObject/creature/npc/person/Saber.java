package ru.korvin.dominion.mechanic.baseObject.creature.npc.person;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Stats;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;

public class Saber extends Person {
    public Saber() {
        super(R.string.creature_Saber_name, Sex.female, Race.Human, new Stats(R.array.creature_Saber_stats), new SkillList());
    }

}
