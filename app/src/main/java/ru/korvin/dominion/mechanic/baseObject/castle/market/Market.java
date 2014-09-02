package ru.korvin.dominion.mechanic.baseObject.castle.market;

import java.util.List;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

public class Market extends Room {
    private static final int nameId = R.string.room_market_name;

    public List<Person> getAvailableSlave() {
        return persons;
    }

    @Override
    public int getNameId() {
        return nameId;
    }
}
