package app.estat.mob.comp.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import app.estat.mob.comp.R;

public class AnimalRecyclerView extends RecyclerView {

    private Drawable mIcon;

    public AnimalRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public AnimalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setIcon(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof AnimalAdapter) {
            ((AnimalAdapter) adapter).setIcon(mIcon);
        }
    }

    private void setIcon(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimalRecyclerView);
        mIcon = typedArray.getDrawable(R.styleable.AnimalRecyclerView_iconName);
        typedArray.recycle();
    }
}
