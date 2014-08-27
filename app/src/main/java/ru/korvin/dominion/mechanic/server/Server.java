package ru.korvin.dominion.mechanic.server;


import java.util.List;

import ru.korvin.dominion.mechanic.baseObject.quest.Quest;
import ru.korvin.dominion.mechanic.baseObject.state.State;

public class Server {
    private State state;

    public boolean doStep() {
        List<Quest> quests = state.getQuests();
        for (Quest quest : quests) {
            quest.doStep();
        }
        return true;
    }
}
