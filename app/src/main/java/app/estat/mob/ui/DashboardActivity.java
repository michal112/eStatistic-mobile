package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.DashboardActivityPresenter;
import app.estat.mob.mvp.view.DashboardActivityView;
import butterknife.BindView;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public class DashboardActivity extends MvpBaseActivity<DashboardActivityPresenter, DashboardActivityView>
        implements DashboardActivityView {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.smooth_app_bar_layout)
    SmoothAppBarLayout mAppBarLayout;

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
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mAppBarLayout.addOnOffsetChangedListener(new ModuleOffsetListener(mAppBarLayout));

        addFragment(R.id.activity_dashboard_container, DashboardFragment.newInstance(), false);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout,
                getMainToolbar(), R.string.drawer_open, R.string.drawer_close);
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

    private class ModuleOffsetListener implements AppBarLayout.OnOffsetChangedListener {
        private final SmoothAppBarLayout mAppBarLayout;

        public ModuleOffsetListener(SmoothAppBarLayout appBarLayout) {
            this.mAppBarLayout = appBarLayout;
        }

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            displayTittle(isToolbarCollapsed(verticalOffset));
        }

        private boolean isToolbarCollapsed(int verticalOffset) {
            return  Math.abs(verticalOffset) == mAppBarLayout.getTotalScrollRange();
        }
    }
}
