package pl.com.app.comp.view.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import pl.com.app.comp.R;

public class ModuleRecyclerView extends RecyclerView {

    private Drawable mIcon;

    private String noDataText;

    public ModuleRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ModuleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModuleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setIcon(context, attrs);
        setNoDataText(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof ModuleAdapter) {
            ((ModuleAdapter) adapter).setIcon(mIcon);
            ((ModuleAdapter) adapter).setNoDataText(noDataText);
        }
    }

    private void setIcon(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ModuleRecyclerView);
        mIcon = typedArray.getDrawable(R.styleable.ModuleRecyclerView_iconName);
        typedArray.recycle();
    }

    private void setNoDataText(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ModuleRecyclerView);
        noDataText = typedArray.getString(R.styleable.ModuleRecyclerView_noDataText);
        typedArray.recycle();
    }
}
