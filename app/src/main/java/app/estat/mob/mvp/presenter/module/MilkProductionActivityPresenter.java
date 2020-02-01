package app.estat.mob.mvp.presenter.module;

import android.content.Context;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.mvp.view.module.MilkProductionActivityView;

public class MilkProductionActivityPresenter extends ModuleActivityPresenter<MilkProductionActivityView> {
    public MilkProductionActivityPresenter(Context context, ApplicationComponent applicationComponent) {
        super(context, applicationComponent);
    }
}
