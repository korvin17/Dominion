package ru.korvin.dominion.mechanic.baseObject.generator;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Creature;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.Stats;

public class CreatureGenerator {
    public static int incrementId=0;
    public static Creature getRat(int level){
        int id=incrementId++;
        int nameId= R.string.creature_rat_name;
        Stats stats;
        switch (level){
            case 1:
                stats=new Stats(R.array.creature_rat_1_stats);
            case 2:
                stats=new Stats(R.array.creature_rat_2_stats);
            case 3:
                stats=new Stats(R.array.creature_rat_3_stats);
            case 4:
                stats=new Stats(R.array.creature_rat_4_stats);
            case 5:
                stats=new Stats(R.array.creature_rat_5_stats);
            default:
                stats=new Stats(R.array.creature_rat_5_stats);
        }
        SkillList skills=new SkillList();
        return new Creature(id,nameId, Sex.male, Race.RAT,stats,skills);
    }
}
