package app.estat.mob.mvp.presenter.dashboard;

import android.content.Context;

import java.util.List;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.dashboard.DashboardFragmentView;

public class DashboardFragmentPresenter extends MvpBaseFragmentPresenter<DashboardFragmentView> {
    private List<Module> mModules;

    public DashboardFragmentPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);

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