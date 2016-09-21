package app.estat.mob.mvp.view.module;

import android.net.Uri;

import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface FarmCardFragmentView extends MvpBaseFragmentView {
    void refreshImage(int imageType, Uri imageUri);

    void showImageProgress(int imageType);
}
