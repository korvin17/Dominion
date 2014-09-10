package ru.korvin.dominion.mechanic.baseObject.castle.room.simple;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;
import ru.korvin.dominion.mechanic.server.progress.Progress;

public class Rest extends Room {
    private static final int NAME_ID = R.string.room_rest_name;
    //TODO добавить картинку
    private static final int IMAGE_ID = R.drawable.ic_drawer;

    public Rest(Generator generator) {
        super(generator.REST_ID, LocationType.invisibleRoom);
    }

    @Override
    public Progress doStepForOnePerson(Person person, Progress progress) {
        if (progress.isFinished()) return progress;
        person.rest();
        progress.finish();
        return progress;
    }

    @Override
    public int getNameId() {
        return NAME_ID;
    }

    @Override
    public int getImageId() {
        return IMAGE_ID;
    }
}
