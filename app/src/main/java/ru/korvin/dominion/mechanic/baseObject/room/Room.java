package ru.korvin.dominion.mechanic.baseObject.room;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.state.progress.IllegalProgressStateException;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;
import ru.korvin.dominion.mechanic.baseObject.state.progress.ProgressState;

public class Room implements Serializable {
    protected List<Person> persons = new ArrayList<Person>();

    public RoomProgress doStep(RoomProgress progress) throws IllegalProgressStateException {
        return doStepForEveryOnePerson((RoomProgress) progress);
    }

    public RoomProgress doStepForEveryOnePerson(RoomProgress currentRoomProgress) throws IllegalProgressStateException {
        RoomProgress resultProgress = new RoomProgress(persons.size());
        resultProgress.startExecute();
        boolean finished = true;
        for (Person person : persons) {
            Progress currentPersonProgress = currentRoomProgress.isReady() ? new RoomProgress(persons.size()) : (RoomProgress) currentRoomProgress.get(person);
            Progress newPersonProgress = doStepForOnePerson(person, currentPersonProgress);
            resultProgress.put(person, newPersonProgress);
            if (!newPersonProgress.isFinished()) {
                finished = false;
            }
        }
        if (finished)
            resultProgress.finish();
        return resultProgress;
    }

    public Progress doStepForOnePerson(Person person, Progress progress) {
        RoomProgress result = new RoomProgress(0);
        result.end();
        return result;
    }

}
