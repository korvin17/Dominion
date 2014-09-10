package ru.korvin.dominion.mechanic.baseObject.castle.room;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.server.progress.IllegalProgressStateException;
import ru.korvin.dominion.mechanic.server.progress.Progress;

public abstract class Room implements Serializable {
    protected List<Person> persons;
    protected LocationType type;

    public RoomProgress doStep(RoomProgress progress) {
        return doStepForEveryOnePerson((RoomProgress) progress);
    }

    protected RoomProgress doStepForEveryOnePerson(RoomProgress currentRoomProgress) {
        RoomProgress resultProgress = new RoomProgress(persons.size());
        //resultProgress.startExecute();
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

    protected Progress doStepForOnePerson(Person person, Progress progress) {
        RoomProgress result = new RoomProgress(0);
        result.end();
        return result;
    }

    public boolean isVisible() {
        return type != LocationType.invisibleRoom;
    }

    public boolean isComplex() {
        return type == LocationType.complexRoom;
    }

    public boolean isWork() {
        return type == LocationType.workRoom;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void deletePerson(Person person) {
        this.persons.remove(person);
    }


    public void initateNewDay() {

    }

    public int id;

    protected Room(int id, LocationType type) {
        this.id = id;
        this.type = type;
        this.persons = new ArrayList<>();
    }

    public abstract int getNameId();

    public abstract int getImageId();


}
