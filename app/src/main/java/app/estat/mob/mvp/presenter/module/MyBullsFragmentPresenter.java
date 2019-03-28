package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Bull;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.BullData;
import app.estat.mob.mvp.view.module.MyBullsFragmentView;

public class MyBullsFragmentPresenter extends MvpBaseFragmentPresenter<MyBullsFragmentView> {

    private List<Bull> mBulls;

    private Context mContext;

    public MyBullsFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
        mContext = context;

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

        getModuleWrapper().getDbCache().prefetchBulls(getModuleWrapper().getDbManager().getDaoSession(mContext));
        mBulls = getModuleWrapper().getDbCache().getBulls();
        getView().refreshAdapter();
    }

    public BullData getBullData(int position) {
        if (mBulls.isEmpty()) {
            return null;
        }

        return BullData.from(mBulls.get(position));
    }
}
