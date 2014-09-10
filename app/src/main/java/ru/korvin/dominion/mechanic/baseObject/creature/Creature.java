package ru.korvin.dominion.mechanic.baseObject.creature;

import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.Stats;
import ru.korvin.dominion.mechanic.server.event.EventDiff;
import ru.korvin.dominion.mechanic.server.event.EventsHistoryList;

//TODO переделать на generic
public class Creature implements Serializable {
    protected int nameId;
    protected int id;
    protected Sex sex;
    protected Race race;
    protected Stats stats;
    protected SkillList skillList;
    public Archetype archetype;
    protected long money;
    protected EventsHistoryList historyList;

    public Creature(int id, int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        this.id = id;
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

    public int getNameId() {
        return nameId;
    }

    public int getId() {
        return id;
    }

    public Sex getSex() {
        return sex;
    }

    public Race getRace() {
        return race;
    }

    public long getMoney() {
        return money;
    }

    public Stats getStats() {
        return stats;
    }

    public SkillList getSkillList() {
        return skillList;
    }

    public boolean spendMoney(EventDiff diff) {
        if (this.money > diff.money) {
            money -= diff.money;
            historyList.addDiff(new EventDiff(diff.type, diff.money));
            return true;
        } else {
            return false;
        }
    }

    public boolean applyDiff(EventDiff diff) {
        // this.stats

        this.skillList.apply(diff.skillDiff);
        return false;
    }
}
