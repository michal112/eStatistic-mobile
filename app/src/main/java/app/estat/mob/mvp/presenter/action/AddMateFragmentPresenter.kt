package app.estat.mob.mvp.presenter.action

import android.content.Context
import android.util.Log
import app.estat.mob.component.ApplicationComponent
import app.estat.mob.db.entity.Cow
import app.estat.mob.db.type.FormSpinnerItem
import app.estat.mob.event.MateSavedEvent
import app.estat.mob.event.StatusEvent
import app.estat.mob.mvp.core.MvpBaseFragmentPresenter
import app.estat.mob.mvp.model.MateData
import app.estat.mob.mvp.model.getMate
import app.estat.mob.mvp.util.PresenterUtils.PresenterHandler
import app.estat.mob.mvp.view.action.AddMateFragmentView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class AddMateFragmentPresenter(context: Context?, applicationComponent: ApplicationComponent?) : MvpBaseFragmentPresenter<AddMateFragmentView>(context, applicationComponent) {
    private val TAG = AddCowFragmentPresenter::class.java.name

    var mCowPublicId = ""

    set(value) {
        field = value
        mCow = moduleWrapper.dbCache.findCowByPublicId(field)
    }

    lateinit var mCow :Cow

    val mMateHandlerThread = PresenterHandler(javaClass.name)

    val mBulls = moduleWrapper.dbCache.bulls

    fun addMate(context :Context?, mateData :MateData) {
        mMateHandlerThread.post(Runnable {
            try {
                val daoSession = moduleWrapper.dbManager.getDaoSession(context)
                moduleWrapper.dbCache.saveMate(daoSession, mateData.getMate())
            } catch (ex: Exception) {
                Log.e(TAG, "unable to save mate", ex)
                moduleWrapper.eventBus.post(MateSavedEvent(StatusEvent.Status.FAILURE))
            }
            moduleWrapper.eventBus.post(MateSavedEvent(StatusEvent.Status.SUCCESS))
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMateSavedEvent(event: MateSavedEvent) {
        if (!isViewAttached) {
            return
        }
        view?.setActivityResult(event.status)
    }

    fun getSpinnerData() :List<FormSpinnerItem> {
        val spinnerData: MutableList<FormSpinnerItem> = ArrayList()
        spinnerData.addAll(mBulls)
        return spinnerData
    }
}