package app.estat.mob.mvp.presenter.module;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter;
import app.estat.mob.mvp.view.module.AverageProductivityFragmentView;

public class AverageProductivityFragmentPresenter extends MvpBaseFragmentPresenter<AverageProductivityFragmentView> {
    public AverageProductivityFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
