package app.estat.mob.ui.action;

import android.os.Bundle;

import app.estat.mob.R;

public class AddBullActivity extends ActionActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_action_container, AddBullFragment.newInstance(), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_add_bull_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_add_bull_menu;
    }
}
