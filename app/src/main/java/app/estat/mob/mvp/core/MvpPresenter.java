package app.estat.mob.mvp.core;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);

    void detachView();

    boolean isViewAttached();

    V getView();
}
