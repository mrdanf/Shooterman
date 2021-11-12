package com.shooterman.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class Player {
    private int column = 0;
    private int row = 0;

    private Sprite sprite;
    private TextureRegion[][] regions;

    public Player() {
        regions = TextureRegion.split(new Texture("playerWalkAnimation.png"), 213, 390);
        sprite = new Sprite(regions[row][column]);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                column++;
                if (column > 5) {
                    column = 0;
                }
                sprite.setRegion(regions[row][column]);
            }
        }, 0, 1 / 10f);
    }

    public Sprite getSprite() {
        return sprite;
    }


}
