package app.estat.mob.mvp.presenter.action

import android.content.Context
import android.util.Log
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.db.entity.Cow
import app.estat.mob.event.LactationSavedEvent
import app.estat.mob.event.StatusEvent
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter
import app.estat.mob.mvp.model.LactationData
import app.estat.mob.mvp.model.getLactation
import app.estat.mob.mvp.util.PresenterUtils.PresenterHandler
import app.estat.mob.mvp.view.action.AddLactationFragmentView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AddLactationFragmentPresenter(context: Context?, applicationComponent: ApplicationComponent?) : MvpBaseFragmentPresenter<AddLactationFragmentView>(context, applicationComponent) {
    private val TAG = AddLactationFragmentPresenter::class.java.name

    var mCowPublicId = ""

    set(value) {
        field = value
        mCow = moduleWrapper.dbCache.findCowByPublicId(field)
    }

    lateinit var mCow :Cow

    val mLactationHandlerThread = PresenterHandler(javaClass.name)

    fun addLactation(context :Context?, lactationData: LactationData) {
        mLactationHandlerThread.post(Runnable {
            try {
                val daoSession = moduleWrapper.dbManager.getDaoSession(context)
                moduleWrapper.dbCache.saveLactation(daoSession, lactationData.getLactation())
            } catch (ex: Exception) {
                Log.e(TAG, "unable to save lactation", ex)
                moduleWrapper.eventBus.post(LactationSavedEvent(StatusEvent.Status.FAILURE))
            }
            moduleWrapper.eventBus.post(LactationSavedEvent(StatusEvent.Status.SUCCESS))
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLactationSavedEvent(event: LactationSavedEvent) {
        if (!isViewAttached) {
            return
        }
        view?.setActivityResult(event.status)
    }
}