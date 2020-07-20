package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.BullsFragmentView;

public class BullsFragmentPresenter extends MvpBaseFragmentPresenter<BullsFragmentView> {

    private List<Bull> mBulls;

    public BullsFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

        mBulls = getModuleWrapper().getDbCache().getBulls();
    }

    public void requestBulls() {
        if (!isViewAttached()) {
            return;
        }

        getView().showBulls(mBulls);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAdapterRefreshEvent(AdapterRefreshEvent adapterRefreshEvent) {
        if (!isViewAttached()) {
            return;
        }

        getView().refreshAdapter();
    }

    public String getBullPublicId(int position) {
        if (mBulls.isEmpty()) {
            return null;
        }

        return mBulls.get(position).getPublicId();
    }
}
