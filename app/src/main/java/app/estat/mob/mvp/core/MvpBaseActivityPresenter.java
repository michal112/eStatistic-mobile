package app.estat.mob.mvp.core;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.R;
import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.FarmDataChangedEvent;
import app.estat.mob.mvp.model.SharedPreferencesManager;

public class MvpBaseActivityPresenter<V extends MvpBaseActivityView> extends MvpBasePresenter<V> {
    public MvpBaseActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFarmDataChangedEvent(FarmDataChangedEvent farmDataChangedEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshDrawerData();
    }

    public String getUserName() {
        return getModuleWrapper().getPreferencesManager().getStringValue(
                getContext(), SharedPreferencesManager.USER_NAME_KEY, R.string.drawer_user_unknown);
    }

    public String getFarmAddress() {
        return getModuleWrapper().getPreferencesManager().getStringValue(
                getContext(), SharedPreferencesManager.FARM_ADDRESS_KEY, "");
    }
}
