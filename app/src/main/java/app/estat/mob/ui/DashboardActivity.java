package app.estat.mob.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.DashboardPresenter;
import app.estat.mob.mvp.view.DashboardView;

public class DashboardActivity extends MvpBaseActivity<DashboardPresenter, DashboardView>
        implements DashboardView {

    @Override
    public int getLayoutResId() {
        return R.layout.main_activity;
    }

    @NonNull
    @Override
    public DashboardPresenter createPresenter() {
        return new DashboardPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
