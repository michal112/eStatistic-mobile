package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.presenter.action.ViewBullActivityPresenter;
import app.estat.mob.mvp.util.ActivityUtils;
import app.estat.mob.mvp.view.action.ViewBullActivityView;

public class ViewBullActivity extends ActionActivity<ViewBullActivityPresenter, ViewBullActivityView>
        implements ViewBullActivityView {

    private final static String TAG =  ViewBullActivity.class.getName();

    private String mBullPublicId;

    @NonNull
    @Override
    public ViewBullActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewBullActivityPresenter(context, applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBullPublicId = getIntent().getStringExtra(ActionActivity.VALUE_KEY);
        addFragment(R.id.activity_action_container, ViewBullFragment.newInstance(mBullPublicId), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_view_bull_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_view_bull_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.activity_view_bull_menu_delete:
                getPresenter().deleteBull(this, mBullPublicId);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    @Override
    public void deleteBull(StatusEvent.Status status) {
        switch (status) {
            case SUCCESS:
                setResult(ActivityUtils.RESULT_BULL_DELETED);
                break;
            case FAILURE:
                showMessage(ActivityUtils.RESULT_BULL_DELETE_ERROR);
                break;
            default:
                Log.d(TAG, "unknown status returned");
                break;
        }
        finish();
    }
}
