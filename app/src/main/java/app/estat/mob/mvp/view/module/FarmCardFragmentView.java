package app.estat.mob.mvp.view.module;

import android.net.Uri;

import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface FarmCardFragmentView extends MvpBaseFragmentView {
    void showImage(int imageType, Uri imageUri);

    void setActivityResult(StatusEvent.Status status);
}
