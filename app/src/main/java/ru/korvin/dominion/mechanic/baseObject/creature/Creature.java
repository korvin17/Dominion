package ru.korvin.dominion.mechanic.baseObject.creature;

import android.graphics.Point;

import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon.Group;
import ru.korvin.dominion.mechanic.baseObject.creature.battle.TacticType;
import ru.korvin.dominion.mechanic.baseObject.creature.gameClass.Archetype;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.Stats;
import ru.korvin.dominion.mechanic.server.GameUtil;
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
    protected EventsHistoryList historyListOld;
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

    //region battle
    private transient TacticType tacticType = TacticType.REST;
    private transient Point nextTarget;

    public void selectNextTarget(Group targets, int distance) {
        switch (distance) {
            case 0:
                selectNextTargetClose(targets);
            case 1:
                selectNextTargetMidle(targets);
            case 2:
                selectNextTargetRange(targets);
            default:
                selectNextTargetVeryLongRange();

        }
    }

    public boolean isClose() {
        return true;
    }

    public boolean isMid() {
        return GameUtil.random.nextBoolean();
    }

    public boolean isRange() {
        return GameUtil.dice1D4() == 1;
    }

    private void selectNextTargetClose(Group targets) {
        if (isClose()) {
            this.nextTarget = GameUtil.selectTargetinRows(targets, 1);
            if (nextTarget != null) {
                tacticType = TacticType.CLOSE;
            } else {
                selectInvalid();
            }

        } else {
            selectInvalid();
        }
    }

    private void selectNextTargetMidle(Group targets) {
        if (isMid()) {
            this.nextTarget = GameUtil.selectTargetinRows(targets, 2);
            if (nextTarget != null) {
                tacticType = TacticType.RANGE;
            } else {
                selectInvalid();
            }

        } else {
            selectInvalid();
        }
    }

    private void selectNextTargetRange(Group targets) {
        if (isMid()) {
            this.nextTarget = GameUtil.selectTargetinRows(targets, 1);
            if (nextTarget != null) {
                tacticType = TacticType.RANGE;
            } else {
                selectInvalid();
            }

        } else {
            selectInvalid();
        }
    }

    private void selectNextTargetVeryLongRange() {
        selectInvalid();
    }

    private void selectInvalid() {
        tacticType = TacticType.REST;
        nextTarget = null;
    }

    public void fate(Group targets) {
        if (isDied()) return;
        Creature target = targets.get(nextTarget);
        if (target != null)
            fate(target);
    }

    public void fate(Creature target) {
        int damage = getAttack();
        target.doDamage(damage);
    }
    //region battle
}
