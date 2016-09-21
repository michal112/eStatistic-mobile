package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;

public class MyCowsFragmentPresenter extends MvpBaseFragmentPresenter<MyCowsFragmentView> {
    public MyCowsFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
