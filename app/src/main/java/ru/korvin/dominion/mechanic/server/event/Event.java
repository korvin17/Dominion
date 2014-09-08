package ru.korvin.dominion.mechanic.server.event;


import android.content.Context;
import android.content.Intent;

import ru.korvin.dominion.mechanic.server.Server;

public abstract class Event {
    protected Server server;

    public abstract Intent getIntent(Context context);

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
