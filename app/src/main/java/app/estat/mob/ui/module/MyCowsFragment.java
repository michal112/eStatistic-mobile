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
import app.estat.mob.mvp.presenter.module.MyCowsFragmentPresenter;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;
import butterknife.ButterKnife;

public class MyCowsFragment extends MvpBaseFragment<MyCowsFragmentPresenter, MyCowsFragmentView>
        implements MyCowsFragmentView {
    public static MyCowsFragment newInstance() {
        return new MyCowsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cows, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @NonNull
    @Override
    public MyCowsFragmentPresenter createPresenter(ApplicationComponent applicationComponent) {
        return new MyCowsFragmentPresenter(applicationComponent);
    }
}
