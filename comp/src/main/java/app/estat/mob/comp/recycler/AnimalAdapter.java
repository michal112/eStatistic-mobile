package app.estat.mob.comp.recycler;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.estat.mob.comp.R;
import app.estat.mob.db.entity.AnimalItem;

public class AnimalAdapter<T extends AnimalItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface AnimalItemClickListener {
        void onClick(int position);
    }

    private Drawable mIcon;

    private List<T> mData;

    private AnimalItemClickListener mListener;

    public AnimalAdapter(List<T> data, AnimalItemClickListener listener) {
        mData = data;
        mListener = listener;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new AnimalViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.component_recycler_view_animal, viewGroup, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof AnimalAdapter.AnimalViewHolder) {
            T mItem = mData.get(i);
            ((AnimalViewHolder) viewHolder).setIcon(mIcon);
            ((AnimalViewHolder) viewHolder).setName(mItem.getName());
            ((AnimalViewHolder) viewHolder).setNumber(mItem.getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        private AnimalItemClickListener mListener;

        TextView mName;

        TextView mNumber;

        ImageView mIcon;

        public AnimalViewHolder(@NonNull View itemView, AnimalItemClickListener listener) {
            super(itemView);

            mName = itemView.findViewById(R.id.component_recycler_view_animal_item_name);
            mNumber = itemView.findViewById(R.id.component_recycler_view_animal_item_number);
            mIcon = itemView.findViewById(R.id.component_recycler_view_animal_item_icon);

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
}
