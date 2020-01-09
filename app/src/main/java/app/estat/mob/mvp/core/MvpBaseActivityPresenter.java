package app.estat.mob.mvp.core;

import android.content.Context;
import android.net.Uri;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.model.SharedPreferencesManager;

public abstract class MvpBaseActivityPresenter<V extends MvpBaseActivityView> extends MvpBasePresenter<V> {
    public MvpBaseActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
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
                getContext(), SharedPreferencesManager.USER_NAME_KEY, R.string.drawer_user_unknown);
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
