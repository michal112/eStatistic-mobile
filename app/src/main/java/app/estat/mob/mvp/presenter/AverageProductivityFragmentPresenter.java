package app.estat.mob.mvp.presenter;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.core.MvpBasePresenter;
import app.estat.mob.mvp.view.AverageProductivityFragmentView;

public class AverageProductivityFragmentPresenter extends MvpBasePresenter<AverageProductivityFragmentView> {
    public AverageProductivityFragmentPresenter(ApplicationComponent applicationComponent) {
        super(applicationComponent);
    }
}
