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
import app.estat.mob.mvp.presenter.action.ViewCowActivityPresenter;
import app.estat.mob.mvp.view.action.ViewCowActivityView;

public class ViewCowActivity extends ActionActivity<ViewCowActivityPresenter, ViewCowActivityView>
        implements ViewCowActivityView {

    private final static String TAG =  ViewCowActivity.class.getName();

    private static final String COW_KEY = "app.estat.mob.ui.action.ViewCowActivity.COW_KEY";

    private String mCowPublicId;

    public static Intent newIntent(@NonNull Context context, String cowPublicId) {
        Intent intent = new Intent(context, ViewCowActivity.class);
        intent.putExtra(COW_KEY, cowPublicId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCowPublicId = getIntent().getStringExtra(ViewCowActivity.COW_KEY);
        addFragment(R.id.activity_action_container, ViewCowFragment.newInstance(mCowPublicId), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_manage_cow_toolbar_title;
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
                showMessage(R.string.cow_successfully_deleted);
                break;
            case FAILURE:
                showMessage(R.string.cow_delete_error);
                break;
            default:
                Log.d(TAG, "unknown status returned");
                break;
        }
        finish();
    }
}
