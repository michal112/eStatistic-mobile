package pl.com.app.comp.view.text;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import pl.com.app.comp.R;

public class FormEditText extends ConstraintLayout {

    private static final float ICON_ALPHA = 0.54f;

    EditText mEditText;

    ImageView mImageView;

    public FormEditText(Context context) {
        this(context, null);
    }

    public FormEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = layoutInflater.inflate(R.layout.component_form_edit_text, this, true);
        mEditText = view.findViewById(R.id.component_form_edit_text);
        mImageView = view.findViewById(R.id.component_form_edit_text_icon);

        mImageView.setAlpha(ICON_ALPHA);
    }

    public void setHint(@StringRes int hint) {
        mEditText.setHint(hint);
    }

    public void setIcon(@DrawableRes int icon) {
        mImageView.setImageResource(icon);
    }
}
