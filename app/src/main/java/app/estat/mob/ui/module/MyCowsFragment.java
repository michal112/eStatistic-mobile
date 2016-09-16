package app.estat.mob.ui.module;

import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.module.MyCowsFragmentPresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;

public class MyCowsFragment extends MvpBaseFragment<MyCowsFragmentPresenter, MyCowsFragmentView>
        implements MyCowsFragmentView {
    public static MyCowsFragment newInstance() {
        return new MyCowsFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_cows;
    }

    @NonNull
    @Override
    public MyCowsFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new MyCowsFragmentPresenter(applicationComponent);
    }
}