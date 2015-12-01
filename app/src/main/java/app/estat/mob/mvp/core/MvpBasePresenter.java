package app.estat.mob.mvp.core;

import java.lang.ref.WeakReference;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> view = null;

    @Override
    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (isViewAttached()) {
            view.clear();
            view = null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Override
    public V getView() {
        return view != null ? view.get() : null;
    }

}
