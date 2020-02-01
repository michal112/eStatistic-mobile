package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.module.MyBullsActivityPresenter;
import app.estat.mob.mvp.util.ActivityUtils;
import app.estat.mob.mvp.view.module.MyBullsActivityView;
import app.estat.mob.ui.action.ActionActivity;
import app.estat.mob.ui.action.AddBullActivity;

public class MyBullsActivity extends ModuleActivity<MyBullsActivityPresenter, MyBullsActivityView>
        implements MyBullsActivityView {
    private static final String TAG = MyBullsActivity.class.getName();

    public static final int ADD_BULL = 0;

    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, MyBullsActivity.class, iconRes, nameRes);
    }

    @NonNull
    @Override
    public MyBullsActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new MyBullsActivityPresenter(context, applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, MyBullsFragment.newInstance(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_bulls_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.activity_my_bulls_menu_add:
                startActivityForResult(ActionActivity.newIntent(this, AddBullActivity.class), ADD_BULL);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_BULL:
                if (resultCode == ActivityUtils.RESULT_BULL_SAVED) {
                    showMessage(R.string.new_bull_successfully_saved);
                    getPresenter().sendAdapterRefreshEvent();
                } else if (resultCode == ActivityUtils.RESULT_BULL_SAVE_ERROR) {
                    showMessage(R.string.new_bull_save_error);
                }
                break;
            case MyBullsFragment.VIEW_BULL:
                if (resultCode == ActivityUtils.RESULT_BULL_DELETED) {
                    showMessage(R.string.bull_successfully_deleted);
                } if (resultCode == ActivityUtils.RESULT_BULL_DELETE_ERROR) {
                    showMessage(R.string.bull_delete_error);
                }
                break;
            default:
                Log.d(TAG, "Unknown activity code received");
                break;
        }
    }
}
