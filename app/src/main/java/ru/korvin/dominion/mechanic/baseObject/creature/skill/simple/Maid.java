package ru.korvin.dominion.mechanic.baseObject.creature.skill.simple;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.Skill;

public class Maid extends Skill {
    private static final int id_name = R.string.game_skill_maid;

    @Override
    public int getNameId() {
        return id_name;
    }
}
