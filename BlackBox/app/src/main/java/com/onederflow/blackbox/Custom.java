package com.onederflow.blackbox;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

public class Custom {
    private static final int FLIP_VERTICAL = 121;
    private static final int FLIP_HORIZONTAL = 232;

    public static Bitmap flipImage(Bitmap src, int type) {
        Matrix matrix = new Matrix();
        // по вертикали
        if(type == FLIP_VERTICAL) {
            // y = y * -1
            matrix.preScale(1.0f, -1.0f);
        }
        // по горизонтали
        else if(type == FLIP_HORIZONTAL) {
            // x = x * -1
            matrix.preScale(-1.0f, 1.0f);

        } else {
            return null;
        }
        // Возвращаем трансформированную картинку
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }



    public static Bitmap getBitmapFromDrawable(@Nullable Drawable drawable, int width, int height) {
        Bitmap bitmap;

        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {

            //int width = (int) (drawable.getIntrinsicWidth() * ratio);
            //int height = (int) (drawable.getIntrinsicHeight() * ratio);

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * Calculate the ratio to multiply the Bitmap size with, for it to be the maximum size of
     * "expected".
     *
     * @param height   Original Bitmap height
     * @param width    Original Bitmap width
     * @param expected Expected maximum size.
     * @return If height and with equals 0, 1 is return. Otherwise the ratio is returned.
     * The ration is base on the greatest side so the image will always be the maximum size.
     */
    public static float calculateRatio(int height, int width, int expected) {
        if (height == 0 && width == 0) {
            return 1f;
        }
        return (height > width)
                ? expected / (float) width
                : expected / (float) height;
    }
}
