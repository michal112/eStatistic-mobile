package app.estat.mob.mvp.view.action;

import app.estat.mob.event.StatusEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface AddCowFragmentView extends MvpBaseFragmentView {
    void setActivityResult(StatusEvent.Status status);
}
