package app.estat.mob.mvp.presenter;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.MyCowsFragmentView;

public class MyCowsFragmentPresenter extends MvpBasePresenter<MyCowsFragmentView> {
    public MyCowsFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
