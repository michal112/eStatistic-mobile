package app.estat.mob;

import android.app.Application;
import android.util.Log;

import app.estat.mob.component.ApplicationComponent;
import app.estat.mob.component.DaggerApplicationComponent;

public class ApplicationCore extends Application {
    private static String TAG = ApplicationCore.class.getName();

    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Application started");

        //load dagger ApplicationComponent (EventBusInstance, DbCache, DbSession, ...)
        mApplicationComponent = DaggerApplicationComponent.create();

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.i(TAG, "Application terminated");
    }
}
