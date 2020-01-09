package app.estat.mob.ui.action;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.action.AddCowActivityPresenter;
import app.estat.mob.mvp.view.action.AddCowActivityView;

public class AddCowActivity extends ActionActivity<AddCowActivityPresenter, AddCowActivityView>
        implements AddCowActivityView {

    @NonNull
    @Override
    public AddCowActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddCowActivityPresenter(context, applicationComponent);
    }

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
