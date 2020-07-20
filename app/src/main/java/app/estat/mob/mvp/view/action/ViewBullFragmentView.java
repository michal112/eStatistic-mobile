package app.estat.mob.mvp.view.action;

import java.util.List;

import app.estat.mob.db.entity.Mate;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface ViewBullFragmentView extends MvpBaseFragmentView {
    void refreshMates();

    void showMates(List<Mate> mates);
}
