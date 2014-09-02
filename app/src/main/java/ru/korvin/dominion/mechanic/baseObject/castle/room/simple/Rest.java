package ru.korvin.dominion.mechanic.baseObject.castle.room.simple;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public abstract class Rest extends Room {
    @Override
    public Progress doStepForOnePerson(Person person, Progress progress) {
        return person.Rest();
    }
}
