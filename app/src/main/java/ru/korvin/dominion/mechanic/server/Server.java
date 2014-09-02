package ru.korvin.dominion.mechanic.server;


import java.util.Collection;
import java.util.logging.Logger;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.state.State;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.state.progress.IllegalProgressStateException;

public class Server {
    protected State state;


    public State getState() {
        return state;
    }

    public void initState(Player player, Castle castle, int year, int month, int day) {
        this.state = new State(player, castle, year, month, day);


    }


    public ServerProgress doStep(ServerProgress progress) throws IllegalProgressStateException {
        if (progress.isReady()) {
            initiateNewDay(progress);
            progress.startExecute();
        }
        boolean finished = true;
        for (Room room : progress.roomProgresses.keySet()) {
            RoomProgress roomProgress = progress.roomProgresses.get(room);
            if (roomProgress.isReady() || roomProgress.isExecute()) {
                RoomProgress newProgress = room.doStep(roomProgress);
                if (!newProgress.isFinished()) finished = false;
                progress.roomProgresses.put(room, newProgress);
            }
        }
        if (finished)
            progress.finish();
        return progress;
    }

    public ServerProgress doStepSafe(ServerProgress progress) {
        try {
            return this.doStep(progress);
        } catch (IllegalProgressStateException exception) {
            //  PrintWriter writer;
            //  exception.printStackTrace(writer);
            //  logger.log();
        }
        progress.end();
        return progress;
    }

    private void initiateNewDay(ServerProgress progress) {

    }


    private static final String LOGGER_NAME = "game server";
    Logger logger = Logger.getLogger(LOGGER_NAME);


    public Collection<Person> getVisibleGirls() {
        return null;
        //     return state.getGirls();
    }


}
