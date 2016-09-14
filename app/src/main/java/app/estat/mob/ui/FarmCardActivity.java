package app.estat.mob.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseActivity;
import app.estat.mob.mvp.presenter.FarmCardActivityPresenter;
import app.estat.mob.mvp.view.FarmCardActivityView;

public class FarmCardActivity extends MvpBaseActivity<FarmCardActivityPresenter,
        FarmCardActivityView> implements FarmCardActivityView {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_farm_card;
    }

    public static void newIntent(@NonNull Context context) {
        Intent intent = new Intent(context, FarmCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(R.id.activity_farm_card_container, FarmCardFragment.newInstance(), false);
    }

    @NonNull
    @Override
    public FarmCardActivityPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardActivityPresenter(applicationComponent);
    }
}
