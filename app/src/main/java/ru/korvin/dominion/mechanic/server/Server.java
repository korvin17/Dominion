package ru.korvin.dominion.mechanic.server;


import java.util.logging.Logger;

import ru.korvin.dominion.mechanic.baseObject.room.Room;
import ru.korvin.dominion.mechanic.baseObject.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.state.State;
import ru.korvin.dominion.mechanic.baseObject.state.progress.IllegalProgressStateException;

public class Server {
    private State state;

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

}
