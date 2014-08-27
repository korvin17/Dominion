package ru.korvin.dominion.mechanic.baseObject.quest;

import java.io.Serializable;

public abstract class Quest implements Serializable {
    public abstract boolean doStep();
}
