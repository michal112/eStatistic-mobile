package app.estat.mob.mvp.view.action;

import java.util.List;

import app.estat.mob.db.entity.Lactation;
import app.estat.mob.db.entity.Mate;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface ViewCowFragmentView extends MvpBaseFragmentView {
    void showMates(List<Mate> mates);

    void refreshMates();

    void refreshLactations();

    void showLactations(List<Lactation> lactations);
}
