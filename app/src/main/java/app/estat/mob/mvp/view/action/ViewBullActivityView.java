package app.estat.mob.mvp.view.action;

import app.estat.mob.event.StatusEvent;

public interface ViewBullActivityView extends ActionActivityView {
    void deleteBull(StatusEvent.Status status);
}
