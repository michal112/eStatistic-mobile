package app.estat.mob.mvp.core;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import app.estat.mob.db.DbCache;
import app.estat.mob.db.DbManager;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> view;

    @Inject
    public DbManager mManager;

    @Inject
    public DbCache mCache;

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
}
