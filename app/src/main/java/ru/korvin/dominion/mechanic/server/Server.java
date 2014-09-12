package ru.korvin.dominion.mechanic.server;


import java.util.Collection;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.RoomProgress;
import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Race;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;
import ru.korvin.dominion.mechanic.server.event.EventDiff;
import ru.korvin.dominion.mechanic.server.event.EventType;
import ru.korvin.dominion.mechanic.server.event.type.TotalEvent;
import ru.korvin.dominion.mechanic.server.state.State;
import ru.korvin.dominion.mechanic.server.event.Event;
import ru.korvin.dominion.mechanic.server.progress.PersonProgress;

//TODO везде перекинуть на дефалт
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
        initPerson();
    }

    //TODO убрать присвоение прогресса
    public Event doStep() {
        serverProgress.setEvent(null);
        doPersonAtBeginDay();
        if (serverProgress.isNeedShowActivity())
            return serverProgress.getEvent();

        doRoomNextDay();
        if (serverProgress.isNeedShowActivity())
            return serverProgress.getEvent();

        /*if ((progress = doRoomAtBeginDay(progress)).isNeedShowActivity())
            return progress;
        if ((progress = doRoomNextDay(progress)).isNeedShowActivity())
            return progress;
        if ((progress = doRoomAtEndDay(progress)).isNeedShowActivity())
            return progress;
        if ((progress = doPersonAtEndDay(progress)).isNeedShowActivity())
            return progress;*/
        //serverProgress.finish();
        doEnd();
        return serverProgress.getEvent();
    }

    private void doPersonAtBeginDay() {
        if (serverProgress.finishBeginPerson) return;
        for (Person person : serverProgress.personProgresses.keySet()) {
            PersonProgress personProgress = serverProgress.personProgresses.get(person);
            personProgress = person.nextDayBegin(personProgress);
            serverProgress.personProgresses.put(person, personProgress);
            if (personProgress.isNeedShowActivity())
                serverProgress.setEvent(personProgress.getEvent());
            return;
        }
        serverProgress.finishBeginPerson = true;
        return;
    }

    private ServerProgress doPersonAtEndDay(ServerProgress progress) {
        return progress;
    }

    private ServerProgress doRoomAtBeginDay(ServerProgress progress) {
        return progress;
    }

    private void doRoomNextDay() {
        if (serverProgress.finishMidleRoom) return;
        for (Room room : serverProgress.roomProgresses.keySet()) {
            RoomProgress roomProgress = serverProgress.roomProgresses.get(room);
            roomProgress = room.doStep(roomProgress);
            serverProgress.roomProgresses.put(room, roomProgress);
            if (roomProgress.isNeedShowActivity())
                serverProgress.setEvent(roomProgress.getEvent());
            return;
        }
        serverProgress.finishMidleRoom = true;
        return;
    }

    private ServerProgress doRoomAtEndDay(ServerProgress progress) {
        return progress;
    }

    private void doEnd() {
        if (serverProgress.finishEnd)
            return;
        serverProgress.setEvent(new TotalEvent(getPlayer().getTotal()));
        serverProgress.finishEnd = true;
    }
    public void initPerson(){
        for (Person person : state.getAllPerson()) {
            person.initiateNewDay();
        }
        for (Room room : state.getVisibleRooms()) {
            room.initateNewDay();
        }
        for (Room room : state.getWorkRooms()) {
            room.initateNewDay();
        }
        getPlayer().initNextDay();
    }

    public ServerProgress initiateNewDay() {
        serverProgress = new ServerProgress(state.getVisibleRooms(), state.getAllPerson());
        initPerson();
        return serverProgress;
    }

    public boolean isFinishedNewDay() {
        return serverProgress.isFinished();
    }


    public List<Person> getVisibleGirls() {
        return state.getVisibleGirls();
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

    public Maid getMaid() {
        return state.getMaid();
    }


    public Person getPersonWithID(int id) {
        return state.getPersonWithID(id);
    }


    public boolean buyPerson(Person person) {
        if (state.getPlayer().spendMoney(new EventDiff(EventType.BUY, person.cost))) {
            state.buyPerson(person);
            return true;
        } else {
            return false;
        }
    }

    public Player getPlayer() {
        return state.getPlayer();
    }

    private static final Server defaultServer = new Server();

    public static Server getDefault() {
        return defaultServer;
    }

    public boolean spendMoney(EventDiff event) {
        return getPlayer().spendMoney(event);
    }
}
