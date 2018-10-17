package com.nexuslink.svgcompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.widget.ImageView;

public class SvgImage {

    private SvgImage(ImageView image, int vectorDrawableResId, int colorTint) {
        if (image == null || vectorDrawableResId == -1) {
            return;
        }
        Context context = image.getContext();
        if (context == null) {
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(context.getApplicationContext(), vectorDrawableResId);
        if (drawable == null) {
            image.setImageDrawable(null);
            return;
        }
        //让着色不共享(不然会导致着一处着色，其他地方被同步着色)
        drawable = drawable.mutate();
        //兼容5.0以下的tint
        Drawable drawableWrap = DrawableCompat.wrap(drawable);
        if (colorTint != -1) {
            DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context.getApplicationContext(), colorTint));
        }
        image.setImageDrawable(drawableWrap);
    }

    public static class Builder {
        private ImageView mImageView;
        private int mVectorDrawableResId = -1;
        private int mColorTint = -1;

        public SvgImage.Builder bind (ImageView image) {
            mImageView = image;
            return this;
        }

        public SvgImage.Builder drawable (int vectorDrawableResId) {
            mVectorDrawableResId = vectorDrawableResId;
            return this;
        }

        public SvgImage.Builder tint (int color) {
            mColorTint = color;
            return this;
        }

        public SvgImage build () {
            return new SvgImage(mImageView, mVectorDrawableResId, mColorTint);
        }
    }
}
