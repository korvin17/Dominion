package ru.korvin.dominion.mechanic.baseObject.creature.skill;

import java.util.HashMap;
import java.util.Map;

public class SkillDiff {
    public Map<Integer, Integer> diff = new HashMap<>();

    public void addExp(int skillId, int exp) {
        Integer oldExp = diff.get(skillId);
        if (oldExp == null) {
            diff.put(skillId, exp);
        } else {
            diff.put(skillId, exp + oldExp);
        }
    }

    public void add(SkillDiff skillDiff) {
        for (Integer skillId : skillDiff.diff.keySet()) {
            addExp(skillId, skillDiff.diff.get(skillId));
        }
    }
}
