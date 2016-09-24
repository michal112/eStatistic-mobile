package app.estat.mob.mvp.core;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public interface MvpBaseActivityView extends MvpView {
    void addFragment(int container, Fragment fragment, boolean addToBackStack);

    void showMessage(@StringRes int resId);
}
