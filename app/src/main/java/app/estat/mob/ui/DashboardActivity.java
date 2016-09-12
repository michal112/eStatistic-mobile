package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.DashboardActivityPresenter;
import app.estat.mob.mvp.view.DashboardActivityView;

public class DashboardActivity extends MvpBaseActivity<DashboardActivityPresenter, DashboardActivityView>
        implements DashboardActivityView {

    public static void newIntent(@NonNull Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_dashboard;
    }

    @NonNull
    @Override
    public DashboardActivityPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new DashboardActivityPresenter(applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_dashboard_container, DashboardFragment.newInstance(), true);
    }
}
