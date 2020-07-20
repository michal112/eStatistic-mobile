package app.estat.mob.mvp.view.module;

import java.util.List;

import app.estat.mob.db.entity.Bull;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface BullsFragmentView extends MvpBaseFragmentView {
    void showBulls(List<Bull> bulls);

    void refreshAdapter();
}
