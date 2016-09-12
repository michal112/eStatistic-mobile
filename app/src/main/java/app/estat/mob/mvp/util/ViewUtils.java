package app.estat.mob.mvp.util;

import android.content.Context;

public abstract class ViewUtils {
    public static int getResId(Context context, String res) {
        return context.getResources().getIdentifier(res.substring(res.indexOf(".", 2) + 1),
                res.substring(res.indexOf(".") + 1, res.indexOf(".", 2)), context.getPackageName());
    }
}
