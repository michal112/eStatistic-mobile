package app.estat.mob.mvp.core;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.model.manager.SharedPreferencesManager;

abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> view;

    private final Context mContext;

    private final ModuleWrapper mModuleWrapper;

    public MvpBasePresenter(Context context, ApplicationComponent applicationComponent) {
        mModuleWrapper = new ModuleWrapper(applicationComponent);

        this.mContext = context;
    }

    @Override
    public void attachView(V view) {
        this.view = new WeakReference<>(view);

        if (!isRegistered()) {
            register();
        }
    }

    @Override
    public void detachView() {
        if (isViewAttached()) {
            view.clear();
        }

        if (isRegistered()) {
            unregister();
        }
    }

    private boolean isRegistered() {
        return getModuleWrapper().getEventBus().isRegistered(this);
    }

    private void register() {
        getModuleWrapper().getEventBus().register(this);
    }

    private void unregister() {
        getModuleWrapper().getEventBus().unregister(this);
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

    public Context getContext() {
        return mContext;
    }

    @Subscribe
    public void onNoSubscriberEvent(NoSubscriberEvent noSubscriberEvent) {
    }

    public Uri getUserImageUri() {
        return getModuleWrapper().getImageManager().getUserImageUri(getContext());
    }

    public Uri getFarmImageUri() {
        return getModuleWrapper().getImageManager().getFarmImageUri(getContext());
    }

    public boolean isFarmImageExists() {
        return getModuleWrapper().getImageManager().isFarmImageExists(getContext());
    }

    public boolean isUserImageExists() {
        return getModuleWrapper().getImageManager().isUserImageExists(getContext());
    }

    public String getUserName() {
        return getModuleWrapper().getPreferencesManager().getStringValue(
                getContext(), SharedPreferencesManager.USER_NAME_KEY, R.string.empty);
    }

    public String getFarmAddress() {
        return getModuleWrapper().getPreferencesManager().getStringValue(
                getContext(), SharedPreferencesManager.FARM_ADDRESS_KEY, R.string.empty);
    }

    public String getBarnNumber() {
        return getModuleWrapper().getPreferencesManager().getStringValue(
                getContext(), SharedPreferencesManager.BARN_NUMBER_KEY, R.string.empty);
    }
}
