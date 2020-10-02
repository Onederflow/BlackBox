package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Canvas;

public class GameWorld {

    //game characteristics
    public float progress = 0;
    private float increase = 0.1f;
    private float alpha = 1;

    private int iteration = 15;
    private int cchangeit = 30;
    private int unitOneIter = 1;

    //world`s layers
    private WorldLayer[] layers;
    private GameBlocks gameBlocks;

    public GameWorld(Context context) {
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
        if(iteration % cchangeit == 0){
            unitOneIter++;
            gameBlocks.newGen(unitOneIter + 6);
            if(gameBlocks.isCollision(unitOneIter)){
                increase = 0;
                return false;
            }
        }
        return true;
    }

    public void click()
    {
        gameBlocks.click();
    }
}
