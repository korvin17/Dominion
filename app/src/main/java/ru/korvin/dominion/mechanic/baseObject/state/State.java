package ru.korvin.dominion.mechanic.baseObject.state;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;


public class State implements Serializable {
    private Player player;
    private Castle castel;
    private Calendar date;


    public State(Player player, Castle castle, int year, int month, int day) {
        this.player = player;
        this.castel = castle;
        this.date = GregorianCalendar.getInstance();
        this.date.set(year, month, day);
    }


    /* private List<Event> lastDayEvent;

    private List<Person> girls;
    private List<Room> rooms;


    public Collection<Quest> getQuests() {
        return null;
    }

    public List<Person> getGirls() {
        return girls;
    }

    public List<Room> getRooms() {
        return rooms;
    }*/
    public Player getPlayer() {
        return player;
    }

}
