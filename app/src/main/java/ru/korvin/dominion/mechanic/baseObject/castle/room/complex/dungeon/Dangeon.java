package ru.korvin.dominion.mechanic.baseObject.castle.room.complex.dungeon;

import ru.korvin.dominion.mechanic.baseObject.castle.room.LocationType;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;

public abstract class Dangeon extends Room {
    protected int level;

    public Dangeon(int id, LocationType type, int level) {
        super(id, type);
        this.level = level;
    }

    protected void win(RoomProgress progress) {

    }

    protected void lost(RoomProgress progress) {

    }

}
