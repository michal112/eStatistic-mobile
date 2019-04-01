package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;

public class ManageBullActivity extends ActionActivity {

    private static final String BULL_KEY = "app.estat.mob.ui.action.ManageBullActivity.BULL_KEY";

    public static Intent newIntent(@NonNull Context context, String bullPublicId) {
        Intent intent = new Intent(context, ManageBullActivity.class);
        intent.putExtra(BULL_KEY, bullPublicId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_action_container, ViewBullFragment.newInstance(getIntent().getStringExtra(ManageBullActivity.BULL_KEY)), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_manage_bull_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_manage_bull_menu;
    }
}
