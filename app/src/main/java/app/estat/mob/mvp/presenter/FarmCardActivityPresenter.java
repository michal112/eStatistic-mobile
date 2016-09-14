package app.estat.mob.mvp.presenter;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.FarmCardActivityView;

public class FarmCardActivityPresenter extends MvpBasePresenter<FarmCardActivityView> {
    public FarmCardActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
