package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.DashboardActivityPresenter;
import app.estat.mob.mvp.view.DashboardActivityView;
import butterknife.BindView;

public class DashboardActivity extends MvpBaseActivity<DashboardActivityPresenter, DashboardActivityView>
        implements DashboardActivityView {

    @BindView(R.id.activity_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

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
        setSupportActionBar(mToolbar);
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        addFragment(R.id.activity_dashboard_container, DashboardFragment.newInstance(), true);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
