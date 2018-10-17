package com.nexuslink.svgcompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.widget.TextView;

public class SvgText {

    private SvgText(TextView textView, int leftVectorDrawableResId, int rightVectorDrawableResId, int topVectorDrawableResId, int bottomVectorDrawableResId, int leftTint, int rightTint, int topTint, int bottomTint, int compoundDrawablePadding) {
        if (textView == null) {
            return;
        }

        Context context = textView.getContext();
        if (context == null) {
            return;
        }

        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;

        Context applicationContext = context.getApplicationContext();
        Resources resources = applicationContext.getResources();
        if (resources == null) {
            return;
        }

        if (leftVectorDrawableResId != -1) {
            drawableLeft = AppCompatResources.getDrawable(applicationContext, leftVectorDrawableResId);
            if (drawableLeft != null) {
                drawableLeft = drawableLeft.mutate();
                if (leftTint != -1) {
                    drawableLeft.setColorFilter(resources.getColor(leftTint), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (topVectorDrawableResId != -1) {
            drawableTop = AppCompatResources.getDrawable(applicationContext, topVectorDrawableResId);
            if (drawableTop != null) {
                drawableTop = drawableTop.mutate();
                if (topTint != -1) {
                    drawableTop.setColorFilter(resources.getColor(topTint), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (rightVectorDrawableResId != -1) {
            drawableRight = AppCompatResources.getDrawable(applicationContext, rightVectorDrawableResId);
            if (drawableRight != null) {
                drawableRight = drawableRight.mutate();
                if (rightTint != -1) {
                    drawableRight.setColorFilter(resources.getColor(rightTint), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (bottomVectorDrawableResId != -1) {
            drawableBottom = AppCompatResources.getDrawable(applicationContext, bottomVectorDrawableResId);
            if (drawableBottom != null) {
                drawableBottom = drawableBottom.mutate();
                if (bottomTint != -1) {
                    drawableBottom.setColorFilter(resources.getColor(bottomTint), PorterDuff.Mode.SRC_IN);
                }
            }
        }

        textView.setCompoundDrawablePadding(compoundDrawablePadding);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public static class Builder {
        private TextView mTextView;
        private int mPadding;
        private int mLeftVectorDrawableResId = -1;
        private int mRightVectorDrawableResId = -1;
        private int mTopVectorDrawableResId = -1;
        private int mBottomVectorDrawableResId = -1;
        private int mLeftTint = -1;
        private int mRightTint = -1;
        private int mTopTint = -1;
        private int mBottomTint = -1;

        public SvgText.Builder bind (TextView text) {
            mTextView = text;
            return this;
        }

        public SvgText.Builder leftDrawable (int leftVectorDrawableResId) {
            mLeftVectorDrawableResId = leftVectorDrawableResId;
            return this;
        }

        public SvgText.Builder rightDrawable (int rightVectorDrawableResId) {
            mRightVectorDrawableResId = rightVectorDrawableResId;
            return this;
        }

        public SvgText.Builder topDrawable (int topVectorDrawableResId) {
            mTopVectorDrawableResId = topVectorDrawableResId;
            return this;
        }

        public SvgText.Builder bottomDrawable (int bottomVectorDrawableResId) {
            mBottomVectorDrawableResId = bottomVectorDrawableResId;
            return this;
        }

        public SvgText.Builder leftTint (int colorLeft) {
            mLeftTint = colorLeft;
            return this;
        }

        public SvgText.Builder rightTint (int colorRight) {
            mRightTint = colorRight;
            return this;
        }

        public SvgText.Builder topTint (int colorTop) {
            mTopTint = colorTop;
            return this;
        }

        public SvgText.Builder bottomTint (int colorBottom) {
            mBottomTint = colorBottom;
            return this;
        }

        public SvgText.Builder padding (int padding) {
            mPadding = padding;
            return this;
        }

        public SvgText build () {
            return new SvgText(mTextView, mLeftVectorDrawableResId, mRightVectorDrawableResId, mTopVectorDrawableResId, mBottomVectorDrawableResId, mLeftTint, mRightTint, mTopTint, mBottomTint, mPadding);
        }
    }
}
