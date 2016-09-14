package app.estat.mob.mvp.util;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public abstract class ActivityUtil {
    public static void animateModuleActivity(FragmentActivity activity, Intent intent,
                                             ImageView imageView, String transitionName) {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // animate activity transition
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, transitionName);
            activity.startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            // start activity without animation
            activity.startActivity(intent);
        }
    }
}
