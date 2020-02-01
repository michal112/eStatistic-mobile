package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.module.MilkProductionActivityPresenter;
import app.estat.mob.mvp.view.module.MilkProductionActivityView;

public class MilkProductionActivity extends ModuleActivity<MilkProductionActivityPresenter, MilkProductionActivityView>
        implements MilkProductionActivityView {
    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, MilkProductionActivity.class, iconRes, nameRes);
    }

    @NonNull
    @Override
    public MilkProductionActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new MilkProductionActivityPresenter(context, applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, MilkProductionFragment.newInstance(), false);
    }
}
