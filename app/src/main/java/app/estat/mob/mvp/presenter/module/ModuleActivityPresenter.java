package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivityPresenter;
import app.estat.mob.mvp.view.module.ModuleActivityView;

public abstract class ModuleActivityPresenter<V extends ModuleActivityView> extends MvpBaseActivityPresenter<V> {
    public ModuleActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
