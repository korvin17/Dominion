package ru.korvin.dominion.mechanic.baseObject.creature.npc.person;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.Stats;

public class Alleyne extends Person {
    public Alleyne(int id) {
        super(id, R.string.creature_Alleyne_name, Sex.female, Race.ELF, new Stats(R.array.creature_Saber_stats), new SkillList());
    }

    @Override
    public int getImageMarketSlave() {
        return R.drawable.girl_alleyne;
    }
}

