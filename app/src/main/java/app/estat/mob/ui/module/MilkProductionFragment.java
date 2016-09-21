package app.estat.mob.ui.module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.MilkProductionFragmentPresenter;
import app.estat.mob.mvp.view.module.MilkProductionFragmentView;
import butterknife.ButterKnife;

public class MilkProductionFragment extends MvpBaseFragment<MilkProductionFragmentPresenter,
        MilkProductionFragmentView> implements MilkProductionFragmentView {
    public static MilkProductionFragment newInstance() {
        return new MilkProductionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_milk_production, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @NonNull
    @Override
    public MilkProductionFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new MilkProductionFragmentPresenter(applicationComponent);
    }
}
