package app.estat.mob.mvp.core;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.FormImageChangeEndEvent;

public class MvpBaseActivityPresenter<V extends MvpBaseActivityView> extends MvpBasePresenter<V> {
    public MvpBaseActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0)
    public void onUserImageChangedEvent(FormImageChangeEndEvent userImageChangedEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshUserImage();
    }
}
