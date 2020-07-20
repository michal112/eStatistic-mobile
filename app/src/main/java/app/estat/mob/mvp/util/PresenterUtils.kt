package app.estat.mob.mvp.util

import android.os.Handler
import android.os.HandlerThread

object PresenterUtils {

    class PresenterHandler(name :String) : HandlerThread(name) {
        lateinit var mHandler : Handler

        init {
            start()
            looper
        }

        override fun onLooperPrepared() {
            mHandler = Handler(looper)
        }

        fun post(runnable :Runnable) {
            mHandler.post(runnable)
        }
    }
}