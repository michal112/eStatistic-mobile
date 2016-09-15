package app.estat.mob.mvp.presenter;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.ModuleActivityView;

public class ModuleActivityPresenter<V extends ModuleActivityView> extends MvpBasePresenter<V> {
    public ModuleActivityPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
