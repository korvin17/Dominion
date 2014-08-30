package ru.korvin.dominion.mechanic.baseObject.state;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.quest.Quest;
import ru.korvin.dominion.mechanic.baseObject.room.Room;
import ru.korvin.dominion.mechanic.baseObject.state.events.Event;
import ru.korvin.dominion.mechanic.baseObject.state.player.Player;


public class State implements Serializable {
    private List<Event> lastDayEvent;

    private List<Person> girls;
    private List<Room> rooms;
    private Player player;


    public Collection<Quest> getQuests() {
        return null;
    }

    public List<Person> getGirls() {
        return girls;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
