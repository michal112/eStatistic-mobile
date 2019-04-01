package pl.com.app.comp.view.text;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.com.app.comp.R;

public class FormTextView extends ConstraintLayout {

    private static final float ICON_ALPHA = 0.54f;

    private Context mContext;

    TextView mTextView;

    TextInputLayout mTextInputLayout;

    TextInputEditText mTextInputEditText;

    ImageView mImageView;

    public FormTextView(Context context) {
        this(context, null);
    }

    public FormTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        String layoutInflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(layoutInflaterService);
        View view = layoutInflater.inflate(R.layout.component_form_text_view, this, true);
        mTextInputLayout = view.findViewById(R.id.component_form_text_view_text_input_layout);
        mTextInputEditText = view.findViewById(R.id.component_form_text_view_text_input_edit_text_layout);
        mImageView = view.findViewById(R.id.component_form_text_view_icon);
        mTextView = view.findViewById(R.id.component_form_text_view_label);

        mImageView.setAlpha(ICON_ALPHA);
        mTextInputLayout.setEnabled(false);
        mContext = context;
    }

    public void setHint(@StringRes int hint) {
        mTextView.setText(mContext.getString(hint));
    }

    public void setIcon(@DrawableRes int icon) {
        mImageView.setImageResource(icon);
    }

    public void setText(String text) {
        mTextInputLayout.getEditText().setText(text);
    }
}
