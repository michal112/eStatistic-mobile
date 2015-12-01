package app.estat.mob;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class ApplicationCore extends Application {

    private static String TAG = ApplicationCore.class.getCanonicalName();

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Application started");
        context = getApplicationContext();

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.i(TAG, "Application terminated");
    }

}
