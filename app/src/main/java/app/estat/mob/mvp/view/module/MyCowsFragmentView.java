package app.estat.mob.mvp.view.module;

import java.util.List;

import app.estat.mob.db.entity.Cow;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface MyCowsFragmentView extends MvpBaseFragmentView {
    void showCows(List<Cow> cows);

    void refreshAdapter();
}
