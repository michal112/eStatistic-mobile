package app.estat.mob.mvp.adapter;

import android.support.annotation.DrawableRes;

public interface RecyclerViewItem {

    String getName();

    String getNumber();

    @DrawableRes
    int getIcon();
}
