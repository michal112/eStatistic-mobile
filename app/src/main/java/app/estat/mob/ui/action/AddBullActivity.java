package app.estat.mob.ui.action;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.action.AddBullActivityPresenter;
import app.estat.mob.mvp.view.action.AddBullActivityView;

public class AddBullActivity extends ActionActivity<AddBullActivityPresenter, AddBullActivityView>
        implements  AddBullActivityView {

    @NonNull
    @Override
    public AddBullActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AddBullActivityPresenter(context, applicationComponent);
    }

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
