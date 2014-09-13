package ru.korvin.dominion.mechanic.baseObject.creature.skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.korvin.dominion.mechanic.baseObject.creature.skill.util.SkillListIterator;
import ru.korvin.dominion.mechanic.baseObject.generator.SkillGenerator;

//TODO сделать на основе мапы id хранить глобально в генераторе а тут просто значения
public class SkillList implements Serializable, Iterable<Skill> {
    protected Map<Integer, Skill> skills;

    public SkillList() {
        this.skills = new TreeMap<>();
    }

    public Skill getSkillWithID(int skillId) {
        Skill skill = skills.get(skillId);
        if (skill == null) {
            skill = SkillGenerator.getSkillWithID(skillId);
            skills.put(skillId, skill);
        }
        return skill;
    }

    public Skill getMaid() {
        return getSkillWithID(SkillGenerator.SKILL_MAID_ID);
    }

    //TODO заменить на нормальную реализацию
    public List<Skill> getActiveSkills() {
        List<Skill> result = new ArrayList<Skill>(1);
        result.add(getMaid());
        return result;
    }

    @Override
    public Iterator<Skill> iterator() {
        return new SkillListIterator(this);
    }

    public void apply(SkillDiff skillDiff) {
        if(skillDiff==null)return;
        for (int skillid : skillDiff.diff.keySet()) {
            int exp = skillDiff.diff.get(skillid);
            Skill skill = getSkillWithID(skillid);
            skill.addExp(exp);
        }
    }
}
