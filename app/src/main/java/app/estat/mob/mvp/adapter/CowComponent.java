package app.estat.mob.mvp.adapter;

import app.estat.mob.R;
import app.estat.mob.db.entity.Cow;

public class CowComponent implements RecyclerViewItem {

    private final static int ICON = R.drawable.ic_my_cows;

    private Cow mCow;

    @Override
    public String getName() {
        return mCow.getName();
    }

    @Override
    public String getNumber() {
        return mCow.getNumber();
    }

    @Override
    public int getIcon() {
        return ICON;
    }
}
