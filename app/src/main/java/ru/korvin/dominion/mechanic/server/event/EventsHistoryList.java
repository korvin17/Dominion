package ru.korvin.dominion.mechanic.server.event;

import java.util.LinkedList;
import java.util.List;

//TODO разбить на чать для людей и часть для предметов
public class EventsHistoryList {
    private EventDiff total;
    private List<EventDiff> events;

    public EventsHistoryList() {
        total = new EventDiff(EventType.TOTAL, 0);
        events = new LinkedList<>();
    }

    public EventDiff getTotal() {
        return total;
    }

    //TODO записывать по типам
    public void addDiff(EventDiff eventDiff) {
        events.add(eventDiff);
        total.add(eventDiff);
    }
}
