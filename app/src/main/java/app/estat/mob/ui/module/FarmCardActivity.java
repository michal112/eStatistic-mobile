package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;

import app.estat.mob.R;

public class FarmCardActivity extends ModuleActivity {
    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, FarmCardActivity.class, iconRes, nameRes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, FarmCardFragment.newInstance(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_farm_card_menu, menu);
        return true;
    }
}
