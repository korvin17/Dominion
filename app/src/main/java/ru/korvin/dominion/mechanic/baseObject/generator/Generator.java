package ru.korvin.dominion.mechanic.baseObject.generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Maid;
import ru.korvin.dominion.mechanic.baseObject.castle.room.simple.Rest;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.npc.person.Alleyne;
import ru.korvin.dominion.mechanic.baseObject.creature.npc.person.Nuliza;
import ru.korvin.dominion.mechanic.baseObject.creature.npc.person.Saber;

public class Generator implements Serializable {
    private Map<Integer, Person> persons;

    public Generator() {
        this.persons = new HashMap<>();
        int increment_girl = 0;
        SABER_ID = increment_girl++;
        ALLEYNE_ID=increment_girl++;
        NULIZA_ID=increment_girl++;


        int increment_room = 0;
        REST_ID = increment_room++;
        MARKET_ID = increment_room++;
        MAID_ID = increment_room++;
        DUNGEON_ROOM_ID = increment_room++;
    }

    public Person getPersonWithID(int id) {
        return persons.get(id);
    }

    public Room[] generateAllRooms() {
        Room[] rooms = new Room[3];
        Room market = new Market(this);
        rooms[market.id] = market;
        Room rest = new Rest(this);
        rooms[rest.id] = rest;
        Room maid = new Maid(this);
        rooms[maid.id] = maid;
        return rooms;
    }

    public List<Person> generateAllSlaves() {
        ArrayList<Person> result = new ArrayList<>(1);
        result.add(getSaber());
        result.add(getAlleyne());
        result.add(getNuliza());
        return result;
    }

    public Person getSaber() {
        Person person = persons.get(SABER_ID);
        if (person == null) {
            person = new Saber(SABER_ID);
            persons.put(SABER_ID, person);
        }
        return person;
    }
    public Person getAlleyne() {
        Person person = persons.get(ALLEYNE_ID);
        if (person == null) {
            person = new Alleyne(ALLEYNE_ID);
            persons.put(ALLEYNE_ID, person);
        }
        return person;
    }
    public Person getNuliza() {
        Person person = persons.get(NULIZA_ID);
        if (person == null) {
            person = new Nuliza(NULIZA_ID);
            persons.put(NULIZA_ID, person);
        }
        return person;
    }

    public final int SABER_ID;
    public final int NULIZA_ID;
    public final int ALLEYNE_ID;

    public final int REST_ID;
    public final int MARKET_ID;
    public final int MAID_ID;
    public final int DUNGEON_ROOM_ID;

}
