package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.estat.mob.R;
import app.estat.mob.mvp.util.ActivityUtil;

public class MyCowsActivity extends ModuleActivity {
    private static final String TAG = MyCowsActivity.class.getName();

    public static final int ADD_COW = 0;

    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, MyCowsActivity.class, iconRes, nameRes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, MyCowsFragment.newInstance(), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_cows_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.activity_my_cows_menu_add:
                startActivityForResult(AddCowActivity.newIntent(this), ADD_COW);
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
            case ADD_COW:
                if (resultCode == ActivityUtil.RESULT_COW_SAVED) {
                    showMessage(R.string.new_cow_successfully_saved);
                    getPresenter().sendAdapterRefreshEvent();
                } else if (resultCode == ActivityUtil.RESULT_COW_SAVE_ERROR) {
                    showMessage(R.string.new_cow_save_error);
                }
                break;
            default:
                Log.d(TAG, "Unknown activity code received");
                break;
        }
    }
}
