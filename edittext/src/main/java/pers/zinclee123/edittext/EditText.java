package pers.zinclee123.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liyanjin on 2017/5/24.
 * 基本是照着这篇文章做的 http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0814/3302.html
 * 加了一点定制化的东西
 */

public class EditText extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private boolean mShowClearIcon;
    private int mIconColor = -1;
    private int mIconResId = -1;

    public EditText(Context context) {
        super(context);
        init(context);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        mShowClearIcon = a.getBoolean(R.styleable.EditText_et_showClearIcon, true);

        //获取图标颜色
        mIconColor = a.getResourceId(R.styleable.EditText_et_clearIconColor, -1);
        if (mIconColor == -1) {
            String colorString = a.getString(R.styleable.EditText_et_clearIconColor);
            if (!TextUtils.isEmpty(colorString)) {
                mIconColor = Color.parseColor(colorString);
            }
        }

        //获取图标
        mIconResId = a.getResourceId(R.styleable.EditText_et_clearIcon, -1);
        a.recycle();
        init(context);
    }

    private void init(final Context context) {
        if (mIconColor == -1) {
            mIconColor = getEditTextActiveColor();
        }

        if (mIconResId == -1) {
            mIconResId = R.drawable.clear_text;
        }

        setClearIcon(mIconResId);
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    private void setClearIconVisible(final boolean visible) {
        final Drawable[] compoundDrawables = getCompoundDrawables();
        if (mShowClearIcon) {
            mClearTextIcon.setVisible(visible, false);
            setCompoundDrawables(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    visible ? mClearTextIcon : null,
                    compoundDrawables[3]);
        } else {
            mClearTextIcon.setVisible(false, false);
            setCompoundDrawables(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    null,
                    compoundDrawables[3]);
        }
    }

    @Override
    public void onFocusChange(final View view, final boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setText("");
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override
    public final void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(final OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    public void setClearIconColor(int color) {
        mIconColor = color;
        DrawableCompat.setTint(mClearTextIcon, color);
        autoShow();
    }

    public void setClearIcon(@DrawableRes int resId) {
        mIconResId = resId;
        final Drawable drawable = ContextCompat.getDrawable(getContext(), mIconResId);
        mClearTextIcon = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(mClearTextIcon, mIconColor);
        mClearTextIcon.setBounds(0, 0, (int) getTextSize(), (int) getTextSize());
        autoShow();
    }

    public void setClearIcon(Drawable drawable) {
        this.mClearTextIcon = drawable;
        mClearTextIcon.setBounds(0, 0, (int) getTextSize(), (int) getTextSize());
        autoShow();
    }

    public void setShowClearIcon(boolean show) {
        this.mShowClearIcon = show;
        autoShow();
    }

    private int getEditTextActiveColor() {
        TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.colorControlActivated
        });
        int colorAccent = array.getColor(0, 0xF5F5F5);
        array.recycle();
        return colorAccent;
    }

    private void autoShow() {
        if (TextUtils.isEmpty(getText().toString())) {
            setClearIconVisible(false);
        } else {
            setClearIconVisible(true);
        }
    }


}
