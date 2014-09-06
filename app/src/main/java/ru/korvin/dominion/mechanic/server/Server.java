package ru.korvin.dominion.mechanic.server;


import java.util.Collection;
import java.util.logging.Logger;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.baseObject.state.State;
import ru.korvin.dominion.mechanic.baseObject.state.progress.IllegalProgressStateException;
import ru.korvin.dominion.mechanic.baseObject.state.progress.PersonProgress;

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
        if (progress == null) {
            progress = new ServerProgress();
            initiateNewDay(progress);
        }
        if ((progress = doPersonAtBeginDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doRoomAtBeginDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doRoomNextDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doRoomAtEndDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doPersonAtEndDay(progress)).isNeedShowActiity())
            return progress;
        progress.finish();
        return progress;
    }

    public ServerProgress doPersonAtBeginDay(ServerProgress serverProgress) {
        if (serverProgress.finishBeginPerson) return serverProgress;
        for (Person person : serverProgress.personProgresses.keySet()) {
            PersonProgress personProgress = serverProgress.personProgresses.get(person);
            personProgress = person.nextDayBegin(personProgress);
            serverProgress.personProgresses.put(person, personProgress);
            if (personProgress.isNeedShowActiity())
                serverProgress.setEvent(personProgress.getEvent());
            return serverProgress;
        }
        serverProgress.finishBeginPerson = true;
        return serverProgress;
    }

    public ServerProgress doPersonAtEndDay(ServerProgress progress) {
        return progress;
    }

    public ServerProgress doRoomAtBeginDay(ServerProgress progress) {
        return progress;
    }

    public ServerProgress doRoomNextDay(ServerProgress progress) {
        return progress;
    }

    public ServerProgress doRoomAtEndDay(ServerProgress progress) {
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
        for (Person person : state.getAllPerson()) {
            person.initateNewDay();
        }
        for (Room room : state.getVisibleRooms()) {
            room.initateNewDay();
        }
        for (Room room : state.getWorkRooms()) {
            room.initateNewDay();
        }
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

    public Room[] getWorkRooms() {
        return state.getWorkRooms();
    }

    public Room getRoomWithID(int id) {
        return state.getRoomWithID(id);
    }

    public Rest getRest() {
        return state.getRest();
    }

    public Person getPersonWithID(int id) {
        return state.getPersonWithID(id);
    }
}
