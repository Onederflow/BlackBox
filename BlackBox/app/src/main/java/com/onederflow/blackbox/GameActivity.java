package com.onederflow.blackbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    private float x;
    private float y;

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = new GameView(this); // создаём gameView

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView
        gameLayout.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
    }

    /*
        public boolean onTouch(View v, MotionEvent event) {
            x = event.getX();
            y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // нажатие
                    break;
                case MotionEvent.ACTION_MOVE: // движение
                    break;
                case MotionEvent.ACTION_UP: // отпускание
                    gameView.ClickRegistered();
                    return false;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            return true;
        }
    */
    public boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) { // определяем какая кнопка
            case R.id.gameLayout:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("\n\n12313123\n\n");
                        break;
                    case MotionEvent.ACTION_UP:
                        gameView.ClickRegistered();
                        System.out.println("\n\n12313123\n\n");
                        break;
                }
                break;
        }
        return true;
    }
}