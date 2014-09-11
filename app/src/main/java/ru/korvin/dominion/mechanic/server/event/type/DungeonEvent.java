package ru.korvin.dominion.mechanic.server.event.type;

import android.content.Context;
import android.content.Intent;

import ru.korvin.dominion.activity.newday.DungeonActivity;
import ru.korvin.dominion.mechanic.server.event.Event;

public class DungeonEvent extends Event {
    @Override
    public Intent getIntent(Context context) {
        return new Intent(context, DungeonActivity.class);
    }
}
