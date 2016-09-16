package app.estat.mob.mvp.presenter.module;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.module.ModuleActivityView;

public class ModuleActivityPresenter extends MvpBasePresenter<ModuleActivityView> {
    public ModuleActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}