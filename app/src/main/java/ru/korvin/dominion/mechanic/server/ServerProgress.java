package ru.korvin.dominion.mechanic.server;

import java.util.HashMap;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.server.progress.PersonProgress;
import ru.korvin.dominion.mechanic.server.progress.Progress;

public class ServerProgress extends Progress {
    public Map<Room, RoomProgress> roomProgresses;
    public Map<Person, PersonProgress> personProgresses;

    private long moneyProfit;


    public ServerProgress() {
        this.roomProgresses = new HashMap<>();
        this.personProgresses = new HashMap<>();
        this.finishBeginPerson = false;
        this.finishBeginRoom = false;
        this.finishMidleRoom = false;
        this.finishEndRoom = false;
        this.finishEndPerson = false;
        this.finishEnd = false;
    }

    public boolean finishBeginPerson;
    public boolean finishBeginRoom;
    public boolean finishMidleRoom;
    public boolean finishEndRoom;
    public boolean finishEndPerson;
    public boolean finishEnd;


    public long getMoneyProfit() {
        return moneyProfit;
    }
}
