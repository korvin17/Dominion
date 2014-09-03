package ru.korvin.dominion.mechanic.server;


import java.util.Collection;
import java.util.logging.Logger;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.state.State;
import ru.korvin.dominion.mechanic.baseObject.state.progress.IllegalProgressStateException;

public class Server {
    protected State state;


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void initState(String playerName, Sex sex, Race race) {
        Player player = new Player(playerName, sex, race, GameConst.INITIAL_MONEY,
                GameConst.INITIAL_HP, GameConst.INITIAL_MP, GameConst.INITIAL_ENERGY,
                GameConst.INITIAL_ABILITY, GameConst.INITIAL_ABILITY, GameConst.INITIAL_ABILITY, GameConst.INITIAL_ABILITY, GameConst.INITIAL_ABILITY, GameConst.INITIAL_ABILITY);
        this.state = new State(player, GameConst.INITIAL_YEAR, GameConst.INITIAL_MONTH, GameConst.INITIAL_DAY);
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

    public Room[] getVisibleRooms() {
        return state.getVisibleRooms();
    }

    public Room getRoomWithID(int id) {
        return state.getRoomWithID(id);
    }

    public Person getPersonWithID(int id) {
        return state.getPersonWithID(id);
    }
}
