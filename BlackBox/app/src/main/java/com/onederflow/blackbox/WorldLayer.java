package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class WorldLayer {
    //speed of current layer
    private float speed;
    private int ID;
    private float unitProgress = 0;

    //unit is a metric system is our custom world
    private float unitWidth = 10;
    private float unitHeight = 10;
    private float unitMax;

    //and real .. is real android screen cords
    private int realHeight = 10;
    private int realWidth = 10;
    private int realMax;

    //Using only to drawing
    private int color = Color.BLACK;
    private Paint paint;
    protected Bitmap firstBitmap;



    public WorldLayer(Context context,int setId, float setSpeed, float setUnitMax, int setRealMax) {
        paint = new Paint();
        paint.setColor(color);
        ID = setId;
        speed = setSpeed;
        unitMax = setUnitMax;
        realMax = setRealMax;
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), ID);
        unitWidth = unitHeight * (float) cBitmap.getWidth() / (float) cBitmap.getHeight();
        realHeight = (int) (unitHeight * realMax);
        realWidth = (int) (unitWidth * realMax);
        firstBitmap = Bitmap.createScaledBitmap(cBitmap, realWidth, realHeight, false);
        cBitmap.recycle();
    }

    public void setProgress(float progress){
        unitProgress = progress;
    }

    public void draw(Canvas canvas) { // рисуем картинку

        float pos_x = ((unitProgress * speed) % unitWidth);
        canvas.drawBitmap(firstBitmap, -pos_x * GameView.unitH, 0, paint);
        if (pos_x >= unitWidth - unitMax){
            canvas.drawBitmap(firstBitmap, (unitWidth - pos_x - 0.01f) * realMax, 0, paint);
        }
    }

    public float mod(float a, float b){
        float n = (int)(a / b);
        return a - a * n;
    }
}
