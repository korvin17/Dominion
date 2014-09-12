package ru.korvin.dominion.mechanic.server.state;


import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.castle.Castle;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.Player;
import ru.korvin.dominion.mechanic.baseObject.generator.Generator;


public class State implements Serializable {
    private Player player;
    private Castle castle;
    private Calendar date;
    private Generator generator;
    private List<Person> persons;

    public State(Player player, int year, int month, int day) {
        this.player = player;
        this.generator = new Generator();
        this.castle = new Castle(generator);
        this.date = GregorianCalendar.getInstance();
        this.date.set(year, month, day);
        this.persons=new ArrayList<>();
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

    public Market getMarket() {
        return castle.getMarket();
    }

    public Maid getMaid() {
        return castle.getMaid();
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

    public void buyPerson(Person person) {
        getMarket().deletePerson(person);
        getRest().addPerson(person);
        persons.add(person);
    }
    public List<Person> getAllPerson() {
        return persons;
    }

    public Player getPlayer() {
        return player;
    }

    public Generator getGenerator() {
        return generator;
    }

    public List<Person> getVisibleGirls() {
        return persons;
    }
}