package app.estat.mob.mvp.view.action;

import app.estat.mob.event.BullSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface AddBullFragmentView extends MvpBaseFragmentView {
    void setActivityResult(BullSavedEvent.Status status);
}
