package app.estat.mob.mvp.presenter.module;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;

public class FarmCardFragmentPresenter extends MvpBasePresenter<FarmCardFragmentView> {
    public FarmCardFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
