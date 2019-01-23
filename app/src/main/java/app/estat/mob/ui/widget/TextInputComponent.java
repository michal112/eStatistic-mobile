package app.estat.mob.ui.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import app.estat.mob.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextInputComponent extends LinearLayout {

    private static final float ICON_ALPHA = 0.54f;

    @BindView(R.id.component_text_input_edit_text)
    EditText mEditText;

    @BindView(R.id.component_text_input_icon)
    ImageView mImageView;

    public TextInputComponent(Context context) {
        super(context);
    }

    public TextInputComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInputComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = layoutInflater.inflate(R.layout.component_text_input, this, true);
        ButterKnife.bind(this, view);

        mImageView.setAlpha(ICON_ALPHA);
    }

    public void setHint(@StringRes int hint) {
        mEditText.setHint(hint);
    }

    public void setIcon(@DrawableRes int icon) {
        mImageView.setImageResource(icon);
    }
}
