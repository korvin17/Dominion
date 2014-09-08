package ru.korvin.dominion.mechanic.baseObject.creature;

import ru.korvin.dominion.mechanic.server.event.TotalEvent;

public class EventsHistoryList {
    private TotalEvent total;

    public EventsHistoryList() {
        total = new TotalEvent();


    }

    public TotalEvent getTotal() {
        return total;
    }

}
