package app.estat.mob.mvp.presenter.action;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivityPresenter;
import app.estat.mob.mvp.view.action.ActionActivityView;

public abstract class ActionActivityPresenter<V extends ActionActivityView> extends MvpBaseActivityPresenter<V> {
    public ActionActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
