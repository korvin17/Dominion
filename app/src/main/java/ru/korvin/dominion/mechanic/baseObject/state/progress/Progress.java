package ru.korvin.dominion.mechanic.baseObject.state.progress;


import ru.korvin.dominion.mechanic.server.event.Event;
import ru.korvin.dominion.mechanic.server.progress.IllegalProgressStateException;
import ru.korvin.dominion.mechanic.server.progress.ProgressState;

public class Progress {

    protected ProgressState state = ProgressState.ready;
    protected boolean needShowActiity = false;
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

/*
    protected static final Progress finishedProgres;
    protected static final Progress readyProgres;

    static {
        finishedProgres = new Progress();
        finishedProgres.state = ProgressState.finished;
        readyProgres = new Progress();
        readyProgres.state = ProgressState.ready;
    }

    public static Progress getFinishedProgress() {
        return finishedProgres;
    }

    public static Progress getReadyProgress() {
        return readyProgres;
    }*/


    public boolean isReady() {
        return state == ProgressState.ready;
    }

    public boolean isExecute() {
        return state == ProgressState.execute;
    }

    public boolean isFinished() {
        return state == ProgressState.ready;
    }

    public boolean isNeedShowActiity() {
        return needShowActiity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.needShowActiity = true;
        this.event = event;
    }
}
