package ru.korvin.dominion.mechanic.baseObject.state.progress;


import ru.korvin.dominion.mechanic.baseObject.state.events.Event;

public class Progress {

    protected ProgressState state;
    protected boolean needShowActiity = false;
    protected Event event;

    public void setState(ProgressState state) {
        this.state = state;
    }

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
    }


    public boolean isReady() {
        return state == ProgressState.ready;
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
        this.event = event;
    }
}
