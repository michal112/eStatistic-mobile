package app.estat.mob.mvp.core;

import app.estat.mob.component.ApplicationComponent;

public abstract class MvpBaseFragmentPresenter<V extends MvpBaseFragmentView> extends MvpBasePresenter<V> {
    public MvpBaseFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
