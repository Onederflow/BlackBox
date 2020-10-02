package com.onederflow.blackbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    public static int maxX = 20; // horizontal size
    public static int maxY = 28; // vertical size
    public static float unitW = 0; // pixels in unit
    public static float unitH = 0; // pixels in unit
    private boolean firstTime = true;
    public boolean gameRunning = true;
    public boolean gameOver = false;
    private Thread gameThread = null;

    private GameWorld gameWord;
    private GameBall gameBall;

    private Paint paint;
    private Paint paintRed = new Paint();
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        //init for drawing
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread(this);
        gameThread.start();
        paintRed.setColor(Color.RED);
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
    }

    @Override
    public void run() {
        while (gameRunning) {
            draw();
            control();
        }
    }


    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //if surface is valid

            if (firstTime) { // start
                init();
            }
            if (!gameOver) {
                canvas = surfaceHolder.lockCanvas(); // close canvas
                canvas.drawColor(Color.BLACK); // set background to black
                gameOver = !gameWord.newIteration();
                gameWord.tick_now = gameBall.timerTicksNow;
                gameWord.draw(canvas);
                gameBall.draw(canvas);
                if (gameOver) {
                    drawGameOver();
                }
                surfaceHolder.unlockCanvasAndPost(canvas); // open canvas
            }
        }
    }

    private void init() {
        firstTime = false;
        unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
        unitH = surfaceHolder.getSurfaceFrame().height() / maxY;

        gameBall = new GameBall(getContext());
        gameWord = new GameWorld(getContext(), gameBall.timerTicksCount);
    }

    private void control() {
        try {
            gameThread.sleep(10); //pause
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void ClickRegistered() {
        if (!gameOver) {
            gameWord.click();
        } else {
            firstTime = true;
            init();
            gameOver = false;
            draw();
        }
    }

    public void drawGameOver() {
        canvas.drawColor(Color.YELLOW);
        Paint paintx = new Paint();
        int bitmapId = R.drawable.gameover;
        Bitmap bitmap;
        Bitmap cBitmap = BitmapFactory.decodeResource(getContext().getResources(), bitmapId);
        int a_height = (int) (cBitmap.getHeight() * 14 * GameView.unitH / cBitmap.getWidth());
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int) (14 * GameView.unitH), a_height, false);
        cBitmap.recycle();
        canvas.drawBitmap(bitmap, 0, 8 * unitH, paint);
        int score = (int) (gameWord.progress);
        String scorestr = "your score is " + score;
        canvas.drawText(scorestr, 0.5f * GameView.unitH, 2f * GameView.unitH, paint);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = myPreferences.edit();
        int maxScore = myPreferences.getInt("SCORE", 0);

        if (maxScore > score) {
            canvas.drawText("max score is " + maxScore, 0.5f * GameView.unitH, 3.5f * GameView.unitH, paint);
        } else {
            myEditor.putInt("SCORE", score);
            myEditor.commit();
            canvas.drawText("New record!", 0.5f * GameView.unitH, 3.5f * GameView.unitH, paint);
        }

    }


}
