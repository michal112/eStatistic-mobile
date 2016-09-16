package app.estat.mob.ui.module;

import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.AverageProductivityFragmentPresenter;
import app.estat.mob.mvp.view.module.AverageProductivityFragmentView;

public class AverageProductivityFragment extends MvpBaseFragment<AverageProductivityFragmentPresenter,
        AverageProductivityFragmentView> implements AverageProductivityFragmentView {
    public static AverageProductivityFragment newInstance() {
        return new AverageProductivityFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_average_productivity;
    }

    @NonNull
    @Override
    public AverageProductivityFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new AverageProductivityFragmentPresenter(applicationComponent);
    }
}
