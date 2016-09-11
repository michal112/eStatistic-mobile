package app.estat.mob.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import app.estat.mob.R;
import app.estat.mob.db.DbCache;
import app.estat.mob.db.DbManager;

public class SplashScreenActivity extends AppCompatActivity {
    @Inject
    private DbCache mCache;

    @Inject
    private DbManager mManager;

    private final static int PREFETCH_DATA = 1;

    private final static String TAG = SplashScreenActivity.class.getName();

    private StarterThread mStarterThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.d(TAG, "Loading data to cache");
        mStarterThread = new StarterThread(new Handler(Looper.getMainLooper()));
        mStarterThread.start();
        mStarterThread.getLooper();
        mStarterThread.prefetchData();
    }

    private void startDashboardActivity() {
        DashboardActivity.newIntent(this);
        mStarterThread.quit();
        finish();
    }

    private class StarterThread extends HandlerThread {
        private final static int MIN_PREFETCH_TIME = 3000;

        private Handler mHandler;

        private Handler mUiHandler;

        public StarterThread(Handler uiHandler) {
            super(StarterThread.class.getName());

            mUiHandler = uiHandler;
        }

        @Override
        protected void onLooperPrepared() {
            mHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == PREFETCH_DATA) {
                        Long startTime = System.currentTimeMillis();
                        mCache.prefetchData(mManager.getDaoSession(getApplicationContext()));
                        Long endTime = System.currentTimeMillis();

                        mUiHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startDashboardActivity();
                            }
                        }, endTime - startTime < MIN_PREFETCH_TIME ? MIN_PREFETCH_TIME : endTime - startTime);
                    }
                }
            };
        }

        public void prefetchData() {
            mHandler.obtainMessage(PREFETCH_DATA).sendToTarget();
        }
    }
}
