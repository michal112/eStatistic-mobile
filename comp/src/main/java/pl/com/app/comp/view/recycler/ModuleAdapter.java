package pl.com.app.comp.view.recycler;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.db.entity.ModuleItem;
import pl.com.app.comp.R;

public class ModuleAdapter<T extends ModuleItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface ModuleItemClickListener {
        void onClick(int position);
    }

    private Drawable mIcon;

    private List<T> mData;

    private ModuleItemClickListener mListener;

    public ModuleAdapter(List<T> data, ModuleItemClickListener listener) {
        mData = data;
        mListener = listener;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ModuleViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.component_recycler_view_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ModuleAdapter.ModuleViewHolder) {
            T mItem = mData.get(i);
            ((ModuleViewHolder) viewHolder).setListener(mListener);
            ((ModuleViewHolder) viewHolder).setIcon(mIcon);
            ((ModuleViewHolder) viewHolder).setName(mItem.getName());
            ((ModuleViewHolder) viewHolder).setNumber(mItem.getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        private ModuleItemClickListener mListener;

        TextView mName;

        TextView mNumber;

        ImageView mIcon;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.component_recycler_view_item_name);
            mNumber = itemView.findViewById(R.id.component_recycler_view_item_number);
            mIcon = itemView.findViewById(R.id.component_recycler_view_item_icon);
        }

        public void setName(String name) {
            mName.setText(name);
        }

        public void setNumber(String number) {
            mNumber.setText(number);
        }

        public void setIcon(Drawable icon) {
            mIcon.setImageDrawable(icon);
        }

        public void setListener(ModuleItemClickListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }
    }
}
