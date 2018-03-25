package com.brickgit.controllersgdx;

import com.badlogic.gdx.Game;

/**
 * Created by Daniel Lin on 23/03/2018.
 */

public class MainGame extends Game {

	@Override
	public void create () {
		setScreen(new DemoScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {}
}
