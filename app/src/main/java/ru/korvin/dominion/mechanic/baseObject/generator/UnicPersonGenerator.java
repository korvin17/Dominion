package ru.korvin.dominion.mechanic.baseObject.generator;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.npc.person.Saber;

public class UnicPersonGenerator {
    public Person generateSaber() {
        Person person = new Saber();
        return person;
    }
}
