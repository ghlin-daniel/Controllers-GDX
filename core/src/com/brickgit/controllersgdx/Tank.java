package com.brickgit.controllersgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Daniel Lin on 23/03/2018.
 */

public class Tank extends Actor {

    private Sprite spriteTank;
    private float speed = 5.0f;

    public Tank(float x, float y) {
        super();

        spriteTank = new Sprite(new Texture(Gdx.files.internal("tank.png")));

        setBounds(x, y,
                spriteTank.getWidth(), spriteTank.getHeight());
        setOrigin(Align.center);
    }

    public void move(Vector2 direction, float movingDegrees, float factorSpeed) {
        if (direction.x == 0 && direction.y == 0) return;

        float newX = getX() + direction.x * factorSpeed * speed;
        if (newX < 0)
            newX = 0;
        else if (newX + getWidth() > Config.WINDOW_WIDTH)
            newX = Config.WINDOW_WIDTH - getWidth();

        float newY = getY() + direction.y * factorSpeed * speed;
        if (newY < 0)
            newY = 0;
        else if (newY + getHeight() > Config.WINDOW_HEIGHT)
            newY = Config.WINDOW_HEIGHT - getHeight();

        setPosition(newX, newY);
        setRotation(movingDegrees);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(getColor());
        batch.draw(spriteTank, getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1,
                getRotation());
    }
}
