package ru.korvin.dominion.mechanic.baseObject.skill.util;

import java.util.Iterator;

import ru.korvin.dominion.mechanic.baseObject.skill.Skill;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;

public class SkillListIterator implements Iterator<Skill> {
    private SkillList skillList;
    private int index;


    private static final int INDEX_MAID = 0;

    public SkillListIterator(SkillList skillList) {
        this.skillList = skillList;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < 1;
    }

    @Override
    public Skill next() {
        switch (index) {
            case INDEX_MAID:
                skillList.getMaid();
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
