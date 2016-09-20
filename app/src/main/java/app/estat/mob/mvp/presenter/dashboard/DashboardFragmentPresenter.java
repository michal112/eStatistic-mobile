package app.estat.mob.mvp.presenter.dashboard;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.dashboard.DashboardFragmentView;

public class DashboardFragmentPresenter extends MvpBasePresenter<DashboardFragmentView> {
    private List<Module> mModules;

    public DashboardFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);

        mModules = getModuleWrapper().getDbCache().getModules();
    }

    public void requestModules() {
        if (!isViewAttached()) {
            return;
        }

        getView().showModules(mModules);
    }

    public Module getModule(int position) {
        if (mModules.isEmpty()) {
            return null;
        }

        return mModules.get(position);
    }
}