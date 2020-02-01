package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.view.module.AverageProductivityActivityView;

public class AverageProductivityActivityPresenter extends ModuleActivityPresenter<AverageProductivityActivityView> {
    public AverageProductivityActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
