package app.estat.mob.event;

public class BullSavedEvent implements Event {
    private final BullSavedEvent.Status mStatus;

    public BullSavedEvent(BullSavedEvent.Status status) {
        mStatus = status;
    }

    public BullSavedEvent.Status getStatus() {
        return mStatus;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
