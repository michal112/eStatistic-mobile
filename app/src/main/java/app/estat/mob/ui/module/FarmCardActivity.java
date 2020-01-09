package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.module.FarmCardActivityPresenter;
import app.estat.mob.mvp.view.module.FarmCardActivityView;

public class FarmCardActivity extends ModuleActivity<FarmCardActivityPresenter, FarmCardActivityView>
        implements FarmCardActivityView {
    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, FarmCardActivity.class, iconRes, nameRes);
    }

    @NonNull
    @Override
    public FarmCardActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new FarmCardActivityPresenter(context, applicationComponent);
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
