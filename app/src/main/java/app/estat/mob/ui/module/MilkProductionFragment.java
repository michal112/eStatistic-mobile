package app.estat.mob.ui.module;

import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.MilkProductionFragmentPresenter;
import app.estat.mob.mvp.view.module.MilkProductionFragmentView;

public class MilkProductionFragment extends MvpBaseFragment<MilkProductionFragmentPresenter,
        MilkProductionFragmentView> implements MilkProductionFragmentView {
    public static MilkProductionFragment newInstance() {
        return new MilkProductionFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_milk_production;
    }

    @NonNull
    @Override
    public MilkProductionFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new MilkProductionFragmentPresenter(applicationComponent);
    }
}
