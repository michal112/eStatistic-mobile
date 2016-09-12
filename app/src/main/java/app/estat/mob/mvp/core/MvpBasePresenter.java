package app.estat.mob.mvp.core;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;

import app.estat.mob.component.ApplicationComponent;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> view;

    private ModuleWrapper mModuleWrapper;

    public MvpBasePresenter(ApplicationComponent applicationComponent) {
        mModuleWrapper = new ModuleWrapper(applicationComponent);
    }

    @Override
    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (isViewAttached()) {
            view.clear();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @Nullable
    @Override
    public V getView() {
        return view != null ? view.get() : null;
    }

    public ModuleWrapper getModuleWrapper() {
        return mModuleWrapper;
    }
}
