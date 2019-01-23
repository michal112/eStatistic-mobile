package app.estat.mob.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.dashboard.DashboardActivityPresenter;
import app.estat.mob.mvp.util.ActivityUtil;
import app.estat.mob.mvp.util.ViewUtils;
import app.estat.mob.mvp.view.dashboard.DashboardActivityView;
import butterknife.BindView;

public class DashboardActivity extends MvpBaseActivity<DashboardActivityPresenter, DashboardActivityView>
        implements DashboardActivityView {
    private static final String TAG = DashboardActivity.class.getName();

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.smooth_app_bar_layout)
    AppBarLayout mAppBarLayout;

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
    public DashboardActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new DashboardActivityPresenter(context, applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mAppBarLayout.addOnOffsetChangedListener(ViewUtils.getAppBarOffsetListener(this, mAppBarLayout));

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DashboardFragment.FARM_CARD_EDIT:
                if (resultCode == ActivityUtil.RESULT_FARM_CARD_SAVED) {
                    showMessage(R.string.farm_card_data_successfully_saved);
                    refreshDrawerData();
                } else if (resultCode == ActivityUtil.RESULT_FARM_CARD_SAVE_ERROR) {
                    showMessage(R.string.farm_card_data_save_error);
                }
                break;
            default:
                Log.d(TAG, "Unknown activity code3 received");
                break;
        }
    }
}
