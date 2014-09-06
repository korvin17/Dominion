package ru.korvin.dominion.mechanic.baseObject.state;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;


public class State implements Serializable {
    private Player player;
    private Castle castle;
    private Calendar date;
    private Generator generator;
    private Rest rest;

    public State(Player player, int year, int month, int day) {
        this.player = player;
        this.generator = new Generator();
        this.castle = new Castle(generator);
        this.date = GregorianCalendar.getInstance();
        this.date.set(year, month, day);
    }

    public Room[] getVisibleRooms() {
        return castle.getRooms();
    }

    public Room[] getWorkRooms() {
        return castle.getWorkRooms();
    }


    public Room getRoomWithID(int id) {
        return castle.getRoomWithID(id);
    }

    public Rest getRest() {
        return this.castle.getRest();
    }

    public Person getPersonWithID(int id) {
        return generator.getPersonWithID(id);
    }

    /* private List<Event> lastDayEvent;

    private List<Person> girls;
    private List<Room> rooms;


    public Collection<Quest> getQuests() {
        return null;
    }*/
    public List<Person> getAllPerson() {
        return null;
    }
    public Player getPlayer() {
        return player;
    }

}
