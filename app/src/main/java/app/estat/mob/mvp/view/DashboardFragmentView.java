package app.estat.mob.mvp.view;

import java.util.List;

import app.estat.mob.db.entity.Module;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface DashboardFragmentView extends MvpBaseFragmentView {
    void showModules(List<Module> modules);
}
