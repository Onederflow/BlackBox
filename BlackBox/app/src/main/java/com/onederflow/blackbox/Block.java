package com.onederflow.blackbox;

import android.graphics.Canvas;

import java.util.Random;

public class Block {
    //if 1 - is blue , is 2 - red
    public int topType = 0;
    public int sideType = 0;
    public int position;

    private Random random = new Random();

    public Block(int pos, int complexity) {
        newGen(pos, complexity);
    }

    public void newGen(int pos ,int complexity) {
        topType = 0;
        sideType = 0;
        position = pos;
        int type = 0;
        int value = random.nextInt(100);
        if (complexity / 2 >= value) {
            type = 1;
        } else if (complexity >= value) {
            type = 2;
        }

        if (type == 1) {
            if (random.nextBoolean()) {
                topType = random.nextInt(2) + 1;
            } else {
                sideType = random.nextInt(2) + 1;
            }
        } else if (type == 2) {
            if (random.nextBoolean()) {
                topType = 1;
                sideType = 2;
            } else {
                topType = 2;
                sideType = 1;
            }
        }
    }


    public void swap(){
        int temp = topType;
        topType = sideType;
        sideType = temp;
    }

    public boolean isCollision(int pos){
        if((position ==  pos) && (topType == 2 || sideType == 1)){
            return true;
        }
        return false;
    }
}
