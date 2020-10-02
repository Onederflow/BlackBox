package com.onederflow.blackbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatDrawableManager;

public class WorldLayer {
    private static final int DEFAULT_DRAWABLE_SIZE = 1;
    //speed of current layer
    private float speed;
    private int ID;
    private int downID;
    private float unitProgress = 0;

    //unit is a metric system is our custom world
    private float unitWidth = 10;
    private float unitHeight = 10;
    private float unitMax;
    float pos_x;

    //and real .. is real android screen cords
    private int realHeight = 10;
    private int realWidth = 10;
    private int realMax;

    //Using only to drawing
    private int color = Color.BLACK;
    private Paint paint;
    protected Bitmap firstBitmap;
    protected Bitmap reflectBitmap;

    public WorldLayer(Context context,int setId, int setDownId, float setSpeed, float setUnitMax, int setRealMax) {
        paint = new Paint();
        paint.setColor(color);
        ID = setId;
        downID = setDownId;
        speed = setSpeed;
        unitMax = setUnitMax;
        realMax = setRealMax;
        @SuppressLint("RestrictedApi") Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, ID);
        unitWidth = unitHeight * (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
        realHeight = (int) (unitHeight * realMax);
        realWidth = (int) (unitWidth * realMax);
        firstBitmap = Custom.getBitmapFromDrawable(drawable, realWidth + 3, realHeight);

        @SuppressLint("RestrictedApi") Drawable drawabledown = AppCompatDrawableManager.get().getDrawable(context, downID);
        unitWidth = unitHeight * (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
        reflectBitmap = Custom.getBitmapFromDrawable(drawabledown, realWidth + 3, realHeight);
        reflectBitmap = Custom.flipImage(reflectBitmap, 121);
    }

    public void setProgress(float progress){
        unitProgress = progress;
    }

    public void draw(Canvas canvas) { // рисуем картинку

        float pos_x = ((unitProgress * speed) % unitWidth);
        canvas.drawBitmap(firstBitmap, -pos_x * GameView.unitH, 0, paint);
        canvas.drawBitmap(firstBitmap, (unitWidth - pos_x + 0.0f) * realMax, 0, paint);
        canvas.drawBitmap(firstBitmap, (2 * unitWidth - pos_x + 0.0f) * realMax, 0, paint);

        canvas.drawBitmap(reflectBitmap, -pos_x * GameView.unitH, realHeight, paint);
        canvas.drawBitmap(reflectBitmap, (unitWidth - pos_x + 0.0f) * realMax, realHeight, paint);
        canvas.drawBitmap(reflectBitmap, (2 * unitWidth - pos_x + 0.0f) * realMax, realHeight, paint);
    }
}
