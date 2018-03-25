package com.brickgit.controllersgdx.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Daniel Lin on 24/03/2018.
 */

public class CrossController extends BaseController {

    private static TextureAtlas atlas =
            new TextureAtlas(Gdx.files.internal("cross-controllers.txt"));

    private Sprite spriteCross;

    private Vector2 direction = new Vector2();
    private float degrees = 0;

    private float degreesRange = 20;

    public CrossController(ControllerType type, float x, float y) {
        super();

        initSprites(type);
        setBounds(x, y, spriteCross.getWidth(), spriteCross.getHeight());
        resetDirection();

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                calculate();
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                calculate();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                resetDirection();
            }
        });
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public float getDegrees() {
        return degrees;
    }

    @Override
    public float getStrength() {
        return 1;
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
                spriteCross,
                getX(),
                getY(),
                getWidth(),
                getHeight());
    }

    private void initSprites(ControllerType type) {
        switch (type) {
            case FLAT_DARK:
                spriteCross = atlas.createSprite("cross-flat-dark");
                break;
            case FLAT_LIGHT:
                spriteCross = atlas.createSprite("cross-flat-light");
                break;
            case LINE_DARK:
                spriteCross = atlas.createSprite("cross-line-dark");
                break;
            case LINE_LIGHT:
                spriteCross = atlas.createSprite("cross-line-light");
                break;
            case SHADED_DARK:
                spriteCross = atlas.createSprite("cross-shaded-dark");
                break;
            case SHADED_LIGHT:
                spriteCross = atlas.createSprite("cross-shaded-light");
                break;
            case TRANSPARENT_DARK:
                spriteCross = atlas.createSprite("cross-trans-dark");
                break;
            case TRANSPARENT_LIGHT:
                spriteCross = atlas.createSprite("cross-trans-light");
                break;
        }
    }

    private void calculate() {
        if (degreesFinger <= degreesRange || degreesFinger >= 360 - degreesRange) {
            direction.set(1, 0);
            degrees = 0;
        }
        else if (degreesFinger <= 90 + degreesRange && degreesFinger >= 90 - degreesFinger) {
            direction.set(0, 1);
            degrees = 90;
        }
        else if (degreesFinger <= 180 + degreesRange && degreesFinger >= 180 - degreesFinger) {
            direction.set(-1, 0);
            degrees = 180;
        }
        else if (degreesFinger <= 270 + degreesRange && degreesFinger >= 270 - degreesFinger) {
            direction.set(0, -1);
            degrees = 270;
        }
        else {
            direction.set(0, 0);
            degrees = 0;
        }
    }

    private void resetDirection() {
        direction.set(0, 0);
        degrees = 0;
    }
}
