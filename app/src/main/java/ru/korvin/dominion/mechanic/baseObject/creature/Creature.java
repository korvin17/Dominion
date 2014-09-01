package ru.korvin.dominion.mechanic.baseObject.creature;

import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;


public class Creature implements Serializable {
    protected String name;
    protected Sex sex;
    protected Race race;
    protected Ability ability;
    protected SkillList skillList;
    public Archetype archetype;
    protected long money;


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
        ability.hp -= damage;
    }

    public int doHeal(int heal) {
        ability.hp += heal;
        if (ability.hp > ability.maxHp) {
            int result = ability.hp - ability.maxHp;
            ability.hp = ability.maxHp;
            return result;
        } else {
            return 0;
        }
    }

    public boolean isDied() {
        return ability.hp <= 0;
    }

    public SkillList getSkillList() {
        return skillList;
    }
}
