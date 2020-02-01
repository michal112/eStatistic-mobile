package app.estat.mob.ui.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.presenter.module.AverageProductivityActivityPresenter;
import app.estat.mob.mvp.view.module.AverageProductivityActivityView;

public class AverageProductivityActivity extends ModuleActivity<AverageProductivityActivityPresenter, AverageProductivityActivityView>
        implements AverageProductivityActivityView {
    public static Intent newIntent(@NonNull Context context, String iconRes, String nameRes) {
        return newIntent(context, AverageProductivityActivity.class, iconRes, nameRes);
    }

    @NonNull
    @Override
    public AverageProductivityActivityPresenter createPresenter(Context context, ApplicationComponent applicationComponent) {
        return new AverageProductivityActivityPresenter(context, applicationComponent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_module_container, AverageProductivityFragment.newInstance(), false);
    }
}
