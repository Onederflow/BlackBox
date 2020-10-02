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
    public int timerTicksCount = 14;
    public int timerTicksNow = 0;

    //for drawing
    private int color_main = Color.RED;
    private Paint paint;
    protected int[] bitmapId; // id картинки
    protected Bitmap[] bitmap; // картинка
    protected int[] bitmapRefId; // id картинки
    protected Bitmap[] bitmapRef; // картинка
    private int a_height;

    public GameBall(Context context) {
        paint = new Paint();
        paint.setColor(color_main);

        bitmapId = new int[]{
                R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
                R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
                R.drawable.d11, R.drawable.d12, R.drawable.d13, R.drawable.d14, R.drawable.d15,
                R.drawable.d16, R.drawable.d17, R.drawable.d18, R.drawable.d19, R.drawable.d20,
                R.drawable.d21, R.drawable.d22, R.drawable.d23, R.drawable.d24, R.drawable.d25,
                R.drawable.d26, R.drawable.d27, R.drawable.d28, R.drawable.d29, R.drawable.d30,
        };
        bitmapRefId = new int[]{
                R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15,
                R.drawable.s16, R.drawable.s17, R.drawable.s18, R.drawable.s19, R.drawable.s20,
                R.drawable.s21, R.drawable.s22, R.drawable.s23, R.drawable.s24, R.drawable.s25,
                R.drawable.s26, R.drawable.s27, R.drawable.s28, R.drawable.s29, R.drawable.s30,
        };

        bitmap = new Bitmap[30];
        bitmapRef = new Bitmap[30];

        for (int i = 0; i < 30; i++){
            Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId[i]);
            a_height = (int) (cBitmap.getHeight() * jumpHeight * GameView.unitH / cBitmap.getWidth());
            bitmap[i] = Bitmap.createScaledBitmap(
                    cBitmap,(int) (jumpHeight * GameView.unitH), a_height, false);
            cBitmap.recycle();

            Bitmap dBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapRefId[i]);
            a_height = (int) (dBitmap.getHeight() * jumpHeight * GameView.unitH / cBitmap.getWidth());
            bitmapRef[i] = Bitmap.createScaledBitmap(
                    dBitmap,(int) (jumpHeight * GameView.unitH), a_height, false);
            dBitmap.recycle();
            bitmapRef[i] = Custom.flipImage(bitmapRef[i], 121);
        }
    }


    public void draw(Canvas canvas) { // рисуем картинку
        timerTickIncrease();
        canvas.drawBitmap(bitmap[timerTicksNow * 2], 2 * GameView.unitH,  10 * GameView.unitH - a_height, paint);
        canvas.drawBitmap(bitmapRef[timerTicksNow * 2], 2 * GameView.unitH,  10 * GameView.unitH , paint);
    }


    private void timerTickIncrease() {
        timerTicksNow++;
        if (timerTicksNow > timerTicksCount) {
            timerTicksNow = 0;
        }
    }
}
