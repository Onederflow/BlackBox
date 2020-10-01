package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameBall {
    //variables
    private float jumpHeight = 2f;
    private int timerTicksCount = 29;
    private int timerTicksNow = 0;

    //for drawing
    private int color_main = Color.RED;
    private Paint paint;
    protected int[] bitmapId; // id картинки
    protected Bitmap[] bitmap; // картинка
    private int a_height;


    public GameBall(Context context) {
        paint = new Paint();
        paint.setColor(color_main);

        bitmapId = new int[]{
                R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15,
                R.drawable.s16, R.drawable.s17, R.drawable.s18, R.drawable.s19, R.drawable.s20,
                R.drawable.s21, R.drawable.s22, R.drawable.s23, R.drawable.s24, R.drawable.s25,
                R.drawable.s26, R.drawable.s27, R.drawable.s28, R.drawable.s29, R.drawable.s30,
        };

        bitmap = new Bitmap[30];

        for (int i = 0; i < 30; i++){
            Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId[i]);
            a_height = (int) (cBitmap.getHeight() * jumpHeight * GameView.unitH / cBitmap.getWidth());


            bitmap[i] = Bitmap.createScaledBitmap(
                    cBitmap,(int) (jumpHeight * GameView.unitH), a_height, false);
            cBitmap.recycle();
        }
    }


    public void draw(Canvas canvas) { // рисуем картинку
        timerTickIncrease();
        canvas.drawBitmap(bitmap[timerTicksNow], 2 * GameView.unitH, 20 * GameView.unitH - a_height, paint);
    }


    private void timerTickIncrease() {
        timerTicksNow++;
        if (timerTicksNow > timerTicksCount) {
            timerTicksNow = 0;
        }
    }
}
