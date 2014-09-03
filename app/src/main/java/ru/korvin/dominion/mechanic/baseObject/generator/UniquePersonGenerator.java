package ru.korvin.dominion.mechanic.baseObject.generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.npc.person.Saber;

public class UniquePersonGenerator implements Serializable {
    private Map<Integer, Person> persons;

    public UniquePersonGenerator() {
        this.persons = new HashMap<>();
    }

    public Person getPersonWithID(int id) {
        return persons.get(id);
    }

    public List<Person> generateAllSlaves() {
        ArrayList<Person> result = new ArrayList<>(1);
        result.add(getSaber());
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

    private static int increment = 0;
    public static final int SABER_ID = increment++;

}
