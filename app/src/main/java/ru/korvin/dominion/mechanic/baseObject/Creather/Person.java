package ru.korvin.dominion.mechanic.baseObject.Creather;


import java.io.Serializable;

import ru.korvin.dominion.mechanic.baseObject.Creather.race.Race;
import ru.korvin.dominion.mechanic.baseObject.Creather.sex.Sex;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class Person implements Serializable {
    private Sex sex;
    private Race race;

    public Progress Rest() {
        return null;
    }
}
