package app.estat.mob.ui;

import android.support.annotation.NonNull;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragment;
import app.estat.mob.mvp.presenter.FarmCardFragmentPresenter;
import app.estat.mob.mvp.view.FarmCardFragmentView;

public class FarmCardFragment extends MvpBaseFragment<FarmCardFragmentPresenter, FarmCardFragmentView>
        implements FarmCardFragmentView {
    public static FarmCardFragment newInstance() {
        return new FarmCardFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_farm_card;
    }

    @NonNull
    @Override
    public FarmCardFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new FarmCardFragmentPresenter(applicationComponent);
    }
}
