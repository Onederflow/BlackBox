package com.onederflow.blackbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatDrawableManager;

import java.util.ArrayList;

public class GameBlocks {
    private Block[] blocks;
    private float realPos = 0;

    private float unitWidth = 1;
    private float unitHeight = 1;
    private float unitMax = 10;

    private int realHeight = 10;
    private int realWidth = 10;

    private int realMax;

    private float x_different = 2;

    //for drawing
    private int color_main = Color.RED;
    private Paint paint;
    protected int[] bitmapIdRed; // id
    protected Bitmap bitmapRed; // picture
    protected int[] bitmapIdBlue; // id
    protected Bitmap bitmapBlue; // picture

    private int i_gen = 0;
    private  int i_max = 6;

    public GameBlocks(Context context) {
        blocks = new Block[i_max];
        for(int i = 0; i < i_max; i++){
            blocks[i] = new Block(0, 0);
        }

        paint = new Paint();
        paint.setColor(color_main);
        unitMax = GameView.maxX;
        realMax = (int) GameView.unitH;


        @SuppressLint("RestrictedApi") Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, R.drawable.block_red);
        unitWidth = unitHeight * (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
        realHeight = (int) (unitHeight * realMax);
        realWidth = (int) (unitWidth * realMax);
        bitmapRed = Custom.getBitmapFromDrawable(drawable, realWidth + 2, realHeight);
        @SuppressLint("RestrictedApi") Drawable drawable_ref = AppCompatDrawableManager.get().getDrawable(context, R.drawable.block_blue);
        unitWidth = unitHeight * (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
        realHeight = (int) (unitHeight * realMax);
        realWidth = (int) (unitWidth * realMax);
        bitmapBlue = Custom.getBitmapFromDrawable(drawable_ref, realWidth + 2, realHeight);
    }

    public void newGen(int pos) {

        blocks[i_gen].newGen(pos, 95);
        i_gen++;
        if (i_gen >= i_max) {
            i_gen = 0;
        }
    }

    public void draw(Canvas canvas) {
        for (Block block : blocks) {
            float pos_x = (float) ((block.position * (0.2f * 15.0) - realPos)) - x_different;
            if(block.topType == 1){
                canvas.drawBitmap(bitmapBlue, (pos_x ) * GameView.unitH, (int)((10.0 - unitHeight) * GameView.unitH), paint);
            } else if(block.topType == 2){
                canvas.drawBitmap(bitmapRed, (pos_x ) * GameView.unitH, (int)((10.0 - unitHeight) * GameView.unitH), paint);
            }
            if(block.sideType == 1){
                canvas.drawBitmap(bitmapBlue, (pos_x ) * GameView.unitH, 10.0f * GameView.unitH, paint);
            } else if(block.sideType == 2){
                canvas.drawBitmap(bitmapRed, (pos_x ) * GameView.unitH, 10.0f * GameView.unitH, paint);
            }
        };
    }

    public void setPosition(float pos){
        realPos = pos;
    }

    public void click(){
        for (Block block : blocks) {
            block.swap();
        }
    }

    public boolean isCollision(int pos){
        for (Block block : blocks) {
            if(block.isCollision(pos)){
                return true;
            }
        }
        return false;
    }
}
