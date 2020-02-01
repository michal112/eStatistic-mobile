package app.estat.mob.mvp.core;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;

public abstract class MvpBaseActivityPresenter<V extends MvpBaseActivityView> extends MvpBasePresenter<V> {
    public MvpBaseActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
