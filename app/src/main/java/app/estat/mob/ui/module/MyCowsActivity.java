package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.estat.mob.R;

public class MyCowsActivity extends ModuleActivity {
    private static final String TAG = MyCowsActivity.class.getName();

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
                addFragment(R.id.activity_module_container, AddCowFragment.newInstance(), false);
                break;
            default:
                Log.d(TAG, "Unknown option was clicked");
                break;
        }

        return true;
    }
}
