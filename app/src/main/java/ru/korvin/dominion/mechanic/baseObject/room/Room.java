package ru.korvin.dominion.mechanic.baseObject.room;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.Creather.Person;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;
import ru.korvin.dominion.mechanic.baseObject.state.progress.ProgressState;

public class Room implements Serializable {
    protected List<Person> persons = new ArrayList<Person>();

    public Progress doStep(Progress progress) {
        return doStepForEveryOnePerson((RoomProgress) progress);
    }

    public RoomProgress doStepForEveryOnePerson(RoomProgress currentRoomProgress) {
        RoomProgress resultProgress = new RoomProgress(persons.size());
        boolean finished = true;
        for (Person person : persons) {
            Progress currentPersonProgress = currentRoomProgress.isReady() ? Progress.getReadyProgress() : (RoomProgress) currentRoomProgress.get(person);
            Progress newPersonProgress = doStepForOnePerson(person, currentPersonProgress);
            resultProgress.put(person, newPersonProgress);
            if (!newPersonProgress.isFinished()) {
                finished = false;
            }
        }
        resultProgress.setState(finished ? ProgressState.finished : ProgressState.execute);
        return resultProgress;
    }

    public Progress doStepForOnePerson(Person person, Progress progress) {
        return Progress.getFinishedProgress();
    }

}
