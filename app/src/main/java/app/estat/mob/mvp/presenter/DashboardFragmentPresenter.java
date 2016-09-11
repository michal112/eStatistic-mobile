package app.estat.mob.mvp.presenter;

import java.util.List;
import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.DashboardFragmentView;

public class DashboardFragmentPresenter extends MvpBasePresenter<DashboardFragmentView> {
    private List<Module> mModules;

    public List<Module> requestModules() {
        mModules = mCache.getModules();
        return mModules;
    }
}