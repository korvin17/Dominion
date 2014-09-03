package ru.korvin.dominion.mechanic.baseObject.state;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.generator.UniquePersonGenerator;


public class State implements Serializable {
    private Player player;
    private Castle castle;
    private Calendar date;
    private UniquePersonGenerator generator;

    public State(Player player, int year, int month, int day) {
        this.player = player;
        this.generator = new UniquePersonGenerator();
        this.castle = new Castle(generator);
        this.date = GregorianCalendar.getInstance();
        this.date.set(year, month, day);
    }

    public Room[] getVisibleRooms() {
        return castle.getRooms();
    }

    public Room getRoomWithID(int id) {
        return castle.getRoomWithID(id);
    }

    public Person getPersonWithID(int id) {
        return generator.getPersonWithID(id);
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
