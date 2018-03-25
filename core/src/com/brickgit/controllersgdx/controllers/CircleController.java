package com.brickgit.controllersgdx.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Daniel Lin on 23/03/2018.
 */

public class CircleController extends BaseController {

    private static TextureAtlas atlas =
            new TextureAtlas(Gdx.files.internal("circle-controllers.txt"));

    private Sprite spriteCircle;
    private Sprite spriteAxis;

    private float radiusCircle;
    private float radiusAxis;
    private float radiusMovingRange;

    private Vector2 offsetAxis = new Vector2();
    private float strength = 0.0f;

    public CircleController(ControllerType type, float x, float y) {
        super();

        initSprites(type);

        setBounds(x, y, spriteCircle.getWidth(), spriteCircle.getHeight());

        radiusCircle = spriteCircle.getHeight() / 2;
        radiusAxis = spriteAxis.getHeight() / 2;
        radiusMovingRange = radiusCircle - radiusAxis;

        resetAxis();

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
                resetAxis();
            }
        });
    }

    @Override
    public Vector2 getDirection() {
        return directionFinger;
    }

    @Override
    public float getDegrees() {
        return degreesFinger;
    }

    @Override
    public float getStrength() {
        return strength;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(getColor());
        batch.draw(
                spriteCircle,
                getX(),
                getY(),
                getWidth(),
                getHeight());
        batch.draw(
                spriteAxis,
                getX() + offsetAxis.x,
                getY() + offsetAxis.y,
                spriteAxis.getWidth(),
                spriteAxis.getHeight());
    }

    private void initSprites(ControllerType type) {
        switch (type) {
            case FLAT_DARK:
                spriteCircle = atlas.createSprite("circle-flat-dark");
                spriteAxis = atlas.createSprite("axis-flat-dark");
                break;
            case FLAT_LIGHT:
                spriteCircle = atlas.createSprite("circle-flat-light");
                spriteAxis = atlas.createSprite("axis-flat-light");
                break;
            case LINE_DARK:
                spriteCircle = atlas.createSprite("circle-line-dark");
                spriteAxis = atlas.createSprite("axis-line-dark");
                break;
            case LINE_LIGHT:
                spriteCircle = atlas.createSprite("circle-line-light");
                spriteAxis = atlas.createSprite("axis-line-light");
                break;
            case SHADED_DARK:
                spriteCircle = atlas.createSprite("circle-shaded-dark");
                spriteAxis = atlas.createSprite("axis-shaded-dark");
                break;
            case SHADED_LIGHT:
                spriteCircle = atlas.createSprite("circle-shaded-light");
                spriteAxis = atlas.createSprite("axis-shaded-light");
                break;
            case TRANSPARENT_DARK:
                spriteCircle = atlas.createSprite("circle-trans-dark");
                spriteAxis = atlas.createSprite("axis-trans-dark");
                break;
            case TRANSPARENT_LIGHT:
                spriteCircle = atlas.createSprite("circle-trans-light");
                spriteAxis = atlas.createSprite("axis-trans-light");
                break;
        }
    }

    private void calculate(Vector2 finger) {
        if (radiusFinger < radiusMovingRange) {
            strength = radiusFinger / radiusMovingRange;
            offsetAxis.set(finger.x - radiusAxis, finger.y - radiusAxis);
        }
        else {
            strength = 1;
            float rangeX = getWidth() / 2 + radiusMovingRange * directionFinger.x;
            float rangeY = getHeight() / 2 + radiusMovingRange * directionFinger.y;
            offsetAxis.set(rangeX - radiusAxis, rangeY - radiusAxis);
        }
    }

    private void resetAxis() {
        offsetAxis.set(
                (getWidth() - spriteAxis.getWidth()) / 2,
                (getHeight() - spriteAxis.getHeight()) / 2);
        strength = 0.0f;
    }
}
