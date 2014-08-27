package ru.korvin.dominion.mechanic.baseObject.room.simple;

import ru.korvin.dominion.mechanic.baseObject.Creather.Person;
import ru.korvin.dominion.mechanic.baseObject.room.Room;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Rest extends Room {
    @Override
    public Progress doStepForOnePerson(Person person, Progress progress) {
        return person.Rest();
    }
}
