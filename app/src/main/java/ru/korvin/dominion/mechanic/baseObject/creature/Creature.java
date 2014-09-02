package ru.korvin.dominion.mechanic.baseObject.creature;

import java.io.Serializable;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;


public class Creature implements Serializable {
    protected int nameId;
    protected Sex sex;
    protected Race race;
    protected Stats stats;
    protected SkillList skillList;
    public Archetype archetype;
    protected long money;

    public Creature(int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        this.nameId = nameId;
        this.sex = sex;
        this.race = race;
        this.stats = stats;
        this.skillList = skills;
    }

    public int getAttack() {
        return 10;
    }

    public int getDefence() {
        return 5;
    }

    public int getHeal() {
        return 2;
    }

    public void doDamage(float damage) {
        stats.hp -= damage;
    }

    public int doHeal(int heal) {
        stats.hp += heal;
        if (stats.hp > stats.maxHp) {
            int result = stats.hp - stats.maxHp;
            stats.hp = stats.maxHp;
            return result;
        } else {
            return 0;
        }
    }

    public boolean isDied() {
        return stats.hp <= 0;
    }

    public SkillList getSkillList() {
        return skillList;
    }
}
