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
        gameView = new GameView(this); // creation gameView
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // found gameLayout
        gameLayout.addView(gameView); // add to gameView
        gameLayout.setOnTouchListener(this);
    }

    public boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) {
            case R.id.gameLayout:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        gameView.ClickRegistered();
                        break;
                }
                break;
        }
        return true;
    }
}