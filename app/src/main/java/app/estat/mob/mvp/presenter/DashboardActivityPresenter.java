package app.estat.mob.mvp.presenter;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.DashboardActivityView;

public class DashboardActivityPresenter extends MvpBasePresenter<DashboardActivityView> {
    public DashboardActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
