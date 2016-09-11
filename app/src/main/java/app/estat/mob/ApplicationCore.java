package app.estat.mob;

import android.app.Application;
import android.util.Log;

public class ApplicationCore extends Application {
    private static String TAG = ApplicationCore.class.getName();

    @Override
    public void onCreate() {
        Log.i(TAG, "Application started");

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.i(TAG, "Application terminated");
    }
}
