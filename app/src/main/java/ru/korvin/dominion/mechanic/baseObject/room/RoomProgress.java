package ru.korvin.dominion.mechanic.baseObject.room;


import java.util.HashMap;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.Creather.Person;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class RoomProgress extends Progress {
    Map<Person, Progress> map;

    public RoomProgress(int size) {
        this.map = new HashMap<Person, Progress>(size);
    }

    public void put(Person person, Progress progress) {
        this.map.put(person, progress);
    }

    public Progress get(Person person) {
        return this.map.get(person);
    }
}
