package ru.korvin.dominion.mechanic.server.event;

import android.content.Context;
import android.content.Intent;

import ru.korvin.dominion.activity.newday.StatsActivity;

public class TotalEvent extends Event {
    public long moneyProfit;

    @Override
    public Intent getIntent(Context context) {
        return new Intent(context, StatsActivity.class);
    }
}
