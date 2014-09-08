package ru.korvin.dominion.mechanic.server;


import java.util.Collection;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.server.event.TotalEvent;
import ru.korvin.dominion.mechanic.server.state.State;
import ru.korvin.dominion.mechanic.server.event.Event;
import ru.korvin.dominion.mechanic.server.progress.PersonProgress;

public class Server {
    protected State state;
    protected ServerProgress serverProgress;

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

    //TODO убрать присвоение прогресса
    public Event doStep() {
        serverProgress.setEvent(null);
        doPersonAtBeginDay();
        if (serverProgress.isNeedShowActiity())
            return serverProgress.getEvent();
        /*if ((progress = doRoomAtBeginDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doRoomNextDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doRoomAtEndDay(progress)).isNeedShowActiity())
            return progress;
        if ((progress = doPersonAtEndDay(progress)).isNeedShowActiity())
            return progress;*/
        //serverProgress.finish();

        return serverProgress.getEvent();
    }

    private ServerProgress doPersonAtBeginDay() {
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

    private ServerProgress doPersonAtEndDay(ServerProgress progress) {
        return progress;
    }

    private ServerProgress doRoomAtBeginDay(ServerProgress progress) {
        return progress;
    }

    private ServerProgress doRoomNextDay(ServerProgress progress) {
        return progress;
    }

    private ServerProgress doRoomAtEndDay(ServerProgress progress) {
        return progress;
    }

    private void doEnd() {
        if (serverProgress.finishEnd)
            return;
        serverProgress.setEvent(getPlayer().getTotal());
        serverProgress.finishEnd = true;
    }

    public ServerProgress initiateNewDay() {
        serverProgress = new ServerProgress();
        for (Person person : state.getAllPerson()) {
            person.initiateNewDay();
        }
        for (Room room : state.getVisibleRooms()) {
            room.initateNewDay();
        }
        for (Room room : state.getWorkRooms()) {
            room.initateNewDay();
        }
        return serverProgress;
    }

    public boolean isFinishedNewDay() {
        return serverProgress.isFinished();
    }



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
    public Market getMarket() {
        return state.getMarket();
    }
    public Maid getMaid(){return state.getMaid();}


    public Person getPersonWithID(int id) {
        return state.getPersonWithID(id);
    }


    public boolean buyPerson(Person person) {
        if(
            state.getPlayer().spendMoney((long) person.cost)) {
            getMarket().deletePerson(person);
            getRest().addPerson(person);
            return true;
        }
        else {
            return false;
        }
    }

    public Player getPlayer() {
        return state.getPlayer();
    }
}
