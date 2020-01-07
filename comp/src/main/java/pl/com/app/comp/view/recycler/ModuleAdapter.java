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

    private static final int NO_DATA = 0;

    private static final int MODULE_ITEM = 1;

    public interface ModuleItemClickListener {
        void onClick(int position);
    }

    private Drawable mIcon;

    private String mNoDataText;

    private List<T> mData;

    private ModuleItemClickListener mListener;

    public ModuleAdapter(List<T> data, ModuleItemClickListener listener) {
        mData = data;
        mListener = listener;
    }


    public void setNoDataText(String noDataText) {
        mNoDataText = noDataText;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.isEmpty()) {
            return NO_DATA;
        } else {
            return MODULE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MODULE_ITEM:
                return new ModuleViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.component_recycler_view_item, viewGroup, false), mListener);
            case NO_DATA:
                return new EmptyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.component_recycler_view_no_items, viewGroup, false));
            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ModuleAdapter.ModuleViewHolder) {
            T mItem = mData.get(i);
            ((ModuleViewHolder) viewHolder).setIcon(mIcon);
            ((ModuleViewHolder) viewHolder).setName(mItem.getName());
            ((ModuleViewHolder) viewHolder).setNumber(mItem.getNumber());
        } else if (viewHolder instanceof ModuleAdapter.EmptyViewHolder) {
            ((EmptyViewHolder) viewHolder).setNoDataText(mNoDataText);
        }
    }

    @Override
    public int getItemCount() {
        return mData.isEmpty() ? 1 : mData.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        private ModuleItemClickListener mListener;

        TextView mName;

        TextView mNumber;

        ImageView mIcon;

        public ModuleViewHolder(@NonNull View itemView, ModuleItemClickListener listener) {
            super(itemView);

            mName = itemView.findViewById(R.id.component_recycler_view_item_name);
            mNumber = itemView.findViewById(R.id.component_recycler_view_item_number);
            mIcon = itemView.findViewById(R.id.component_recycler_view_item_icon);

            this.mListener = listener;
            itemView.setOnClickListener(this);
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

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {

        TextView mNoDataText;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNoDataText = itemView.findViewById(R.id.component_recycler_view_no_items_text);
        }

        public void setNoDataText(String noDataText) {
            mNoDataText.setText(noDataText);
        }
    }
}
