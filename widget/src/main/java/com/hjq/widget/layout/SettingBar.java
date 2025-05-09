package com.hjq.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.hjq.widget.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/01/23
 *    desc   : 设置条自定义控件
 */
public final class SettingBar extends FrameLayout {

    private final LinearLayout mLinearLayout;
    private final TextView mLeftView;
    private final TextView mRightView;
    private final View mLineView;

    public SettingBar(Context context) {
        this(context, null);
    }

    public SettingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SettingBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mLinearLayout = new LinearLayout(getContext());
        mLeftView = new TextView(getContext());
        mRightView = new TextView(getContext());
        mLineView  = new View(getContext());

        mLeftView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        mRightView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        mLeftView.setSingleLine(true);
        mRightView.setSingleLine(true);

        mLeftView.setEllipsize(TextUtils.TruncateAt.END);
        mRightView.setEllipsize(TextUtils.TruncateAt.END);

        mLeftView.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), mLeftView.getLineSpacingMultiplier());
        mRightView.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), mRightView.getLineSpacingMultiplier());

        mLeftView.setPaddingRelative((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        mRightView.setPaddingRelative((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));

        mLeftView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        mRightView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SettingBar);

        // 文本设置
        if (array.hasValue(R.styleable.SettingBar_bar_leftText)) {
            setLeftText(array.getString(R.styleable.SettingBar_bar_leftText));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_rightText)) {
            setRightText(array.getString(R.styleable.SettingBar_bar_rightText));
        }

        // 提示设置
        if (array.hasValue(R.styleable.SettingBar_bar_leftHint)) {
            setLeftHint(array.getString(R.styleable.SettingBar_bar_leftHint));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_rightHint)) {
            setRightHint(array.getString(R.styleable.SettingBar_bar_rightHint));
        }

        // 图标设置
        if (array.hasValue(R.styleable.SettingBar_bar_leftIcon)) {
            setLeftIcon(array.getDrawable(R.styleable.SettingBar_bar_leftIcon));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_rightIcon)) {
            setRightIcon(array.getDrawable(R.styleable.SettingBar_bar_rightIcon));
        }

        // 文字颜色设置
        setLeftColor(array.getColor(R.styleable.SettingBar_bar_leftColor, ContextCompat.getColor(getContext(), com.hjq.base.R.color.black80)));
        setRightColor(array.getColor(R.styleable.SettingBar_bar_rightColor, ContextCompat.getColor(getContext(),  com.hjq.base.R.color.black60)));

        // 文字大小设置
        setLeftSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.SettingBar_bar_leftSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics())));
        setRightSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.SettingBar_bar_rightSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics())));

        // 分割线设置
        if (array.hasValue(R.styleable.SettingBar_bar_lineColor)) {
            setLineDrawable(array.getDrawable(R.styleable.SettingBar_bar_lineColor));
        } else {
            setLineDrawable(new ColorDrawable(0xFFECECEC));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_lineVisible)) {
            setLineVisible(array.getBoolean(R.styleable.SettingBar_bar_lineVisible, true));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_lineSize)) {
            setLineSize(array.getDimensionPixelSize(R.styleable.SettingBar_bar_lineSize, 0));
        }

        if (array.hasValue(R.styleable.SettingBar_bar_lineMargin)) {
            setLineMargin(array.getDimensionPixelSize(R.styleable.SettingBar_bar_lineMargin, 0));
        }

        if (getBackground() == null) {
            StateListDrawable drawable = new StateListDrawable();
            drawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(ContextCompat.getColor(getContext(), com.hjq.base.R.color.black5)));
            drawable.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(ContextCompat.getColor(getContext(),com.hjq.base.R.color.black5)));
            drawable.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(ContextCompat.getColor(getContext(), com.hjq.base.R.color.black5)));
            drawable.addState(new int[]{}, new ColorDrawable(ContextCompat.getColor(getContext(), com.hjq.base.R.color.white)));
            setBackground(drawable);

            // 必须要设置可点击，否则点击屏幕任何角落都会触发按压事件
            setFocusable(true);
            setClickable(true);
        }

        array.recycle();

        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.gravity = Gravity.CENTER_VERTICAL;
        mLinearLayout.addView(mLeftView, leftParams);

        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
        rightParams.gravity = Gravity.CENTER_VERTICAL;
        rightParams.weight = 1;
        mLinearLayout.addView(mRightView, rightParams);

        addView(mLinearLayout, 0, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL));
        addView(mLineView, 1, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM));
    }

    /**
     * 设置左边的标题
     */
    public SettingBar setLeftText(@StringRes int id) {
        return setLeftText(getResources().getString(id));
    }

    public SettingBar setLeftText(CharSequence text) {
        mLeftView.setText(text);
        return this;
    }

    public CharSequence getLeftText() {
        return mLeftView.getText();
    }

    /**
     * 设置左边的提示
     */
    public SettingBar setLeftHint(@StringRes int id) {
        return setLeftHint(getResources().getString(id));
    }

    public SettingBar setLeftHint(CharSequence hint) {
        mLeftView.setHint(hint);
        return this;
    }

    /**
     * 设置右边的标题
     */
    public SettingBar setRightText(@StringRes int id) {
        setRightText(getResources().getString(id));
        return this;
    }

    public SettingBar setRightText(CharSequence text) {
        mRightView.setText(text);
        return this;
    }

    public CharSequence getRightText() {
        return mRightView.getText();
    }

    /**
     * 设置右边的提示
     */
    public SettingBar setRightHint(@StringRes int id) {
        return setRightHint(getResources().getString(id));
    }

    public SettingBar setRightHint(CharSequence hint) {
        mRightView.setHint(hint);
        return this;
    }

    /**
     * 设置左边的图标
     */
    public SettingBar setLeftIcon(@DrawableRes int id) {
        setLeftIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }

    public SettingBar setLeftIcon(Drawable drawable) {
        mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }

    public Drawable getLeftIcon() {
        return mLeftView.getCompoundDrawables()[0];
    }

    /**
     * 设置右边的图标
     */
    public SettingBar setRightIcon(@DrawableRes int id) {
        setRightIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }

    public SettingBar setRightIcon(Drawable drawable) {
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }

    public Drawable getRightIcon() {
        return mRightView.getCompoundDrawables()[2];
    }

    /**
     * 设置左标题颜色
     */
    public SettingBar setLeftColor(@ColorInt int color) {
        mLeftView.setTextColor(color);
        return this;
    }

    /**
     * 设置右标题颜色
     */
    public SettingBar setRightColor(@ColorInt int color) {
        mRightView.setTextColor(color);
        return this;
    }

    /**
     * 设置左标题的文本大小
     */
    public SettingBar setLeftSize(int unit, float size) {
        mLeftView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置右标题的文本大小
     */
    public SettingBar setRightSize(int unit, float size) {
        mRightView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置分割线是否显示
     */
    public SettingBar setLineVisible(boolean visible) {
        mLineView.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置分割线的颜色
     */
    public SettingBar setLineColor(@ColorInt int color) {
        return setLineDrawable(new ColorDrawable(color));
    }
    public SettingBar setLineDrawable(Drawable drawable) {
        mLineView.setBackground(drawable);
        return this;
    }

    /**
     * 设置分割线的大小
     */
    public SettingBar setLineSize(int size) {
        ViewGroup.LayoutParams layoutParams = mLineView.getLayoutParams();
        layoutParams.height = size;
        mLineView.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 设置分割线边界
     */
    public SettingBar setLineMargin(int margin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mLineView.getLayoutParams();
        params.leftMargin = margin;
        params.rightMargin = margin;
        mLineView.setLayoutParams(params);
        return this;
    }

    /**
     * 获取主布局
     */
    public LinearLayout getMainLayout() {
        return mLinearLayout;
    }

    /**
     * 获取左标题
     */
    public TextView getLeftView() {
        return mLeftView;
    }

    /**
     * 获取右标题
     */
    public TextView getRightView() {
        return mRightView;
    }

    /**
     * 获取分割线
     */
    public View getLineView() {
        return mLineView;
    }
}