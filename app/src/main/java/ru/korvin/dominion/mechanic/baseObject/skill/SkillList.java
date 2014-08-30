package ru.korvin.dominion.mechanic.baseObject.skill;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.skill.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.skill.util.SkillListIterator;

public class SkillList implements Iterable<Skill> {

    protected Skill maid;

    public SkillList() {
        this.maid = new Maid();
    }

    public Skill getMaid() {
        return maid;
    }

    public List<Skill> getActiveSkills() {
        List<Skill> result = new ArrayList<Skill>(1);
        result.add(maid);
        return result;
    }

    @Override
    public Iterator<Skill> iterator() {
        return new SkillListIterator(this);
    }
}
