package com.onederflow.blackbox;

import android.content.Context;
import android.graphics.Canvas;

public class GameWorld {

    //game characteristics
    private float progress = 0;
    private float increase = 0.1f;
    private float alpha = 1;

    //world`s layers
    private WorldLayer[] layers;

    public GameWorld(Context context) {
        layers = new WorldLayer[5];
        layers[0] = new WorldLayer(context, R.drawable.ground, 1f, GameView.maxX, (int) GameView.unitH);
        layers[1] = new WorldLayer(context, R.drawable.rocks, 0.4f, GameView.maxX, (int) GameView.unitH);
        layers[2] = new WorldLayer(context, R.drawable.clouds_1, 0.1f, GameView.maxX, (int) GameView.unitH);
        layers[3] = new WorldLayer(context, R.drawable.clouds_2, 0.08f, GameView.maxX, (int) GameView.unitH);
        layers[4] = new WorldLayer(context, R.drawable.sky, 0.0f, GameView.maxX, (int) GameView.unitH);
    }

    public void draw(Canvas canvas) {
        newIteration();
        float unitProgress = progress * alpha;
        for (int i = 4; i >= 0; i--) {
            layers[i].setProgress(unitProgress);
            layers[i].draw(canvas);
        }
    }

    public void newIteration() {
        progress += increase;
    }
}
