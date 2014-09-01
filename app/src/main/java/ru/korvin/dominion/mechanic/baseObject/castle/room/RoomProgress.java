package ru.korvin.dominion.mechanic.baseObject.castle.room;


import java.util.HashMap;
import java.util.Map;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.state.progress.Progress;

public class RoomProgress extends Progress {
    Map<Person, Progress> map;

    public RoomProgress(int size) {
        this.moneyProfit = 0;
        this.map = new HashMap<Person, Progress>(size);
    }

    public void put(Person person, Progress progress) {
        this.map.put(person, progress);
    }

    public Progress get(Person person) {
        return this.map.get(person);
    }

    protected int moneyProfit;

    public void addMoney(int money) {
        moneyProfit += money;
    }

    public int getMoneyProfit() {
        return moneyProfit;
    }
}
