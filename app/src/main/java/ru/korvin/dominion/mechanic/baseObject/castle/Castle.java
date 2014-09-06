package ru.korvin.dominion.mechanic.baseObject.castle;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;


public class Castle implements Serializable {
    private Generator generator;
    private Room[] rooms;
    private Room[] workRoom;
    private Room[] complexRoom;

    public int dirt;


    public Castle(Generator generator) {
        this.generator = generator;
        this.rooms = generator.generateAllRooms();
        List<Room> w = new LinkedList<>();
        List<Room> c = new LinkedList<>();
        for (Room room : this.rooms) {
            if (room.isVisible() && room.isComplex()) {
                c.add(room);
            }
            if (room.isVisible() && room.isWork()) {
                w.add(room);
            }
            this.workRoom = (Room[]) w.toArray(new Room[w.size()]);
            this.complexRoom = (Room[]) c.toArray(new Room[c.size()]);
        }
    }

    //region getter and setter
    public Market getMarket() {
        return (Market) rooms[generator.MARKET_ID];
    }

    public Rest getRest() {
        return (Rest) rooms[generator.REST_ID];
    }

    public Maid getMaid() {
        return (Maid) rooms[generator.MAID_ID];
    }

    public Room[] getRooms() {
        return complexRoom;
    }

    public Room[] getWorkRooms() {
        return workRoom;
    }


    public Room getRoomWithID(int id) {
        return rooms[id];
    }
    //region getter and setter
}
