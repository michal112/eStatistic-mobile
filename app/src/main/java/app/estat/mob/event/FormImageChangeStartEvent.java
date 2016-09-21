package app.estat.mob.event;

public class FormImageChangeStartEvent implements Event {
    private final int mImageType;

    public FormImageChangeStartEvent(int imageType) {
        this.mImageType = imageType;
    }

    public int getImageType() {
        return mImageType;
    }
}
