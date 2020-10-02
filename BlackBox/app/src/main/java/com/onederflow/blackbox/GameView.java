package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
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
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
        paintRed.setColor(Color.RED);
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
            if(!gameOver) {
                canvas = surfaceHolder.lockCanvas(); // close canvas
                canvas.drawColor(Color.BLACK); // set background to black
                gameOver = !gameWord.newIteration();

                gameWord.tick_now = gameBall.timerTicksNow;
                gameWord.draw(canvas);
                gameBall.draw(canvas);

                if (gameOver) {
                    drawGameOver();
                }
                surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
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

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(10);
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
        paintx.setColor(Color.BLACK);
        paintx.setTextSize(200);
        canvas.drawText("Game over!", 50, 400, paintx);
        paintx.setTextSize(140);
        canvas.drawText("Click to restart", 70, 600, paintx);
    }


}
