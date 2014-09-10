package ru.korvin.dominion.mechanic.baseObject.creature.skill;

import ru.korvin.dominion.mechanic.baseObject.creature.skill.simple.Maid;

public class SkillGenerator {
    //TODO перефигачить на основе enum
    public static final int SKILL_MAID_ID = 0;

    public static Skill getSkillWithID(int skillId) {
        switch (skillId) {
            case SKILL_MAID_ID:
                return new Maid();
            default:
                return null;
        }
    }
}
