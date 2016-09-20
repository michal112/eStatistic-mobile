package app.estat.mob.mvp.presenter.module;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.event.UserImageChangeStartEvent;
import app.estat.mob.event.UserImageChangedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.FarmCardFragmentView;

public class FarmCardFragmentPresenter extends MvpBaseFragmentPresenter<FarmCardFragmentView> {
    public FarmCardFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onUserImageChangedEvent(UserImageChangedEvent userImageChangedEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshUserImage();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserImageChangeStartEvent(UserImageChangeStartEvent userImageChangeStartEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().showUserImageProgress();
    }
}
