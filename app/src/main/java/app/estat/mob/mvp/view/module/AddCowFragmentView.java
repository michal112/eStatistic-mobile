package app.estat.mob.mvp.view.module;

import app.estat.mob.event.CowSavedEvent;
import app.estat.mob.mvp.core.MvpBaseFragmentView;

public interface AddCowFragmentView extends MvpBaseFragmentView {
    void setActivityResult(CowSavedEvent.Status status);
}
