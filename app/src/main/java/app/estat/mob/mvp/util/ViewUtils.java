package app.estat.mob.mvp.util;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import app.estat.mob.mvp.core.MvpBaseActivity;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public abstract class ViewUtils {
    private final static String TAG = ViewUtils.class.getName();

    private final static String DIR_FONT_NAME = "font";

    private static final float IMAGE_LOADING_ALPHA = 0.5f;

    private static final float IMAGE_LOADED_ALPHA = 1f;

    public static void insertImage(Context context, Uri imageUri, @DrawableRes int errorImage,
                                   final ImageView destView, final ProgressBar imageProgress) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.d(TAG, "Unable to load image", exception);
            }
        });
        builder.build().load(imageUri).error(errorImage)
                .centerCrop().fit().into(destView, new Callback() {
            @Override
            public void onSuccess() {
                hideProgress(destView, imageProgress);
            }

            @Override
            public void onError() {
                hideProgress(destView, imageProgress);
            }
        });
    }

    public static void showProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(IMAGE_LOADING_ALPHA);
        imageProgress.setVisibility(View.VISIBLE);
    }

    public static void hideProgress(ImageView imageView, ProgressBar imageProgress) {
        imageView.setAlpha(IMAGE_LOADED_ALPHA);
        imageProgress.setVisibility(View.GONE);
    }

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
