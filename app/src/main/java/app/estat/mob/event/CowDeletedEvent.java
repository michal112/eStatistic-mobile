package app.estat.mob.event;

public class CowDeletedEvent implements Event {
    private final CowDeletedEvent.Status mStatus;

    public CowDeletedEvent(CowDeletedEvent.Status status) {
        mStatus = status;
    }

    public CowDeletedEvent.Status getStatus() {
        return mStatus;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
