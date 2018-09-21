package com.nexuslink.svgcompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 兼容leftDrawable，rightDrawable这样的属性设置svg图
 * @author yuanrui
 * @date 2018/9/19
 */
public class SvgCompatTextView extends android.support.v7.widget.AppCompatTextView {
    public SvgCompatTextView(Context context) {
        this(context, null);
    }

    public SvgCompatTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SvgCompatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null || context == null) {
            return;
        }

        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.SvgCompatTextView);

        if (attributeArray == null) {
            return;
        }

        String colorStart = attributeArray.getString(R.styleable.SvgCompatTextView_tintStartDrawable);
        String colorEnd = attributeArray.getString(R.styleable.SvgCompatTextView_tintEndDrawable);
        String colorTop = attributeArray.getString(R.styleable.SvgCompatTextView_tintTopDrawable);
        String colorBottom = attributeArray.getString(R.styleable.SvgCompatTextView_tintBottomDrawable);

        Drawable drawableStart = null;
        Drawable drawableEnd = null;
        Drawable drawableBottom = null;
        Drawable drawableTop = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawableStart = attributeArray.getDrawable(R.styleable.SvgCompatTextView_drawableStartCompat);
            drawableEnd = attributeArray.getDrawable(R.styleable.SvgCompatTextView_drawableEndCompat);
            drawableBottom = attributeArray.getDrawable(R.styleable.SvgCompatTextView_drawableBottomCompat);
            drawableTop = attributeArray.getDrawable(R.styleable.SvgCompatTextView_drawableTopCompat);

            if (drawableStart != null) {
                drawableStart = drawableStart.mutate();
                if (!TextUtils.isEmpty(colorStart)) {
                    DrawableCompat.setTint(drawableStart.mutate(), Color.parseColor(colorStart));
                }
            }
            if (drawableEnd != null) {
                drawableEnd = drawableEnd.mutate();
                if (!TextUtils.isEmpty(colorEnd)) {
                    DrawableCompat.setTint(drawableEnd.mutate(), Color.parseColor(colorEnd));
                }
            }
            if (drawableBottom != null) {
                drawableBottom = drawableBottom.mutate();
                if (!TextUtils.isEmpty(colorBottom)) {
                    DrawableCompat.setTint(drawableBottom.mutate(), Color.parseColor(colorBottom));
                }
            }
            if (drawableTop != null && !TextUtils.isEmpty(colorEnd)) {
                drawableTop = drawableTop.mutate();
                if (!TextUtils.isEmpty(colorTop)) {
                    DrawableCompat.setTint(drawableTop.mutate(), Color.parseColor(colorTop));
                }
            }
        } else {
            int drawableStartId = attributeArray.getResourceId(R.styleable.SvgCompatTextView_drawableStartCompat, -1);
            int drawableEndId = attributeArray.getResourceId(R.styleable.SvgCompatTextView_drawableEndCompat, -1);
            int drawableBottomId = attributeArray.getResourceId(R.styleable.SvgCompatTextView_drawableBottomCompat, -1);
            int drawableTopId = attributeArray.getResourceId(R.styleable.SvgCompatTextView_drawableTopCompat, -1);
            //LOLLIPOP以下使用AppCompatResources加载
            if (drawableStartId != -1) {
                drawableStart = AppCompatResources.getDrawable(context, drawableStartId);
                if (drawableStart != null) {
                    drawableStart = drawableStart.mutate();
                    if (!TextUtils.isEmpty(colorStart)) {
                        drawableStart.setColorFilter(Color.parseColor(colorStart), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
            if (drawableEndId != -1) {
                drawableEnd = AppCompatResources.getDrawable(context, drawableEndId);
                if (drawableEnd != null) {
                    drawableEnd = drawableEnd.mutate();
                    if (!TextUtils.isEmpty(colorEnd)) {
                        drawableEnd.setColorFilter(Color.parseColor(colorEnd), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
            if (drawableBottomId != -1) {
                drawableBottom = AppCompatResources.getDrawable(context, drawableBottomId);
                if (drawableBottom != null) {
                    drawableBottom = drawableBottom.mutate();
                    if (!TextUtils.isEmpty(colorBottom)) {
                        drawableBottom.setColorFilter(Color.parseColor(colorBottom), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
            if (drawableTopId != -1) {
                drawableTop = AppCompatResources.getDrawable(context, drawableTopId);
                if (drawableTop != null) {
                    drawableTop = drawableTop.mutate();
                    if (!TextUtils.isEmpty(colorTop)) {
                        drawableTop.setColorFilter(Color.parseColor(colorTop), PorterDuff.Mode.SRC_IN);
                    }
                }
            }
        }

        setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);

        attributeArray.recycle();
    }
}
