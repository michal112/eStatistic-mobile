package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.FarmCardActivityPresenter;
import app.estat.mob.mvp.view.FarmCardActivityView;

public class FarmCardActivity extends ModuleActivity<FarmCardActivityPresenter,
        FarmCardActivityView> implements FarmCardActivityView {
    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, FarmCardActivity.class, iconRes, nameRes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, FarmCardFragment.newInstance(), false);
    }

    @NonNull
    @Override
    public FarmCardActivityPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardActivityPresenter(applicationComponent);
    }
}
