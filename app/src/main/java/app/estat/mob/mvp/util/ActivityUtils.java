package app.estat.mob.mvp.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;

public abstract class ActivityUtils {
    public static final int RESULT_FARM_CARD_SAVED = 1;

    public static final int RESULT_FARM_CARD_SAVE_ERROR = 2;

    public static final int RESULT_COW_SAVED = 3;

    public static final int RESULT_COW_SAVE_ERROR = 4;

    public static final int RESULT_BULL_SAVED = 5;

    public static final int RESULT_BULL_SAVE_ERROR = 6;

    public static final int RESULT_BULL_DELETE_ERROR = 7;

    public static final int RESULT_BULL_DELETED = 8;

    public static final int RESULT_COW_DELETE_ERROR = 9;

    public static final int RESULT_COW_DELETED = 10;

    public static void animateModuleActivity(Activity activity, Intent intent,
                                             ImageView imageView, String transitionName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, transitionName);
            activity.startActivity(intent, activityOptionsCompat.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    public static void animateModuleActivityForResult(Activity activity, Intent intent,
                                             ImageView imageView, String transitionName, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageView, transitionName);
            activity.startActivityForResult(intent, requestCode, activityOptionsCompat.toBundle());
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }
}
