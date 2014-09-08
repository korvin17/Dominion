package ru.korvin.dominion.mechanic.baseObject.creature;


import java.util.Random;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;
import ru.korvin.dominion.mechanic.server.progress.PersonProgress;
import ru.korvin.dominion.mechanic.server.progress.Progress;

public class Person extends Creature {
    public int cost=100;
    public Person(int id, int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        super(id, nameId, sex, race, stats, skills);
    }

    public int getImageMarketSlave(){
        Random random=new Random();
        switch(random.nextInt(2)){
            case 0:
                return R.drawable.slave_market_2;
            default:
                return R.drawable.slave_market_2;
        }
    }




    public Progress Rest() {
        return null;
    }

    public PersonProgress Sleep(PersonProgress personProgress) {
        return personProgress;
    }


    @Override
    public String toString() {
        return GameApplication.getDefaultGameApplication().getResources().getString(nameId);
    }


    protected PersonProgress doNextDayBegin(PersonProgress progress) {
        progress.nextDayBegin = true;
        return progress;
    }

    protected PersonProgress doNextDayEnd(PersonProgress progress) {
        progress = Sleep(progress);
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
