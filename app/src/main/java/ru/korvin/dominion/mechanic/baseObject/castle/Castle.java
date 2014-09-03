package ru.korvin.dominion.mechanic.baseObject.castle;

import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.castle.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.generator.UniquePersonGenerator;

public class Castle implements Serializable {
    private Room[] rooms;
    private Market market;

    public Market getMarket() {
        return market;
    }

    public Room[] getRooms() {
        return rooms;
    }


    public Castle(UniquePersonGenerator generator) {
        rooms = new Room[1];
        market = new Market(generator);
        market.id = 0;
        rooms[0] = (market);
    }

    public Room getRoomWithID(int id) {
        return rooms[id];
    }
}
