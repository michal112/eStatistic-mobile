package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.mvp.core.MvpBaseActivityPresenter;
import app.estat.mob.mvp.view.module.MyBullsActivityView;
import app.estat.mob.mvp.view.module.MyCowsActivityView;

public class MyBullsActivityPresenter extends ModuleActivityPresenter<MyBullsActivityView> {
    public MyBullsActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }

    public void sendAdapterRefreshEvent() {
        getModuleWrapper().getEventBus().post(new AdapterRefreshEvent());
    }
}
