package ru.korvin.dominion.mechanic.server.progress;


import ru.korvin.dominion.mechanic.server.event.Event;

public class Progress {

    protected ProgressState state = ProgressState.ready;
    protected Event event;

    public void startExecute() throws IllegalProgressStateException {
        if (this.state != ProgressState.ready) {
            throw new IllegalProgressStateException();
        }
        this.state = ProgressState.execute;
    }

    public void finish() throws IllegalProgressStateException {
        if (this.state != ProgressState.execute) {
            throw new IllegalProgressStateException();
        }
        this.state = ProgressState.finished;
    }

    public void end() {
        this.state = ProgressState.finished;
    }

    public boolean isReady() {
        return state == ProgressState.ready;
    }

    public boolean isFinished() {
        return state == ProgressState.ready;
    }

    public boolean isNeedShowActiity() {
        return this.event == null;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
