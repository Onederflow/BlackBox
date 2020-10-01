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

public class GameView extends SurfaceView implements Runnable{
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Thread gameThread = null;

    private GameWorld gameWord;
    private GameBall gameBall;

    private Paint paint;
    private Paint paintRed = new Paint();
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;

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
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                gameBall = new GameBall(getContext());
                gameWord = new GameWorld(getContext());
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            gameWord.draw(canvas);
            gameBall.draw(canvas);

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void ClickRegistered(){
        System.out.println("Click");
       // gameRoad.newRevolution();
    }

}
