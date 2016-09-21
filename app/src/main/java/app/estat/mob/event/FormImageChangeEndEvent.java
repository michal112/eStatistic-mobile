package app.estat.mob.event;

import android.net.Uri;

public class FormImageChangeEndEvent implements Event {
    private final int mImageType;

    private final Uri mImageUri;

    public FormImageChangeEndEvent(int imageType, Uri imageUri) {
        this.mImageType = imageType;
        this.mImageUri = imageUri;
    }

    public int getImageType() {
        return mImageType;
    }

    public Uri getImageUri() {
        return mImageUri;
    }
}
