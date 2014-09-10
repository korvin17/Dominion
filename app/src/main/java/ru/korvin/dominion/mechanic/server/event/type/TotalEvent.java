package ru.korvin.dominion.mechanic.server.event.type;

import android.content.Context;
import android.content.Intent;

import ru.korvin.dominion.activity.newday.StatsActivity;
import ru.korvin.dominion.mechanic.server.event.Event;
import ru.korvin.dominion.mechanic.server.event.EventDiff;

public class TotalEvent extends Event {
    public long moneyDiff;
    public long hpDiff;
    public long energyDiff;

    public TotalEvent(EventDiff total) {
        moneyDiff = total.money;
    }

    @Override
    public Intent getIntent(Context context) {
        return new Intent(context, StatsActivity.class);
    }
}
