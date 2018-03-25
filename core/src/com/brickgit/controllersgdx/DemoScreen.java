package com.brickgit.controllersgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brickgit.controllersgdx.controllers.BaseController;
import com.brickgit.controllersgdx.controllers.CircleController;
import com.brickgit.controllersgdx.controllers.ControllerType;
import com.brickgit.controllersgdx.controllers.CrossController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Lin on 23/03/2018.
 */

public class DemoScreen implements Screen {

    private Stage stage;
    private Tank tank;
    private List<BaseController> controllers = new ArrayList<BaseController>();

    public DemoScreen() {
        stage = new Stage();
        stage.setViewport(
                new FitViewport(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));

        addControllers();

        tank = new Tank(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2);
        stage.addActor(tank);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta) {
        for (BaseController controller : controllers) {
            tank.move(
                    controller.getDirection(),
                    controller.getDegrees(),
                    controller.getStrength());
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void addControllers() {
        ControllerType[] controllerTypes = {
                ControllerType.FLAT_DARK, ControllerType.FLAT_LIGHT,
                ControllerType.LINE_DARK, ControllerType.LINE_LIGHT,
                ControllerType.SHADED_DARK, ControllerType.SHADED_LIGHT,
                ControllerType.TRANSPARENT_DARK, ControllerType.TRANSPARENT_LIGHT
        };

        float x = 160;
        for (ControllerType type : controllerTypes) {
            CrossController crossController =
                    new CrossController(type, x, 0);
            stage.addActor(crossController);
            controllers.add(crossController);

            CircleController circleController =
                    new CircleController(type, x, Config.WINDOW_HEIGHT - 160);
            stage.addActor(circleController);
            controllers.add(circleController);

            x += 200;
        }
    }
}
