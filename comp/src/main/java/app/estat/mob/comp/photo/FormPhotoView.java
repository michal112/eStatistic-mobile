package app.estat.mob.comp.photo;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import app.estat.mob.comp.R;

public class FormPhotoView extends ConstraintLayout {

    public interface OnPopupClickListener {
        void onClick();
    }

    private static final String TAG = FormPhotoView.class.getName();

    private static final float ALPHA = 0.54f;

    ProgressBar mProgressBar;

    ImageView mImageView;

    ImageView mImageViewIcon;

    PopupMenu mPopupMenu;

    Button mButton;

    private OnPopupClickListener mClickListener;

    public FormPhotoView(Context context) {
        this(context, null);
    }

    public FormPhotoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormPhotoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = layoutInflater.inflate(R.layout.component_form_photo_view, this, true);
        mImageViewIcon = view.findViewById(R.id.component_photo_view_icon);
        mImageView = view.findViewById(R.id.component_form_photo_view_photo_image);
        mProgressBar = view.findViewById(R.id.component_form_photo_view_photo_progress);
        mButton = view.findViewById(R.id.component_form_photo_view_button);

        mImageViewIcon.setAlpha(ALPHA);

        mPopupMenu = new PopupMenu(context, mButton);
        mPopupMenu.inflate(R.menu.component_photo_view_popup_menu);
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.component_photo_view_popup_menu_photo) {
                    mClickListener.onClick();
                } else {
                    Log.d(TAG, "Unknown item selected");
                }
                return true;
            }
        });
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupMenu.show();
            }
        });
    }

    public void setClickListener(OnPopupClickListener listener) {
        this.mClickListener = listener;
    }

    public void setPhotoImage(@DrawableRes int image) {
        mImageView.setImageResource(image);
    }

    public void setIcon(@DrawableRes int icon) {
        mImageViewIcon.setImageResource(icon);
    }

    public void showProgress(boolean show) {
        if (show) {
            mImageView.setAlpha(ALPHA);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mImageView.setAlpha(1f);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public ImageView getImageView() {
        return mImageView;
    }
}
