package app.estat.mob.event;

public class CowSavedEvent implements Event {
    private final CowSavedEvent.Status mStatus;

    public CowSavedEvent(CowSavedEvent.Status status) {
        mStatus = status;
    }

    public CowSavedEvent.Status getStatus() {
        return mStatus;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
