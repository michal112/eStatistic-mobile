package app.estat.mob.mvp.presenter.module;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;

public class MyCowsFragmentPresenter extends MvpBasePresenter<MyCowsFragmentView> {
    public MyCowsFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
