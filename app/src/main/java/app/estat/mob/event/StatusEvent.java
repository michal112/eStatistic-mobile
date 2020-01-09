package app.estat.mob.event;

public abstract class StatusEvent {
    private final StatusEvent.Status mStatus;

    public StatusEvent(StatusEvent.Status status) {
        mStatus = status;
    }

    public StatusEvent.Status getStatus() {
        return mStatus;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
