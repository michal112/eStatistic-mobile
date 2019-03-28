package app.estat.mob.ui.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.mvp.model.BullData;

public class ManageBullActivity extends ActionActivity {

    private static final String BULL_KEY = "app.estat.mob.ui.action.ManageBullActivity.BULL_KEY";

    public static Intent newIntent(@NonNull Context context, BullData bullData) {
        Intent intent = new Intent(context, ManageBullActivity.class);
        intent.putExtra(BULL_KEY, bullData);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_action_container, ViewBullFragment.newInstance((BullData) getIntent().getSerializableExtra(ManageBullActivity.BULL_KEY)), false);
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
