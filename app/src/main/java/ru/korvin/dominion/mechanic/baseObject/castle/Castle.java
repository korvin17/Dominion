package ru.korvin.dominion.mechanic.baseObject.castle;

import java.io.Serializable;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;

public class Castle implements Serializable {
    private List<Room> rooms;
    private Market market;


    public Market getMarket() {
        return market;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
