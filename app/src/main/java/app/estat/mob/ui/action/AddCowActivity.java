package app.estat.mob.ui.action;

import android.os.Bundle;

import app.estat.mob.R;

public class AddCowActivity  extends ActionActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_action_container, AddCowFragment.newInstance(), false);
    }

    @Override
    protected int getTitleRes() {
        return R.string.activity_add_cow_toolbar_title;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.activity_add_cow_menu;
    }
}
