package app.estat.mob.mvp.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.estat.mob.ApplicationCore;
import app.estat.mob.component.ApplicationComponent;
import butterknife.ButterKnife;

public abstract class MvpBaseFragment<P extends MvpBasePresenter<V>, V extends MvpBaseFragmentView>
        extends Fragment implements MvpBaseFragmentView {

    private P presenter;

    @NonNull
    public P getPresenter() {
        return presenter;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    @NonNull
    public abstract P createPresenter(ApplicationComponent applicationComponent);

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);

        presenter = createPresenter(((ApplicationCore) getActivity().getApplication()).getApplicationComponent());
        presenter.attachView((V) this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        presenter.detachView();
    }
}
