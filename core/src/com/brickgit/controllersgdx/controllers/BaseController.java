package com.brickgit.controllersgdx.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by Daniel Lin on 23/03/2018.
 */

public abstract class BaseController extends Actor {

    protected Vector2 directionFinger = new Vector2();
    protected float degreesFinger;
    protected float radiusFinger;

    public BaseController() {
        super();

        reset();

        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 finger = new Vector2(x, y);
                calculate(finger);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Vector2 finger = new Vector2(x, y);
                calculate(finger);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                reset();
            }
        });
    }

    public abstract Vector2 getDirection();

    public abstract float getDegrees();

    public abstract float getStrength();

    private void calculate(Vector2 finger) {
        float offsetX = finger.x - getWidth() / 2;
        float offsetY = finger.y - getHeight() / 2;

        float theta = (float) Math.atan2(offsetY, offsetX);
        directionFinger.set((float) Math.cos(theta), (float) Math.sin(theta));

        degreesFinger = (float) (theta / Math.PI * 180) + (theta > 0 ? 0 : 360);
        radiusFinger = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
    }

    private void reset() {
        directionFinger.set(0, 0);

        degreesFinger = 0;
        radiusFinger = 0;
    }
}
