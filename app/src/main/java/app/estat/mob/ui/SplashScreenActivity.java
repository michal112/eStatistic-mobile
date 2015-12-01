package app.estat.mob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.estat.mob.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int MIN_RUN_TIME = 3000;

    private final static String TAG = SplashScreenActivity.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        StarterThread starterThread = new StarterThread();
        starterThread.run();
    }

    private void getDashboardActivity() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    private class StarterThread extends Thread {

        @Override
        public void run() {
            try {
                Long timeBefore = System.currentTimeMillis();

                //TODO load db data
                Long timeDiff = System.currentTimeMillis() - timeBefore;

                if (MIN_RUN_TIME > timeDiff) {
                    Thread.sleep(MIN_RUN_TIME - timeDiff);
                }

                getDashboardActivity();
            } catch (InterruptedException e) {
                Log.d(TAG, "Waiting thread is activated before the condition has been satisfied");
            }
        }

    }

}
