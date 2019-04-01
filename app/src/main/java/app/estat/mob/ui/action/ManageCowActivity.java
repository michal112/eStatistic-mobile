package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;

public class ManageCowActivity extends ActionActivity {

    private static final String COW_KEY = "app.estat.mob.ui.action.ManageCowActivity.COW_KEY";

    public static Intent newIntent(@NonNull Context context, String cowPublicId) {
        Intent intent = new Intent(context, ManageCowActivity.class);
        intent.putExtra(COW_KEY, cowPublicId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_action_container, ViewCowFragment.newInstance(getIntent().getStringExtra(ManageCowActivity.COW_KEY)), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_manage_cow_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_manage_cow_menu;
    }
}
