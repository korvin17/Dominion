package ru.korvin.dominion.mechanic.server.event;


import android.content.Context;
import android.content.Intent;

import ru.korvin.dominion.mechanic.server.Server;

public abstract class Event {

    public abstract Intent getIntent(Context context);
}
