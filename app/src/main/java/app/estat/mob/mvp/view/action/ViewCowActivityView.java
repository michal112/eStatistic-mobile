package app.estat.mob.mvp.view.action;

import app.estat.mob.event.StatusEvent;

public interface ViewCowActivityView extends ActionActivityView {
    void deleteCow(StatusEvent.Status status);
}
