package ru.korvin.dominion.mechanic.baseObject.state;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.quest.Quest;
import ru.korvin.dominion.mechanic.baseObject.state.events.Event;


public class State implements Serializable {


    private ArrayList<Quest> quests;

    private List<Event> lastDayEvent;


    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }
}
