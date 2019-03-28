package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Cow;
import app.estat.mob.event.AdapterRefreshEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.model.CowData;
import app.estat.mob.mvp.view.module.MyCowsFragmentView;

public class MyCowsFragmentPresenter extends MvpBaseFragmentPresenter<MyCowsFragmentView> {

    private List<Cow> mCows;

    private Context mContext;

    public MyCowsFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
        mContext = context;

        mCows = getModuleWrapper().getDbCache().getCows();
    }

    public void requestCows() {
        if (!isViewAttached()) {
            return;
        }

        getView().showCows(mCows);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAdapterRefreshEvent(AdapterRefreshEvent adapterRefreshEvent) {
        if (!isViewAttached()) {
            return;
        }

        getModuleWrapper().getDbCache().prefetchCows(getModuleWrapper().getDbManager().getDaoSession(mContext));
        mCows = getModuleWrapper().getDbCache().getCows();
        getView().refreshAdapter();
    }

    public CowData getCowData(int position) {
        if (mCows.isEmpty()) {
            return null;
        }

        return CowData.from(mCows.get(position));
    }
}
