package app.estat.mob.mvp.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.widget.TextView;

import java.io.File;

import app.estat.mob.mvp.core.MvpBaseActivity;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public abstract class ViewUtils {
    private final static String DIR_FONT_NAME = "font";

    public static int getResId(Context context, String res) {
        return context.getResources().getIdentifier(res.substring(res.indexOf(".", 2) + 1),
                res.substring(res.indexOf(".") + 1, res.indexOf(".", 2)), context.getPackageName());
    }

    public static void setTextViewFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(
                textView.getContext().getAssets(), DIR_FONT_NAME + File.separator + fontName));
    }

    public static <A extends MvpBaseActivity> AppBarLayout.OnOffsetChangedListener getAppBarOffsetListener(
            final A activity, final SmoothAppBarLayout mAppBarLayout) {
        return new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                activity.displayActionBarTittle(isToolbarCollapsed(verticalOffset));
            }

            private boolean isToolbarCollapsed(int verticalOffset) {
                return  Math.abs(verticalOffset) == mAppBarLayout.getTotalScrollRange();
            }
        };
    }
}
