package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Canvas;

public class GameWorld {

    //game characteristics
    public float progress = 0;
    private float increase = 0.2f;
    private float alpha = 1;

    private int iteration = 0;
    private int tick_count = 0;
    public int tick_now = 0;
    private int unitOneIter = 2;

    //world`s layers
    private WorldLayer[] layers;
    private GameBlocks gameBlocks;

    public GameWorld(Context context, int set_tick_count) {
        tick_count = set_tick_count;
        layers = new WorldLayer[5];
        layers[0] = new WorldLayer(context, R.drawable.back2, R.drawable.downback2, 1.0f, GameView.maxX, (int) GameView.unitH);
        layers[1] = new WorldLayer(context, R.drawable.back3, R.drawable.downback3, 0.4f, GameView.maxX, (int) GameView.unitH);
        layers[2] = new WorldLayer(context, R.drawable.back5, R.drawable.downback4, 0.2f, GameView.maxX, (int) GameView.unitH);
        layers[3] = new WorldLayer(context, R.drawable.back4, R.drawable.back5, 0.05f, GameView.maxX, (int) GameView.unitH);
        layers[4] = new WorldLayer(context, R.drawable.back6,R.drawable.downback6, 0.0f, GameView.maxX, (int) GameView.unitH);

        gameBlocks = new GameBlocks(context);
    }

    public void draw(Canvas canvas) {
        float unitProgress = progress * alpha;
        for (int i = 4; i >= 0; i--) {
            layers[i].setProgress(unitProgress);
            layers[i].draw(canvas);
        }

        gameBlocks.setPosition(unitProgress);
        gameBlocks.draw(canvas);
    }

    public boolean newIteration() {
        progress += increase;
        iteration ++;
        if(tick_now == tick_count / 2){
            if(gameBlocks.isCollision(unitOneIter)){
                increase = 0;
                return false;
            }
            unitOneIter++;
            gameBlocks.newGen(unitOneIter + 5);
        }
        return true;
    }

    public void click()
    {
        gameBlocks.click();
    }
}
