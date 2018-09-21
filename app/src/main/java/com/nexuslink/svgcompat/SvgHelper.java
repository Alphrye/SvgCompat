package com.nexuslink.svgcompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Svg帮助类
 * @author yuanrui
 * @date 2018/9/19
 */
public class SvgHelper {

    private static SvgHelper sInstance;
    private Resources mResource;
    private Context mContext;

    private SvgHelper(Context context) {
        //防止传入非Application Context导致内存泄露
        this.mContext = context.getApplicationContext();
        this.mResource = mContext.getResources();
    }

    public synchronized static SvgHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SvgHelper(context);
        }
        return sInstance;
    }

    public void displayImageVectorDrawable (ImageView imageView, int vectorDrawableId) {
        displayImageVectorDrawable (imageView, vectorDrawableId, -1);
    }

    public void displayImageVectorDrawable (ImageView imageView, int vectorDrawableId, int tintColorId) {
        if (imageView == null) {
            return;
        }

        Drawable drawable = AppCompatResources.getDrawable(mContext, vectorDrawableId);
        if (drawable == null) {
            imageView.setImageDrawable(null);
            return;
        }
        //让着色不共享(不然会导致着一处着色，其他地方被同步着色)
        drawable = drawable.mutate();
        //兼容5.0以下的tint
        Drawable drawableWrap = DrawableCompat.wrap(drawable);
        if (tintColorId != -1) {
            DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(mContext, tintColorId));
        }

        imageView.setImageDrawable(drawableWrap);
    }

    public void displayTextVectorLeftDrawable(TextView textView, int vectorDrawableLeftId) {
        displayTextVectorCompoundDrawables(textView,
                vectorDrawableLeftId, -1, -1, -1,
                -1, -1, -1, -1);
    }

    public void displayTextVectorRightDrawable(TextView textView, int vectorDrawableRightId) {
        displayTextVectorCompoundDrawables(textView,
                -1, -1, vectorDrawableRightId, -1,
                -1, -1, -1, -1);
    }

    public void displayTextVectorTopDrawable(TextView textView, int vectorDrawableTopId) {
        displayTextVectorCompoundDrawables(textView,
                -1, vectorDrawableTopId, -1, -1,
                -1, -1, -1, -1);
    }

    public void displayTextVectorBottomDrawable(TextView textView, int vectorDrawableBottomId) {
        displayTextVectorCompoundDrawables(textView,
                -1, -1, -1, vectorDrawableBottomId,
                -1, -1, -1, -1);
    }

    public void displayTextVectorLeftDrawable(TextView textView, int vectorDrawableLeftId, int leftTintColorId) {
        displayTextVectorCompoundDrawables(textView,
                vectorDrawableLeftId, -1, -1, -1,
                leftTintColorId, -1, -1, -1);
    }

    public void displayTextVectorRightDrawable(TextView textView, int vectorDrawableRightId, int rightTintColorId) {
        displayTextVectorCompoundDrawables(textView,
                -1, -1, vectorDrawableRightId, -1,
                -1, -1, rightTintColorId, -1);
    }

    public void displayTextVectorTopDrawable(TextView textView, int vectorDrawableTopId, int topTintColorId) {
        displayTextVectorCompoundDrawables(textView,
                -1, vectorDrawableTopId, -1, -1,
                -1, topTintColorId, -1, -1);
    }

    public void displayTextVectorBottomDrawable(TextView textView, int vectorDrawableBottomId, int bottomTintColorId) {
        displayTextVectorCompoundDrawables(textView,
                -1, -1, -1, vectorDrawableBottomId,
                -1, -1, -1, bottomTintColorId);
    }

    public void displayTextVectorCompoundDrawables(TextView textView, int vectorDrawableLeftId, int vectorDrawableTopId, int vectorDrawableRightId, int vectorDrawableBottomId,
                                                   int leftTintColorId, int topTintColorId, int rightTintColorId, int bottomTintColorId) {
        if (textView == null) {
            return;
        }

        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;

        if (vectorDrawableLeftId != -1) {
            drawableLeft = AppCompatResources.getDrawable(mContext, vectorDrawableLeftId);
            if (drawableLeft != null) {
                drawableLeft = drawableLeft.mutate();
                if (leftTintColorId != -1) {
                    drawableLeft.setColorFilter(mResource.getColor(leftTintColorId), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (vectorDrawableTopId != -1) {
            drawableTop = AppCompatResources.getDrawable(mContext, vectorDrawableTopId);
            if (drawableTop != null) {
                drawableTop = drawableTop.mutate();
                if (topTintColorId != -1) {
                    drawableTop.setColorFilter(mResource.getColor(topTintColorId), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (vectorDrawableRightId != -1) {
            drawableRight = AppCompatResources.getDrawable(mContext, vectorDrawableRightId);
            if (drawableRight != null) {
                drawableRight = drawableRight.mutate();
                if (rightTintColorId != -1) {
                    drawableRight.setColorFilter(mResource.getColor(rightTintColorId), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (vectorDrawableBottomId != -1) {
            drawableBottom = AppCompatResources.getDrawable(mContext, vectorDrawableBottomId);
            if (drawableBottom != null) {
                drawableBottom = drawableBottom.mutate();
                if (bottomTintColorId != -1) {
                    drawableBottom.setColorFilter(mResource.getColor(bottomTintColorId), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }
}
