package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.presenter.action.ViewCowActivityPresenter;
import app.estat.mob.mvp.util.ActivityUtils;
import app.estat.mob.mvp.view.action.ViewCowActivityView;

public class ViewCowActivity extends ActionActivity<ViewCowActivityPresenter, ViewCowActivityView>
        implements ViewCowActivityView {

    public static final int ADD_MATE = 0;

    public static final int ADD_LACTATION = 1;

    private final static String TAG =  ViewCowActivity.class.getName();

    private String mCowPublicId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCowPublicId = getIntent().getStringExtra(ActionActivity.VALUE_KEY);
        addFragment(R.id.activity_action_container, ViewCowFragment.newInstance(mCowPublicId), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_view_cow_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_view_cow_menu;
    }

    @NonNull
    @Override
    public ViewCowActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new ViewCowActivityPresenter(context, applicationComponent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.activity_view_cow_menu_delete:
                getPresenter().deleteCow(this, mCowPublicId);
                break;
            case R.id.activity_view_cow_menu_add_mate:
                startActivityForResult(ActionActivity.newIntent(this, AddMateActivity.class, mCowPublicId), ADD_MATE);
                break;
            case R.id.activity_view_cow_menu_add_lactation:
                startActivityForResult(ActionActivity.newIntent(this, AddLactationActivity.class, mCowPublicId), ADD_LACTATION);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    @Override
    public void deleteCow(StatusEvent.Status status) {
        switch (status) {
            case SUCCESS:
                setResult(ActivityUtils.RESULT_COW_DELETED);
                break;
            case FAILURE:
                setResult(ActivityUtils.RESULT_COW_DELETE_ERROR);
                break;
            default:
                Log.d(TAG, "unknown status returned");
                break;
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case ADD_MATE:
                if (resultCode == ActivityUtils.RESULT_MATE_SAVED) {
                    showMessage(R.string.new_mate_successfully_saved);
                    getPresenter().sendMateAdapterRefreshEvent();
                } if (resultCode == ActivityUtils.RESULT_MATE_SAVE_ERROR) {
                    showMessage(R.string.new_mate_save_error);
                }
                break;
            case ADD_LACTATION:
                if (resultCode == ActivityUtils.RESULT_LACTATION_SAVED) {
                    showMessage(R.string.new_lactation_successfully_saved);
                    getPresenter().sendLactationAdapterRefreshEvent();
                } if (resultCode == ActivityUtils.RESULT_LACTATION_SAVE_ERROR) {
                    showMessage(R.string.new_lactation_save_error);
                }
                break;
            default:
                Log.d(TAG, "Unknown activity code received");
                break;
        }
    }
}
