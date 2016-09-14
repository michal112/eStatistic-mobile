package app.estat.mob.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import app.estat.mob.R;
import app.estat.mob.mvp.util.ViewUtils;

public class SplashScreenTextView extends TextView {
    public SplashScreenTextView(Context context) {
        this(context, null);
    }

    public SplashScreenTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public SplashScreenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initFont(attrs);
    }

    private void initFont(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.SplashScreenTextView);
        ViewUtils.setTextViewFont(this, attributes.getString(R.styleable.SplashScreenTextView_fontName));
        attributes.recycle();
    }
}
