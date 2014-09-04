package ru.korvin.dominion.mechanic.baseObject.castle.room.market;

import java.util.List;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;

public class Market extends Room {
    private static final int nameId = R.string.room_market_name;
    private static final int imageId = R.drawable.slave_market_1;

    public List<Person> getAvailableSlave() {
        return persons;
    }

    @Override
    public int getNameId() {
        return nameId;
    }

    @Override
    public int getImageId() {
        return imageId;
    }

    public Market(Generator generator) {
        super(generator.MARKET_ID, LocationType.complexRoom);
        this.persons = generator.generateAllSlaves();
    }
}
