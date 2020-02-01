package app.estat.mob.comp.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ModuleRecyclerView extends RecyclerView {

    public ModuleRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ModuleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModuleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
