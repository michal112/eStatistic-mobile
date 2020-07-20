package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.mvp.view.module.BullsActivityView;

public class BullsActivityPresenter extends ModuleActivityPresenter<BullsActivityView> {
    public BullsActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    public void sendAdapterRefreshEvent() {
        getModuleWrapper().getEventBus().post(new AdapterRefreshEvent());
    }
}
