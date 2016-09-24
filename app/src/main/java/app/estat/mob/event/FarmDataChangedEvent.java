package app.estat.mob.event;

public class FarmDataChangedEvent implements Event {
    private final Status mStatus;

    public FarmDataChangedEvent(Status status) {
        mStatus = status;
    }

    public Status getStatus() {
        return mStatus;
    }

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
