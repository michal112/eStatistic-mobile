package app.estat.mob.mvp.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import app.estat.mob.ApplicationCore;
import app.estat.mob.component.ApplicationComponent;

public abstract class MvpBaseFragment<P extends MvpBasePresenter<V>, V extends MvpBaseFragmentView>
        extends Fragment implements MvpBaseFragmentView {
    private P presenter;

    @NonNull
    public P getPresenter() {
        return presenter;
    }

    @NonNull
    public abstract P createPresenter(Context context, ApplicationComponent applicationComponent);

    @Override
    @SuppressWarnings("unchecked")
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter(getActivity(), ((ApplicationCore) getActivity().getApplication()).getApplicationComponent());
        presenter.attachView((V) this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        presenter.detachView();
    }
}
