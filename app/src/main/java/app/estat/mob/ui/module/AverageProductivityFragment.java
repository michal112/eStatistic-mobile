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
import app.estat.mob.mvp.presenter.module.AverageProductivityFragmentPresenter;
import app.estat.mob.mvp.view.module.AverageProductivityFragmentView;
import butterknife.ButterKnife;

public class AverageProductivityFragment extends MvpBaseFragment<AverageProductivityFragmentPresenter,
        AverageProductivityFragmentView> implements AverageProductivityFragmentView {
    public static AverageProductivityFragment newInstance() {
        return new AverageProductivityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_average_productivity, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @NonNull
    @Override
    public AverageProductivityFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new AverageProductivityFragmentPresenter(applicationComponent);
    }
}
