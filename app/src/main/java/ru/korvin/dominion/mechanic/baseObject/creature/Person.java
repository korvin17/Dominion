package ru.korvin.dominion.mechanic.baseObject.creature;


import java.util.Random;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.Stats;
import ru.korvin.dominion.mechanic.baseObject.creature.stats.StatsDiff;
import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.mechanic.server.event.EventDiff;
import ru.korvin.dominion.mechanic.server.event.EventType;
import ru.korvin.dominion.mechanic.server.progress.PersonProgress;
import ru.korvin.dominion.mechanic.server.progress.Progress;

public class Person extends Creature {
    public int cost = 100;
    public int eatCost = 1;

    public Person(int id, int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        super(id, nameId, sex, race, stats, skills);
    }

    public int getImageMarketSlave() {
        Random random = new Random();
        switch (random.nextInt(2)) {
            case 0:
                return R.drawable.slave_market_2;
            default:
                return R.drawable.slave_market_2;
        }
    }


    public void logWork(EventDiff event) {
        if (Server.getDefault().spendMoney(event)) {
            historyList.addDiff(event);
            this.applyDiff(event);
        }
    }

    public void rest() {
        StatsDiff diff = new StatsDiff();
        diff.energy += 10;
        diff.hp += 10;
        EventDiff event = new EventDiff(EventType.WORK, 0, diff);
        if (Server.getDefault().spendMoney(event)) {
            historyList.addDiff(event);
            this.applyDiff(event);
        }
    }

    public PersonProgress sleep(PersonProgress personProgress) {
        return personProgress;
    }

    public void eat(PersonProgress personProgress) {
        StatsDiff diff = new StatsDiff();
        diff.energy += 10;
        EventDiff event = new EventDiff(EventType.EAT, eatCost, diff);
        if (Server.getDefault().spendMoney(event)) {
            historyList.addDiff(event);
            this.applyDiff(event);
        }
    }


    @Override
    public String toString() {
        return GameApplication.getDefaultGameApplication().getResources().getString(nameId);
    }


    protected PersonProgress doNextDayBegin(PersonProgress progress) {
        progress.nextDayBegin = true;
        eat(progress);
        return progress;
    }

    protected PersonProgress doNextDayEnd(PersonProgress progress) {
        progress = sleep(progress);
        progress.nextDayEnd = true;
        return progress;
    }

    public void initiateNewDay() {

    }

    public final PersonProgress nextDayBegin(PersonProgress progress) {
        if (progress == null)
            progress = new PersonProgress();
        if (progress.nextDayBegin) return progress;
        progress = doNextDayBegin(progress);
        return progress;
    }

    public final PersonProgress nextDayEnd(PersonProgress progress) {
        if (progress == null)
            progress = new PersonProgress();
        if (progress.nextDayEnd) return progress;
        progress = doNextDayEnd(progress);
        return progress;
    }

}
