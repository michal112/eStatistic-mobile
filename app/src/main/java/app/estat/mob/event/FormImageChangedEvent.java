package app.estat.mob.event;

import android.net.Uri;

public class FormImageChangedEvent implements Event {
    private final int mImageType;

    private final Uri mImageUri;

    public FormImageChangedEvent(int imageType, Uri imageUri) {
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
