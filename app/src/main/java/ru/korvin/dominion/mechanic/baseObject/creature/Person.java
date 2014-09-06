package ru.korvin.dominion.mechanic.baseObject.creature;


import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.skill.SkillList;
import ru.korvin.dominion.mechanic.baseObject.state.progress.PersonProgress;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Person extends Creature {
    public Person(int id, int nameId, Sex sex, Race race, Stats stats, SkillList skills) {
        super(id, nameId, sex, race, stats, skills);
    }

    public Progress Rest() {
        return null;
    }

    public PersonProgress Sleep(PersonProgress personProgress) {
        return personProgress;
    }


    @Override
    public String toString() {
        return "test name";
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

    public void initateNewDay() {

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
