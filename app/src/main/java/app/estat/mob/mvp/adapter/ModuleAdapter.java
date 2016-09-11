package app.estat.mob.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import app.estat.mob.R;
import app.estat.mob.db.entity.Module;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    private Context mContext;

    private final List<Module> mModules;

    public ModuleAdapter(List<Module> modules, Context context) {
        this.mModules = modules;
        mContext = context;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_dashboard_item, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position) {
        holder.bindName(mModules.get(position).getNameRes());
        holder.bindDescription(mModules.get(position).getDescriptionRes());
        holder.bindImage(mModules.get(position).getIconRes());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_dashboard_item_image)
        ImageView mImage;

        @BindView(R.id.fragment_dashboard_item_name)
        TextView mName;

        @BindView(R.id.fragment_dashboard_item_description)
        TextView mDescription;

        public ModuleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindName(String name) {
            mName.setText(name);
        }

        public void bindDescription(String description) {
            mDescription.setText(description);
        }

        public void bindImage() {
            mImage.setR
        }
    }

}
