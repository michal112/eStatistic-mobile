package app.estat.mob.mvp.view.action

import app.estat.mob.event.StatusEvent
import app.estat.mob.mvp.core.MvpBaseFragmentView

interface AddLactationFragmentView : MvpBaseFragmentView {
    fun setActivityResult(status: StatusEvent.Status?)
}