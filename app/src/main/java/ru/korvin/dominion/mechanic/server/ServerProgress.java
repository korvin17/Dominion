package ru.korvin.dominion.mechanic.server;

import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.room.Room;
import ru.korvin.dominion.mechanic.baseObject.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class ServerProgress extends Progress {
    public Map<Room, RoomProgress> roomProgresses;

}