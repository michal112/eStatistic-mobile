package app.estat.mob.mvp.presenter.dashboard;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivityPresenter;
import app.estat.mob.mvp.view.dashboard.DashboardActivityView;

public class DashboardActivityPresenter extends MvpBaseActivityPresenter<DashboardActivityView> {
    public DashboardActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
