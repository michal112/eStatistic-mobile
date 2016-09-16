package app.estat.mob.mvp.presenter.dashboard;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.dashboard.DashboardActivityView;

public class DashboardActivityPresenter extends MvpBasePresenter<DashboardActivityView> {
    public DashboardActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
