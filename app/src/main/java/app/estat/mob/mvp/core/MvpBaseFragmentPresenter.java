package app.estat.mob.mvp.core;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;

public abstract class MvpBaseFragmentPresenter<V extends MvpBaseFragmentView> extends MvpBasePresenter<V> {
    public MvpBaseFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
